package shvyn22.finalspaceapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import shvyn22.finalspaceapplication.api.FakeApiInterface
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object FakeNetworkModule {

    @Singleton
    @Provides
    fun provideApiInterface() : FakeApiInterface = FakeApiInterface()
}