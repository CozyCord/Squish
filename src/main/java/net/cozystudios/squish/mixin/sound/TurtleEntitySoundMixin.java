package net.cozystudios.squish.mixin.sound;

import net.cozystudios.squish.registry.sound.SquishSounds;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TurtleEntity.class)
public abstract class TurtleEntitySoundMixin {

    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        TurtleEntity turtle = (TurtleEntity) (Object) this;
        if (turtle.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_TURTLE_HURT);
        }
    }

    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void squish$babyDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        TurtleEntity turtle = (TurtleEntity) (Object) this;
        if (turtle.isBaby()) {
            cir.setReturnValue(SquishSounds.BABY_TURTLE_DEATH);
        }
    }
}
