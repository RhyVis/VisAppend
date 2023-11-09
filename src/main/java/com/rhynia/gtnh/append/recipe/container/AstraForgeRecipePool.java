package com.rhynia.gtnh.append.recipe.container;

import static gregtech.api.enums.Mods.GTPlusPlus;
import static gregtech.api.enums.TierEU.RECIPE_UMV;
import static gregtech.api.enums.TierEU.RECIPE_UV;

import com.rhynia.gtnh.append.common.machine.recipe.GTAppendRecipe;
import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;

public class AstraForgeRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map AF = GTAppendRecipe.instance.AstraForgeRecipes;
        // Test
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(1), ItemList.Circuit_Ultimate.get(1))
            .fluidInputs(Materials.Hydrogen.getPlasma(16000))
            .itemOutputs(GT_ModHandler.getModItem(GTPlusPlus.ID, "particleBase", 1, 14))
            .fluidOutputs(Materials.Helium.getPlasma(4000))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(3580 * 20)
            .addTo(AF);

        // 润滑油
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.01", 0, 24252),
                MaterialGTMethod.Astro.getDust(16),
                Materials.Redstone.getDust(1))
            .fluidInputs(Materials.Water.getFluid(32000))
            .fluidOutputs(Materials.Lubricant.getFluid(256000))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * 20)
            .addTo(AF);
    }
}
