package com.rhynia.gtnh.append.common.recipe.GTRecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.BUCKETS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_LV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_LuV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_ZPM;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import com.dreammaster.gthandler.CustomItemList;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.util.FluidHelper;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.material.VAMaterials;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.common.items.CombType;
import gregtech.loaders.misc.GT_Bees;

public class VAHammerRecipePool implements IRecipePool {

    @Override
    public void loadRecipesPostInit() {}

    @Override
    public void loadRecipesCompleteInit() {
        final IRecipeMap HM = RecipeMaps.hammerRecipes;

        // region 杂项
        // Nt
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Bees.combs.getStackForType(CombType.NEUTRONIUM, 16),
                VAMaterials.Astrium.get(OrePrefixes.dust, 12))
            .itemOutputs(Materials.Neutronium.getIngots(2))
            .fluidOutputs(VAMaterials.AstriumMagic.getMolten(12), Materials.Neutronium.getMolten(2304))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(25 * SECONDS)
            .addTo(HM);
        // SpNt
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Bees.combs.getStackForType(CombType.COSMICNEUTRONIUM, 16),
                VAMaterials.Astrium.get(OrePrefixes.dust, 12))
            .itemOutputs(Materials.CosmicNeutronium.getIngots(2))
            .fluidOutputs(VAMaterials.AstriumMagic.getMolten(12), Materials.CosmicNeutronium.getMolten(2304))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(25 * SECONDS)
            .addTo(HM);
        // Kevlar
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Bees.combs.getStackForType(CombType.KEVLAR, 16),
                VAMaterials.Astrium.get(OrePrefixes.dust, 12))
            .fluidOutputs(
                VAMaterials.AstriumMagic.getMolten(12),
                FluidHelper.getFluidStackByName("molten.kevlar", 4608))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(25 * SECONDS)
            .addTo(HM);
        // endregion

        // region 矩阵
        // 兰波顿矩阵
        GT_Values.RA.stdBuilder()
            .itemInputs(CustomItemList.LapotronDust.get(64), VAMaterials.Astrium.get(OrePrefixes.dust, 16))
            .itemOutputs(VAItemList.LapoMatrix.get(4))
            .fluidInputs(VAMaterials.LapotronEnhancedFluid.getFluidOrGas(12 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(40 * SECONDS)
            .addTo(HM);
        // 晶体矩阵
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Aluminiumoxide.getDust(64), VAMaterials.Astrium.get(OrePrefixes.dust, 16))
            .itemOutputs(VAItemList.CrystalMatrix.get(16))
            .fluidInputs(Materials.Europium.getMolten(4 * INGOTS), Materials.Americium.getMolten(4 * INGOTS))
            .fluidOutputs()
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(45 * SECONDS)
            .addTo(HM);
        // endregion

        // region 致密云母
        // 致密云母
        GT_Values.RA.stdBuilder()
            .itemInputs(CustomItemList.MicaInsulatorSheet.get(4))
            .itemOutputs(VAItemList.DenseMicaInsulatorFoil.get(1))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(5 * SECONDS)
            .addTo(HM);
        // endregion
    }
}
