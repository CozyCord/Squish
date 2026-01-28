package net.cozystudios.squish.mixin;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.cozystudios.squish.util.Squishable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DolphinEntity.class)
public abstract class DolphinEntitySquishMixin extends WaterCreatureEntity implements Squishable {

    @Unique
    private static final TrackedData<Boolean> SQUISH_PERMA_BABY = DataTracker.registerData(DolphinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected DolphinEntitySquishMixin(EntityType<? extends WaterCreatureEntity> type, World world) {
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

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void squish$onInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack held = player.getStackInHand(hand);

        if (held.isOf(RegistryHelper.BITTER_CANDY) && player.isSneaking()) {
            if (this.squish$isSquished()) {
                World world = this.getWorld();
                if (!world.isClient) {
                    this.squish$setSquished(false);

                    ServerWorld server = (ServerWorld) world;
                    server.playSound(null, this.getBlockPos(),
                            SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.NEUTRAL,
                            1.0F, 0.8F);
                    server.spawnParticles(ParticleTypes.SMOKE,
                            this.getX(), this.getBodyY(0.5), this.getZ(),
                            20, 0.3, 0.3, 0.3, 0.02);

                    if (!player.getAbilities().creativeMode) {
                        held.decrement(1);
                    }
                }
                cir.setReturnValue(ActionResult.SUCCESS);
            }
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
