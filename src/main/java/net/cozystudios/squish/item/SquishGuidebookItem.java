package net.cozystudios.squish.item;

import net.cozystudios.squish.client.tooltip.SquishBadgeTooltipData;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import vazkii.patchouli.common.item.ItemModBook;

import java.util.Optional;

public class SquishGuidebookItem extends ItemModBook {
    private static final Identifier BADGE_TEXTURE =
            new Identifier("squish", "textures/gui/tooltip_badges/squish_badge.png");

    public SquishGuidebookItem(Settings settings) {
        super();
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return Optional.of(new SquishBadgeTooltipData(BADGE_TEXTURE, 64, 17));
    }
}