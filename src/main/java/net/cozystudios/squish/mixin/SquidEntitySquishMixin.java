package net.cozystudios.squish.mixin;

import net.cozystudios.squish.util.Squishable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SquidEntity.class)
public abstract class SquidEntitySquishMixin extends WaterCreatureEntity implements Squishable {

    @Unique
    private static final TrackedData<Boolean> SQUISH_PERMA_BABY = DataTracker.registerData(SquidEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected SquidEntitySquishMixin(EntityType<? extends WaterCreatureEntity> type, World world) {
        super(type, world);
    }

    //? if <=1.20.4 {
    /*@Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SQUISH_PERMA_BABY, false);
    }
    *///?} else {
    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(SQUISH_PERMA_BABY, false);
    }
    //?}

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("SquishPermaBaby", this.dataTracker.get(SQUISH_PERMA_BABY));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
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
