package net.cozystudios.squish.registry.item;

//? if <=1.20.4 {
/*import net.cozystudios.squish.client.tooltip.SquishBadgeTooltipData;
import net.minecraft.client.item.TooltipData;
*///?}
import net.cozystudios.squish.util.SquishId;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.Optional;

public class SquishGuidebookItem extends Item {
    private static final Identifier BOOK_ID = SquishId.of("squish", "squish_guidebook");
    //? if <=1.20.4 {
    /*private static final Identifier BADGE_TEXTURE =
            SquishId.of("squish", "textures/gui/tooltip_badges/squish_badge.png");
    *///?}

    public SquishGuidebookItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            PatchouliAPI.get().openBookGUI(BOOK_ID);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    //? if <=1.20.4 {
    /*@Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return Optional.of(new SquishBadgeTooltipData(BADGE_TEXTURE, 64, 17));
    }
    *///?}

    //? if >1.20.4 {
    @Override
    public net.minecraft.text.Text getName(ItemStack stack) {
        return SquishBaseItem.applySquishStyle(super.getName(stack));
    }
    //?}
}
