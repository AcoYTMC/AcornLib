plugins {
    id("dev.kikugie.stonecutter")
    id("com.modrinth.minotaur") version "2.+" apply false
}

stonecutter active "26.3"

// See https://stonecutter.kikugie.dev/wiki/config/params
stonecutter parameters {
    swaps["mod_id"] = "\"${property("mod.id")}\";"
    swaps["mod_version"] = "\"${property("mod.version")}\";"
    swaps["minecraft"] = "\"${node.metadata.version}\";"
    constants["release"] = property("mod.id") != "template"
    dependencies["fapi"] = node.project.property("deps.fabric_api") as String

    replacements {
        string(current.parsed >= "1.21.11") {
            replace("ResourceLocation", "Identifier")
        }

        string(current.parsed >= "26.1") {
            replace("classTweaker v2 named", "classTweaker v2 official")
            replace("GuiGraphics", "GuiGraphicsExtractor")
            replace("state.CameraRenderState", "state.level.CameraRenderState")
            replace("state/CameraRenderState", "state/level/CameraRenderState")
        }

        string(current.parsed >= "26.3") {
            replace("WHITE_WOOL", "WOOL.white()")
            replace("ORANGE_WOOL", "WOOL.orange()")
            replace("MAGENTA_WOOL", "WOOL.magenta()")
            replace("LIGHT_BLUE_WOOL", "WOOL.lightBlue()")
            replace("YELLOW_WOOL", "WOOL.yellow()")
            replace("LIME_WOOL", "WOOL.lime()")
            replace("PINK_WOOL", "WOOL.pink()")
            replace("GRAY_WOOL", "WOOL.gray()")
            replace("LIGHT_GRAY_WOOL", "WOOL.lightGray()")
            replace("CYAN_WOOL", "WOOL.cyan()")
            replace("PURPLE_WOOL", "WOOL.purple()")
            replace("BLUE_WOOL", "WOOL.blue()")
            replace("BROWN_WOOL", "WOOL.brown()")
            replace("GREEN_WOOL", "WOOL.green()")
            replace("RED_WOOL", "WOOL.red()")
            replace("BLACK_WOOL", "WOOL.black()")

            replace("com.mojang.blaze3d.pipeline.RenderPipeline", "com.mojang.renderpearl.api.pipeline.RenderPipeline")
        }
    }
}
