buildscript {
    configurations.all {
        resolutionStrategy.eachDependency {
            // https://github.com/google/dagger/issues/3068#issuecomment-1023798465
            if (requested.name == "javapoet") {
                useVersion("1.13.0")
            }
        }
    }
}

plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}
