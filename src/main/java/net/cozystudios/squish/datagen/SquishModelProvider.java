package net.cozystudios.squish.datagen;

import net.cozystudios.squish.block.SquishBlocks;
import net.cozystudios.squish.item.SquishItems;
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
        blockStateModelGenerator.registerSimpleCubeAll(SquishBlocks.MELTED_SUGAR_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(SquishBlocks.HARDENED_SUGAR_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(SquishItems.SQUISH_CANDY, Models.GENERATED);
        itemModelGenerator.register(SquishItems.LOLLIPOP, Models.GENERATED);
        itemModelGenerator.register(SquishItems.SQUISH_ESSENCE, Models.GENERATED);
        itemModelGenerator.register(SquishItems.HARDENED_SUGAR_SHARD, Models.GENERATED);
    }
}