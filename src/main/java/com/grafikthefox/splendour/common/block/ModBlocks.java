package com.grafikthefox.splendour.common.block;

import com.grafikthefox.splendour.Splendour;
import com.grafikthefox.splendour.common.block.custom.CelestialPedestalBlock;
import com.grafikthefox.splendour.common.block.custom.DevastatingThornsBlock;
import com.grafikthefox.splendour.common.block.custom.MysticGlobeBlock;
import com.grafikthefox.splendour.common.block.custom.TallDevastatingThornsBlock;
import com.grafikthefox.splendour.common.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Splendour.MOD_ID);

    public static final RegistryObject<Block> DEVOIDED_GRASS_BLOCK = registerBlock("devoided_grass_block",
            () -> new GrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).strength(1.2F).mapColor(MapColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<Block> DEVOIDED_DIRT = registerBlock("devoided_dirt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).strength(1F).mapColor(MapColor.DIRT)));
    public static final RegistryObject<Block> FADED_LOG = registerBlock("faded_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VOID_FUNGUS = registerBlock("void_fungus",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).mapColor(MapColor.TERRACOTTA_MAGENTA)));
    public static final RegistryObject<Block> POTTED_VOID_FUNGUS = registerBlock("potted_void_fungus",
            () -> new FlowerPotBlock(VOID_FUNGUS.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).instabreak().noOcclusion()));
    public static final RegistryObject<Block> DEVASTATING_THORNS = registerBlock("devastating_thorns",
            () -> new DevastatingThornsBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));
    public static final RegistryObject<Block> TALL_DEVASTATING_THORNS = registerBlock("tall_devastating_thorns",
            () -> new TallDevastatingThornsBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));
    public static final RegistryObject<Block> MOONLIGHT_MOTH_HIVE = registerBlock("moonlight_moth_hive",
            () -> new TallDevastatingThornsBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.5F)));
    public static final RegistryObject<Block> AMETHYST_GLASS = registerBlock("amethyst_glass",
            () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static final RegistryObject<Block> MYSTIC_GLOBE = registerBlock("mystic_globe",
            () -> new MysticGlobeBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).mapColor(MapColor.COLOR_MAGENTA)));
    public static final RegistryObject<Block> CELESTIAL_PEDESTAL = registerBlock("celestial_pedestal",
            () -> new CelestialPedestalBlock(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_GRAY)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
