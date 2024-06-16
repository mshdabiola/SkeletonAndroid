/*
 *abiola 2022
 */

package com.mshdabiola.skeletonandroid.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.mshdabiola.designsystem.icon.SkIcons
import com.mshdabiola.main.R

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    FOR_YOU(
        selectedIcon = SkIcons.Upcoming,
        unselectedIcon = SkIcons.UpcomingBorder,
        iconTextId = R.string.features_main_title,
        titleTextId = R.string.features_main_title,
    ),
    BOOKMARKS(
        selectedIcon = SkIcons.Bookmarks,
        unselectedIcon = SkIcons.BookmarksBorder,
        iconTextId = R.string.features_main_title,
        titleTextId = R.string.features_main_title,
    ),
    INTERESTS(
        selectedIcon = SkIcons.Grid3x3,
        unselectedIcon = SkIcons.Grid3x3,
        iconTextId = R.string.features_main_title,
        titleTextId = R.string.features_main_title,
    ),
}
