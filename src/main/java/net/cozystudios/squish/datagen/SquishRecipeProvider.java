package net.cozystudios.squish.datagen;

import net.cozystudios.squish.item.SquishItems;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class SquishRecipeProvider extends FabricRecipeProvider {
    public SquishRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, SquishItems.BLANK_LOLLIPOP, 4)
                .pattern(" S ")
                .pattern(" S ")
                .pattern(" T ")
                .input('S', Items.SUGAR)
                .input('T', Items.STICK)
                .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                .offerTo(exporter, new Identifier("squish", "blank_lollipop"));
    }
}