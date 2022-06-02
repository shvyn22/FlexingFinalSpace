package shvyn22.flexingfinalspace.presentation.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.repository.Repository
import shvyn22.flexingfinalspace.util.Resource
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repository: Repository<EpisodeModel>
) : ViewModel() {

    val items = flow {
        repository.getItems().collect {
            emit(it)
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, Resource.Loading())
}