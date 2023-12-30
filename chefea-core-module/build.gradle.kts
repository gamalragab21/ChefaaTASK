plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "net.gamal.chefaa.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        debug {
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    val truthVersion = "1.1.5"
    val turbineVersion = "1.0.0"
    val nhaarmanMockito = "2.2.0"
    val coroutinesTest = "1.7.3"

    // Unit Testing
    testImplementation("com.google.truth:truth:$truthVersion")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("app.cash.turbine:turbine:$turbineVersion")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:$nhaarmanMockito")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTest") {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
    }

    // Instrumented Testing
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("org.mockito:mockito-android:5.7.0")
    androidTestImplementation("com.google.truth:truth:$truthVersion")
    androidTestImplementation("app.cash.turbine:turbine:$turbineVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    androidTestImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:$nhaarmanMockito")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTest") {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
    }

    // ---------------------------------------------------------------------------------------------

    // Android
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Kotlin
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.20")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Work Manager for (Kotlin + coroutines)
    implementation("androidx.work:work-runtime-ktx:2.9.0")

    // Android Advertising ID (AAID)
    implementation("com.google.android.gms:play-services-ads-identifier:18.0.1")

    // Google Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.5")


    // Hilt
    val hiltVersion = "2.48.1"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // Inject WorkManager with Hilt
    implementation("androidx.hilt:hilt-work:1.1.0")
    kapt("androidx.hilt:hilt-compiler:1.1.0")
}