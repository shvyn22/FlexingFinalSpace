package shvyn22.flexingfinalspace.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shvyn22.flexingfinalspace.api.ApiInterface
import shvyn22.flexingfinalspace.data.local.dao.CharacterDao
import shvyn22.flexingfinalspace.data.local.dao.EpisodeDao
import shvyn22.flexingfinalspace.data.local.dao.QuoteDao
import shvyn22.flexingfinalspace.data.local.model.CharacterModel
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.data.local.model.QuoteModel
import shvyn22.flexingfinalspace.repository.CharacterRepository
import shvyn22.flexingfinalspace.repository.EpisodeRepository
import shvyn22.flexingfinalspace.repository.QuoteRepository
import shvyn22.flexingfinalspace.repository.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(
        dao: CharacterDao,
        api: ApiInterface
    ): Repository<CharacterModel> = CharacterRepository(dao, api)

    @Singleton
    @Provides
    fun provideEpisodeRepository(
        dao: EpisodeDao,
        api: ApiInterface
    ): Repository<EpisodeModel> = EpisodeRepository(dao, api)

    @Singleton
    @Provides
    fun provideQuoteRepository(
        dao: QuoteDao,
        api: ApiInterface
    ): Repository<QuoteModel> = QuoteRepository(dao, api)
}