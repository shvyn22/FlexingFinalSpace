package shvyn22.flexingfinalspace.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel

@Dao
interface EpisodeDao : DaoInterface<EpisodeModel> {

    @Query("SELECT * FROM Episode")
    override fun getAll(): Observable<List<EpisodeModel>>

    @Query("DELETE FROM Episode")
    override fun deleteAll(): Completable
}