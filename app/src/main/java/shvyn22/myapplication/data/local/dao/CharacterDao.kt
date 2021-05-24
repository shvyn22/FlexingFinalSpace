package shvyn22.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import shvyn22.myapplication.data.local.model.CharacterModel

@Dao
interface CharacterDao: DaoInterface<CharacterModel> {

    @Query("SELECT * FROM Character")
    override fun getAll(): Flow<CharacterModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertAll(items: List<CharacterModel>)

    @Query("DELETE FROM Character")
    override suspend fun deleteAll()
}