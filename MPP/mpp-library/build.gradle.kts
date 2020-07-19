import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
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

}

kotlin {
    targets {
        android()
        iosArm64("iosArm64").binaries{ framework("MultiPlatformLibrary") }
        iosX64("iosX64").binaries{ framework("MultiPlatformLibrary") }
    }

    sourceSets["commonMain"].dependencies {
        implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
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