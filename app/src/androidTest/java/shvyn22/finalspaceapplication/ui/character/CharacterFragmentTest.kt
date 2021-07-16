package shvyn22.finalspaceapplication.ui.character

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
import shvyn22.finalspaceapplication.data.local.dao.CharacterDao
import shvyn22.finalspaceapplication.data.util.fromCharacterDTOToModel
import shvyn22.finalspaceapplication.util.RecyclerViewItemCountAssertion.Companion.withItemCount
import shvyn22.finalspaceapplication.util.character1
import shvyn22.finalspaceapplication.util.character2
import shvyn22.finalspaceapplication.util.characters
import shvyn22.finalspaceapplication.util.launchFragmentInHiltContainer
import javax.inject.Inject

@HiltAndroidTest
class CharacterFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var api: FakeApiInterface

    @Inject
    lateinit var dao: CharacterDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun remoteIsAvailable_populateApiWith2Items_2ItemsAreInView() {
        api.changeFailBehavior(false)
        api.initCharacters(characters)

        launchFragmentInHiltContainer<CharacterFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_characters))
            .check(withItemCount(2))

        onView(withText(character1.name))
            .check(matches(isDisplayed()))

        onView(withText(character2.name))
            .check(matches(isDisplayed()))
    }

    @Test
    fun remoteIsAvailable_populateApiWith1Item_1ItemIsInView() {
        api.changeFailBehavior(false)
        api.initCharacters(listOf(character1))

        launchFragmentInHiltContainer<CharacterFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_characters))
            .check(withItemCount(1))

        onView(withText(character1.name))
            .check(matches(isDisplayed()))
    }

    @Test
    fun remoteIsAvailable_populateApiWithNoItems_NoItemsAreInView() {
        api.changeFailBehavior(false)

        launchFragmentInHiltContainer<CharacterFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_characters))
            .check(withItemCount(0))
    }

    @Test
    fun remoteIsNotAvailable_populateApiWith2Items_populateDaoWith1Item_1ItemIsInView() {
        api.changeFailBehavior(true)
        api.initCharacters(characters)

        runBlocking {
            dao.insertAll(listOf(fromCharacterDTOToModel(character1)))
        }

        launchFragmentInHiltContainer<CharacterFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_characters))
            .check(withItemCount(1))

        onView(withText(character1.name))
            .check(matches(isDisplayed()))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Error fetching data: null")))
    }

    @Test
    fun remoteIsNotAvailable_populateApiWith2Items_populateDaoWithNoItems_NoItemsAreInView() {
        api.changeFailBehavior(true)
        api.initCharacters(characters)

        launchFragmentInHiltContainer<CharacterFragment>()

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_characters))
            .check(withItemCount(0))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Error fetching data: null")))
    }

    @Test
    fun selectItem_navigateToDetailsFragment() {
        val navController = mock(NavController::class.java)

        api.changeFailBehavior(false)
        api.initCharacters(characters)

        launchFragmentInHiltContainer<CharacterFragment>(
            navController = navController
        )

        runBlocking { delay(500) } // waiting for items to get in view

        onView(withId(R.id.rv_characters))
            .perform(
                actionOnItemAtPosition<CharacterAdapter.CharacterViewHolder>(
                    0,
                    click()
                )
            )

        verify(navController).navigate(
            CharacterFragmentDirections.actionNavigateToCharacterDetails(
                fromCharacterDTOToModel(character1)
            )
        )
    }
}