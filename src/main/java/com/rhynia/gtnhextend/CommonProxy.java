package com.rhynia.gtnhextend;

import com.rhynia.gtnhextend.register.fluid.FluidReg;
import com.rhynia.gtnhextend.register.material.MaterialAstra;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        new MaterialAstra();
        new FluidReg();
    }
}
