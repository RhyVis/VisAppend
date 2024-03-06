package com.rhynia.gtnh.append.common.recipe.GTRecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.BUCKETS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UEV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UHV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_ZPM;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;
import static gregtech.api.enums.Mods.SuperSolarPanels;

import net.minecraftforge.fluids.FluidRegistry;

import com.dreammaster.gthandler.CustomItemList;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.common.loader.VA_ItemList;
import com.rhynia.gtnh.append.common.material.VA_Materials;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GT_ModHandler;

public class VALaserEngraverRecipePool implements IRecipePool {

    @Override
    public void loadRecipesCompleteInit() {
        final IRecipeMap LE = RecipeMaps.laserEngraverRecipes;

        // region 光刻
        // 异氙
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_Materials.AstriumMagic.get(OrePrefixes.dust, 1), VA_ItemList.LensAstriumMagic.get(0))
            .fluidInputs(Materials.UUMatter.getFluid(16), WerkstoffLoader.Xenon.getFluidOrGas(1000))
            .fluidOutputs(FluidRegistry.getFluidStack("xenoxene", 500))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(15 * SECONDS)
            .addTo(LE);
        // 活性晶体
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.03", 1, 32071),
                VA_ItemList.LensAstriumMagic.get(0))
            .itemOutputs(GT_ModHandler.getModItem("gregtech", "gt.metaitem.01", 4, 32668))
            .fluidInputs(VA_Materials.Astrium.getMolten(50))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(45 * SECONDS)
            .addTo(LE);
        // 皮米晶圆
        GT_Values.RA.stdBuilder()
            .itemInputs(CustomItemList.RawPicoWafer.get(1), VA_ItemList.LensAstriumInfinity.get(0))
            .itemOutputs(CustomItemList.PicoWafer.get(4))
            .fluidInputs(
                VA_Materials.AstralCatalystBaseExcited.getFluidOrGas(100),
                Materials.Neutronium.getMolten(2 * 144))
            .fluidOutputs(VA_Materials.AstralResidue.getFluidOrGas(50))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(15 * SECONDS)
            .addTo(LE);
        // 生物活性晶圆
        GT_Values.RA.stdBuilder()
            .itemInputs(ItemList.Circuit_Silicon_Wafer6.get(1), VA_ItemList.LensAstriumMagic.get(0))
            .itemOutputs(ItemList.Circuit_Wafer_Bioware.get(4))
            .fluidInputs(Materials.BioMediumSterilized.getFluid(4000), VA_Materials.Astrium.getMolten(10 * INGOTS))
            .fluidOutputs(Materials.UUMatter.getFluid(500))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(40 * SECONDS)
            .addTo(LE);
        // 光子强化晶圆
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Circuit_Silicon_Wafer6.get(1),
                Materials.Glowstone.getNanite(1),
                VA_ItemList.LensAstriumMagic.get(0),
                GT_ModHandler.getModItem(SuperSolarPanels.ID, "solarsplitter", 0L, 0))
            .itemOutputs(ItemList.Circuit_Silicon_Wafer7.get(4L))
            .fluidInputs(
                WerkstoffLoader.Oganesson.getFluidOrGas(2500),
                VA_Materials.AstralCatalystBaseExcited.getFluidOrGas(800))
            .fluidOutputs(VA_Materials.AstralResidue.getFluidOrGas(400), VA_Materials.Astrium.getMolten(500))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(20 * SECONDS)
            .addTo(LE);
        // 增殖星辉
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_Materials.Astrium.get(OrePrefixes.dust, 64), VA_ItemList.LensAstriumInfinity.get(0))
            .fluidOutputs(VA_Materials.Astrium.getMolten(96 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(60 * SECONDS)
            .addTo(LE);
        // 激活催化剂
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_ItemList.LensAstriumInfinity.get(0))
            .fluidInputs(VA_Materials.AstralCatalystBase.getFluidOrGas(1000))
            .fluidOutputs(VA_Materials.AstralCatalystBaseExcited.getFluidOrGas(1000))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(45 * SECONDS)
            .addTo(LE);
        // endregion

        // region 星辉燃料
        // 星辉燃料MKI
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_ItemList.LensOriginium.get(0))
            .fluidInputs(
                Materials.Naquadria.getMolten(16 * INGOTS),
                MyMaterial.naquadahBasedFuelMkI.getFluidOrGas(8 * BUCKETS))
            .fluidOutputs(VA_Materials.AstralFuelMkI.getFluidOrGas(BUCKETS))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(25 * SECONDS)
            .addTo(LE);
        // endregion

        // region 矩阵
        // 兰波顿矩阵
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_ItemList.LapoMatrix.get(1), VA_ItemList.LensAstriumInfinity.get(0))
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
            .itemInputs(VA_ItemList.CrystalMatrix.get(1), VA_ItemList.LensAstriumInfinity.get(0))
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
            .itemInputs(VA_ItemList.CrystalMatrix.get(1), VA_ItemList.LensAstriumInfinity.get(0))
            .fluidInputs(Materials.Americium.getMolten(16 * INGOTS))
            .itemOutputs(ItemList.Circuit_Chip_CrystalSoC.get(64), ItemList.Circuit_Chip_CrystalSoC.get(32))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(15 * SECONDS)
            .addTo(LE);
        // endregion
    }
}
