# Hypertrace Jacoco Report Plugin

###### org.hypertrace.jacoco-report-plugin

[![CircleCI](https://circleci.com/gh/hypertrace/hypertrace-gradle-jacoco-report-plugin.svg?style=svg)](https://circleci.com/gh/hypertrace/hypertrace-gradle-jacoco-report-plugin)
### Purpose
This plugin configures the jacoco report task for each test task in the project to be enabled
and depend on its corresponding test task. It is used in addition to the jacoco plugin.

If the respective report task is not present, then it also takes care of creating and configuring it.s

### Usages
Add the following snippet to the project's build.gradle.kts

```kotlin
plugins {
  jacoco
  id("org.hypertrace.jacoco-report-plugin") version "<version>"
}
```
