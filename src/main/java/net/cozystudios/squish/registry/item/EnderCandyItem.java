package net.cozystudios.squish.registry.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EnderCandyItem extends SquishBaseItem {
    public EnderCandyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            // Give night vision for 60 seconds (1200 ticks)
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1200, 0));

            // Teleport like chorus fruit
            teleportRandomly(player, world);
        }
        return super.finishUsing(stack, world, user);
    }

    private void teleportRandomly(PlayerEntity player, World world) {
        double startX = player.getX();
        double startY = player.getY();
        double startZ = player.getZ();

        for (int i = 0; i < 16; ++i) {
            double targetX = player.getX() + (world.random.nextDouble() - 0.5) * 16.0;
            double targetY = MathHelper.clamp(
                    player.getY() + (double)(world.random.nextInt(16) - 8),
                    world.getBottomY(),
                    world.getTopY() - 1
            );
            double targetZ = player.getZ() + (world.random.nextDouble() - 0.5) * 16.0;

            if (player.hasVehicle()) {
                player.stopRiding();
            }

            Vec3d vec3d = player.getPos();
            if (player.teleport(targetX, targetY, targetZ, true)) {
                world.emitGameEvent(net.minecraft.world.event.GameEvent.TELEPORT, vec3d, net.minecraft.world.event.GameEvent.Emitter.of(player));
                world.playSound(null, startX, startY, startZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                break;
            }
        }
    }
}
