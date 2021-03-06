package shvyn22.flexingfinalspace.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import shvyn22.flexingfinalspace.data.local.model.CharacterModel

@Dao
interface CharacterDao : DaoInterface<CharacterModel> {

    @Query("SELECT * FROM Character")
    override fun getAll(): Flow<List<CharacterModel>>

    @Query("DELETE FROM Character")
    override suspend fun deleteAll()
}