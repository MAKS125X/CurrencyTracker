@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
buildscript {

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.kotlin.serialization)
        classpath(libs.gradle)
        classpath(libs.hilt.android.gradle.plugin.v2381)
        classpath(libs.android.junit5)
    }
}

allprojects {

    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("org.jetbrains.changelog") version "2.0.0"
    alias(libs.plugins.kotlinAndroid) apply false
}