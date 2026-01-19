package net.cozystudios.squish.registry.item;

import net.cozystudios.squish.client.tooltip.SquishBadgeTooltipData;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class SquishBaseItem extends Item {
    private static final Identifier BADGE_TEXTURE =
            new Identifier("squish", "textures/gui/tooltip_badges/squish_badge.png");

    public SquishBaseItem(Settings settings) {
        super(settings);
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return Optional.of(new SquishBadgeTooltipData(BADGE_TEXTURE, 64, 17));
    }
}
