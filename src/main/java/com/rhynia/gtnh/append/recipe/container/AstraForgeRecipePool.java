package com.rhynia.gtnh.append.recipe.container;

import static gregtech.api.enums.Mods.GTPlusPlus;
import static gregtech.api.enums.TierEU.*;

import com.rhynia.gtnh.append.common.machine.recipe.GTAppendRecipe;
import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;

public class AstraForgeRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map AF = GTAppendRecipe.instance.AstraForgeRecipes;
        // Test
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(1), ItemList.Circuit_Ultimate.get(1))
            .fluidInputs(Materials.Hydrogen.getPlasma(16000))
            .itemOutputs(GT_ModHandler.getModItem(GTPlusPlus.ID, "particleBase", 1, 14))
            .fluidOutputs(Materials.Helium.getPlasma(4000))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(3580 * 20)
            .addTo(AF);

        // 润滑油
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.01", 0, 24252),
                MaterialGTMethod.Astro.getDust(16),
                Materials.Redstone.getDust(1))
            .fluidInputs(Materials.Water.getFluid(32000))
            .fluidOutputs(Materials.Lubricant.getFluid(256000))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * 20)
            .addTo(AF);

        //合成宇宙中子素 SpNt
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.01", 0, 24251),
                MaterialGTMethod.Astro.getDust(4),
                Materials.BlackPlutonium.getDust(16))
            .fluidInputs(Materials.Helium.getFluid(2000))
            .itemOutputs(Materials.CosmicNeutronium.getDust(12))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(30 * 20)
            .addTo(AF);

        //合成阳光化合物 Su
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.01", 0, 24251),
                MaterialGTMethod.Astro.getDust(1),
                Materials.Glowstone.getDust(16))
            .fluidInputs(Materials.Hydrogen.getFluid(12000))
            .itemOutputs(Materials.Sunnarium.getDust(64))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(22 * 20)
            .addTo(AF);

        //合成超能硅岩 Nq*
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.01", 0, 24251),
                MaterialGTMethod.AstroInf.getDust(12),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64))
            .fluidInputs(
                Materials.NaquadahEnriched.getFluid(144*32))
            .itemOutputs(
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(36 * 20)
            .addTo(AF);

        //合成合成玉 Or
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.01", 0, 24252),
                MaterialGTMethod.Astro.getDust(6),
                Materials.Naquadah.getDust(6))
            .fluidInputs(
                Materials.Helium.getFluid(12000),
                Materials.Quantium.getFluid(4000))
            .itemOutputs(GT_ModHandler.getModItem("bartworks", "gt.bwMetaGenerateddust", 8, 10023))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(10 * 20)
            .addTo(AF);
    }
}
