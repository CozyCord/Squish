package net.cozystudios.squish.registry.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.UUID;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExplosiveCandyItem extends SquishBaseItem {

    private static final Map<UUID, Long> PENDING_EXPLOSIONS = new ConcurrentHashMap<>();
    private static final int EXPLOSION_DELAY_TICKS = 50;

    public ExplosiveCandyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_CREEPER_PRIMED, SoundCategory.PLAYERS,
                    1.0F, 1.0F);

            long explosionTime = world.getTime() + EXPLOSION_DELAY_TICKS;
            PENDING_EXPLOSIONS.put(player.getUuid(), explosionTime);
        }
        return super.finishUsing(stack, world, user);
    }

    public static void tickExplosions(ServerWorld world) {
        long currentTime = world.getTime();

        PENDING_EXPLOSIONS.entrySet().removeIf(entry -> {
            if (currentTime >= entry.getValue()) {
                PlayerEntity player = world.getPlayerByUuid(entry.getKey());
                if (player != null && player.isAlive() && !player.isRemoved()) {
                    triggerExplosion(world, player);
                }
                return true;
            }
            return false;
        });
    }

    private static void triggerExplosion(ServerWorld world, PlayerEntity player) {
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS,
                1.0F, 1.0F);

        world.spawnParticles(ParticleTypes.EXPLOSION_EMITTER,
                player.getX(), player.getY() + 1, player.getZ(),
                1, 0, 0, 0, 0);

        if (player.getHealth() > 1.0F) {
            player.setHealth(1.0F);
        }

        player.setVelocity(player.getVelocity().add(0, 0.5, 0));
        player.velocityModified = true;
    }
}
