package com.example.an_addon;

import com.example.an_addon.entities.ModBlockEntites;
import com.example.an_addon.init.BlockInit;
import com.example.an_addon.init.ItemInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ANShieldMainMod.MODID)
public class ANShieldMainMod
{
    public static final String MODID = "arsshield";

    private static final Logger LOGGER = LogManager.getLogger();

    public ANShieldMainMod() {
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(this::setup);
        modbus.addListener(this::doClientStuff);
        ItemInit.ITEMS.register(modbus);
        BlockInit.BLOCKS.register(modbus);
        ModBlockEntites.BLOCK_ENTITIES.register(modbus);
    }

    public static ResourceLocation prefix(String path){
        return new ResourceLocation(MODID, path);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

}
