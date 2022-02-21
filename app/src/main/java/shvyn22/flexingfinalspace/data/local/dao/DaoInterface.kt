package shvyn22.flexingfinalspace.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoInterface<T> {

    fun getAll(): Observable<List<T>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<T>): Completable

    fun deleteAll(): Completable
}