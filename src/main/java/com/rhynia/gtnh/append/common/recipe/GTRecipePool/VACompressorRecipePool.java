package com.rhynia.gtnh.append.common.recipe.GTRecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import com.rhynia.gtnh.append.api.interfaces.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;

public class VACompressorRecipePool implements IRecipePool {

    @Override
    public void loadRecipesCompleteInit() {}

    @Override
    public void loadRecipesPostInit() {
        final IRecipeMap CP = RecipeMaps.compressorRecipes;

        // 青金石
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Lapis.getDust(9))
            .itemOutputs(Materials.Lapis.getBlocks(1))
            .noOptimize()
            .eut(2)
            .duration(15 * SECONDS)
            .addTo(CP);
    }
}
