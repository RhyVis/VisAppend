package com.rhynia.gtnh.append.common.recipe.GTRecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.FullChance;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_LV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_MV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.common.material.VA_Materials;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gtPlusPlus.core.material.ELEMENT;
import gtPlusPlus.core.material.MISC_MATERIALS;

public class VAElectrolyzeRecipePool implements IRecipePool {

    @Override
    public void loadRecipesCompleteInit() {
        final IRecipeMap EC = RecipeMaps.electrolyzerRecipes;

        // region 焙烧粉还原
        // 焙烧铁粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedIron.getDust(4))
            .itemOutputs(Materials.Iron.getDust(4), VA_Materials.Astrium.get(OrePrefixes.dust, 1))
            .outputChances(FullChance, 5000)
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * SECONDS)
            .addTo(EC);
        // 焙烧铅粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedLead.getDust(4))
            .itemOutputs(Materials.Lead.getDust(4), VA_Materials.Astrium.get(OrePrefixes.dust, 1))
            .outputChances(FullChance, 5000)
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * SECONDS)
            .addTo(EC);
        // 焙烧镍粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedNickel.getDust(4))
            .itemOutputs(Materials.Nickel.getDust(4), VA_Materials.Astrium.get(OrePrefixes.dust, 1))
            .outputChances(FullChance, 5000)
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * SECONDS)
            .addTo(EC);
        // 焙烧锌粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedZinc.getDust(4))
            .itemOutputs(Materials.Zinc.getDust(4), VA_Materials.Astrium.get(OrePrefixes.dust, 1))
            .outputChances(FullChance, 5000)
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * SECONDS)
            .addTo(EC);
        // 焙烧铜粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedCopper.getDust(4))
            .itemOutputs(Materials.Copper.getDust(4), VA_Materials.Astrium.get(OrePrefixes.dust, 1))
            .outputChances(FullChance, 5000)
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * SECONDS)
            .addTo(EC);
        // 焙烧钴粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedCobalt.getDust(4))
            .itemOutputs(Materials.Cobalt.getDust(4), VA_Materials.Astrium.get(OrePrefixes.dust, 1))
            .outputChances(FullChance, 5000)
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * SECONDS)
            .addTo(EC);
        // 焙烧锑粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedAntimony.getDust(4))
            .itemOutputs(Materials.Antimony.getDust(4), VA_Materials.Astrium.get(OrePrefixes.dust, 1))
            .outputChances(FullChance, 5000)
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * SECONDS)
            .addTo(EC);
        // 焙烧砷粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedArsenic.getDust(4))
            .itemOutputs(Materials.Arsenic.getDust(4), VA_Materials.Astrium.get(OrePrefixes.dust, 1))
            .outputChances(FullChance, 5000)
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * SECONDS)
            .addTo(EC);
        // endregion

        // region 电解钨-钛系列
        // 钛铁矿
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Ilmenite.getDust(8))
            .itemOutputs(Materials.Titanium.getDust(5), Materials.Iron.getDust(4))
            .fluidOutputs(Materials.Oxygen.getGas(6000))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(8 * SECONDS)
            .addTo(EC);
        // endregion

        // region 电解杂项矿
        // 二氧化锶
        GT_Values.RA.stdBuilder()
            .itemInputs(MISC_MATERIALS.STRONTIUM_OXIDE.getDust(6))
            .itemOutputs(Materials.Strontium.getDust(4))
            .fluidOutputs(Materials.Oxygen.getGas(4500))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(8 * SECONDS)
            .addTo(EC);
        // 黑物质
        GT_Values.RA.stdBuilder()
            .itemInputs(ELEMENT.STANDALONE.BLACK_METAL.getDust(16))
            .itemOutputs(
                Materials.Lead.getDust(3),
                Materials.Manganese.getDust(5),
                ELEMENT.getInstance().RHENIUM.getDust(4),
                ELEMENT.getInstance().THALLIUM.getDust(4),
                Materials.Copper.getDust(3),
                Materials.Molybdenum.getDust(1))
            .fluidOutputs(Materials.Oxygen.getGas(200), Materials.Hydrogen.getGas(150))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(28 * SECONDS)
            .addTo(EC);
        // endregion
    }
}
