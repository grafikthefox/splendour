package com.grafikthefox.splendour.common.item;

import com.grafikthefox.splendour.Splendour;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Splendour.MOD_ID);

    public static final RegistryObject<Item> FORCERITE_INGOT = ITEMS.register("forcerite_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FORCERITE_NUGGET = ITEMS.register("forcerite_nugget",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
