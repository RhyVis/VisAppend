package com.rhynia.gtnh.append.common.recipe.GTRecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_EV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import com.rhynia.gtnh.append.api.enums.refHelper.Tier;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GT_Utility;

public class VAAssemblerRecipePool implements IRecipePool {

    private static final IRecipeMap A = RecipeMaps.assemblerRecipes;

    @Override
    public void loadRecipesCompleteInit() {
        loadMainRecipes();
        loadWirelessHatchRecipesEasy();
    }

    public void loadMainRecipes() {
        // Stocking input hatch
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Super_Tank_EV.get(1),
                Tier.HV.getCircuit(1),
                Tier.HV.getComponent(Tier.Component.Electric_Pump, 1),
                GT_Utility.getIntegratedCircuit(23))
            .itemOutputs(ItemList.Hatch_Input_ME.get(1))
            .fluidInputs(Tier.HV.getSolderStack(72))
            .eut(RECIPE_EV)
            .duration(8 * SECONDS)
            .addTo(A);
        // Crafting input bus
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Hatch_Input_Bus_ME.get(1),
                Tier.HV.getCircuit(2),
                Tier.HV.getComponent(Tier.Component.Robot_Arm, 2),
                GT_Utility.getIntegratedCircuit(23))
            .itemOutputs(ItemList.Hatch_CraftingInput_Bus_ME_ItemOnly.get(1))
            .fluidInputs(Tier.HV.getSolderStack(72))
            .eut(RECIPE_EV)
            .duration(8 * SECONDS)
            .addTo(A);
        // Crafting input buffer
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Hatch_Input_Bus_ME.get(1),
                ItemList.Hatch_Input_ME.get(1),
                Tier.EV.getCircuit(2),
                Tier.EV.getComponent(Tier.Component.Robot_Arm, 2),
                GT_Utility.getIntegratedCircuit(23))
            .itemOutputs(ItemList.Hatch_CraftingInput_Bus_ME.get(1))
            .fluidInputs(Tier.EV.getSolderStack(72))
            .eut(RECIPE_EV)
            .duration(8 * SECONDS)
            .addTo(A);
    }

    public void loadWirelessHatchRecipesEasy() {

        int time = 10 * SECONDS;
        Tier[] FULL = { Tier.ULV, Tier.LV, Tier.MV, Tier.HV, Tier.EV, Tier.IV, Tier.LuV, Tier.ZPM, Tier.UV, Tier.UHV,
            Tier.UEV, Tier.UIV, Tier.UMV, Tier.UXV };
        Tier[] MULTI = { Tier.EV, Tier.IV, Tier.LuV, Tier.ZPM, Tier.UV, Tier.UHV, Tier.UEV, Tier.UIV, Tier.UMV,
            Tier.UXV };
        Tier[] LASER = { Tier.IV, Tier.LuV, Tier.ZPM, Tier.UV, Tier.UHV, Tier.UEV, Tier.UIV, Tier.UMV, Tier.UXV };

        for (var tier : FULL) {
            GT_Values.RA.stdBuilder()
                .itemInputs(
                    tier.getEnergyHatch(1),
                    tier.getCircuit(1),
                    tier.getComponent(Tier.Component.Emitter, 1),
                    tier.getComponent(Tier.Component.Sensor, 1),
                    GT_Utility.getIntegratedCircuit(22))
                .fluidInputs(tier.getSolderStack(INGOTS))
                .itemOutputs(tier.getEnergyWireless(1))
                .eut(tier.getVoltageRecipe())
                .duration(time)
                .addTo(A);
            GT_Values.RA.stdBuilder()
                .itemInputs(
                    tier.getDynamoHatch(1),
                    tier.getCircuit(1),
                    tier.getComponent(Tier.Component.Emitter, 1),
                    tier.getComponent(Tier.Component.Sensor, 1),
                    GT_Utility.getIntegratedCircuit(22))
                .fluidInputs(tier.getSolderStack(INGOTS))
                .itemOutputs(tier.getDynamoWireless(1))
                .eut(tier.getVoltageRecipe())
                .duration(time)
                .addTo(A);
        }

        for (var tier : MULTI) {
            GT_Values.RA.stdBuilder()
                .itemInputs(
                    tier.getEnergyHatch4A(1),
                    tier.getCircuit(2),
                    tier.getComponent(Tier.Component.Emitter, 2),
                    tier.getComponent(Tier.Component.Sensor, 2),
                    GT_Utility.getIntegratedCircuit(22))
                .fluidInputs(tier.getSolderStack(4 * INGOTS))
                .itemOutputs(tier.getEnergyWireless4A(1))
                .eut(tier.getVoltageRecipe())
                .duration(time)
                .addTo(A);
            GT_Values.RA.stdBuilder()
                .itemInputs(
                    tier.getEnergyHatch16A(1),
                    tier.getCircuit(4),
                    tier.getComponent(Tier.Component.Emitter, 4),
                    tier.getComponent(Tier.Component.Sensor, 4),
                    GT_Utility.getIntegratedCircuit(22))
                .fluidInputs(tier.getSolderStack(16 * INGOTS))
                .itemOutputs(tier.getEnergyWireless16A(1))
                .eut(tier.getVoltageRecipe())
                .duration(time)
                .addTo(A);
            GT_Values.RA.stdBuilder()
                .itemInputs(
                    tier.getEnergyHatch64A(1),
                    tier.getCircuit(8),
                    tier.getComponent(Tier.Component.Emitter, 8),
                    tier.getComponent(Tier.Component.Sensor, 8),
                    GT_Utility.getIntegratedCircuit(22))
                .fluidInputs(tier.getSolderStack(64 * INGOTS))
                .itemOutputs(tier.getEnergyWireless64A(1))
                .eut(tier.getVoltageRecipe())
                .duration(time)
                .addTo(A);
        }

        for (var tier : LASER) {
            for (int i = 1; i < 6; i++) {
                GT_Values.RA.stdBuilder()
                    .itemInputs(
                        tier.getLaserTarget(i, 1),
                        tier.getCircuit(16),
                        tier.getComponent(Tier.Component.Emitter, 16),
                        tier.getComponent(Tier.Component.Sensor, 16),
                        GT_Utility.getIntegratedCircuit(22))
                    .fluidInputs(tier.getSolderStack(64 * INGOTS))
                    .itemOutputs(tier.getLaserWireless(i, 1))
                    .eut(tier.getVoltageRecipe())
                    .duration(time)
                    .addTo(A);
            }
        }
    }
}
