package shvyn22.flexingfinalspace.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(FakePreferencesManager())
    }

    @Test
    fun editInitialMode_ReturnsModeNightYes() = runBlocking {
        viewModel.onToggleModeIcon()

        assertThat(
            viewModel.nightMode.value,
            `is`(AppCompatDelegate.MODE_NIGHT_YES)
        )
    }

    @Test
    fun toggleModes_ReturnsValidMode() = runBlocking {
        viewModel.onToggleModeIcon()

        assertThat(
            viewModel.nightMode.value,
            `is`(AppCompatDelegate.MODE_NIGHT_YES)
        )

        viewModel.onToggleModeIcon()

        assertThat(
            viewModel.nightMode.value,
            `is`(AppCompatDelegate.MODE_NIGHT_NO)
        )
    }
}