package com.grafikthefox.splendour.common.tileentity;

import com.grafikthefox.splendour.Splendour;
import com.grafikthefox.splendour.common.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModTileEntities {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Splendour.MOD_ID);

    public static final RegistryObject<BlockEntityType<CelestialPedestalTileEntity>> CELESTIAL_PEDESTAL_TE
            = TILE_ENTITIES.register("celestial_pedestal_te",
            () -> BlockEntityType.Builder.of(CelestialPedestalTileEntity::new, ModBlocks.CELESTIAL_PEDESTAL.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
