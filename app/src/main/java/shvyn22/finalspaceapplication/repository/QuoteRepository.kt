package shvyn22.finalspaceapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.finalspaceapplication.api.ApiInterface
import shvyn22.finalspaceapplication.data.local.dao.QuoteDao
import shvyn22.finalspaceapplication.data.local.model.QuoteModel
import shvyn22.finalspaceapplication.data.util.fromQuoteDTOToModel
import shvyn22.finalspaceapplication.util.Resource
import shvyn22.finalspaceapplication.util.networkBoundResource

class QuoteRepository(
    private val dao: QuoteDao,
    private val api: ApiInterface
) : Repository<QuoteModel> {

    override fun getItems(): Flow<Resource<List<QuoteModel>>> =
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