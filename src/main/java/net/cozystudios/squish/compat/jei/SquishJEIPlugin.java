package net.cozystudios.squish.compat.jei;

//? if <=1.20.4 {
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.vanilla.IJeiBrewingRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
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
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new EssenceInfusionCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IJeiHelpers helpers = registration.getJeiHelpers();

        IVanillaRecipeFactory factory = helpers.getVanillaRecipeFactory();
        ItemStack water = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER);
        ItemStack shard = new ItemStack(RegistryHelper.HARDENED_SUGAR_SHARD);
        ItemStack essence = new ItemStack(RegistryHelper.SQUISH_ESSENCE);
        IJeiBrewingRecipe brew = factory.createBrewingRecipe(
                List.of(shard),
                List.of(water),
                essence
        );
        registration.addRecipes(RecipeTypes.BREWING, List.of(brew));

        ItemStack lollipop = new ItemStack(RegistryHelper.LOLLIPOP);
        List<EssenceInfusionRecipe> infusions = List.of(
                new EssenceInfusionRecipe(
                        new ItemStack(RegistryHelper.SQUISH_ESSENCE),
                        lollipop,
                        new ItemStack(RegistryHelper.SQUISH_CANDY)),
                new EssenceInfusionRecipe(
                        new ItemStack(RegistryHelper.EXPLOSIVE_ESSENCE),
                        lollipop,
                        new ItemStack(RegistryHelper.EXPLOSIVE_CANDY)),
                new EssenceInfusionRecipe(
                        new ItemStack(RegistryHelper.POPPY_ESSENCE),
                        lollipop,
                        new ItemStack(RegistryHelper.POPPY_CANDY)),
                new EssenceInfusionRecipe(
                        new ItemStack(RegistryHelper.ENDER_ESSENCE),
                        lollipop,
                        new ItemStack(RegistryHelper.ENDER_CANDY)),
                new EssenceInfusionRecipe(
                        new ItemStack(RegistryHelper.SKELLY_ESSENCE),
                        lollipop,
                        new ItemStack(RegistryHelper.SKELLY_CANDY))
        );
        registration.addRecipes(EssenceInfusionCategory.TYPE, infusions);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(RegistryHelper.LOLLIPOP), EssenceInfusionCategory.TYPE);
    }
}
//?} else {
/*public class SquishJEIPlugin {}
*///?}
