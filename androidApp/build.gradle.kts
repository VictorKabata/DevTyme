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
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions { jvmTarget = JavaVersion.VERSION_1_8.toString() }
}

dependencies {
    implementation(project(":shared"))

    implementation(AndroidDependencies.androidCore)
    implementation(AndroidDependencies.appCompat)

    implementation(AndroidDependencies.material)

    implementation(AndroidDependencies.composeUi)
    implementation(AndroidDependencies.composeMaterial)
    implementation(AndroidDependencies.composeTooling)
    implementation(AndroidDependencies.composeConstraint)
    // androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
    implementation(AndroidDependencies.composeLiveData)
    // debugImplementation "androidx.compose.ui:ui-tooling:$compose_version"
    implementation(AndroidDependencies.composeActivity)

    implementation(AndroidDependencies.lifeCycleRuntime)

    // Koin-Dependency injection
    implementation(AndroidDependencies.koinAndroid)
    implementation(AndroidDependencies.koinCompose)

    // Accompanist Libs
    implementation(AndroidDependencies.accompanistNavigationAnimation)

    // Splash Screen API
    implementation(AndroidDependencies.splashScreen)

    // Coil-Image Loader
    implementation(AndroidDependencies.coil)

    // Compose Navigation-Navigation between various screens
    implementation(AndroidDependencies.navigation)

    coreLibraryDesugaring(AndroidDependencies.desugaring)
}
