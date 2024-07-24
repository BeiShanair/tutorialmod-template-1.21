package com.besson.tutorialmod.block.custom;

import com.besson.tutorialmod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class CornCropBlock extends CropBlock {
    public static final int FIRST_STAGE_AGE = 7;
    public static final int SECOND_STAGE_AGE = 1;
    public static final IntProperty AGE = IntProperty.of("age", 0, 8);
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)
    };
    public CornCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[state.get(AGE)];
    }

    @Override
    public int getMaxAge() {
        return FIRST_STAGE_AGE + SECOND_STAGE_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.CORN_SEEDS;
    }

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState block = world.getBlockState(pos.down());
        return super.canPlaceAt(state, world, pos) ||
                block.isOf(this) && block.get(AGE) == 7;
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int nextAge = this.getAge(state) + this.getGrowthAmount(world);
        int maxAge = this.getMaxAge();
        if (nextAge > maxAge) {
            nextAge = maxAge;
        }
        BlockState upState = world.getBlockState(pos.up());
        if (this.getAge(state) == 7 && upState.isOf(Blocks.AIR)) {
            world.setBlockState(pos.up(), this.withAge(nextAge), Block.NOTIFY_LISTENERS);
        } else {
            world.setBlockState(pos, this.withAge(nextAge - 1), Block.NOTIFY_LISTENERS);
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int age = this.getAge(state);
        float f = getAvailableMoisture(this, world, pos);
        if (world.getBaseLightLevel(pos, 0) >= 9 && random.nextInt((int)(25.0F / f) + 1) == 0
        && age < this.getMaxAge()) {
            if (age == FIRST_STAGE_AGE) {
                BlockState upState = world.getBlockState(pos.up());
                if (upState.isOf(Blocks.AIR)) {
                    world.setBlockState(pos.up(), this.withAge(age + 1), Block.NOTIFY_LISTENERS);
                }
            } else {
                world.setBlockState(pos, this.withAge(age + 1), Block.NOTIFY_LISTENERS);
            }
        }
    }
}
