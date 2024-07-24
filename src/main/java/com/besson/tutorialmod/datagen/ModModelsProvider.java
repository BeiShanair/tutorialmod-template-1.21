package com.besson.tutorialmod.datagen;

import com.besson.tutorialmod.block.ModBlockFamilies;
import com.besson.tutorialmod.block.ModBlocks;
import com.besson.tutorialmod.block.custom.CornCropBlock;
import com.besson.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.ArmorItem;
import net.minecraft.state.property.Properties;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        ModBlockFamilies.getFamilies()
                        .filter(BlockFamily::shouldGenerateModels).forEach(
                                blockFamily -> blockStateModelGenerator.registerCubeAllModelTexturePool(blockFamily.getBaseBlock())
                                        .family(blockFamily));
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_ICE_ETHER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ICE_ETHER_ORE);

        blockStateModelGenerator.registerCrop(ModBlocks.STRAWBERRY_CROP, Properties.AGE_5, 0, 1, 2, 3, 4, 5);

        blockStateModelGenerator.blockStateCollector.accept(
                        VariantsBlockStateSupplier.create(ModBlocks.CORN_CROP)
                                .coordinate(BlockStateVariantMap.create(CornCropBlock.AGE)
                                                .register(stage -> BlockStateVariant.create()
                                                                .put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(
                                                                        ModBlocks.CORN_CROP, "_stage" + stage, Models.CROSS, TextureMap::cross))
                                                )
                                )
                );

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ICE_ETHER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIRE_ETHER, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_ICE_ETHER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHEESE, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRAWBERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORN_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANTHRACITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PROSPECTOR, Models.GENERATED);

        itemModelGenerator.register(ModItems.FIRE_ETHER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_ETHER_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_ETHER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_ETHER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_ETHER_HOE, Models.HANDHELD);

        itemModelGenerator.registerArmor((ArmorItem) ModItems.ICE_ETHER_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.ICE_ETHER_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.ICE_ETHER_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.ICE_ETHER_BOOTS);

    }
}
