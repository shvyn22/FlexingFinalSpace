package shvyn22.myapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.myapplication.api.ApiInterface
import shvyn22.myapplication.data.local.dao.EpisodeDao
import shvyn22.myapplication.data.local.model.EpisodeModel
import shvyn22.myapplication.data.util.fromEpisodeDTOToModel
import shvyn22.myapplication.util.Resource
import shvyn22.myapplication.util.networkBoundResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodeRepository @Inject constructor(
    private val dao: EpisodeDao,
    private val api: ApiInterface
): Repository<Resource<List<EpisodeModel>>> {

    override fun getItems(): Flow<Resource<List<EpisodeModel>>> =
            networkBoundResource(
                query = { dao.getAll() },
                fetch = { api.getEpisodes() },
                saveFetchResult = {
                    dao.deleteAll()
                    dao.insertAll( it.map { dto ->
                        fromEpisodeDTOToModel(dto)
                    })
                }
            )
}