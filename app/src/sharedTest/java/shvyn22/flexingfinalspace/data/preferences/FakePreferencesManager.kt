package shvyn22.flexingfinalspace.data.preferences

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakePreferencesManager : PreferencesManager {

    private val _isDarkTheme = MutableStateFlow(false)
    override val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    override suspend fun editThemePreferences(newThemeValue: Boolean) {
        _isDarkTheme.value = newThemeValue
    }
}