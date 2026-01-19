//? if fabric {
package net.cozystudios.squish.loader.fabric.datagen;

import net.cozystudios.squish.loader.fabric.RegistryHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class SquishBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public SquishBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(RegistryHelper.HARDENED_SUGAR_BLOCK);
    }
}
//? }
