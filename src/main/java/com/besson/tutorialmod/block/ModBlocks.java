package com.besson.tutorialmod.block;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.block.custom.BoxBlock;
import com.besson.tutorialmod.block.custom.CornCropBlock;
import com.besson.tutorialmod.block.custom.PolishingMachine;
import com.besson.tutorialmod.block.custom.StrawberryCropBlock;
import com.besson.tutorialmod.block.entity.ModBlockEntities;
import com.besson.tutorialmod.sound.ModSoundEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block ICE_ETHER_BLOCK = register("ice_ether_block", new Block(AbstractBlock.Settings.create()
            .requiresTool().strength(3.0f, 3.0f).sounds(ModSoundEvents.BLOCK_SOUND_GROUP)));
    public static final Block ICE_ETHER_ORE = register("ice_ether_ore", new Block(AbstractBlock.Settings.create().requiresTool().strength(4.5f, 6.0f)));
    public static final Block RAW_ICE_ETHER_BLOCK = register("raw_ice_ether_block", new Block(AbstractBlock.Settings.create().requiresTool().strength(3.0f, 3.0f)));
    public static final Block  ICE_ETHER_STAIRS = register("ice_ether_stairs",
        new StairsBlock(ICE_ETHER_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    public static final Block  ICE_ETHER_SLAB = register("ice_ether_slab",
            new SlabBlock(AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    public static final Block ICE_ETHER_BUTTON = register("ice_ether_button",
            new ButtonBlock(BlockSetType.OAK, 10, AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    public static final Block ICE_ETHER_PRESSURE_PLATE = register("ice_ether_pressure_plate",
            new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    public static final Block ICE_ETHER_FENCE = register("ice_ether_fence",
            new FenceBlock(AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    public static final Block ICE_ETHER_FENCE_GATE = register("ice_ether_fence_gate",
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    public static final Block ICE_ETHER_WALL = register("ice_ether_wall",
            new WallBlock(AbstractBlock.Settings.copy(ICE_ETHER_BLOCK)));
    public static final Block ICE_ETHER_DOOR = register("ice_ether_door",
            new DoorBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(ICE_ETHER_BLOCK).nonOpaque()));
    public static final Block ICE_ETHER_TRAPDOOR = register("ice_ether_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(ICE_ETHER_BLOCK).nonOpaque()));
    public static final Block STRAWBERRY_CROP = Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, "strawberry_crop"),
            new StrawberryCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));
    public static final Block CORN_CROP = Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, "corn_crop"),
            new CornCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));
    public static final Block OIL = Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, "oil"),
            new FluidBlock(ModFluids.OIL, AbstractBlock.Settings.copy(Blocks.WATER)){});
    public static final Block BOX = register("box", new BoxBlock(AbstractBlock.Settings.copy(Blocks.CHEST), () -> ModBlockEntities.BOX));
    public static final Block POLISHING_MACHINE = register("polishing_machine",
            new PolishingMachine(AbstractBlock.Settings.copy(Blocks.STONE)));
    public static void registerBlockItems(String id, Block block) {
        Item item = Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, id), new BlockItem(block, new Item.Settings()));
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
    }
    public static Block register(String id, Block block) {
        registerBlockItems(id, block);
        return Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, id), block);
    }
    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Blocks");
    }
}
