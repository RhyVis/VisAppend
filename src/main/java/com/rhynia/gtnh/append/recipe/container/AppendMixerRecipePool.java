package com.rhynia.gtnh.append.recipe.container;

import static gregtech.api.enums.TierEU.*;

import com.rhynia.gtnh.append.common.material.MaterialBartworksMethod;
import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;

public class AppendMixerRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map MX = GT_Recipe.GT_Recipe_Map.sMixerRecipes;

        // region 杂项
        // 深渊铁
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Iron.getDust(1), Materials.Thaumium.getDust(3), GT_Utility.getIntegratedCircuit(2))
            .itemOutputs(Materials.ShadowIron.getDust(3))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(21 * 20)
            .addTo(MX);
        // 星辉催化剂
        GT_Values.RA.stdBuilder()
            .itemInputs(
                MaterialGTMethod.AstroInf.getDust(8),
                Materials.InfinityCatalyst.getDust(12),
                GT_Utility.getIntegratedCircuit(12))
            .itemOutputs(MaterialBartworksMethod.astroCatalystBase.get(OrePrefixes.dust, 64))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * 20)
            .addTo(MX);
        // endregion

        // region 生物培养基
        // 培养基
        GT_Values.RA.stdBuilder()
            .itemInputs(
                Materials.Calcium.getDust(3),
                MaterialGTMethod.Astro.getDust(4),
                GT_Utility.getIntegratedCircuit(15))
            .fluidInputs(GT_ModHandler.getDistilledWater(4000))
            .fluidOutputs(Materials.GrowthMediumRaw.getFluid(8000))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(15 * 20)
            .addTo(MX);
        // 生物培养基
        GT_Values.RA.stdBuilder()
            .itemInputs(
                Materials.NetherStar.getDust(2),
                MaterialGTMethod.Astro.getDust(6),
                GT_Utility.getIntegratedCircuit(16))
            .fluidInputs(GT_ModHandler.getDistilledWater(4000))
            .fluidOutputs(Materials.BioMediumRaw.getFluid(8000))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(20 * 20)
            .addTo(MX);
        // endregion
    }
}
