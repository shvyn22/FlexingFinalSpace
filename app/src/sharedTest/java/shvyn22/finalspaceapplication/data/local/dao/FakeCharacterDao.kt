package shvyn22.finalspaceapplication.data.local.dao

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shvyn22.finalspaceapplication.data.local.model.CharacterModel

class FakeCharacterDao: CharacterDao {

    private val characters = mutableListOf<CharacterModel>()

    override fun getAll(): Flow<List<CharacterModel>> = flow {
        emit(characters)
    }

    override suspend fun insertAll(items: List<CharacterModel>) {
        characters.addAll(items)
    }

    override suspend fun deleteAll() {
        characters.clear()
    }
}