package shvyn22.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import shvyn22.myapplication.data.local.model.EpisodeModel

@Dao
interface EpisodeDao: DaoInterface<EpisodeModel> {

    @Query("SELECT * FROM Episode")
    override fun getAll(): Flow<EpisodeModel>

    @Query("DELETE FROM Episode")
    override suspend fun deleteAll()
}