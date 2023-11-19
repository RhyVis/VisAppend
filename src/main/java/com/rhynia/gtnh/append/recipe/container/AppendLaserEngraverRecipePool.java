package com.rhynia.gtnh.append.recipe.container;

import static com.rhynia.gtnh.append.util.UtilValues.lensInf;
import static com.rhynia.gtnh.append.util.UtilValues.lensMagic;
import static gregtech.api.enums.TierEU.RECIPE_UHV;
import static gregtech.api.enums.TierEU.RECIPE_ZPM;

import net.minecraftforge.fluids.FluidRegistry;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.common.AppendItemList;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;

public class AppendLaserEngraverRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map LE = GT_Recipe.GT_Recipe_Map.sLaserEngraverRecipes;

        // region 光刻
        // 异氙
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_GregtechMaterialPool.AstroMagic.getDust(1), lensMagic)
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
            .fluidInputs(VA_GregtechMaterialPool.Astro.getFluid(50))
            .noOptimize()
            .eut(160000)
            .duration(900)
            .addTo(LE);
        // 激活催化剂
        GT_Values.RA.stdBuilder()
            .itemInputs(lensInf)
            .fluidInputs(VA_WerkstoffMaterialPool.astroCatalystBase.getFluidOrGas(1000))
            .fluidOutputs(VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(1000))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(900)
            .addTo(LE);
        // endregion

        // region 兰波顿
        // 兰波顿矩阵
        GT_Values.RA.stdBuilder()
            .itemInputs(AppendItemList.ItemLapoMatrix.get(4), lensInf)
            .fluidInputs(Materials.EnergeticAlloy.getMolten(1440))
            .itemOutputs(
                ItemList.Circuit_Parts_Crystal_Chip_Master.get(64),
                ItemList.Circuit_Parts_Crystal_Chip_Master.get(64),
                ItemList.Circuit_Parts_Crystal_Chip_Master.get(64),
                ItemList.Circuit_Parts_Crystal_Chip_Master.get(64))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(15 * 20)
            .addTo(LE);
        // endregion
    }
}
