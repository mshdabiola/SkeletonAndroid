/*
 *abiola 2024
 */
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("mshdabiola.android.library")
    id("mshdabiola.android.library.compose")
    id("mshdabiola.android.library.jacoco")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.mshdabiola.ui"
}

dependencies {
    api(libs.androidx.metrics)
    api(libs.androidx.metrics)
    api(projects.modules.analytics)
    api(projects.modules.designsystem)
    api(projects.modules.model)

    implementation(libs.androidx.browser)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    androidTestImplementation(projects.modules.testing)
}
