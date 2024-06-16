/*
 *abiola 2023
 */

package com.mshdabiola.detail

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.mshdabiola.designsystem.theme.SkTheme
import com.mshdabiola.testing.util.DefaultTestDevices
import com.mshdabiola.testing.util.captureForDevice
import com.mshdabiola.testing.util.captureMultiDevice
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.LooperMode
import java.util.TimeZone

/**
 * Screenshot tests for the [ForYouScreen].
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(application = HiltTestApplication::class)
@LooperMode(LooperMode.Mode.PAUSED)
class DetailScreenScreenshotTests {

    /**
     * Use a test activity to set the content on.
     */
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setTimeZone() {
        // Make time zone deterministic in tests
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }

    @Test
    fun forYouScreenPopulatedFeed() {
        composeTestRule.captureMultiDevice("ForYouScreenPopulatedFeed") {
            SkTheme {
                DetailScreen()
            }
        }
    }

    @Test
    fun detailWithText() {
        composeTestRule.captureMultiDevice("DetailWithText") {
            SkTheme {
                DetailScreen(title = "title", content = "content")
            }
        }
    }

    @Test
    fun detailScreen() {
        composeTestRule.captureMultiDevice("DetailScreen") {
            DetailScreen1()
        }
    }

    @Test
    fun detailScreen_Dark() {
        composeTestRule.captureForDevice(
            deviceName = "phone_dark",
            deviceSpec = DefaultTestDevices.PHONE.spec,
            screenshotName = "DetailScreen",
            darkMode = true,
        ) {
            DetailScreen1()
        }
    }

    @Test
    fun detailScreenWithText() {
        composeTestRule.captureMultiDevice("DetailScreenWithText") {
            DetailScreenWithText1()
        }
    }

    @Test
    fun detailScreenWithText_dark() {
        composeTestRule.captureForDevice(
            deviceName = "phone_dark",
            deviceSpec = DefaultTestDevices.PHONE.spec,
            screenshotName = "DetailScreenWithText",
            darkMode = true,
        ) {
            DetailScreenWithText1()
        }
    }

    @Composable
    private fun DetailScreen1() {
        SkTheme {
            DetailScreen()
        }
    }

    @Composable
    private fun DetailScreenWithText1() {
        SkTheme {
            DetailScreen(title = "title", content = "content")
        }
    }
}
