// ============================================================================
// App Module build.gradle.kts - Multi-channel Build Configuration Demo
// ============================================================================

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.androiddemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.androiddemo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // ========================================================================
    // Signing Config - For Release Builds
    // ========================================================================
    signingConfigs {
        create("release") {
            // Note: In production, read from local.properties or environment variables
            // storeFile = file("keystore/release.keystore")
            // storePassword = System.getenv("KEYSTORE_PASSWORD")
            // keyAlias = System.getenv("KEY_ALIAS")
            // keyPassword = System.getenv("KEY_PASSWORD")
        }
    }

    // ========================================================================
    // Build Types Configuration
    // ========================================================================
    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // signingConfig = signingConfigs.getByName("release")
        }
    }

    // ========================================================================
    // Product Flavors - Multi-channel Build Configuration
    // ========================================================================
    // Use Cases:
    // 1. Different versions for app stores (Huawei, Xiaomi, OPPO, Google Play)
    // 2. Free / Paid versions
    // 3. Different environments (Dev / Staging / Prod)
    // ========================================================================
    
    // Define flavor dimensions
    flavorDimensions += listOf("channel", "environment")
    
    productFlavors {
        // ====================================================================
        // Channel Dimension - Different App Stores
        // ====================================================================
        create("huawei") {
            dimension = "channel"
            applicationIdSuffix = ".huawei"
            versionNameSuffix = "-huawei"
            // Huawei channel config
            buildConfigField("String", "CHANNEL_NAME", "\"huawei\"")
            buildConfigField("int", "CHANNEL_CODE", "1001")
            // Optional: use different resources
            // resValue("string", "app_name", "Demo-Huawei")
        }
        
        create("xiaomi") {
            dimension = "channel"
            applicationIdSuffix = ".xiaomi"
            versionNameSuffix = "-xiaomi"
            buildConfigField("String", "CHANNEL_NAME", "\"xiaomi\"")
            buildConfigField("int", "CHANNEL_CODE", "1002")
        }
        
        create("oppo") {
            dimension = "channel"
            applicationIdSuffix = ".oppo"
            versionNameSuffix = "-oppo"
            buildConfigField("String", "CHANNEL_NAME", "\"oppo\"")
            buildConfigField("int", "CHANNEL_CODE", "1003")
        }
        
        create("googleplay") {
            dimension = "channel"
            applicationIdSuffix = ".gp"
            versionNameSuffix = "-googleplay"
            buildConfigField("String", "CHANNEL_NAME", "\"googleplay\"")
            buildConfigField("int", "CHANNEL_CODE", "2001")
        }

        // ====================================================================
        // Environment Dimension - Dev / Prod
        // ====================================================================
        create("dev") {
            dimension = "environment"
            buildConfigField("String", "BASE_URL", "\"https://dev-api.example.com\"")
            buildConfigField("Boolean", "ENABLE_LOGGING", "true")
        }
        
        create("prod") {
            dimension = "environment"
            buildConfigField("String", "BASE_URL", "\"https://api.example.com\"")
            buildConfigField("Boolean", "ENABLE_LOGGING", "false")
        }
    }

    // ========================================================================
    // Compile Options
    // ========================================================================
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlinOptions {
        jvmTarget = "17"
    }

    // ========================================================================
    // Build Features - Compose & BuildConfig
    // ========================================================================
    buildFeatures {
        compose = true
        buildConfig = true  // Enable BuildConfig generation
        viewBinding = true  // Enable ViewBinding (for XML layout demo)
    }
}

// ============================================================================
// Dependencies - Using Version Catalog
// ============================================================================
dependencies {
    // AndroidX Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.lifecycle)
    
    // Jetpack Compose - Using BOM for version management
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.androidx.activity.compose)
    
    // Navigation
    implementation(libs.androidx.navigation.compose)
    
    // XML Layout Support (for comparison demo)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    
    // Coil - Image Loading Library
    // 图片加载库，支持网络图片、缓存、变换等
    implementation(libs.coil)
    implementation(libs.coil.compose)
    
    // Debug 依赖
    debugImplementation(libs.bundles.compose.debug)
    
    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
}

// ============================================================================
// Custom Gradle Task Example
// ============================================================================
// Note: To support Configuration Cache, we extract variant info at configuration time
// and use it at execution time. This avoids serializing Gradle script object references.
// ============================================================================

// Data class to hold variant information (must be Serializable for Configuration Cache)
data class VariantInfo(
    val name: String,
    val applicationId: String,
    val versionName: String?,
    val versionCode: Int?
) : java.io.Serializable

// Extract variant info at configuration time
val variantInfoList = mutableListOf<VariantInfo>()

android.applicationVariants.configureEach {
    variantInfoList.add(
        VariantInfo(
            name = name,
            applicationId = applicationId,
            versionName = versionName,
            versionCode = versionCode
        )
    )
}

tasks.register("printBuildVariants") {
    group = "custom"
    description = "Print all available build variants"
    
    // Capture the list as task input for Configuration Cache compatibility
    val variants = variantInfoList.toList()
    
    doLast {
        println("=".repeat(60))
        println("Available Build Variants:")
        println("=".repeat(60))
        variants.forEach { variant ->
            println("- ${variant.name}")
            println("  Application ID: ${variant.applicationId}")
            println("  Version: ${variant.versionName} (${variant.versionCode})")
            println()
        }
    }
}
