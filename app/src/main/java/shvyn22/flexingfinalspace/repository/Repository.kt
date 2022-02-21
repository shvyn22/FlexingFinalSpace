package shvyn22.flexingfinalspace.repository

import io.reactivex.rxjava3.core.Observable
import shvyn22.flexingfinalspace.util.Resource

interface Repository<T> {

    fun getItems(): Observable<Resource<List<T>>>
}