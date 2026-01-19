package net.cozystudios.squish;

//? if fabric {
import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.cozystudios.squish.event.EntityInteractHandler;
import net.cozystudios.squish.event.PlayerJoinHandler;
import net.cozystudios.squish.registry.item.ExplosiveCandyItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
//? }

//? if forge {
/*import net.cozystudios.squish.event.EntityInteractHandler;
import net.cozystudios.squish.event.PlayerJoinHandler;
import net.cozystudios.squish.loader.forge.RegistryHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
*///? }

import net.cozystudios.squish.registry.block.entity.SquishBlockEntities;
import net.cozystudios.squish.registry.entity.SquishEntities;
import net.cozystudios.squish.event.SugarRushScaleHandler;
import net.cozystudios.squish.registry.sound.SquishSounds;
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
        try {
            // Try fabric RegistryHelper first
            Class.forName("net.cozystudios.squish.loader.fabric.RegistryHelper")
                .getMethod("register")
                .invoke(null);
        } catch (ClassNotFoundException e) {
            try {
                // Fallback to forge RegistryHelper
                Class.forName("net.cozystudios.squish.loader.forge.RegistryHelper")
                    .getMethod("register")
                    .invoke(null);
            } catch (ClassNotFoundException ex) {
                LOGGER.warn("No RegistryHelper found for fabric or forge; skipping registry registration");
            } catch (Exception ex) {
                LOGGER.warn("Failed to invoke forge RegistryHelper.register()", ex);
            }
        } catch (Exception e) {
            LOGGER.warn("Failed to invoke fabric RegistryHelper.register()", e);
        }
        SquishBlockEntities.register();
        SquishEntities.register();
        SquishSounds.register();
        SugarRushScaleHandler.register();

        //? if fabric {
        UseEntityCallback.EVENT.register(EntityInteractHandler::onEntityInteract);
        ServerPlayConnectionEvents.JOIN.register(PlayerJoinHandler::handleJoin);
        ServerTickEvents.END_WORLD_TICK.register(world -> ExplosiveCandyItem.tickExplosions(world));
        //? }

        //? if forge {
        /*MinecraftForge.EVENT_BUS.addListener(EntityInteractHandler::onEntityInteract);
        MinecraftForge.EVENT_BUS.addListener(PlayerJoinHandler::handleJoin);
        *///? }
    }
}
