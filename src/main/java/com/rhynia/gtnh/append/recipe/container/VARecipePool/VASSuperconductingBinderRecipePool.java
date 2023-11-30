package com.rhynia.gtnh.append.recipe.container.VARecipePool;

import static gregtech.api.enums.TierEU.RECIPE_MAX;
import static gregtech.api.enums.TierEU.RECIPE_MV;
import static gregtech.api.util.GT_RecipeBuilder.HOURS;
import static gregtech.api.util.GT_RecipeBuilder.INGOTS;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;

import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.machine.mapRecipe.VARecipe;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.util.GT_Recipe;

public class VASSuperconductingBinderRecipePool implements IRecipePool {

    final String BWMI0 = "gt.bwMetaGeneratedItem0";

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map SB = VARecipe.instance.SuperconductingBinderRecipes;
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
            .itemOutputs(VAItemList.ItemUltimate.get(1))
            .noOptimize()
            .eut(RECIPE_MAX)
            .duration(160 * HOURS)
            .addTo(SB);

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
