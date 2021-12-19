package shvyn22.flexingfinalspace.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.flexingfinalspace.util.Resource

interface Repository<T> {

    fun getItems(): Flow<Resource<List<T>>>
}