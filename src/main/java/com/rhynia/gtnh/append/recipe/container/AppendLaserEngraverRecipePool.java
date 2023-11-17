package com.rhynia.gtnh.append.recipe.container;

import static gregtech.api.enums.TierEU.RECIPE_UHV;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;

public class AppendLaserEngraverRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map LE = GT_Recipe.GT_Recipe_Map.sLaserEngraverRecipes;
        final ItemStack lensMagic = GT_Utility
            .copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroMagic, 1));
        final ItemStack lensInf = GT_Utility
            .copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroInf, 1));

        // region 光刻
        // 异氙
        GT_Values.RA.stdBuilder()
            .itemInputs(MaterialGTMethod.AstroMagic.getDust(1), lensMagic)
            .fluidInputs(Materials.UUMatter.getFluid(16), WerkstoffLoader.Xenon.getFluidOrGas(1000))
            .fluidOutputs(FluidRegistry.getFluidStack("xenoxene", 500))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(15 * 20)
            .addTo(LE);
        // 活性晶体
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_ModHandler.getModItem("gregtech", "gt.metaitem.03", 1, 32071), lensMagic)
            .itemOutputs(GT_ModHandler.getModItem("gregtech", "gt.metaitem.01", 4, 32668))
            .fluidInputs(MaterialGTMethod.Astro.getFluid(50))
            .noOptimize()
            .eut(160000)
            .duration(900)
            .addTo(LE);
        // endregion
    }
}
