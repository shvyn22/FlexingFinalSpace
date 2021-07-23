package shvyn22.finalspaceapplication.ui.quote

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.finalspaceapplication.R
import shvyn22.finalspaceapplication.api.FakeApiInterface
import shvyn22.finalspaceapplication.data.local.dao.QuoteDao
import shvyn22.finalspaceapplication.data.util.fromQuoteDTOToModel
import shvyn22.finalspaceapplication.util.*
import javax.inject.Inject

@HiltAndroidTest
class QuoteFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var api: FakeApiInterface

    @Inject
    lateinit var dao: QuoteDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun remoteIsAvailable_populateApiWith2Items_2ItemsAreInView() {
        api.changeFailBehavior(false)
        api.initQuotes(quotes)

        launchFragmentInHiltContainer<QuoteFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_quotes))
            .check(withItemCount(2))

        onView(withText(quote1.quote))
            .check(matches(isDisplayed()))

        onView(withText(quote2.quote))
            .check(matches(isDisplayed()))
    }

    @Test
    fun remoteIsAvailable_populateApiWith1Item_1ItemIsInView() {
        api.changeFailBehavior(false)
        api.initQuotes(listOf(quote1))

        launchFragmentInHiltContainer<QuoteFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_quotes))
            .check(withItemCount(1))

        onView(withText(quote1.quote))
            .check(matches(isDisplayed()))
    }

    @Test
    fun remoteIsAvailable_populateApiWithNoItems_NoItemsAreInView() {
        api.changeFailBehavior(false)

        launchFragmentInHiltContainer<QuoteFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_quotes))
            .check(withItemCount(0))
    }

    @Test
    fun remoteIsNotAvailable_populateApiWith2Items_populateDaoWith1Item_1ItemIsInView() {
        api.changeFailBehavior(true)
        api.initQuotes(quotes)

        runBlocking {
            dao.insertAll(listOf(fromQuoteDTOToModel(quote1)))
        }

        launchFragmentInHiltContainer<QuoteFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_quotes))
            .check(withItemCount(1))

        onView(withText(quote1.quote))
            .check(matches(isDisplayed()))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(
                matches(
                    withText(
                        ApplicationProvider.getApplicationContext<Context>().getString(
                            R.string.text_error, "null"
                        )
                    )
                )
            )
    }

    @Test
    fun remoteIsNotAvailable_populateApiWith2Items_populateDaoWithNoItems_NoItemsAreInView() {
        api.changeFailBehavior(true)
        api.initQuotes(quotes)

        launchFragmentInHiltContainer<QuoteFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_quotes))
            .check(withItemCount(0))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(
                matches(
                    withText(
                        ApplicationProvider.getApplicationContext<Context>().getString(
                            R.string.text_error, "null"
                        )
                    )
                )
            )
    }
}