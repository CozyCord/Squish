//? if fabric {
package net.cozystudios.squish.loader.fabric;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.registry.block.MeltedSugarBlock;
import net.cozystudios.squish.util.SquishId;
import net.cozystudios.squish.registry.effect.SugarRushStatusEffect;
import net.cozystudios.squish.registry.entity.EnderEssenceEntity;
import net.cozystudios.squish.registry.entity.ExplosiveEssenceEntity;
import net.cozystudios.squish.registry.entity.PoppyEssenceEntity;
import net.cozystudios.squish.registry.entity.SquishEssenceEntity;
import net.cozystudios.squish.registry.item.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
//? if <=1.20.4 {
/*import net.minecraft.block.enums.Instrument;
*///?}
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
//? if >1.20.4 {
import net.minecraft.registry.entry.RegistryEntry;
//?}
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.cozystudios.squish.registry.item.SquishItems.LOLLIPOP_FOOD;
import static net.cozystudios.squish.registry.item.SquishItems.SQUISH_CANDY_FOOD;
import static net.cozystudios.squish.registry.item.SquishItems.BITTER_CANDY_FOOD;
import static net.cozystudios.squish.registry.item.SquishItems.EXPLOSIVE_CANDY_FOOD;
import static net.cozystudios.squish.registry.item.SquishItems.POPPY_CANDY_FOOD;
import static net.cozystudios.squish.registry.item.SquishItems.ENDER_CANDY_FOOD;

public class RegistryHelper {
    // block registry
    public static final Block MELTED_SUGAR_BLOCK = registerBlock(
            "melted_sugar",
            new MeltedSugarBlock(
                FabricBlockSettings.create()
                    .mapColor(MapColor.ORANGE)
                    //? if <=1.20.4 {
                    /*.instrument(Instrument.BASS)
                    *///?}
                    .strength(0.0f)
                    .velocityMultiplier(0.4F)
                    .jumpVelocityMultiplier(0.5F)
                    .sounds(BlockSoundGroup.HONEY)
                    .nonOpaque()
                    .allowsSpawning((state, world, pos, entityType) -> false)
                    .solidBlock((state, world, pos) -> false)
                    .suffocates((state, world, pos) -> false)
                    .blockVision((state, world, pos) -> false)
            ),
            MeltedSugarBlockItem.class
    );

    public static final Block HARDENED_SUGAR_BLOCK = registerBlock(
            "hardened_sugar",
            new Block(
                FabricBlockSettings.create()
                    .mapColor(MapColor.ORANGE)
                    .strength(0.8f)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .nonOpaque()
            ),
            SquishBaseBlockItem.class
    );

    // item registry
    public static final Item SQUISH_CANDY = registerItem("squish_candy", new SquishCandyItem(new Item.Settings().maxCount(64).food(SQUISH_CANDY_FOOD)));
    public static final Item LOLLIPOP = registerItem("lollipop", new BlankLollipopItem(new Item.Settings().maxCount(64).food(LOLLIPOP_FOOD)));
    public static final Item SQUISH_ESSENCE = registerItem("squish_essence", new SquishEssenceItem(new Item.Settings().maxCount(16)));
    public static final Item HARDENED_SUGAR_SHARD = registerItem("hardened_sugar_shard", new SquishBaseItem(new Item.Settings()));
    public static final Item SQUISH_GUIDEBOOK = registerItem("squish_guidebook", new SquishGuidebookItem(new Item.Settings().maxCount(1)));

    // new items
    public static final Item BITTER_SUGAR_SHARD = registerItem("bitter_sugar_shard", new BitterSugarShardItem(new Item.Settings()));
    public static final Item BITTER_CANDY = registerItem("bitter_candy", new BitterCandyItem(new Item.Settings().maxCount(64).food(BITTER_CANDY_FOOD)));
    public static final Item EXPLOSIVE_ESSENCE = registerItem("explosive_essence", new ExplosiveEssenceItem(new Item.Settings().maxCount(16)));
    public static final Item EXPLOSIVE_CANDY = registerItem("explosive_candy", new ExplosiveCandyItem(new Item.Settings().maxCount(64).food(EXPLOSIVE_CANDY_FOOD)));
    public static final Item POPPY_ESSENCE = registerItem("poppy_essence", new PoppyEssenceItem(new Item.Settings().maxCount(16)));
    public static final Item POPPY_CANDY = registerItem("poppy_candy", new PoppyCandyItem(new Item.Settings().maxCount(64).food(POPPY_CANDY_FOOD)));
    public static final Item ENDER_ESSENCE = registerItem("ender_essence", new EnderEssenceItem(new Item.Settings().maxCount(16)));
    public static final Item ENDER_CANDY = registerItem("ender_candy", new EnderCandyItem(new Item.Settings().maxCount(64).food(ENDER_CANDY_FOOD)));

    public static final ItemStack SQUISH_GUIDEBOOK_STACK = createGuidebookStack();

