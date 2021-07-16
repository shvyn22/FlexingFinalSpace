package shvyn22.finalspaceapplication.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.TestCoroutineDispatcher
import shvyn22.finalspaceapplication.data.preferences.PreferencesManager
import shvyn22.finalspaceapplication.data.preferences.PreferencesManagerImpl
import shvyn22.finalspaceapplication.util.TEST_PREFERENCES_FILENAME
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PreferencesModule::class]
)
object FakePreferencesModule {

    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideScope(): CoroutineScope =
        CoroutineScope(TestCoroutineDispatcher() + Job())

    @Singleton
    @Provides
    fun provideDataStore(app: Application, scope: CoroutineScope): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            scope = scope,
            produceFile = {
                app.preferencesDataStoreFile(TEST_PREFERENCES_FILENAME)
            }
        )

    @Singleton
    @Provides
    fun providePreferencesManager(
        dataStore: DataStore<Preferences>
    ): PreferencesManager = PreferencesManagerImpl(dataStore)
}