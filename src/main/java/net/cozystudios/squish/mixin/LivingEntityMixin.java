package net.cozystudios.squish.mixin;

import net.cozystudios.squish.effect.SquishEffects;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "jump", at = @At("TAIL"))
    private void squish$boostJump(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.hasStatusEffect(SquishEffects.SUGAR_RUSH)) {
            int amplifier = entity.getStatusEffect(SquishEffects.SUGAR_RUSH).getAmplifier();
            double boost = 0.4D + (0.1D * amplifier);
            entity.addVelocity(0, boost, 0);
            entity.velocityModified = true;
        }
    }
}