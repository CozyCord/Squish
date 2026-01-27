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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.List;

public class EnderEssenceEntity extends ThrownItemEntity {

    public EnderEssenceEntity(EntityType<? extends EnderEssenceEntity> type, World world) {
        super(type, world);
    }

    public EnderEssenceEntity(World world, LivingEntity owner) {
        super(RegistryHelper.ENDER_ESSENCE_ENTITY, owner, world);
    }

    public EnderEssenceEntity(World world, double x, double y, double z) {
        super(RegistryHelper.ENDER_ESSENCE_ENTITY, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return RegistryHelper.ENDER_ESSENCE;
    }

    //? if <=1.20.4 {
    /*@Override
    protected float getGravity() {
        return 0.05F;
    }
    *///?}

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (this.getWorld().isClient) return;

        World world = this.getWorld();
        Vec3d pos = this.getPos();

        // Play ender teleport sound
        world.playSound(null, getX(), getY(), getZ(),
                SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.NEUTRAL,
                0.8F, 1.2F);

        // Spawn purple/ender particles
        if (world instanceof ServerWorld serverWorld) {
            // Ender purple color
            DustParticleEffect enderDust = new DustParticleEffect(new Vector3f(0.5f, 0.0f, 0.5f), 1.0F);
            serverWorld.spawnParticles(enderDust,
                    pos.x, pos.y, pos.z,
                    40,
                    0.4, 0.4, 0.4, 0.05);

            // Also spawn portal particles for effect
            serverWorld.spawnParticles(ParticleTypes.PORTAL,
                    pos.x, pos.y, pos.z,
                    20,
                    0.3, 0.3, 0.3, 0.5);
        }

        // Convert lollipops to ender candy
        Box box = new Box(pos.add(-1.5, -1.5, -1.5), pos.add(1.5, 1.5, 1.5));
        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class, box, e -> {
            ItemStack st = e.getStack();
            return st.isOf(RegistryHelper.LOLLIPOP);
        });

        for (ItemEntity it : items) {
            ItemStack s = it.getStack();
            int count = s.getCount();
            ItemStack converted = new ItemStack(RegistryHelper.ENDER_CANDY, count);
            it.setStack(converted);
        }

        this.discard();
    }
}
