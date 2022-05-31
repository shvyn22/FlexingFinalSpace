package shvyn22.flexingfinalspace.data.remote.api

import shvyn22.flexingfinalspace.data.remote.dto.CharacterDTO
import shvyn22.flexingfinalspace.data.remote.dto.EpisodeDTO
import shvyn22.flexingfinalspace.data.remote.dto.QuoteDTO

class FakeApiService(
    private var shouldFail: Boolean = false
) : ApiService {

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