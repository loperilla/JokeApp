plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kspPlugin)
    alias(libs.plugins.androidJUnit5)
    id("kotlinx-serialization")
}

android {
    namespace = "io.loperilla.jokeapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "io.loperilla.jokeapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }

    ksp {
        arg("KOIN_CONFIG_CHECK","true")
        arg("KOIN_USE_COMPOSE_VIEWMODEL","true")
        arg("room.schemaLocation", "$projectDir/schemas")
    }

    testOptions {
        animationsDisabled=true
        reportDir = "$rootDir/instrumentedTestsResults/reports/$project.name"
        resultsDir = "$rootDir/instrumentedTestsResults/results/$project.name"
        unitTests{
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
            all { test ->
                test.useJUnitPlatform()
            }
        }
    }
    applicationVariants.all {
        val variantName = name
        sourceSets {
            getByName("debug") {
                java.srcDir(File("build/generated/ksp/$variantName/kotlin"))
            }
        }
    }
}

dependencies {

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)

    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(platform(libs.koin.annotations.bom))
    implementation(libs.bundles.koin.annototations)
    api(libs.koin.annotations)

    implementation(libs.bundles.ktor)

    testImplementation(libs.junit)
    testImplementation(libs.koin.test)
    testImplementation(libs.turbine)
    testImplementation(libs.koin.junit5)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.mockk)
    testImplementation(libs.ktor.mock)
    testImplementation(libs.assertk)
    testImplementation(libs.core.testing)
    testImplementation(libs.bundles.jupiter)
    testRuntimeOnly(libs.jupiter.engine)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}