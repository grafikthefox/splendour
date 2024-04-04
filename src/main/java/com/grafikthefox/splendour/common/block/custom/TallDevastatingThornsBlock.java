package com.grafikthefox.splendour.common.block.custom;

import com.grafikthefox.splendour.common.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class TallDevastatingThornsBlock extends DoublePlantBlock {
    public TallDevastatingThornsBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return (pState.is(ModBlocks.DEVOIDED_GRASS_BLOCK.get()) || pState.is(ModBlocks.DEVOIDED_DIRT.get())) && !pState.is(Blocks.GRASS_BLOCK);
    }


    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        pEntity.makeStuckInBlock(pState, new Vec3(0.600000011920929, 0.5, 0.600000011920929));
        if (!pLevel.isClientSide && (pEntity.xOld != pEntity.getX() || pEntity.zOld != pEntity.getZ())) {
            double d0 = Math.abs(pEntity.getX() - pEntity.xOld);
            double d1 = Math.abs(pEntity.getZ() - pEntity.zOld);
            if (d0 >= 0.03005 || d1 >= 0.03005) {
                pEntity.hurt(pLevel.damageSources().thorns(pEntity), 1.0F);
            }
        }
        if(pEntity instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20,0, true, false));
        }
    }
}
