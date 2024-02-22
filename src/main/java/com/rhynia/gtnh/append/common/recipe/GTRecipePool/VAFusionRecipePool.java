package com.rhynia.gtnh.append.common.recipe.GTRecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_ZPM;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;
import static gregtech.api.util.GT_RecipeConstants.FUSION_THRESHOLD;

import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.common.material.VA_Materials;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;

public class VAFusionRecipePool implements IRecipePool {

    @Override
    public void loadRecipesCompleteInit() {
        final IRecipeMap FS = RecipeMaps.fusionRecipes;
        // ACR
        GT_Values.RA.stdBuilder()
            .fluidInputs(VA_Materials.AstralCatalystBaseExcited.getFluidOrGas(500), MyMaterial.orundum.getMolten(288))
            .fluidOutputs(VA_Materials.AstralCatalystReforged.getFluidOrGas(125))
            .duration(2 * SECONDS)
            .eut(RECIPE_ZPM)
            .metadata(FUSION_THRESHOLD, 400000000)
            .addTo(FS);
    }
}
