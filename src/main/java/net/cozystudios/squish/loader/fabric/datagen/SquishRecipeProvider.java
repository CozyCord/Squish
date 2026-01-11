//? if fabric {
package net.cozystudios.squish.loader.fabric.datagen;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class SquishRecipeProvider extends FabricRecipeProvider {
    public SquishRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(Items.SUGAR), RecipeCategory.MISC, RegistryHelper.MELTED_SUGAR_BLOCK, 0.1f, 600)
                .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                .offerTo(exporter, new Identifier("squish", "campfire_melt_sugar"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, RegistryHelper.LOLLIPOP, 1)
                .input(RegistryHelper.HARDENED_SUGAR_SHARD)
                .input(Items.STICK)
                .criterion(hasItem(RegistryHelper.HARDENED_SUGAR_SHARD), conditionsFromItem(RegistryHelper.HARDENED_SUGAR_SHARD))
                .offerTo(exporter, new Identifier("squish", "blank_lollipop"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, RegistryHelper.HARDENED_SUGAR_SHARD, 4)
                .input(RegistryHelper.HARDENED_SUGAR_BLOCK)
                .criterion(hasItem(RegistryHelper.HARDENED_SUGAR_BLOCK), conditionsFromItem(RegistryHelper.HARDENED_SUGAR_BLOCK))
                .offerTo(exporter, new Identifier("squish", "hardened_sugar_shard_from_block"));
    }
}
//? }
