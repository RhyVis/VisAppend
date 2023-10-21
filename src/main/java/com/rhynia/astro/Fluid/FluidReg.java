package com.rhynia.astro.Fluid;

import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;

import gregtech.api.enums.TextureSet;

public class FluidReg implements Runnable {

    protected static final int OffsetID = 24510;

    // AstroActivated
    public static final Werkstoff astroActivated = new Werkstoff(
        new short[] { 0x3a, 0x77, 0x3d },
        "Astro Activated Fluid",
        "AoNq",
        new Werkstoff.Stats().setRadioactive(true),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        OffsetID,
        TextureSet.SET_FLUID);

    @Override
    public void run() {}
}
