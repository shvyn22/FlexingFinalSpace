package shvyn22.finalspaceapplication.data.preferences

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePreferencesManager : PreferencesManager {

    private val _nightMode = MutableStateFlow(1)
    override val nightMode: Flow<Int> = _nightMode

    override suspend fun editNightMode(nightMode: Int) {
        _nightMode.value = nightMode
    }
}