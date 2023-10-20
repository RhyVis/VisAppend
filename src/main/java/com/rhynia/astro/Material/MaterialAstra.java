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

    public static Materials Astro = new MaterialBuilder(250, TextureSet.SET_GEM_VERTICAL, "Astro").setName("Astro")
        .setRGBA(30, 144, 252, 255)
        .addDustItems()
        .addGemItems()
        .addCell()
        .setLiquidTemperature(200)
        .setGasTemperature(7400)
        .addFluid()
        .setAspects(Arrays.asList(new TC_AspectStack(TC_Aspects.ORDO, 3), new TC_AspectStack(TC_Aspects.ALIENIS, 1)))
        .constructMaterial();

    public static Materials AstroInf = new MaterialBuilder(251, TextureSet.SET_GEM_VERTICAL, "AstroInf").setName("AstroInf")
        .setRGBA(0, 191, 255, 255)
        .addDustItems()
        .addGemItems()
        .addCell()
        .setLiquidTemperature(50)
        .setGasTemperature(8000)
        .addGas()
        .setAspects(
            Arrays.asList(
                new TC_AspectStack(TC_Aspects.ORDO, 15),
                new TC_AspectStack(TC_Aspects.ALIENIS, 9),
                new TC_AspectStack(TC_Aspects.POTENTIA, 5)))
        .constructMaterial();

    static {
        Astro.mChemicalFormula = "Ao";
        AstroInf.mChemicalFormula = "Aoⁿ";

        Astro.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.SMELTING_TO_FLUID);
        AstroInf.add(SubTag.CRYSTAL, SubTag.NO_SMASHING);

        ingot.mNotGeneratedItems.add(Astro);
        ingot.mNotGeneratedItems.add(AstroInf);
        nugget.mNotGeneratedItems.add(Astro);
        nugget.mNotGeneratedItems.add(AstroInf);
    }
}
