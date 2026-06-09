plugins {
    java
    war
    id("io.spring.dependency-management") version "1.1.7"

}

group = "ru.hawoline"
version = "0.0.1-SNAPSHOT"
description = "TennisScoreBoard"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate.orm:hibernate-core:7.4.0.Final")
    implementation("com.h2database:h2:2.4.240")
    val springVersion = "7.0.8"
    implementation("org.springframework:spring-core:$springVersion")
    implementation("org.springframework:spring-context:$springVersion")
    implementation("org.springframework:spring-web:$springVersion")
    implementation("org.springframework:spring-webmvc:$springVersion")
    implementation("org.thymeleaf:thymeleaf-spring6:3.1.5.RELEASE")
    testImplementation(platform("org.junit:junit-bom:6.1.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
