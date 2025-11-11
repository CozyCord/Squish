package net.cozystudios.squish.item;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.block.SquishBlocks;
import net.cozystudios.squish.entity.SquishEssenceEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

public class SquishItems {
    public static final Item SQUISH_CANDY = new SquishCandyItem(new Item.Settings().maxCount(16));
    public static final Item LOLLIPOP = new Item(new Item.Settings().maxCount(64));
    public static final Item SQUISH_ESSENCE = new SquishEssenceItem(new Item.Settings().maxCount(16));
    public static final Item MELTED_SUGAR = new BlockItem(SquishBlocks.MELTED_SUGAR_BLOCK, new Item.Settings());
    public static final Item HARDENED_SUGAR = new BlockItem(SquishBlocks.HARDENED_SUGAR_BLOCK, new Item.Settings());
    public static final Item HARDENED_SUGAR_SHARD = new Item(new Item.Settings());

    public static final EntityType<SquishEssenceEntity> SQUISH_ESSENCE_ENTITY =
            Registry.register(Registries.ENTITY_TYPE,
                    new Identifier(Squish.MOD_ID, "squish_essence"),
                    FabricEntityTypeBuilder.<SquishEssenceEntity>create(SpawnGroup.MISC,
                                    (type, world) -> new SquishEssenceEntity(type, world))
                            .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                            .trackRangeBlocks(4).trackedUpdateRate(10)
                            .build()
            );

    public static void register() {
        Registry.register(Registries.ITEM, new Identifier(Squish.MOD_ID, "squish_candy"), SQUISH_CANDY);
        Registry.register(Registries.ITEM, new Identifier(Squish.MOD_ID, "squish_essence"), SQUISH_ESSENCE);
        Registry.register(Registries.ITEM, new Identifier(Squish.MOD_ID, "lollipop"), LOLLIPOP);
        Registry.register(Registries.ITEM, new Identifier(Squish.MOD_ID, "melted_sugar"), MELTED_SUGAR);
        Registry.register(Registries.ITEM, new Identifier(Squish.MOD_ID, "hardened_sugar"), HARDENED_SUGAR);
        Registry.register(Registries.ITEM, new Identifier(Squish.MOD_ID, "hardened_sugar_shard"), HARDENED_SUGAR_SHARD);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(SQUISH_CANDY);
            entries.add(LOLLIPOP);
            entries.add(SQUISH_ESSENCE);
            entries.add(MELTED_SUGAR);
            entries.add(HARDENED_SUGAR);
            entries.add(HARDENED_SUGAR_SHARD);
        });
    }

    private static Identifier id(String path) { return new Identifier(Squish.MOD_ID, path); }
}