import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin

val kotlin_version: String by project
val kotlin_coroutines_version: String by project

plugins {
  base
  kotlin("jvm") version "1.3.41"
  id("com.github.johnrengelman.shadow") version "5.1.0"
}

allprojects {
  apply<JavaLibraryPlugin>()
  apply<KotlinPlatformJvmPlugin>()
  apply(plugin = "com.github.johnrengelman.shadow")

  group = "com.github.wao"
  version = "1.0"

  repositories {
    jcenter()
  }

  configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    manifest {
      attributes(mapOf("Implementation-Title" to project.name, "Implementation-Version" to project.version))
    }
  }

  configurations.compileClasspath {
    resolutionStrategy.activateDependencyLocking()
  }

  configurations.runtimeClasspath {
    resolutionStrategy.activateDependencyLocking()
  }

  dependencies {
    val implementation by configurations

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines_version")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
  }
}

tasks.withType<Wrapper> {
  gradleVersion = "5.6-rc-2"
}
