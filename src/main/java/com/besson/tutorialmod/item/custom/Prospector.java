package com.besson.tutorialmod.item.custom;

import com.besson.tutorialmod.sound.ModSoundEvents;
import com.besson.tutorialmod.tags.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class Prospector extends Item {
    public Prospector(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();

        if (!world.isClient()) {
            boolean foundBlock = false;
            if (!Screen.hasShiftDown()) {
                // 实现模糊搜索
                for (int i = 0; i <= pos.getY() + 64; i++) {
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            BlockPos pos1 = pos.down(i).north(j).east(k);
                            BlockState blockState = world.getBlockState(pos1);
                            String name = blockState.getBlock().getName().getString();

                            if (isRightBlock(blockState)) {
                                player.sendMessage(Text.of("Found" + name + "!"));
                                world.playSound(null, pos, ModSoundEvents.PROSPECTOR_FOUND_ORE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                                foundBlock = true;
                                break;
                            }
                        }
                    }
                }
                if (!foundBlock) {
                    player.sendMessage(Text.of("No ore found!"));
                }
            } else {
                // 实现精确搜索
                for (int i = 0; i <= pos.getY() + 64; i++) {
                    BlockPos pos1 = pos.down(i);
                    BlockState blockState = world.getBlockState(pos1);
                    String name = blockState.getBlock().getName().getString();

                    if (isRightBlock(blockState)) {
                        player.sendMessage(Text.of("Found" + name + "!"));
                        world.playSound(null, pos, ModSoundEvents.PROSPECTOR_FOUND_ORE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        foundBlock = true;
                        break;
                    }
                }
                if (!foundBlock) {
                    player.sendMessage(Text.of("No ore found!"));
                }
            }
            context.getStack().damage(1, player, EquipmentSlot.MAINHAND);
            return ActionResult.SUCCESS;
        }
        return super.useOnBlock(context);
    }

    private boolean isRightBlock(BlockState blockState) {
        if (blockState.isIn(ModBlockTags.ORE_LIST)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.tutorialmod.prospector.shift_tooltip"));
        } else {
            tooltip.add(Text.translatable("item.tutorialmod.prospector.tooltip"));
        }
    }
}
