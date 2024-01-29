/*
 *abiola 2022
 */

package com.mshdabiola.skeletonandroid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mshdabiola.main.navigation.MAIN_ROUTE
import com.mshdabiola.main.navigation.mainScreen
import com.mshdabiola.skeletonandroid.ui.SkAppState

@Composable
fun SkNavHost(
    appState: SkAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = MAIN_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        mainScreen(onShowSnackbar = onShowSnackbar, onClicked = {})
    }
}
