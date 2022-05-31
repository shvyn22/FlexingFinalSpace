package shvyn22.flexingfinalspace.presentation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.flexingfinalspace.data.preferences.FakePreferencesManager
import shvyn22.flexingfinalspace.util.MainCoroutineRule

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel
    private val prefs = FakePreferencesManager()

    @Before
    fun setup() {
        viewModel = MainViewModel(prefs)
    }

    @Test
    fun editInitialMode_ReturnsModeNightYes() = runTest {
        viewModel.editThemePreferences(true)

        assertThat(
            viewModel.isDarkTheme.value,
            `is`(true)
        )
    }

    @Test
    fun toggleModes_ReturnsValidMode() = runTest {
        viewModel.editThemePreferences(true)

        assertThat(
            viewModel.isDarkTheme.value,
            `is`(true)
        )

        viewModel.editThemePreferences(false)

        assertThat(
            viewModel.isDarkTheme.value,
            `is`(false)
        )
    }
}