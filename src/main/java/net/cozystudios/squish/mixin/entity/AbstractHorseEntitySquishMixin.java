package net.cozystudios.squish.mixin.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.cozystudios.squish.util.Squishable;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@Mixin(AbstractHorseEntity.class)
public abstract class AbstractHorseEntitySquishMixin {

    @Inject(
            method = "interactMob",
            at = @At("HEAD"),
            cancellable = true
    )
    private void squish$onInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack held = player.getStackInHand(hand);

        if (held.isOf(RegistryHelper.BITTER_CANDY) && player.isSneaking()) {
            AbstractHorseEntity horse = (AbstractHorseEntity) (Object) this;
            World world = horse.getWorld();

            if (horse instanceof Squishable squishable && squishable.squish$isSquished()) {
                if (!world.isClient) {
                    squishable.squish$setSquished(false);
                    horse.setBreedingAge(0);

                    ServerWorld server = (ServerWorld) world;
                    server.playSound(null, horse.getBlockPos(),
                            SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.NEUTRAL,
                            1.0F, 0.8F);
                    server.spawnParticles(ParticleTypes.SMOKE,
                            horse.getX(), horse.getBodyY(0.5), horse.getZ(),
                            20, 0.3, 0.3, 0.3, 0.02);

                    if (!player.getAbilities().creativeMode) {
                        held.decrement(1);
                    }
                }
                cir.setReturnValue(ActionResult.SUCCESS);
                return;
            }
            cir.setReturnValue(ActionResult.PASS);
        }
    }
}
