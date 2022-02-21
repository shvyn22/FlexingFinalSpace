package shvyn22.flexingfinalspace.util

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call

inline fun <Model, DTO> networkBoundResource(
    crossinline query: () -> Observable<Model>,
    crossinline fetch: () -> Call<DTO>,
    crossinline saveFetchResult: (DTO) -> Completable
): Observable<Resource<Model>> = Observable.create { sub ->
    sub.onNext(Resource.Loading())
    try {
        saveFetchResult(
            fetch().execute().body() ?: throw IllegalArgumentException()
        ).subscribe()

        query()
            .map { sub.onNext(Resource.Success(it)) }
            .subscribe()
    } catch (e: Throwable) {
        query()
            .map { sub.onNext(Resource.Error(it, e)) }
            .subscribe()
    }
}