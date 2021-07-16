package shvyn22.finalspaceapplication.ui.character

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import shvyn22.finalspaceapplication.data.local.model.CharacterModel
import shvyn22.finalspaceapplication.repository.Repository
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: Repository<CharacterModel>
) : ViewModel() {

    val items = flow {
        repository.getItems().collect {
            emit(it)
        }
    }
}