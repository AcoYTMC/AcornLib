@file:Suppress("deprecation")

plugins {
    // This plugin applies the correct loom variant based on the Minecraft version
    id("dev.kikugie.loom-back-compat")
    id("com.modrinth.minotaur")
}

// DO NOT set group = ...!
version = "${property("mod.version")}+${sc.current.version}"
base.archivesName = property("mod.id") as String

val requiredJava: JavaVersion = when {
    sc.current.parsed >= "26.1" -> JavaVersion.VERSION_25
    sc.current.parsed >= "1.20.5" -> JavaVersion.VERSION_21
    sc.current.parsed >= "1.18" -> JavaVersion.VERSION_17
    sc.current.parsed >= "1.17" -> JavaVersion.VERSION_16
    else -> JavaVersion.VERSION_1_8
}

// This can be used for publishing on Modrinth and Curseforge
val compatibleVersions: List<String> = sc.properties.rawOrNull("mod", "mc_releases")
    ?.asList().orEmpty().map { it.toString() }

repositories {
    /**
     * Restricts dependency search of the given [groups] to the [maven URL][url],
     * improving the setup speed.
     */
    fun strictMaven(url: String, alias: String, vararg groups: String) = exclusiveContent {
        forRepository { maven(url) { name = alias } }
        filter { groups.forEach(::includeGroup) }
    }
    strictMaven("https://www.cursemaven.com", "CurseForge", "curse.maven")
    strictMaven("https://api.modrinth.com/maven", "Modrinth", "maven.modrinth")

    maven("https://maven.terraformersmc.com/") { name = "Terraformers" }
    maven("https://maven.ladysnake.org/releases") { name = "Ladysnake Mods" }
}

dependencies {
    minecraft("com.mojang:minecraft:${sc.current.version}")
    // Applies Mojang Mappings on obfuscated versions
    loomx.applyMojangMappings()

    // Use `mod{dependency type}` even on 26.1+ - loom-back-compat converts them
    modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")

    modImplementation("com.terraformersmc:modmenu:${property("deps.modmenu")}")
    modImplementation("maven.modrinth:midnightlib:${property("deps.midnightlib")}")

    modImplementation("org.ladysnake.cardinal-components-api:cardinal-components-base:${property("deps.cca")}")
    modImplementation("org.ladysnake.cardinal-components-api:cardinal-components-entity:${property("deps.cca")}")
}

val accessWidener = when {
    sc.eval(sc.current.version, ">26.1.2") -> "acornlib-latest.classtweaker"
    sc.eval(sc.current.version, ">1.21.1") -> "acornlib.classtweaker"
    else -> "acornlib-1.21.1.classtweaker"
}

loom {
    fabricModJsonPath = rootProject.file("src/main/resources/fabric.mod.json") // Useful for interface injection
    accessWidenerPath = sc.process(
        rootProject.file("src/main/resources/${accessWidener}"),
        "build/processed.classtweaker"
    )

    decompilerOptions.named("vineflower") {
        options.put("mark-corresponding-synthetics", "1") // Adds names to lambdas - useful for mixins
    }

    runConfigs.all {
        preferGradleTask = true
        generateRunConfig = true
        runDirectory = rootProject.file("run") // Shares the run directory between versions
        jvmArguments.add("-Dmixin.debug.export=true") // Exports transformed classes for debugging
        programArgs("--username AcoYT", "--uuid 017f5cdc-086b-4d98-a0c2-7dc43d5117bd")
    }
}

fabricApi {
    configureDataGeneration {
        modId = project.property("mod.id") as String
        client = true
    }
}

java {
    withSourcesJar()
    targetCompatibility = requiredJava
    sourceCompatibility = requiredJava

    toolchain {
        vendor = JvmVendorSpec.ADOPTIUM
        languageVersion = JavaLanguageVersion.of(requiredJava.majorVersion)
    }
}

tasks {
    processResources {
        fun MutableMap<String, String>.register(key: String, property: String) {
            val value: String = sc.properties[property]
            inputs.property(key, value)
            set(key, value)
        }

        val components = sc.properties.raw("mod:components").toJson().toString()
        val mixinJava = "JAVA_${requiredJava.majorVersion}"

        inputs.property("components", components)
        inputs.property("java", mixinJava)
        val props = buildMap {
            register("id", "mod.id")
            register("name", "mod.name")
            register("version", "mod.version")
            register("minecraft", "mod.mc_compat")
            this["access_widener"] = accessWidener
            this["components"] = components.removeSurrounding("[\"", "\"]")
            this["java"] = mixinJava
        }

        filesMatching(listOf("fabric.mod.json", "*.mixins.json")) { expand(props) }
    }

    // Includes the license file in the built mod
    withType<Jar> {
        val name = project.property("mod.id")
        inputs.property("mod_id", name)
        from("../../LICENSE") { rename { "$it-$name" } }
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        description = "Builds mod jars and copies results to `build/libs/{mod version}/`"

        inputs.property("version", project.property("mod.version"))
        // loomx.mod(Sources)Jar returns the jar task for the applied loom variant
        from(loomx.modJar.flatMap { it.archiveFile }, loomx.modSourcesJar.flatMap { it.archiveFile })
        into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
    }
}

// Thank you TheTyphothanian and SkyNotTheLimit!!
modrinth {
    token = System.getenv("MODRINTH_TOKEN")
    projectId = project.base.archivesName
    version = project.version

    uploadFile.set(loomx.modJar.flatMap { it.archiveFile })
    additionalFiles.add(loomx.modSourcesJar.flatMap { it.archiveFile })

    gameVersions.addAll(compatibleVersions)
    loaders.addAll("fabric")
    changelog = rootProject.file("changelog.md").readText()

    dependencies {
        required.project("fabric-api")
        optional.project("midnightlib")
        optional.project("modmenu")
        embedded.project("cardinal-components-api")
    }
}