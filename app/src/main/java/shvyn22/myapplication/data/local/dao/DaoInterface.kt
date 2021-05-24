package shvyn22.myapplication.data.local.dao

import kotlinx.coroutines.flow.Flow

interface DaoInterface<T> {

    fun getAll(): Flow<T>

    suspend fun insertAll(items: List<T>)

    suspend fun deleteAll()
}