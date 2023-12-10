package com.rhynia.gtnh.append.common.material;

import static com.github.bartimaeusnek.bartworks.util.BW_Util.subscriptNumbers;

import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;

import gregtech.api.enums.TextureSet;

@Deprecated
public class VA_WerkstoffMaterialPool implements Runnable {

    protected static final int OffsetID = 24510;

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
        subscriptNumbers("Aoⁿ2If3"),
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
        subscriptNumbers("(?Aoⁿ2If3?)*"),
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
        "[-Lapo-Lapo-]",
        new Werkstoff.Stats().setRadioactive(true)
            .setMeltingPoint(4888),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells()
            .enforceUnification(),
        OffsetID + 7,
        TextureSet.SET_FLUID);
    // Superconducting Flux Raw
    public static final Werkstoff superconductingFluxRaw = new Werkstoff(
        new short[] { 0x69, 0x69, 0x69 },
        "SuperconductingFluxRaw",
        subscriptNumbers("(?Aoⁿ2If3?)*12D*12M11If*10SpNt8In7Nq+6Nq*5Or5(⚷⚙⚷Ni4Ti6)4(✧◇✧)4"),
        new Werkstoff.Stats().setMeltingPoint(3),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust()
            .addMolten()
            .enforceUnification(),
        OffsetID + 8,
        TextureSet.SET_MAGNETIC);
    // SuperconductingFluid
    // We regard 64UHV, 16UEV, 4UIV, 1UMV SC as 4 Ingots of SX
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
    // Astro Residue
    public static final Werkstoff astroResidue = new Werkstoff(
        new short[] { 0x19, 0x19, 0x70 },
        "AstroResidue",
        "Ao°",
        new Werkstoff.Stats().setMeltingPoint(1)
            .setBoilingPoint(100000),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().disable()
            .addCells()
            .enforceUnification(),
        OffsetID + 10,
        TextureSet.SET_FLUID);
    // Astro Catalyst Reforged
    public static final Werkstoff astroCatalystReforged = new Werkstoff(
        new short[] { 0x41, 0x69, 0xe1 },
        "AstroCatalystReforged",
        subscriptNumbers("Aoⁿ4?3"),
        new Werkstoff.Stats().setMeltingPoint(1)
            .setBoilingPoint(100000),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().disable()
            .addCells()
            .enforceUnification(),
        OffsetID + 11,
        TextureSet.SET_FLUID);

    @Override
    public void run() {}
}
