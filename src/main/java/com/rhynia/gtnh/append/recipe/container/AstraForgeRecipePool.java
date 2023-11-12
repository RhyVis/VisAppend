package com.rhynia.gtnh.append.recipe.container;

import static gregtech.api.enums.OrePrefixes.dust;
import static gregtech.api.enums.TierEU.*;

import com.rhynia.gtnh.append.common.machine.recipe.GTAppendRecipe;
import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class AstraForgeRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map AF = GTAppendRecipe.instance.AstraForgeRecipes;

        // region 12号电路

        // 润滑油
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                GT_Utility.copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroMagic, 1)),
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
                GT_Utility.copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroMagic, 1)),
                Materials.Iron.getDust(64),
                Materials.Iron.getDust(64),
                Materials.Diamond.getDust(64),
                Materials.Diamond.getDust(64))
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(20 * 20)
            .addTo(AF);
        // endregion

        // region 11号电路
        // 灵魂沙
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                GT_Utility.copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroInf, 1)),
                Materials.Sand.getDust(64),
                Materials.Sand.getDust(64),
                new ItemStack(Items.blaze_powder, 4, 0))
            .fluidInputs(
                Materials.Water.getFluid(1000),
                MaterialGTMethod.Astro.getFluid(16))
            .itemOutputs(
                Materials.SoulSand.getBlocks(64),
                Materials.SoulSand.getBlocks(64))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(350)
            .addTo(AF);
        // 烈焰粉
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                GT_Utility.copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroInf, 1)),
                Materials.Carbon.getDust(64),
                Materials.Sulfur.getDust(64))
            .fluidInputs(
                Materials.Lava.getFluid(1000),
                MaterialGTMethod.Astro.getFluid(16))
            .itemOutputs(
                new ItemStack(Items.blaze_powder, 64, 0),
                new ItemStack(Items.blaze_powder, 64, 0),
                new ItemStack(Items.blaze_powder, 64, 0),
                new ItemStack(Items.blaze_powder, 64, 0))
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(350)
            .addTo(AF);
        // endregion

        // region 10号电路
        // UU增殖
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                GT_Utility.copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroInf, 1)),
                MaterialGTMethod.Astro.getDust(64),
                MaterialGTMethod.Astro.getDust(64))
            .fluidInputs(Materials.UUMatter.getFluid(2048))
            .fluidOutputs(Materials.UUMatter.getFluid(32768))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(40 * 20)
            .addTo(AF);
        // endregion

        // region 4号电路
        // 合成宇宙中子素 SpNt
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                GT_Utility.copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroInf, 1)),
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
                GT_Utility.copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroInf, 1)),
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
                GT_Utility.copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroInf, 1)),
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
                GT_Utility.copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroInf, 1)),
                MaterialGTMethod.Astro.getDust(60),
                Materials.Naquadah.getDust(60))
            .fluidInputs(Materials.Helium.getFluid(120000), Materials.Quantium.getFluid(40000))
            .itemOutputs(MyMaterial.orundum.get(dust, 64), MyMaterial.orundum.get(dust, 64))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(100 * 20)
            .addTo(AF);

        // endregion 星辉转化
    }
}
