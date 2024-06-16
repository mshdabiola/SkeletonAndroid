/*
 *abiola 2024
 */

package com.mshdabiola.data.repository

import com.mshdabiola.analytics.NoOpAnalyticsHelper
import com.mshdabiola.datastore.SkPreferencesDataSource
import com.mshdabiola.model.DarkThemeConfig
import com.mshdabiola.model.ThemeBrand
import com.mshdabiola.model.UserData
import com.mshdabiola.testing.datastore.testUserPreferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertEquals

class OfflineFirstUserDataRepositoryTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: OfflineFirstUserDataRepository

    private lateinit var niaPreferencesDataSource: SkPreferencesDataSource

    private val analyticsHelper = NoOpAnalyticsHelper()

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @Before
    fun setup() {
        niaPreferencesDataSource = SkPreferencesDataSource(
            tmpFolder.testUserPreferencesDataStore(testScope),
        )

        subject = OfflineFirstUserDataRepository(
            skPreferencesDataSource = niaPreferencesDataSource,
            analyticsHelper,
        )
    }

    @Test
    fun offlineFirstUserDataRepository_default_user_data_is_correct() =
        testScope.runTest {
            assertEquals(
                UserData(
                    themeBrand = ThemeBrand.DEFAULT,
                    darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
                    useDynamicColor = false,
                    shouldHideOnboarding = false,
                ),
                subject.userData.first(),
            )
        }

    @Test
    fun offlineFirstUserDataRepository_set_theme_brand_delegates_to_nia_preferences() =
        testScope.runTest {
            subject.setThemeBrand(ThemeBrand.GREEN)

            assertEquals(
                ThemeBrand.GREEN,
                subject.userData
                    .map { it.themeBrand }
                    .first(),
            )
            assertEquals(
                ThemeBrand.GREEN,
                niaPreferencesDataSource
                    .userData
                    .map { it.themeBrand }
                    .first(),
            )
        }

    @Test
    fun offlineFirstUserDataRepository_set_dynamic_color_delegates_to_nia_preferences() =
        testScope.runTest {
            subject.setDynamicColorPreference(true)

            assertEquals(
                true,
                subject.userData
                    .map { it.useDynamicColor }
                    .first(),
            )
            assertEquals(
                true,
                niaPreferencesDataSource
                    .userData
                    .map { it.useDynamicColor }
                    .first(),
            )
        }

    @Test
    fun offlineFirstUserDataRepository_set_dark_theme_config_delegates_to_nia_preferences() =
        testScope.runTest {
            subject.setDarkThemeConfig(DarkThemeConfig.DARK)

            assertEquals(
                DarkThemeConfig.DARK,
                subject.userData
                    .map { it.darkThemeConfig }
                    .first(),
            )
            assertEquals(
                DarkThemeConfig.DARK,
                niaPreferencesDataSource
                    .userData
                    .map { it.darkThemeConfig }
                    .first(),
            )
        }
}
