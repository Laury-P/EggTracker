plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt.android) //Pour Hilt
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.openclassroom.eggtracker"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.openclassroom.eggtracker"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // ######################
    // BASE ANDROID
    // ######################
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime)

    // ######################
    // UI COMPOSE
    // ######################
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.preview)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.material3)
    implementation(libs.activity.compose)
    implementation(libs.navigation.compose)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.foundation)

    // ######################
    // ROOM
    // ######################
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // ######################
    // HILT
    // ######################
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // ######################
    // API (Retrofit + OkHttp + Moshi)
    // ######################
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)


    // ######################
    // TESTS
    // ######################
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.turbine)
}
