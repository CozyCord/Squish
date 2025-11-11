package net.cozystudios.squish;

import net.cozystudios.squish.item.SquishItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class SquishClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(SquishItems.SQUISH_ESSENCE_ENTITY, FlyingItemEntityRenderer::new);
    }
}
