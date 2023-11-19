package com.rhynia.gtnh.append.recipe.container;

import static gregtech.api.enums.TierEU.*;

import net.minecraftforge.fluids.FluidRegistry;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.github.bartimaeusnek.bartworks.util.BWRecipes;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;

public class AppendCommonRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map EMS = GT_Recipe.GT_Recipe_Map.sElectroMagneticSeparatorRecipes;
        final GT_Recipe.GT_Recipe_Map LE = GT_Recipe.GT_Recipe_Map.sLaserEngraverRecipes;
        final GT_Recipe.GT_Recipe_Map IMP = BWRecipes.instance.eicMap;

        // region 星辉
        // 磁析神秘
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Thaumium.getDust(8))
            .itemOutputs(Materials.Iron.getDust(6), VA_GregtechMaterialPool.Astro.getDust(4))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(21 * 20)
            .addTo(EMS);
        // 聚爆星辉
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_GregtechMaterialPool.AstroInf.getDust(1))
            .fluidInputs(VA_GregtechMaterialPool.Astro.getFluid(125))
            .itemOutputs(VA_GregtechMaterialPool.AstroInf.getGems(1))
            .noOptimize()
            .eut(6000000)
            .duration(1)
            .addTo(IMP);
        // endregion

        // region 异氙光刻
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility
                    .copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 1)),
                VA_GregtechMaterialPool.AstroMagic.getDust(1))
            .fluidInputs(Materials.UUMatter.getFluid(16), WerkstoffLoader.Xenon.getFluidOrGas(1000))
            .fluidOutputs(FluidRegistry.getFluidStack("xenoxene", 500))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(15 * 20)
            .addTo(LE);
        // endregion
    }
}
