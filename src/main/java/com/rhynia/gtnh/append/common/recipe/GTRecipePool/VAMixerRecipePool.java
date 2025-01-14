package com.rhynia.gtnh.append.common.recipe.GTRecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.BUCKETS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_HV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_IV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_LuV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_MV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UHV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_ZPM;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.util.FluidHelper;
import com.rhynia.gtnh.append.common.material.VA_Materials;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.api.recipe.GTPPRecipeMaps;
import gtPlusPlus.core.material.ELEMENT;

public class VAMixerRecipePool implements IRecipePool {

    @Override
    public void loadRecipesCompleteInit() {
        final IRecipeMap MX = RecipeMaps.mixerRecipes;
        final IRecipeMap MXGTPP = GTPPRecipeMaps.mixerNonCellRecipes;

        // region 杂项
        // 深渊铁
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Iron.getDust(1), Materials.Thaumium.getDust(3), GT_Utility.getIntegratedCircuit(2))
            .itemOutputs(Materials.ShadowIron.getDust(3))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(21 * SECONDS)
            .addTo(MX);
        // 星辉催化剂
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_Materials.AstriumInfinity.get(OrePrefixes.dust, 8), Materials.InfinityCatalyst.getDust(17))
            .itemOutputs(VA_Materials.AstralCatalystBase.get(OrePrefixes.dust, 50))
            .fluidInputs(Materials.Helium.getGas(12 * BUCKETS), VA_Materials.Astrium.getMolten(4 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * SECONDS)
            .addTo(MXGTPP);
        // 超导通流
        GT_Values.RA.stdBuilder()
            .itemInputs(
                VA_Materials.AstralCatalystBase.get(OrePrefixes.dust, 12),
                Materials.TengamPurified.getDust(11),
                Materials.Infinity.getDust(10),
                Materials.CosmicNeutronium.getDust(8),
                Materials.NaquadahEnriched.getDust(6),
                Materials.Naquadria.getDust(5),
                MyMaterial.orundum.get(OrePrefixes.dust, 5),
                ELEMENT.STANDALONE.ADVANCED_NITINOL.getDust(4),
                ELEMENT.STANDALONE.ASTRAL_TITANIUM.getDust(4))
            .itemOutputs(
                VA_Materials.SuperconductorFluxRaw.get(OrePrefixes.dust, 64),
                VA_Materials.SuperconductorFluxRaw.get(OrePrefixes.dust, 15))
            .fluidInputs(
                Materials.Helium.getPlasma(6 * BUCKETS),
                Materials.DraconiumAwakened.getMolten(12 * INGOTS),
                Materials.Indium.getMolten(7 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(30 * SECONDS)
            .addTo(MXGTPP);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(3),
                Materials.Clay.getDust(4),
                Materials.Calcite.getDust(4),
                Materials.Quartzite.getDust(4))
            .fluidInputs(Materials.Water.getFluid(16 * BUCKETS))
            .fluidOutputs(FluidHelper.getFluidStackByName("wet.concrete", 18 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(5 * SECONDS)
            .addTo(MX);
        // endregion

        // region 生物培养基
        // 培养基
        GT_Values.RA.stdBuilder()
            .itemInputs(
                Materials.Calcium.getDust(3),
                VA_Materials.Astrium.get(OrePrefixes.dust, 4),
                GT_Utility.getIntegratedCircuit(15))
            .fluidInputs(GT_ModHandler.getDistilledWater(4000))
            .fluidOutputs(Materials.GrowthMediumRaw.getFluid(8000))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(15 * SECONDS)
            .addTo(MX);
        // 生物培养基
        GT_Values.RA.stdBuilder()
            .itemInputs(
                Materials.NetherStar.getDust(2),
                VA_Materials.Astrium.get(OrePrefixes.dust, 6),
                GT_Utility.getIntegratedCircuit(16))
            .fluidInputs(GT_ModHandler.getDistilledWater(4000))
            .fluidOutputs(Materials.BioMediumRaw.getFluid(8000))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(20 * SECONDS)
            .addTo(MX);
        // endregion

        // region 极寒-烈焰
        // 极寒
        GT_Values.RA.stdBuilder()
            .itemInputs(
                Materials.CallistoIce.getDust(12),
                Materials.Ledox.getDust(12),
                VA_Materials.Astrium.get(OrePrefixes.dust, 18),
                GT_Utility.getIntegratedCircuit(15))
            .itemOutputs(Materials.Cryotheum.getDust(128))
            .fluidInputs(GT_ModHandler.getDistilledWater(64 * BUCKETS))
            .fluidOutputs(FluidHelper.getFluidStackByName("cryotheum", 256 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(32 * SECONDS)
            .addTo(MX);
        // 烈焰
        GT_Values.RA.stdBuilder()
            .itemInputs(
                Materials.Sulfur.getDust(12),
                Materials.Coal.getDust(12),
                VA_Materials.Astrium.get(OrePrefixes.dust, 18),
                GT_Utility.getIntegratedCircuit(16))
            .itemOutputs(Materials.Pyrotheum.getDust(128))
            .fluidInputs(Materials.Lava.getFluid(64 * BUCKETS))
            .fluidOutputs(FluidHelper.getFluidStackByName("pyrotheum", 256 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(32 * SECONDS)
            .addTo(MX);
        // endregion
    }
}
