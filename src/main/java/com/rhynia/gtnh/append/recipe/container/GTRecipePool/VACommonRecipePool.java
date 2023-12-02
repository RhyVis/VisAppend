package com.rhynia.gtnh.append.recipe.container.GTRecipePool;

import static gregtech.api.enums.TierEU.RECIPE_MV;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;

import com.github.bartimaeusnek.bartworks.util.BWRecipes;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_Recipe;

public class VACommonRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map EMS = GT_Recipe.GT_Recipe_Map.sElectroMagneticSeparatorRecipes;
        final GT_Recipe.GT_Recipe_Map IMP = BWRecipes.instance.eicMap;

        // region 星辉
        // 磁析神秘
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Thaumium.getDust(8))
            .itemOutputs(Materials.Iron.getDust(6), VA_GregtechMaterialPool.Astro.getDust(4))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(21 * SECONDS)
            .addTo(EMS);
        // 聚爆星辉
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_GregtechMaterialPool.AstroInf.getDust(1))
            .fluidInputs(VA_GregtechMaterialPool.Astro.getFluid(125))
            .itemOutputs(VA_GregtechMaterialPool.AstroInf.getGems(1))
            .noOptimize()
            .eut(6000000)
            .duration(1)
            .addTo(IMP);
        // endregion

    }
}
