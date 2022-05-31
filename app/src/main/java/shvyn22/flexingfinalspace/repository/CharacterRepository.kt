package shvyn22.flexingfinalspace.repository

import io.reactivex.rxjava3.core.Observable
import shvyn22.flexingfinalspace.data.remote.api.ApiService
import shvyn22.flexingfinalspace.data.local.dao.CharacterDao
import shvyn22.flexingfinalspace.data.local.model.CharacterModel
import shvyn22.flexingfinalspace.data.util.fromCharacterDTOToModel
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.networkBoundResource

class CharacterRepository(
    private val dao: CharacterDao,
    private val api: ApiService
) : Repository<CharacterModel> {

    override fun getItems(): Observable<Resource<List<CharacterModel>>> =
        networkBoundResource(
            query = { dao.getAll() },
            fetch = { api.getCharacters() },
            saveFetchResult = {
                dao.deleteAll()
                dao.insertAll(it.map { dto ->
                    fromCharacterDTOToModel(dto)
                })
            }
        )
}