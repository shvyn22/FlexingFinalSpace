package shvyn22.finalspaceapplication.api

import shvyn22.finalspaceapplication.data.remote.CharacterDTO
import shvyn22.finalspaceapplication.data.remote.EpisodeDTO
import shvyn22.finalspaceapplication.data.remote.QuoteDTO

class FakeApiInterface(
    private var shouldFail: Boolean = false
) : ApiInterface {

    fun changeFailBehavior(shouldFail: Boolean) {
        this.shouldFail = shouldFail
    }

    private val characters = mutableListOf<CharacterDTO>()

    fun initCharacters(items: List<CharacterDTO>) {
        characters.clear()
        characters.addAll(items)
    }

    override suspend fun getCharacters(): List<CharacterDTO> {
        if (shouldFail) throw Exception()
        return characters
    }

    private val episodes = mutableListOf<EpisodeDTO>()

    fun initEpisodes(items: List<EpisodeDTO>) {
        episodes.clear()
        episodes.addAll(items)
    }

    override suspend fun getEpisodes(): List<EpisodeDTO> {
        if (shouldFail) throw Exception()
        return episodes
    }

    private val quotes = mutableListOf<QuoteDTO>()

    fun initQuotes(items: List<QuoteDTO>) {
        quotes.clear()
        quotes.addAll(items)
    }

    override suspend fun getQuotes(): List<QuoteDTO> {
        if (shouldFail) throw Exception()
        return quotes
    }
}