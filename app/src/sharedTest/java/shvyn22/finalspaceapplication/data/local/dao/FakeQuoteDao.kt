package shvyn22.finalspaceapplication.data.local.dao

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shvyn22.finalspaceapplication.data.local.model.QuoteModel

class FakeQuoteDao: QuoteDao {

    private val quotes = mutableListOf<QuoteModel>()

    override fun getAll(): Flow<List<QuoteModel>> = flow {
        emit(quotes)
    }

    override suspend fun insertAll(items: List<QuoteModel>) {
        quotes.addAll(items)
    }

    override suspend fun deleteAll() {
        quotes.clear()
    }
}