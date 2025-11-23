[![fabric-api](https://cdn.modrinth.com/data/cached_images/c4f1a960f18ab198a516e9e092572e3d4be63879.png)](https://modrinth.com/mod/fabric-api)
[![modmenu](https://cdn.modrinth.com/data/cached_images/e9fb2e9371d524583865608a6d3fada35581c8c3.png)](https://modrinth.com/mod/modmenu)
[![ko-fi](https://cdn.modrinth.com/data/cached_images/e824cc27f1e507782c301d1a820f8aff027da507.png)](https://ko-fi.com/acoyt)

## Features
### Modding Utilities
- Kill effect items (normal and no die [will run the effect, but will keep them alive, as if they used a totem])
- Events
    - Can consume event (disallows entities to eat if false)
    - Can entity heal event (disallows entities to heal if false)
    - Custom riptide event (returns an optional identifier [will not render if empty])
    - Render screen overlay event (renders an overlay over the player's screen)
- Custom item settings class (allows easy application of components)
- Advanced burning item (applies fire before damage, meaning entities will drop cooked food when one-shot)
- Base item with skins
- Shield-breaker item (returns the number of seconds to disable the shield, as a float)
- Utility classes
    - ItemUtils
    - MiscUtils
    - ParticleUtils
    - PortingUtils
    - VelocityUtils
- ModMenu compat helper
    - Ability to change mod name color in ModMenu
    - Ability to add (up to) two icons for your mod
### Extra Item Components
- Two-handed component (Pretty Self-Explanatory)
- "Follows camera" holding component (Makes the item be pointed at the holder's look direction, like a loaded crossbow)
- Show hand component (Shows the hand when holding an item)
- Undroppable component
- Item skin component (string)
- Custom hit particle component (example below)
- Custom hit sound component
- Sweep particle component (example below)
- Secondary model component (displays a custom item with identical components and display context to the parent stack)
- Tertiary model component (same as secondary model, example below)
### More Commands
- Velocity command (exact & directional)
- AcornLib command (dev env only, checks if player is a supporter)
### Supporter System
- Ability to make mods (AcoYT) supporter only
- Custom perks for supporters and friends in other mods using this library

Oh yeah, and to make this even more like a [Ratatouille](https://modrinth.com/mod/ratatouille) ripoff, have a (few) custom plushies of me (and my friends), on the house :]

***(THIS IS A JOKE, I TRIED TO DIFFERENTIATE FROM RATATOULIE, RAT PLEASE DON'T SUE ME)***

![Baby on ma' Head :3](https://cdn.modrinth.com/data/cached_images/3e1b7ac63fd73673cce416b13b5afbc8e0d69c7a.png)

## Examples
### Custom Hit Particle Component Give Command
```py
give @s acornlib:gnarp_plush[acornlib:hit_particle={particle:"acornlib:green_sweep"}]
```
**Notice:** You have to put a valid identifier in the sweep particle; otherwise, it sends an invalid packet, and kicks you from the game, might even crash your server, haven't checked

### Custom Hit Sound Component Give Command
```py
give @s acornlib:gnarp_plush[acornlib:hit_sound={soundEvent:"acornlib:block.gnarp_plush.honk"}]
```

### Secondary/Tertiary Model Component
```py
give @s acornlib:gnarp_plush[acornlib:secondary_model="carrot"]
```

## Example on how to implement
Put this in your repositories
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
```

Put this in your dependencies
```java
dependencies {
    // AcornLib
    modImplementation "maven.modrinth:acornlib:${project.acornlib_version}"

    // Config
    modApi ("com.terraformersmc:modmenu:${project.modmenu_version}")
    modImplementation ("maven.modrinth:midnightlib:${project.midnightlib_version}")
}
```

Put this inside your `gradle.properties`
```java
acornlib_version=VERSION
modmenu_version=ACCORDING_MODMENU_VERSION
midnightlib_version=ACCORDING_MIDNIGHTLIB_VERSION
```

You can find AcornLib's latest version on its [Modrinth Page](https://modrinth.com/mod/acorn-lib/versions) and the corresponding versions of ModMenu and Midnightlib in the [gradle.properties](https://github.com/AcoYTMC/AcornLib/blob/master/gradle.properties#L19)

## Credits
The Acorn Texture is made by [ShinyEmerald](https://modrinth.com/user/ShinyEmerald), in a resource pack listed [here](https://modrinth.com/resourcepack/apples2acorns/versions).
Apples2Acorns is listed under the license [`CC-BY-SA-4.0`](https://creativecommons.org/licenses/by-sa/4.0/legalcode.en#s3)
