package net.cozystudios.squish.registry.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

public class SquishBaseItem extends Item {
    protected static final int MAGENTA = 0xDF3C73;
    protected static final int LILAC = 0xCFBDDF;
    protected static final int POPPY_RED = 0xED1C24;
    protected static final int TNT_RED = 0xDB2E00;
    protected static final int DARK_GRAY = 0x555555;
    protected static final int ENDER_PURPLE = 0xCC00FA;
    protected static final int SKELETON_WHITE = 0xE0DED8;

    public SquishBaseItem(Settings settings) {
        super(settings);
    }

    //? if >1.20.4 {
    @Override
    public Text getName(ItemStack stack) {
        Text original = super.getName(stack);
        return applySquishStyle(original);
    }
    //?}

    public static Text applySquishStyle(Text original) {
        String plainString = original.getString();
        String plain = plainString.toLowerCase();

        int color = MAGENTA;

        if (plain.contains("lollipop") || plain.contains("melted sugar") || plain.contains("hardened sugar")) {
            color = LILAC;
        } else if (plain.contains("squish") || plain.contains("manual")) {
            color = MAGENTA;
        } else if (plain.contains("poppy")) {
            color = POPPY_RED;
        } else if (plain.contains("explosive")) {
            color = TNT_RED;
        } else if (plain.contains("bitter")) {
            color = DARK_GRAY;
        } else if (plain.contains("ender")) {
            color = ENDER_PURPLE;
        } else if (plain.contains("skelly")) {
            color = SKELETON_WHITE;
        }

        final int finalColor = color;
        return Text.literal(plainString)
                .formatted(Formatting.BOLD)
                .styled(style -> style.withColor(TextColor.fromRgb(finalColor)));
    }
}
