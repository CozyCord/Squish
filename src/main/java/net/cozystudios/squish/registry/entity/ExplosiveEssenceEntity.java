package net.cozystudios.squish.registry.entity;

//? if fabric {
import net.cozystudios.squish.loader.fabric.RegistryHelper;
//? }

//? if forge {
/*import net.cozystudios.squish.loader.forge.RegistryHelper;
*///? }

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
import org.joml.Vector3f;

import java.util.List;

public class ExplosiveEssenceEntity extends ThrownItemEntity {

    public ExplosiveEssenceEntity(EntityType<? extends ExplosiveEssenceEntity> type, World world) {
        super(type, world);
    }

    public ExplosiveEssenceEntity(World world, LivingEntity owner) {
        super(RegistryHelper.EXPLOSIVE_ESSENCE_ENTITY, owner, world);
    }

    public ExplosiveEssenceEntity(World world, double x, double y, double z) {
        super(RegistryHelper.EXPLOSIVE_ESSENCE_ENTITY, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return RegistryHelper.EXPLOSIVE_ESSENCE;
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

        // Play explosion-like sound
        world.playSound(null, getX(), getY(), getZ(),
                SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.NEUTRAL,
                0.8F, 1.2F);

        // Spawn orange/red particles
        if (world instanceof ServerWorld serverWorld) {
            // Orange color for explosive
            DustParticleEffect orangeDust = new DustParticleEffect(new Vector3f(1.0f, 0.5f, 0.0f), 1.0F);
            serverWorld.spawnParticles(orangeDust,
                    pos.x, pos.y, pos.z,
                    40,
                    0.4, 0.4, 0.4, 0.05);
        }

        // Convert lollipops to explosive candy
        Box box = new Box(pos.add(-1.5, -1.5, -1.5), pos.add(1.5, 1.5, 1.5));
        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class, box, e -> {
            ItemStack st = e.getStack();
            return st.isOf(RegistryHelper.LOLLIPOP);
        });

        for (ItemEntity it : items) {
            ItemStack s = it.getStack();
            int count = s.getCount();
            ItemStack converted = new ItemStack(RegistryHelper.EXPLOSIVE_CANDY, count);
            it.setStack(converted);
        }

        this.discard();
    }
}
