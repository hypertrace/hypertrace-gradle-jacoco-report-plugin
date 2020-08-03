import org.hypertrace.gradle.publishing.License.APACHE_2_0

plugins {
  `java-gradle-plugin`
  id("org.hypertrace.ci-utils-plugin") version "0.1.1"
  id("org.hypertrace.publish-plugin") version "0.3.1"
}

group = "org.hypertrace.gradle.jacoco"

java {
  targetCompatibility = JavaVersion.VERSION_1_8
  sourceCompatibility = JavaVersion.VERSION_1_8
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
  license.set(APACHE_2_0)
}