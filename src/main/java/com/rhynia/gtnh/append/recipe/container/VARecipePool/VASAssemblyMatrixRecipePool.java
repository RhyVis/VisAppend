package com.rhynia.gtnh.append.recipe.container.VARecipePool;

import static gregtech.api.enums.TierEU.*;

import com.github.bartimaeusnek.bartworks.system.material.CircuitGeneration.BW_Meta_Items;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.machine.mapRecipe.VARecipe;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;

public class VASAssemblyMatrixRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map AM = VARecipe.instance.AssemblyMatrixRecipes;
        // 透镜配方
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 64),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 64),
                VA_WerkstoffMaterialPool.Primogem.get(OrePrefixes.lens, 64),
                VA_WerkstoffMaterialPool.Originiums.get(OrePrefixes.lens, 64),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 64),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 64),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.AstroInf.getGems(64),
                VA_GregtechMaterialPool.AstroInf.getGems(64),
                MaterialsUEVplus.Eternity.getNanite(64),
                MaterialsUEVplus.Eternity.getNanite(64))
            .fluidInputs(
                MaterialsUEVplus.Universium.getMolten(1440000000),
                MaterialsUEVplus.ExcitedDTSC.getFluid(1200000000),
                Materials.Neutronium.getMolten(1440000000),
                Materials.Americium.getMolten(1440000000),
                MaterialsUEVplus.SpaceTime.getMolten(1440000000),
                Materials.Infinity.getMolten(1440000000))
            .itemOutputs(VAItemList.ItemUltimate.get(1))
            .noOptimize()
            .eut(RECIPE_MAX)
            .duration(16000 * 20)
            .addTo(AM);

        // region 能量球
        // 兰波顿能量球 IV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem("bartworks","gt.bwMetaGeneratedItem0",64,32749),
                GT_ModHandler.getModItem("bartworks","gt.bwMetaGeneratedItem0",64,32729),
                GT_ModHandler.getModItem("bartworks","gt.bwMetaGeneratedItem0",32,32729))
            .fluidInputs(
                Materials.SolderingAlloy.getMolten(160*144),
                Materials.Platinum.getMolten(128*144),
                VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(12000))
            .itemOutputs(
                ItemList.Energy_LapotronicOrb.get(64),
                ItemList.Energy_LapotronicOrb.get(64),
                ItemList.Energy_LapotronicOrb.get(64),
                ItemList.Energy_LapotronicOrb.get(64),
                ItemList.Energy_LapotronicOrb.get(64),
                ItemList.Energy_LapotronicOrb.get(64))
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(480 * 20)
            .addTo(AM);
        // 兰波顿能量簇 LUV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem("bartworks","gt.bwMetaGeneratedItem0",64,32749),
                GT_ModHandler.getModItem("bartworks","gt.bwMetaGeneratedItem0",64,32727),
                GT_ModHandler.getModItem("bartworks","gt.bwMetaGeneratedItem0",64,32727),
                GT_ModHandler.getModItem("bartworks","gt.bwMetaGeneratedItem0",16,32719))
            .fluidInputs(
                Materials.SolderingAlloy.getMolten(256*144),
                Materials.Osmium.getMolten(96*144),
                VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(26000))
            .itemOutputs(
                ItemList.Energy_LapotronicOrb2.get(64),
                ItemList.Energy_LapotronicOrb2.get(64),
                ItemList.Energy_LapotronicOrb2.get(64),
                ItemList.Energy_LapotronicOrb2.get(64)
            )
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(500 * 20)
            .addTo(AM);
        // 能量模块 ZPM
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 64),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 11))
            .fluidInputs(
                MaterialsUEVplus.Universium.getMolten(888),
                MaterialsUEVplus.ExcitedDTSC.getFluid(1200000000),
                Materials.Neutronium.getMolten(1440000000),
                Materials.CosmicNeutronium.getMolten(1440000000),
                MaterialsUEVplus.ExcitedDTCC.getMolten(1440000000),
                Materials.Infinity.getMolten(1440000000))
            .itemOutputs(ItemList.Energy_Module.get(1))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(16000 * 20)
            .addTo(AM);
        // 能量簇 UV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 64),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 22))
            .fluidInputs(
                MaterialsUEVplus.Universium.getMolten(888),
                MaterialsUEVplus.ExcitedDTSC.getFluid(44),
                Materials.Neutronium.getMolten(1440000000),
                Materials.Europium.getMolten(1440000000),
                MaterialsUEVplus.SpaceTime.getMolten(1440000000),
                Materials.InfinityCatalyst.getMolten(1440000000))
            .itemOutputs(ItemList.Energy_Cluster.get(1))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(16000 * 20)
            .addTo(AM);
        // 零点能模块 ZPM
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 1),
                GT_OreDictUnificator.get(OrePrefixes.dustTiny, VA_GregtechMaterialPool.AstroInf, 64))
            .fluidInputs(
                MaterialsUEVplus.Universium.getMolten(888),
                MaterialsUEVplus.ExcitedDTSC.getFluid(1200000000),
                Materials.Neutronium.getMolten(55),
                Materials.CosmicNeutronium.getMolten(1440000000),
                MaterialsUEVplus.Eternity.getMolten(1440000000),
                Materials.Infinity.getMolten(1440000000))
            .itemOutputs(ItemList.ZPM.get(1))
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(16000 * 20)
            .addTo(AM);
        // 终极电池 UV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 2),
                GT_OreDictUnificator.get(OrePrefixes.dustSmall, VA_GregtechMaterialPool.AstroInf, 64))
            .fluidInputs(
                MaterialsUEVplus.Universium.getMolten(888),
                MaterialsUEVplus.ExcitedDTSC.getFluid(1200000000),
                Materials.Neutronium.getMolten(444),
                Materials.Glass.getMolten(44),
                MaterialsUEVplus.SpaceTime.getMolten(1440000000),
                Materials.Infinity.getMolten(1440000000))
            .itemOutputs(ItemList.ZPM2.get(1))
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(16000 * 20)
            .addTo(AM);
        // 真·终极电池 UMV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 3),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 64))
            .fluidInputs(
                MaterialsUEVplus.Universium.getMolten(888),
                MaterialsUEVplus.ExcitedDTSC.getFluid(3333),
                Materials.Neutronium.getMolten(444),
                Materials.Thaumium.getMolten(1440000000),
                MaterialsUEVplus.SpaceTime.getMolten(1440000000),
                Materials.Infinity.getMolten(1440000000))
            .itemOutputs(ItemList.ZPM3.get(1))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(16000 * 20)
            .addTo(AM);
        // 极·终极电池 UXV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 4),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 64))
            .fluidInputs(
                MaterialsUEVplus.Universium.getMolten(888),
                MaterialsUEVplus.ExcitedDTSC.getFluid(1200000000),
                Materials.Water.getMolten(444),
                Materials.CosmicNeutronium.getMolten(1440000000),
                MaterialsUEVplus.SpaceTime.getMolten(6666),
                Materials.Infinity.getMolten(1440000000))
            .itemOutputs(ItemList.ZPM4.get(1))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(16000 * 20)
            .addTo(AM);
        // 狂·终极电池 MAX
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.gem, VA_GregtechMaterialPool.AstroMagic, 5),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 64))
            .fluidInputs(
                MaterialsUEVplus.Universium.getMolten(888),
                MaterialsUEVplus.ExcitedDTSC.getFluid(1200000000),
                Materials.Neutronium.getMolten(444),
                Materials.Gold.getMolten(1440000000),
                MaterialsUEVplus.SpaceTime.getMolten(6666),
                Materials.Infinity.getMolten(1440000000))
            .itemOutputs(ItemList.ZPM5.get(1))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(16000 * 20)
            .addTo(AM);
        // 太·终极电池 ER
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.dust, VA_GregtechMaterialPool.AstroMagic, 6),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 64))
            .fluidInputs(
                MaterialsUEVplus.Universium.getMolten(888),
                MaterialsUEVplus.ExcitedDTSC.getFluid(1200000000),
                Materials.Iron.getMolten(444),
                Materials.CosmicNeutronium.getMolten(1440000000),
                MaterialsUEVplus.SpaceTime.getMolten(6666),
                Materials.Infinity.getMolten(1440000000))
            .itemOutputs(ItemList.ZPM6.get(1))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(16000 * 20)
            .addTo(AM);
        // endregion
    }
}
