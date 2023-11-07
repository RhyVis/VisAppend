package com.rhynia.gtnh.append;

import com.rhynia.gtnh.append.common.material.MaterialBartworksMethod;
import com.rhynia.gtnh.append.common.material.MaterialGTMethod;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        GTNHAppend.LOG.info(Tags.MODNAME + " at version " + Tags.VERSION);
        new MaterialGTMethod();
        new MaterialBartworksMethod();
    }
}
