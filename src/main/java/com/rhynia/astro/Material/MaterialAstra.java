package com.rhynia.astro.Material;

import static gregtech.api.enums.OrePrefixes.*;

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
        .addFluid()
        .setAspects(Arrays.asList(new TC_AspectStack(TC_Aspects.ORDO, 3), new TC_AspectStack(TC_Aspects.ALIENIS, 1)))
        .constructMaterial();

    public static Materials AstroInf = new MaterialBuilder(251, TextureSet.SET_DIAMOND, "AstroInf").setName("AstroInf")
        .setRGBA(0, 191, 255, 255)
        .addDustItems()
        .addGemItems()
        .setTransparent(true)
        .addCell()
        .setLiquidTemperature(50)
        .addFluid()
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
        AstroInf.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.SMELTING_TO_FLUID);

        ingot.mNotGeneratedItems.add(Astro);
        ingot.mNotGeneratedItems.add(AstroInf);
        nugget.mNotGeneratedItems.add(Astro);
        nugget.mNotGeneratedItems.add(AstroInf);
        screw.mNotGeneratedItems.add(Astro);
        screw.mNotGeneratedItems.add(AstroInf);
        rod.mNotGeneratedItems.add(Astro);
        rod.mNotGeneratedItems.add(AstroInf);
        stick.mNotGeneratedItems.add(Astro);
        stick.mNotGeneratedItems.add(AstroInf);
        stickLong.mNotGeneratedItems.add(Astro);
        stickLong.mNotGeneratedItems.add(AstroInf);
        bolt.mNotGeneratedItems.add(Astro);
        bolt.mNotGeneratedItems.add(AstroInf);
        itemCasing.mNotGeneratedItems.add(Astro);
        itemCasing.mNotGeneratedItems.add(AstroInf);
        plate.mNotGeneratedItems.add(Astro);
        plate.mNotGeneratedItems.add(AstroInf);
        gemFlawless.mNotGeneratedItems.add(AstroInf);
        gemChipped.mNotGeneratedItems.add(AstroInf);
        gemExquisite.mNotGeneratedItems.add(AstroInf);
        gemFlawed.mNotGeneratedItems.add(AstroInf);
    }
}
