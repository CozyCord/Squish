package net.cozystudios.squish.item;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.entity.SquishEssenceEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

public class SquishItems {
    public static final Item SQUISH_CANDY = new SquishCandyItem(new Item.Settings().maxCount(16));
    public static final Item BLANK_LOLLIPOP = new Item(new Item.Settings().maxCount(64));
    public static final Item SQUISH_ESSENCE = new SquishEssenceItem(new Item.Settings().maxCount(16));

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
        Registry.register(Registries.ITEM, new Identifier(Squish.MOD_ID, "blank_lollipop"), BLANK_LOLLIPOP);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(SQUISH_CANDY);
            entries.add(BLANK_LOLLIPOP);
            entries.add(SQUISH_ESSENCE);
        });
    }

    private static Identifier id(String path) { return new Identifier(Squish.MOD_ID, path); }
}