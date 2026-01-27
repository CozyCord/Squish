package net.cozystudios.squish.registry.item;

//? if <=1.20.4 {
/*import net.cozystudios.squish.client.tooltip.SquishBadgeTooltipData;
import net.minecraft.client.item.TooltipData;
*///?}
import net.cozystudios.squish.util.SquishId;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class SquishBaseItem extends Item {
    //? if <=1.20.4 {
    /*private static final Identifier BADGE_TEXTURE =
            SquishId.of("squish", "textures/gui/tooltip_badges/squish_badge.png");
    *///?}

    protected static final int MAGENTA = 0xDF3C73;
    protected static final int LILAC = 0xCFBDDF;
    protected static final int POPPY_RED = 0xED1C24;
    protected static final int TNT_RED = 0xDB2E00;
    protected static final int DARK_GRAY = 0x555555;
    protected static final int ENDER_PURPLE = 0xCC00FA;

    public SquishBaseItem(Settings settings) {
        super(settings);
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
        }

        final int finalColor = color;
        return Text.literal(plainString)
                .formatted(Formatting.BOLD)
                .styled(style -> style.withColor(TextColor.fromRgb(finalColor)));
    }
}
