package shvyn22.flexingfinalspace.data.remote.api

import retrofit2.Call
import retrofit2.http.GET
import shvyn22.flexingfinalspace.data.remote.dto.CharacterDTO
import shvyn22.flexingfinalspace.data.remote.dto.EpisodeDTO
import shvyn22.flexingfinalspace.data.remote.dto.QuoteDTO

interface ApiService {

    @GET("character")
    fun getCharacters(): Call<List<CharacterDTO>>

    @GET("episode")
    fun getEpisodes(): Call<List<EpisodeDTO>>

    @GET("quote")
    fun getQuotes(): Call<List<QuoteDTO>>
}