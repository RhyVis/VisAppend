package com.rhynia.wireless_ext.proxy;

import static com.rhynia.wireless_ext.WirelessExt.MOD_NAME;
import static com.rhynia.wireless_ext.WirelessExt.VERSION;

import com.rhynia.wireless_ext.WirelessExt;
import com.rhynia.wireless_ext.common.loader.control.WirelessExtraLoader;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        WirelessExt.LOG.info("Hello Minecraft! " + MOD_NAME + " start preInit process at version " + VERSION);
    }

    public void init(FMLInitializationEvent event) {
        WirelessExt.LOG.info("Loading machines.");
        WirelessExtraLoader.doRegister();// Load Wireless
    }

    public void postInit(FMLPostInitializationEvent event) {}

    public void completeInit(FMLLoadCompleteEvent event) {}

    public void serverStarting(FMLServerStartingEvent event) {
        WirelessExt.LOG.info("Now " + MOD_NAME + " at version " + VERSION + " loaded successfully.");
    }
}
