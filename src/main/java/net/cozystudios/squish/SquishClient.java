package net.cozystudios.squish;

import net.cozystudios.squish.block.SquishBlocks;
import net.cozystudios.squish.client.tooltip.SquishBadgeTooltipComponent;
import net.cozystudios.squish.client.tooltip.SquishBadgeTooltipData;
import net.cozystudios.squish.item.SquishItems;
import net.cozystudios.squish.mixin.CreativeInventoryScreenAccessor;
import net.cozystudios.squish.mixin.HandledScreenAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;

public class SquishClient implements ClientModInitializer {

    private static final Identifier SQUISH_BADGE =
            new Identifier("squish", "textures/gui/tooltip_badges/squish_badge.png");

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(SquishItems.SQUISH_ESSENCE_ENTITY, FlyingItemEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(SquishBlocks.MELTED_SUGAR_BLOCK, RenderLayer.getTranslucent());

        TooltipComponentCallback.EVENT.register(data -> {
            if (data instanceof SquishBadgeTooltipData d) {
                return new SquishBadgeTooltipComponent(d);
            }
            if (data != null && data.getClass().getName().contains("ModNameTooltipData")) {
                return null;
            }
            return null;
        });

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (!(screen instanceof CreativeInventoryScreen creative)) return;

            ScreenEvents.afterRender(screen).register((scr, context, mouseX, mouseY, delta) -> {
                ItemGroup selectedTab = CreativeInventoryScreenAccessor.getSelectedTab();
                if (selectedTab == null) return;

                Identifier tabId = Registries.ITEM_GROUP.getId(selectedTab);
                if (tabId == null || !tabId.equals(new Identifier("squish", "squish_group"))) return;

                int left = ((HandledScreenAccessor) creative).getX();
                int top = ((HandledScreenAccessor) creative).getY();

                context.getMatrices().push();
                context.getMatrices().translate(left + 7, top + 4, 0);
                context.getMatrices().scale(0.35f, 0.35f, 1.0f);
                context.drawTexture(SQUISH_BADGE, 0, 0, 0, 0, 128, 32, 128, 32);
                context.getMatrices().pop();
            });
        });

        ItemTooltipCallback.EVENT.register((stack, ctx, lines) -> {
            if (lines.isEmpty()) return;

            var id = Registries.ITEM.getId(stack.getItem());
            if (!"squish".equals(id.getNamespace())) return;

            Text original = lines.get(0);
            String plain = original.getString();

            MutableText name = Text.literal(plain).styled(s -> s.withBold(false));

            final int MAGENTA = 0xDF3C73;
            final int LILAC   = 0xCFBDDF;

            String path = id.getPath();

            boolean isLilac = path.equals("lollipop")
                    || path.equals("melted_sugar")
                    || path.equals("hardened_sugar")
                    || path.equals("hardened_sugar_shard");

            if (path.equals("squish_candy")
                    || path.equals("squish_essence")
                    || path.equals("squish_guidebook"))
            {
                name = name.styled(style -> style.withColor(TextColor.fromRgb(MAGENTA)));
            }
            else if (isLilac)
            {
                name = name.styled(style -> style.withColor(TextColor.fromRgb(LILAC)));
            }

            lines.set(0, name);
        });
    }
}