package com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool;

import static com.rhynia.gtnh.append.api.util.enums.RecipeValues.FullChance;
import static com.rhynia.gtnh.append.api.util.enums.RecipeValues.RECIPE_HV;
import static com.rhynia.gtnh.append.api.util.enums.RecipeValues.RECIPE_LV;
import static com.rhynia.gtnh.append.api.util.enums.RecipeValues.RECIPE_LuV;
import static com.rhynia.gtnh.append.api.util.enums.RecipeValues.RECIPE_MV;
import static com.rhynia.gtnh.append.api.util.enums.RecipeValues.SECONDS;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.common.material.VAMaterials;
import com.rhynia.gtnh.append.common.recipePool.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.api.recipe.GTPPRecipeMaps;
import gtPlusPlus.core.material.ELEMENT;
import gtPlusPlus.core.material.MISC_MATERIALS;

public class VACentrifugeRecipePool implements IRecipePool {

    @Override
    public void loadRecipesPostInit() {}

    @Override
    public void loadRecipes() {
        final IRecipeMap CF = RecipeMaps.centrifugeRecipes;
        final IRecipeMap CFGTPP = GTPPRecipeMaps.centrifugeNonCellRecipes;

        // region 杂项系列
        // 褐煤制煤
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Lignite.getDust(4))
            .itemOutputs(Materials.Coal.getDust(3))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(4 * SECONDS)
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
            .duration(8 * SECONDS)
            .addTo(CFGTPP);
        // 褐铜
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Rubracium.getDust(12))
            .itemOutputs(
                Materials.Copper.getDust(8),
                Materials.Ledox.getDust(2),
                WerkstoffLoader.Roquesit.get(OrePrefixes.dust, 1),
                Materials.Firestone.getDust(1),
                VAMaterials.Astrium.get(OrePrefixes.dust, 1))
            .outputChances(FullChance, FullChance, FullChance, FullChance, 5000)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(8 * SECONDS)
            .addTo(CF);
        // 山铜
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Orichalcum.getDust(12))
            .itemOutputs(Materials.Copper.getDust(10), VAMaterials.Astrium.get(OrePrefixes.dust, 4))
            .outputChances(FullChance, 5000)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(4 * SECONDS)
            .addTo(CF);
        // 深空冰
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.CallistoIce.getDust(4))
            .itemOutputs(
                Materials.Ice.getDust(2),
                Materials.Cryotheum.getDust(1),
                VAMaterials.Astrium.get(OrePrefixes.dust, 1))
            .outputChances(FullChance, FullChance, 5000)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(8 * SECONDS)
            .addTo(CF);
        // 离心秘银
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Mithril.getDust(6))
            .itemOutputs(
                Materials.Pyrotheum.getDust(1),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 2),
                VAMaterials.Astrium.get(OrePrefixes.dust, 2))
            .outputChances(FullChance, FullChance, 5000)
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(16 * SECONDS)
            .addTo(CF);
        // 离心深空秘银
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Mytryl.getDust(8))
            .itemOutputs(
                Materials.Mithril.getDust(4),
                Materials.Thaumium.getDust(3),
                Materials.AstralSilver.getDust(1),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 2),
                VAMaterials.Astrium.get(OrePrefixes.dust, 2))
            .outputChances(FullChance, FullChance, FullChance, FullChance, 5000)
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(16 * SECONDS)
            .addTo(CF);
        // 离心方钍石
        GT_Values.RA.stdBuilder()
            .itemInputs(WerkstoffLoader.Thorianit.get(OrePrefixes.dust, 8))
            .itemOutputs(Materials.Thorium.getDust(6), WerkstoffLoader.Thorium232.get(OrePrefixes.dust, 2))
            .outputChances(FullChance, 2000)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(8 * SECONDS)
            .addTo(CF);
        // 离心金刚砂
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Emery.getDust(18))
            .itemOutputs(Materials.Quartzite.getDust(10), Materials.Diamond.getDust(8))
            .outputChances(FullChance, 7500)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(8 * SECONDS)
            .addTo(CF);
        // 离心幽冥毒晶
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Vyroxeres.getDust(7))
            .itemOutputs(
                Materials.Uranium235.getDust(3),
                ELEMENT.getInstance().RHENIUM.getDust(2),
                ELEMENT.getInstance().THALLIUM.getDust(2))
            .outputChances(FullChance, 5000, 5000)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(8 * SECONDS)
            .addTo(CF);
        // 离心神秘蓝金
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Alduorite.getDust(16))
            .itemOutputs(
                Materials.ElectrumFlux.getDust(8),
                Materials.Thaumium.getDust(2),
                Materials.MysteriousCrystal.getDust(2),
                VAMaterials.Astrium.get(OrePrefixes.dust, 4))
            .outputChances(FullChance, FullChance, 6500, 5000)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(8 * SECONDS)
            .addTo(CF);
        // 离心胶木
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Vulcanite.getDust(16))
            .itemOutputs(Materials.RawRubber.getDust(8))
            .fluidOutputs(
                MISC_MATERIALS.ETHYL_CYANOACRYLATE.getFluidStack(16000),
                Materials.AdvancedGlue.getFluid(8000))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(16 * SECONDS)
            .addTo(CFGTPP);
        // endregion

        // region 钨处理
        // 白钨
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Scheelite.getDust(4))
            .itemOutputs(Materials.Tungsten.getDust(3), Materials.Calcium.getDust(3))
            .fluidOutputs(Materials.Oxygen.getGas(2000))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(8 * SECONDS)
            .addTo(CF);
        // 钨酸锂
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Tungstate.getDust(6))
            .itemOutputs(Materials.Tungsten.getDust(3), Materials.Lithium.getDust(5))
            .fluidOutputs(Materials.Oxygen.getGas(1500))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(8 * SECONDS)
            .addTo(CF);
        // 钨铁
        GT_Values.RA.stdBuilder()
            .itemInputs(WerkstoffLoader.Ferberite.get(OrePrefixes.dust, 6))
            .itemOutputs(Materials.Tungsten.getDust(3), Materials.Iron.getDust(2))
            .fluidOutputs(Materials.Oxygen.getGas(1500))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(8 * SECONDS)
            .addTo(CF);
        // 钨酸锰
        GT_Values.RA.stdBuilder()
            .itemInputs(WerkstoffLoader.Huebnerit.get(OrePrefixes.dust, 8))
            .itemOutputs(Materials.Tungsten.getDust(3), Materials.Manganese.getDust(4))
            .fluidOutputs(Materials.Oxygen.getGas(2000))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(8 * SECONDS)
            .addTo(CF);
        // endregion

        // region 星辉燃料
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(1))
            .fluidInputs(VAMaterials.AstralFuelMkIDepleted.getFluidOrGas(125))
            .itemOutputs(
                VAMaterials.Astrium.get(OrePrefixes.dust, 8),
                VAMaterials.AstriumMagic.get(OrePrefixes.dust, 6),
                VAMaterials.AstriumInfinity.get(OrePrefixes.dust, 2),
                Materials.Naquadah.getDust(6),
                Materials.Naquadria.getDust(6),
                WerkstoffLoader.Tiberium.get(OrePrefixes.dust, 6))
            .outputChances(8000, 6000, 4000, 5000, 5000, 5000)
            .fluidOutputs(VAMaterials.Astrium.getMolten(80), VAMaterials.AstriumInfinity.getMolten(20))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(7 * SECONDS)
            .addTo(CFGTPP);
        // endregion
    }
}
