package shvyn22.flexingfinalspace.presentation.quote

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.data.local.model.QuoteModel
import shvyn22.flexingfinalspace.presentation.ui.components.QuoteColumnProgressList
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.removeAndAdd

@ExperimentalFoundationApi
@Composable
fun QuoteScreen(
    onErrorOccurred: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuoteViewModel = hiltViewModel()
) {
    val resource = viewModel.items.collectAsState()

    QuoteContent(
        resource = resource.value,
        onErrorOccurred = onErrorOccurred,
        modifier = modifier
    )
}

@ExperimentalFoundationApi
@Composable
fun QuoteContent(
    resource: Resource<List<QuoteModel>>,
    onErrorOccurred: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isLoading = resource is Resource.Loading
    val quotes = mutableListOf<QuoteModel>()

    if (resource is Resource.Success) quotes.removeAndAdd(resource.data)
    else if (resource is Resource.Error) {
        onErrorOccurred(
            resource.error.localizedMessage ?: stringResource(id = R.string.text_default_error)
        )
        quotes.removeAndAdd(resource.data)
    }

    QuoteColumnProgressList(
        isLoading = isLoading,
        quotes = quotes,
        modifier = modifier,
    )
}