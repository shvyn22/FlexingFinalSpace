package shvyn22.flexingfinalspace.ui.character

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import shvyn22.flexingfinalspace.data.local.model.CharacterModel
import shvyn22.flexingfinalspace.repository.Repository
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val repository: Repository<CharacterModel>
) : ViewModel() {

    val items = flow {
        repository.getItems().collect {
            emit(it)
        }
    }
}