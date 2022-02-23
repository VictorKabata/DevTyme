plugins {
    kotlin("multiplatform")
    id("com.android.library")
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

                implementation(Dependencies.coroutinesKmm)

                implementation(Dependencies.koinCore)

                implementation(Dependencies.kotlinxSerialization)

                implementation(Dependencies.ktorCore)
                implementation(Dependencies.ktorSerialization)
                implementation(Dependencies.ktorLogging)
                implementation(Dependencies.ktorAuth)

                // implementation(Dependencies.realm)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Dependencies.ktorAndroid)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)

            dependencies {
                implementation(Dependencies.ktoriOS)
            }
        }

        /*val iosMain by getting {
            dependencies {
            }
        }*/

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
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
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
}
