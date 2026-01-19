package net.cozystudios.squish.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PoppyCandyItem extends SquishBaseItem {
    public PoppyCandyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            // Give regeneration for 30 seconds (600 ticks)
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 1));
        }
        return super.finishUsing(stack, world, user);
    }
}
