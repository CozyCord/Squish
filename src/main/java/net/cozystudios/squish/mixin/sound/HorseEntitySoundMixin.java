package net.cozystudios.squish.mixin.sound;

import net.cozystudios.squish.registry.sound.SquishSounds;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HorseEntity.class)
public abstract class HorseEntitySoundMixin {

    @Inject(method = "getAmbientSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        HorseEntity horse = (HorseEntity) (Object) this;
        if (horse.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_HORSE_AMBIENT);
        }
    }

    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        HorseEntity horse = (HorseEntity) (Object) this;
        if (horse.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_HORSE_HURT);
        }
    }

    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        HorseEntity horse = (HorseEntity) (Object) this;
        if (horse.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_HORSE_DEATH);
        }
    }

    @Inject(method = "getAngrySound", at = @At("HEAD"), cancellable = true)
    private void squish$babyAngrySound(CallbackInfoReturnable<SoundEvent> cir) {
        HorseEntity horse = (HorseEntity) (Object) this;
        if (horse.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_HORSE_ANGRY);
        }
    }

    @Inject(method = "getEatSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyEatSound(CallbackInfoReturnable<SoundEvent> cir) {
        HorseEntity horse = (HorseEntity) (Object) this;
        if (horse.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_HORSE_EAT);
        }
    }
}
