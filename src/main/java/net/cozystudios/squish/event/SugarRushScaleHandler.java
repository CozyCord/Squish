package net.cozystudios.squish.event;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

public class SugarRushScaleHandler {
    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                boolean hasEffect = player.hasStatusEffect(RegistryHelper.SUGAR_RUSH);
                ScaleData data = ScaleTypes.BASE.getScaleData(player);

                if (!hasEffect && data.getScale() != 1.0f) {
                    data.setScale(1.0f);
                    data.markForSync(true);
                }
            }
        });
    }
}
