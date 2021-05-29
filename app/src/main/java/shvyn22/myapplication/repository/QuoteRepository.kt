package shvyn22.myapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.myapplication.api.ApiInterface
import shvyn22.myapplication.data.local.dao.QuoteDao
import shvyn22.myapplication.data.local.model.QuoteModel
import shvyn22.myapplication.data.util.fromQuoteDTOToModel
import shvyn22.myapplication.util.Resource
import shvyn22.myapplication.util.networkBoundResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepository @Inject constructor(
    private val dao: QuoteDao,
    private val api: ApiInterface
): Repository<Resource<List<QuoteModel>>> {

    override fun getItems(): Flow<Resource<List<QuoteModel>>> =
            networkBoundResource(
                query = { dao.getAll() },
                fetch = { api.getQuotes() },
                saveFetchResult = {
                    dao.deleteAll()
                    dao.insertAll( it.map { dto ->
                        fromQuoteDTOToModel(dto)
                    })
                }
            )
}