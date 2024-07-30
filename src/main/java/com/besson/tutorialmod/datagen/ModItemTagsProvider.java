package com.besson.tutorialmod.datagen;

import com.besson.tutorialmod.item.ModItems;
import com.besson.tutorialmod.tags.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagProvider.ItemTagProvider {


    public ModItemTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModItemTags.SUGAR_TAG)
                .add(Items.BEETROOT)
                .add(ModItems.CHEESE)
                .add(ModItems.STRAWBERRY);
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.ICE_ETHER_HELMET, ModItems.ICE_ETHER_CHESTPLATE, ModItems.ICE_ETHER_LEGGINGS, ModItems.ICE_ETHER_BOOTS);
        getOrCreateTagBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ModItems.TEST_MUSIC_DISC);
    }
}
