package shvyn22.flexingfinalspace.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.flexingfinalspace.data.remote.api.ApiService
import shvyn22.flexingfinalspace.data.local.dao.EpisodeDao
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.data.util.fromEpisodeDTOToModel
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.networkBoundResource

class EpisodeRepository(
    private val dao: EpisodeDao,
    private val api: ApiService
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