package net.cozystudios.squish.compat.jei;

//? if <=1.20.4 {
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IJeiBrewingRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeRegistration;
import net.cozystudios.squish.Squish;
import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;

import java.util.List;

@JeiPlugin
public class SquishJEIPlugin implements IModPlugin {

    private static final Identifier ID = new Identifier(Squish.MOD_ID, "jei");

    @Override
    public Identifier getPluginUid() {
        return ID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IVanillaRecipeFactory factory = registration.getJeiHelpers().getVanillaRecipeFactory();

        ItemStack water = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER);
        ItemStack shard = new ItemStack(RegistryHelper.HARDENED_SUGAR_SHARD);
        ItemStack essence = new ItemStack(RegistryHelper.SQUISH_ESSENCE);

        IJeiBrewingRecipe recipe = factory.createBrewingRecipe(
                List.of(shard),
                List.of(water),
                essence
        );

        registration.addRecipes(RecipeTypes.BREWING, List.of(recipe));
    }
}
//?} else {
/*public class SquishJEIPlugin {}
*///?}
