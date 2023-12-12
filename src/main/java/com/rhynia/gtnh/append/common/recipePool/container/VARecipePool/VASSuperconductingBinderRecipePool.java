package com.rhynia.gtnh.append.common.recipePool.container.VARecipePool;

import static gregtech.api.enums.TierEU.RECIPE_MAX;
import static gregtech.api.enums.TierEU.RECIPE_MV;
import static gregtech.api.util.GT_RecipeBuilder.HOURS;
import static gregtech.api.util.GT_RecipeBuilder.INGOTS;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;

import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.recipePool.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMapBackend;

public class VASSuperconductingBinderRecipePool implements IRecipePool {

    private final RecipeMap<RecipeMapBackend> SB = AppendRecipeMaps.superconductingAssemblyRecipes;

    @Override
    public void loadRecipesPostInit() {
        // 终极配方-测试
        GT_Values.RA.stdBuilder()
            .itemInputs(MaterialsUEVplus.Eternity.getNanite(64), MaterialsUEVplus.Eternity.getNanite(64))
            .fluidInputs(
                MaterialsUEVplus.Universium.getMolten(1440000000),
                MaterialsUEVplus.ExcitedDTSC.getFluid(1200000000),
                Materials.CosmicNeutronium.getMolten(1440000000),
                MaterialsUEVplus.Eternity.getMolten(1440000000),
                MaterialsUEVplus.SpaceTime.getMolten(1440000000),
                Materials.Infinity.getMolten(1440000000))
            .itemOutputs(VAItemList.Test.get(1))
            .noOptimize()
            .eut(RECIPE_MAX)
            .duration(160 * HOURS)
            .addTo(SB);
    }

    @Override
    public void loadRecipes() {
        // region 超导配方

        // 终极配方-测试
        GT_Values.RA.stdBuilder()
            .itemInputs(MaterialsUEVplus.BlackDwarfMatter.getNanite(64))
            .fluidInputs(
                Materials.Pentacadmiummagnesiumhexaoxid.getMolten(16 * INGOTS),
                Materials.Helium.getMolten(16 * INGOTS))
            .itemOutputs(ItemList.Superconducting_Magnet_Solenoid_MV.get(1))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(160 * SECONDS)
            .addTo(SB);
        // endregion
    }

}
