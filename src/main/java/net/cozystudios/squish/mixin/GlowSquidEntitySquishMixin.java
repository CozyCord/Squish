package net.cozystudios.squish.mixin;

import net.cozystudios.squish.util.Squishable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.GlowSquidEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GlowSquidEntity.class)
public abstract class GlowSquidEntitySquishMixin extends SquidEntity implements Squishable {

    @Unique
    private static final TrackedData<Boolean> SQUISH_PERMA_BABY = DataTracker.registerData(GlowSquidEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected GlowSquidEntitySquishMixin(EntityType<? extends SquidEntity> type, World world) {
        super(type, world);
    }

    //? if <=1.20.4 {
    /*@Inject(method = "initDataTracker", at = @At("TAIL"))
    private void squish$initDataTracker(CallbackInfo ci) {
        this.dataTracker.startTracking(SQUISH_PERMA_BABY, false);
    }
    *///?} else {
    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void squish$initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(SQUISH_PERMA_BABY, false);
    }
    //?}

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void squish$writeNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("SquishPermaBaby", this.dataTracker.get(SQUISH_PERMA_BABY));
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    private void squish$readNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("SquishPermaBaby")) {
            this.dataTracker.set(SQUISH_PERMA_BABY, nbt.getBoolean("SquishPermaBaby"));
        }
    }

    @Override
    public boolean squish$isSquished() {
        return this.dataTracker.get(SQUISH_PERMA_BABY);
    }

    @Override
    public void squish$setSquished(boolean squished) {
        this.dataTracker.set(SQUISH_PERMA_BABY, squished);
    }
}
