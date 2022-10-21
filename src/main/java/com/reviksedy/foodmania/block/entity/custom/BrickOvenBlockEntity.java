package com.reviksedy.foodmania.block.entity.custom;

import com.reviksedy.foodmania.block.custom.BrickOvenBlock;
import com.reviksedy.foodmania.block.entity.ModBlockEntities;
import com.reviksedy.foodmania.recipe.BrickOvenRecipe;
import com.reviksedy.foodmania.screen.BrickOvenMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Random;

import static com.reviksedy.foodmania.block.custom.BrickOvenBlock.LIT;

public class BrickOvenBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    protected final ContainerData data2;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();



    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 144;

    private int burnTime = 360;
    private int maxBurnTime = 360;

    public BrickOvenBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.BRICK_OVEN_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return BrickOvenBlockEntity.this.progress;
                    case 1: return BrickOvenBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: BrickOvenBlockEntity.this.progress = value; break;
                    case 1: BrickOvenBlockEntity.this.maxProgress = value; break;
                }
            }

            public int getCount() {
                return 2;
            }
        };
        this.data2 = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return BrickOvenBlockEntity.this.burnTime;
                    case 1: return BrickOvenBlockEntity.this.maxBurnTime;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: BrickOvenBlockEntity.this.burnTime = value; break;
                    case 1: BrickOvenBlockEntity.this.maxBurnTime = value; break;
                }
            }

            public int getCount() {
                return 2;
            }

        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Brick Oven");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new BrickOvenMenu(pContainerId, pInventory, this, this.data, this.data2);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("brick_oven.progress", progress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("brick_oven.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, BrickOvenBlockEntity pBlockEntity) {

        if (pBlockEntity.burnTime >= pBlockEntity.maxBurnTime && hasCoalInFuelSlot(pBlockEntity) && hasRecipe(pBlockEntity)) {
            pBlockEntity.burnTime = 0;
            pBlockEntity.itemHandler.extractItem(3,1, false);
        }

        if (hasRecipe(pBlockEntity) && pBlockEntity.burnTime < pBlockEntity.maxBurnTime) {
            pBlockEntity.progress++;
            setChanged(pLevel, pPos, pState);
            if(pBlockEntity.progress >= pBlockEntity.maxProgress) {

                craftItem(pBlockEntity);

            }
        }

        if (pBlockEntity.burnTime < pBlockEntity.maxBurnTime) {
            pBlockEntity.burnTime++;
            pLevel.setBlock(pPos, pState.setValue(LIT, true), 3);
        }

        if (!hasRecipe(pBlockEntity) || pBlockEntity.burnTime >= pBlockEntity.maxBurnTime) {
            pLevel.setBlock(pPos, pState.setValue(LIT, false), 3);
            if (pBlockEntity.progress != 0){
                pBlockEntity.progress--;
            }
            setChanged(pLevel, pPos, pState);
        }
    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, BrickOvenBlockEntity cookingPot) {
        if (state.getValue(LIT)) {
            Random random = level.random;
            if (random.nextFloat() < 0.3F) {
                double x = (double) pos.getX() + 0.5D + (random.nextDouble() * 0.6D - 0.3D);
                double y = (double) pos.getY() + 2.0D;
                double z = (double) pos.getZ() + 0.5D + (random.nextDouble() * 0.6D - 0.3D);
                level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
            }
        }

    }

    private static boolean hasRecipe(BrickOvenBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<BrickOvenRecipe> match = level.getRecipeManager()
                .getRecipeFor(BrickOvenRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }

    private static boolean hasCoalInFuelSlot(BrickOvenBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(3).getItem() == Items.COAL;
    }



    private static void craftItem(BrickOvenBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<BrickOvenRecipe> match = level.getRecipeManager()
                .getRecipeFor(BrickOvenRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(0,1, false);
            entity.itemHandler.extractItem(1,1, false);
            entity.itemHandler.extractItem(2,1, false);

            entity.itemHandler.setStackInSlot(4, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(4).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(4).getItem() == output.getItem() || inventory.getItem(4).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(4).getMaxStackSize() > inventory.getItem(4).getCount();
    }
}