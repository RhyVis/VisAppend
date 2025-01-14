package com.rhynia.gtnh.append.common.recipe.GTRecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_EV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_HV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_LV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_LuV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.common.material.VA_Materials;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;

public class VAChemicalReactorRecipePool implements IRecipePool {

    @Override
    public void loadRecipesCompleteInit() {
        final IRecipeMap LCR = RecipeMaps.multiblockChemicalReactorRecipes;

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
            .duration(21 * SECONDS)
            .addTo(LCR);
        // 磷酸
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Phosphate.getDust(8))
            .fluidInputs(Materials.Water.getFluid(12000))
            .fluidOutputs(Materials.PhosphoricAcid.getFluid(10000))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(21 * SECONDS)
            .addTo(LCR);
        // 氟锑酸
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Antimony.getDust(8), GT_Utility.getIntegratedCircuit(20))
            .fluidInputs(Materials.HydrofluoricAcid.getFluid(96000))
            .fluidOutputs(
                new FluidStack(FluidRegistry.getFluid("fluoroantimonic acid"), 16000),
                Materials.Hydrogen.getGas(80000))
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(45 * SECONDS)
            .addTo(LCR);
        // 钯金属粉处理
        GT_Values.RA.stdBuilder()
            .itemInputs(WerkstoffLoader.PDMetallicPowder.get(OrePrefixes.dust, 8), GT_Utility.getIntegratedCircuit(24))
            .itemOutputs(WerkstoffLoader.PDRawPowder.get(OrePrefixes.dust, 8))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(15 * SECONDS)
            .addTo(LCR);
        // 干细胞
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_Materials.Astrium.get(OrePrefixes.dust, 12), Materials.Osmiridium.getDust(8))
            .itemOutputs(
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.03", 64, 32073),
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.03", 64, 32073),
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.03", 64, 32073),
                Materials.MysteriousCrystal.getDust(2))
            .fluidInputs(Materials.GrowthMediumSterilized.getFluid(4000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("bacterialsludge"), 4000))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(21 * SECONDS)
            .addTo(LCR);
        // endregion
    }
}
