package shvyn22.flexingfinalspace.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.flexingfinalspace.presentation.main.MainScreen
import shvyn22.flexingfinalspace.presentation.main.MainViewModel
import shvyn22.flexingfinalspace.presentation.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @OptIn(
        ExperimentalFoundationApi::class,
        ExperimentalMaterialApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val isDarkTheme = viewModel.isDarkTheme.collectAsState()

            AppTheme(
                isDarkTheme = isDarkTheme.value
            ) {
                MainScreen(
                    onToggleTheme = viewModel::editThemePreferences
                )
            }
        }
    }
}