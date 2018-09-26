import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
}

android {
    compileSdkVersion(28)
    buildToolsVersion("28.0.3")
    dataBinding { isEnabled = true }
    defaultConfig {
        applicationId = "me.gr.sunflower"
        minSdkVersion(19)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }
}

val supportLibraryVersion = "1.0.0-rc02"
val navigationVersion: String by rootProject.extra
val roomVersion = "2.0.0-rc01"
val glideVersion = "4.4.0"
val espressoVersion = "3.1.0-alpha4"

dependencies {
    kapt("androidx.room:room-compiler:$roomVersion")
    kapt("com.github.bumptech.glide:compiler:$glideVersion")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation("androidx.appcompat:appcompat:$supportLibraryVersion")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-alpha2")
    implementation("androidx.core:core-ktx:1.0.0")
    implementation("androidx.legacy:legacy-support-v4:$supportLibraryVersion")
    implementation("androidx.recyclerview:recyclerview:$supportLibraryVersion")
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("android.arch.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("android.arch.navigation:navigation-ui-ktx:$navigationVersion")
    implementation("android.arch.work:work-runtime-ktx:1.0.0-alpha09")
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    implementation("com.google.android.material:material:$supportLibraryVersion")
    implementation("com.google.code.gson:gson:2.8.2")

    // Testing dependencies
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:$espressoVersion")
    androidTestImplementation("androidx.test.espresso:espresso-intents:$espressoVersion")
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.2.0-alpha4")
    androidTestImplementation("androidx.arch.core:core-testing:2.0.0")
}
