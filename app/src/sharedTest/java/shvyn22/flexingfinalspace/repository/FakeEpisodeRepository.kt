package shvyn22.flexingfinalspace.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.flexingfinalspace.api.ApiInterface
import shvyn22.flexingfinalspace.api.FakeApiInterface
import shvyn22.flexingfinalspace.data.local.dao.EpisodeDao
import shvyn22.flexingfinalspace.data.local.dao.FakeEpisodeDao
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.data.util.fromEpisodeDTOToModel
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.networkBoundResource

class FakeEpisodeRepository(
    private val dao: EpisodeDao = FakeEpisodeDao(),
    private val api: ApiInterface = FakeApiInterface()
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