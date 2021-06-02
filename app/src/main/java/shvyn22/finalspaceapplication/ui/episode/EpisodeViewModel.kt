package shvyn22.finalspaceapplication.ui.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import shvyn22.finalspaceapplication.data.local.model.EpisodeModel
import shvyn22.finalspaceapplication.repository.EpisodeRepository
import shvyn22.finalspaceapplication.util.Resource
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repository: EpisodeRepository
): ViewModel() {

    private val _items = MutableStateFlow<Resource<List<EpisodeModel>>>(Resource.Loading())
    val items: StateFlow<Resource<List<EpisodeModel>>> get() = _items

    init {
        viewModelScope.launch {
            repository.getItems().collect {
                _items.value = it
            }
        }
    }
}