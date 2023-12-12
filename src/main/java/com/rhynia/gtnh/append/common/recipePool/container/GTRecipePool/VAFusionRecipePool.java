package com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool;

import static gregtech.api.enums.TierEU.RECIPE_ZPM;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;
import static gregtech.api.util.GT_RecipeConstants.FUSION_THRESHOLD;

import com.rhynia.gtnh.append.common.material.VAMaterials;
import com.rhynia.gtnh.append.common.recipePool.IRecipePool;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;

public class VAFusionRecipePool implements IRecipePool {

    @Override
    public void loadRecipesPostInit() {}

    @Override
    public void loadRecipes() {
        final RecipeMap<?> FS = RecipeMaps.fusionRecipes;
        // ACR
        GT_Values.RA.stdBuilder()
            .fluidInputs(VAMaterials.AstralCatalystBaseExcited.getFluidOrGas(500), MyMaterial.orundum.getMolten(288))
            .fluidOutputs(VAMaterials.AstralCatalystReforged.getFluidOrGas(125))
            .duration(2 * SECONDS)
            .eut(RECIPE_ZPM)
            .metadata(FUSION_THRESHOLD, 400000000)
            .addTo(FS);
    }
}
