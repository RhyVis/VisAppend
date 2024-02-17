package com.rhynia.gtnh.append.common.recipe.GTRecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_LV;

import com.rhynia.gtnh.append.api.interfaces.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;

public class VAAssemblerRecipePool implements IRecipePool {

    private final IRecipeMap AS = RecipeMaps.assemblerRecipes;

    @Override
    public void loadRecipesCompleteInit() {
        // region Pipe
        // Bronze
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Bronze, 1),
                Materials.Bronze.getPlates(4),
                GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Bronze, 4),
                GT_Utility.getIntegratedCircuit(4))
            .itemOutputs(ItemList.Casing_Pipe_Bronze.get(1))
            .eut(RECIPE_LV)
            .duration(64)
            .addTo(AS);
        // Steel
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Steel, 1),
                Materials.Steel.getPlates(4),
                GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Steel, 4),
                GT_Utility.getIntegratedCircuit(4))
            .itemOutputs(ItemList.Casing_Pipe_Steel.get(1))
            .eut(RECIPE_LV)
            .duration(64)
            .addTo(AS);
        // Titanium
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Titanium, 1),
                Materials.Titanium.getPlates(4),
                GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Titanium, 4),
                GT_Utility.getIntegratedCircuit(4))
            .itemOutputs(ItemList.Casing_Pipe_Titanium.get(1))
            .eut(RECIPE_LV)
            .duration(64)
            .addTo(AS);
        // TungstenSteel
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.TungstenSteel, 1),
                Materials.TungstenSteel.getPlates(4),
                GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.TungstenSteel, 4),
                GT_Utility.getIntegratedCircuit(4))
            .itemOutputs(ItemList.Casing_Pipe_TungstenSteel.get(1))
            .eut(RECIPE_LV)
            .duration(64)
            .addTo(AS);
        // Polytetrafluoroethylene CF4
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Polytetrafluoroethylene, 1),
                Materials.Polytetrafluoroethylene.getPlates(4),
                GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Polytetrafluoroethylene, 4),
                GT_Utility.getIntegratedCircuit(4))
            .itemOutputs(ItemList.Casing_Pipe_Polytetrafluoroethylene.get(1))
            .eut(RECIPE_LV)
            .duration(64)
            .addTo(AS);
        // Polybenzimidazole PBI
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Polybenzimidazole, 1),
                Materials.Polybenzimidazole.getPlates(4),
                GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Polybenzimidazole, 4),
                GT_Utility.getIntegratedCircuit(4))
            .itemOutputs(ItemList.Casing_Pipe_Polybenzimidazole.get(1))
            .eut(RECIPE_LV)
            .duration(64)
            .addTo(AS);
        // endregion
    }
}
