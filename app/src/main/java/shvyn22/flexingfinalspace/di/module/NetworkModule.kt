package shvyn22.flexingfinalspace.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shvyn22.flexingfinalspace.data.remote.api.ApiService
import shvyn22.flexingfinalspace.util.BASE_URL
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}