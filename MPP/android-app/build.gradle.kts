plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}


android {
    compileSdkVersion (30)
    buildToolsVersion ("30.0.1")

    defaultConfig {
        applicationId = "com.architecture.mpp"
        minSdkVersion (21)
        targetSdkVersion (30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include"  to listOf("*.jar"))))
    implementation (project(":mpp-library"))

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
    implementation ("androidx.core:core-ktx:1.3.0")
    implementation ("androidx.appcompat:appcompat:1.1.0")
    implementation ("androidx.constraintlayout:constraintlayout:1.1.3")

    implementation ("org.kodein.di:kodein-di-generic-jvm:6.4.0")
    implementation ("org.kodein.di:kodein-di-framework-android-x:6.4.0")
    
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7")

    testImplementation ("junit:junit:4.13")
    androidTestImplementation ("androidx.test.ext:junit:1.1.1")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.2.0")

}