package shvyn22.myapplication.repository

import kotlinx.coroutines.flow.Flow

interface Repository<out T> {

    fun getItems(): Flow<T>
}