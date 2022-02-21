package shvyn22.flexingfinalspace.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import shvyn22.flexingfinalspace.data.local.model.QuoteModel

@Dao
interface QuoteDao : DaoInterface<QuoteModel> {

    @Query("SELECT * FROM Quote")
    override fun getAll(): Observable<List<QuoteModel>>

    @Query("DELETE FROM Quote")
    override fun deleteAll(): Completable
}