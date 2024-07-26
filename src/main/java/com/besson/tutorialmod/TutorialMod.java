package com.besson.tutorialmod;

import com.besson.tutorialmod.block.ModBlocks;
import com.besson.tutorialmod.item.ModItemGroups;
import com.besson.tutorialmod.item.ModItems;
import com.besson.tutorialmod.mixin.GrassColorsMixin;
import com.besson.tutorialmod.tags.ModBlockTags;
import com.besson.tutorialmod.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		// 调用各类的初始化方法
		ModItems.registerModItems();
		ModItemGroups.registerModItemGroups();
		ModBlocks.registerModBlocks();
		ModBlockTags.registerModBlockTags();
		ModLootTableModifiers.modifyLootTable();

//		int[] colorMap = GrassColorsMixin.getColorMap();
//        LOGGER.info("Grass color map length: {}", colorMap.length);
//
//		int[] newColorMap = new int[128];
//		GrassColorsMixin.setColorMap(newColorMap);
//		LOGGER.info("Grass color map length: {}", GrassColorsMixin.getColorMap().length);

//		FuelRegistry.INSTANCE.add(ModItems.ANTHRACITE, 1600);

		LOGGER.info("Hello Fabric world!");
	}
}