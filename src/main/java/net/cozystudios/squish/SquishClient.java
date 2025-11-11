package net.cozystudios.squish;

import net.cozystudios.squish.block.SquishBlocks;
import net.cozystudios.squish.item.SquishItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class SquishClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(SquishItems.SQUISH_ESSENCE_ENTITY, FlyingItemEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(SquishBlocks.MELTED_SUGAR_BLOCK, RenderLayer.getTranslucent());
    }
}
