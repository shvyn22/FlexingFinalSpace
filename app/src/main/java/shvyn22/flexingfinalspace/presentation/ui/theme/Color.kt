package shvyn22.flexingfinalspace.presentation.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val white500 = Color(0xFFCCCCCC)

val black800 = Color(0xFF242526)

val blue900 = Color(0xFF2C4975)

val LightColors = lightColors(
    primary = Color.White,
    onPrimary = Color.Black,
    secondary = blue900,
    surface = white500,
    onSurface = Color.Black,
    background = Color.White,
    onBackground = Color.Black
)

val DarkColors = darkColors(
    primary = black800,
    onPrimary = Color.White,
    secondary = blue900,
    surface = black800,
    onSurface = Color.White,
    background = Color.Black
)