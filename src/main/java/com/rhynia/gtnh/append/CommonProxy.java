package com.rhynia.gtnh.append;

import com.rhynia.gtnh.append.register.material.MaterialBartworksMethod;
import com.rhynia.gtnh.append.register.material.MaterialGTMethod;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        new MaterialGTMethod();
        new MaterialBartworksMethod();
    }
}
