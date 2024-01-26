plugins {
    id("mshdabiola.android.library")
    id("mshdabiola.android.library.compose")
    id("mshdabiola.android.hilt")
}

android {
    namespace = "com.mshdabiola.testing"
}
dependencies {
//    implementation(project(":core:common"))
//    implementation(project(":core:data"))
//    implementation(project(":core:model"))
//
//    api(libs.junit4)
//    api(libs.androidx.test.core)
//    api(libs.kotlinx.coroutines.test)
//    api(libs.turbine)
////
//    api(libs.androidx.test.espresso.core)
//    api(libs.androidx.test.runner)
//    api(libs.androidx.test.rules)
//    api(libs.androidx.compose.ui.test)
//    api(libs.hilt.android.testing)
//    api(libs.paging.common)
//    api(libs.paging.testing)
//
//    debugApi(libs.androidx.compose.ui.testManifest)
    api(kotlin("test"))
    api(libs.androidx.compose.ui.test)
    api(libs.roborazzi)

    api(project(":modules:analytics"))
//    api(project(":modules:data"))
    api(project(":modules:model"))

//    api(projects.core.data)
//    api(projects.core.model)
//    api(projects.core.notifications)

    debugApi(libs.androidx.compose.ui.testManifest)

    implementation(libs.accompanist.testharness)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android.testing)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.kotlinx.datetime)
    implementation(libs.robolectric.shadows)
    implementation(project(":modules:common"))
    implementation(project(":modules:designsystem"))
}