package net.cozystudios.squish.mixin.brewing;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
//? if <=1.20.4 {
/*import net.minecraft.potion.PotionUtil;
*///?} else {
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
//?}
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingStandBlockEntity.class)
public abstract class BrewingStandBlockEntityMixin {

    @Shadow int brewTime;
    @Shadow int fuel;

    @Unique
    private int squish$essenceBrewTime = 0;

    @Unique
    private static final int SQUISH_BREW_TIME = 400;

    @Inject(method = "tick", at = @At("TAIL"))
    private static void squish$brewEssence(World world,
                                           BlockPos pos,
                                           BlockState state,
                                           BrewingStandBlockEntity stand,
                                           CallbackInfo ci) {
        if (world.isClient) return;

        BrewingStandBlockEntityMixin self = (BrewingStandBlockEntityMixin)(Object) stand;

        ItemStack ingredient = stand.getStack(3);
        if (!ingredient.isOf(RegistryHelper.HARDENED_SUGAR_SHARD)) {
            self.squish$essenceBrewTime = 0;
            return;
        }

        if (self.fuel <= 0) {
            self.squish$essenceBrewTime = 0;
            return;
        }

        boolean hasWater = false;
        for (int i = 0; i < 3; i++) {
            ItemStack stack = stand.getStack(i);
            //? if <=1.20.4 {
            /*if (stack.isOf(Items.POTION) && PotionUtil.getPotion(stack) == Potions.WATER) {
            *///?} else {
            if (stack.isOf(Items.POTION) && stack.contains(DataComponentTypes.POTION_CONTENTS) &&
                    stack.get(DataComponentTypes.POTION_CONTENTS).potion().isPresent() &&
                    stack.get(DataComponentTypes.POTION_CONTENTS).potion().get() == Potions.WATER) {
            //?}
                hasWater = true;
                break;
            }
        }

        if (!hasWater) {
            self.squish$essenceBrewTime = 0;
            return;
        }

        if (self.squish$essenceBrewTime <= 0) {
            self.squish$essenceBrewTime = SQUISH_BREW_TIME;
            self.brewTime = SQUISH_BREW_TIME;
            world.playSound(
                    null,
                    pos,
                    SoundEvents.BLOCK_BREWING_STAND_BREW,
                    SoundCategory.BLOCKS,
                    0.7F,
                    1.0F
            );
        }

        self.squish$essenceBrewTime--;
        self.brewTime = self.squish$essenceBrewTime;

        if (self.squish$essenceBrewTime <= 0) {
            boolean brewed = false;

            for (int i = 0; i < 3; i++) {
                ItemStack stack = stand.getStack(i);
                //? if <=1.20.4 {
                /*if (stack.isOf(Items.POTION) && PotionUtil.getPotion(stack) == Potions.WATER) {
                *///?} else {
                if (stack.isOf(Items.POTION) && stack.contains(DataComponentTypes.POTION_CONTENTS) &&
                        stack.get(DataComponentTypes.POTION_CONTENTS).potion().isPresent() &&
                        stack.get(DataComponentTypes.POTION_CONTENTS).potion().get() == Potions.WATER) {
                //?}
                    stand.setStack(i, new ItemStack(RegistryHelper.SQUISH_ESSENCE));
                    brewed = true;
                }
            }

            if (brewed) {
                self.fuel = Math.max(0, self.fuel - 1);
                ingredient.decrement(1);
                stand.markDirty();

                world.playSound(
                        null,
                        pos,
                        SoundEvents.BLOCK_BREWING_STAND_BREW,
                        SoundCategory.BLOCKS,
                        0.8F,
                        1.0F
                );
            }

            self.squish$essenceBrewTime = 0;
            self.brewTime = 0;
        }
    }
}
