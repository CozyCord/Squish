package net.cozystudios.squish.mixin.sound;

import net.cozystudios.squish.registry.sound.SquishSounds;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WolfEntity.class)
public abstract class WolfEntitySoundMixin {

    @Inject(method = "getAmbientSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        WolfEntity wolf = (WolfEntity) (Object) this;
        if (wolf.isBaby()) {
            if (wolf.hasAngerTime()) {
                cir.setReturnValue(SquishSounds.BABY_WOLF_ANGRY);
            } else if (wolf.isTamed()) {
                cir.setReturnValue(SquishSounds.BABY_WOLF_PANT);
            } else {
                cir.setReturnValue(SquishSounds.BABY_WOLF_AMBIENT);
            }
        }
    }

    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        WolfEntity wolf = (WolfEntity) (Object) this;
        if (wolf.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_WOLF_HURT);
        }
    }

    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        WolfEntity wolf = (WolfEntity) (Object) this;
        if (wolf.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_WOLF_DEATH);
        }
    }
}
