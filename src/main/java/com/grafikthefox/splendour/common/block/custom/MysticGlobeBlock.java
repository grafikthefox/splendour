package com.grafikthefox.splendour.common.block.custom;

import com.grafikthefox.splendour.client.particle.ModParticles;
import com.grafikthefox.splendour.client.particle.types.Particles;
import com.grafikthefox.splendour.common.block.ModBlocks;
import com.grafikthefox.splendour.util.BiomeEssenceColorEnum;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
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
        if(pLevel.getBiome(pPos).unwrap().left().isPresent()) {
            ResourceKey<Biome> biomeResourceKey = pLevel.getBiome(pPos).unwrap().left().get();

            Float[] colors = getFloats(biomeResourceKey);

            double x = pPos.getCenter().x;
            double y = pPos.getCenter().y;
            double z = pPos.getCenter().z;
            int[] borders = {-1, 1};

            for (int i = 0; i < 3; i++) {
                float x1 = pRandom.nextFloat() * 2.5f * borders[pRandom.nextInt(borders.length)];
                float y1 = pRandom.nextFloat() * 2.5f;
                float z1 = pRandom.nextFloat() * 2.5f * borders[pRandom.nextInt(borders.length)];

                Particles.create(ModParticles.GLOWING_SPHERE)
                        .addVelocity(-x1 / 10f, -y1 / 10, -z1 / 10f)
                        .setAlpha(0.1f, 0.8f)
                        .setScale(0.12f, 0.01f)
                        .setColor(colors)
                        .setLifetime(10)
                        .spawn(pLevel, (float) x + x1, (float) y + y1, (float) z + z1);
            }

            Particles.create(ModParticles.GLOWING_SPHERE)
                    .addVelocity(0, 0, 0)
                    .setAlpha(0.1f, 0.8f)
                    .setScale(0.1f, 0.05f)
                    .setColor(colors)
                    .setLifetime(10)
                    .spawn(pLevel, (float) x, (float) y - 0.125f, (float) z);

        }
    }

    private static Float @NotNull [] getFloats(ResourceKey<Biome> biomeResourceKey) {
        Float[] colors = {1f, 1f, 1f, 1f, 1f, 1f, 1f};

        for(BiomeEssenceColorEnum essenceColor : BiomeEssenceColorEnum.values()) {
            if(essenceColor.getBiomeList().contains(biomeResourceKey)) {
                colors = essenceColor.getColors();
                break;
            }
        }

        return colors;
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
