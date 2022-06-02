package shvyn22.flexingfinalspace.presentation.quote

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.data.local.dao.QuoteDao
import shvyn22.flexingfinalspace.data.remote.api.ApiService
import shvyn22.flexingfinalspace.data.remote.api.FakeApiService
import shvyn22.flexingfinalspace.di.tearDownPreferencesDependencies
import shvyn22.flexingfinalspace.presentation.MainActivity
import shvyn22.flexingfinalspace.presentation.main.MainScreen
import shvyn22.flexingfinalspace.util.quote1
import shvyn22.flexingfinalspace.util.quote1Model
import shvyn22.flexingfinalspace.util.quote2Model
import shvyn22.flexingfinalspace.util.quotes
import javax.inject.Inject

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@HiltAndroidTest
class QuoteScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var api: ApiService
    private val fakeApi: FakeApiService get() = api as FakeApiService

    @Inject
    lateinit var dao: QuoteDao

    @Inject
    lateinit var scope: CoroutineScope

    @Before
    fun init() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        tearDownPreferencesDependencies(scope)
    }

    @Test
    fun remoteIsAvailable_populateApiWith2Items_2ItemsAreInView() {
        fakeApi.changeFailBehavior(false)
        fakeApi.initQuotes(quotes)

        composeRule.apply {
            setContent {
                MainScreen(onToggleTheme = {})
            }

            onNodeWithText(
                ApplicationProvider.getApplicationContext<Context>()
                    .getString(R.string.nav_quotes)
            ).performClick()

            onNodeWithText(quote1Model.quote)
                .assertIsDisplayed()

            onNodeWithText(quote2Model.quote)
                .assertIsDisplayed()
        }
    }

    @Test
    fun remoteIsAvailable_populateApiWith1Item_1ItemIsInView() {
        fakeApi.changeFailBehavior(false)
        fakeApi.initQuotes(listOf(quote1))

        composeRule.apply {
            setContent {
                MainScreen(onToggleTheme = {})
            }

            onNodeWithText(
                ApplicationProvider.getApplicationContext<Context>()
                    .getString(R.string.nav_quotes)
            ).performClick()

            onNodeWithText(
                ApplicationProvider.getApplicationContext<Context>()
                    .getString(R.string.nav_quotes)
            ).performClick()

            onNodeWithText(quote1Model.quote)
                .assertIsDisplayed()
        }
    }

    @Test
    fun remoteIsAvailable_populateApiWithNoItems_NoItemsAreInView() {
        fakeApi.changeFailBehavior(false)

        composeRule.apply {
            setContent {
                MainScreen(onToggleTheme = {})
            }

            onNodeWithText(
                ApplicationProvider.getApplicationContext<Context>()
                    .getString(R.string.nav_quotes)
            ).performClick()

            // Note: this can't really assert the items aren't in view only because of
            // providing such initial data, but not because of some unexpected error.
            // Used only because of lack of sufficient assertion functions for lazy-layouts.
            onNodeWithText(quote1Model.quote)
                .assertDoesNotExist()

            onNodeWithText(quote2Model.quote)
                .assertDoesNotExist()
        }
    }

    @Test
    fun remoteIsNotAvailable_populateApiWith2Items_populateDaoWith1Item_1ItemIsInView() {
        fakeApi.changeFailBehavior(true)
        fakeApi.initQuotes(quotes)

        runBlocking { dao.insertAll(listOf(quote1Model)) }

        composeRule.apply {
            setContent {
                MainScreen(onToggleTheme = {})
            }

            onNodeWithText(
                ApplicationProvider.getApplicationContext<Context>()
                    .getString(R.string.nav_quotes)
            ).performClick()

            onNodeWithText(quote1Model.quote)
                .assertIsDisplayed()

            onNodeWithText(quote2Model.quote)
                .assertDoesNotExist()
        }
    }

    @Test
    fun remoteIsNotAvailable_populateApiWith2Items_populateDaoWithNoItems_NoItemsAreInView() {
        fakeApi.changeFailBehavior(true)
        fakeApi.initQuotes(quotes)

        composeRule.apply {
            setContent {
                MainScreen(onToggleTheme = {})
            }

            onNodeWithText(
                ApplicationProvider.getApplicationContext<Context>()
                    .getString(R.string.nav_quotes)
            ).performClick()

            // Note: this can't really assert the items aren't in view only because of
            // providing such initial data, but not because of some unexpected error.
            // Used only because of lack of sufficient assertion functions for lazy-layouts.
            onNodeWithText(quote1Model.quote)
                .assertDoesNotExist()

            onNodeWithText(quote2Model.quote)
                .assertDoesNotExist()
        }
    }
}