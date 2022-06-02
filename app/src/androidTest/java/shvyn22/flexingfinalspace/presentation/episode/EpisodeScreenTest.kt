package shvyn22.flexingfinalspace.presentation.episode

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.data.remote.api.ApiService
import shvyn22.flexingfinalspace.data.remote.api.FakeApiService
import shvyn22.flexingfinalspace.data.local.dao.EpisodeDao
import shvyn22.flexingfinalspace.di.tearDownPreferencesDependencies
import shvyn22.flexingfinalspace.presentation.MainActivity
import shvyn22.flexingfinalspace.util.episode1
import shvyn22.flexingfinalspace.util.episode1Model
import shvyn22.flexingfinalspace.util.episode2Model
import shvyn22.flexingfinalspace.util.episodes
import javax.inject.Inject

@ExperimentalFoundationApi
@HiltAndroidTest
class EpisodeScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var api: ApiService
    private val fakeApi: FakeApiService get() = api as FakeApiService

    @Inject
    lateinit var dao: EpisodeDao

    @Inject
    lateinit var scope: CoroutineScope

    @Before
    fun init() {
        hiltRule.inject()
        composeRule
            .onNodeWithText(
                ApplicationProvider.getApplicationContext<Context>()
                    .getString(R.string.nav_episodes)
            )
            .performClick()
    }

    @After
    fun tearDown() {
        tearDownPreferencesDependencies(scope)
    }

    @Test
    fun remoteIsAvailable_populateApiWith2Items_2ItemsAreInView() {
        fakeApi.changeFailBehavior(false)
        fakeApi.initEpisodes(episodes)

        composeRule.apply {
            onNodeWithText(episode1Model.name)
                .assertIsDisplayed()

            onNodeWithText(episode2Model.name)
                .assertIsDisplayed()
        }
    }

    @Test
    fun remoteIsAvailable_populateApiWith1Item_1ItemIsInView() {
        fakeApi.changeFailBehavior(false)
        fakeApi.initEpisodes(listOf(episode1))

        composeRule.apply {
            onNodeWithText(episode1Model.name)
                .assertIsDisplayed()
        }
    }

    @Test
    fun remoteIsAvailable_populateApiWithNoItems_NoItemsAreInView() {
        fakeApi.changeFailBehavior(false)

        composeRule.apply {
            // Note: this can't really assert the items aren't in view only because of
            // providing such initial data, but not because of some unexpected error.
            // Used only because of lack of sufficient assertion functions for lazy-layouts.
            onNodeWithText(episode1Model.name)
                .assertDoesNotExist()

            onNodeWithText(episode2Model.name)
                .assertDoesNotExist()
        }
    }

    @Test
    fun remoteIsNotAvailable_populateApiWith2Items_populateDaoWith1Item_1ItemIsInView() {
        fakeApi.changeFailBehavior(true)
        fakeApi.initEpisodes(episodes)

        runBlocking {
            dao.insertAll(listOf(episode1Model))
        }

        composeRule.apply {
            onNodeWithText(episode1Model.name)
                .assertIsDisplayed()

            onNodeWithText(episode2Model.name)
                .assertDoesNotExist()
        }
    }

    @Test
    fun remoteIsNotAvailable_populateApiWith2Items_populateDaoWithNoItems_NoItemsAreInView() {
        fakeApi.changeFailBehavior(true)
        fakeApi.initEpisodes(episodes)

        composeRule.apply {
            // Note: this can't really assert the items aren't in view only because of
            // providing such initial data, but not because of some unexpected error.
            // Used only because of lack of sufficient assertion functions for lazy-layouts.
            onNodeWithText(episode1Model.name)
                .assertDoesNotExist()

            onNodeWithText(episode2Model.name)
                .assertDoesNotExist()
        }
    }
}