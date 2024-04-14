package com.grafikthefox.splendour.common.item;

import com.grafikthefox.splendour.Splendour;
import com.grafikthefox.splendour.common.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SplendourModTab {
    public static final DeferredRegister<CreativeModeTab> MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Splendour.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN_TAB = MOD_TABS.register("splendour_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.FORCERITE_INGOT.get()))
                    .title(Component.literal("Splendour"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //ITEMS
                        output.accept(ModItems.FORCERITE_INGOT.get());
                        output.accept(ModItems.FORCERITE_NUGGET.get());

                        //BLOCKS
                        output.accept(ModBlocks.DEVOIDED_DIRT.get());
                        output.accept(ModBlocks.DEVOIDED_GRASS_BLOCK.get());
                        output.accept(ModBlocks.FADED_LOG.get());
                        output.accept(ModBlocks.DEVASTATING_THORNS.get());
                        output.accept(ModBlocks.TALL_DEVASTATING_THORNS.get());
                        output.accept(ModBlocks.VOID_FUNGUS.get());
                        output.accept(ModBlocks.WALL_FUNGI.get());
                        output.accept(ModBlocks.FADED_PLANKS.get());
                        output.accept(ModBlocks.AMETHYST_GLASS.get());
                        output.accept(ModBlocks.CELESTIAL_PEDESTAL.get());
                        output.accept(ModBlocks.MOONLIGHT_MOTH_HIVE.get());
                        output.accept(ModBlocks.MYSTIC_GLOBE.get());
                        output.accept(ModBlocks.CELESTIAL_STONE.get());
                        output.accept(ModBlocks.VOID_FUNGUS_BLOCK.get());
                        output.accept(ModBlocks.VOID_FUNGUS_STEM.get());

                    })
                    .build());
    public static void register(IEventBus eventBus) {
        MOD_TABS.register(eventBus);
    }
}
