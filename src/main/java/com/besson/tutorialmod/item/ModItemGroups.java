package com.besson.tutorialmod.item;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    // 原版物品栏注册方法
//    public static final RegistryKey<ItemGroup> TUTORIAL_GROUP = register("tutorial_group");
//    private static RegistryKey<ItemGroup> register(String id) {
//        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(TutorialMod.MOD_ID, id));
//    }
//    public static void registerModItemGroups() {
//        Registry.register(Registries.ITEM_GROUP, TUTORIAL_GROUP,
//                ItemGroup.create(ItemGroup.Row.TOP, 7)
//                                .displayName(Text.translatable("itemGroup.tutorial_group"))
//                                        .icon(() -> new ItemStack(ModItems.ICE_ETHER))
//                        .entries((displayContext, entries) -> {
//                            entries.add(ModItems.ICE_ETHER);
//                        }).build());
//        TutorialMod.LOGGER.info("Registering Item Groups");
//    }
    // 利用返回值为ItemGroup，直接使用static final变量进行注册
    public static final ItemGroup TUTORIAL_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(TutorialMod.MOD_ID, "tutorial_group"),
        ItemGroup.create(null, -1).displayName(Text.translatable("itemGroup.tutorial_group"))
                .icon(() -> new ItemStack(ModItems.ICE_ETHER))
                .entries((displayContext, entries) -> {
                    entries.add(ModItems.ICE_ETHER);
                    entries.add(ModItems.FIRE_ETHER);
                    entries.add(Blocks.BRICKS);
                    entries.add(Items.DIAMOND);
                    entries.add(ModItems.RAW_ICE_ETHER);
                    entries.add(ModItems.CHEESE);
                    entries.add(ModItems.STRAWBERRY);
                    entries.add(ModItems.ANTHRACITE);
                    entries.add(ModItems.PROSPECTOR);
                    entries.add(ModItems.PLATE);

                    entries.add(ModBlocks.ICE_ETHER_BLOCK);
                    entries.add(ModBlocks.ICE_ETHER_ORE);
                    entries.add(ModBlocks.RAW_ICE_ETHER_BLOCK);

                    entries.add(ModBlocks.ICE_ETHER_STAIRS);
                    entries.add(ModBlocks.ICE_ETHER_SLAB);
                    entries.add(ModBlocks.ICE_ETHER_BUTTON);
                    entries.add(ModBlocks.ICE_ETHER_PRESSURE_PLATE);
                    entries.add(ModBlocks.ICE_ETHER_FENCE);
                    entries.add(ModBlocks.ICE_ETHER_FENCE_GATE);
                    entries.add(ModBlocks.ICE_ETHER_WALL);
                    entries.add(ModBlocks.ICE_ETHER_DOOR);
                    entries.add(ModBlocks.ICE_ETHER_TRAPDOOR);

                    entries.add(ModItems.FIRE_ETHER_SWORD);
                    entries.add(ModItems.FIRE_ETHER_SHOVEL);
                    entries.add(ModItems.FIRE_ETHER_PICKAXE);
                    entries.add(ModItems.FIRE_ETHER_AXE);
                    entries.add(ModItems.FIRE_ETHER_HOE);

                    entries.add(ModItems.ICE_ETHER_HELMET);
                    entries.add(ModItems.ICE_ETHER_CHESTPLATE);
                    entries.add(ModItems.ICE_ETHER_LEGGINGS);
                    entries.add(ModItems.ICE_ETHER_BOOTS);

                    entries.add(ModItems.HAT);
                    entries.add(ModItems.STRAWBERRY_SEEDS);
                    entries.add(ModItems.CORN_SEEDS);
                    entries.add(ModItems.CORN);
                    entries.add(ModItems.TEST_MUSIC_DISC);
                }).build());
    // 初始化方法
    public static void registerModItemGroups() {
        TutorialMod.LOGGER.info("Registering Item Groups");
    }
}
