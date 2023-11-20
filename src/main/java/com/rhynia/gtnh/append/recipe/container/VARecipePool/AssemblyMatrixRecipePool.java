package com.rhynia.gtnh.append.recipe.container.VARecipePool;

import static com.rhynia.gtnh.append.util.UtilValues.lensInf;
import static com.rhynia.gtnh.append.util.UtilValues.lensMagic;
import static gregtech.api.enums.TierEU.RECIPE_UV;

import com.rhynia.gtnh.append.common.machine.mapRecipe.VARecipe;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;

public class AssemblyMatrixRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map AM = VARecipe.instance.AssemblyMatrixRecipes;
        // 透镜配方
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensMagic,
                VA_GregtechMaterialPool.Astro.getDust(16),
                Materials.Redstone.getDust(1))
            .fluidInputs(Materials.Water.getFluid(32000))
            .itemOutputs(lensInf)
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * 20)
            .addTo(AM);
    }
}
