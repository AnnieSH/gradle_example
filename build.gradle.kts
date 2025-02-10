plugins {
    id("java")
    application
    jacoco

    // For jar packaging
    alias(libs.plugins.shadow)
    // Code cleanup
    alias(libs.plugins.spotless)
    // Java helpers
    alias(libs.plugins.lombok)
    // Dependency version checker
    alias(libs.plugins.versions)
}

group = "org.annieshu"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.guava)
    implementation(libs.jackson.core)
    implementation(libs.spring.boot.starter.webflux)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)

    testImplementation(platform(libs.mockito.bom))
    testImplementation(libs.bundles.mockito)

    testImplementation(libs.bundles.okhttp.test)

    testImplementation(libs.spring.boot.starter.test)
}

// For gradle run
application {
    mainClass = "Main"
}

spotless {
    java {
        googleJavaFormat()
    }
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}