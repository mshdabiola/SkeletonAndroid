/*
 *abiola 2024
 */

package com.google.samples.apps.nowinandroid.core.model.data

import com.mshdabiola.model.ThemeBrand

/**
 * Class summarizing user interest data
 */
data class UserData(
    val bookmarkedNewsResources: Set<String>,
    val viewedNewsResources: Set<String>,
    val followedTopics: Set<String>,
    val themeBrand: ThemeBrand,
    val darkThemeConfig: DarkThemeConfig,
    val useDynamicColor: Boolean,
    val shouldHideOnboarding: Boolean,
)
