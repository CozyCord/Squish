package net.cozystudios.squish.registry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.math.random.Random;

import java.awt.Color;
import java.util.UUID;

public class SugarRushStatusEffect extends StatusEffect {
    private static final UUID SPEED_MODIFIER_UUID = UUID.fromString("6a6c8d24-8f3b-4f1c-bc9c-9a0e3dbb2f63");
    private static final UUID ATTACK_SPEED_UUID = UUID.fromString("2c2d5c44-5aa4-40cc-a8b1-1a8b1df59b22");

    public SugarRushStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xdf3c73);

        this.addAttributeModifier(
                EntityAttributes.GENERIC_MOVEMENT_SPEED,
                SPEED_MODIFIER_UUID.toString(),
                0.90D,
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL
        );

        this.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                ATTACK_SPEED_UUID.toString(),
                0.50D,
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    @Override
    public int getColor() {
        float hue = (System.currentTimeMillis() % 1200L) / 1200f;
        return Color.HSBtoRGB(hue, 0.9f, 1.0f) & 0xFFFFFF;
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);

        if (!entity.getWorld().isClient) {
            entity.removeStatusEffect(StatusEffects.HASTE);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getWorld().isClient) {
            Random rand = entity.getWorld().getRandom();

            float hue = (System.currentTimeMillis() % 3000L) / 3000f;
            int rgb = Color.HSBtoRGB(hue, 0.9f, 1.0f);
            float r = ((rgb >> 16) & 0xFF) / 255f;
            float g = ((rgb >> 8) & 0xFF) / 255f;
            float b = (rgb & 0xFF) / 255f;

            entity.getWorld().addParticle(
                    new net.minecraft.particle.DustParticleEffect(new org.joml.Vector3f(r, g, b), 1.0f),
                    entity.getX() + (rand.nextDouble() - 0.5) * 0.6,
                    entity.getBodyY(0.6) + rand.nextDouble() * 0.5,
                    entity.getZ() + (rand.nextDouble() - 0.5) * 0.6,
                    0, 0.02, 0
            );
        }
    }

    @Override
    public Text getName() {
        float hue = (System.currentTimeMillis() % 3000L) / 3000f;
        int rgb = java.awt.Color.HSBtoRGB(hue, 0.9f, 1.0f) & 0xFFFFFF;

        return Text.literal("Sugar Rush")
                .styled(s -> s.withBold(true).withColor(TextColor.fromRgb(rgb)));
    }
}
