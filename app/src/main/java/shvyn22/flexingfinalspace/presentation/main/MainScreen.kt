package shvyn22.flexingfinalspace.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.presentation.episode.details.EpisodeDetailsBottomSheet
import shvyn22.flexingfinalspace.presentation.ui.components.AppBar
import shvyn22.flexingfinalspace.presentation.ui.components.BottomNavBar
import shvyn22.flexingfinalspace.presentation.ui.components.NavigationConfig
import shvyn22.flexingfinalspace.presentation.ui.components.Screen

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(
    onToggleTheme: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val episode = remember { mutableStateOf<EpisodeModel?>(null) }
    val error = remember { mutableStateOf<String?>(null) }
    val isUpButtonShown = remember { mutableStateOf(false) }

    val bottomNavItems = listOf(
        Screen.Character,
        Screen.Episode,
        Screen.Quote
    )

    val isDarkTheme = !MaterialTheme.colors.isLight

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            Box(modifier.defaultMinSize(minHeight = 1.dp)) {
                episode.value?.let {
                    EpisodeDetailsBottomSheet(episode = it)
                }
            }
        }
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                AppBar(
                    isDarkTheme = isDarkTheme,
                    isUpButtonShown = isUpButtonShown.value,
                    navController = navController,
                    onToggleTheme = onToggleTheme,
                    onChangeUpButton = {
                        isUpButtonShown.value = !isUpButtonShown.value
                    },
                )
            },
            bottomBar = {
                BottomNavBar(
                    navController = navController,
                    items = bottomNavItems
                )
            },
            modifier = modifier
                .fillMaxSize()
        ) { innerPadding ->
            error.value?.let {
                val msg = stringResource(id = R.string.text_error, it)
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(msg)
                }
            }

            NavigationConfig(
                navController = navController,
                onShowError = { error.value = it },
                onShowBottomSheet = {
                    episode.value = it.also {
                        coroutineScope.launch { bottomSheetState.show() }
                    }
                },
                onChangeUpButton = { isUpButtonShown.value = !isUpButtonShown.value },
                modifier = modifier
                    .padding(innerPadding)
            )
        }
    }
}