//? if fabric {
package net.cozystudios.squish.loader.fabric.datagen;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.Models;

public class SquishModelProvider extends FabricModelProvider {
    public SquishModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(RegistryHelper.MELTED_SUGAR_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(RegistryHelper.HARDENED_SUGAR_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(RegistryHelper.SQUISH_CANDY, Models.GENERATED);
        itemModelGenerator.register(RegistryHelper.LOLLIPOP, Models.GENERATED);
        itemModelGenerator.register(RegistryHelper.SQUISH_ESSENCE, Models.GENERATED);
        itemModelGenerator.register(RegistryHelper.HARDENED_SUGAR_SHARD, Models.GENERATED);
        itemModelGenerator.register(RegistryHelper.BITTER_SUGAR_SHARD, Models.GENERATED);
        itemModelGenerator.register(RegistryHelper.BITTER_CANDY, Models.GENERATED);
        itemModelGenerator.register(RegistryHelper.EXPLOSIVE_ESSENCE, Models.GENERATED);
        itemModelGenerator.register(RegistryHelper.EXPLOSIVE_CANDY, Models.GENERATED);
        itemModelGenerator.register(RegistryHelper.POPPY_ESSENCE, Models.GENERATED);
        itemModelGenerator.register(RegistryHelper.POPPY_CANDY, Models.GENERATED);
    }
}
//? }
