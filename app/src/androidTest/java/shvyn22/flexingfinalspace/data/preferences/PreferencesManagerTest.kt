package shvyn22.flexingfinalspace.data.preferences

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.flexingfinalspace.di.tearDownPreferencesDependencies
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class PreferencesManagerTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var scope: CoroutineScope

    @Inject
    lateinit var preferencesManager: PreferencesManager

    @Before
    fun init() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        tearDownPreferencesDependencies(scope)
    }

    @Test
    fun editInitialMode_ReturnsModeNightYes() = runTest {
        preferencesManager.editThemePreferences(true)

        val nightMode = preferencesManager.isDarkTheme.first()

        assertThat(
            nightMode,
            `is`(true)
        )
    }

    @Test
    fun toggleModes_ReturnsValidMode() = runTest {
        preferencesManager.editThemePreferences(false)

        val initialMode = preferencesManager.isDarkTheme.first()

        assertThat(
            initialMode,
            `is`(false)
        )

        preferencesManager.editThemePreferences(true)

        val afterChangeMode = preferencesManager.isDarkTheme.first()

        assertThat(
            afterChangeMode,
            `is`(true)
        )
    }
}