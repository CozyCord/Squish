package net.cozystudios.squish.mixin.sound;

import net.cozystudios.squish.registry.sound.SquishSounds;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PigEntity.class)
public abstract class PigEntitySoundMixin {

    @Inject(method = "getAmbientSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        PigEntity pig = (PigEntity) (Object) this;
        if (pig.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_PIG_AMBIENT);
        }
    }

    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        PigEntity pig = (PigEntity) (Object) this;
        if (pig.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_PIG_HURT);
        }
    }

    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        PigEntity pig = (PigEntity) (Object) this;
        if (pig.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_PIG_DEATH);
        }
    }
}
