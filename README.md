Add AcornLib to your modding project.

Inside your `build.gradle`
```java
repositories {
    // Modrinth
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = "https://api.modrinth.com/maven"
            }
        }
        filter {
            includeGroup "maven.modrinth"
        }
    }

    // Mod Menu
    maven {
        name = "Terraformers"
        url = "https://maven.terraformersmc.com/"
    }
}

dependencies {
    // AcornLib
    modImplementation "maven.modrinth:acorn-lib:${project.acornlib_version}"

    // Config
    modApi ("com.terraformersmc:modmenu:${project.modmenu_version}")
    modImplementation ("maven.modrinth:midnightlib:${project.midnightlib_version}")
}
```
Inside your `gradle.properties`
```java
acornlib_version=VERSION
modmenu_version=14.0.0-rc.2
midnightlib_version=1.7.3+1.21.4-fabric
```

You can find AcornLib's latest version on its [Modrinth Page](https://modrinth.com/mod/acorn-lib/versions)
