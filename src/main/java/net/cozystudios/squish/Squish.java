package net.cozystudios.squish;

import net.cozystudios.squish.block.SquishBlocks;
import net.cozystudios.squish.block.entity.SquishBlockEntities;
import net.cozystudios.squish.effect.SquishEffects;
import net.cozystudios.squish.event.SugarRushScaleHandler;
import net.cozystudios.squish.item.SquishItemGroups;
import net.cozystudios.squish.sound.SquishSounds;
import net.fabricmc.api.ModInitializer;
import net.cozystudios.squish.item.SquishItems;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Squish implements ModInitializer {
    public static final String MOD_ID = "squish";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Squish Initialized");
        SquishItems.register();
        SquishBlocks.register();
        SquishBlockEntities.register();
        SquishEffects.register();
        SquishSounds.register();
        SugarRushScaleHandler.register();
        SquishItemGroups.registerItemGroups();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            PlayerEntity player = handler.player;
            ItemStack book = new ItemStack(SquishItems.SQUISH_GUIDEBOOK);
            book.getOrCreateNbt().putString("patchouli:book", "squish:squish_guidebook");

            if (!player.getInventory().contains(book)) {
                player.giveItemStack(book);
            }
        });
    }
}