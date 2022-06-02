package shvyn22.flexingfinalspace.presentation.ui.components

import android.net.Uri
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.data.local.model.CharacterModel
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.presentation.character.CharacterScreen
import shvyn22.flexingfinalspace.presentation.character.details.CharacterDetailsScreen
import shvyn22.flexingfinalspace.presentation.episode.EpisodeScreen
import shvyn22.flexingfinalspace.presentation.quote.QuoteScreen

sealed class Screen(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    object Character : Screen("character", R.string.nav_characters, R.drawable.ic_character)
    object Episode : Screen("episode", R.string.nav_episodes, R.drawable.ic_episode)
    object Quote : Screen("quote", R.string.nav_quotes, R.drawable.ic_quote)
    object CharacterDetails :
        Screen("characterDetails/{character}", R.string.nav_details, R.drawable.ic_character)
}

@ExperimentalFoundationApi
@Composable
fun NavigationConfig(
    navController: NavHostController,
    onShowError: (String) -> Unit,
    onShowBottomSheet: (EpisodeModel) -> Unit,
    onChangeUpButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Character.route
    ) {
        composable(Screen.Character.route) {
            CharacterScreen(
                onErrorOccurred = onShowError,
                onNavigateToDetails = {
                    val json = Uri.encode(Gson().toJson(it))
                    onChangeUpButton()
                    navController.navigate("characterDetails/$json")
                },
                modifier = modifier
            )
        }

        composable(Screen.Episode.route) {
            EpisodeScreen(
                onErrorOccurred = onShowError,
                onShowBottomSheet = onShowBottomSheet,
                modifier = modifier
            )
        }

        composable(Screen.Quote.route) {
            QuoteScreen(
                onErrorOccurred = onShowError,
                modifier = modifier
            )
        }

        composable(
            Screen.CharacterDetails.route,
            arguments = listOf(navArgument("character") { type = CharacterArgument() })
        ) {
            val character = it.arguments?.getParcelable<CharacterModel>("character")
            CharacterDetailsScreen(
                character = character,
                modifier = modifier
            )
        }
    }
}

class CharacterArgument : NavType<CharacterModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): CharacterModel? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): CharacterModel {
        return Gson().fromJson(value, CharacterModel::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: CharacterModel) {
        bundle.putParcelable(key, value)
    }
}