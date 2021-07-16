package shvyn22.finalspaceapplication.data.local.dao

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shvyn22.finalspaceapplication.data.local.model.EpisodeModel

class FakeEpisodeDao: EpisodeDao {

    private val episodes = mutableListOf<EpisodeModel>()

    override fun getAll(): Flow<List<EpisodeModel>> = flow {
        emit(episodes)
    }

    override suspend fun insertAll(items: List<EpisodeModel>) {
        episodes.addAll(items)
    }

    override suspend fun deleteAll() {
        episodes.clear()
    }
}