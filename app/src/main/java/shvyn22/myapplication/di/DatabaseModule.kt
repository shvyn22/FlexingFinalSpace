package shvyn22.myapplication.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shvyn22.myapplication.data.local.AppDatabase
import shvyn22.myapplication.data.local.dao.CharacterDao
import shvyn22.myapplication.data.local.dao.EpisodeDao
import shvyn22.myapplication.data.local.dao.QuoteDao
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
                "appDatabase"
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