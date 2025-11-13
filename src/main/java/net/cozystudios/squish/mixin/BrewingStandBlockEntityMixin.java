package net.cozystudios.squish.mixin;

import net.cozystudios.squish.item.SquishItems;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
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

    @Unique private int squish$customBrewTime = 0;
    @Unique private static final int SQUISH_BREW_TIME = 400;

    @Inject(method = "tick", at = @At("TAIL"))
    private static void squish$customBrew(World world, BlockPos pos,
                                          net.minecraft.block.BlockState state,
                                          BrewingStandBlockEntity stand,
                                          CallbackInfo ci) {
        if (world.isClient) return;

        BrewingStandBlockEntityMixin self = (BrewingStandBlockEntityMixin)(Object) stand;

        if (self.fuel <= 0) {
            self.squish$customBrewTime = 0;
            self.brewTime = 0;
            return;
        }

        ItemStack ingredient = stand.getStack(3);
        if (!ingredient.isOf(Items.AMETHYST_SHARD)) {
            self.squish$customBrewTime = 0;
            self.brewTime = 0;
            return;
        }

        boolean hasWater = false;
        for (int i = 0; i < 3; i++) {
            ItemStack stack = stand.getStack(i);
            if (stack.isOf(Items.POTION) && PotionUtil.getPotion(stack) == Potions.WATER) {
                hasWater = true;
                break;
            }
        }

        if (!hasWater) {
            self.squish$customBrewTime = 0;
            self.brewTime = 0;
            return;
        }

        if (self.squish$customBrewTime == 0) {
            self.squish$customBrewTime = SQUISH_BREW_TIME;
            self.brewTime = SQUISH_BREW_TIME;
            world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW,
                    SoundCategory.BLOCKS, 0.7F, 1.0F);
        }

        self.squish$customBrewTime--;
        self.brewTime = self.squish$customBrewTime;

        if (self.squish$customBrewTime <= 0) {
            boolean brewed = false;
            for (int i = 0; i < 3; i++) {
                ItemStack stack = stand.getStack(i);
                if (stack.isOf(Items.POTION) && PotionUtil.getPotion(stack) == Potions.WATER) {
                    stand.setStack(i, new ItemStack(SquishItems.SQUISH_ESSENCE));
                    brewed = true;
                }
            }

            if (brewed) {
                self.fuel = Math.max(0, self.fuel - 1);
                ingredient.decrement(1);
                stand.markDirty();
                world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW,
                        SoundCategory.BLOCKS, 0.8F, 1.0F);
            }

            self.squish$customBrewTime = 0;
            self.brewTime = 0;
        }
    }
}