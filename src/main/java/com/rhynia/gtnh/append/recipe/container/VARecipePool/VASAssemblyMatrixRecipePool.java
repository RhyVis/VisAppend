package com.rhynia.gtnh.append.recipe.container.VARecipePool;

import static gregtech.api.enums.Mods.Names.BART_WORKS;
import static gregtech.api.enums.TierEU.*;
import static gregtech.api.util.GT_RecipeBuilder.*;

import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.machine.mapRecipe.VARecipe;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;
import gtPlusPlus.core.material.ALLOY;
import gtPlusPlus.core.material.ELEMENT;
import gtPlusPlus.core.material.MISC_MATERIALS;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class VASAssemblyMatrixRecipePool implements IRecipePool {
    final String BWMI0 = "gt.bwMetaGeneratedItem0";

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map AM = VARecipe.instance.AssemblyMatrixRecipes;
        // 终极配方-测试
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
                Materials.CosmicNeutronium.getMolten(1440000000),
                MaterialsUEVplus.Eternity.getMolten(1440000000),
                MaterialsUEVplus.SpaceTime.getMolten(1440000000),
                Materials.Infinity.getMolten(1440000000))
            .itemOutputs(VAItemList.ItemUltimate.get(1))
            .noOptimize()
            .eut(RECIPE_MAX)
            .duration(160 * HOURS)
            .addTo(AM);

        // region 电池配方
        // 兰波顿能量球 IV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32754),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32729),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 32, 32729),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 8, 32737),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 8, 32738),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 8, 32739),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 8, 32740))
            .fluidInputs(
                Materials.SolderingAlloy.getMolten(160 * INGOTS),
                Materials.Platinum.getMolten(128 * INGOTS),
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
            .duration(480 * SECONDS)
            .addTo(AM);
        // 兰波顿能量簇 LUV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32750),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32727),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32727),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 32, 32731),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 16, 32737),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 16, 32738),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 16, 32739),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 16, 32740))
            .fluidInputs(
                ALLOY.INDALLOY_140.getFluidStack(128 * INGOTS),
                Materials.NaquadahAlloy.getMolten(48 * INGOTS),
                Materials.Platinum.getMolten(48 * INGOTS),
                Materials.Osmium.getMolten(96 * INGOTS),
                VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(26000))
            .itemOutputs(
                ItemList.Energy_LapotronicOrb2.get(64),
                ItemList.Energy_LapotronicOrb2.get(64),
                ItemList.Energy_LapotronicOrb2.get(64),
                ItemList.Energy_LapotronicOrb2.get(64))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(500 * SECONDS)
            .addTo(AM);
        // 能量模块 ZPM
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32750),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 32, 32722),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32723),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32723),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 32, 32723),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 32, 32730),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 32, 32737),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 32, 32738),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 32, 32739),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 32, 32740))
            .fluidInputs(
                ALLOY.INDALLOY_140.getFluidStack(256 * INGOTS),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 16 * 1000),
                Materials.Europium.getMolten(128 * INGOTS),
                Materials.Americium.getMolten(64 * INGOTS),
                Materials.Neutronium.getMolten(48 * INGOTS),
                VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(48000))
            .itemOutputs(ItemList.Energy_Module.get(64), ItemList.Energy_Module.get(64))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(615 * SECONDS)
            .addTo(AM);
        // 能量簇 UV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32746),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32721),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32721),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32722),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32722),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 48, 32730),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 48, 32737),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 48, 32738),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 48, 32739),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 48, 32740))
            .fluidInputs(
                ALLOY.INDALLOY_140.getFluidStack(256 * INGOTS),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 48 * 1000),
                ELEMENT.STANDALONE.ADVANCED_NITINOL.getFluidStack(90 * 1000),
                Materials.Naquadah.getMolten(128 * INGOTS),
                ALLOY.QUANTUM.getFluidStack(16 * INGOTS),
                VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(96 * 1000))
            .itemOutputs(ItemList.Energy_Cluster.get(64),ItemList.Energy_Cluster.get(32))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(800 * SECONDS)
            .addTo(AM);
        // 终极电池 UV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32746),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32721),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32721),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32721),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32721),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32722),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32722),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32730),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32737),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32738),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32739),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32740))
            .fluidInputs(
                MISC_MATERIALS.MUTATED_LIVING_SOLDER.getFluidStack(96 * INGOTS),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 80 * 1000),
                Materials.Neutronium.getMolten(256 * INGOTS),
                Materials.Infinity.getMolten(64 * INGOTS),
                Materials.EnrichedNaquadria.getMolten(128 * INGOTS),
                VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(160 * 1000))
            .itemOutputs(ItemList.ZPM2.get(64))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(1000 * SECONDS)
            .addTo(AM);
        // 真·终极电池 UMV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 32, 32704),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 16, 32703),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 16, 32701),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32721),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32721),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32721),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 64, 32707),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 16, 32708),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 16, 32709),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 16, 32710),
                GT_ModHandler.getModItem(BART_WORKS, BWMI0, 16, 32711))
            .fluidInputs(
                MISC_MATERIALS.MUTATED_LIVING_SOLDER.getFluidStack(128 * INGOTS),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 120 * 1000),
                Materials.CosmicNeutronium.getMolten(256 * INGOTS),
                Materials.Infinity.getMolten(128 * INGOTS),
                ELEMENT.STANDALONE.HYPOGEN.getFluidStack(16 * INGOTS),
                VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(210 * 1000))
            .itemOutputs(ItemList.ZPM3.get(32))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(950 * SECONDS)
            .addTo(AM);
        /*
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
            .itemOutputs(ItemList.ZPM4.get(8))
            .noOptimize()
            .eut(RECIPE_UIV)
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
            .itemOutputs(ItemList.ZPM5.get(4))
            .noOptimize()
            .eut(RECIPE_UMV)
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
            .eut(RECIPE_UXV)
            .duration(16000 * 20)
            .addTo(AM);
            */
        // endregion

        // region 杂项组装
        // Magic 透镜
        GT_Values.RA.stdBuilder()
            .itemInputs(
                VA_GregtechMaterialPool.AstroMagic.getGems(64))
            .fluidInputs(
                Materials.DraconiumAwakened.getMolten(48 * INGOTS),
                VA_GregtechMaterialPool.Astro.getFluid(64000))
            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 1))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(810 * SECONDS)
            .addTo(AM);
        // Inf 透镜
        GT_Values.RA.stdBuilder()
            .itemInputs(
                VA_GregtechMaterialPool.AstroMagic.getGems(64))
            .fluidInputs(
                Materials.DraconiumAwakened.getMolten(256 * INGOTS),
                VA_GregtechMaterialPool.Astro.getFluid(128000),
                VA_GregtechMaterialPool.AstroInf.getFluid(48000))
            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 1))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(750 * SECONDS)
            .addTo(AM);
        // endregion
    }
}
