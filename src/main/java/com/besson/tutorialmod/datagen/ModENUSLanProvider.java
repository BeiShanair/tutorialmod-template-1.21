package com.besson.tutorialmod.datagen;

import com.besson.tutorialmod.block.ModBlocks;
import com.besson.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModENUSLanProvider extends FabricLanguageProvider {
    public ModENUSLanProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.ICE_ETHER, "Ice Ether");
        translationBuilder.add(ModItems.FIRE_ETHER, "Fire Ether");
        translationBuilder.add(ModItems.RAW_ICE_ETHER, "Raw Ice Ether");
        translationBuilder.add(ModItems.CHEESE, "Cheese");
        translationBuilder.add(ModItems.STRAWBERRY, "Strawberry");
        translationBuilder.add(ModItems.ANTHRACITE, "Anthracite");
        translationBuilder.add(ModItems.PROSPECTOR, "Prospector");
        translationBuilder.add(ModItems.PLATE, "Plate");
        translationBuilder.add(ModItems.FIRE_ETHER_SWORD, "Fire Ether Sword");
        translationBuilder.add(ModItems.FIRE_ETHER_SHOVEL, "Fire Ether Shovel");
        translationBuilder.add(ModItems.FIRE_ETHER_PICKAXE, "Fire Ether Pickaxe");
        translationBuilder.add(ModItems.FIRE_ETHER_AXE, "Fire Ether Axe");
        translationBuilder.add(ModItems.FIRE_ETHER_HOE, "Fire Ether Hoe");
        translationBuilder.add(ModItems.ICE_ETHER_HELMET, "Ice Ether Helmet");
        translationBuilder.add(ModItems.ICE_ETHER_CHESTPLATE, "Ice Ether Chestplate");
        translationBuilder.add(ModItems.ICE_ETHER_LEGGINGS, "Ice Ether Leggings");
        translationBuilder.add(ModItems.ICE_ETHER_BOOTS, "Ice Ether Boots");
        translationBuilder.add(ModItems.HAT, "Hat");
        translationBuilder.add(ModItems.STRAWBERRY_SEEDS, "Strawberry Seeds");
        translationBuilder.add(ModItems.CORN_SEEDS, "Corn Seeds");
        translationBuilder.add(ModItems.CORN, "Corn");
        translationBuilder.add(ModItems.TEST_MUSIC_DISC, "Test Music Disc");

        translationBuilder.add(ModBlocks.ICE_ETHER_BLOCK, "Ice Ether Block");
        translationBuilder.add(ModBlocks.ICE_ETHER_ORE, "Ice Ether Ore");
        translationBuilder.add(ModBlocks.RAW_ICE_ETHER_BLOCK, "Raw Ice Ether Block");

        translationBuilder.add(ModBlocks.ICE_ETHER_STAIRS, "Ice Ether Stairs");
        translationBuilder.add(ModBlocks.ICE_ETHER_SLAB, "Ice Ether Slab");
        translationBuilder.add(ModBlocks.ICE_ETHER_BUTTON, "Ice Ether Button");
        translationBuilder.add(ModBlocks.ICE_ETHER_PRESSURE_PLATE, "Ice Ether Pressure Plate");
        translationBuilder.add(ModBlocks.ICE_ETHER_FENCE, "Ice Ether Fence");
        translationBuilder.add(ModBlocks.ICE_ETHER_FENCE_GATE, "Ice Ether Fence Gate");
        translationBuilder.add(ModBlocks.ICE_ETHER_WALL, "Ice Ether Wall");
        translationBuilder.add(ModBlocks.ICE_ETHER_DOOR, "Ice Ether Door");
        translationBuilder.add(ModBlocks.ICE_ETHER_TRAPDOOR, "Ice Ether Trapdoor");

        translationBuilder.add("itemGroup.tutorial_group", "Tutorial Group");

        translationBuilder.add("item.tutorialmod.prospector.shift_tooltip", "A tool used to find ores.");
        translationBuilder.add("item.tutorialmod.prospector.tooltip", "Hold \u00A76SHIFT\u00A7r for more information.");

        translationBuilder.add("entity.minecraft.villager.ice_ether_master", "Ice Ether Master");

        translationBuilder.add("sounds.tutorialmod.prospector_found_ore", "Prospector Found Ore");
        translationBuilder.add("sounds.tutorialmod.block_break", "Block Break");
        translationBuilder.add("sounds.tutorialmod.block_step", "Block Step");
        translationBuilder.add("sounds.tutorialmod.block_place", "Block Place");
        translationBuilder.add("sounds.tutorialmod.block_hit", "Block Hit");
        translationBuilder.add("sounds.tutorialmod.block_fall", "Block Fall");

        translationBuilder.add("jukebox_song.tutorialmod.test", "Test");
    }
}
