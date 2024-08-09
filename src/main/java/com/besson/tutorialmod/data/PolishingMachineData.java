package com.besson.tutorialmod.data;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record PolishingMachineData(BlockPos pos) implements BlockPosPayload {
    public static final PacketCodec<RegistryByteBuf, PolishingMachineData> CODEC=
            PacketCodec.tuple(BlockPos.PACKET_CODEC, PolishingMachineData::pos, PolishingMachineData::new);
}
