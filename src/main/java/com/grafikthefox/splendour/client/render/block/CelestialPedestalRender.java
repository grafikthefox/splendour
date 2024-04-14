package com.grafikthefox.splendour.client.render.block;

import com.grafikthefox.splendour.client.event.ClientTickHandler;
import com.grafikthefox.splendour.common.tileentity.CelestialPedestalTileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.fml.earlydisplay.SimpleBufferBuilder;

public class CelestialPedestalRender implements BlockEntityRenderer<CelestialPedestalTileEntity> {

    public CelestialPedestalRender() {}

    @Override
    public void render(CelestialPedestalTileEntity tileEntity, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        Minecraft mc = Minecraft.getInstance();
        double ticks = (ClientTickHandler.ticksInGame + partialTicks) * 2;
        double ticksUp = (ClientTickHandler.ticksInGame + partialTicks) * 4;
        ticksUp = (ticksUp) % 360;

        poseStack.pushPose();
        poseStack.translate(0.5F, 1.25F, 0.5F);
        poseStack.translate(0F, (float) (Math.sin(Math.toRadians(ticksUp)) * 0.03125F), 0F);
        poseStack.mulPose(Axis.YP.rotationDegrees((float) ticks));
        poseStack.scale(1F, 1F, 1F);
        mc.getItemRenderer().renderStatic(tileEntity.getItemHandler().getItem(0), ItemDisplayContext.GROUND, light, overlay, poseStack, mc.renderBuffers().bufferSource(), tileEntity.getLevel(), 0);
        poseStack.popPose();
    }
}
