package net.cozystudios.squish.item;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.block.SquishBlocks;
import net.cozystudios.squish.entity.SquishEssenceEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import vazkii.patchouli.common.item.ItemModBook;

public class SquishItems {

    public static final FoodComponent LOLLIPOP_FOOD = new FoodComponent.Builder()
            .hunger(2)
            .saturationModifier(1.0f)
            .snack()
            .alwaysEdible()
            .build();

    public static final FoodComponent SQUISH_CANDY_FOOD = new FoodComponent.Builder()
            .hunger(4)
            .saturationModifier(2.0f)
            .snack()
            .alwaysEdible()
            .build();

    public static final Item SQUISH_CANDY = new SquishCandyItem(new Item.Settings().maxCount(64).food(SQUISH_CANDY_FOOD));
    public static final Item LOLLIPOP = new SquishBaseItem(new Item.Settings().maxCount(64).food(LOLLIPOP_FOOD));
    public static final Item SQUISH_ESSENCE = new SquishEssenceItem(new Item.Settings().maxCount(16));
    public static final Item MELTED_SUGAR = new SquishBaseBlockItem(SquishBlocks.MELTED_SUGAR_BLOCK, new Item.Settings());
    public static final Item HARDENED_SUGAR = new SquishBaseBlockItem(SquishBlocks.HARDENED_SUGAR_BLOCK, new Item.Settings());
    public static final Item HARDENED_SUGAR_SHARD = new SquishBaseItem(new Item.Settings());
    public static final Item SQUISH_GUIDEBOOK = new SquishGuidebookItem(new Item.Settings().maxCount(1));

    public static final ItemStack SQUISH_GUIDEBOOK_STACK = createGuidebookStack();

    private static ItemStack createGuidebookStack() {
        ItemStack stack = new ItemStack(SQUISH_GUIDEBOOK);
        NbtCompound tag = stack.getOrCreateNbt();
        tag.putString("patchouli:book", "squish:squish_guidebook");
        return stack;
    }

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
        Registry.register(Registries.ITEM, new Identifier(Squish.MOD_ID, "squish_guidebook"), SQUISH_GUIDEBOOK);

        ItemStack stack = new ItemStack(SQUISH_GUIDEBOOK);
        NbtCompound tag = stack.getOrCreateNbt();
        tag.putString("patchouli:book", "squish:squish_guidebook");
    }
}