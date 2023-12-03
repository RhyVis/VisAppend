package com.rhynia.gtnh.append.recipe.container.VARecipePool;

import static gregtech.api.enums.TierEU.*;
import static gregtech.api.util.GT_RecipeBuilder.*;

import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.machine.recipeMap.VA_RecipeAdder;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.*;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;

public class VASMicroAssemblyRecipePool implements IRecipePool {

    final GT_Recipe.GT_Recipe_Map AM = VA_RecipeAdder.instance.sMicroAssemblyRecipes;
    final String BWMI0 = "gt.bwMetaGeneratedItem0";

    @Override
    public void loadRecipesPostInit() {
        // 终极配方-测试
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 64),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 64),
                VA_WerkstoffMaterialPool.Primogem.get(OrePrefixes.lens, 64),
                VA_WerkstoffMaterialPool.Originiums.get(OrePrefixes.lens, 64))
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
    }

    @Override
    public void loadRecipes() {}
}
