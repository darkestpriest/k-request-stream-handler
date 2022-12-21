pluginManagement {
    repositories {
        mavenCentral()
    }
}

buildCache {
    local {
        removeUnusedEntriesAfterDays = 1
    }
}

include("api")
