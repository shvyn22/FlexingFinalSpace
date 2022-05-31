package shvyn22.flexingfinalspace.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import shvyn22.flexingfinalspace.data.local.model.CharacterModel

@Dao
interface CharacterDao: DaoInterface<CharacterModel> {

    @Query("SELECT * FROM Character")
    override fun getAll(): Observable<List<CharacterModel>>

    @Query("DELETE FROM Character")
    override fun deleteAll(): Completable
}