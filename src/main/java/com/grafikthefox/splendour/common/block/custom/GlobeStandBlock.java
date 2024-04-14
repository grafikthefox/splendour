package com.grafikthefox.splendour.common.block.custom;

import com.grafikthefox.splendour.common.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class GlobeStandBlock extends Block {
    public GlobeStandBlock(Properties pProperties) {
        super(pProperties);
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.box(1, 0, 1, 15, 2, 15),
            Block.box(3, 2, 3, 13, 10, 13),
            Block.box(1, 10, 1, 15, 16, 15)
    ).reduce((voxelShape, voxelShape2) -> Shapes.join(voxelShape, voxelShape2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        BlockPos abovePos = pPos.above();
        BlockState aboveState = pLevel.getBlockState(abovePos);
        return aboveState.is(ModBlocks.MYSTIC_GLOBE.get()) ? super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos) : ModBlocks.CELESTIAL_PEDESTAL.get().defaultBlockState();
    }
}
