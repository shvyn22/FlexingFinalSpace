package shvyn22.flexingfinalspace.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shvyn22.flexingfinalspace.di.util.ViewModelKey
import shvyn22.flexingfinalspace.ui.MainViewModel
import shvyn22.flexingfinalspace.ui.character.CharacterViewModel
import shvyn22.flexingfinalspace.ui.episode.EpisodeViewModel
import shvyn22.flexingfinalspace.ui.quote.QuoteViewModel

@Module
interface ViewModelModule {

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(CharacterViewModel::class)]
    fun bindCharacterViewModel(characterViewModel: CharacterViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(EpisodeViewModel::class)]
    fun bindEpisodeViewModel(episodeViewModel: EpisodeViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(QuoteViewModel::class)]
    fun bindQuoteViewModel(quoteViewModel: QuoteViewModel): ViewModel
}