package com.besson.tutorialmod.mixin;

import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EndermanEntity.class)
public interface EndermanEntityMixin {
    @Invoker("teleportTo")
    boolean invokeTeleportTo(double x, double y, double z);
}
