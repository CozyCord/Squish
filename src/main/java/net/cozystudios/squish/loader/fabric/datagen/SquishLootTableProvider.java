//? if fabric {
package net.cozystudios.squish.loader.fabric.datagen;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
//? if <=1.20.4 {
/*import net.minecraft.enchantment.Enchantments;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.predicate.NumberRange;
*///?}
//? if >1.20.4 {
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
//?}
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.ExplosionDecayLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
//? if >1.20.4 {
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;
//?}

public class SquishLootTableProvider extends FabricBlockLootTableProvider {
    //? if <=1.20.4 {
    /*public SquishLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }
    *///?} else {
    private final RegistryWrapper.WrapperLookup registries;

    public SquishLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
        this.registries = registryLookup.join();
    }
    //?}

    @Override
    public void generate() {
        addDrop(RegistryHelper.MELTED_SUGAR_BLOCK);

        //? if <=1.20.4 {
        /*addDrop(RegistryHelper.HARDENED_SUGAR_BLOCK,
                dropsWithSilkTouch(RegistryHelper.HARDENED_SUGAR_BLOCK,
                        AlternativeEntry.builder(
                                // Fortune 3: 11-12
                                ItemEntry.builder(RegistryHelper.HARDENED_SUGAR_SHARD)
                                        .conditionally(MatchToolLootCondition.builder(
                                                ItemPredicate.Builder.create().enchantment(
                                                        new EnchantmentPredicate(Enchantments.FORTUNE, NumberRange.IntRange.atLeast(3)))))
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(11.0f, 12.0f)))
                                        .apply(ExplosionDecayLootFunction.builder()),
                                // Fortune 2: 8-10
                                ItemEntry.builder(RegistryHelper.HARDENED_SUGAR_SHARD)
                                        .conditionally(MatchToolLootCondition.builder(
                                                ItemPredicate.Builder.create().enchantment(
                                                        new EnchantmentPredicate(Enchantments.FORTUNE, NumberRange.IntRange.atLeast(2)))))
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 10.0f)))
                                        .apply(ExplosionDecayLootFunction.builder()),
                                // Fortune 1: 5-7
                                ItemEntry.builder(RegistryHelper.HARDENED_SUGAR_SHARD)
                                        .conditionally(MatchToolLootCondition.builder(
                                                ItemPredicate.Builder.create().enchantment(
                                                        new EnchantmentPredicate(Enchantments.FORTUNE, NumberRange.IntRange.atLeast(1)))))
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0f, 7.0f)))
                                        .apply(ExplosionDecayLootFunction.builder()),
                                // No fortune: 2-4
                                ItemEntry.builder(RegistryHelper.HARDENED_SUGAR_SHARD)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)))
                                        .apply(ExplosionDecayLootFunction.builder())
                        )));
        *///?} else {
        RegistryEntry<Enchantment> fortune = registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                .getOrThrow(net.minecraft.enchantment.Enchantments.FORTUNE);
        // For 1.21+, use uniformBonusCount which adds 0 to (level*3) extra items
        // Base: 2-4, Fortune adds roughly 3 per level
        addDrop(RegistryHelper.HARDENED_SUGAR_BLOCK,
                dropsWithSilkTouch(RegistryHelper.HARDENED_SUGAR_BLOCK,
                        applyExplosionDecay(RegistryHelper.HARDENED_SUGAR_BLOCK,
                                ItemEntry.builder(RegistryHelper.HARDENED_SUGAR_SHARD)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)))
                                        .apply(ApplyBonusLootFunction.uniformBonusCount(fortune, 3)))));
        //?}
    }
}
//? }
