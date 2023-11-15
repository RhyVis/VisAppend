package com.rhynia.gtnh.append.recipe.container;

import static gregtech.api.enums.TierEU.RECIPE_LV;
import static gregtech.api.enums.TierEU.RECIPE_MV;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_Recipe;

public class AppendElectroRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map EC = GT_Recipe.GT_Recipe_Map.sElectrolyzerRecipes;

        // region 焙烧粉还原
        // 焙烧铁粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedIron.getDust(4))
            .itemOutputs(Materials.Iron.getDust(4), MaterialGTMethod.Astro.getDust(1))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * 20)
            .addTo(EC);
        // 焙烧铅粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedLead.getDust(4))
            .itemOutputs(Materials.Lead.getDust(4), MaterialGTMethod.Astro.getDust(1))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * 20)
            .addTo(EC);
        // 焙烧镍粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedNickel.getDust(4))
            .itemOutputs(Materials.Nickel.getDust(4), MaterialGTMethod.Astro.getDust(1))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * 20)
            .addTo(EC);
        // 焙烧锌粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedZinc.getDust(4))
            .itemOutputs(Materials.Zinc.getDust(4), MaterialGTMethod.Astro.getDust(1))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * 20)
            .addTo(EC);
        // 焙烧铜粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedCopper.getDust(4))
            .itemOutputs(Materials.Copper.getDust(4), MaterialGTMethod.Astro.getDust(1))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * 20)
            .addTo(EC);
        // 焙烧钴粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedCobalt.getDust(4))
            .itemOutputs(Materials.Cobalt.getDust(4), MaterialGTMethod.Astro.getDust(1))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * 20)
            .addTo(EC);
        // 焙烧锑粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedAntimony.getDust(4))
            .itemOutputs(Materials.Antimony.getDust(4), MaterialGTMethod.Astro.getDust(1))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * 20)
            .addTo(EC);
        // 焙烧砷粉
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.RoastedArsenic.getDust(4))
            .itemOutputs(Materials.Arsenic.getDust(4), MaterialGTMethod.Astro.getDust(1))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(8 * 20)
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
            .duration(8 * 20)
            .addTo(EC);
        // endregion
    }
}
