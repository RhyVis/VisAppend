package com.rhynia.gtnh.append.proxy;

import static com.rhynia.gtnh.append.VisAppend.MOD_NAME;
import static com.rhynia.gtnh.append.VisAppend.VERSION;

import com.rhynia.gtnh.append.VisAppend;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {}

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}

    public void serverStarting(FMLServerStartingEvent event) {
        VisAppend.LOG.info("Now " + MOD_NAME + " at version " + VERSION + " loaded successfully.");
    }
}
