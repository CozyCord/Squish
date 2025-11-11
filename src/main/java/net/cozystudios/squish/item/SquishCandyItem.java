package net.cozystudios.squish.item;

import net.cozystudios.squish.util.Squishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.List;

public class SquishCandyItem extends Item {
    public SquishCandyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        ActionResult result = applySquish(user, entity, stack);
        return result.isAccepted() ? ActionResult.SUCCESS : ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) return TypedActionResult.pass(user.getStackInHand(hand));

        Vec3d eyePos = user.getCameraPosVec(1.0F);
        Vec3d lookVec = user.getRotationVec(1.0F);
        Vec3d reachVec = eyePos.add(lookVec.multiply(3.5D));

        Box searchBox = user.getBoundingBox().stretch(lookVec.multiply(3.5D)).expand(1.0D, 1.0D, 1.0D);
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
            ActionResult result = applySquish(user, closest, user.getStackInHand(hand));
            if (result.isAccepted()) {
                return TypedActionResult.success(user.getStackInHand(hand));
            }
        }

        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    private ActionResult applySquish(PlayerEntity user, LivingEntity entity, ItemStack stack) {
        if (!(entity instanceof AnimalEntity animal)) {
            return ActionResult.PASS;
        }

        World world = entity.getWorld();
        if (world.isClient) return ActionResult.SUCCESS;

        boolean alreadySquished = entity instanceof Squishable squishable && squishable.squish$isSquished();
        if (alreadySquished) {
            user.sendMessage(Text.literal("That mob is already squished!"), true);
            return ActionResult.FAIL;
        }

        if (entity instanceof Squishable squishable) {
            squishable.squish$setSquished(true);
        }

        animal.setBreedingAge(-24000);

        ServerWorld server = (ServerWorld) world;
        server.spawnParticles(
                new DustParticleEffect(new Vector3f(1.0f, 0.0f, 1.0f), 1.0f),
                entity.getX(), entity.getBodyY(0.5), entity.getZ(),
                20, 0.4, 0.4, 0.4, 0.0
        );
        server.playSound(
                null, entity.getBlockPos(),
                SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                SoundCategory.PLAYERS,
                0.6f, 1.8f
        );

        if (!user.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        return ActionResult.SUCCESS;
    }
}