package net.cozystudios.squish.registry.entity;

//? if fabric {
import net.cozystudios.squish.loader.fabric.RegistryHelper;
//? }

//? if forge {
/*import net.cozystudios.squish.loader.forge.RegistryHelper;
*///? }

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BabySkeletonEntity extends TameableEntity {

    public BabySkeletonEntity(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createBabySkeletonAttributes() {
        return TameableEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SitGoal(this));
        //? if <=1.20.4 {
        /*this.goalSelector.add(2, new FollowOwnerGoal(this, 1.0, 3.0f, 1.0f, false));
        *///?} else {
        this.goalSelector.add(2, new FollowOwnerGoal(this, 1.0, 3.0f, 1.0f));
        //?}
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack held = player.getStackInHand(hand);

        if (player.isSneaking() && held.isOf(RegistryHelper.BITTER_CANDY)) {
            return ActionResult.PASS;
        }

        if (this.isTamed() && this.isOwner(player)) {
            if (!this.getWorld().isClient) {
                this.setSitting(!this.isSitting());
                this.playSound(SoundEvents.ENTITY_SKELETON_AMBIENT, 0.5f, 1.7f);
            }
            return ActionResult.success(this.getWorld().isClient);
        }

        if (!this.isTamed() && held.isOf(RegistryHelper.SKELLY_CANDY)) {
            if (this.getWorld().isClient) return ActionResult.SUCCESS;

            if (!player.getAbilities().creativeMode) held.decrement(1);

            this.setOwner(player);
            //? if <=1.20.4 {
            /*this.setTamed(true);
            *///?} else {
            this.setTamed(true, true);
            //?}
            this.setSitting(false);

            this.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 0.6f, 1.7f);
            this.getWorld().sendEntityStatus(this, (byte) 7);
            return ActionResult.SUCCESS;
        }

        return super.interactMob(player, hand);
    }

    //? if <=1.20.4 {
    /*@Override
    public boolean canBeLeashedBy(PlayerEntity player) {
        return this.isTamed() && this.isOwner(player);
    }
    *///?}

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity otherParent) {
        return null;
    }

    @Override
    public boolean isBaby() {
        return true;
    }

    //? if >1.20.4 {
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }
    //?}

    //? if <=1.20.4 {
    /*@Override
    public EntityView method_48926() {
        return this.getWorld();
    }
    *///?}
}
