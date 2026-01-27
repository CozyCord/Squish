package net.cozystudios.squish.registry.entity;

import net.cozystudios.squish.util.SquishId;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class SquishEntities {

    public static final EntityType<BabyCreeperEntity> BABY_CREEPER = Registry.register(
            Registries.ENTITY_TYPE,
            SquishId.of("baby_creeper"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BabyCreeperEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 0.9f))
                    .trackRangeBlocks(8)
                    .trackedUpdateRate(3)
                    .build()
    );

    public static final EntityType<BabyIronGolemEntity> BABY_IRON_GOLEM = Registry.register(
            Registries.ENTITY_TYPE,
            SquishId.of("baby_iron_golem"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BabyIronGolemEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f, 0.95f))
                    .trackRangeBlocks(8)
                    .trackedUpdateRate(3)
                    .build()
    );

    public static final EntityType<BabyEndermanEntity> BABY_ENDERMAN = Registry.register(
            Registries.ENTITY_TYPE,
            SquishId.of("baby_enderman"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BabyEndermanEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 1.0f))
                    .trackRangeBlocks(8)
                    .trackedUpdateRate(3)
                    .build()
    );

    private SquishEntities() {}

    public static void register() {
        FabricDefaultAttributeRegistry.register(BABY_CREEPER, BabyCreeperEntity.createBabyCreeperAttributes());
        FabricDefaultAttributeRegistry.register(BABY_IRON_GOLEM, BabyIronGolemEntity.createBabyIronGolemAttributes());
        FabricDefaultAttributeRegistry.register(BABY_ENDERMAN, BabyEndermanEntity.createBabyEndermanAttributes());
    }
}
