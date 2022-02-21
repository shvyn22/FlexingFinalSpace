package shvyn22.flexingfinalspace.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import shvyn22.flexingfinalspace.di.module.AppModule
import shvyn22.flexingfinalspace.ui.MainActivity
import shvyn22.flexingfinalspace.ui.character.CharacterFragment
import shvyn22.flexingfinalspace.ui.episode.EpisodeFragment
import shvyn22.flexingfinalspace.ui.quote.QuoteFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface SingletonComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(characterFragment: CharacterFragment)

    fun inject(episodeFragment: EpisodeFragment)

    fun inject(quoteFragment: QuoteFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): SingletonComponent
    }
}