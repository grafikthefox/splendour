package com.grafikthefox.splendour.common.block.custom;

import com.grafikthefox.splendour.common.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class VoidFungusBlock extends MushroomBlock {
    public VoidFungusBlock(Properties pProperties, ResourceKey<ConfiguredFeature<?, ?>> pFeature) {
        super(pProperties, pFeature);
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(ModBlocks.DEVOIDED_GRASS_BLOCK.get()) || pState.is(ModBlocks.DEVOIDED_DIRT.get());
    }
}
