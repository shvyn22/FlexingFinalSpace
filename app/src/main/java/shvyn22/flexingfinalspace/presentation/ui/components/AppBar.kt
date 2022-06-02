package shvyn22.flexingfinalspace.presentation.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import shvyn22.flexingfinalspace.R

@Composable
fun AppBar(
    isDarkTheme: Boolean,
    isUpButtonShown: Boolean,
    onToggleTheme: (Boolean) -> Unit,
    onUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        },
        actions = {
            IconButton(
                onClick = { onToggleTheme(!isDarkTheme) }
            ) {
                Icon(
                    imageVector = if (isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                    contentDescription = null
                )
            }
        },
        navigationIcon = if (!isUpButtonShown) null else {
            {
                IconButton(
                    onClick = { onUpButtonClick() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        modifier = modifier
    )
}