/*
 *abiola 2024
 */
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("mshdabiola.android.library")

    id("mshdabiola.android.hilt")
    id("mshdabiola.android.room")
}

android {
    defaultConfig {
        testInstrumentationRunner = "com.mshdabiola.testing.TestRunner"

    }
    namespace = "com.mshdabiola.database"


}

room {
    schemaDirectory("$projectDir/schemas")
}
dependencies {
    implementation(project(":modules:model"))
    implementation(libs.kotlinx.datetime)

    androidTestImplementation(project(":modules:testing"))

}
tasks.withType<Test> {
    if (name == "mergeDebugAndroidTestAssetsFossReliant") {
        enabled = false
    }
}

tasks.withType<Test> {
    if (name == "copyRoomSchemasToAndroidTestAssetsFossReliantDebugAndroidTest") {
        enabled = false
    }
}

tasks.whenTaskAdded {
    if (name.contains("copyRoomSchemasToAndroidTestAssetsFossReliantDebugAndroidTest")) {
        enabled = false
    }
}

gradle.taskGraph.whenReady {
    allTasks.onEach { task ->
        if (task.name.contains("androidTest") || task.name.contains("connectedAndroidTest")) {
            task.enabled = false
        }
    }
}