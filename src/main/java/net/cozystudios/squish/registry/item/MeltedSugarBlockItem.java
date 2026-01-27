package net.cozystudios.squish.registry.item;

import net.cozystudios.squish.util.StackNbt;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class MeltedSugarBlockItem extends SquishBaseBlockItem {

    public MeltedSugarBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        boolean preserved = StackNbt.getBoolean(stack, "Preserved");
        String key = preserved ? "block.squish.waxed_melted_sugar_block" : this.getTranslationKey();
        Text name = Text.translatable(key);
        //? if >1.20.4 {
        return SquishBaseItem.applySquishStyle(name);
        //?} else {
        /*return name;
        *///?}
    }
}
