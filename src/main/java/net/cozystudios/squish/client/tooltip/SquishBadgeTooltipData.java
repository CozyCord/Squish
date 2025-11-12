package net.cozystudios.squish.client.tooltip;

import net.minecraft.util.Identifier;
import net.minecraft.client.item.TooltipData;

public record SquishBadgeTooltipData(Identifier texture, int width, int height) implements TooltipData {
    public static SquishBadgeTooltipData of(Identifier tex, int w, int h) {
        return new SquishBadgeTooltipData(tex, w, h);
    }
}