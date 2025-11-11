package net.cozystudios.squish;

import net.cozystudios.squish.datagen.SquishModelProvider;
import net.cozystudios.squish.datagen.SquishRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SquishDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(SquishRecipeProvider::new);
        pack.addProvider(SquishModelProvider::new);
    }
}