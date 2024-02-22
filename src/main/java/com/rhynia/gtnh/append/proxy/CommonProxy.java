package com.rhynia.gtnh.append.proxy;

import static com.rhynia.gtnh.append.VisAppend.MOD_NAME;
import static com.rhynia.gtnh.append.VisAppend.VERSION;

import com.rhynia.gtnh.append.VisAppend;
import com.rhynia.gtnh.append.api.util.ProcessingMap;
import com.rhynia.gtnh.append.common.loader.MachineLoader;
import com.rhynia.gtnh.append.common.loader.MaterialLoader;
import com.rhynia.gtnh.append.common.loader.RecipeLoader;
import com.rhynia.gtnh.append.config.Config;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        VisAppend.LOG.info("Hello Minecraft! " + MOD_NAME + " start preInit process at version " + VERSION);
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());// Init Conf
        VisAppend.LOG.info("Materials initializing.");
        MaterialLoader.load();// Load Materials
    }

    public void init(FMLInitializationEvent event) {
        VisAppend.LOG.info("Loading machines.");
        MachineLoader.loadMachines();// Load Machines
    }

    public void postInit(FMLPostInitializationEvent event) {
        VisAppend.LOG.info("Loading recipes stage 1/2.");
        RecipeLoader.loadRecipesPostInit();// Init RecipeMap - No longer required
    }

    public void completeInit(FMLLoadCompleteEvent event) {
        VisAppend.LOG.info("Loading recipes stage 2/2.");
        RecipeLoader.loadRecipesCompleteInit();// Complete left recipes
        VisAppend.LOG.info("Generating processing matrix map.");
        ProcessingMap.generateMap();// Generate Map
    }

    public void serverStarting(FMLServerStartingEvent event) {
        VisAppend.LOG.info("Now " + MOD_NAME + " at version " + VERSION + " loaded successfully.");
    }
}
