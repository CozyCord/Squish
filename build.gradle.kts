import gg.meza.stonecraft.mod

plugins {
    id("gg.meza.stonecraft")
}

modSettings {
    generatedResources = project.layout.projectDirectory.dir("src/main/generated")
    clientOptions {
        fov = 90
        guiScale = 3
        narrator = false
        darkBackground = true
        musicVolume = 0.0
    }
}

repositories {
    mavenCentral()
    maven("https://maven.blamejared.com")
    maven("https://jitpack.io")
}

dependencies {
    if (mod.isFabric) {
        modImplementation("net.fabricmc.fabric-api:fabric-api:${mod.prop("fabric_version")}")
        modApi("com.github.Virtuoel:Pehkui:${mod.prop("pehkui_version")}") {
            exclude(group = "net.fabricmc.fabric-api")
        }
        modImplementation("vazkii.patchouli:Patchouli:${mod.prop("patchouli_version")}")

        if (mod.prop("minecraft_version") == "1.20.1") {
            modCompileOnly("mezz.jei:jei-1.20.1-common-api:${mod.prop("jei_version")}")
            modCompileOnly("mezz.jei:jei-1.20.1-fabric-api:${mod.prop("jei_version")}")
        }
    }
}

