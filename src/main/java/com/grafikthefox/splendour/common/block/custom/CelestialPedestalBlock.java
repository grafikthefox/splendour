package com.grafikthefox.splendour.common.block.custom;

import com.grafikthefox.splendour.common.tileentity.CelestialPedestalTileEntity;
import com.grafikthefox.splendour.common.tileentity.ModTileEntities;
import com.grafikthefox.splendour.common.tileentity.ModTileSimpleInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

public class CelestialPedestalBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {

    private static final VoxelShape SHAPE = Stream.of(
            Block.box(1, 0, 1, 15, 2, 15),
            Block.box(4, 2, 4, 12, 10, 12),
            Block.box(2, 10, 2, 14, 12, 14),
            Block.box(3, 14, 3, 13, 16, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public CelestialPedestalBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CelestialPedestalTileEntity(blockPos, blockState);
    }

    //SHAPE

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState  state, @Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }

//    @Override
//    public RenderShape getRenderShape(BlockState pState) {
//        return RenderShape.MODEL;
//    }

    @Override
    public BlockState updateShape(BlockState state, @Nonnull Direction direction, @Nonnull BlockState neighborState, @Nonnull LevelAccessor level, @Nonnull BlockPos pos, @Nonnull BlockPos neighborPos) {
        if(state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return state;
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    //STATE

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidState = pContext.getLevel().getFluidState(pContext.getClickedPos());

        return this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    //INTERACTIONS

    @Override
    public void onRemove(@Nonnull BlockState state, @Nonnull Level world, @Nonnull BlockPos pos, @Nonnull BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tile = world.getBlockEntity(pos);
            if (tile instanceof ModTileSimpleInventory) {
                Containers.dropContents(world, pos, ((ModTileSimpleInventory) tile).getItemHandler());
            }
            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        CelestialPedestalTileEntity tileEntity = (CelestialPedestalTileEntity) world.getBlockEntity(pos);
        ItemStack stack = player.getItemInHand(hand).copy();
        Container container = tileEntity.getItemHandler();

        if ((!stack.isEmpty()) && (container.getItem(0).isEmpty())) {
            if (stack.getCount() > 1) {
                if (!player.isCreative()) {
                    player.getItemInHand(hand).setCount(stack.getCount() - 1);
                }

                stack.setCount(1);
                container.setItem(0, stack);
                return InteractionResult.SUCCESS;
            } else {
                tileEntity.getItemHandler().setItem(0, stack);
                if (!player.isCreative()) {
                    player.getInventory().removeItem(player.getItemInHand(hand));
                }

                return InteractionResult.SUCCESS;
            }
        }

        if (!container.getItem(0).isEmpty()) {
            if (!player.isCreative()) {
                if(stack.isEmpty()) {
                    player.setItemInHand(hand, container.getItem(0));
                } else if (!player.addItem(container.getItem(0))) {
                    player.drop(container.getItem(0), false);
                }
//                world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, container.getItem(0).copy()));
            }

            container.removeItemNoUpdate(0);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }




}
