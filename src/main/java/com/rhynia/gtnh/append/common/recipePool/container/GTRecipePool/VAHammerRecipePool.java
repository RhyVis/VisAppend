package com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool;

import static gregtech.api.enums.TierEU.RECIPE_LV;
import static gregtech.api.enums.TierEU.RECIPE_LuV;
import static gregtech.api.enums.TierEU.RECIPE_ZPM;
import static gregtech.api.util.GT_RecipeBuilder.BUCKETS;
import static gregtech.api.util.GT_RecipeBuilder.INGOTS;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.dreammaster.gthandler.CustomItemList;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.common.recipePool.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_Recipe;
import gregtech.common.items.CombType;
import gregtech.loaders.misc.GT_Bees;

public class VAHammerRecipePool implements IRecipePool {

    @Override
    public void loadRecipesPostInit() {}

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map HM = GT_Recipe.GT_Recipe_Map.sHammerRecipes;

        // region 杂项
        // Am
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Bees.combs.getStackForType(CombType.AMERICIUM, 16), VA_GregtechMaterialPool.Astro.getDust(4))
            .itemOutputs(Materials.Americium.getDust(32))
            .fluidOutputs(VA_GregtechMaterialPool.Astro.getFluid(125))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(25 * SECONDS)
            .addTo(HM);
        // Nt
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Bees.combs.getStackForType(CombType.NEUTRONIUM, 16),
                VA_GregtechMaterialPool.Astro.getDust(12))
            .itemOutputs(Materials.Neutronium.getIngots(2))
            .fluidOutputs(VA_GregtechMaterialPool.Astro.getFluid(12), Materials.Neutronium.getMolten(2304))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(25 * SECONDS)
            .addTo(HM);
        // Kevlar
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Bees.combs.getStackForType(CombType.KEVLAR, 16), VA_GregtechMaterialPool.Astro.getDust(12))
            .fluidOutputs(
                VA_GregtechMaterialPool.Astro.getFluid(12),
                new FluidStack(FluidRegistry.getFluid("molten.kevlar"), 4608))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(25 * SECONDS)
            .addTo(HM);
        // endregion

        // region 矩阵
        // 兰波顿矩阵
        GT_Values.RA.stdBuilder()
            .itemInputs(CustomItemList.LapotronDust.get(64), VA_GregtechMaterialPool.Astro.getDust(16))
            .itemOutputs(VAItemList.LapoMatrix.get(4))
            .fluidInputs(VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(12 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(40 * SECONDS)
            .addTo(HM);
        // 晶体矩阵
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Aluminiumoxide.getDust(64), VA_GregtechMaterialPool.Astro.getDust(16))
            .itemOutputs(VAItemList.CrystalMatrix.get(16))
            .fluidInputs(
                Materials.Europium.getMolten(4 * INGOTS),
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(4 * BUCKETS))
            .fluidOutputs(VA_WerkstoffMaterialPool.astroResidue.getFluidOrGas(2 * BUCKETS))
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
