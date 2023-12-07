package com.rhynia.gtnh.append.recipe.container.VARecipePool;

import static gregtech.api.enums.TierEU.RECIPE_UEV;
import static gregtech.api.util.GT_RecipeBuilder.BUCKETS;
import static gregtech.api.util.GT_RecipeBuilder.INGOTS;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;

import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.machine.recipeMap.VA_RecipeAdder;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.HeatingCoilLevel;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;

public class VASTranscendentReactorRecipePool implements IRecipePool {

    final GT_Recipe.GT_Recipe_Map TR = VA_RecipeAdder.instance.sTranscendentReactorRecipes;
    final ItemStack esCata = GT_ModHandler.getModItem("eternalsingularity", "eternal_singularity", 0);

    @Override
    public void loadRecipesPostInit() {}

    @Override
    public void loadRecipes() {

        // region 特殊材料制造
        // 超导通流
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_WerkstoffMaterialPool.Primogem.get(OrePrefixes.lens, 0),
                VA_WerkstoffMaterialPool.Originiums.get(OrePrefixes.lens, 0))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(32 * BUCKETS),
                VA_WerkstoffMaterialPool.superconductingFluxRaw.getMolten(72 * INGOTS))
            .fluidOutputs(
                VA_WerkstoffMaterialPool.superconductingFlux.getFluidOrGas(64 * INGOTS),
                VA_WerkstoffMaterialPool.astroResidue.getFluidOrGas(16 * BUCKETS))
            .specialValue((int) HeatingCoilLevel.UEV.getHeat())
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(30 * SECONDS)
            .addTo(TR);
        // endregion
    }
}
