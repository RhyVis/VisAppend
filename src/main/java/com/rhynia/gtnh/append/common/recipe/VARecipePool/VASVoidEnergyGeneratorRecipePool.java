package com.rhynia.gtnh.append.common.recipe.VARecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UMV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import com.rhynia.gtnh.append.api.enums.refHelper.SCPart;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.common.VA_ItemList;
import com.rhynia.gtnh.append.common.material.VA_Materials;

import gregtech.api.enums.GT_Values;
import gregtech.api.interfaces.IRecipeMap;

public class VASVoidEnergyGeneratorRecipePool implements IRecipePool {

    private final IRecipeMap VEG = AppendRecipeMaps.voidEnergyGeneratorRecipes;

    @Override
    public void loadRecipesCompleteInit() {
        // region 超导配方

        // 终极配方-测试
        GT_Values.RA.stdBuilder()
            .itemInputs(SCPart.UMV.getSolenoid(64))
            .fluidInputs(VA_Materials.SuperconductorFlux.getFluidOrGas(16 * INGOTS))
            .itemOutputs(VA_ItemList.Test01.get(1))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(999 * SECONDS)
            .addTo(VEG);
        // endregion
    }

}
