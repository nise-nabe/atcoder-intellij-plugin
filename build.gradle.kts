import com.nisecoder.gradle.plugin.idea.ext.packagePrefix
import com.nisecoder.gradle.plugin.idea.ext.settings
import org.jetbrains.changelog.ChangelogPluginExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.intellij")
    id("org.jetbrains.changelog")
    id("org.jetbrains.gradle.plugin.idea-ext")
    id("com.nisecoder.idea-ext-ext")
    id("com.nisecoder.github-pages.asciidoctor")
    id("org.asciidoctor.jvm.convert")
    id("com.nisecoder.github-release-upload")
}

group = "com.nisecoder.intellij"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

idea {
    module {
        settings {
            packagePrefix["src/main/kotlin"] = "com.nisecoder.intellij.plugins.atcoder"
        }
    }
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2022.2")
    type.set("IU")
    downloadSources.set(true)
    plugins.set(listOf("org.jetbrains.plugins.gradle"))
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(11))
    }
}


tasks {
    runIde {
        autoReloadPlugins.set(true)
    }
    val changelog: ChangelogPluginExtension = extensions.getByType()
    patchPluginXml {
        changeNotes.set(provider { changelog.getLatest().toHTML()} )
    }

    withType<KotlinCompile>().configureEach {
        kotlinOptions {
            // https://plugins.jetbrains.com/docs/intellij/kotlin.html#adding-kotlin-support
            apiVersion = "1.6"
            languageVersion = "1.6"
            javaParameters = true
        }
    }

    githubReleaseUpload {
        githubRepository.set("nise-nabe/atcoder-intellij-plugin")
        val credentials = providers.credentials(PasswordCredentials::class, "github")
        githubToken.set(credentials.map { it.password ?: throw GradleException("githubPassword is required") })
        releaseName.set(provider { "v$version" })
        releaseFile.set(buildPlugin.flatMap { it.archiveFile })
    }
}
