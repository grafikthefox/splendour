package com.grafikthefox.splendour.client.particle.types;

import com.grafikthefox.splendour.util.RenderUtil;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;

public class SphereParticle extends GenericParticle{
    public SphereParticle(ClientLevel world, GenericParticleData data, double x, double y, double z, double vx, double vy, double vz) {
        super(world, data, x, y, z, vx, vy, vz);
    }

    @Override
    public void render(VertexConsumer b, Camera info, float pticks) {
        super.render(Minecraft.getInstance().options.graphicsMode().get().getId() == 3 ? RenderUtil.getDelayedRender().getBuffer(RenderUtil.GLOWING_PARTICLE) : b, info, pticks);
    }
}
