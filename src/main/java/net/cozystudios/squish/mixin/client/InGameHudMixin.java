package net.cozystudios.squish.mixin.client;

import net.cozystudios.squish.Squish;

//? if fabric {
import net.cozystudios.squish.loader.fabric.RegistryHelper;
//? }

//? if forge {
/*import net.cozystudios.squish.loader.forge.RegistryHelper;
*///? }
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
//? if <=1.20.4 {
/*import net.minecraft.entity.effect.StatusEffect;
*///?}
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
//? if >1.20.4 {
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.client.render.RenderTickCounter;
//?}
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

    //? if <=1.20.4 {
    /*@ModifyArg(
            method = "renderHeldItemTooltip",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)I"
            ),
            index = 1
    )
    private Text squish$styleHotbarItemName(Text original) {
        if (!(original instanceof MutableText text)) return original;

        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return original;

        ItemStack heldItem = player.getMainHandStack();
        Identifier itemId = Registries.ITEM.getId(heldItem.getItem());
        if (!itemId.getNamespace().equals(Squish.MOD_ID)) {
            return original;
        }

        String plain = text.getString().toLowerCase();

        final int MAGENTA = 0xDF3C73;
        final int LILAC   = 0xCFBDDF;
        final int POPPY_RED = 0xED1C24;
        final int TNT_RED = 0xDB2E00;
        final int DARK_GRAY = 0x555555;
        final int ENDER_PURPLE = 0xCC00FA;

        boolean isLilac = plain.contains("lollipop")
                || plain.contains("melted sugar")
                || plain.contains("hardened sugar");

        boolean isMagenta = plain.contains("squish")
                || plain.contains("manual");

        boolean isPoppy = plain.contains("poppy");

        boolean isExplosive = plain.contains("explosive");

        boolean isBitter = plain.contains("bitter");

        boolean isEnder = plain.contains("ender");

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
        } else if (isEnder) {
            return text.setStyle(text.getStyle()
                    .withBold(true)
                    .withColor(TextColor.fromRgb(ENDER_PURPLE)));
        }
        return original;
    }
    *///?}

    //? if <=1.20.4 {
    /*@Inject(method = "renderStatusEffectOverlay", at = @At("HEAD"))
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
    *///?} else {
    @Inject(method = "renderStatusEffectOverlay", at = @At("HEAD"))
    private void squish$hideHaste(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        if (player.hasStatusEffect(RegistryHelper.SUGAR_RUSH)) {
            Iterator<Map.Entry<RegistryEntry<net.minecraft.entity.effect.StatusEffect>, StatusEffectInstance>> it =
                    player.getActiveStatusEffects().entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<RegistryEntry<net.minecraft.entity.effect.StatusEffect>, StatusEffectInstance> e = it.next();
                if (e.getKey() == StatusEffects.HASTE) {
                    it.remove();
                }
            }
        }
    }
    //?}
}
