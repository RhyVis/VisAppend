package com.rhynia.gtnhextend;

import com.rhynia.gtnhextend.register.material.MaterialBartworksMethod;
import com.rhynia.gtnhextend.register.material.MaterialGTMethod;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        new MaterialGTMethod();
        new MaterialBartworksMethod();
    }
}
