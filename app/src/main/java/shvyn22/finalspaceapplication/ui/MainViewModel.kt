package shvyn22.finalspaceapplication.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import shvyn22.finalspaceapplication.data.preferences.PreferencesManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferences: PreferencesManager
): ViewModel() {

    private val _nightMode = MutableStateFlow(AppCompatDelegate.getDefaultNightMode())
    val nightMode: StateFlow<Int> get() = _nightMode

    init {
        viewModelScope.launch {
            preferences.nightMode.collect {
                _nightMode.value = it
            }
        }
    }

    fun onToggleModeIcon() = viewModelScope.launch {
        preferences.editNightMode(
            if (_nightMode.value == AppCompatDelegate.MODE_NIGHT_YES)
                AppCompatDelegate.MODE_NIGHT_NO
            else AppCompatDelegate.MODE_NIGHT_YES
        )
    }
}