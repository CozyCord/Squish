package net.cozystudios.squish.mixin;

import net.cozystudios.squish.item.SquishItems;
import net.cozystudios.squish.util.Squishable;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntitySquishMixin {

    @Inject(
            method = "interactMob",
            at = @At("HEAD"),
            cancellable = true
    )
    private void squish$onInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack held = player.getStackInHand(hand);
        if (!held.isOf(SquishItems.SQUISH_CANDY)) return;

        AnimalEntity animal = (AnimalEntity)(Object)this;
        World world = animal.getWorld();
        if (world.isClient) return;

        boolean alreadySquished = animal instanceof Squishable squishable && squishable.squish$isSquished();
        if (alreadySquished) {
            player.sendMessage(Text.literal("That mob is already squished!"), true);
            cir.setReturnValue(ActionResult.FAIL);
            return;
        }

        if (animal instanceof Squishable squishable) {
            squishable.squish$setSquished(true);
        }
        animal.setBreedingAge(-24000);

        world.playSound(null, animal.getBlockPos(),
                SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                SoundCategory.PLAYERS,
                0.6f, 1.8f
        );

        if (!player.getAbilities().creativeMode) {
            held.decrement(1);
        }

        cir.setReturnValue(ActionResult.SUCCESS);
    }
}