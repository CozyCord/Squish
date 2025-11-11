package net.cozystudios.squish.entity;

import net.cozystudios.squish.item.SquishItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class SquishEssenceEntity extends ThrownItemEntity {

    public SquishEssenceEntity(EntityType<? extends SquishEssenceEntity> type, World world) {
        super(type, world);
    }

    public SquishEssenceEntity(World world, LivingEntity owner) {
        super(SquishItems.SQUISH_ESSENCE_ENTITY, owner, world);
    }

    public SquishEssenceEntity(World world, double x, double y, double z) {
        super(SquishItems.SQUISH_ESSENCE_ENTITY, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return SquishItems.SQUISH_ESSENCE;
    }

    @Override
    protected float getGravity() {
        return 0.05F;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (this.getWorld().isClient) return;

        World world = this.getWorld();
        Vec3d pos = this.getPos();

        world.playSound(null, getX(), getY(), getZ(),
                SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.NEUTRAL,
                0.8F, 1.2F);

        if (world instanceof ServerWorld serverWorld) {
            DustParticleEffect magentaDust = new DustParticleEffect(new Vec3d(1.0, 0.0, 1.0).toVector3f(), 1.0F);
            serverWorld.spawnParticles(magentaDust,
                    pos.x, pos.y, pos.z,
                    40, // count
                    0.4, 0.4, 0.4, 0.05);
        }

        Box box = new Box(pos.add(-1.5, -1.5, -1.5), pos.add(1.5, 1.5, 1.5));
        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class, box,
                e -> e.getStack().isOf(SquishItems.LOLLIPOP));

        for (ItemEntity it : items) {
            ItemStack s = it.getStack();
            int count = s.getCount();
            ItemStack converted = new ItemStack(SquishItems.SQUISH_CANDY, count);
            it.setStack(converted);
        }

        this.discard();
    }
}