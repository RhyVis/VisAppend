package com.rhynia.gtnh.append.recipe.container;

import static gregtech.api.enums.TierEU.RECIPE_LuV;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.dreammaster.gthandler.CustomItemList;
import com.rhynia.gtnh.append.common.AppendItemList;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_Recipe;
import gregtech.common.items.CombType;
import gregtech.loaders.misc.GT_Bees;

public class AppendHammerRecipePool implements IRecipePool {

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
            .duration(25 * 20)
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
            .duration(25 * 20)
            .addTo(HM);
        // Kevlar
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Bees.combs.getStackForType(CombType.KEVLAR, 16), VA_GregtechMaterialPool.Astro.getDust(12))
            .fluidOutputs(
                VA_GregtechMaterialPool.Astro.getFluid(12),
                new FluidStack(FluidRegistry.getFluid("molten.kevlar"), 4608))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(25 * 20)
            .addTo(HM);
        // endregion

        // region 兰波顿
        // 兰波顿矩阵
        GT_Values.RA.stdBuilder()
            .itemInputs(CustomItemList.LapotronDust.get(64), VA_GregtechMaterialPool.Astro.getDust(16))
            .itemOutputs(AppendItemList.ItemLapoMatrix.get(16))
            .fluidInputs(VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(12000))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(40 * 20)
            .addTo(HM);
        // endregion
    }
}
