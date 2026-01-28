package net.cozystudios.squish.mixin.sound;

import net.cozystudios.squish.registry.sound.SquishSounds;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CatEntity.class)
public abstract class CatEntitySoundMixin {

    @Inject(method = "getAmbientSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        CatEntity cat = (CatEntity) (Object) this;
        if (cat.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_CAT_AMBIENT);
        }
    }

    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        CatEntity cat = (CatEntity) (Object) this;
        if (cat.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_CAT_HURT);
        }
    }

    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        CatEntity cat = (CatEntity) (Object) this;
        if (cat.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_CAT_DEATH);
        }
    }
}
