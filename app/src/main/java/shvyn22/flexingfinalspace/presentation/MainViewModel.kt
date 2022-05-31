package shvyn22.flexingfinalspace.presentation

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import shvyn22.flexingfinalspace.data.preferences.PreferencesManager
import shvyn22.flexingfinalspace.util.toLiveData
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val preferences: PreferencesManager
) : ViewModel() {

    val isDarkTheme = preferences.isDarkTheme
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .toLiveData()

    fun editThemePreferences(newThemeValue: Boolean) {
        preferences.editThemePreferences(newThemeValue)
    }
}