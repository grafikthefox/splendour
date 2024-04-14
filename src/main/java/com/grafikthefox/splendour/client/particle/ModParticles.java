package com.grafikthefox.splendour.client.particle;

import com.grafikthefox.splendour.Splendour;
import com.grafikthefox.splendour.client.particle.types.SparkleParticleType;
import com.grafikthefox.splendour.client.particle.types.SphereParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Splendour.MOD_ID);

    public static RegistryObject<SphereParticleType> SPHERE = PARTICLES.register("sphere", SphereParticleType::new);
    public static RegistryObject<SparkleParticleType> GLOWING_SPHERE = PARTICLES.register("glowing_sphere", SparkleParticleType::new);

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}
