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
modmenu_version=ACCORDING_MODMENU_VERSION
midnightlib_version=ACCORDING_MIDNIGHTLIB_VERSION
```

You can find AcornLib's latest version on its [Modrinth Page](https://modrinth.com/mod/acorn-lib/versions) and the according versions of ModMenu and Midnightlib in the [gradle.properties](https://github.com/AcoYTMC/AcornLib/blob/master/gradle.properties#L19)
