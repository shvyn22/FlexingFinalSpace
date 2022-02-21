package shvyn22.flexingfinalspace.ui.quote

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import shvyn22.flexingfinalspace.data.local.model.QuoteModel
import shvyn22.flexingfinalspace.repository.Repository
import javax.inject.Inject

class QuoteViewModel @Inject constructor(
    private val repository: Repository<QuoteModel>
) : ViewModel() {

    val items = flow {
        repository.getItems().collect {
            emit(it)
        }
    }
}