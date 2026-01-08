package net.cozystudios.squish.event;

import net.cozystudios.squish.item.SquishItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
//? if forge {
/*import net.minecraftforge.event.entity.player.PlayerInteractEvent;
*///? }

public class EntityInteractHandler {
    //? if fabric {
    public static ActionResult onEntityInteract(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) {
        return handleInteraction(player, hand, entity) ? ActionResult.SUCCESS : ActionResult.PASS;
    }
    //? }

    //? if forge {
    /*public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (handleInteraction(event.getEntity(), event.getHand(), event.getTarget())) {
            event.setCanceled(true);
            event.setCancellationResult(ActionResult.SUCCESS);
        }
    }
    *///? }

    private static boolean handleInteraction(PlayerEntity player, Hand hand, Entity entity) {
        ItemStack stack = player.getStackInHand(hand);

        if (!stack.isOf(SquishItems.SQUISH_CANDY)) {
            return false;
        }

        if (!(entity instanceof LivingEntity living)) {
            return false;
        }

        if (player.isSneaking()) {
            stack.useOnEntity(player, living, hand);
            return true;
        }
        return false;
    }
}
