package com.besson.tutorialmod.block;

import com.besson.tutorialmod.TutorialMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block ICE_ETHER_BLOCK = register("ice_ether_block", AbstractBlock.Settings.create().requiresTool().strength(3.0f, 3.0f));
    public static final Block ICE_ETHER_ORE = register("ice_ether_ore", AbstractBlock.Settings.create().requiresTool().strength(4.5f, 6.0f));
    public static final Block RAW_ICE_ETHER_BLOCK = register("raw_ice_ether_block", AbstractBlock.Settings.create().requiresTool().strength(3.0f, 3.0f));

    //region 1.21.2+ 的写法
    public static Block register(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));
        Block block = (Block)factory.apply(settings.registryKey(key));
        registerBlockItems(name, block);
        return Registry.register(Registries.BLOCK, key, block);
    }

    // 1.21.2+方块物品注册方法
    public static void registerBlockItems(String name, Block block) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name));
        BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(key).useBlockPrefixedTranslationKey());
        Registry.register(Registries.ITEM, key, blockItem);
    }

    // 一个简化的重载方法
    public static Block register(String name, AbstractBlock.Settings settings) {
        return register(name, Block::new, settings);
    }
    // endregion
    
    //region 1.21 ~ 1.21.1 的写法
//    public static void registerBlockItems(String id, Block block) {
//        Item item = Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, id), new BlockItem(block, new Item.Settings()));
//        if (item instanceof BlockItem) {
//            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
//        }
//    }
//    public static Block register(String id, Block block) {
//        registerBlockItems(id, block);
//        return Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, id), block);
//    }
    // endregion
    
    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Blocks");
    }
}
