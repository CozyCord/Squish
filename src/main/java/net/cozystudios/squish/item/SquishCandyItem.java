package net.cozystudios.squish.item;

import net.cozystudios.squish.effect.SquishEffects;
import net.cozystudios.squish.sound.SquishSounds;
import net.cozystudios.squish.util.Squishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleType;
import virtuoel.pehkui.api.ScaleTypes;

import java.awt.Color;
import java.util.List;

public class SquishCandyItem extends SquishBaseItem {
    public SquishCandyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        ActionResult result = applySquish(user, entity, stack);
        if (result == ActionResult.SUCCESS || result == ActionResult.FAIL) {
            return ActionResult.FAIL;
        }
        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!user.isSneaking()) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(stack);
        }

        if (world.isClient) return TypedActionResult.pass(stack);

        Vec3d lookVec = user.getRotationVec(1.0F);
        Box searchBox = user.getBoundingBox().stretch(lookVec.multiply(3.5D)).expand(1.0D);
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, searchBox,
                e -> e instanceof AnimalEntity && e.isAlive());

        LivingEntity closest = null;
        double closestDist = 3.5D;

        for (LivingEntity e : entities) {
            double dist = e.squaredDistanceTo(user);
            if (dist < closestDist * closestDist) {
                closest = e;
            }
        }

        if (closest != null) {
            ActionResult result = applySquish(user, closest, stack);
            if (result.isAccepted()) {
                return TypedActionResult.success(stack);
            }
        }

        return TypedActionResult.pass(stack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);

        if (!world.isClient && user instanceof PlayerEntity player) {
            player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                    SquishEffects.SUGAR_RUSH,
                    20 * 60,
                    0,
                    false,
                    true,
                    true
            ));

            float hue = (System.currentTimeMillis() % 3000L) / 3000f;
            int rgb = Color.HSBtoRGB(hue, 0.9f, 1.0f) & 0xFFFFFF;
            float r = ((rgb >> 16) & 0xFF) / 255f;
            float g = ((rgb >> 8) & 0xFF) / 255f;
            float b = (rgb & 0xFF) / 255f;

            ServerWorld server = (ServerWorld) world;
            server.playSound(null, player.getBlockPos(),
                    SquishSounds.SUGAR_POP,
                    SoundCategory.PLAYERS,
                    0.7f, 1.6f);

            server.spawnParticles(
                    new DustParticleEffect(new Vector3f(r, g, b), 1.0f),
                    player.getX(), player.getBodyY(0.5), player.getZ(),
                    50, 0.5, 0.5, 0.5, 0.02
            );

            ScaleType scaleType = ScaleTypes.BASE;
            ScaleData scaleData = scaleType.getScaleData(player);
            scaleData.setScale(0.5f);
            scaleData.markForSync(true);
        }

        return result;
    }

    private ActionResult applySquish(PlayerEntity user, LivingEntity entity, ItemStack stack) {

        if (!user.isSneaking()) return ActionResult.PASS;

        if (!(entity instanceof AnimalEntity animal)) return ActionResult.PASS;

        World world = entity.getWorld();
        if (world.isClient) return ActionResult.SUCCESS;

        boolean alreadySquished = entity instanceof Squishable s && s.squish$isSquished();
        if (alreadySquished) {
            user.sendMessage(Text.literal("That mob is already squished!"), true);
            return ActionResult.FAIL;
        }

        if (entity instanceof Squishable squishable) {
            squishable.squish$setSquished(true);
        }

        animal.setBreedingAge(-24000);

        float hue = (System.currentTimeMillis() % 3000L) / 3000f;
        int rgb = Color.HSBtoRGB(hue, 0.9f, 1.0f) & 0xFFFFFF;
        float r = ((rgb >> 16) & 0xFF) / 255f;
        float g = ((rgb >> 8) & 0xFF) / 255f;
        float b = (rgb & 0xFF) / 255f;

        ServerWorld server = (ServerWorld) world;
        server.spawnParticles(
                new DustParticleEffect(new Vector3f(r, g, b), 1.0f),
                entity.getX(), entity.getBodyY(0.5), entity.getZ(),
                25, 0.4, 0.4, 0.4, 0.01
        );
        server.playSound(null, entity.getBlockPos(),
                SquishSounds.SUGAR_POP,
                SoundCategory.PLAYERS,
                0.6f, 1.8f);

        if (!user.getAbilities().creativeMode) stack.decrement(1);

        return ActionResult.SUCCESS;
    }
}