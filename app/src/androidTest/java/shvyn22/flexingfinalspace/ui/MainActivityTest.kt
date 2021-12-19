package shvyn22.flexingfinalspace.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.api.FakeApiInterface
import shvyn22.flexingfinalspace.data.preferences.PreferencesManager
import shvyn22.flexingfinalspace.di.tearDownPreferencesDependencies
import shvyn22.flexingfinalspace.util.*
import javax.inject.Inject

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var api: FakeApiInterface

    @Inject
    lateinit var scope: CoroutineScope

    @Inject
    lateinit var preferences: PreferencesManager

    @Before
    fun init() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        tearDownPreferencesDependencies(scope)
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

        runBlocking {
            preferences.editNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        onView(withId(R.id.menu_mode))
            .check(withItemTitle(R.string.mode_night))

        onView(withId(R.id.menu_mode))
            .perform(click())

        onView(withId(R.id.menu_mode))
            .check(withItemTitle(R.string.mode_light))

        onView(withId(R.id.menu_mode))
            .perform(click())

        onView(withId(R.id.menu_mode))
            .check(withItemTitle(R.string.mode_night))
    }
}