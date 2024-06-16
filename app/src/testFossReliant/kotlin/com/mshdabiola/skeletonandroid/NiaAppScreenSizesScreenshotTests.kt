/*
 *abiola 2023
 */

package com.mshdabiola.skeletonandroid

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.Posture
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.ForcedSize
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import com.github.takahirom.roborazzi.captureRoboImage
import com.google.samples.apps.nowinandroid.uitesthiltmanifest.HiltComponentActivity
import com.mshdabiola.data.repository.UserDataRepository
import com.mshdabiola.data.util.NetworkMonitor
import com.mshdabiola.designsystem.theme.SkTheme
import com.mshdabiola.skeletonandroid.ui.SkApp
import com.mshdabiola.skeletonandroid.ui.rememberSkAppState
import com.mshdabiola.testing.util.DefaultRoborazziOptions
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.LooperMode
import java.util.TimeZone
import javax.inject.Inject

/**
 * Tests that the navigation UI is rendered correctly on different screen sizes.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
// Configure Robolectric to use a very large screen size that can fit all of the test sizes.
// This allows enough room to render the content under test without clipping or scaling.
@Config(application = HiltTestApplication::class, qualifiers = "w1000dp-h1000dp-480dpi")
@LooperMode(LooperMode.Mode.PAUSED)
@HiltAndroidTest
class NiaAppScreenSizesScreenshotTests {

    /**
     * Manages the components' state and is used to perform injection on your test
     */
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    /**
     * Create a temporary folder used to create a Data Store file. This guarantees that
     * the file is removed in between each test, preventing a crash.
     */
    @BindValue
    @get:Rule(order = 1)
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    /**
     * Use a test activity to set the content on.
     */
    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<HiltComponentActivity>()

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    @Inject
    lateinit var userDataRepository: UserDataRepository

    @Before
    fun setup() {
        hiltRule.inject()

        // Configure user data
        runBlocking {
            userDataRepository.setShouldHideOnboarding(true)
        }
    }

    @Before
    fun setTimeZone() {
        // Make time zone deterministic in tests
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    private fun testNiaAppScreenshotWithSize(width: Dp, height: Dp, screenshotName: String) {
        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalInspectionMode provides true,
            ) {
                DeviceConfigurationOverride(
                    override = DeviceConfigurationOverride.ForcedSize(DpSize(width, height)),
                ) {
                    SkTheme {
                        val fakeAppState = rememberSkAppState(
                            networkMonitor = networkMonitor,
                        )
                        SkApp(
                            fakeAppState,
                            windowAdaptiveInfo = WindowAdaptiveInfo(
                                windowSizeClass = WindowSizeClass.compute(
                                    width.value,
                                    height.value,
                                ),
                                windowPosture = Posture(),
                            ),
                        )
                    }
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage(
                "src/testFossReliant/screenshots/$screenshotName.png",
                roborazziOptions = DefaultRoborazziOptions,
            )
    }

    @Test
    fun compactWidth_compactHeight_showsNavigationBar() {
        testNiaAppScreenshotWithSize(
            400.dp,
            400.dp,
            "compactWidth_compactHeight_showsNavigationBar",
        )
    }

    @Test
    fun mediumWidth_compactHeight_showsNavigationBar() {
        testNiaAppScreenshotWithSize(
            610.dp,
            400.dp,
            "mediumWidth_compactHeight_showsNavigationBar",
        )
    }

    @Test
    fun expandedWidth_compactHeight_showsNavigationBar() {
        testNiaAppScreenshotWithSize(
            900.dp,
            400.dp,
            "expandedWidth_compactHeight_showsNavigationBar",
        )
    }

    @Test
    fun compactWidth_mediumHeight_showsNavigationBar() {
        testNiaAppScreenshotWithSize(
            400.dp,
            500.dp,
            "compactWidth_mediumHeight_showsNavigationBar",
        )
    }

    @Test
    fun mediumWidth_mediumHeight_showsNavigationRail() {
        testNiaAppScreenshotWithSize(
            610.dp,
            500.dp,
            "mediumWidth_mediumHeight_showsNavigationRail",
        )
    }

    @Test
    fun expandedWidth_mediumHeight_showsNavigationRail() {
        testNiaAppScreenshotWithSize(
            900.dp,
            500.dp,
            "expandedWidth_mediumHeight_showsNavigationRail",
        )
    }

    @Test
    fun compactWidth_expandedHeight_showsNavigationBar() {
        testNiaAppScreenshotWithSize(
            400.dp,
            1000.dp,
            "compactWidth_expandedHeight_showsNavigationBar",
        )
    }

    @Test
    fun mediumWidth_expandedHeight_showsNavigationRail() {
        testNiaAppScreenshotWithSize(
            610.dp,
            1000.dp,
            "mediumWidth_expandedHeight_showsNavigationRail",
        )
    }

    @Test
    fun expandedWidth_expandedHeight_showsNavigationRail() {
        testNiaAppScreenshotWithSize(
            900.dp,
            1000.dp,
            "expandedWidth_expandedHeight_showsNavigationRail",
        )
    }
}
