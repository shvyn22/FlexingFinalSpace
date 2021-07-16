package shvyn22.finalspaceapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.finalspaceapplication.util.Resource

interface Repository<T> {

    fun getItems(): Flow<Resource<List<T>>>
}