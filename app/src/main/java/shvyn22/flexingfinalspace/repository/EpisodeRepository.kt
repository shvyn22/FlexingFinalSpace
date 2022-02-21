package shvyn22.flexingfinalspace.repository

import io.reactivex.rxjava3.core.Observable
import shvyn22.flexingfinalspace.api.ApiInterface
import shvyn22.flexingfinalspace.data.local.dao.EpisodeDao
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.data.util.fromEpisodeDTOToModel
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.networkBoundResource

class EpisodeRepository(
    private val dao: EpisodeDao,
    private val api: ApiInterface
) : Repository<EpisodeModel> {

    override fun getItems(): Observable<Resource<List<EpisodeModel>>> =
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