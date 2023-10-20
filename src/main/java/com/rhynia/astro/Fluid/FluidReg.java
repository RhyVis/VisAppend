package com.rhynia.astro.Fluid;

import static gregtech.api.enums.FluidState.LIQUID;

import gregtech.api.fluid.GT_FluidFactory;

public class FluidReg implements Runnable {

    @Override
    public void run() {
        GT_FluidFactory.builder("AstroActivated")
            .withLocalizedName("AstroActivated")
            .withStateAndTemperature(LIQUID, 250)
            .buildAndRegister()
            .asFluid();
    }
}
