package net.cozystudios.squish;

import net.fabricmc.api.ModInitializer;
import net.cozystudios.squish.item.SquishItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Squish implements ModInitializer {
    public static final String MOD_ID = "squish";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Squish Initialized");
        SquishItems.register();
    }
}