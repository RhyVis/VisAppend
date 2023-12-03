package com.rhynia.gtnh.append.common.material;

import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;

import gregtech.api.enums.TextureSet;

import static com.github.bartimaeusnek.bartworks.util.BW_Util.subscriptNumbers;

public class VA_WerkstoffMaterialPool implements Runnable {

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
        new short[] { 0x64, 0x95, 0xed },
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
    // SuperconductingFluxRaw
    public static final Werkstoff superconductingFluxRaw = new Werkstoff(
        new short[] { 0x69, 0x69, 0x69 },
        "SuperconductingFluxRaw",
        subscriptNumbers("(?AoⁿIf?)12D*12M11If*10SpNt8In7Nq+6Nq*5(⚷⚙⚷ Ni4Ti6)4(✧◇✧)4"),
        new Werkstoff.Stats().setMeltingPoint(3),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust()
            .addMolten()
            .enforceUnification(),
        OffsetID + 8,
        TextureSet.SET_MAGNETIC);
    // SuperconductingFluid
    public static final Werkstoff superconductingFlux = new Werkstoff(
        new short[] { 0xC0, 0xC0, 0xC0 },
        "SuperconductingFlux",
        "Sx",
        new Werkstoff.Stats().setMeltingPoint(1),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().disable()
            .addCells()
            .enforceUnification(),
        OffsetID + 9,
        TextureSet.SET_FLUID);

    @Override
    public void run() {}
}
