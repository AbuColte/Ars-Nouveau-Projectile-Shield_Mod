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

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleANAddon.MODID)
public class ExampleANAddon
{
    public static final String MODID = "arsshield";

    private static final Logger LOGGER = LogManager.getLogger();

    public ExampleANAddon() {
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

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

}
