package shvyn22.flexingfinalspace.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import shvyn22.flexingfinalspace.data.remote.api.FakeApiService
import shvyn22.flexingfinalspace.data.local.dao.CharacterDao
import shvyn22.flexingfinalspace.data.local.dao.EpisodeDao
import shvyn22.flexingfinalspace.data.local.dao.QuoteDao
import shvyn22.flexingfinalspace.data.local.model.CharacterModel
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.data.local.model.QuoteModel
import shvyn22.flexingfinalspace.repository.*
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object FakeRepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(
        api: FakeApiService,
        dao: CharacterDao
    ): Repository<CharacterModel> = FakeCharacterRepository(dao, api)

    @Singleton
    @Provides
    fun provideEpisodeRepository(
        api: FakeApiService,
        dao: EpisodeDao
    ): Repository<EpisodeModel> = FakeEpisodeRepository(dao, api)

    @Singleton
    @Provides
    fun provideQuoteRepository(
        api: FakeApiService,
        dao: QuoteDao
    ): Repository<QuoteModel> = FakeQuoteRepository(dao, api)
}