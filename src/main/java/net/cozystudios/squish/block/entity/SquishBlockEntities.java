package net.cozystudios.squish.block.entity;

import net.cozystudios.squish.Squish;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
//? if fabric {
import net.cozystudios.squish.fabric.RegistryHelper;
//? }

public class SquishBlockEntities {
    public static BlockEntityType<net.cozystudios.squish.block.entity.MeltedSugarBlockEntity> MELTED_SUGAR_BE;

    public static void register() {
        MELTED_SUGAR_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Squish.MOD_ID, "melted_sugar"),
                BlockEntityType.Builder.create(net.cozystudios.squish.block.entity.MeltedSugarBlockEntity::new, RegistryHelper.MELTED_SUGAR_BLOCK).build(null)
        );
    }
}
