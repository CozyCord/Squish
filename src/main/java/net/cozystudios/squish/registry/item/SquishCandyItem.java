package net.cozystudios.squish.registry.item;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.cozystudios.squish.registry.entity.BabyCreeperEntity;
import net.cozystudios.squish.registry.entity.BabyEndermanEntity;
import net.cozystudios.squish.registry.entity.BabyIronGolemEntity;
import net.cozystudios.squish.registry.entity.SquishEntities;
import net.cozystudios.squish.registry.sound.SquishSounds;
import net.cozystudios.squish.util.CandyInfusion;
import net.cozystudios.squish.util.Squishable;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
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
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        int level = CandyInfusion.getLevel(stack);
        tooltip.add(Text.literal("Infusion: " + level + "/5").formatted(Formatting.LIGHT_PURPLE));
    }


    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.isSneaking()) {
            return ActionResult.PASS;
        }

        ActionResult result = applySquish(user, entity, stack);

        return (result == ActionResult.SUCCESS || result == ActionResult.FAIL) ? ActionResult.SUCCESS : ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!user.isSneaking()) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(stack);
        }

        if (world.isClient) {
            return TypedActionResult.pass(stack);
        }

        Vec3d lookVec = user.getRotationVec(1.0F);
        Box searchBox = user.getBoundingBox().stretch(lookVec.multiply(3.5D)).expand(1.0D);

        List<LivingEntity> entities = world.getEntitiesByClass(
                LivingEntity.class,
                searchBox,
                e -> e instanceof AnimalEntity && e.isAlive()
        );

        LivingEntity closest = null;
        double closestDistSq = 3.5D * 3.5D;

        for (LivingEntity e : entities) {
            double distSq = e.squaredDistanceTo(user);
            if (distSq < closestDistSq) {
                closestDistSq = distSq;
                closest = e;
            }
        }

        if (closest != null) {
            ActionResult result = applySquish(user, closest, stack);
            if (result.isAccepted()) {
                return TypedActionResult.success(stack);
            }
            return TypedActionResult.pass(stack);
        }

        return TypedActionResult.pass(stack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);

        if (!world.isClient && user instanceof PlayerEntity player) {

            int infusionLevel = CandyInfusion.getLevel(stack);

            int addedTicks = 20 * 60 * infusionLevel;

            int newAmplifier = 0;

            StatusEffectInstance existing = player.getStatusEffect(RegistryHelper.SUGAR_RUSH);

            int finalDuration = addedTicks;
            int finalAmplifier = newAmplifier;

            if (existing != null) {
                finalDuration = existing.getDuration() + addedTicks;

                finalAmplifier = Math.max(existing.getAmplifier(), newAmplifier);
            }

            player.addStatusEffect(new StatusEffectInstance(
                    RegistryHelper.SUGAR_RUSH,
                    finalDuration,
                    finalAmplifier,
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
            server.playSound(
                    null,
                    player.getBlockPos(),
                    SquishSounds.SUGAR_POP,
                    SoundCategory.PLAYERS,
                    0.7f,
                    1.6f
            );

            server.spawnParticles(
                    new DustParticleEffect(new Vector3f(r, g, b), 1.0f),
                    player.getX(),
                    player.getBodyY(0.5),
                    player.getZ(),
                    50,
                    0.5, 0.5, 0.5,
                    0.02
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

        World world = entity.getWorld();
        if (world.isClient) return ActionResult.SUCCESS;

        if (entity instanceof CreeperEntity creeper) {

            if (entity instanceof BabyCreeperEntity) {
                user.sendMessage(Text.literal("That mob is already squished!"), true);
                return ActionResult.FAIL;
            }

            BabyCreeperEntity baby = SquishEntities.BABY_CREEPER.create(world);
            if (baby == null) return ActionResult.FAIL;

            baby.refreshPositionAndAngles(creeper.getX(), creeper.getY(), creeper.getZ(), creeper.getYaw(), creeper.getPitch());
            baby.setBodyYaw(creeper.getBodyYaw());
            baby.setHeadYaw(creeper.getHeadYaw());
            baby.setAiDisabled(creeper.isAiDisabled());

            if (creeper.hasCustomName()) {
                baby.setCustomName(creeper.getCustomName());
                baby.setCustomNameVisible(creeper.isCustomNameVisible());
            }

            world.spawnEntity(baby);
            creeper.discard();

            float hue = (System.currentTimeMillis() % 3000L) / 3000f;
            int rgb = Color.HSBtoRGB(hue, 0.9f, 1.0f) & 0xFFFFFF;
            float r = ((rgb >> 16) & 0xFF) / 255f;
            float g = ((rgb >> 8) & 0xFF) / 255f;
            float b = (rgb & 0xFF) / 255f;

            ServerWorld server = (ServerWorld) world;
            server.spawnParticles(
                    new DustParticleEffect(new Vector3f(r, g, b), 1.0f),
                    baby.getX(), baby.getBodyY(0.5), baby.getZ(),
                    35, 0.4, 0.4, 0.4, 0.01
            );
            server.playSound(null, baby.getBlockPos(),
                    SquishSounds.SUGAR_POP,
                    SoundCategory.PLAYERS,
                    0.7f, 1.6f);

            if (!user.getAbilities().creativeMode) stack.decrement(1);

            user.sendMessage(Text.literal("...it worked?"), true);
            return ActionResult.SUCCESS;
        }

        if (entity instanceof net.minecraft.entity.passive.IronGolemEntity golem) {

            if (entity instanceof BabyIronGolemEntity) {
                user.sendMessage(Text.literal("That mob is already squished!"), true);
                return ActionResult.FAIL;
            }

            BabyIronGolemEntity baby = SquishEntities.BABY_IRON_GOLEM.create(world);
            if (baby == null) return ActionResult.FAIL;

            baby.refreshPositionAndAngles(golem.getX(), golem.getY(), golem.getZ(), golem.getYaw(), golem.getPitch());
            baby.setBodyYaw(golem.getBodyYaw());
            baby.setHeadYaw(golem.getHeadYaw());
            baby.setAiDisabled(golem.isAiDisabled());

            if (golem.hasCustomName()) {
                baby.setCustomName(golem.getCustomName());
                baby.setCustomNameVisible(golem.isCustomNameVisible());
            }

            world.spawnEntity(baby);
            golem.discard();

            float hue = (System.currentTimeMillis() % 3000L) / 3000f;
            int rgb = Color.HSBtoRGB(hue, 0.9f, 1.0f) & 0xFFFFFF;
            float r = ((rgb >> 16) & 0xFF) / 255f;
            float g = ((rgb >> 8) & 0xFF) / 255f;
            float b = (rgb & 0xFF) / 255f;

            ServerWorld server = (ServerWorld) world;
            server.spawnParticles(
                    new DustParticleEffect(new Vector3f(r, g, b), 1.0f),
                    baby.getX(), baby.getBodyY(0.5), baby.getZ(),
                    45, 0.5, 0.5, 0.5, 0.01
            );
            server.playSound(null, baby.getBlockPos(),
                    SquishSounds.SUGAR_POP,
                    SoundCategory.PLAYERS,
                    0.8f, 1.35f);

            if (!user.getAbilities().creativeMode) stack.decrement(1);

            user.sendMessage(Text.literal("...it worked?"), true);
            return ActionResult.SUCCESS;
        }

        if (entity instanceof net.minecraft.entity.mob.EndermanEntity enderman) {

            if (entity instanceof BabyEndermanEntity) {
                user.sendMessage(Text.literal("That mob is already squished!"), true);
                return ActionResult.FAIL;
            }

            BabyEndermanEntity baby = SquishEntities.BABY_ENDERMAN.create(world);
            if (baby == null) return ActionResult.FAIL;

            baby.refreshPositionAndAngles(enderman.getX(), enderman.getY(), enderman.getZ(), enderman.getYaw(), enderman.getPitch());
            baby.setBodyYaw(enderman.getBodyYaw());
            baby.setHeadYaw(enderman.getHeadYaw());
            baby.setAiDisabled(enderman.isAiDisabled());

            if (enderman.hasCustomName()) {
                baby.setCustomName(enderman.getCustomName());
                baby.setCustomNameVisible(enderman.isCustomNameVisible());
            }

            world.spawnEntity(baby);
            enderman.discard();

            float hue = (System.currentTimeMillis() % 3000L) / 3000f;
            int rgb = Color.HSBtoRGB(hue, 0.9f, 1.0f) & 0xFFFFFF;
            float r = ((rgb >> 16) & 0xFF) / 255f;
            float g = ((rgb >> 8) & 0xFF) / 255f;
            float b = (rgb & 0xFF) / 255f;

            ServerWorld server = (ServerWorld) world;
            server.spawnParticles(
                    new DustParticleEffect(new Vector3f(r, g, b), 1.0f),
                    baby.getX(), baby.getBodyY(0.5), baby.getZ(),
                    45, 0.5, 0.6, 0.5, 0.01
            );
            server.playSound(null, baby.getBlockPos(),
                    SquishSounds.SUGAR_POP,
                    SoundCategory.PLAYERS,
                    0.8f, 1.35f);

            if (!user.getAbilities().creativeMode) stack.decrement(1);

            user.sendMessage(Text.literal("...it worked?"), true);
            return ActionResult.SUCCESS;
        }

        if (!(entity instanceof AnimalEntity animal)) return ActionResult.PASS;

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
