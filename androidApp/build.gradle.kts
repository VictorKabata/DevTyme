plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = AndroidSdk.compileSdkVersion

    defaultConfig {
        applicationId = AndroidSdk.applicationId
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion

        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName
        testInstrumentationRunner = AndroidSdk.testInstrumentationRunner

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
        // kotlinCompilerVersion = Versions.composeCompiler
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions { jvmTarget = JavaVersion.VERSION_1_8.toString() }
}

dependencies {
    implementation(project(":shared"))

    implementation(Dependencies.androidCore)
    implementation(Dependencies.appCompat)

    implementation(Dependencies.material)

    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeTooling)
    // androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
    implementation(Dependencies.composeLiveData)
    // debugImplementation "androidx.compose.ui:ui-tooling:$compose_version"
    implementation(Dependencies.composeActivity)

    implementation(Dependencies.lifeCycleRuntime)

    // Koin-Dependency injection
    implementation(Dependencies.koinAndroid)
    implementation(Dependencies.koinCompose)

    // Accompanist Libs
    implementation(Dependencies.accompanistNavigationAnimation)

    // Splash Screen API
    implementation(Dependencies.splashScreen)

    // Coil-Image Loader
    implementation(Dependencies.coil)

    // Timber-Logging
    implementation(Dependencies.timber)

    // Compose Navigation-Navigation between various screens
    implementation(Dependencies.navigation)
}
