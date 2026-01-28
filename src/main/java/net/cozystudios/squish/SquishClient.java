package net.cozystudios.squish;

//? if fabric {
import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
//? }

//? if forge {
/*import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
*///? }

import net.cozystudios.squish.client.model.SquishModelLayers;
import net.cozystudios.squish.util.SquishId;
import net.cozystudios.squish.client.render.entity.*;
import net.cozystudios.squish.registry.entity.SquishEntities;
import net.cozystudios.squish.mixin.CreativeInventoryScreenAccessor;
import net.cozystudios.squish.mixin.HandledScreenAccessor;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

//? if fabric {
public class SquishClient implements ClientModInitializer {
//? }

//? if forge {
/*@Mod.EventBusSubscriber(modid = Squish.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SquishClient {
*///? }

    //? if fabric {
    private static final Identifier SQUISH_BADGE =
            SquishId.of("squish", "textures/gui/tooltip_badges/squish_badge.png");
     //? }

    //? if fabric {
    @Override
    public void onInitializeClient() {
    //? }

    //? if forge {
    /*@SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    *///? }

        SquishModelLayers.register();

        //? if fabric {
        EntityRendererRegistry.register(RegistryHelper.SQUISH_ESSENCE_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RegistryHelper.EXPLOSIVE_ESSENCE_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RegistryHelper.POPPY_ESSENCE_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RegistryHelper.ENDER_ESSENCE_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RegistryHelper.SKELLY_ESSENCE_ENTITY, FlyingItemEntityRenderer::new);

        EntityRendererRegistry.register(EntityType.CAT, SquishCatRenderer::new);
        EntityRendererRegistry.register(EntityType.CHICKEN, SquishChickenRenderer::new);
        EntityRendererRegistry.register(EntityType.COW, SquishCowRenderer::new);
        EntityRendererRegistry.register(EntityType.PIG, SquishPigRenderer::new);
        EntityRendererRegistry.register(EntityType.RABBIT, SquishRabbitRenderer::new);
        EntityRendererRegistry.register(EntityType.SHEEP, SquishSheepRenderer::new);
        EntityRendererRegistry.register(EntityType.WOLF, SquishWolfRenderer::new);
        EntityRendererRegistry.register(SquishEntities.BABY_CREEPER, SquishBabyCreeperRenderer::new);
        EntityRendererRegistry.register(SquishEntities.BABY_IRON_GOLEM, SquishBabyIronGolemRenderer::new);
        EntityRendererRegistry.register(SquishEntities.BABY_ENDERMAN, SquishBabyEndermanRenderer::new);
        EntityRendererRegistry.register(SquishEntities.BABY_SKELETON, SquishBabySkeletonRenderer::new);
        EntityRendererRegistry.register(EntityType.AXOLOTL, SquishAxolotlRenderer::new);
        EntityRendererRegistry.register(EntityType.DOLPHIN, SquishDolphinRenderer::new);
        EntityRendererRegistry.register(EntityType.HORSE, SquishHorseRenderer::new);
        EntityRendererRegistry.register(EntityType.DONKEY, SquishDonkeyRenderer::new);
        EntityRendererRegistry.register(EntityType.MULE, SquishMuleRenderer::new);
        EntityRendererRegistry.register(EntityType.SQUID, SquishSquidRenderer::new);
        EntityRendererRegistry.register(EntityType.GLOW_SQUID, SquishGlowSquidRenderer::new);
        EntityRendererRegistry.register(EntityType.TURTLE, SquishTurtleRenderer::new);

        // Blocks
        // TODO: move this
        BlockRenderLayerMap.INSTANCE.putBlock(RegistryHelper.MELTED_SUGAR_BLOCK, RenderLayer.getTranslucent());

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (!(screen instanceof CreativeInventoryScreen creative)) return;

            ScreenEvents.afterRender(screen).register((scr, context, mouseX, mouseY, delta) -> {
                ItemGroup selectedTab = CreativeInventoryScreenAccessor.getSelectedTab();
                if (selectedTab == null) return;

                Identifier tabId = Registries.ITEM_GROUP.getId(selectedTab);
                if (tabId == null || !tabId.equals(SquishId.of("squish", "squish_group"))) return;

                int left = ((HandledScreenAccessor) creative).getX();
                int top = ((HandledScreenAccessor) creative).getY();

                context.getMatrices().push();
                context.getMatrices().translate(left + 7, top + 4, 0);
                context.getMatrices().scale(0.35f, 0.35f, 1.0f);
                context.drawTexture(SQUISH_BADGE, 0, 0, 0, 0, 128, 32, 128, 32);
                context.getMatrices().pop();
            });
        });

        //? if <=1.20.4 {
        /*ItemTooltipCallback.EVENT.register((stack, ctx, lines) -> {
        *///?} else {
        ItemTooltipCallback.EVENT.register((stack, ctx, type, lines) -> {
        //?}
            if (lines.isEmpty()) return;

            var id = Registries.ITEM.getId(stack.getItem());
            if (!"squish".equals(id.getNamespace())) return;

            Text original = lines.get(0);
            lines.set(0, net.cozystudios.squish.registry.item.SquishBaseItem.applySquishStyle(original));
        });
        //? }
    }
}
