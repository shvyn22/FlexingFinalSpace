package shvyn22.myapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.myapplication.api.ApiInterface
import shvyn22.myapplication.data.local.dao.CharacterDao
import shvyn22.myapplication.data.local.model.CharacterModel
import shvyn22.myapplication.data.util.fromCharacterDTOToModel
import shvyn22.myapplication.util.Resource
import shvyn22.myapplication.util.networkBoundResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(
    private val dao: CharacterDao,
    private val api: ApiInterface
): Repository<Resource<List<CharacterModel>>> {

    override fun getItems(): Flow<Resource<List<CharacterModel>>> =
            networkBoundResource(
                query = { dao.getAll() },
                fetch = { api.getCharacters() },
                saveFetchResult = {
                    dao.deleteAll()
                    dao.insertAll( it.map { dto ->
                        fromCharacterDTOToModel(dto)
                    })
                }
            )
}