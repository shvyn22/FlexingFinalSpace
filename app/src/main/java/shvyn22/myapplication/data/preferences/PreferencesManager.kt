package shvyn22.myapplication.data.preferences

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    val nightMode = dataStore.data.map {
        it[PreferencesKeys.NIGHT_MODE] ?: AppCompatDelegate.getDefaultNightMode()
    }

    suspend fun editNightMode(nightMode: Int) = dataStore.edit {
        it[PreferencesKeys.NIGHT_MODE] = nightMode
    }

    private object PreferencesKeys {
        val NIGHT_MODE = intPreferencesKey("nightMode")
    }
}