package shvyn22.flexingfinalspace.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shvyn22.flexingfinalspace.data.local.AppDatabase
import shvyn22.flexingfinalspace.data.local.dao.CharacterDao
import shvyn22.flexingfinalspace.data.local.dao.EpisodeDao
import shvyn22.flexingfinalspace.data.local.dao.QuoteDao
import shvyn22.flexingfinalspace.util.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase =
        Room
            .databaseBuilder(
                app,
                AppDatabase::class.java,
                DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
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