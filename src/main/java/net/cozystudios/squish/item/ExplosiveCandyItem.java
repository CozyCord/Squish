package net.cozystudios.squish.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class ExplosiveCandyItem extends SquishBaseItem {
    public ExplosiveCandyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            // Play creeper hiss sound
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_CREEPER_PRIMED, SoundCategory.PLAYERS,
                    1.0F, 1.0F);

            // Schedule the explosion effect after 2-3 seconds (40-60 ticks)
            // We'll use a simple approach: spawn a marker and check in tick
            // For simplicity, we'll use the server to schedule this
            final double x = player.getX();
            final double y = player.getY();
            final double z = player.getZ();

            // Create a thread to delay the explosion (2.5 seconds = 2500ms)
            new Thread(() -> {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    return;
                }

                // Schedule on main thread
                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.getServer().execute(() -> {
                        if (player.isAlive() && !player.isRemoved()) {
                            // Play explosion sound
                            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                                    SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS,
                                    1.0F, 1.0F);

                            // Spawn explosion particles
                            serverWorld.spawnParticles(ParticleTypes.EXPLOSION_EMITTER,
                                    player.getX(), player.getY() + 1, player.getZ(),
                                    1, 0, 0, 0, 0);

                            // Set player to half a heart (1 health = half heart)
                            // but don't kill them
                            if (player.getHealth() > 1.0F) {
                                player.setHealth(1.0F);
                            }

                            // Knock the player back a bit for effect
                            player.setVelocity(player.getVelocity().add(0, 0.5, 0));
                            player.velocityModified = true;
                        }
                    });
                }
            }).start();
        }
        return super.finishUsing(stack, world, user);
    }
}
