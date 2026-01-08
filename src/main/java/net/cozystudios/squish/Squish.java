package net.cozystudios.squish;

//? if fabric {
import net.cozystudios.squish.event.EntityInteractHandler;
import net.cozystudios.squish.event.PlayerJoinHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
//? }

//? if forge {
/*import net.cozystudios.squish.event.EntityInteractHandler;
import net.cozystudios.squish.event.PlayerJoinHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
*///? }

import net.cozystudios.squish.block.SquishBlocks;
import net.cozystudios.squish.block.entity.SquishBlockEntities;
import net.cozystudios.squish.effect.SquishEffects;
import net.cozystudios.squish.entity.SquishEntities;
import net.cozystudios.squish.event.OnEntityInteract;
import net.cozystudios.squish.event.SugarRushScaleHandler;
import net.cozystudios.squish.item.SquishItemGroups;
import net.cozystudios.squish.sound.SquishSounds;
import net.cozystudios.squish.item.SquishItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? if fabric {
public class Squish implements ModInitializer {
//? }

//? if forge {
/*@Mod("squish")
public class Squish {
*///? }
    public static final String MOD_ID = "squish";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    //? if fabric {
    @Override
    public void onInitialize() {
    //? }

    //? if forge {
    /*public Squish(final FMLJavaModLoadingContext context) {
    *///? }
        LOGGER.info("Squish Initialized");
        SquishItems.register();
        SquishBlocks.register();
        SquishBlockEntities.register();
        SquishEffects.register();
        SquishEntities.register();
        SquishSounds.register();
        SugarRushScaleHandler.register();
        SquishItemGroups.registerItemGroups();

        //? if fabric {
        UseEntityCallback.EVENT.register(EntityInteractHandler::onEntityInteract);
        ServerPlayConnectionEvents.JOIN.register(PlayerJoinHandler::handleJoin);
        //? }

        //? if forge {
        /*MinecraftForge.EVENT_BUS.addListener(EntityInteractHandler::onEntityInteract);
        MinecraftForge.EVENT_BUS.addListener(PlayerJoinHandler::handleJoin);
        *///? }
    }
}
