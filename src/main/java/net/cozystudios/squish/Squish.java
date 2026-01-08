package net.cozystudios.squish;

/*? if fabric {*/
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
/*?}*/

import net.cozystudios.squish.block.SquishBlocks;
import net.cozystudios.squish.block.entity.SquishBlockEntities;
import net.cozystudios.squish.effect.SquishEffects;
import net.cozystudios.squish.event.SugarRushScaleHandler;
import net.cozystudios.squish.item.SquishItemGroups;
import net.cozystudios.squish.sound.SquishSounds;
import net.cozystudios.squish.item.SquishItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*?if fabric {*/
public class Squish implements ModInitializer {
/*?}*/
    public static final String MOD_ID = "squish";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    /*?if fabric {*/
    @Override
    public void onInitialize() {
    /*?}*/
        LOGGER.info("Squish Initialized");
        SquishItems.register();
        SquishBlocks.register();
        SquishBlockEntities.register();
        SquishEffects.register();
        SquishSounds.register();
        SugarRushScaleHandler.register();
        SquishItemGroups.registerItemGroups();

        /*?if fabric {*/
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!player.getStackInHand(hand).isOf(SquishItems.SQUISH_CANDY)) {
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
        });

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();

            var state = net.cozystudios.squish.save.SquishFirstJoinBookState.get(server);

            if (state.markIfNew(player.getUuid())) {

                ItemStack book = new ItemStack(SquishItems.SQUISH_GUIDEBOOK);
                book.getOrCreateNbt().putString("patchouli:book", "squish:squish_guidebook");

                if (!player.getInventory().insertStack(book)) {
                    player.dropItem(book, false);
                }
            }
        });
        /*?}*/
    }
}
