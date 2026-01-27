package net.cozystudios.squish.registry.item;

//? if <=1.20.4 {
/*import net.cozystudios.squish.client.tooltip.SquishBadgeTooltipData;
import net.minecraft.client.item.TooltipData;
*///?}
import net.cozystudios.squish.util.SquishId;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class SquishBaseBlockItem extends BlockItem {
    //? if <=1.20.4 {
    /*private static final Identifier BADGE_TEXTURE =
            SquishId.of("squish", "textures/gui/tooltip_badges/squish_badge.png");
    *///?}

    public SquishBaseBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    //? if <=1.20.4 {
    /*@Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return Optional.of(new SquishBadgeTooltipData(BADGE_TEXTURE, 64, 17));
    }
    *///?}

    //? if >1.20.4 {
    @Override
    public Text getName(ItemStack stack) {
        Text original = super.getName(stack);
        return SquishBaseItem.applySquishStyle(original);
    }
    //?}
}
