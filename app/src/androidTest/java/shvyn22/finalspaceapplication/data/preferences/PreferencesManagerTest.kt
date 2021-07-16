package shvyn22.finalspaceapplication.data.preferences

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.test.core.app.ApplicationProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.finalspaceapplication.util.TEST_PREFERENCES_FILENAME
import java.io.File
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
        File(
            ApplicationProvider
                .getApplicationContext<Context>()
                .filesDir,
            TEST_PREFERENCES_FILENAME
        ).deleteRecursively()

        scope.cancel()
    }

    @Test
    fun editInitialMode_ReturnsModeNightYes() = runBlocking {

        preferencesManager.editNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val nightMode = preferencesManager.nightMode.first()

        assertThat(nightMode, `is`(AppCompatDelegate.MODE_NIGHT_YES))
    }

    @Test
    fun toggleModes_ReturnsValidMode() = runBlocking {

        preferencesManager.editNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val initialMode = preferencesManager.nightMode.first()

        assertThat(initialMode, `is`(AppCompatDelegate.MODE_NIGHT_NO))

        preferencesManager.editNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val afterChangeMode = preferencesManager.nightMode.first()

        assertThat(afterChangeMode, `is`(AppCompatDelegate.MODE_NIGHT_YES))
    }
}