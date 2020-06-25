import org.hypertrace.gradle.publishing.License.AGPL_V3

plugins {
  `java-gradle-plugin`
  id("org.hypertrace.ci-utils-plugin") version "0.1.0"
  id("org.hypertrace.publish-plugin") version "0.1.0"
}

group = "org.hypertrace.gradle.jacoco"

java {
  targetCompatibility = JavaVersion.VERSION_11
  sourceCompatibility = JavaVersion.VERSION_11
}

gradlePlugin {
  plugins {
    create("gradlePlugin") {
      id = "org.hypertrace.jacoco-report-plugin"
      implementationClass = "org.hypertrace.gradle.jacoco.JacocoReportPlugin"
    }
  }
}

hypertracePublish {
  license.set(AGPL_V3)
}