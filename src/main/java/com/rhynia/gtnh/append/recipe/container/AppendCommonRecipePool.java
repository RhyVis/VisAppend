package com.rhynia.gtnh.append.recipe.container;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.common.machine.recipe.GTAppendRecipe;
import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gregtech.common.fluid.GT_Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import static gregtech.api.enums.TierEU.*;

public class AppendCommonRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map LCR = GT_Recipe.GT_Recipe_Map.sMultiblockChemicalRecipes;
        final GT_Recipe.GT_Recipe_Map CF = GT_Recipe.GT_Recipe_Map.sMultiblockCentrifugeRecipes;
        final GT_Recipe.GT_Recipe_Map EMS = GT_Recipe.GT_Recipe_Map.sElectroMagneticSeparatorRecipes;
        final GT_Recipe.GT_Recipe_Map LE = GT_Recipe.GT_Recipe_Map.sLaserEngraverRecipes;

        //region 星辉产生
        //磁析神秘 FeMa=>Fe+Ao
        GT_Values.RA.stdBuilder()
            .itemInputs(
                Materials.Thaumium.getDust(8))
            .itemOutputs(
                Materials.Iron.getDust(6),
                MaterialGTMethod.Astro.getDust(4)
            )
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(21 * 20)
            .addTo(EMS);
        //离心秘银 FePtMa=>CSMa+Pt+Ao
        GT_Values.RA.stdBuilder()
            .itemInputs(
                Materials.Mithril.getDust(6))
            .itemOutputs(
                Materials.Pyrotheum.getDust(1),
                Materials.Platinum.getDust(1),
                MaterialGTMethod.Astro.getDust(2)
            )
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(21 * 20)
            .addTo(CF);
        //endregion

        //region 异氙光刻
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroMagic, 1)),
                MaterialGTMethod.AstroMagic.getDust(1))
            .fluidInputs(
                Materials.UUMatter.getFluid(16),
                WerkstoffLoader.Xenon.getFluidOrGas(1000)
            )
            .fluidOutputs(FluidRegistry.getFluidStack("xenoxene",500))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(15 * 20)
            .addTo(LE);
        //endregion
    }
}
