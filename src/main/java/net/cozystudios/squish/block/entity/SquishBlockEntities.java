package net.cozystudios.squish.block.entity;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.block.SquishBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SquishBlockEntities {
    public static BlockEntityType<net.cozystudios.squish.block.entity.MeltedSugarBlockEntity> MELTED_SUGAR_BE;

    public static void register() {
        MELTED_SUGAR_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Squish.MOD_ID, "melted_sugar"),
                BlockEntityType.Builder.create(net.cozystudios.squish.block.entity.MeltedSugarBlockEntity::new, SquishBlocks.MELTED_SUGAR_BLOCK).build(null)
        );
    }
}