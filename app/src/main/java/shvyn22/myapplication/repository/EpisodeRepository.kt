package shvyn22.myapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.myapplication.api.ApiInterface
import shvyn22.myapplication.data.local.dao.EpisodeDao
import shvyn22.myapplication.data.local.model.EpisodeModel
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val dao: EpisodeDao,
    private val api: ApiInterface
): Repository<EpisodeModel> {

    override fun getItems(): Flow<EpisodeModel> {
        TODO("Not yet implemented")
    }
}