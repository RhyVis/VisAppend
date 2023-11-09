package com.rhynia.gtnh.append.common.material;

import static gregtech.api.enums.OrePrefixes.*;

import java.util.Arrays;

import gregtech.api.enums.*;
import gregtech.api.enums.TC_Aspects.TC_AspectStack;

public class MaterialGTMethod implements Runnable {

    public static final Materials Astro = new MaterialBuilder(250, TextureSet.SET_GEM_VERTICAL, "Astro")
        .setName("Astro")
        .setRGBA(30, 144, 252, 255)
        .addDustItems()
        .addGemItems()
        .addCell()
        .addMetalItems()
        .setLiquidTemperature(200)
        .addFluid()
        .setAspects(
            Arrays.asList(
                new TC_AspectStack(TC_Aspects.ORDO, 3),
                new TC_AspectStack(TC_Aspects.ALIENIS, 1),
                new TC_AspectStack(TC_Aspects.VACUOS, 1)))
        .constructMaterial();

    public static final Materials AstroInf = new MaterialBuilder(251, TextureSet.SET_DIAMOND, "AstroInf")
        .setName("AstroInf")
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

    public static final Materials AstroMagic = new MaterialBuilder(252, TextureSet.SET_DIAMOND, "AstroMagic")
        .setName("AstroMagic")
        .setRGBA(0, 32, 178, 170)
        .addDustItems()
        .addGemItems()
        .setTransparent(true)
        .addCell()
        .setLiquidTemperature(170)
        .addFluid()
        .setAspects(
            Arrays.asList(
                new TC_AspectStack(TC_Aspects.ORDO, 7),
                new TC_AspectStack(TC_Aspects.ALIENIS, 6),
                new TC_AspectStack(TC_Aspects.PRAECANTATIO, 4)))
        .constructMaterial();

    static {
        Astro.mChemicalFormula = "Ao";
        AstroInf.mChemicalFormula = "Aoⁿ";
        AstroMagic.mChemicalFormula = "AoMa";

        Astro.add(SubTag.CRYSTAL, SubTag.NO_SMASHING);
        AstroInf.add(SubTag.CRYSTAL, SubTag.NO_SMASHING);
        AstroMagic.add(SubTag.CRYSTAL, SubTag.NO_SMASHING);

        ingot.mNotGeneratedItems.add(Astro);
        ingot.mNotGeneratedItems.add(AstroInf);
        ingot.mNotGeneratedItems.add(AstroMagic);
        nugget.mNotGeneratedItems.add(Astro);
        nugget.mNotGeneratedItems.add(AstroInf);
        nugget.mNotGeneratedItems.add(AstroMagic);
        screw.mNotGeneratedItems.add(Astro);
        screw.mNotGeneratedItems.add(AstroInf);
        screw.mNotGeneratedItems.add(AstroMagic);
        rod.mNotGeneratedItems.add(Astro);
        rod.mNotGeneratedItems.add(AstroInf);
        rod.mNotGeneratedItems.add(AstroMagic);
        stick.mNotGeneratedItems.add(Astro);
        stick.mNotGeneratedItems.add(AstroInf);
        stick.mNotGeneratedItems.add(AstroMagic);
        stickLong.mNotGeneratedItems.add(Astro);
        stickLong.mNotGeneratedItems.add(AstroInf);
        stickLong.mNotGeneratedItems.add(AstroMagic);
        bolt.mNotGeneratedItems.add(Astro);
        bolt.mNotGeneratedItems.add(AstroInf);
        bolt.mNotGeneratedItems.add(AstroMagic);
        itemCasing.mNotGeneratedItems.add(Astro);
        itemCasing.mNotGeneratedItems.add(AstroInf);
        itemCasing.mNotGeneratedItems.add(AstroMagic);
        plate.mNotGeneratedItems.add(Astro);
        plate.mNotGeneratedItems.add(AstroInf);
        plate.mNotGeneratedItems.add(AstroMagic);
        gemFlawless.mNotGeneratedItems.add(AstroInf);
        gemFlawless.mNotGeneratedItems.add(AstroMagic);
        gemChipped.mNotGeneratedItems.add(AstroInf);
        gemChipped.mNotGeneratedItems.add(AstroMagic);
        gemExquisite.mNotGeneratedItems.add(AstroInf);
        gemExquisite.mNotGeneratedItems.add(AstroMagic);
        gemFlawed.mNotGeneratedItems.add(AstroInf);
        gemFlawed.mNotGeneratedItems.add(AstroMagic);
    }

    @Override
    public void run() {}
}
