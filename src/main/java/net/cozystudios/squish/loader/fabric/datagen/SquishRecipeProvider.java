//? if fabric {
package net.cozystudios.squish.loader.fabric.datagen;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.cozystudios.squish.util.SquishId;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
//? if <=1.20.4 {
/*import net.minecraft.data.server.recipe.RecipeJsonProvider;
*///?} else {
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.registry.RegistryWrapper;
//?}
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

//? if <=1.20.4 {
/*import java.util.function.Consumer;
*///?} else {
import java.util.concurrent.CompletableFuture;
//?}

public class SquishRecipeProvider extends FabricRecipeProvider {
    //? if <=1.20.4 {
    /*public SquishRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
    *///?} else {
    public SquishRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
    //?}
        CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(Items.SUGAR), RecipeCategory.MISC, RegistryHelper.MELTED_SUGAR_BLOCK, 0.1f, 600)
                .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                .offerTo(exporter, SquishId.of("squish", "campfire_melt_sugar"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, RegistryHelper.LOLLIPOP, 1)
                .input(RegistryHelper.HARDENED_SUGAR_SHARD)
                .input(Items.STICK)
                .criterion(hasItem(RegistryHelper.HARDENED_SUGAR_SHARD), conditionsFromItem(RegistryHelper.HARDENED_SUGAR_SHARD))
                .offerTo(exporter, SquishId.of("squish", "blank_lollipop"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, RegistryHelper.HARDENED_SUGAR_SHARD, 4)
                .input(RegistryHelper.HARDENED_SUGAR_BLOCK)
                .criterion(hasItem(RegistryHelper.HARDENED_SUGAR_BLOCK), conditionsFromItem(RegistryHelper.HARDENED_SUGAR_BLOCK))
                .offerTo(exporter, SquishId.of("squish", "hardened_sugar_shard_from_block"));

        // Bitter sugar shard from smelting hardened sugar shard
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(RegistryHelper.HARDENED_SUGAR_SHARD), RecipeCategory.MISC, RegistryHelper.BITTER_SUGAR_SHARD, 0.1f, 200)
                .criterion(hasItem(RegistryHelper.HARDENED_SUGAR_SHARD), conditionsFromItem(RegistryHelper.HARDENED_SUGAR_SHARD))
                .offerTo(exporter, SquishId.of("squish", "bitter_sugar_shard_from_smelting"));

        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(RegistryHelper.HARDENED_SUGAR_SHARD), RecipeCategory.MISC, RegistryHelper.BITTER_SUGAR_SHARD, 0.1f, 100)
                .criterion(hasItem(RegistryHelper.HARDENED_SUGAR_SHARD), conditionsFromItem(RegistryHelper.HARDENED_SUGAR_SHARD))
                .offerTo(exporter, SquishId.of("squish", "bitter_sugar_shard_from_smoking"));

        // Bitter candy: bitter sugar shard + stick
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, RegistryHelper.BITTER_CANDY, 1)
                .input(RegistryHelper.BITTER_SUGAR_SHARD)
                .input(Items.STICK)
                .criterion(hasItem(RegistryHelper.BITTER_SUGAR_SHARD), conditionsFromItem(RegistryHelper.BITTER_SUGAR_SHARD))
                .offerTo(exporter, SquishId.of("squish", "bitter_candy"));

        // Explosive essence: gunpowder + squish essence
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, RegistryHelper.EXPLOSIVE_ESSENCE, 1)
                .input(Items.GUNPOWDER)
                .input(RegistryHelper.SQUISH_ESSENCE)
                .criterion(hasItem(RegistryHelper.SQUISH_ESSENCE), conditionsFromItem(RegistryHelper.SQUISH_ESSENCE))
                .offerTo(exporter, SquishId.of("squish", "explosive_essence"));

        // Poppy essence: poppy + squish essence
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, RegistryHelper.POPPY_ESSENCE, 1)
                .input(Items.POPPY)
                .input(RegistryHelper.SQUISH_ESSENCE)
                .criterion(hasItem(RegistryHelper.SQUISH_ESSENCE), conditionsFromItem(RegistryHelper.SQUISH_ESSENCE))
                .offerTo(exporter, SquishId.of("squish", "poppy_essence"));

        // Ender essence: ender pearl + squish essence
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, RegistryHelper.ENDER_ESSENCE, 1)
                .input(Items.ENDER_PEARL)
                .input(RegistryHelper.SQUISH_ESSENCE)
                .criterion(hasItem(RegistryHelper.SQUISH_ESSENCE), conditionsFromItem(RegistryHelper.SQUISH_ESSENCE))
                .offerTo(exporter, SquishId.of("squish", "ender_essence"));
    }
}
//? }
