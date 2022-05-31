package shvyn22.flexingfinalspace.util

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    class Loading<T> : Resource<T>()
    data class Error<T>(val data: T, val error: Throwable) : Resource<T>()
}
