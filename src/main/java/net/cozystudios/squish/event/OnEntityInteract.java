package net.cozystudios.squish.event;

import net.cozystudios.squish.fabric.RegistryHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class OnEntityInteract {
    public static ActionResult onEntityInteract(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) {
        if (!player.getStackInHand(hand).isOf(RegistryHelper.SQUISH_CANDY)) {
            return ActionResult.PASS;
        }

        if (!(entity instanceof LivingEntity living)) {
            return ActionResult.PASS;
        }

        if (player.isSneaking()) {

            ActionResult result = player.getStackInHand(hand).useOnEntity(player, living, hand);

            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
