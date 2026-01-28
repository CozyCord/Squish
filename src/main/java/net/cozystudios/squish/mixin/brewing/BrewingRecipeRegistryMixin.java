package net.cozystudios.squish.mixin.brewing;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingRecipeRegistry.class)
public abstract class BrewingRecipeRegistryMixin {

    //? if <=1.20.4 {
    /*@Inject(method = "isValidIngredient", at = @At("HEAD"), cancellable = true)
    private static void squish$allowShardAsIngredient(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(RegistryHelper.HARDENED_SUGAR_SHARD)) {
            cir.setReturnValue(true);
        }
    }
    *///?} else {
    @Inject(method = "isValidIngredient", at = @At("HEAD"), cancellable = true)
    private void squish$allowShardAsIngredient(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(RegistryHelper.HARDENED_SUGAR_SHARD)) {
            cir.setReturnValue(true);
        }
    }
    //?}
}
