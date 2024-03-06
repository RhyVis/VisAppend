package com.rhynia.gtnh.append.common.recipe.VARecipePool;

import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.recipe.builder.VA_RecipeBuilder;
import com.rhynia.gtnh.append.api.util.AssemblyLineRecipeHelper;
import com.rhynia.gtnh.append.common.loader.VA_ItemList;
import com.rhynia.gtnh.append.common.material.VA_Materials;

import gregtech.api.recipe.RecipeMap;

public class VASCompatibilityRecipePool implements IRecipePool {

    private final RecipeMap<?> RAL = AssemblyLineRecipeHelper.compatibilityRALMap;

    @Override
    public void loadRecipesCompleteInit() {
        // Test
        VA_RecipeBuilder.builder()
            .itemInputs(VA_ItemList.ItemUltimate.get(1))
            .fluidInputs(VA_Materials.Astrium.getMolten(1))
            .itemOutputs(VA_ItemList.Test01.get(1))
            .eut(100_000_000)
            .duration(128)
            .addTo(RAL);
        // Maybe future use one day
    }
}
