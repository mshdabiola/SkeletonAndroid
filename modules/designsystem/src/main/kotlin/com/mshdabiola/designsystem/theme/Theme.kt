/*
 *abiola 2024
 */

package com.mshdabiola.designsystem.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mshdabiola.model.Contrast
import com.mshdabiola.model.ThemeBrand


@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color,
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified,
    Color.Unspecified,
    Color.Unspecified,
    Color.Unspecified,
)

@Composable
fun  SkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeBrand: ThemeBrand = ThemeBrand.DEFAULT,
    themeContrast: Contrast = Contrast.Normal,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    val theme = when (themeBrand) {
        ThemeBrand.GREEN -> Theme.GreenTheme(darkTheme, themeContrast)
        else -> Theme.DefaultTheme(darkTheme, themeContrast)
    }

    val useDynamicTheme = when {
        themeBrand != ThemeBrand.DEFAULT -> false
        !disableDynamicTheming -> true
        else -> false
    }
    val colorScheme = if (useDynamicTheme && supportsDynamicTheming()) {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        theme.getColorScheme()
    }

    // Composition locals
    CompositionLocalProvider(
        LocalGradientColors provides if (useDynamicTheme) GradientColors(
            container = colorScheme.surfaceColorAtElevation(
                2.dp,
            ),
        ) else theme.getGradientColors(),
        LocalBackgroundTheme provides theme.getBackgroundTheme(),
        LocalTintTheme provides theme.getTintTheme(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = SkTypography,
            content = content,
        )
    }
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

