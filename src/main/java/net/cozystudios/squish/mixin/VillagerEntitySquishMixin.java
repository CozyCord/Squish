package net.cozystudios.squish.mixin;

import net.cozystudios.squish.util.Squishable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntitySquishMixin extends LivingEntity implements Squishable {

    @Unique private boolean squish$permaBaby = false;

    protected VillagerEntitySquishMixin(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
    }

    // Save flag
    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void squish$writeNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("SquishPermaBaby", squish$permaBaby);
    }

    // Load flag
    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void squish$readNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("SquishPermaBaby")) {
            squish$permaBaby = nbt.getBoolean("SquishPermaBaby");
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void squish$keepBaby(CallbackInfo ci) {
        if (squish$permaBaby && !this.getWorld().isClient) {
            VillagerEntity villager = (VillagerEntity)(Object)this;
            if (villager.getBreedingAge() >= 0) {
                villager.setBreedingAge(-24000);
            }
        }
    }

    @Override
    public boolean squish$isSquished() {
        return squish$permaBaby;
    }

    @Override
    public void squish$setSquished(boolean squished) {
        this.squish$permaBaby = squished;
    }
}