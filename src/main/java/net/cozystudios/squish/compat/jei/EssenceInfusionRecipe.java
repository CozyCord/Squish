package net.cozystudios.squish.compat.jei;

//? if <=1.20.4 {
import net.minecraft.item.ItemStack;

public record EssenceInfusionRecipe(ItemStack essence, ItemStack lollipop, ItemStack result) {}
//?} else {
/*public class EssenceInfusionRecipe {}
*///?}
