package shvyn22.finalspaceapplication.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import shvyn22.finalspaceapplication.data.preferences.PreferencesManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferences: PreferencesManager
) : ViewModel() {

    val nightMode = flow {
        preferences.nightMode.collect {
            emit(it)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = AppCompatDelegate.getDefaultNightMode()
    )

    fun onToggleModeIcon() = viewModelScope.launch {
        preferences.editNightMode(
            if (nightMode.value == AppCompatDelegate.MODE_NIGHT_YES)
                AppCompatDelegate.MODE_NIGHT_NO
            else AppCompatDelegate.MODE_NIGHT_YES
        )
    }
}