package com.rhynia.astro;

import com.rhynia.astro.Fluid.FluidReg;
import com.rhynia.astro.Material.MaterialAstra;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        new MaterialAstra();
        new FluidReg();
    }
}