    // entity registry
    public static final EntityType<SquishEssenceEntity> SQUISH_ESSENCE_ENTITY =
            registerEntity("squish_essence",
                    FabricEntityTypeBuilder.<SquishEssenceEntity>create(SpawnGroup.MISC,
                                    (type, world) -> new SquishEssenceEntity(type, world))
                            .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                            .trackRangeBlocks(4).trackedUpdateRate(10)
                            .build()
            );

    public static final EntityType<ExplosiveEssenceEntity> EXPLOSIVE_ESSENCE_ENTITY =
            registerEntity("explosive_essence",
                    FabricEntityTypeBuilder.<ExplosiveEssenceEntity>create(SpawnGroup.MISC,
                                    (type, world) -> new ExplosiveEssenceEntity(type, world))
                            .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                            .trackRangeBlocks(4).trackedUpdateRate(10)
                            .build()
            );

    public static final EntityType<PoppyEssenceEntity> POPPY_ESSENCE_ENTITY =
            registerEntity("poppy_essence",
                    FabricEntityTypeBuilder.<PoppyEssenceEntity>create(SpawnGroup.MISC,
                                    (type, world) -> new PoppyEssenceEntity(type, world))
                            .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                            .trackRangeBlocks(4).trackedUpdateRate(10)
                            .build()
            );

    public static final EntityType<EnderEssenceEntity> ENDER_ESSENCE_ENTITY =
            registerEntity("ender_essence",
                    FabricEntityTypeBuilder.<EnderEssenceEntity>create(SpawnGroup.MISC,
                                    (type, world) -> new EnderEssenceEntity(type, world))
                            .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                            .trackRangeBlocks(4).trackedUpdateRate(10)
                            .build()
            );

    // effects registry
    //? if <=1.20.4 {
    /*public static final StatusEffect SUGAR_RUSH = registerEffect("sugar_rush", new SugarRushStatusEffect());
    *///?} else {
    public static final RegistryEntry<StatusEffect> SUGAR_RUSH = registerEffect("sugar_rush", new SugarRushStatusEffect());
    //?}

    // helpers
    public static Block registerBlock(String name, Block block, Class<? extends BlockItem> blockItemClass) {
        registerBlockItem(name, block, blockItemClass);
        Identifier id = SquishId.of(Squish.MOD_ID, name);
        return Registry.register(Registries.BLOCK, id, block);
    }

    public static Item registerBlockItem(String name, Block block, Class<? extends BlockItem> blockItemClass) {
        Identifier id = SquishId.of(Squish.MOD_ID, name);
        Item.Settings settings = new Item.Settings();

        try {
            BlockItem blockItem = blockItemClass.getConstructor(Block.class, Item.Settings.class).newInstance(block, settings);
            return Registry.register(Registries.ITEM, id, blockItem);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create block item", e);
        }
    }

    public static <T extends Entity> EntityType<T> registerEntity(String name, EntityType<T> entityType) {
        Identifier id = SquishId.of(Squish.MOD_ID, name);
        return Registry.register(Registries.ENTITY_TYPE, id, entityType);
    }

    //? if <=1.20.4 {
    /*public static StatusEffect registerEffect(String name, StatusEffect effect) {
        Identifier id = SquishId.of(Squish.MOD_ID, name);
        return Registry.register(Registries.STATUS_EFFECT, id, effect);
    }
    *///?} else {
    public static RegistryEntry<StatusEffect> registerEffect(String name, StatusEffect effect) {
        Identifier id = SquishId.of(Squish.MOD_ID, name);
        return Registry.registerReference(Registries.STATUS_EFFECT, id, effect);
    }
    //?}

    public static Item registerItem(String name, Item item) {
        Identifier id = SquishId.of(Squish.MOD_ID, name);
        return Registry.register(Registries.ITEM, id, item);
    }

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, SquishId.of(Squish.MOD_ID, "squish_group"), FabricItemGroup.builder()
                .displayName(Text.literal("Squish"))
                .icon(() -> new ItemStack(SQUISH_CANDY))
                .entries((displayContext, entries) -> {
                    entries.add(SQUISH_CANDY);
                    entries.add(BITTER_CANDY);
                    entries.add(EXPLOSIVE_CANDY);
                    entries.add(POPPY_CANDY);
                    entries.add(ENDER_CANDY);
                    entries.add(LOLLIPOP);
                    entries.add(SQUISH_ESSENCE);
                    entries.add(EXPLOSIVE_ESSENCE);
                    entries.add(POPPY_ESSENCE);
                    entries.add(ENDER_ESSENCE);
                    entries.add(MELTED_SUGAR_BLOCK);
                    entries.add(HARDENED_SUGAR_BLOCK);
                    entries.add(HARDENED_SUGAR_SHARD);
                    entries.add(BITTER_SUGAR_SHARD);
                    entries.add(SQUISH_GUIDEBOOK_STACK);
                })
                .build());
        Squish.LOGGER.info("Registered Squish registry");
    }

    private static ItemStack createGuidebookStack() {
        return new ItemStack(SQUISH_GUIDEBOOK);
    }
}
//? }
