package com.rhynia.gtnh.append.common.recipe.VARecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_MAX;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UMV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import com.Nxer.TwistSpaceTechnology.common.GTCMItemList;
import com.rhynia.gtnh.append.api.enums.VA_Mods;
import com.rhynia.gtnh.append.api.enums.refHelper.BasicRef;
import com.rhynia.gtnh.append.api.enums.refHelper.Tier;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.util.GT_Utility;

public class VASQuarkRefactoringRecipePool implements IRecipePool {

    private final IRecipeMap QA = AppendRecipeMaps.quarkRefactoringRecipes;

    @Override
    public void loadRecipesCompleteInit() {
        loadMainRecipes();
        if (VA_Mods.TwistSpaceTechnology.isModLoaded()) {
            loadTSTRecipes();
        }
    }

    private void loadMainRecipes() {
        // 量子反常
        GT_Values.RA.stdBuilder()
            .itemInputs(Tier.UXV.getCircuitWrap(1), BasicRef.getFusionMatrixCatalyst())
            .fluidInputs(Materials.Duranium.getMolten(256 * INGOTS), MaterialsUEVplus.SpaceTime.getMolten(64 * INGOTS))
            .itemOutputs(GT_Utility.copyAmountUnsafe(4096, BasicRef.getQuantumAnomaly(1)))
            .eut(RECIPE_UMV)
            .duration(16 * SECONDS)
            .addTo(QA);
    }

    private void loadTSTRecipes() {
        // 临界光子
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.copyAmountUnsafe(128, BasicRef.getQuantumAnomaly(1)),
                BasicRef.getFusionMatrixCatalyst())
            .itemOutputs(GT_Utility.copyAmountUnsafe(512, GTCMItemList.CriticalPhoton.get(1)))
            .eut(RECIPE_MAX)
            .duration(16 * SECONDS)
            .addTo(QA);
    }
}
