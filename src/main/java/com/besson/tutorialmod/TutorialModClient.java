package com.besson.tutorialmod;

import com.besson.tutorialmod.block.ModBlocks;
import com.besson.tutorialmod.block.ModFluids;
import com.besson.tutorialmod.screen.ModScreenHandlers;
import com.besson.tutorialmod.screen.PolishingMachineScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ICE_ETHER_DOOR, RenderLayer.getCutout());
//        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ICE_ETHER_TRAPDOOR, RenderLayer.getCutout());
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.OIL, ModFluids.FLOWING_OIL,
                new SimpleFluidRenderHandler(
                        Identifier.of("minecraft:block/water_still"),
                        Identifier.of("minecraft:block/water_flow"),
                        0x42413b
                ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.OIL, ModFluids.FLOWING_OIL);
        HandledScreens.register(ModScreenHandlers.POLISHING_MACHINE_SCREEN_HANDLER, PolishingMachineScreen::new);
    }
}
