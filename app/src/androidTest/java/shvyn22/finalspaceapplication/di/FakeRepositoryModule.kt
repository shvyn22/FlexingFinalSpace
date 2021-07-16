package shvyn22.finalspaceapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import shvyn22.finalspaceapplication.api.FakeApiInterface
import shvyn22.finalspaceapplication.data.local.dao.CharacterDao
import shvyn22.finalspaceapplication.data.local.dao.EpisodeDao
import shvyn22.finalspaceapplication.data.local.dao.QuoteDao
import shvyn22.finalspaceapplication.data.local.model.CharacterModel
import shvyn22.finalspaceapplication.data.local.model.EpisodeModel
import shvyn22.finalspaceapplication.data.local.model.QuoteModel
import shvyn22.finalspaceapplication.repository.*
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
        api: FakeApiInterface,
        dao: CharacterDao
    ): Repository<CharacterModel> = FakeCharacterRepository(dao, api)

    @Singleton
    @Provides
    fun provideEpisodeRepository(
        api: FakeApiInterface,
        dao: EpisodeDao
    ): Repository<EpisodeModel> = FakeEpisodeRepository(dao, api)

    @Singleton
    @Provides
    fun provideQuoteRepository(
        api: FakeApiInterface,
        dao: QuoteDao
    ): Repository<QuoteModel> = FakeQuoteRepository(dao, api)
}