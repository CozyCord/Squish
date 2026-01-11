//? if fabric {
package net.cozystudios.squish.fabric.datagen;

import net.cozystudios.squish.fabric.RegistryHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class SquishLootTableProvider extends FabricBlockLootTableProvider {
    public SquishLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(RegistryHelper.MELTED_SUGAR_BLOCK);
    }
}
//? }
