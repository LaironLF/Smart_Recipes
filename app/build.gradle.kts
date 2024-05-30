plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //dagger hilt
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    kotlin("plugin.serialization")

}

android {
    namespace = "com.laironlf.smartRecipes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.laironlf.smartRecipes"
        minSdk = 27
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

//    implementation("com.yarolegovich:sliding-root-nav:1.1.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.github.seven332:drawerlayout:0.1.0")
//    implementation("com.github.lzls:SlidingDrawerLayout:2.0.2")
//    implementation("com.mikepenz:materialdrawer:9.0.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation(project(":circleMenu"))
    implementation(project(":data"))
    implementation(project(":domain"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.facebook.shimmer:shimmer:0.5.0")
    implementation("com.github.dhaval2404:imagepicker:2.1")
    implementation("io.github.ShawnLin013:number-picker:2.4.13")

    //for api
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.7.2")
    implementation("com.squareup.okhttp3:logging-interceptor:4.7.0")

    //serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    implementation("com.squareup.picasso:picasso:2.8")

    //splash
    implementation("androidx.core:core-splashscreen:1.0.1")
    //hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
}
kapt {
    correctErrorTypes = true
}