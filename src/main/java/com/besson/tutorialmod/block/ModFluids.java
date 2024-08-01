package com.besson.tutorialmod.block;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.block.custom.OilFluid;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static final FlowableFluid FLOWING_OIL = register("flowing_oil", new OilFluid.Flowing());
    public static final FlowableFluid OIL = register("oil", new OilFluid.Still());
    private static <T extends Fluid> T register(String id, T value) {
        return Registry.register(Registries.FLUID, Identifier.of(TutorialMod.MOD_ID, id), value);
    }

    static {
        for (Fluid fluid : Registries.FLUID) {
            for (FluidState fluidState : fluid.getStateManager().getStates()) {
                Fluid.STATE_IDS.add(fluidState);
            }
        }
    }
    public static void registerModFluids() {

    }
}
