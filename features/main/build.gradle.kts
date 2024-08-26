/*
 *abiola 2022
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("mshdabiola.android.feature")
    id("mshdabiola.android.library.compose")
}

android {
    namespace = "com.mshdabiola.main"
}

dependencies {
    implementation(project(":modules:data"))
    implementation(project(":modules:domain"))

    testImplementation(libs.hilt.android.testing)
    testImplementation(project(":modules:testing"))

    androidTestImplementation(project(":modules:testing"))
}
