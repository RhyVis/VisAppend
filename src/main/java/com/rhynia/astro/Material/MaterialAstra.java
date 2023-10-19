package com.rhynia.astro.Material;

import static gregtech.api.enums.OrePrefixes.ingot;
import static gregtech.api.enums.OrePrefixes.nugget;

import java.util.Arrays;

import gregtech.api.enums.MaterialBuilder;
import gregtech.api.enums.Materials;
import gregtech.api.enums.SubTag;
import gregtech.api.enums.TC_Aspects;
import gregtech.api.enums.TC_Aspects.TC_AspectStack;
import gregtech.api.enums.TextureSet;

public class MaterialAstra {

    public static Materials Astro = new MaterialBuilder(250, TextureSet.SET_METALLIC, "星辉").setName("Astro")
        .setRGBA(150, 219, 252, 255)
        .addDustItems()
        .addGemItems()
        .addFluid()
        .addPlasma()
        .setLiquidTemperature(200)
        .addCell()
        .setAspects(
            Arrays.asList(new TC_AspectStack(TC_Aspects.ORDO, 3), new TC_AspectStack(TC_Aspects.PRAECANTATIO, 1)))
        .constructMaterial();

    public static Materials AstroInf = new MaterialBuilder(251, TextureSet.SET_METALLIC, "星极").setName("AstroInf")
        .setRGBA(150, 219, 252, 255)
        .addDustItems()
        .addGemItems()
        .addFluid()
        .addPlasma()
        .addGas()
        .setGasTemperature(8000)
        .setLiquidTemperature(250)
        .addCell()
        .setAspects(
            Arrays.asList(new TC_AspectStack(TC_Aspects.ORDO, 15), new TC_AspectStack(TC_Aspects.PRAECANTATIO, 1)))
        .constructMaterial();

    public static void init() {
        Astro.mChemicalFormula = "Ao";
        AstroInf.mChemicalFormula = "Aoⁿ";

        Astro.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        AstroInf.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);

        ingot.mNotGeneratedItems.add(Astro);
        ingot.mNotGeneratedItems.add(AstroInf);
        nugget.mNotGeneratedItems.add(Astro);
        nugget.mNotGeneratedItems.add(AstroInf);
    }
}
