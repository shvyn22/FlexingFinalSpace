package shvyn22.flexingfinalspace.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel

@Dao
interface EpisodeDao : DaoInterface<EpisodeModel> {

    @Query("SELECT * FROM Episode")
    override fun getAll(): Flow<List<EpisodeModel>>

    @Query("DELETE FROM Episode")
    override suspend fun deleteAll()
}