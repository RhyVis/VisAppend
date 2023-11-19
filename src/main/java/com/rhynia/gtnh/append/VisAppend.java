package com.rhynia.gtnh.append;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rhynia.gtnh.append.common.machine.recipe.crtsupport.AstraForge;
import com.rhynia.gtnh.append.loader.LoaderMachine;
import com.rhynia.gtnh.append.loader.LoaderMaterial;
import com.rhynia.gtnh.append.loader.LoaderRecipe;
import com.rhynia.gtnh.append.nei.NEIHandler;
import com.rhynia.gtnh.append.nei.NEIHandlerLong;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import minetweaker.MineTweakerAPI;

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

    public static final String MODID = Tags.MODID;
    public static final String MOD_ID = Tags.MODID;
    public static final String MOD_NAME = Tags.MODNAME;
    public static final String VERSION = Tags.VERSION;
    public static final Logger LOG = LogManager.getLogger(Tags.MODID);

    @SidedProxy(clientSide = "com.rhynia.gtnh.append.ClientProxy", serverSide = "com.rhynia.gtnh.append.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        LoaderMaterial.load();// Load Materials
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        MineTweakerAPI.registerClass(AstraForge.class);// MineTweaker Support
        LoaderMachine.loadMachines();// Load Machines
        NEIHandler.IMCSender();// NEI reg 2 each page
        NEIHandlerLong.IMCSender();// NEI reg 1 each page

    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void completeInit(FMLLoadCompleteEvent event) {
        LoaderRecipe.loadRecipes();
    }
}
