package net.cozystudios.squish.registry.item;

import net.cozystudios.squish.util.SquishId;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import vazkii.patchouli.api.PatchouliAPI;

public class SquishGuidebookItem extends Item {
    private static final Identifier BOOK_ID = SquishId.of("squish", "squish_guidebook");

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

    //? if >1.20.4 {
    @Override
    public net.minecraft.text.Text getName(ItemStack stack) {
        return SquishBaseItem.applySquishStyle(super.getName(stack));
    }
    //?}
}
