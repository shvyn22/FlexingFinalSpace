package shvyn22.flexingfinalspace.di.module

import android.app.Application
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder
import androidx.datastore.rxjava3.RxDataStore
import dagger.Module
import dagger.Provides
import shvyn22.flexingfinalspace.data.preferences.PreferencesManager
import shvyn22.flexingfinalspace.data.preferences.PreferencesManagerImpl
import javax.inject.Singleton

@Module
object PreferencesModule {

    @Singleton
    @Provides
    fun provideDataStore(app: Application): RxDataStore<Preferences> =
        RxPreferenceDataStoreBuilder(app, "preferences").build()

    @Singleton
    @Provides
    fun providePreferencesManager(
        dataStore: RxDataStore<Preferences>
    ): PreferencesManager = PreferencesManagerImpl(dataStore)
}