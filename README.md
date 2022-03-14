<p align="center"><img src="assets/logo_devtyme.png" alt="DevTyme logo" height="200px"></p>

<p align="center">
<img  src="https://img.shields.io/badge/-KOTLIN-006CBC?logo=kotlin&logoColor=white&style=for-the-badge">&nbsp;
<img  src="https://img.shields.io/badge/-ANDROID-006CBC?logo=android&logoColor=white&style=for-the-badge">&nbsp;
<img  src="https://img.shields.io/badge/-LICENSE:%20MIT-006CBC?logo=licenselogoColor=white&style=for-the-badge">&nbsp;
</p>

# DevTyme

🛠️ Work In progress - Develop Branch

Kotlin Multiplatform Mobile application built using that consumes [WakaTime API ⏱️](https://wakatime.com/developers) to display user's coding stats such as day's or day's of weeks coding time, coding time for various programming languages or worked on projects etc. on various platforms.

## Table Of Content

- [Libraries](#libraries)
  - [Shared](#shared)
  - [Android](#android)
- [Extras](#extras)
- [Related Resources](#related-resources)
- [Other Helpful Resources](#other-helpful-resources)

## Libraries

### Shared

- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library support for Kotlin coroutines with multiplatform support.
- [Koin](https://insert-koin.io/docs/setup/v3.1) - Kotin dependency injection library with multiplatform support.
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) - Provides sets of libraries for various serialization formats eg. JSON, protocol buffers, CBOR etc.
- [Ktor](https://ktor.io/docs/http-client-multiplatform.html) - Provides multiplatform libraries required to make network calls to the REST API.
- [Realm](https://github.com/realm/realm-kotlin) - Caching of application data from network responses.
- [Multiplatform Settings](https://github.com/russhwolf/multiplatform-settings) - This is a Kotlin library for Multiplatform apps, so that common code can persist key-value data.
- [Napier](https://github.com/AAkira/Napier) -  Logger library for Kotlin Multiplatform.
- [Mockk](https://github.com/mockk/mockk) - Library for creating mocks for tests.

### Android

- [Jetpack Compose](https://developer.android.com/jetpack/compose?gclid=Cj0KCQiA95aRBhCsARIsAC2xvfwC4pw6JG3r8U_4zVVSzwfCSIMMM8MKPMGAOTRoMjpkfpimPVz1FwoaAqlUEALw_wcB&gclsrc=aw.ds) - Modern toolkit for building native UI.
- [Coil](https://coil-kt.github.io/coil/) - An image loading library for Android backed by kotlin coroutines.
- [Splash Screen API](https://developer.android.com/guide/topics/ui/splash-screen) - Splash screen API reduces boilerplate code required to create a splash screen.
- [Accompanist Navigation Animation](https://google.github.io/accompanist/navigation-animation/) - Add animation support when navigating between screens using Compose navigation component.
- [Lottie Compose](https://github.com/airbnb/lottie/blob/master/android-compose.md) - Supports loading Lottie animations using jetpack compose.

## Extras

- [GitHub Actions](https://docs.github.com/en/actions) -The project uses GitHub actions for CI?CD operations such as running automated builds, tests and deploying applications.

## Related Resources

In this section I've included some resources ie. articles and GitHub reposirtories that i used to learn about kotlin mutltiplatform mobile:

### Videos 📽️

- [Your First Kotlin Multiplatform Mobile App Tutorial](https://www.youtube.com/watch?v=GcqFhoUuNNI)
- [Sharing Code between iOS and Android with Kotlin #1](https://www.youtube.com/watch?v=mdN6P6RI__k&t=13s)

### Articles/Blogs 📖

- [Kotlin Multiplatform Hands-on: Networking and Data Storage](https://play.kotlinlang.org/hands-on/Networking%20and%20Data%20Storage%20with%20Kotlin%20Multiplatfrom%20Mobile/01_Introduction)
- [KaMPKit General Architecture](https://github.com/touchlab/KaMPKit/blob/main/docs/GENERAL_ARCHITECTURE.md)
- [Using Koin in a Kotlin Multiplatform Project](https://johnoreilly.dev/posts/kotlinmultiplatform-koin/)
- [Create your first cross-platform mobile app – tutorial](https://kotlinlang.org/docs/multiplatform-mobile-create-first-app.html) - Learn how to create and run your first Kotlin Multiplatform Mobile application.
- [Kotlin Multiplatform. Very beginner’s guide (part 1-3)](https://medium.com/proandroiddev/kotlin-multiplatform-very-beginners-guide-part-1-6419f74afa0f)
- [Using Realm persistence library in a Kotlin Multiplatform project](https://johnoreilly.dev/posts/realm-kotlinmultiplatform/)

### Sample Projects 🤖

- [KMM Sample](https://github.com/KaterinaPetrova/kmm-sample) - A sample project for experiments with Kotlin Multiplatform mobile.
- [Fantasy Premier League](https://github.com/joreilly/FantasyPremierLeague) - Kotlin Multiplatform project with Jetpack Compose, Compose for Desktop and SwiftUI clients (and using Ktor for remote API requests and Realm for persistence).
- [KaMPKit](https://github.com/touchlab/KaMPKit)
- [People In Space](https://github.com/joreilly/PeopleInSpace) - Minimal Kotlin Multiplatform project with SwiftUI, Jetpack Compose, Compose for Wear OS, Compose for Desktop, Compose for Web, and Kotlin/JS + React clients along with Ktor backend.

## Other Helpful Resources

In this section I've included resources that are not related to kotin multiplatform mobile but were really helpful in learning other android components and tools:

### Videos 📽️

- ToDo

### Articles/Blogs 📖

- ToDo

### Sample Projects 🤖

- [MortyComposeKMM](https://github.com/joreilly/MortyComposeKMM) - Kotlin Multiplatform sample that demonstrates use of GraphQL + Jetpack Compose and SwiftUI (based on <https://github.com/Dimillian/MortyUI> SwiftUI project).
