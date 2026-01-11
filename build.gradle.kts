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
    }
}

// TODO: publishing config
//publishMods {
//    modrinth {
//        if (mod.isFabric) requires("fabric-api")
//    }
//
//    curseforge {
//        clientRequired = true // Set as needed
//        serverRequired = false // Set as needed
//        if (mod.isFabric) requires("fabric-api")
//    }
//}
