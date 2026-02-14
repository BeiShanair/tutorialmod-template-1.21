package com.besson.tutorialmod.item;

import com.besson.tutorialmod.TutorialMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    // 注册物品
    public static final Item ICE_ETHER = registerItems("ice_ether");
    public static final Item RAW_ICE_ETHER = registerItems("raw_ice_ether");

    // 1.21 ~ 1.21.1的写法
    private static Item registerItems(String id, Item item){
        // 使用原版的注册方法
//        return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(TutorialMod.MOD_ID, id)), item);
        // 由原版简化的方法
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, id), item);
    }
    
    // 1.21.2+ 的基于原版方法的写法
    public static Item registerItems(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name));
        Item item = (Item)factory.apply(settings.registryKey(key));

        return Registry.register(Registries.ITEM, key, item);
    }
    // 1.21.2+ 相关重载方法
    public static Item registerItems(String name) {
        return registerItems(name, Item::new, new Item.Settings());
    }
    public static Item registerItems(String name, Function<Item.Settings, Item> factory) {
        return registerItems(name, factory, new Item.Settings());
    }
    
    // 使用Fabric的ItemGroupEntries添加物品
    private static void addItemToIG(FabricItemGroupEntries fabricItemGroupEntries){
        fabricItemGroupEntries.add(ICE_ETHER);
    }
    // 初始化方法
    public static void registerModItems(){
        // 通过Fabric的ItemGroupEvents添加物品
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToIG);
        TutorialMod.LOGGER.info("Registering Items");
    }
}
