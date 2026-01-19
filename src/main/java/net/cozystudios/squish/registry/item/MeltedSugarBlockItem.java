package net.cozystudios.squish.registry.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class MeltedSugarBlockItem extends SquishBaseBlockItem {

    public MeltedSugarBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        boolean preserved = stack.hasNbt() && stack.getNbt().getBoolean("Preserved");
        String key = preserved ? "block.squish.waxed_melted_sugar_block" : this.getTranslationKey();
        return Text.translatable(key);
    }
}
