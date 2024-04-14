package com.grafikthefox.splendour;

import com.grafikthefox.splendour.client.particle.ModParticles;
import com.grafikthefox.splendour.client.particle.types.SparkleParticleType;
import com.grafikthefox.splendour.client.particle.types.SphereParticleType;
import com.grafikthefox.splendour.client.render.block.CelestialPedestalRender;
import com.grafikthefox.splendour.common.tileentity.ModTileEntities;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.io.IOException;

public class SplendourClient {

    public static ShaderInstance GLOWING_PARTICLE_SHADER, SPRITE_PARTICLE_SHADER;

    public static ShaderInstance getGlowingParticleShader() {
        return GLOWING_PARTICLE_SHADER;
    }

    public static ShaderInstance getSpriteParticleShader() {
        return SPRITE_PARTICLE_SHADER;
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class RegistryEvents{
        @SubscribeEvent
        public static void onRenderTypeSetup(FMLClientSetupEvent event) {
            BlockEntityRenderers.register(ModTileEntities.CELESTIAL_PEDESTAL_TE.get(), (t) -> new CelestialPedestalRender());
        }

//        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void registerFactories(RegisterParticleProvidersEvent event) {
            Minecraft.getInstance().particleEngine.register(ModParticles.SPHERE.get(), SphereParticleType.Factory::new);
            Minecraft.getInstance().particleEngine.register(ModParticles.GLOWING_SPHERE.get(), SparkleParticleType.Factory::new);
        }

//        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void shaderRegistry(RegisterShadersEvent event) throws IOException {
            event.registerShader(new ShaderInstance(event.getResourceProvider(), new ResourceLocation("splendour:glowing_particle"), DefaultVertexFormat.PARTICLE),
                    shader -> { GLOWING_PARTICLE_SHADER = shader; });
            event.registerShader(new ShaderInstance(event.getResourceProvider(), new ResourceLocation("splendour:sprite_particle"), DefaultVertexFormat.PARTICLE),
                    shader -> { SPRITE_PARTICLE_SHADER = shader; });
        }
    }

}
