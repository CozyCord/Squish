package net.cozystudios.squish.mixin;

import net.cozystudios.squish.fabric.RegistryHelper;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class) public abstract class LivingEntityMixin {

    @Inject(method = "jump", at = @At("TAIL"))

    private void squish$boostJump(CallbackInfo ci) { LivingEntity entity = (LivingEntity) (Object)
            this; if (entity.hasStatusEffect(RegistryHelper.SUGAR_RUSH)) {
                int amplifier = entity.getStatusEffect(RegistryHelper.SUGAR_RUSH).getAmplifier();
                double boost = 0.2D + (0.1D * amplifier);
                entity.addVelocity(
                        0, boost,
                        0); entity.velocityModified = true; }
    }
}
