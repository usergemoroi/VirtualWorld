plugins {
    id("com.android.library")
}

android {
    namespace = "com.virtualworld.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        externalNativeBuild {
            cmake {
                cppFlags += "-std=c++17"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
        }
    }
}

dependencies {
    implementation("androidx.annotation:annotation:1.7.1")
}
