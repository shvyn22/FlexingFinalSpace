package shvyn22.finalspaceapplication.ui.episode

import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import shvyn22.finalspaceapplication.R
import shvyn22.finalspaceapplication.api.FakeApiInterface
import shvyn22.finalspaceapplication.data.local.dao.EpisodeDao
import shvyn22.finalspaceapplication.data.util.fromEpisodeDTOToModel
import shvyn22.finalspaceapplication.util.RecyclerViewItemCountAssertion.Companion.withItemCount
import shvyn22.finalspaceapplication.util.episode1
import shvyn22.finalspaceapplication.util.episodes
import shvyn22.finalspaceapplication.util.launchFragmentInHiltContainer
import javax.inject.Inject

@HiltAndroidTest
class EpisodeFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var api: FakeApiInterface

    @Inject
    lateinit var dao: EpisodeDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun remoteIsAvailable_populateApiWith2Items_2ItemsAreInView() {
        api.changeFailBehavior(false)
        api.initEpisodes(episodes)

        launchFragmentInHiltContainer<EpisodeFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_episodes))
            .check(withItemCount(2))

        onView(withText(episode1.name))
            .check(matches(isDisplayed()))

        onView(withText(episode1.name))
            .check(matches(isDisplayed()))
    }

    @Test
    fun remoteIsAvailable_populateApiWith1Item_1ItemIsInView() {
        api.changeFailBehavior(false)
        api.initEpisodes(listOf(episode1))

        launchFragmentInHiltContainer<EpisodeFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_episodes))
            .check(withItemCount(1))

        onView(withText(episode1.name))
            .check(matches(isDisplayed()))
    }

    @Test
    fun remoteIsAvailable_populateApiWithNoItems_NoItemsAreInView() {
        api.changeFailBehavior(false)

        launchFragmentInHiltContainer<EpisodeFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_episodes))
            .check(withItemCount(0))
    }

    @Test
    fun remoteIsNotAvailable_populateApiWith2Items_populateDaoWith1Item_1ItemIsInView() {
        api.changeFailBehavior(true)
        api.initEpisodes(episodes)

        runBlocking {
            dao.insertAll(listOf(fromEpisodeDTOToModel(episode1)))
        }

        launchFragmentInHiltContainer<EpisodeFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_episodes))
            .check(withItemCount(1))

        onView(withText(episode1.name))
            .check(matches(isDisplayed()))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Error fetching data: null")))
    }

    @Test
    fun remoteIsNotAvailable_populateApiWith2Items_populateDaoWithNoItems_NoItemsAreInView() {
        api.changeFailBehavior(true)
        api.initEpisodes(episodes)

        launchFragmentInHiltContainer<EpisodeFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_episodes))
            .check(withItemCount(0))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Error fetching data: null")))
    }

    @Test
    fun selectItem_navigateToDetailsFragment() {
        val navController = mock(NavController::class.java)

        api.changeFailBehavior(false)
        api.initEpisodes(episodes)

        launchFragmentInHiltContainer<EpisodeFragment>(
            navController = navController
        )

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_episodes))
            .perform(
                actionOnItemAtPosition<EpisodeAdapter.EpisodeViewHolder>(
                    0,
                    click()
                )
            )

        verify(navController).navigate(
            EpisodeFragmentDirections.actionNavigateToEpisodeDetails(
                fromEpisodeDTOToModel(episode1)
            )
        )
    }
}