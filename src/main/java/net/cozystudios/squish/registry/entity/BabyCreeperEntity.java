package net.cozystudios.squish.registry.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BabyCreeperEntity extends TameableEntity {

    public BabyCreeperEntity(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createBabyCreeperAttributes() {
        return TameableEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SitGoal(this));
        this.goalSelector.add(2, new FollowOwnerGoal(this, 1.0, 3.0f, 1.0f, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack held = player.getStackInHand(hand);

        if (this.isTamed() && this.isOwner(player)) {
            if (!this.getWorld().isClient) {
                this.setSitting(!this.isSitting());
                this.playSound(SoundEvents.ENTITY_SLIME_SQUISH, 0.7f, 1.7f);
            }
            return ActionResult.success(this.getWorld().isClient);
        }

        if (!this.isTamed() && held.isOf(Items.TNT)) {
            if (this.getWorld().isClient) return ActionResult.SUCCESS;

            if (!player.getAbilities().creativeMode) held.decrement(1);

            this.setOwner(player);
            this.setTamed(true);
            this.setSitting(false);

            this.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 0.6f, 1.7f);
            this.getWorld().sendEntityStatus(this, (byte) 7);
            return ActionResult.SUCCESS;
        }

        return super.interactMob(player, hand);
    }

    @Override
    public boolean canBeLeashedBy(PlayerEntity player) {
        return this.isTamed() && this.isOwner(player);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity otherParent) {
        return null;
    }

    @Override
    public boolean isBaby() {
        return true;
    }

    @Override
    public EntityView method_48926() {
        return this.getWorld();
    }
}
