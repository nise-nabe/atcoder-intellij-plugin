rootProject.name = "atcoder-intellij-plugin"

pluginManagement {
    repositories {
        gradlePluginPortal()
        exclusiveContent {
            forRepository {
                maven {
                    name = "gitHubPackages"
                    url = uri("https://maven.pkg.github.com/nise-nabe/gradle-plugins")
                    credentials(PasswordCredentials::class)
                }
            }
            filter {
                includeGroupByRegex("com.nisecoder.*")
            }
        }
    }

    plugins {
        // https://plugins.jetbrains.com/docs/intellij/kotlin.html#kotlin-gradle-plugin
        kotlin("jvm") version "1.7.10"
        id("org.jetbrains.intellij") version "1.8.1"
        id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.6"
        id("org.asciidoctor.jvm.convert") version "3.+"
        id("org.jetbrains.changelog") version "2.0.0"
        id("com.nisecoder.idea-ext-ext") version "0.0.8"
        id("com.nisecoder.github-pages.asciidoctor") version "0.0.8"
        id("com.nisecoder.github-release-upload") version "0.0.8"
    }
}
