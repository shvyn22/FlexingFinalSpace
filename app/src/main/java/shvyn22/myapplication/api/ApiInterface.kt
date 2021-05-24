package shvyn22.myapplication.api

import retrofit2.http.GET
import shvyn22.myapplication.data.remote.CharacterDTO
import shvyn22.myapplication.data.remote.EpisodeDTO
import shvyn22.myapplication.data.remote.QuoteDTO

interface ApiInterface {

    @GET("character")
    suspend fun getCharacters(): List<CharacterDTO>

    @GET("episode")
    suspend fun getEpisodes(): List<EpisodeDTO>

    @GET("quote")
    suspend fun getQuotes(): List<QuoteDTO>
}