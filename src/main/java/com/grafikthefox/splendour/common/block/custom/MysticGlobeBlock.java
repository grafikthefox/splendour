package com.grafikthefox.splendour.common.block.custom;

import com.grafikthefox.splendour.client.particle.ModParticles;
import com.grafikthefox.splendour.client.particle.types.Particles;
import com.grafikthefox.splendour.common.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
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
        double x = pPos.getCenter().x;
        double y = pPos.getCenter().y;
        double z = pPos.getCenter().z;
        int[] borders = {-1, 1};

        for(int i = 0; i<3; i++) {
            float x1 = pRandom.nextFloat() * 2.5f * borders[pRandom.nextInt(borders.length)];
            float y1 = pRandom.nextFloat() * 2.5f;
            float z1 = pRandom.nextFloat() * 2.5f * borders[pRandom.nextInt(borders.length)];

            Particles.create(ModParticles.GLOWING_SPHERE)
                    .addVelocity(-x1/10f, -y1/10, -z1/10f)
                    .setAlpha(0.1f, 0.8f)
                    .setScale(0.12f, 0.01f)
                    .setColor(119 / 255f, 221 / 255f, 252 / 255f, 165 / 255f, 136 / 255f, 245 / 255f, 1f)
                    .setLifetime(10)
                    .spawn(pLevel, (float) x + x1, (float) y + y1, (float) z + z1);
        }

        Particles.create(ModParticles.GLOWING_SPHERE)
                .addVelocity(0, 0, 0)
                .setAlpha(0.1f, 0.8f)
                .setScale(0.1f, 0.05f)
                .setColor(0 / 255f, 185 / 255f, 255 / 255f, 0 / 255f, 185 / 255f, 255 / 255f, 1f)
                .setLifetime(10)
                .spawn(pLevel, (float) x, (float) y - 0.125f, (float) z);


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
