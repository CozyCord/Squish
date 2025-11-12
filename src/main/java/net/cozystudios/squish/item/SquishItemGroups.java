package net.cozystudios.squish.item;

import net.cozystudios.squish.Squish;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.cozystudios.squish.item.SquishItems.*;

public class SquishItemGroups {

    private static final Identifier BADGE_TEXTURE = new Identifier("squish", "textures/gui/tooltip_badges/squish_badge.png");

    public static final ItemGroup SQUISH_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(Squish.MOD_ID, "squish_group"),
            FabricItemGroup.builder()
                    .displayName(Text.literal("Squish"))
                    .icon(() -> new ItemStack(SQUISH_CANDY))
                    .entries((displayContext, entries) -> {
                        entries.add(SQUISH_CANDY);
                        entries.add(LOLLIPOP);
                        entries.add(SQUISH_ESSENCE);
                        entries.add(MELTED_SUGAR);
                        entries.add(HARDENED_SUGAR);
                        entries.add(HARDENED_SUGAR_SHARD);
                        entries.add(SquishItems.SQUISH_GUIDEBOOK_STACK);
                    })
                    .build()
    );

    public static void registerItemGroups() {
    }
}