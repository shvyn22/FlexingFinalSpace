package shvyn22.flexingfinalspace.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shvyn22.flexingfinalspace.di.util.ViewModelKey
import shvyn22.flexingfinalspace.presentation.MainViewModel
import shvyn22.flexingfinalspace.presentation.character.CharacterViewModel
import shvyn22.flexingfinalspace.presentation.episode.EpisodeViewModel
import shvyn22.flexingfinalspace.presentation.quote.QuoteViewModel

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