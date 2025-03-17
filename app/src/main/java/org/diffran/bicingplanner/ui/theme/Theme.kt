package org.diffran.bicingplanner.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = RosaSalmo,
    secondary = Pink80,
    tertiary = Pink80,
)

private val LightColorScheme = lightColorScheme(
    primary = VermellBicing,
    secondary = PurpleGrey40,
    tertiary = VermellBicing,

    onTertiaryContainer = VermellSuau,
    onTertiary = VermellSuau,
    //onSurfaceVariant = VermellSuau, <- punts de la slidebar

    surfaceBright = Blue,
    surfaceDim = Blue,
    surfaceContainerLow = Blue,
    surfaceContainer = Blue,

    surfaceContainerHigh = Blue,
    surfaceContainerLowest = Blue,
    inverseOnSurface = Blue,

    onSurfaceVariant = VermellBicing, //punts slide bar menys opacitat
    //surface = White,
    outline = VermellBicing,
    onSurface = VermellBicing,
    secondaryContainer = VermellBicing,
    onSecondaryContainer = White,
    surfaceTint = White,//color bottom bar



    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun BicingPlannerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}