package com.rhynia.gtnh.append.common.material;

import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;

import gregtech.api.enums.TextureSet;

public class MaterialBartworksMethod implements Runnable {

    protected static final int OffsetID = 24510;

    // Primogem
    public static final Werkstoff Primogem = new Werkstoff(
        new short[] { 0x87, 0xce, 0xeb },
        "Primogem",
        "Maybe you were right, but...",
        new Werkstoff.Stats(),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().disable()
            .addGems(),
        OffsetID + 1,
        TextureSet.SET_NETHERSTAR);
    // Originiums
    public static final Werkstoff Originiums = new Werkstoff(
        new short[] { 0xda, 0xa5, 0x20 },
        "Originiums",
        "The Stone of Origins.",
        new Werkstoff.Stats(),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().disable()
            .addGems(),
        OffsetID + 2,
        TextureSet.SET_DIAMOND);
    // Astro Fuel MKI
    public static final Werkstoff astroFuelMKI = new Werkstoff(
        new short[] { 0x00, 0xcd, 0xcd },
        "AstroFuelMKI",
        "~?Ao?~",
        new Werkstoff.Stats().setRadioactive(true),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        OffsetID + 3,
        TextureSet.SET_FLUID);
    // Astro Fuel MKI Depleted
    public static final Werkstoff astroFuelMKIDepleted = new Werkstoff(
        new short[] { 0x00, 0x8b, 0x8b },
        "AstroFuelMKIDepleted",
        "~Aoⁿ~",
        new Werkstoff.Stats().setRadioactive(true),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        OffsetID + 4,
        TextureSet.SET_FLUID);
    // Astro Catalyst Base
    public static final Werkstoff astroCatalystBase = new Werkstoff(
        new short[] { 0x48, 0x3d, 0x8b },
        "AstroCatalystBase",
        "?AoⁿIf?",
        new Werkstoff.Stats().setRadioactive(true)
            .setSublimation(true)
            .setBoilingPoint(570)
            .setGas(true),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust()
            .addCells()
            .enforceUnification(),
        OffsetID + 5,
        TextureSet.SET_FLUID);
    // Astro Catalyst Activated
    public static final Werkstoff astroCatalystActivated = new Werkstoff(
        new short[] { 0x6a, 0x5a, 0xcd },
        "AstroCatalystBaseActivated",
        "(?AoⁿIf?)*",
        new Werkstoff.Stats().setRadioactive(true)
            .setMeltingPoint(980),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells()
            .enforceUnification(),
        OffsetID + 6,
        TextureSet.SET_FLUID);
    // Lapo Activation Fluid
    public static final Werkstoff lapoActivatedFluid = new Werkstoff(
        new short[] { 0x6a, 0x5a, 0xcd },
        "LapoActivatedFluid",
        "为什么只用兰波顿储能？",
        new Werkstoff.Stats().setRadioactive(true)
            .setMeltingPoint(4888),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells()
            .enforceUnification(),
        OffsetID + 7,
        TextureSet.SET_FLUID);

    @Override
    public void run() {}
}
