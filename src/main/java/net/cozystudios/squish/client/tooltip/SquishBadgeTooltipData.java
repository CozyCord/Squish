package net.cozystudios.squish.client.tooltip;

import net.minecraft.util.Identifier;
//? if <=1.20.4 {
/*import net.minecraft.client.item.TooltipData;
*///?}

//? if <=1.20.4 {
/*public record SquishBadgeTooltipData(Identifier texture, int width, int height) implements TooltipData {
}
*///?} else {
public record SquishBadgeTooltipData(Identifier texture, int width, int height) {
}
//?}