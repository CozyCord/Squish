package net.cozystudios.squish.item;

import net.cozystudios.squish.entity.BabyCreeperEntity;
import net.cozystudios.squish.entity.BabyIronGolemEntity;
import net.cozystudios.squish.util.Squishable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class BitterCandyItem extends SquishBaseItem {
    public BitterCandyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            // Give nausea for 5 seconds (100 ticks)
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 0));
            // Give instant damage
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 0));
        }
        return super.finishUsing(stack, world, user);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // If not sneaking, allow eating
        if (!user.isSneaking()) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(stack);
        }

        // If sneaking, try to find a nearby squished animal to unsquish
        if (world.isClient) {
            return TypedActionResult.pass(stack);
        }

        Vec3d lookVec = user.getRotationVec(1.0F);
        Box searchBox = user.getBoundingBox().stretch(lookVec.multiply(3.5D)).expand(1.0D);

        // Search for squished animals, baby creepers, and baby iron golems
        List<LivingEntity> entities = world.getEntitiesByClass(
                LivingEntity.class,
                searchBox,
                e -> e.isAlive() && (
                        (e instanceof AnimalEntity && e instanceof Squishable s && s.squish$isSquished()) ||
                        e instanceof BabyCreeperEntity ||
                        e instanceof BabyIronGolemEntity
                )
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
            ActionResult result = applyUnsquish(user, closest, stack);
            if (result.isAccepted()) {
                return TypedActionResult.success(stack);
            }
        }

        return TypedActionResult.pass(stack);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        // Only unsquish when sneaking
        if (!user.isSneaking()) {
            return ActionResult.PASS;
        }

        return applyUnsquish(user, entity, stack);
    }

    private ActionResult applyUnsquish(PlayerEntity user, LivingEntity entity, ItemStack stack) {
        World world = user.getWorld();

        // Handle Baby Creeper -> Creeper
        if (entity instanceof BabyCreeperEntity babyCreeper) {
            if (!world.isClient) {
                CreeperEntity creeper = EntityType.CREEPER.create(world);
                if (creeper == null) return ActionResult.FAIL;

                creeper.refreshPositionAndAngles(babyCreeper.getX(), babyCreeper.getY(), babyCreeper.getZ(),
                        babyCreeper.getYaw(), babyCreeper.getPitch());
                creeper.setBodyYaw(babyCreeper.getBodyYaw());
                creeper.setHeadYaw(babyCreeper.getHeadYaw());

                if (babyCreeper.hasCustomName()) {
                    creeper.setCustomName(babyCreeper.getCustomName());
                    creeper.setCustomNameVisible(babyCreeper.isCustomNameVisible());
                }

                world.spawnEntity(creeper);
                babyCreeper.discard();

                playUnsquishEffects(world, creeper.getX(), creeper.getY(), creeper.getZ());

                if (!user.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }
            return ActionResult.success(world.isClient);
        }

        // Handle Baby Iron Golem -> Iron Golem
        if (entity instanceof BabyIronGolemEntity babyGolem) {
            if (!world.isClient) {
                IronGolemEntity golem = EntityType.IRON_GOLEM.create(world);
                if (golem == null) return ActionResult.FAIL;

                golem.refreshPositionAndAngles(babyGolem.getX(), babyGolem.getY(), babyGolem.getZ(),
                        babyGolem.getYaw(), babyGolem.getPitch());
                golem.setBodyYaw(babyGolem.getBodyYaw());
                golem.setHeadYaw(babyGolem.getHeadYaw());

                if (babyGolem.hasCustomName()) {
                    golem.setCustomName(babyGolem.getCustomName());
                    golem.setCustomNameVisible(babyGolem.isCustomNameVisible());
                }

                world.spawnEntity(golem);
                babyGolem.discard();

                playUnsquishEffects(world, golem.getX(), golem.getY(), golem.getZ());

                if (!user.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }
            return ActionResult.success(world.isClient);
        }

        // Handle squished animals -> grow them back to adult
        if (entity instanceof AnimalEntity animal && animal instanceof Squishable squishable) {
            if (squishable.squish$isSquished()) {
                if (!world.isClient) {
                    // Unsquish the animal and make it an adult
                    squishable.squish$setSquished(false);
                    animal.setBreedingAge(0); // Set to adult

                    playUnsquishEffects(world, animal.getX(), animal.getY(), animal.getZ());

                    if (!user.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                }
                return ActionResult.success(world.isClient);
            }
        }
        return ActionResult.PASS;
    }

    private void playUnsquishEffects(World world, double x, double y, double z) {
        world.playSound(null, x, y, z,
                SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.NEUTRAL,
                1.0F, 0.8F);

        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.SMOKE,
                    x, y + 0.5, z,
                    20, 0.3, 0.3, 0.3, 0.02);
        }
    }
}
