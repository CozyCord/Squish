package net.cozystudios.squish.mixin;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.cozystudios.squish.util.Squishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.GlowSquidEntity;
import net.minecraft.entity.passive.SquidEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "jump", at = @At("TAIL"))
    private void squish$boostJump(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.hasStatusEffect(RegistryHelper.SUGAR_RUSH)) {
            int amplifier = entity.getStatusEffect(RegistryHelper.SUGAR_RUSH).getAmplifier();
            double boost = 0.2D + (0.1D * amplifier);
            entity.addVelocity(0, boost, 0);
            entity.velocityModified = true;
        }
    }

    @Inject(method = "isBaby", at = @At("RETURN"), cancellable = true)
    private void squish$modifyIsBabyForWaterCreatures(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        // Check if this is a water creature that we've made squishable
        if (entity instanceof DolphinEntity || entity instanceof SquidEntity || entity instanceof GlowSquidEntity) {
            if (entity instanceof Squishable squishable && squishable.squish$isSquished()) {
                cir.setReturnValue(true);
            }
        }
    }
}
