package com.rhynia.gtnh.append.recipe.container;

import static gregtech.api.enums.TierEU.RECIPE_UEV;
import static gregtech.api.enums.TierEU.RECIPE_UHV;
import static gregtech.api.util.GT_RecipeBuilder.HOURS;
import static gregtech.api.util.GT_RecipeConstants.*;

import com.rhynia.gtnh.append.common.AppendItemList;
import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;

public class AppendMachineRecipePool implements IRecipePool {

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
            .fluidInputs(
                Materials.CosmicNeutronium.getMolten(1440),
                MaterialGTMethod.Astro.getFluid(16000),
                MaterialGTMethod.AstroInf.getFluid(8000))
            .itemOutputs(AppendItemList.InfiniteLiquidAirHatch.get(1))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(140 * 20)
            .addTo(AssemblyLine);

        // Astra Forge
        GT_Values.RA.stdBuilder()
            .metadata(RESEARCH_ITEM, MaterialGTMethod.AstroInf.getGems(1))
            .metadata(RESEARCH_TIME, 2 * HOURS)
            .itemInputs(
                ItemList.Machine_IV_LaserEngraver.get(64),
                ItemList.Field_Generator_UHV.get(8),
                ItemList.Sensor_UHV.get(8),
                Materials.Infinity.getPlates(12),
                MaterialGTMethod.AstroMagic.getGems(16),
                GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroMagic, 32),
                GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroMagic, 32),
                MaterialGTMethod.AstroMagic.getGems(16))
            .fluidInputs(
                Materials.CosmicNeutronium.getMolten(10 * 144),
                MaterialGTMethod.Astro.getFluid(16000),
                MaterialGTMethod.AstroInf.getFluid(8000))
            .itemOutputs(AppendItemList.AstraForge.get(1))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(95 * 20)
            .addTo(AssemblyLine);
    }
}
