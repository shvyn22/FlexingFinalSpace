package shvyn22.flexingfinalspace.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import shvyn22.flexingfinalspace.data.preferences.PreferencesManager
import shvyn22.flexingfinalspace.util.toLiveData
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val preferences: PreferencesManager
) : ViewModel() {

    val nightMode = preferences.nightMode
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .toLiveData()

    fun onToggleModeIcon() {
        preferences.editNightMode(
            if (nightMode.value == AppCompatDelegate.MODE_NIGHT_YES)
                AppCompatDelegate.MODE_NIGHT_NO
            else AppCompatDelegate.MODE_NIGHT_YES
        )
    }
}