import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val ktor_version: String by project

plugins {
  application
}

application {
  mainClassName = "com.github.wao.ServiceComponentBKt"
}

val shadowJar: ShadowJar by tasks
shadowJar.apply {
  manifest.attributes.apply {
    put("Implementation-Title", "Gradle Jar File Example")
    put("Implementation-Version", "1.0.0")
    put("Main-Class", "com.github.wao.ServiceComponentBKt")
  }

  baseName = project.name + "-all"
}

dependencies {
  implementation(project(":api"))
}