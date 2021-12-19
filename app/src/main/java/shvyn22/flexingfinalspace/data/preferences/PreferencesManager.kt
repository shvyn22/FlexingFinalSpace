package shvyn22.flexingfinalspace.data.preferences

import kotlinx.coroutines.flow.Flow

interface PreferencesManager {

    val nightMode: Flow<Int>

    suspend fun editNightMode(nightMode: Int)
}