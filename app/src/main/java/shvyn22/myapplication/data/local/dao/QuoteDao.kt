package shvyn22.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import shvyn22.myapplication.data.local.model.QuoteModel

@Dao
interface QuoteDao: DaoInterface<QuoteModel> {

    @Query("SELECT * FROM Quote")
    override fun getAll(): Flow<List<QuoteModel>>

    @Query("DELETE FROM Quote")
    override suspend fun deleteAll()
}