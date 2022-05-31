package shvyn22.flexingfinalspace.data.preferences

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePreferencesManager : PreferencesManager {

    private val _isDarkTheme = MutableStateFlow(false)
    override val isDarkTheme: Flow<Boolean> = _isDarkTheme

    override suspend fun editThemePreferences(newThemeValue: Boolean) {
        _isDarkTheme.value = newThemeValue
    }
}