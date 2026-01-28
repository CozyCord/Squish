package net.cozystudios.squish.mixin.sound;

import net.cozystudios.squish.registry.sound.SquishSounds;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChickenEntity.class)
public abstract class ChickenEntitySoundMixin {

    @Inject(method = "getAmbientSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        ChickenEntity chicken = (ChickenEntity) (Object) this;
        if (chicken.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_CHICKEN_AMBIENT);
        }
    }

    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        ChickenEntity chicken = (ChickenEntity) (Object) this;
        if (chicken.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_CHICKEN_HURT);
        }
    }

    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        ChickenEntity chicken = (ChickenEntity) (Object) this;
        if (chicken.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_CHICKEN_DEATH);
        }
    }
}
