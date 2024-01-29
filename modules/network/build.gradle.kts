/*
 *abiola 2024
 */
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("mshdabiola.android.library")
    id("mshdabiola.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.mshdabiola.network"
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
}
dependencies {
    androidTestImplementation(project(":modules:testing"))

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.resources)

    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.kotlinx.coroutines.test)

}