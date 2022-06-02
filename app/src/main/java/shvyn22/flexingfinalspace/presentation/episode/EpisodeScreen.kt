package shvyn22.flexingfinalspace.presentation.episode

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.presentation.ui.components.EpisodeGridProgressList
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.removeAndAdd

@ExperimentalFoundationApi
@Composable
fun EpisodeScreen(
    onErrorOccurred: (String) -> Unit,
    onShowBottomSheet: (EpisodeModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EpisodeViewModel = hiltViewModel()
) {
    val resource = viewModel.items.collectAsState()

    EpisodeContent(
        resource = resource.value,
        onErrorOccurred = onErrorOccurred,
        onShowBottomSheet = onShowBottomSheet,
        modifier = modifier
    )
}

@ExperimentalFoundationApi
@Composable
fun EpisodeContent(
    resource: Resource<List<EpisodeModel>>,
    onErrorOccurred: (String) -> Unit,
    onShowBottomSheet: (EpisodeModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val isLoading = resource is Resource.Loading
    val episodes = mutableListOf<EpisodeModel>()

    if (resource is Resource.Success) episodes.removeAndAdd(resource.data)
    else if (resource is Resource.Error) {
        onErrorOccurred(
            resource.error.localizedMessage ?: stringResource(id = R.string.text_default_error)
        )
        episodes.removeAndAdd(resource.data)
    }

    EpisodeGridProgressList(
        isLoading = isLoading,
        episodes = episodes,
        modifier = modifier,
        onClick = { episode ->
            onShowBottomSheet(episode)
        }
    )
}