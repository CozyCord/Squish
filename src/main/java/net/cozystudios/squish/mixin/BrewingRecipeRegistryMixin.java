package net.cozystudios.squish.mixin;

import net.cozystudios.squish.item.SquishItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingRecipeRegistry.class)
public abstract class BrewingRecipeRegistryMixin {

    @Inject(method = "isValidIngredient", at = @At("HEAD"), cancellable = true)
    private static void squish$allowAmethystShard(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(SquishItems.HARDENED_SUGAR_SHARD)) {
            cir.setReturnValue(true);
        }
    }
}