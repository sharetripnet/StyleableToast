# Setup OSS Library to Jitpack io

1. in the library module's build.gradle.kts, add     `id("maven-publish")`
```kotlin
plugins {
    id("maven-publish")
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    // other plugins....
}
```

2. Add this block to library module's build.gradle.kts:

```kotlin
publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate { // Ensures the Android component is properly evaluated
                from(components["release"])
            }
            groupId = "io.github.muddz" // Replace with your group ID
            artifactId = "styleabletoast"          // Replace with your artifact ID
            version = "v0.0.6"                 // Replace with your version
        }
    }
}
```

So your end file should look like this:
```kotlin
plugins {
    id("maven-publish")     // <--------------- add this
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "io.github.muddz.styleabletoast"
    compileSdk = 35

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

// <--------------- add this
publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate { // Ensures the Android component is properly evaluated
                from(components["release"])
            }
            groupId = "io.github.muddz" // Replace with your group ID
            artifactId = "styleabletoast"          // Replace with your artifact ID
            version = "v0.0.6"                 // Replace with your version
        }
    }
}

dependencies {
    // ... ... 
}
```

3. create a jitpack.yml file in project root. Add this to the file:

```yml
jdk:
  - openjdk17
before_install:
  - sdk install java 17.0.1-open
  - sdk use java 17.0.1-open
```

4. Run this command  in terminal:
   ./gradlew publishToMavenLocal

This command should run successfully, and you should get the generated 
files in `~/.m2/repository/` directory.