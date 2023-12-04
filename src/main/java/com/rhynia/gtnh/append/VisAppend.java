package com.rhynia.gtnh.append;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rhynia.gtnh.append.loader.MachineLoader;
import com.rhynia.gtnh.append.loader.MaterialLoader;
import com.rhynia.gtnh.append.loader.RecipeLoader;
import com.rhynia.gtnh.append.nei.NEIHandler;
import com.rhynia.gtnh.append.nei.NEIHandlerLong;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(
    modid = Tags.MODID,
    version = Tags.VERSION,
    name = Tags.MODNAME,
    dependencies = "required-after:IC2; " + "required-after:gregtech; "
        + "required-after:bartworks; "
        + "before:miscutils; "
        + "after:dreamcraft;",
    acceptedMinecraftVersions = "[1.7.10]")

public class VisAppend {

    public static final String MOD_ID = Tags.MODID;
    public static final String MOD_NAME = Tags.MODNAME;
    public static final String VERSION = Tags.VERSION;
    public static final Logger LOG = LogManager.getLogger(Tags.MODID);

    @SidedProxy(clientSide = "com.rhynia.gtnh.append.ClientProxy", serverSide = "com.rhynia.gtnh.append.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        VisAppend.LOG.info(MOD_NAME + " start preInit process at version " + VERSION);
        VisAppend.LOG.info("Materials initializing.");
        proxy.preInit(event);
        MaterialLoader.load();// Load Materials
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        VisAppend.LOG.info("Loading machines.");
        MachineLoader.loadMachines();// Load Machines
        VisAppend.LOG.info("Sending IMC.");
        NEIHandler.IMCSender();// NEI reg 2 each page
        NEIHandlerLong.IMCSender();// NEI reg 1 each page
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        VisAppend.LOG.info("Loading recipes stage 1/2.");
        RecipeLoader.loadRecipesPostInit();// Init RecipeMap
    }

    @Mod.EventHandler
    public void completeInit(FMLLoadCompleteEvent event) {
        VisAppend.LOG.info("Loading recipes stage 2/2.");
        RecipeLoader.loadRecipesCompleteInit();// Complete left recipes
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
