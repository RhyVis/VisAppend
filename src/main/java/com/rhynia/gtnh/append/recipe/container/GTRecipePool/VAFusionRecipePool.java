package com.rhynia.gtnh.append.recipe.container.GTRecipePool;

import static gregtech.api.enums.TierEU.RECIPE_ZPM;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;
import static gregtech.api.util.GT_RecipeConstants.FUSION_THRESHOLD;

import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.util.GT_Recipe;

public class VAFusionRecipePool implements IRecipePool {

    @Override
    public void loadRecipesPostInit() {}

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map FS = GT_Recipe.GT_Recipe_Map.sFusionRecipes;
        // ACR
        GT_Values.RA.stdBuilder()
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(500),
                MyMaterial.orundum.getMolten(288))
            .fluidOutputs(VA_WerkstoffMaterialPool.astroCatalystReforged.getFluidOrGas(125))
            .duration(2 * SECONDS)
            .eut(RECIPE_ZPM)
            .metadata(FUSION_THRESHOLD, 400000000)
            .addTo(FS);
    }
}
