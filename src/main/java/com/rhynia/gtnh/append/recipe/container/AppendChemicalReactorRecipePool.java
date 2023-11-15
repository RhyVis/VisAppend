package com.rhynia.gtnh.append.recipe.container;

import static gregtech.api.enums.TierEU.RECIPE_HV;
import static gregtech.api.enums.TierEU.RECIPE_LuV;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;

public class AppendChemicalReactorRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map LCR = GT_Recipe.GT_Recipe_Map.sMultiblockChemicalRecipes;

        // region 杂项
        // 磷化钙
        GT_Values.RA.stdBuilder()
            .itemInputs(
                Materials.Calcium.getDust(3),
                Materials.Phosphate.getDust(8),
                GT_Utility.getIntegratedCircuit(2))
            .itemOutputs(Materials.TricalciumPhosphate.getGems(5))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(21 * 20)
            .addTo(LCR);
        // 干细胞
        GT_Values.RA.stdBuilder()
            .itemInputs(MaterialGTMethod.Astro.getDust(12), Materials.Osmiridium.getDust(8))
            .itemOutputs(
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.03", 64, 32073),
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.03", 64, 32073),
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.03", 64, 32073),
                Materials.MysteriousCrystal.getDust(2))
            .fluidInputs(Materials.GrowthMediumSterilized.getFluid(4000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("bacterialsludge"), 4000))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(21 * 20)
            .addTo(LCR);
        // endregion
    }
}