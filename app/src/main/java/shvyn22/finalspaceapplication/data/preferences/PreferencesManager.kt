package shvyn22.finalspaceapplication.data.preferences

import kotlinx.coroutines.flow.Flow

interface PreferencesManager {

    val nightMode: Flow<Int>

    suspend fun editNightMode(nightMode: Int)
}