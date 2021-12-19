package shvyn22.flexingfinalspace.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.flexingfinalspace.api.ApiInterface
import shvyn22.flexingfinalspace.api.FakeApiInterface
import shvyn22.flexingfinalspace.data.local.dao.CharacterDao
import shvyn22.flexingfinalspace.data.local.dao.FakeCharacterDao
import shvyn22.flexingfinalspace.data.local.model.CharacterModel
import shvyn22.flexingfinalspace.data.util.fromCharacterDTOToModel
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.networkBoundResource

class FakeCharacterRepository(
    private val dao: CharacterDao = FakeCharacterDao(),
    private val api: ApiInterface = FakeApiInterface()
) : Repository<CharacterModel> {

    override fun getItems(): Flow<Resource<List<CharacterModel>>> =
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