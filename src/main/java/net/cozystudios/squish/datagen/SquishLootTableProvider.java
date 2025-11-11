package net.cozystudios.squish.datagen;

import net.cozystudios.squish.block.SquishBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class SquishLootTableProvider extends FabricBlockLootTableProvider {
    public SquishLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(SquishBlocks.MELTED_SUGAR_BLOCK);
    }
}