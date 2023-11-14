package com.rhynia.gtnh.append.recipe.container;

import static gregtech.api.enums.TierEU.*;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_Recipe;

public class AppendCentrifugeRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map CF = GT_Recipe.GT_Recipe_Map.sCentrifugeRecipes;

        // region 杂项系列
        // 褐煤制煤
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Lignite.getDust(4))
            .itemOutputs(Materials.Coal.getDust(3))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * 20)
            .addTo(CF);
        // 氟碳铈
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Bastnasite.getDust(12))
            .itemOutputs(
                Materials.Cerium.getDust(2),
                Materials.Gadolinium.getDust(1),
                Materials.Samarium.getDust(1),
                Materials.Carbon.getDust(2))
            .fluidOutputs(Materials.Oxygen.getGas(6000), Materials.Fluorine.getGas(2000))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(8 * 20)
            .addTo(CF);
        // 褐铜
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Rubracium.getDust(12))
            .itemOutputs(
                Materials.Copper.getDust(8),
                Materials.Ledox.getDust(2),
                WerkstoffLoader.Roquesit.get(OrePrefixes.dust, 1),
                Materials.Firestone.getDust(1),
                MaterialGTMethod.Astro.getDust(1))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(8 * 20)
            .addTo(CF);
        // 深空冰
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.CallistoIce.getDust(4))
            .itemOutputs(Materials.Ice.getDust(2), Materials.Cryotheum.getDust(1), MaterialGTMethod.Astro.getDust(1))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(8 * 20)
            .addTo(CF);
        // 离心秘银
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Mithril.getDust(6))
            .itemOutputs(
                Materials.Pyrotheum.getDust(1),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 2),
                MaterialGTMethod.Astro.getDust(2))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(21 * 20)
            .addTo(CF);
        // 离心深空秘银
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Mytryl.getDust(8))
            .itemOutputs(
                Materials.Mithril.getDust(4),
                Materials.Thaumium.getDust(3),
                Materials.AstralSilver.getDust(1),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 2),
                MaterialGTMethod.Astro.getDust(2))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(21 * 20)
            .addTo(CF);
        // endregion
    }
}
