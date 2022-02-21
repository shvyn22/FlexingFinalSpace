package shvyn22.flexingfinalspace.data.preferences

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.rxjava3.RxDataStore
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class PreferencesManagerImpl(
    private val dataStore: RxDataStore<Preferences>
) : PreferencesManager {

    override val nightMode: Observable<Int> = dataStore.data().map {
        it[PreferencesKeys.NIGHT_MODE] ?: AppCompatDelegate.getDefaultNightMode()
    }.toObservable()

    override fun editNightMode(nightMode: Int) {
        dataStore.updateDataAsync {
            val mutablePrefs = it.toMutablePreferences()
            mutablePrefs[PreferencesKeys.NIGHT_MODE] = nightMode
            Single.just(mutablePrefs)
        }
    }

    private object PreferencesKeys {
        val NIGHT_MODE = intPreferencesKey("nightMode")
    }
}