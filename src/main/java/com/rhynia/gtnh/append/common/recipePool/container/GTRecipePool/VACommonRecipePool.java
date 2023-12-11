package com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool;

import static gregtech.api.enums.TierEU.RECIPE_MV;
import static gregtech.api.util.GT_RecipeBuilder.BUCKETS;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;

import com.github.bartimaeusnek.bartworks.util.BWRecipes;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.material.VAMaterials;
import com.rhynia.gtnh.append.common.recipePool.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_Recipe;
import gtPlusPlus.core.material.ELEMENT;

public class VACommonRecipePool implements IRecipePool {

    @Override
    public void loadRecipesPostInit() {}

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map EMS = GT_Recipe.GT_Recipe_Map.sElectroMagneticSeparatorRecipes;
        final GT_Recipe.GT_Recipe_Map IMP = BWRecipes.instance.eicMap;

        // region 星辉
        // 磁析神秘
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Thaumium.getDust(8))
            .itemOutputs(Materials.Iron.getDust(6), VAMaterials.Astrium.get(OrePrefixes.dust, 4))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(21 * SECONDS)
            .addTo(EMS);
        // 聚爆星辉
        GT_Values.RA.stdBuilder()
            .itemInputs(VAMaterials.AstriumInfinity.get(OrePrefixes.dust, 1))
            .fluidInputs(VAMaterials.Astrium.getMolten(125))
            .itemOutputs(VAItemList.AstriumInfinityGem.get(1))
            .noOptimize()
            .eut(6000000)
            .duration(1)
            .addTo(IMP);
        // (临时)聚爆海珀珍
        // TODO 为星辉残留提供新用法
        GT_Values.RA.stdBuilder()
            .fluidInputs(VAMaterials.AstralResidue.getFluidOrGas(256 * BUCKETS))
            .itemOutputs(ELEMENT.STANDALONE.HYPOGEN.getDust(16))
            .noOptimize()
            .eut(8000000)
            .duration(1)
            .addTo(IMP);
        // endregion

    }
}
