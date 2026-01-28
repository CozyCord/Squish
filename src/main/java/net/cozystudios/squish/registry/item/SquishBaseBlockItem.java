package net.cozystudios.squish.registry.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class SquishBaseBlockItem extends BlockItem {
    public SquishBaseBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    //? if >1.20.4 {
    @Override
    public Text getName(ItemStack stack) {
        Text original = super.getName(stack);
        return SquishBaseItem.applySquishStyle(original);
    }
    //?}
}
