package shvyn22.flexingfinalspace.data.preferences

import io.reactivex.rxjava3.core.Observable

interface PreferencesManager {

    val nightMode: Observable<Int>

    fun editNightMode(nightMode: Int)
}