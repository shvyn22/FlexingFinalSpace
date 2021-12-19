package shvyn22.flexingfinalspace.data.preferences

import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.flexingfinalspace.di.tearDownPreferencesDependencies
import javax.inject.Inject

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
    fun editInitialMode_ReturnsModeNightYes() = runBlocking {

        preferencesManager.editNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val nightMode = preferencesManager.nightMode.first()

        assertThat(
            nightMode,
            `is`(AppCompatDelegate.MODE_NIGHT_YES)
        )
    }

    @Test
    fun toggleModes_ReturnsValidMode() = runBlocking {

        preferencesManager.editNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val initialMode = preferencesManager.nightMode.first()

        assertThat(
            initialMode,
            `is`(AppCompatDelegate.MODE_NIGHT_NO)
        )

        preferencesManager.editNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val afterChangeMode = preferencesManager.nightMode.first()

        assertThat(
            afterChangeMode,
            `is`(AppCompatDelegate.MODE_NIGHT_YES)
        )
    }
}