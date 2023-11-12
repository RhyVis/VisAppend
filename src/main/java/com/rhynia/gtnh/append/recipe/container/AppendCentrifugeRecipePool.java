package com.rhynia.gtnh.append.recipe.container;

import static gregtech.api.enums.TierEU.RECIPE_MV;

import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_Recipe;

public class AppendCentrifugeRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map CF = GT_Recipe.GT_Recipe_Map.sMultiblockCentrifugeRecipes;

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
    }
}
