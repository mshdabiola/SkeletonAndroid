/*
 *abiola 2022
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("mshdabiola.android.feature")
    id("mshdabiola.android.library.compose")
    id("mshdabiola.android.library.jacoco")
}

android {
    namespace = "com.mshdabiola.detail"
}

dependencies {
    implementation(projects.modules.data)
    implementation(projects.modules.domain)

    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.robolectric)
    testImplementation(projects.modules.testing)
    testImplementation(projects.modules.screenshotTesting)


    androidTestImplementation(projects.modules.testing)
}
