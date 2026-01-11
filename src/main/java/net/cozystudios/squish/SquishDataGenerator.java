package net.cozystudios.squish;

//? if fabric {
import net.cozystudios.squish.loader.fabric.datagen.SquishBlockTagProvider;
import net.cozystudios.squish.loader.fabric.datagen.SquishLootTableProvider;
import net.cozystudios.squish.loader.fabric.datagen.SquishModelProvider;
import net.cozystudios.squish.loader.fabric.datagen.SquishRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
//? }

//? if fabric {
public class SquishDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(SquishRecipeProvider::new);
        pack.addProvider(SquishModelProvider::new);
        pack.addProvider(SquishLootTableProvider::new);
        pack.addProvider(SquishBlockTagProvider::new);
    }
//? }

//? if forgeLike {
/*public class SquishDataGenerator {
*///? }
}
