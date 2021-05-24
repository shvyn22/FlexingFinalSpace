package shvyn22.myapplication.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import shvyn22.myapplication.data.local.model.QuoteModel

interface QuoteDao: DaoInterface<QuoteModel> {

    @Query("SELECT * FROM Quote")
    override fun getAll(): Flow<QuoteModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertAll(items: List<QuoteModel>)

    @Query("DELETE FROM Quote")
    override suspend fun deleteAll()
}