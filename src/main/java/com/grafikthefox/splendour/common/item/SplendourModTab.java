package com.grafikthefox.splendour.common.item;

import com.grafikthefox.splendour.Splendour;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
                        output.accept(ModItems.FORCERITE_INGOT.get());
                        output.accept(ModItems.FORCERITE_NUGGET.get());
                        output.accept(Items.DIAMOND);
                    })
                    .build());
    public static void register(IEventBus eventBus) {
        MOD_TABS.register(eventBus);
    }
}
