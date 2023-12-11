package com.rhynia.gtnh.append.common.material;

import static com.github.bartimaeusnek.bartworks.util.BW_Util.subscriptNumbers;

import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;

import gregtech.api.enums.TextureSet;

public class VAMaterials implements Runnable {

    private static final int Offset_Element = 23000;
    private static final int Offset_Mix = 23100;
    private static final int Offset_Production = 23200;

    // region Element
    // Astrium
    public static final Werkstoff Astrium = new Werkstoff(
        new short[] { 30, 144, 252, 255 },
        "Astrium",
        "Aμ",
        new Werkstoff.Stats().setProtons(170)
            .setMass(452)
            .setBlastFurnace(true)
            .setMeltingPoint(8500),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().onlyDust()
            .addMolten(),
        Offset_Element + 1,
        TextureSet.SET_GEM_HORIZONTAL);
    // AstriumInfinity
    public static final Werkstoff AstriumInfinity = new Werkstoff(
        new short[] { 30, 144, 252, 255 },
        "AstriumInfinity",
        "Aμⁿ",
        new Werkstoff.Stats().setProtons(191)
            .setMass(510)
            .setBlastFurnace(true)
            .setMeltingPoint(12560),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().onlyDust()
            .addMolten(),
        Offset_Element + 2,
        TextureSet.SET_GEM_VERTICAL);
    // AstriumMagic
    public static final Werkstoff AstriumMagic = new Werkstoff(
        new short[] { 0, 32, 178, 170 },
        "AstriumMagic",
        "AμMa",
        new Werkstoff.Stats().setProtons(170)
            .setMass(475)
            .setBlastFurnace(true)
            .setMeltingPoint(7),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().onlyDust()
            .addMolten(),
        Offset_Element + 3,
        TextureSet.SET_DIAMOND);
    // Primogem
    public static final Werkstoff Primoium = new Werkstoff(
        new short[] { 0x87, 0xce, 0xeb },
        "Primoium",
        "Pr",
        new Werkstoff.Stats().setProtons(145)
            .setMass(385)
            .setBlastFurnace(true)
            .setMeltingPoint(7250),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().onlyDust()
            .addMolten(),
        Offset_Element + 4,
        TextureSet.SET_SHINY);
    // Originiums
    public static final Werkstoff Originium = new Werkstoff(
        new short[] { 0xda, 0xa5, 0x20 },
        "Originium",
        "Or*",
        new Werkstoff.Stats().setProtons(165)
            .setMass(445)
            .setBlastFurnace(true)
            .setMeltingPoint(8540),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().onlyDust()
            .addMolten(),
        Offset_Element + 5,
        TextureSet.SET_SHINY);
    // endregion

    // region Element
    // Astral Fuel MkI
    public static final Werkstoff AstralFuelMkI = new Werkstoff(
        new short[] { 0x00, 0xcd, 0xcd },
        "AstralFuelMkI",
        "~?Aμ?~",
        new Werkstoff.Stats().setRadioactive(true),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        Offset_Mix + 1,
        TextureSet.SET_FLUID);
    // Astral Fuel MkI
    public static final Werkstoff AstralFuelMkIDepleted = new Werkstoff(
        new short[] { 0x00, 0x8b, 0x8b },
        "AstralFuelMkIDepleted",
        "~?Aμⁿ?~",
        new Werkstoff.Stats().setRadioactive(true),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        Offset_Mix + 2,
        TextureSet.SET_FLUID);
    // Astral Catalyst Base
    public static final Werkstoff AstralCatalystBase = new Werkstoff(
        new short[] { 0x48, 0x3d, 0x8b },
        "AstralCatalystBase",
        subscriptNumbers("Aμⁿ2If3"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        Offset_Mix + 3,
        TextureSet.SET_FLUID);
    // Astral Catalyst Base Excited
    public static final Werkstoff AstralCatalystBaseExcited = new Werkstoff(
        new short[] { 0x6a, 0x5a, 0xcd },
        "AstralCatalystBaseExcited",
        subscriptNumbers("(?Aμⁿ2If3?)*"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        Offset_Mix + 4,
        TextureSet.SET_FLUID);
    // Astral Catalyst Base
    public static final Werkstoff AstralCatalystReforged = new Werkstoff(
        new short[] { 0x41, 0x69, 0xe1 },
        "AstralCatalystReforged",
        subscriptNumbers("Aμⁿ4?3"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        Offset_Mix + 5,
        TextureSet.SET_FLUID);
    // Astral Catalyst Base Excited
    public static final Werkstoff AstralCatalystReforgedExcited = new Werkstoff(
        new short[] { 0x41, 0x84, 0xe1 },
        "AstralCatalystReforgedExcited",
        subscriptNumbers("(Aμⁿ4?3)*"),
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        Offset_Mix + 6,
        TextureSet.SET_FLUID);
    // endregion

    // region
    // Astro Residue
    public static final Werkstoff AstralResidue = new Werkstoff(
        new short[] { 0x19, 0x19, 0x70 },
        "AstralResidue",
        "Aμ°",
        new Werkstoff.Stats(),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        Offset_Production + 1,
        TextureSet.SET_FLUID);
    // Lapotron Enhanced Fluid
    public static final Werkstoff LapotronEnhancedFluid = new Werkstoff(
        new short[] { 0x64, 0x95, 0xed },
        "LapotronEnhancedFluid",
        "[-Lapo-Lapo-]",
        new Werkstoff.Stats(),
        Werkstoff.Types.COMPOUND,
        new Werkstoff.GenerationFeatures().disable()
            .addCells(),
        Offset_Production + 2,
        TextureSet.SET_FLUID);
    // Superconductor Flux Raw
    public static final Werkstoff SuperconductorFluxRaw = new Werkstoff(
        new short[] { 0x69, 0x69, 0x69 },
        "SuperconductorFluxRaw",
        subscriptNumbers("(?Aμⁿ2If3?)*12D*12M11If*10SpNt8In7Nq+6Nq*5Or5(⚷⚙⚷Ni4Ti6)4(✧◇✧)4"),
        new Werkstoff.Stats().setMeltingPoint(3),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust()
            .addMolten()
            .enforceUnification(),
        Offset_Production + 3,
        TextureSet.SET_MAGNETIC);
    // Superconductor Fluid
    // We regard 64UHV, 16UEV, 4UIV, 1UMV SC as 4 Ingots of SX
    public static final Werkstoff SuperconductorFlux = new Werkstoff(
        new short[] { 0xC0, 0xC0, 0xC0 },
        "SuperconductorFlux",
        "Sx",
        new Werkstoff.Stats().setMeltingPoint(1),
        Werkstoff.Types.ELEMENT,
        new Werkstoff.GenerationFeatures().disable()
            .addCells()
            .enforceUnification(),
        Offset_Production + 4,
        TextureSet.SET_FLUID);

    // endregion

    @Override
    public void run() {}
}
