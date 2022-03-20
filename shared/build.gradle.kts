plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version Versions.kotlinSerialization
    id("com.squareup.sqldelight")
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {

                implementation(KmmDependencies.kotlinxCoroutines)

                implementation(KmmDependencies.koinCore)

                implementation(KmmDependencies.kotlinxSerialization)

                implementation(KmmDependencies.ktorCore)
                implementation(KmmDependencies.ktorSerialization)
                implementation(KmmDependencies.ktorLogging)
                implementation(KmmDependencies.ktorClientAuth)

                implementation(KmmDependencies.sqlDelight)
                implementation(KmmDependencies.sqlDelightCoroutine)

                api(KmmDependencies.napier)

                implementation(KmmDependencies.kotlinxDateTime)

                implementation(KmmDependencies.multiplatformSettings)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(KmmDependencies.ktorAndroid)
                implementation(KmmDependencies.sqlDelightAndroid)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)

            dependencies {
                implementation(KmmDependencies.ktoriOS)
                implementation(KmmDependencies.sqlDelightIos)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                // implementation(KmmDependencies.mockk)
            }
        }

        val androidTest by getting

        val iosX64Test by getting
        val iosArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = AndroidSdk.compileSdkVersion
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion
    }
}

sqldelight {
    database(name = "AppDatabase") {
        packageName = "com.vickikbt.devtyme.data.cache.sqldelight"
        sourceFolders = listOf("kotlin")
    }
}
