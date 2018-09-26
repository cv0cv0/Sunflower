// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val navigationVersion by extra("1.0.0-alpha05")

    repositories {
        google()
        jcenter()
        maven { url = uri("http://dl.bintray.com/kotlin/kotlin-eap") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.2.0")
        classpath(kotlin("gradle-plugin", "1.3.0-rc-57"))
        classpath("android.arch.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("com.diffplug.gradle.spotless") version "3.13.0"
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("http://dl.bintray.com/kotlin/kotlin-eap") }
    }
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint("0.28.0")
    }
}