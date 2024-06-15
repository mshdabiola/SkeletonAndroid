pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SkeletonAndroid"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":benchmarks")
include(":modules:data")
include(":modules:datastore")
include(":modules:model")
include(":modules:common")
include(":modules:testing")
include(":modules:database")
include(":modules:network")
include(":modules:analytics")
include(":modules:designsystem")
include(":modules:domain")
include(":modules:ui")
include(":features:main")
include(":features:detail")



