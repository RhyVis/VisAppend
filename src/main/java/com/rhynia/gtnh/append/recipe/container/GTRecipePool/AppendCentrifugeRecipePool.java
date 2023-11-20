package com.rhynia.gtnh.append.recipe.container.GTRecipePool;

import static gregtech.api.enums.TierEU.*;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_Recipe;

public class AppendCentrifugeRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map CF = GT_Recipe.GT_Recipe_Map.sCentrifugeRecipes;

        // region 杂项系列
        // 褐煤制煤
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Lignite.getDust(4))
            .itemOutputs(Materials.Coal.getDust(3))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * 20)
            .addTo(CF);
        // 氟碳铈
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Bastnasite.getDust(12))
            .itemOutputs(
                Materials.Cerium.getDust(2),
                Materials.Gadolinium.getDust(1),
                Materials.Samarium.getDust(1),
                Materials.Carbon.getDust(2))
            .fluidOutputs(Materials.Oxygen.getGas(6000), Materials.Fluorine.getGas(2000))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(8 * 20)
            .addTo(CF);
        // 褐铜
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Rubracium.getDust(12))
            .itemOutputs(
                Materials.Copper.getDust(8),
                Materials.Ledox.getDust(2),
                WerkstoffLoader.Roquesit.get(OrePrefixes.dust, 1),
                Materials.Firestone.getDust(1),
                VA_GregtechMaterialPool.Astro.getDust(1))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(8 * 20)
            .addTo(CF);
        // 深空冰
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.CallistoIce.getDust(4))
            .itemOutputs(
                Materials.Ice.getDust(2),
                Materials.Cryotheum.getDust(1),
                VA_GregtechMaterialPool.Astro.getDust(1))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(8 * 20)
            .addTo(CF);
        // 离心秘银
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Mithril.getDust(6))
            .itemOutputs(
                Materials.Pyrotheum.getDust(1),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 2),
                VA_GregtechMaterialPool.Astro.getDust(2))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(21 * 20)
            .addTo(CF);
        // 离心深空秘银
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Mytryl.getDust(8))
            .itemOutputs(
                Materials.Mithril.getDust(4),
                Materials.Thaumium.getDust(3),
                Materials.AstralSilver.getDust(1),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 2),
                VA_GregtechMaterialPool.Astro.getDust(2))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(21 * 20)
            .addTo(CF);
        // 离心方钍石
        GT_Values.RA.stdBuilder()
            .itemInputs(WerkstoffLoader.Thorianit.get(OrePrefixes.dust, 8))
            .itemOutputs(Materials.Thorium.getDust(6), WerkstoffLoader.Thorium232.get(OrePrefixes.dust, 2))
            .outputChances(10000, 2000)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(15 * 20)
            .addTo(CF);
        // endregion

        // region 钨处理
        // 白钨
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Scheelite.getDust(4))
            .itemOutputs(Materials.Tungsten.getDust(3), Materials.Calcium.getDust(3))
            .fluidOutputs(Materials.Oxygen.getGas(2000))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(8 * 20)
            .addTo(CF);
        // 钨酸锂
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Tungstate.getDust(6))
            .itemOutputs(Materials.Tungsten.getDust(3), Materials.Lithium.getDust(5))
            .fluidOutputs(Materials.Oxygen.getGas(1500))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(8 * 20)
            .addTo(CF);
        // 钨铁
        GT_Values.RA.stdBuilder()
            .itemInputs(WerkstoffLoader.Ferberite.get(OrePrefixes.dust, 6))
            .itemOutputs(Materials.Tungsten.getDust(3), Materials.Iron.getDust(2))
            .fluidOutputs(Materials.Oxygen.getGas(1500))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(8 * 20)
            .addTo(CF);
        // 钨酸锰
        GT_Values.RA.stdBuilder()
            .itemInputs(WerkstoffLoader.Huebnerit.get(OrePrefixes.dust, 8))
            .itemOutputs(Materials.Tungsten.getDust(3), Materials.Manganese.getDust(4))
            .fluidOutputs(Materials.Oxygen.getGas(2000))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(8 * 20)
            .addTo(CF);
        // endregion
    }
}
