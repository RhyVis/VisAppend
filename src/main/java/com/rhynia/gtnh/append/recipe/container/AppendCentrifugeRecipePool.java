package com.rhynia.gtnh.append.recipe.container;

import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_Recipe;

import static gregtech.api.enums.TierEU.*;

public class AppendCentrifugeRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map CF = GT_Recipe.GT_Recipe_Map.sMultiblockCentrifugeRecipes;

        // region 杂项系列
        // 褐煤制煤
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Lignite.getDust(4))
            .itemOutputs(Materials.Coal.getDust(3))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8*20)
            .addTo(CF);
        // 氟碳铈
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Bastnasite.getDust(12))
            .itemOutputs(
                Materials.Cerium.getDust(2),
                Materials.Gadolinium.getDust(1),
                Materials.Samarium.getDust(1),
                Materials.Carbon.getDust(2))
            .fluidOutputs(
                Materials.Oxygen.getGas(6000),
                Materials.Fluorine.getGas(2000)
            )
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(8*20)
            .addTo(CF);
        // 离心秘银 FePtMa=>CSMa+Pt+Ao
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Mithril.getDust(6))
            .itemOutputs(
                Materials.Pyrotheum.getDust(1),
                Materials.Platinum.getDust(1),
                MaterialGTMethod.Astro.getDust(2))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(21 * 20)
            .addTo(CF);
        // endregion
    }
}
