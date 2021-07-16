package shvyn22.finalspaceapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.finalspaceapplication.api.ApiInterface
import shvyn22.finalspaceapplication.api.FakeApiInterface
import shvyn22.finalspaceapplication.data.local.dao.CharacterDao
import shvyn22.finalspaceapplication.data.local.dao.FakeCharacterDao
import shvyn22.finalspaceapplication.data.local.model.CharacterModel
import shvyn22.finalspaceapplication.data.util.fromCharacterDTOToModel
import shvyn22.finalspaceapplication.util.Resource
import shvyn22.finalspaceapplication.util.networkBoundResource

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