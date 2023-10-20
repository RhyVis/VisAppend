package com.rhynia.astro.Fluid;

import static gregtech.api.enums.FluidState.*;

import gregtech.api.fluid.GT_FluidFactory;

public class FluidReg implements Runnable {

    @Override
    public void run() {
        GT_FluidFactory.builder("AstroActivated")
            .withLocalizedName("激发态星辉超流体")
            .withStateAndTemperature(LIQUID, 250)
            .buildAndRegister()
            .asFluid();
        GT_FluidFactory.builder("AstroUnstable")
            .withLocalizedName("亚稳态星辉超流体")
            .withStateAndTemperature(LIQUID, 25)
            .buildAndRegister()
            .asFluid();
        GT_FluidFactory.builder("AstroLimited")
            .withLocalizedName("极限态星辉超载体")
            .withStateAndTemperature(LIQUID, 10)
            .buildAndRegister()
            .asFluid();
        GT_FluidFactory.builder("AstroCatalyst")
            .withLocalizedName("星辉催化载体")
            .withStateAndTemperature(PLASMA, 1100)
            .buildAndRegister()
            .asFluid();
    }

}
