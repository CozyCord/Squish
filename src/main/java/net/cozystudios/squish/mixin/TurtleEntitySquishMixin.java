package net.cozystudios.squish.mixin;

import net.cozystudios.squish.util.Squishable;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TurtleEntity.class)
public abstract class TurtleEntitySquishMixin {

    @Redirect(
            method = "onGrowUp()V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/TurtleEntity;dropItem(Lnet/minecraft/item/ItemConvertible;I)Lnet/minecraft/entity/ItemEntity;")
    )
    private ItemEntity squish$preventScuteDropOnUnsquish(TurtleEntity turtle, ItemConvertible item, int count) {
        if (turtle instanceof Squishable squishable && squishable.squish$isUnsquishing()) {
            return null;
        }
        return turtle.dropItem(item, count);
    }
}
