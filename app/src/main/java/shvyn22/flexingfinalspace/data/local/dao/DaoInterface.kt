package shvyn22.flexingfinalspace.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoInterface<T> {

    fun getAll(): Flow<List<T>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<T>)

    suspend fun deleteAll()
}