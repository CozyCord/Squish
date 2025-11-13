package net.cozystudios.squish.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.cozystudios.squish.mixin.CreativeInventoryScreenAccessor;
import net.cozystudios.squish.mixin.HandledScreenAccessor;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin {

    @Unique
    private static final Identifier SQUISH_GUI =
            new Identifier("squish", "textures/gui/squish_creative.png");

    @Inject(
            method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V",
            at = @At("TAIL")
    )
    private void squish$drawBackground(DrawContext ctx, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        ItemGroup selected = CreativeInventoryScreenAccessor.getSelectedTab();
        if (selected == null) return;

        Identifier id = Registries.ITEM_GROUP.getId(selected);
        if (id == null || !id.equals(new Identifier("squish", "squish_group"))) return;

        int x = ((HandledScreenAccessor) this).getX();
        int y = ((HandledScreenAccessor) this).getY();
        int w = ((HandledScreenAccessor) this).getBackgroundWidth();
        int h = ((HandledScreenAccessor) this).getBackgroundHeight();

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, SQUISH_GUI);
        ctx.drawTexture(SQUISH_GUI, x, y, 0, 0, w, h, 256, 256);
    }
}