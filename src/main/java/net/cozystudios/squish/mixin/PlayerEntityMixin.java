package net.cozystudios.squish.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @ModifyReturnValue(method = "getBlockBreakingSpeed", at = @At("RETURN"))
    private float squish$boostMiningSpeed(float original) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.hasStatusEffect(RegistryHelper.SUGAR_RUSH)) {
            return original * 7.0F;
        }

        return original;
    }
}
