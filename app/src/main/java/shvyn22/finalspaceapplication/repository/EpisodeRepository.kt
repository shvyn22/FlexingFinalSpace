package shvyn22.finalspaceapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.finalspaceapplication.api.ApiInterface
import shvyn22.finalspaceapplication.data.local.dao.EpisodeDao
import shvyn22.finalspaceapplication.data.local.model.EpisodeModel
import shvyn22.finalspaceapplication.data.util.fromEpisodeDTOToModel
import shvyn22.finalspaceapplication.util.Resource
import shvyn22.finalspaceapplication.util.networkBoundResource

class EpisodeRepository(
    private val dao: EpisodeDao,
    private val api: ApiInterface
) : Repository<EpisodeModel> {

    override fun getItems(): Flow<Resource<List<EpisodeModel>>> =
        networkBoundResource(
            query = { dao.getAll() },
            fetch = { api.getEpisodes() },
            saveFetchResult = {
                dao.deleteAll()
                dao.insertAll(it.map { dto ->
                    fromEpisodeDTOToModel(dto)
                })
            }
        )
}