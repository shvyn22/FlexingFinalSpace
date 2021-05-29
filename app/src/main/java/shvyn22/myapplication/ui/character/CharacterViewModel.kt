package shvyn22.myapplication.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import shvyn22.myapplication.data.local.model.CharacterModel
import shvyn22.myapplication.repository.CharacterRepository
import shvyn22.myapplication.util.Resource
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
): ViewModel() {

    private val _items = MutableStateFlow<Resource<List<CharacterModel>>>(Resource.Loading())
    val items: StateFlow<Resource<List<CharacterModel>>> get() = _items

    init {
        viewModelScope.launch {
            repository.getItems().collect {
                _items.value = it
            }
        }
    }
}