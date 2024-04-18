package com.grafikthefox.splendour.common.block.custom;

import com.grafikthefox.splendour.Splendour;
import com.grafikthefox.splendour.client.particle.ModParticles;
import com.grafikthefox.splendour.client.particle.types.Particles;
import com.grafikthefox.splendour.common.block.ModBlocks;
import com.grafikthefox.splendour.core.network.PacketHandler;
import com.grafikthefox.splendour.core.network.packets.MysticGlobeParticlePacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;


public class MysticGlobeBlock extends Block {
    private static final VoxelShape SHAPE = Stream.of(
            Block.box(4, 0, 4, 12,2,12),
            Block.box(5,3,5,11,9,11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public MysticGlobeBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        if(pLevel.getBlockState(pPos.below()).getBlock() == ModBlocks.CELESTIAL_PEDESTAL.get()) {
            pLevel.setBlockAndUpdate(pPos.below(), ModBlocks.GLOBE_STAND.get().defaultBlockState());
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        float chance = 0.5f;
        if (chance < pRandom.nextFloat()) {
            Particles.create(ModParticles.GLOWING_SPHERE)
                    .addVelocity(0f, 0f, 0f)
                    .setAlpha(0.45f, 0)
                    .setScale(1f, 1f)
                    .setColor(1f, 1f, 1f, 1f, 1f, 1f, 1f)
                    .setLifetime(5)
                    .spawn(pLevel, pPos.getX(),  pPos.getY() + 0.5f, pPos.getZ());
        }

    }


//    @Override
//    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
//        if (pLevel.isClientSide) {
//            return InteractionResult.SUCCESS;
//        }
//
//        for (int i = 0; i < 360; i += 10) {
//            PacketHandler.sendToTrackingChunk(pLevel, pPos, new MysticGlobeParticlePacket(pPos.getX() + 0.5f, pPos.getY(), pPos.getZ() + 0.5f, pPos.getX() + 0.5f, pPos.getY() - 0.25F, pPos.getZ() + 0.5f, pPlayer.getRotationVector().y + i,66, 135, 212));
//        }
//
//        return InteractionResult.CONSUME;
//    }
}
