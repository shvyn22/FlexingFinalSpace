package shvyn22.finalspaceapplication.ui.episode

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import shvyn22.finalspaceapplication.repository.EpisodeRepository
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repository: EpisodeRepository
): ViewModel() {

    val items = flow {
        repository.getItems().collect {
            emit(it)
        }
    }
}