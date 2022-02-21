package shvyn22.flexingfinalspace.ui.episode

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.repository.Repository
import javax.inject.Inject

class EpisodeViewModel @Inject constructor(
    private val repository: Repository<EpisodeModel>
) : ViewModel() {

    val items = flow {
        repository.getItems().collect {
            emit(it)
        }
    }
}