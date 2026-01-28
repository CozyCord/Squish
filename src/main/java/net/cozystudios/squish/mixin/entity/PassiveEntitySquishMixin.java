package net.cozystudios.squish.mixin.entity;

import net.cozystudios.squish.util.Squishable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PassiveEntity.class)
public abstract class PassiveEntitySquishMixin extends LivingEntity implements Squishable {

    @Shadow
    protected int breedingAge;
    @Shadow public abstract void setBreedingAge(int age);

    @Unique private boolean squish$permaBaby = false;
    @Unique private boolean squish$unsquishing = false;

    protected PassiveEntitySquishMixin(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void squish$writeNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("SquishPermaBaby", squish$permaBaby);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void squish$readNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("SquishPermaBaby")) {
            squish$permaBaby = nbt.getBoolean("SquishPermaBaby");
        }
        if (squish$permaBaby) {
            this.breedingAge = -24000;
        }
    }

    @Inject(method = "setBreedingAge", at = @At("HEAD"), cancellable = true)
    private void squish$blockPositiveAge(int age, CallbackInfo ci) {
        if (squish$permaBaby && age > -24000) {
            this.breedingAge = -24000;
            ci.cancel();
        }
    }

    @Override public boolean squish$isSquished() { return squish$permaBaby; }
    @Override public void squish$setSquished(boolean squished) { this.squish$permaBaby = squished; }
    public boolean squish$isUnsquishing() { return squish$unsquishing; }
    public void squish$setUnsquishing(boolean unsquishing) { this.squish$unsquishing = unsquishing; }
}
