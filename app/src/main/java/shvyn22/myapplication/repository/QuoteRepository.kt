package shvyn22.myapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.myapplication.api.ApiInterface
import shvyn22.myapplication.data.local.dao.QuoteDao
import shvyn22.myapplication.data.local.model.QuoteModel
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val dao: QuoteDao,
    private val api: ApiInterface
): Repository<QuoteModel> {

    override fun getItems(): Flow<QuoteModel> {
        TODO("Not yet implemented")
    }
}