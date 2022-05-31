package shvyn22.flexingfinalspace.repository

import io.reactivex.rxjava3.core.Observable
import shvyn22.flexingfinalspace.data.remote.api.ApiService
import shvyn22.flexingfinalspace.data.local.dao.QuoteDao
import shvyn22.flexingfinalspace.data.local.model.QuoteModel
import shvyn22.flexingfinalspace.data.util.fromQuoteDTOToModel
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.networkBoundResource

class QuoteRepository(
    private val dao: QuoteDao,
    private val api: ApiService
) : Repository<QuoteModel> {

    override fun getItems(): Observable<Resource<List<QuoteModel>>> =
        networkBoundResource(
            query = { dao.getAll() },
            fetch = { api.getQuotes() },
            saveFetchResult = {
                dao.deleteAll()
                dao.insertAll(it.map { dto ->
                    fromQuoteDTOToModel(dto)
                })
            }
        )
}