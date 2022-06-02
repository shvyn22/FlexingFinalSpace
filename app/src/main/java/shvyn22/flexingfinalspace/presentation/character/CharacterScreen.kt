package shvyn22.flexingfinalspace.presentation.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.data.local.model.CharacterModel
import shvyn22.flexingfinalspace.presentation.ui.components.CharacterGridProgressList
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.removeAndAdd

@ExperimentalFoundationApi
@Composable
fun CharacterScreen(
    onErrorOccurred: (String) -> Unit,
    onNavigateToDetails: (CharacterModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = hiltViewModel()
) {
    val resource = viewModel.items.collectAsState()

    CharacterContent(
        resource = resource.value,
        onErrorOccurred = onErrorOccurred,
        onNavigateToDetails = onNavigateToDetails,
        modifier = modifier
    )
}

@ExperimentalFoundationApi
@Composable
fun CharacterContent(
    resource: Resource<List<CharacterModel>>,
    onErrorOccurred: (String) -> Unit,
    onNavigateToDetails: (CharacterModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val isLoading = resource is Resource.Loading
    val characters = mutableListOf<CharacterModel>()

    if (resource is Resource.Success) characters.removeAndAdd(resource.data)
    else if (resource is Resource.Error) {
        onErrorOccurred(
            resource.error.localizedMessage ?: stringResource(id = R.string.text_default_error)
        )
        characters.removeAndAdd(resource.data)
    }

    CharacterGridProgressList(
        isLoading = isLoading,
        characters = characters,
        modifier = modifier,
        onClick = { character ->
            onNavigateToDetails(character)
        }
    )
}