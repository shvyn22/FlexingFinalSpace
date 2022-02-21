package shvyn22.flexingfinalspace.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.flexingfinalspace.api.ApiInterface
import shvyn22.flexingfinalspace.api.FakeApiInterface
import shvyn22.flexingfinalspace.data.local.dao.FakeQuoteDao
import shvyn22.flexingfinalspace.data.local.dao.QuoteDao
import shvyn22.flexingfinalspace.data.local.model.QuoteModel
import shvyn22.flexingfinalspace.data.util.fromQuoteDTOToModel
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.networkBoundResource

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