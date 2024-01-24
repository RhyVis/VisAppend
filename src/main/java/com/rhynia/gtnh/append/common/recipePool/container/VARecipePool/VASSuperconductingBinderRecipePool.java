package com.rhynia.gtnh.append.common.recipePool.container.VARecipePool;

import static com.rhynia.gtnh.append.api.util.enums.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.util.enums.RecipeValues.RECIPE_UMV;
import static com.rhynia.gtnh.append.api.util.enums.RecipeValues.SECONDS;

import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.material.VAMaterials;
import com.rhynia.gtnh.append.common.recipePool.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.interfaces.IRecipeMap;

public class VASSuperconductingBinderRecipePool implements IRecipePool {

    private final IRecipeMap SB = AppendRecipeMaps.voidEnergyGeneratorRecipes;

    @Override
    public void loadRecipesPostInit() {}

    @Override
    public void loadRecipes() {
        // region 超导配方

        // 终极配方-测试
        GT_Values.RA.stdBuilder()
            .itemInputs(VAItemList.Test.get(1))
            .fluidInputs(VAMaterials.SuperconductorFlux.getFluidOrGas(16 * INGOTS))
            .itemOutputs(ItemList.Superconducting_Magnet_Solenoid_UMV.get(1))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(160 * SECONDS)
            .addTo(SB);
        // endregion
    }

}
