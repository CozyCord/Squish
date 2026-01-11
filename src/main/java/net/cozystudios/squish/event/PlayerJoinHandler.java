package net.cozystudios.squish.event;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

//? if fabric {
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
//? }

//? if forge {
/*import net.minecraftforge.event.entity.player.PlayerEvent;
*///? }

public class PlayerJoinHandler {
    //? if fabric {
    public static void handleJoin(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        giveGuideBook(handler.getPlayer(), server);
    }
    //? }

    //? if forge {
    /*public static void handleJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayerEntity serverPlayer) {
            giveGuideBook(serverPlayer, serverPlayer.getServer());
        }
    }
    *///? }

    private static void giveGuideBook(ServerPlayerEntity player, MinecraftServer server) {
        var state = net.cozystudios.squish.save.SquishFirstJoinBookState.get(server);

        if (state.markIfNew(player.getUuid())) {
            ItemStack book = new ItemStack(RegistryHelper.SQUISH_GUIDEBOOK);
            book.getOrCreateNbt().putString("patchouli:book", "squish:squish_guidebook");

            if (!player.getInventory().insertStack(book)) {
                player.dropItem(book, false);
            }
        }
    }
}
