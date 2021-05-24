package shvyn22.myapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.myapplication.api.ApiInterface
import shvyn22.myapplication.data.local.dao.CharacterDao
import shvyn22.myapplication.data.local.model.CharacterModel
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val dao: CharacterDao,
    private val api: ApiInterface
): Repository<CharacterModel> {

    override fun getItems(): Flow<CharacterModel> {
        TODO("Not yet implemented")
    }
}