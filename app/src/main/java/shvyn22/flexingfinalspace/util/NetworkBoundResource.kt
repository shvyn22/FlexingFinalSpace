package shvyn22.flexingfinalspace.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <Model, DTO> networkBoundResource(
    crossinline query: () -> Flow<Model>,
    crossinline fetch: suspend () -> DTO,
    crossinline saveFetchResult: suspend (DTO) -> Unit
) = flow {
    emit(Resource.Loading())
    val result = try {
        saveFetchResult(fetch())
        query().map { Resource.Success(it) }
    } catch (e: Throwable) {
        query().map { Resource.Error(it, e) }
    }
    emitAll(result)
}