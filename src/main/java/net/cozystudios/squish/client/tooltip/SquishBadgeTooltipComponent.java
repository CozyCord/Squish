package net.cozystudios.squish.client.tooltip;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.util.Identifier;

public class SquishBadgeTooltipComponent implements TooltipComponent {
    private final Identifier texture;
    private final int w, h;

    public SquishBadgeTooltipComponent(SquishBadgeTooltipData data) {
        this.texture = data.texture();
        this.w = data.width();
        this.h = data.height();
    }

    @Override
    public int getHeight() { return h; }

    @Override
    public int getWidth(net.minecraft.client.font.TextRenderer tr) { return w; }

    @Override
    public void drawItems(net.minecraft.client.font.TextRenderer tr, int x, int y, DrawContext ctx) {
        float scale = 0.75f;
        ctx.getMatrices().push();
        ctx.getMatrices().translate(x, y, 0);
        ctx.getMatrices().scale(scale, scale, 1);

        ctx.drawTexture(texture, 0, 0, 0, 0, w, h, w, h);

        ctx.getMatrices().pop();
    }
}