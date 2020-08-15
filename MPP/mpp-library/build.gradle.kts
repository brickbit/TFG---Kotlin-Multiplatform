import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    kotlin("plugin.serialization") version "1.3.72"
}


android {
    compileSdkVersion (30)
    defaultConfig {
        minSdkVersion (21)
        targetSdkVersion (30)
    }
    sourceSets{
        getByName("main").setRoot("src/androidMain")
        getByName("release").setRoot("src/androidMainRelease")
        getByName("debug").setRoot("src/androidMainDebug")
        getByName("test").setRoot("src/androidUnitTest")
        getByName("testRelease").setRoot("src/androidUnitTestRelease")
        getByName("testDebug").setRoot("src/androidUnitTestDebug")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildToolsVersion = "30.0.1"

}

kotlin {
    targets {
        android()
        iosArm64("iosArm64").binaries{ framework("MultiPlatformLibrary") }
        iosX64("iosX64").binaries{ framework("MultiPlatformLibrary") }
    }

    sourceSets["commonMain"].dependencies {
        implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.5-native-mt")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.20.0")
        implementation("io.ktor:ktor-client-core:1.3.2")
    }

    sourceSets["androidMain"].dependencies {
        implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.5-native-mt")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
        implementation("io.ktor:ktor-client-android:1.3.2")
    }

    sourceSets["iosX64Main"].dependencies {
        implementation("io.ktor:ktor-client-ios:1.3.2")
        implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.5-native-mt")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.20.0")
    }

    sourceSets["iosArm64Main"].dependencies {
        implementation("io.ktor:ktor-client-ios:1.3.2")
        implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.5-native-mt")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.20.0")
    }
}

tasks.toList().forEach { task ->
    if (task !is KotlinNativeLink) return@forEach
    val framework = task.binary
    if (framework !is Framework) return@forEach
    val linkTask = framework.linkTask
    val syncTaskName = linkTask.name.replaceFirst("link", "sync")
    val syncFramework = tasks.create(syncTaskName, Sync::class) {
        group = "cocoapods"
        from(framework.outputDirectory)
        into(file("build/cocoapods/framework"))
    }
    syncFramework.dependsOn(linkTask)
}