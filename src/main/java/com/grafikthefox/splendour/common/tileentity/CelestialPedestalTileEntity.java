package com.grafikthefox.splendour.common.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class CelestialPedestalTileEntity extends ModTileSimpleInventory {

//    private static final int DEFAULT_HEIGHT = 120;
//    private int itemHeight = DEFAULT_HEIGHT;
//    private final float hoverStart;
//    private int ticksExisted;

    public CelestialPedestalTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
//        this.hoverStart = (float) (Math.random() * Math.PI * 2.0D);
    }

    public CelestialPedestalTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.CELESTIAL_PEDESTAL_TE.get(), pos, state);
//        this.hoverStart = (float) (Math.random() * Math.PI * 2.0D);
    }

//    public static void clientTick(Level level, BlockPos pos, BlockState state, CelestialPedestalTileEntity tileEntity) {
//        tileEntity.ticksExisted++;
//    }
//
//    public float getItemHover(float partialTicks) {
//        return (this.ticksExisted + partialTicks) * 2 + this.hoverStart;
//    }
//
//    public int getItemHeight() {
//        return this.itemHeight;
//    }
//
//    public void setItemHeight(int itemHeight) {
//        this.itemHeight = itemHeight;
//    }

    @Override
    protected SimpleContainer createItemHandler() {
        return new SimpleContainer(1) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        };
    }

    @NotNull
    @Override
    public final CompoundTag getUpdateTag() {
        var tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }


}
