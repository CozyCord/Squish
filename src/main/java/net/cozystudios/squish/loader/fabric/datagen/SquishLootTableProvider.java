//? if fabric {
package net.cozystudios.squish.loader.fabric.datagen;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class SquishLootTableProvider extends FabricBlockLootTableProvider {
    public SquishLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(RegistryHelper.MELTED_SUGAR_BLOCK);

        // Hardened sugar block drops 2-4 shards, with Fortune bonus
        addDrop(RegistryHelper.HARDENED_SUGAR_BLOCK, block -> LootTable.builder()
                .pool(addSurvivesExplosionCondition(block, LootPool.builder()
                        .with(ItemEntry.builder(RegistryHelper.HARDENED_SUGAR_SHARD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)))
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))))));
    }
}
//? }
