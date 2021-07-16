package shvyn22.finalspaceapplication.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.finalspaceapplication.R
import shvyn22.finalspaceapplication.api.FakeApiInterface
import shvyn22.finalspaceapplication.util.*
import shvyn22.finalspaceapplication.util.RecyclerViewItemCountAssertion.Companion.withItemCount
import java.io.File
import javax.inject.Inject

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var api: FakeApiInterface

    @Inject
    lateinit var scope: CoroutineScope

    @Before
    fun init() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        File(
            ApplicationProvider
                .getApplicationContext<Context>()
                .filesDir,
            TEST_PREFERENCES_FILENAME
        ).deleteRecursively()

        scope.cancel()
    }

    @Test
    fun launchMainActivity_StartDestinationIsInView() {
        api.initCharacters(characters)

        launchActivity<MainActivity>()

        onView(withText(character1.name))
            .check(matches(isDisplayed()))

        onView(withText(character2.name))
            .check(matches(isDisplayed()))
    }

    @Test
    fun selectEpisodeTabOnBottomNavigation_EpisodeFragmentIsInView() {
        api.initEpisodes(episodes)

        launchActivity<MainActivity>()

        onView(withId(R.id.episodeFragment))
            .perform(click())

        onView(withText(episode1.name))
            .check(matches(isDisplayed()))

        onView(withText(episode2.name))
            .check(matches(isDisplayed()))
    }

    @Test
    fun selectQuoteTabOnBottomNavigation_QuoteFragmentIsInView() {
        api.initQuotes(quotes)

        launchActivity<MainActivity>()

        onView(withId(R.id.quoteFragment))
            .perform(click())

        onView(withText(quote1.quote))
            .check(matches(isDisplayed()))

        onView(withText(quote2.quote))
            .check(matches(isDisplayed()))
    }

    @Test
    fun selectCharacterTabAfterAnotherTabs_CharacterFragmentIsInView() {
        api.initCharacters(characters)

        launchActivity<MainActivity>()

        onView(withId(R.id.episodeFragment))
            .perform(click())

        onView(withId(R.id.characterFragment))
            .perform(click())

        onView(withId(R.id.rv_characters))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_characters))
            .check(withItemCount(2))

        onView(withId(R.id.quoteFragment))
            .perform(click())

        onView(withId(R.id.characterFragment))
            .perform(click())

        onView(withId(R.id.rv_characters))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_characters))
            .check(withItemCount(2))
    }

    @Test
    fun toggleModeIcon_ValidStatesAreDisplayed() {
        launchActivity<MainActivity>()

        onView(withId(R.id.menu_mode))
            .check(
                matches(
                    withDrawable(
                        R.drawable.ic_night_mode
                    )
                )
            )

        onView(withId(R.id.menu_mode))
            .perform(click())

        onView(withId(R.id.menu_mode))
            .check(
                matches(
                    withDrawable(
                        R.drawable.ic_light_mode
                    )
                )
            )

        onView(withId(R.id.menu_mode))
            .perform(click())

        onView(withId(R.id.menu_mode))
            .check(
                matches(
                    withDrawable(
                        R.drawable.ic_night_mode
                    )
                )
            )

    }
}