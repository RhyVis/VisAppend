package com.rhynia.astro;

import com.rhynia.astro.Material.MaterialAstra;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        Astro.LOG.info(Config.greeting);
        Astro.LOG.info("I am " + Tags.MODNAME + " at version " + Tags.VERSION);
        new MaterialAstra();
    }
}
