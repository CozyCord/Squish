package net.cozystudios.squish.registry.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SkellyCandyItem extends SquishBaseItem {
    public SkellyCandyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            int duration = 600;
            StatusEffectInstance existingEffect = player.getStatusEffect(StatusEffects.RESISTANCE);
            if (existingEffect != null) {
                int newDuration = existingEffect.getDuration() + duration;
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, newDuration, 0));
            } else {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, duration, 0));
            }
        }
        return super.finishUsing(stack, world, user);
    }
}
