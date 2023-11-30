package com.rhynia.gtnh.append.recipe.container.GTRecipePool;

import static com.rhynia.gtnh.append.util.UtilValues.lensInf;
import static com.rhynia.gtnh.append.util.UtilValues.lensMagic;
import static gregtech.api.enums.TierEU.RECIPE_UHV;
import static gregtech.api.enums.TierEU.RECIPE_UV;
import static gregtech.api.enums.TierEU.RECIPE_ZPM;
import static gregtech.api.util.GT_RecipeBuilder.BUCKETS;
import static gregtech.api.util.GT_RecipeBuilder.INGOTS;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;

import net.minecraftforge.fluids.FluidRegistry;

import com.dreammaster.gthandler.CustomItemList;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;

public class VALaserEngraverRecipePool implements IRecipePool {

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
            .duration(15 * SECONDS)
            .addTo(LE);
        // 活性晶体
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_ModHandler.getModItem("gregtech", "gt.metaitem.03", 1, 32071), lensMagic)
            .itemOutputs(GT_ModHandler.getModItem("gregtech", "gt.metaitem.01", 4, 32668))
            .fluidInputs(VA_GregtechMaterialPool.Astro.getFluid(50))
            .noOptimize()
            .eut(160000)
            .duration(45 * SECONDS)
            .addTo(LE);
        // 皮米晶圆
        GT_Values.RA.stdBuilder()
            .itemInputs(CustomItemList.RawPicoWafer.get(1), lensInf)
            .itemOutputs(CustomItemList.PicoWafer.get(4))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(100),
                Materials.Neutronium.getMolten(2 * 144))
            .noOptimize()
            .eut(6000000)
            .duration(300)
            .addTo(LE);
        // 激活催化剂
        GT_Values.RA.stdBuilder()
            .itemInputs(lensInf)
            .fluidInputs(VA_WerkstoffMaterialPool.astroCatalystBase.getFluidOrGas(1000))
            .fluidOutputs(VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(1000))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(45 * SECONDS)
            .addTo(LE);
        // endregion

        // region 星辉燃料
        // 星辉燃料MKI
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_WerkstoffMaterialPool.Originiums.get(OrePrefixes.lens, 0))
            .fluidInputs(
                Materials.Naquadria.getMolten(16 * INGOTS),
                MyMaterial.naquadahBasedFuelMkI.getFluidOrGas(8 * BUCKETS))
            .fluidOutputs(VA_WerkstoffMaterialPool.astroFuelMKI.getFluidOrGas(BUCKETS))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(25 * SECONDS)
            .addTo(LE);
        // endregion

        // region 矩阵
        // 兰波顿矩阵
        GT_Values.RA.stdBuilder()
            .itemInputs(VAItemList.ItemLapoMatrix.get(1), lensInf)
            .fluidInputs(Materials.EnergeticAlloy.getMolten(1440))
            .itemOutputs(
                ItemList.Circuit_Parts_Crystal_Chip_Master.get(64),
                ItemList.Circuit_Parts_Crystal_Chip_Master.get(64),
                ItemList.Circuit_Parts_Crystal_Chip_Master.get(64),
                ItemList.Circuit_Parts_Crystal_Chip_Master.get(64))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(15 * SECONDS)
            .addTo(LE);
        // 晶体矩阵-绿
        GT_Values.RA.stdBuilder()
            .itemInputs(VAItemList.ItemCrystalMatrix.get(1), lensInf)
            .fluidInputs(Materials.Europium.getMolten(16 * INGOTS))
            .itemOutputs(
                ItemList.Circuit_Chip_CrystalCPU.get(64),
                ItemList.Circuit_Chip_CrystalCPU.get(64),
                ItemList.Circuit_Chip_CrystalCPU.get(64),
                ItemList.Circuit_Chip_CrystalCPU.get(64))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(25 * SECONDS)
            .addTo(LE);
        // 晶体矩阵-红
        GT_Values.RA.stdBuilder()
            .itemInputs(VAItemList.ItemCrystalMatrix.get(1), lensInf)
            .fluidInputs(Materials.Americium.getMolten(16 * INGOTS))
            .itemOutputs(ItemList.Circuit_Chip_CrystalSoC.get(64), ItemList.Circuit_Chip_CrystalSoC.get(32))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(15 * SECONDS)
            .addTo(LE);
        // endregion
    }
}
