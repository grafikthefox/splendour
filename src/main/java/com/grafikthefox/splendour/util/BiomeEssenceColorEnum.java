package com.grafikthefox.splendour.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.Arrays;
import java.util.List;

public enum BiomeEssenceColorEnum {
    AQUA(119f, 221f, 252f, 119f, 221f, 252f, 1f, new ResourceKey[] {Biomes.OCEAN, Biomes.SNOWY_TAIGA}),
    IGNIS(235f, 72f, 47f, 235f, 72f, 47f, 1f, new ResourceKey[] {Biomes.NETHER_WASTES, Biomes.SAVANNA}),
    TERRA(38f, 235f, 47f, 38f, 235f, 47f, 1f, new ResourceKey[] {Biomes.PLAINS, Biomes.SWAMP}),
    AERO(190f, 199f, 129f, 190f, 199f, 129f, 1f, new ResourceKey[] {Biomes.JAGGED_PEAKS, Biomes.STONY_PEAKS}),
    TENEBRIS(118f, 80f, 128f, 118f, 80f, 128f, 1f, new ResourceKey[] {Biomes.THE_VOID, Biomes.THE_END}),
    LUX(255f, 251f, 38f, 255f, 251f, 38f, 1f, new ResourceKey[] {Biomes.LUSH_CAVES});

    private float r1, g1, b1, r2, g2, b2, a;
    private List<ResourceKey<Biome>> biomeList;

    BiomeEssenceColorEnum(float r1, float g1, float b1, float r2, float g2, float b2, float a, ResourceKey<Biome>[] biomeList) {
        this.r1 = r1;
        this.g1 = g1;
        this.b1 = b1;
        this.r2 = r2;
        this.g2 = g2;
        this.b2 = b2;
        this.a = a;
        this.biomeList = Arrays.asList(biomeList);
    }


    public Float[] getColors() {
        return new Float[] {r1, g1, b1, r2, g2, b2, a};
    }

    public List<ResourceKey<Biome>> getBiomeList() {
        return biomeList;
    }

}
