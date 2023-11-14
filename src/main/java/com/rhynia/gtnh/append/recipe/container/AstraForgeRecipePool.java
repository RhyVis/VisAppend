package com.rhynia.gtnh.append.recipe.container;

import static gregtech.api.enums.OrePrefixes.dust;
import static gregtech.api.enums.TierEU.*;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.machine.recipe.GTAppendRecipe;
import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;

public class AstraForgeRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map AF = GTAppendRecipe.instance.AstraForgeRecipes;
        final ItemStack lensMagic = GT_Utility
            .copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroMagic, 1));
        final ItemStack lensInf = GT_Utility
            .copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroInf, 1));

        // region 12号电路

        // 润滑油
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensMagic,
                MaterialGTMethod.Astro.getDust(16),
                Materials.Redstone.getDust(1))
            .fluidInputs(Materials.Water.getFluid(32000))
            .fluidOutputs(Materials.Lubricant.getFluid(256000))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * 20)
            .addTo(AF);
        // 不稳定金属
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensMagic,
                Materials.Iron.getDust(64),
                Materials.Iron.getDust(64),
                Materials.Diamond.getDust(64),
                Materials.Diamond.getDust(64))
            .itemOutputs(Materials.Unstable.getDust(64), Materials.Unstable.getDust(64))
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(20 * 20)
            .addTo(AF);
        // endregion

        // region 11号电路 魔法合成
        // 灵魂沙
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                lensInf,
                new ItemStack(Blocks.sand, 64),
                new ItemStack(Blocks.sand, 64),
                new ItemStack(Items.blaze_powder, 4, 0))
            .fluidInputs(Materials.Water.getFluid(1000), MaterialGTMethod.Astro.getFluid(16))
            .itemOutputs(new ItemStack(Blocks.soul_sand, 64, 0), new ItemStack(Blocks.soul_sand, 64, 0))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(350)
            .addTo(AF);
        // 烈焰粉
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                lensInf,
                Materials.Carbon.getDust(64),
                Materials.Sulfur.getDust(64))
            .fluidInputs(Materials.Lava.getFluid(1000), MaterialGTMethod.Astro.getFluid(16))
            .itemOutputs(
                new ItemStack(Items.blaze_powder, 64, 0),
                new ItemStack(Items.blaze_powder, 64, 0),
                new ItemStack(Items.blaze_powder, 64, 0),
                new ItemStack(Items.blaze_powder, 64, 0))
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(350)
            .addTo(AF);
        // 末影珍珠
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                lensInf,
                GT_ModHandler.getModItem("HardcoreEnderExpansion", "end_powder", 16),
                MaterialGTMethod.Astro.getDust(64))
            .fluidInputs(MaterialGTMethod.Astro.getFluid(16))
            .itemOutputs(
                new ItemStack(Items.ender_pearl, 64, 0),
                new ItemStack(Items.ender_pearl, 64, 0),
                new ItemStack(Items.ender_pearl, 64, 0),
                new ItemStack(Items.ender_pearl, 64, 0))
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(350)
            .addTo(AF);
        // endregion

        // region 10号电路 增殖
        // UU增殖
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                MaterialGTMethod.Astro.getDust(64),
                MaterialGTMethod.Astro.getDust(64))
            .fluidInputs(Materials.UUMatter.getFluid(2048))
            .fluidOutputs(Materials.UUMatter.getFluid(32768))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(40 * 20)
            .addTo(AF);
        // endregion

        // region 8号电路 矿物处理
        // 石英岩
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64))
            .fluidInputs(MaterialGTMethod.Astro.getFluid(16))
            .itemOutputs(
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.SiliconSG.getDust(64),
                Materials.SiliconSG.getDust(64),
                Materials.SiliconSG.getDust(64),
                Materials.SiliconSG.getDust(64),
                MaterialGTMethod.Astro.getDust(64),
                MaterialGTMethod.Astro.getDust(64))
            .fluidOutputs(MaterialGTMethod.Astro.getFluid(16))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * 20)
            .addTo(AF);
        // 二氧化硅
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64))
            .fluidInputs(MaterialGTMethod.Astro.getFluid(16))
            .itemOutputs(
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.SiliconSG.getDust(64),
                Materials.SiliconSG.getDust(64),
                Materials.SiliconSG.getDust(64),
                Materials.SiliconSG.getDust(64),
                MaterialGTMethod.Astro.getDust(64),
                MaterialGTMethod.Astro.getDust(64))
            .fluidOutputs(MaterialGTMethod.Astro.getFluid(16))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * 20)
            .addTo(AF);
        // 方解石
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64))
            .fluidInputs(MaterialGTMethod.Astro.getFluid(16))
            .itemOutputs(
                Materials.Calcium.getDust(64),
                Materials.Calcium.getDust(64),
                Materials.Calcium.getDust(64),
                Materials.Calcium.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                MaterialGTMethod.Astro.getDust(64),
                MaterialGTMethod.Astro.getDust(64))
            .fluidOutputs(MaterialGTMethod.Astro.getFluid(16))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * 20)
            .addTo(AF);
        // 磷酸盐
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64))
            .fluidInputs(MaterialGTMethod.Astro.getFluid(16))
            .itemOutputs(
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                MaterialGTMethod.Astro.getDust(64),
                MaterialGTMethod.Astro.getDust(64),
                MaterialGTMethod.Astro.getDust(64),
                MaterialGTMethod.Astro.getDust(64))
            .fluidOutputs(MaterialGTMethod.Astro.getFluid(16))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * 20)
            .addTo(AF);
        // 硫酸钠
        final ItemStack Na2SO4 = WerkstoffLoader.Sodiumsulfate.get(dust, 64);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4)
            .fluidInputs(MaterialGTMethod.Astro.getFluid(16))
            .itemOutputs(
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sulfur.getDust(64),
                Materials.Sulfur.getDust(64),
                MaterialGTMethod.Astro.getDust(64),
                MaterialGTMethod.Astro.getDust(64))
            .fluidOutputs(MaterialGTMethod.Astro.getFluid(16))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * 20)
            .addTo(AF);
        // 钐
        final ItemStack SmMix = GT_ModHandler.getModItem("bartworks", "bwMetaGenerateddust", 64, 11128);
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(8), lensInf, SmMix, SmMix, SmMix, SmMix)
            .fluidInputs(MaterialGTMethod.Astro.getFluid(16))
            .itemOutputs(
                Materials.Samarium.getDust(64),
                Materials.Samarium.getDust(32),
                Materials.Lanthanum.getDust(16),
                Materials.Cerium.getDust(16),
                MaterialGTMethod.Astro.getDust(64),
                MaterialGTMethod.Astro.getDust(64))
            .fluidOutputs(MaterialGTMethod.Astro.getFluid(16))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * 20)
            .addTo(AF);
        // 富铈粉
        final ItemStack CeMix = GT_ModHandler.getModItem("bartworks", "bwMetaGenerateddust", 64, 11029);
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(8), lensInf, CeMix, CeMix, CeMix, CeMix)
            .fluidInputs(MaterialGTMethod.Astro.getFluid(16))
            .itemOutputs(
                Materials.Cerium.getDust(64),
                Materials.Cerium.getDust(16),
                Materials.Lanthanum.getDust(16),
                Materials.Europium.getDust(16),
                Materials.Gadolinium.getDust(16),
                MaterialGTMethod.Astro.getDust(64),
                MaterialGTMethod.Astro.getDust(64))
            .fluidOutputs(MaterialGTMethod.Astro.getFluid(16))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * 20)
            .addTo(AF);
        // 稀土
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
                Materials.RareEarth.getDust(64),
                Materials.RareEarth.getDust(64))
            .itemOutputs(
                GT_ModHandler.getModItem("miscutils", "itemDustRareEarthI", 48),
                GT_ModHandler.getModItem("miscutils", "itemDustRareEarthII", 40),
                GT_ModHandler.getModItem("miscutils", "itemDustRareEarthIII", 32))
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(12 * 20)
            .addTo(AF);
        // endregion

        // region 4号电路 合成
        // 合成宇宙中子素 SpNt
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                lensInf,
                MaterialGTMethod.Astro.getDust(4),
                Materials.BlackPlutonium.getDust(16))
            .fluidInputs(Materials.Helium.getFluid(2000))
            .itemOutputs(Materials.CosmicNeutronium.getDust(12))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(30 * 20)
            .addTo(AF);

        // 合成阳光化合物 Su
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                lensInf,
                MaterialGTMethod.Astro.getDust(1),
                Materials.Glowstone.getDust(16))
            .fluidInputs(Materials.Hydrogen.getFluid(12000))
            .itemOutputs(Materials.Sunnarium.getDust(64))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(22 * 20)
            .addTo(AF);

        // 合成超能硅岩 Nq*
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                lensInf,
                MaterialGTMethod.AstroInf.getDust(12),
                MaterialGTMethod.Astro.getDust(32),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64))
            .fluidInputs(Materials.NaquadahEnriched.getFluid(144 * 32))
            .itemOutputs(
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(36 * 20)
            .addTo(AF);

        // 合成合成玉 Or
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                lensInf,
                MaterialGTMethod.Astro.getDust(60),
                Materials.Naquadah.getDust(60))
            .fluidInputs(Materials.Helium.getFluid(120000), Materials.Quantium.getFluid(40000))
            .itemOutputs(MyMaterial.orundum.get(dust, 64), MyMaterial.orundum.get(dust, 64))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(100 * 20)
            .addTo(AF);

        // endregion
    }
}
