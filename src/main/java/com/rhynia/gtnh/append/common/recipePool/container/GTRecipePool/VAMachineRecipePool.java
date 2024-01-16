package com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool;

import static com.rhynia.gtnh.append.api.util.enums.RecipeValues.HOURS;
import static com.rhynia.gtnh.append.api.util.enums.RecipeValues.RECIPE_UEV;
import static com.rhynia.gtnh.append.api.util.enums.RecipeValues.RECIPE_UHV;
import static com.rhynia.gtnh.append.api.util.enums.RecipeValues.SECONDS;
import static gregtech.api.util.GT_RecipeConstants.AssemblyLine;
import static gregtech.api.util.GT_RecipeConstants.RESEARCH_ITEM;
import static gregtech.api.util.GT_RecipeConstants.RESEARCH_TIME;

import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.recipePool.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;

public class VAMachineRecipePool implements IRecipePool {

    @Override
    public void loadRecipesPostInit() {}

    @Override
    public void loadRecipes() {
        // Infinite Liquid Air Hatch
        GT_Values.RA.stdBuilder()
            .metadata(RESEARCH_ITEM, GT_ModHandler.getModItem("gregtech", "gt.blockmachines", 1, 18999))
            .metadata(RESEARCH_TIME, 2 * HOURS)
            .itemInputs(
                GT_ModHandler.getModItem("gregtech", "gt.blockmachines", 32, 18999),
                ItemList.Electric_Pump_UEV.get(32),
                ItemList.Field_Generator_UEV.get(12),
                new Object[] { OrePrefixes.circuit.get(Materials.Infinite), 8 },
                Materials.Infinity.getPlates(4),
                GT_ModHandler.getModItem("eternalsingularity", "eternal_singularity", 1),
                ItemList.UHV_Coil.get(1),
                Materials.Infinity.getPlates(4))
            .fluidInputs(Materials.Neutronium.getMolten(1440))
            .itemOutputs(VAItemList.InfiniteLiquidAirHatch.get(1))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(140 * SECONDS)
            .addTo(AssemblyLine);

        // Astra Forge
        GT_Values.RA.stdBuilder()
            .metadata(RESEARCH_ITEM, VAItemList.AstriumInfinityGem.get(1))
            .metadata(RESEARCH_TIME, 2 * HOURS)
            .itemInputs(
                ItemList.Machine_IV_LaserEngraver.get(64),
                ItemList.Field_Generator_UHV.get(8),
                ItemList.Sensor_UHV.get(8),
                Materials.Infinity.getPlates(12))
            .fluidInputs(Materials.CosmicNeutronium.getMolten(10 * 144))
            .itemOutputs(VAItemList.AstraForge.get(1))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(95 * SECONDS)
            .addTo(AssemblyLine);
    }
}
