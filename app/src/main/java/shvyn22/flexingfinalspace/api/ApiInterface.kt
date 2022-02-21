package shvyn22.flexingfinalspace.api

import retrofit2.Call
import retrofit2.http.GET
import shvyn22.flexingfinalspace.data.remote.CharacterDTO
import shvyn22.flexingfinalspace.data.remote.EpisodeDTO
import shvyn22.flexingfinalspace.data.remote.QuoteDTO

interface ApiInterface {

    @GET("character")
    fun getCharacters(): Call<List<CharacterDTO>>

    @GET("episode")
    fun getEpisodes(): Call<List<EpisodeDTO>>

    @GET("quote")
    fun getQuotes(): Call<List<QuoteDTO>>
}