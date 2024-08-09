package com.besson.tutorialmod.block.entity;

import com.besson.tutorialmod.data.PolishingMachineData;
import com.besson.tutorialmod.item.ModItems;
import com.besson.tutorialmod.screen.PolishingMachineScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PolishingMachineBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<PolishingMachineData>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    public PolishingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POLISHING_MACHINE_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {

            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> PolishingMachineBlockEntity.this.progress;
                    case 1 -> PolishingMachineBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> PolishingMachineBlockEntity.this.progress = value;
                    case 1 -> PolishingMachineBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.polishing_machine");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PolishingMachineScreenHandler(syncId, playerInventory, this.propertyDelegate, this);
    }

    @Override
    public PolishingMachineData getScreenOpeningData(ServerPlayerEntity player) {
        return new PolishingMachineData(pos);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.inventory, false, registryLookup);
        nbt.putInt("polishing_machine", progress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, this.inventory, registryLookup);
        progress = nbt.getInt("polishing_machine");
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }
    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }
        if (isOutputSlotAvailable()) {
            if (hasRecipe()) {
                increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished()) {
                    craftItem();
                    resetProgress();
                }
            } else {
                resetProgress();
            }
        } else {
            resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ModItems.ICE_ETHER);
        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        ItemStack result = new ItemStack(ModItems.ICE_ETHER);
        boolean hasInput = getStack(INPUT_SLOT).getItem() == Items.ICE;
        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertIntoOutputSlot(result.getItem());
    }

    private boolean canInsertIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getItem() == item;
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= this.getMaxCountPerStack();
    }

    private boolean isOutputSlotAvailable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getCount() <= this.getMaxCountPerStack();
    }
}
