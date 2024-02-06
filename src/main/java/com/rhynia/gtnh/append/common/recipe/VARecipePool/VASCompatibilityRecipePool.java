package com.rhynia.gtnh.append.common.recipe.VARecipePool;

import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.recipe.builder.VA_RecipeBuilder;
import com.rhynia.gtnh.append.api.util.AssemblyLineRecipeHelper;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.material.VAMaterials;

import gregtech.api.recipe.RecipeMap;

public class VASCompatibilityRecipePool implements IRecipePool {

    private final RecipeMap<?> RAL = AssemblyLineRecipeHelper.compatibilityRALMap;

    @Override
    public void loadRecipesCompleteInit() {
        // Test
        VA_RecipeBuilder.builder()
            .itemInputs(VAItemList.ItemUltimate.get(1))
            .fluidInputs(VAMaterials.Astrium.getMolten(1))
            .itemOutputs(VAItemList.Test.get(1))
            .eut(100_000_000)
            .duration(128)
            .addTo(RAL);
        // Maybe future use one day
    }

    @Override
    public void loadRecipesPostInit() {}
}
