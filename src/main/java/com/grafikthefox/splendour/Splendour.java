package com.grafikthefox.splendour;

import com.grafikthefox.splendour.client.event.ClientTickHandler;
import com.grafikthefox.splendour.client.particle.ModParticles;
import com.grafikthefox.splendour.common.block.ModBlocks;
import com.grafikthefox.splendour.common.item.ModItems;
import com.grafikthefox.splendour.common.item.SplendourModTab;
import com.grafikthefox.splendour.common.tileentity.ModTileEntities;
import com.grafikthefox.splendour.core.network.PacketHandler;
import com.grafikthefox.splendour.core.proxy.ClientProxy;
import com.grafikthefox.splendour.core.proxy.ISidedProxy;
import com.grafikthefox.splendour.core.proxy.ServerProxy;
import com.grafikthefox.splendour.util.RenderUtil;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Splendour.MOD_ID)
public class Splendour
{
    public static final String MOD_ID = "splendour";
    public static final ISidedProxy proxy = DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public Splendour() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        SplendourModTab.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModTileEntities.register(modEventBus);
        ModParticles.register(modEventBus);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            forgeBus.addListener(ClientTickHandler::clientTickEnd);
            forgeBus.addListener(RenderUtil::onRenderWorldLast);
        });

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);


    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        PacketHandler.init();
    }

//    @SubscribeEvent
//    public void onServerStarting(ServerStartingEvent event)
//    {
//    }
//
//    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//    public static class ClientModEvents
//    {
//        @SubscribeEvent
//        public static void onClientSetup(FMLClientSetupEvent event)
//        {
//        }
//    }
}
