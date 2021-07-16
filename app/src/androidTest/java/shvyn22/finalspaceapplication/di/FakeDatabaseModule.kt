package shvyn22.finalspaceapplication.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import shvyn22.finalspaceapplication.data.local.AppDatabase
import shvyn22.finalspaceapplication.data.local.dao.CharacterDao
import shvyn22.finalspaceapplication.data.local.dao.EpisodeDao
import shvyn22.finalspaceapplication.data.local.dao.QuoteDao
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object FakeDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase =
        Room
            .inMemoryDatabaseBuilder(
                app,
                AppDatabase::class.java
            )
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase): CharacterDao =
        db.characterDao()

    @Singleton
    @Provides
    fun provideEpisodeDao(db: AppDatabase): EpisodeDao =
        db.episodeDao()

    @Singleton
    @Provides
    fun provideQuoteDao(db: AppDatabase): QuoteDao =
        db.quoteDao()
}