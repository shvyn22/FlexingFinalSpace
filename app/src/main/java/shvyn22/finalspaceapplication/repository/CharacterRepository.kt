package shvyn22.finalspaceapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.finalspaceapplication.api.ApiInterface
import shvyn22.finalspaceapplication.data.local.dao.CharacterDao
import shvyn22.finalspaceapplication.data.local.model.CharacterModel
import shvyn22.finalspaceapplication.data.util.fromCharacterDTOToModel
import shvyn22.finalspaceapplication.util.Resource
import shvyn22.finalspaceapplication.util.networkBoundResource

class CharacterRepository(
    private val dao: CharacterDao,
    private val api: ApiInterface
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