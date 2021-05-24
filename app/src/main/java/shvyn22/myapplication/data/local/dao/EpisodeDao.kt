package shvyn22.myapplication.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface EpisodeDao: DaoInterface<EpisodeDao> {

    @Query("SELECT * FROM Episode")
    override fun getAll(): Flow<EpisodeDao>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertAll(items: List<EpisodeDao>)

    @Query("DELETE FROM Episode")
    override suspend fun deleteAll()
}