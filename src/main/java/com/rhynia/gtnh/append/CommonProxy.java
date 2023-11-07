package com.rhynia.gtnh.append;

import com.rhynia.gtnh.append.common.material.MaterialBartworksMethod;
import com.rhynia.gtnh.append.common.material.MaterialGTMethod;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        new MaterialGTMethod();
    }

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}
}
