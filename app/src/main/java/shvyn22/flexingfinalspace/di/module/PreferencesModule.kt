package shvyn22.flexingfinalspace.di.module

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import shvyn22.flexingfinalspace.data.preferences.PreferencesManager
import shvyn22.flexingfinalspace.data.preferences.PreferencesManagerImpl
import javax.inject.Singleton

@Module
object PreferencesModule {

    @Singleton
    @Provides
    fun provideDataStore(app: Application): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                app.preferencesDataStoreFile("preferences")
            }
        )

    @Singleton
    @Provides
    fun providePreferencesManager(
        dataStore: DataStore<Preferences>
    ): PreferencesManager = PreferencesManagerImpl(dataStore)
}