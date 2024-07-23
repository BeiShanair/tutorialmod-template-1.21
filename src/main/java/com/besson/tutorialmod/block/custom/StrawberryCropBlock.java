package com.besson.tutorialmod.block.custom;

import com.besson.tutorialmod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class StrawberryCropBlock extends CropBlock {
    public static final int MAX_AGE = 5;
    public static final IntProperty AGE = Properties.AGE_5;
    public StrawberryCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.STRAWBERRY_SEEDS;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT) || floor.isOf(Blocks.FARMLAND);
    }
}
