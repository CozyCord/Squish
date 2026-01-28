package net.cozystudios.squish.mixin;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.cozystudios.squish.registry.sound.SquishSounds;
import net.cozystudios.squish.util.Squishable;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntitySquishMixin {

    @Inject(
            method = "interactMob",
            at = @At("HEAD"),
            cancellable = true
    )
    private void squish$onInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack held = player.getStackInHand(hand);

        if (held.isOf(RegistryHelper.BITTER_CANDY) && player.isSneaking()) {
            AnimalEntity animal = (AnimalEntity) (Object) this;
            World world = animal.getWorld();

            if (animal instanceof Squishable squishable && squishable.squish$isSquished()) {
                if (!world.isClient) {
                    squishable.squish$setSquished(false);
                    squishable.squish$setUnsquishing(true);
                    animal.setBreedingAge(0);
                    squishable.squish$setUnsquishing(false);

                    ServerWorld server = (ServerWorld) world;
                    server.playSound(null, animal.getBlockPos(),
                            SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.NEUTRAL,
                            1.0F, 0.8F);
                    server.spawnParticles(ParticleTypes.SMOKE,
                            animal.getX(), animal.getBodyY(0.5), animal.getZ(),
                            20, 0.3, 0.3, 0.3, 0.02);

                    if (!player.getAbilities().creativeMode) {
                        held.decrement(1);
                    }
                }
                cir.setReturnValue(ActionResult.SUCCESS);
                return;
            }
            cir.setReturnValue(ActionResult.PASS);
            return;
        }

        if (!held.isOf(RegistryHelper.SQUISH_CANDY)) return;

        AnimalEntity animal = (AnimalEntity) (Object) this;
        World world = animal.getWorld();
        if (world.isClient) return;

        boolean alreadySquished = animal instanceof Squishable squishable && squishable.squish$isSquished();
        if (alreadySquished) {
            player.sendMessage(Text.literal("That mob is already squished!"), true);
            cir.setReturnValue(ActionResult.FAIL);
            return;
        }

        if (animal instanceof Squishable squishable) {
            squishable.squish$setSquished(true);
        }

        animal.setBreedingAge(-24000);

        ServerWorld server = (ServerWorld) world;
        server.playSound(
                null,
                animal.getBlockPos(),
                SquishSounds.SUGAR_POP,
                SoundCategory.PLAYERS,
                0.9f,
                1.3f
        );

        server.spawnParticles(
                new DustParticleEffect(new Vector3f(1.0f, 0.0f, 1.0f), 1.0f),
                animal.getX(),
                animal.getBodyY(0.5),
                animal.getZ(),
                40,
                0.5,
                0.5,
                0.5,
                0.0
        );

        if (!player.getAbilities().creativeMode) {
            held.decrement(1);
        }

        cir.setReturnValue(ActionResult.SUCCESS);
    }
}
