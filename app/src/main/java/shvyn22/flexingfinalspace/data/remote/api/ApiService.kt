package shvyn22.flexingfinalspace.data.remote.api

import retrofit2.http.GET
import shvyn22.flexingfinalspace.data.remote.dto.CharacterDTO
import shvyn22.flexingfinalspace.data.remote.dto.EpisodeDTO
import shvyn22.flexingfinalspace.data.remote.dto.QuoteDTO

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): List<CharacterDTO>

    @GET("episode")
    suspend fun getEpisodes(): List<EpisodeDTO>

    @GET("quote")
    suspend fun getQuotes(): List<QuoteDTO>
}