package shvyn22.finalspaceapplication.repository

import kotlinx.coroutines.flow.Flow

interface Repository<out T> {

    fun getItems(): Flow<T>
}