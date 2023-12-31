plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("io.realm.kotlin")
//    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.udemy_multi_module_dairy_restarted"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.udemy_multi_module_dairy_restarted"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true

    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    val compose_version = "1.3.1"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.compose.material3:material3:1.1.0-alpha04")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")

// Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.5.3")

// Firebase
    implementation("com.google.firebase:firebase-auth-ktx:21.1.0")
    implementation("com.google.firebase:firebase-storage-ktx:20.1.0")

// Room components
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")

// Runtime Compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha04")

// Splash API
    implementation("androidx.core:core-splashscreen:1.0.0")

// Mongo DB Realm
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt") {
        version {
            strictly("1.6.0-native-mt")
        }
    }
    implementation("io.realm.kotlin:library-sync:1.0.2")

// Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-compiler:2.44.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

// Coil
    implementation("io.coil-kt:coil-compose:2.2.2")

// Pager - Accompanist
    implementation("com.google.accompanist:accompanist-pager:0.27.0")

// Date-Time Picker
    implementation("com.maxkeppeler.sheets-compose-dialogs:core:1.0.2")

// CALENDAR
    implementation("com.maxkeppeler.sheets-compose-dialogs:calendar:1.0.2")

// CLOCK
    implementation("com.maxkeppeler.sheets-compose-dialogs:clock:1.0.2")

// Message Bar Compose
    implementation("com.github.stevdza-san:MessageBarCompose:1.0.5")

// One-Tap Compose
    implementation("com.github.stevdza-san:OneTapCompose:1.0.0")

// Desugar JDK
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.2.0")
}