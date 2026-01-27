package net.cozystudios.squish.mixin.client;

import net.cozystudios.squish.Squish;
import net.cozystudios.squish.registry.item.SquishBaseItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackTooltipMixin {

    //? if <=1.20.4 {
    /*@Inject(method = "getTooltip", at = @At("RETURN"))
    private void squish$styleTooltipName(@Nullable PlayerEntity player,
            net.minecraft.client.item.TooltipContext context, CallbackInfoReturnable<List<Text>> cir) {
        applySquishTooltipStyle(cir);
    }
    *///?} else {
    @Inject(method = "getTooltip", at = @At("RETURN"))
    private void squish$styleTooltipName(Item.TooltipContext context, @Nullable PlayerEntity player,
            net.minecraft.item.tooltip.TooltipType type, CallbackInfoReturnable<List<Text>> cir) {
        applySquishTooltipStyle(cir);
    }
    //?}

    private void applySquishTooltipStyle(CallbackInfoReturnable<List<Text>> cir) {
        ItemStack self = (ItemStack) (Object) this;
        Identifier itemId = Registries.ITEM.getId(self.getItem());

        if (!itemId.getNamespace().equals(Squish.MOD_ID)) {
            return;
        }

        List<Text> tooltip = cir.getReturnValue();
        if (tooltip.isEmpty()) {
            return;
        }

        Text originalName = tooltip.get(0);
        Text styledName = SquishBaseItem.applySquishStyle(originalName);
        tooltip.set(0, styledName);
    }
}
