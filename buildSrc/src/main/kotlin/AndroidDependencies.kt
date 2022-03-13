object AndroidDependencies {
    const val kotlin = "1.5.30"

    const val androidCore = "androidx.core:core-ktx:${Versions.androidCore}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val composeConstraint =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraint}"

    const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val accompanistNavigationAnimation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val coroutines = "1.5.2"
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"

    const val desugaring = "com.android.tools:desugar_jdk_libs:${Versions.desugaring}"

    const val lottie = "com.airbnb.android:lottie-compose:${Versions.lottie}"

    const val jUnit = "4.13.2"
    const val truth = "1.1.3"
    const val coroutinesTest = "1.5.0"
    const val robolectic = "4.5.1"
    const val mockWebServer = "4.7.2"
}
