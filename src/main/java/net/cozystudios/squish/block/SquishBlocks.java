package net.cozystudios.squish.block;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.item.SquishItems;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.List;

public class SquishBlocks {
    public static final Block MELTED_SUGAR_BLOCK = new MeltedSugarBlock(
            FabricBlockSettings.create()
                    .mapColor(MapColor.ORANGE)
                    .instrument(Instrument.BASS)
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.HONEY)
                    .nonOpaque()
                    .allowsSpawning((state, world, pos, entityType) -> false)
                    .solidBlock((state, world, pos) -> false)
                    .suffocates((state, world, pos) -> false)
                    .blockVision((state, world, pos) -> false)
    );

    public static final Block HARDENED_SUGAR_BLOCK = new Block(
            FabricBlockSettings.create()
                    .mapColor(MapColor.ORANGE)
                    .strength(0.8f)
                    .sounds(BlockSoundGroup.GLASS)
                    .nonOpaque()
    ) {
        @Override
        public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
            ItemStack tool = builder.get(LootContextParameters.TOOL);
            if (tool != null && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, tool) > 0) {
                return Collections.singletonList(new ItemStack(this));
            }

            int count = 2 + builder.getWorld().random.nextInt(3); // 2–4 shards
            return Collections.singletonList(new ItemStack(SquishItems.HARDENED_SUGAR_SHARD, count));
        }
    };

    public static void register() {
        Registry.register(Registries.BLOCK, new Identifier(Squish.MOD_ID, "melted_sugar"), MELTED_SUGAR_BLOCK);
        Registry.register(Registries.BLOCK, new Identifier(Squish.MOD_ID, "hardened_sugar"), HARDENED_SUGAR_BLOCK);
    }
}