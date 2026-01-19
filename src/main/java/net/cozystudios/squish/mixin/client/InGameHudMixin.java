package net.cozystudios.squish.mixin.client;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.Map;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @ModifyArg(
            method = "renderHeldItemTooltip",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)I"
            ),
            index = 1
    )
    private Text squish$styleHotbarItemName(Text original) {
        if (!(original instanceof MutableText text)) return original;

        String plain = text.getString().toLowerCase();

        final int MAGENTA = 0xDF3C73;
        final int LILAC   = 0xCFBDDF;
        final int POPPY_RED = 0xED1C24;
        final int TNT_RED = 0xDB2E00;
        final int DARK_GRAY = 0x555555;

        boolean isLilac = plain.contains("lollipop")
                || plain.contains("melted sugar")
                || plain.contains("hardened sugar");

        boolean isMagenta = plain.contains("squish")
                || plain.contains("manual");

        boolean isPoppy = plain.contains("poppy");

        boolean isExplosive = plain.contains("explosive");

        boolean isBitter = plain.contains("bitter");

        if (isMagenta) {
            return text.setStyle(text.getStyle()
                    .withBold(true)
                    .withColor(TextColor.fromRgb(MAGENTA)));
        } else if (isLilac) {
            return text.setStyle(text.getStyle()
                    .withBold(true)
                    .withColor(TextColor.fromRgb(LILAC)));
        } else if (isPoppy) {
            return text.setStyle(text.getStyle()
                    .withBold(true)
                    .withColor(TextColor.fromRgb(POPPY_RED)));
        } else if (isExplosive) {
            return text.setStyle(text.getStyle()
                    .withBold(true)
                    .withColor(TextColor.fromRgb(TNT_RED)));
        } else if (isBitter) {
            return text.setStyle(text.getStyle()
                    .withBold(true)
                    .withColor(TextColor.fromRgb(DARK_GRAY)));
        }
        return original;
    }


    @Inject(method = "renderStatusEffectOverlay", at = @At("HEAD"))
    private void squish$hideHaste(DrawContext context, CallbackInfo ci) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        if (player.hasStatusEffect(RegistryHelper.SUGAR_RUSH)) {
            Iterator<Map.Entry<StatusEffect, StatusEffectInstance>> it =
                    player.getActiveStatusEffects().entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<StatusEffect, StatusEffectInstance> e = it.next();
                if (e.getKey() == StatusEffects.HASTE) {
                    it.remove();
                }
            }
        }
    }
}
