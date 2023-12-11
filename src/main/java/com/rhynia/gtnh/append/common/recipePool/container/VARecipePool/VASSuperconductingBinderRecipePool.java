package com.rhynia.gtnh.append.common.recipePool.container.VARecipePool;

import static gregtech.api.enums.TierEU.RECIPE_LV;
import static gregtech.api.enums.TierEU.RECIPE_MAX;
import static gregtech.api.enums.TierEU.RECIPE_MV;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sCentrifugeRecipes;
import static gregtech.api.util.GT_RecipeBuilder.HOURS;
import static gregtech.api.util.GT_RecipeBuilder.INGOTS;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;

import com.rhynia.gtnh.append.api.recipe.VA_Recipe;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.material.VAMaterials;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.common.recipePool.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_Recipe;

public class VASSuperconductingBinderRecipePool implements IRecipePool {

    private final GT_Recipe.GT_Recipe_Map SB = VA_Recipe.instance.sSuperconductingBinderRecipes;

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

        // TODO: 在转移完成后移除
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_GregtechMaterialPool.Astro.getDust(64))
            .itemOutputs(VAMaterials.Astrium.get(OrePrefixes.dust, 64))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(1)
            .addTo(sCentrifugeRecipes);
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_GregtechMaterialPool.AstroInf.getDust(64))
            .itemOutputs(VAMaterials.AstriumInfinity.get(OrePrefixes.dust, 64))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(1)
            .addTo(sCentrifugeRecipes);
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_GregtechMaterialPool.AstroMagic.getDust(64))
            .itemOutputs(VAMaterials.AstriumMagic.get(OrePrefixes.dust, 64))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(1)
            .addTo(sCentrifugeRecipes);
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_WerkstoffMaterialPool.astroCatalystBase.get(OrePrefixes.dust, 1))
            .itemOutputs(VAMaterials.AstralCatalystBase.get(OrePrefixes.dust, 1))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(1)
            .addTo(sCentrifugeRecipes);
        GT_Values.RA.stdBuilder()
            .fluidInputs(VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(250))
            .fluidOutputs(VAMaterials.AstralCatalystBaseExcited.getFluidOrGas(250))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(1)
            .addTo(sCentrifugeRecipes);
        GT_Values.RA.stdBuilder()
            .fluidInputs(VA_GregtechMaterialPool.Astro.getFluid(250))
            .fluidOutputs(VAMaterials.Astrium.getMolten(250))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(1)
            .addTo(sCentrifugeRecipes);
        GT_Values.RA.stdBuilder()
            .fluidInputs(VA_GregtechMaterialPool.AstroInf.getFluid(250))
            .fluidOutputs(VAMaterials.AstriumInfinity.getMolten(250))
            .noOptimize()
            .eut(RECIPE_LV)
            .duration(1)
            .addTo(sCentrifugeRecipes);
    }

}
