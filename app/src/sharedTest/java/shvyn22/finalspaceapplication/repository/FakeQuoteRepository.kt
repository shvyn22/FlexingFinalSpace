package shvyn22.finalspaceapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.finalspaceapplication.api.ApiInterface
import shvyn22.finalspaceapplication.api.FakeApiInterface
import shvyn22.finalspaceapplication.data.local.dao.FakeQuoteDao
import shvyn22.finalspaceapplication.data.local.dao.QuoteDao
import shvyn22.finalspaceapplication.data.local.model.QuoteModel
import shvyn22.finalspaceapplication.data.util.fromQuoteDTOToModel
import shvyn22.finalspaceapplication.util.Resource
import shvyn22.finalspaceapplication.util.networkBoundResource

class FakeQuoteRepository(
    private val dao: QuoteDao = FakeQuoteDao(),
    private val api: ApiInterface = FakeApiInterface()
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