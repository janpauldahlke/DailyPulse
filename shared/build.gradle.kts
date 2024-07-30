plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation(libs.kotlinx.coroutines.core)
            }
        }


        //this is how to add dependencies
        val androidMain by getting {
            dependencies{
                //add android view model dependency here
                //btw api keyword allows usage in shared, implemntation only will allow it
                // to be used in androidMain folder
                // how to know about which ones are the correct deps here?
                // it will be added here gradle/libs.versions.toml
                implementation(libs.androidx.lifecycle.viewmodel.ktx)
            }
        }

        val iosMain by getting {
            dependencies {

            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "com.petros.efthymiou.dailypulse"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
