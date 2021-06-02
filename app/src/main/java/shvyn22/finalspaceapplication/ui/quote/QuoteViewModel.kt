package shvyn22.finalspaceapplication.ui.quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import shvyn22.finalspaceapplication.data.local.model.QuoteModel
import shvyn22.finalspaceapplication.repository.QuoteRepository
import shvyn22.finalspaceapplication.util.Resource
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel() {

    private val _items = MutableStateFlow<Resource<List<QuoteModel>>>(Resource.Loading())
    val items: StateFlow<Resource<List<QuoteModel>>> get() = _items

    init {
        viewModelScope.launch {
            repository.getItems().collect {
                _items.value = it
            }
        }
    }
}