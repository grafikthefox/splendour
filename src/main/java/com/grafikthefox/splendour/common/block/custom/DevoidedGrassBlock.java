package com.grafikthefox.splendour.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public class DevoidedGrassBlock extends GrassBlock {
    public DevoidedGrassBlock(Properties p_53685_) {
        super(p_53685_);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {
        BlockPos posAbove = pos.above();
        BlockState grassState = Blocks.GRASS.defaultBlockState();
        Optional<Holder.Reference<PlacedFeature>> feature = level.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(VegetationPlacements.GRASS_BONEMEAL);

        label49:
        for(int i = 0; i < 128; ++i) {
            BlockPos posAbove1 = posAbove;

            for(int j = 0; j < i / 16; ++j) {
                posAbove1 = posAbove1.offset(source.nextInt(3) - 1, (source.nextInt(3) - 1) * source.nextInt(3) / 2, source.nextInt(3) - 1);
                if (!level.getBlockState(posAbove1.below()).is(this) || level.getBlockState(posAbove1).isCollisionShapeFullBlock(level, posAbove1)) {
                    continue label49;
                }
            }

            BlockState stateAbove = level.getBlockState(posAbove1);
            if (stateAbove.is(grassState.getBlock()) && source.nextInt(10) == 0) {
                ((BonemealableBlock)grassState.getBlock()).performBonemeal(level, source, posAbove1, stateAbove);
            }

            if (stateAbove.isAir()) {
                Holder holder;
                if (source.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> features = ((Biome)level.getBiome(posAbove1).value()).getGenerationSettings().getFlowerFeatures();
                    if (features.isEmpty()) {
                        continue;
                    }

                    holder = ((RandomPatchConfiguration)((ConfiguredFeature)features.get(0)).config()).feature();
                } else {
                    if (!feature.isPresent()) {
                        continue;
                    }

                    holder = feature.get();
                }

                ((PlacedFeature)holder.value()).place(level, level.getChunkSource().getGenerator(), source, posAbove1);
            }
        }
    }
}
