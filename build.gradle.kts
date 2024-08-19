plugins {
    kotlin("jvm") version "2.0.0"
    `maven-publish`
    signing
}

group = "io.github.andjsrk"
version = "1.0"
description = "A better way to write commands using CommandAPI"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    listOf(
        "io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT",
        "dev.jorel:commandapi-bukkit-core:9.5.3",
        "dev.jorel:commandapi-bukkit-kotlin:9.5.3",
    ).forEach {
        compileOnly(it)
        testCompileOnly(it)
    }
    testImplementation(kotlin("test"))
}

tasks {
    test {
        useJUnitPlatform()
    }
    create<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }
}
kotlin {
    jvmToolchain(21)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = rootProject.name
            from(rootProject.components["java"])
            artifact(rootProject.tasks["sourcesJar"])

            repositories {
                maven {
                    name = "central"
                    url = uri(
                        if ("SNAPSHOT" in version) "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                        else "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                    )

                    credentials.runCatching {
                        val nexusUsername: String by project
                        val nexusPassword: String by project
                        username = nexusUsername
                        password = nexusPassword
                    }
                }
            }

            pom {
                name.set(rootProject.name)
                description.set(rootProject.description)
                url.set("https://github.com/andjsrk/commandapi-safeext")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://www.opensource.org/licenses/mit-license.php")
                    }
                }
                scm {
                    url.set("https://github.com/andjsrk/commandapi-safeext")
                }
            }
        }
    }
}

signing {
    isRequired = true
    sign(publishing.publications)
}
