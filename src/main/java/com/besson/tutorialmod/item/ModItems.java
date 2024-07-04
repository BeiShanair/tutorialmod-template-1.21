package com.besson.tutorialmod.item;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.item.custom.Prospector;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // 注册物品
    public static final Item ICE_ETHER = registerItems("ice_ether", new Item(new Item.Settings()));
    public static final Item RAW_ICE_ETHER = registerItems("raw_ice_ether", new Item(new Item.Settings()));
    public static final Item CHEESE = registerItems("cheese", new Item(new Item.Settings().food(ModFoodComponents.CHEESE)));
    public static final Item STRAWBERRY = registerItems("strawberry", new Item(new Item.Settings().food(ModFoodComponents.STRAWBERRY)));
    public static final Item ANTHRACITE = registerItems("anthracite", new Item(new Item.Settings()));
    public static final Item PROSPECTOR = registerItems("prospector", new Prospector(new Item.Settings().maxDamage(127)));

    private static Item registerItems(String id, Item item){
        // 使用原版的注册方法
//        return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(TutorialMod.MOD_ID, id)), item);
        // 由原版简化的方法
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, id), item);
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
