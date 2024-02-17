package com.rhynia.gtnh.append.common.recipe.VARecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.BUCKETS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_MAX;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UEV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UIV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UMV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UXV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;
import static gregtech.api.enums.Mods.GTPlusPlus;

import com.rhynia.gtnh.append.api.enums.refHelper.SCPart;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.api.recipe.builder.VA_RecipeBuilder;
import com.rhynia.gtnh.append.api.util.FluidHelper;
import com.rhynia.gtnh.append.api.util.ItemHelper;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.material.VAMaterials;
import com.rhynia.gtnh.append.config.Config;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.HeatingCoilLevel;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.core.material.ALLOY;
import gtPlusPlus.core.material.ELEMENT;

@SuppressWarnings({ "SpellCheckingInspection", "IntegerMultiplicationImplicitCastToLong" })
public class VASTranscendentReactorRecipePool implements IRecipePool {

    private final RecipeMap<?> TR = AppendRecipeMaps.transcendentReactorRecipes;
    final boolean EnableDTCRecipes = Config.Reccipe_TR_DTC_Enable;
    final int RecipeMult = Config.Recipe_TR_RecipeMult;
    final int OutputMult = Config.Recipe_TR_OutputMult;
    final int CatalystACA = Config.Recipe_TR_CatalystACAMult;

    @Override
    public void loadRecipesCompleteInit() {
        // DTC Conf
        if (EnableDTCRecipes) {
            loadTRDTCRecipes();
        }
        loadMiscRecipes();
    }

    public void loadTRDTCRecipes() {

        // region 催化剂
        // DTCC
        VA_RecipeBuilder.builder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(1),
                ItemHelper.setStackSize(Materials.Iron.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Calcium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Niobium.getDust(1), RecipeMult))
            .fluidInputs(
                VAMaterials.AstralCatalystBaseExcited.getFluidOrGas(CatalystACA / 4),
                Materials.Helium.getGas(RecipeMult * INGOTS))
            .fluidOutputs(
                MaterialsUEVplus.ExcitedDTCC.getFluid(RecipeMult * OutputMult),
                VAMaterials.AstralResidue.getFluidOrGas(CatalystACA / 8))
            .specialValue((int) HeatingCoilLevel.UEV.getHeat())
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(10 * SECONDS)
            .addTo(TR);
        // DTPC
        VA_RecipeBuilder.builder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(2),
                ItemHelper.setStackSize(Materials.Iron.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Calcium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Niobium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Nickel.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Boron.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Sulfur.getDust(1), RecipeMult))
            .fluidInputs(
                VAMaterials.AstralCatalystBaseExcited.getFluidOrGas(CatalystACA / 2),
                Materials.Helium.getGas(RecipeMult * INGOTS),
                Materials.Radon.getGas(RecipeMult * INGOTS))
            .fluidOutputs(
                MaterialsUEVplus.ExcitedDTPC.getFluid(RecipeMult * OutputMult),
                VAMaterials.AstralResidue.getFluidOrGas(CatalystACA / 4))
            .specialValue((int) HeatingCoilLevel.UIV.getHeat())
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(10 * SECONDS)
            .addTo(TR);
        // DTRC
        VA_RecipeBuilder.builder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(3),
                ItemHelper.setStackSize(Materials.Iron.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Calcium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Niobium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Nickel.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Boron.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Sulfur.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Zinc.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Silver.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Titanium.getDust(1), RecipeMult))
            .fluidInputs(
                VAMaterials.AstralCatalystBaseExcited.getFluidOrGas(CatalystACA),
                Materials.Helium.getGas(RecipeMult * INGOTS),
                Materials.Radon.getGas(RecipeMult * INGOTS),
                Materials.Nitrogen.getGas(RecipeMult * INGOTS))
            .fluidOutputs(
                MaterialsUEVplus.ExcitedDTRC.getFluid(RecipeMult * OutputMult),
                VAMaterials.AstralResidue.getFluidOrGas(CatalystACA / 2))
            .specialValue((int) HeatingCoilLevel.UMV.getHeat())
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(10 * SECONDS)
            .addTo(TR);
        // DTEC
        VA_RecipeBuilder.builder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                ItemHelper.setStackSize(Materials.Iron.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Calcium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Niobium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Nickel.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Boron.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Sulfur.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Zinc.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Silver.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Titanium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Americium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Bismuth.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Tin.getDust(1), RecipeMult))
            .fluidInputs(
                VAMaterials.AstralCatalystBaseExcited.getFluidOrGas(CatalystACA * 2),
                Materials.Helium.getGas(RecipeMult * INGOTS),
                Materials.Radon.getGas(RecipeMult * INGOTS),
                Materials.Nitrogen.getGas(RecipeMult * INGOTS),
                Materials.Oxygen.getGas(RecipeMult * INGOTS))
            .fluidOutputs(
                MaterialsUEVplus.ExcitedDTEC.getFluid(RecipeMult * OutputMult),
                VAMaterials.AstralResidue.getFluidOrGas(CatalystACA))
            .specialValue((int) HeatingCoilLevel.UXV.getHeat())
            .noOptimize()
            .eut(RECIPE_UXV)
            .duration(10 * SECONDS)
            .addTo(TR);
        // DTSC
        VA_RecipeBuilder.builder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(5),
                ItemHelper.setStackSize(Materials.Iron.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Calcium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Niobium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Nickel.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Boron.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Sulfur.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Zinc.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Silver.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Titanium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Americium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Bismuth.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Tin.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Lead.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Thorium.getDust(1), RecipeMult),
                ItemHelper.setStackSize(Materials.Plutonium241.getDust(1), RecipeMult))
            .fluidInputs(
                VAMaterials.AstralCatalystBaseExcited.getFluidOrGas(CatalystACA * 4),
                Materials.Helium.getGas(RecipeMult * INGOTS),
                Materials.Radon.getGas(RecipeMult * INGOTS),
                Materials.Nitrogen.getGas(RecipeMult * INGOTS),
                Materials.Oxygen.getGas(RecipeMult * INGOTS),
                MaterialsUEVplus.RawStarMatter.getFluid(RecipeMult * 100L))
            .fluidOutputs(
                MaterialsUEVplus.ExcitedDTSC.getFluid(RecipeMult * OutputMult),
                VAMaterials.AstralResidue.getFluidOrGas(CatalystACA * 2))
            .specialValue((int) HeatingCoilLevel.MAX.getHeat())
            .noOptimize()
            .eut(RECIPE_MAX)
            .duration(10 * SECONDS)
            .addTo(TR);
        // endregion
    }

    public void loadMiscRecipes() {
        // region SC
        // UV
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(10), VAItemList.AstriumInfinityGem.get(4))
            .fluidInputs(
                Materials.Samarium.getMolten(1816 * INGOTS),
                Materials.Europium.getMolten(1816 * INGOTS),
                Materials.Osmiridium.getMolten(5448 * INGOTS),
                Materials.Naquadria.getMolten(7264 * INGOTS))
            .fluidOutputs(SCPart.UV.getMolten(16344 * INGOTS), VAMaterials.AstralResidue.getFluidOrGas(4 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UXV)
            .duration(4 * SECONDS)
            .addTo(TR);
        // UHV
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(10), VAItemList.AstriumInfinityGem.get(8))
            .fluidInputs(
                Materials.Draconium.getMolten(2016 * INGOTS),
                Materials.Americium.getMolten(2016 * INGOTS),
                Materials.CosmicNeutronium.getMolten(2352 * INGOTS),
                Materials.Tritanium.getMolten(1680 * INGOTS))
            .fluidOutputs(SCPart.UHV.getMolten(8064 * INGOTS), VAMaterials.AstralResidue.getFluidOrGas(8 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UXV)
            .duration(8 * SECONDS)
            .addTo(TR);
        // UEV
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(10), VAItemList.AstriumInfinityGem.get(16))
            .fluidInputs(
                ELEMENT.STANDALONE.ADVANCED_NITINOL.getFluidStack(168 * INGOTS),
                ELEMENT.STANDALONE.CELESTIAL_TUNGSTEN.getFluidStack(168 * INGOTS),
                Materials.DraconiumAwakened.getMolten(840 * INGOTS),
                Materials.Infinity.getMolten(840 * INGOTS),
                Materials.Iron.getMolten(168 * INGOTS))
            .fluidOutputs(SCPart.UEV.getMolten(2016 * INGOTS), VAMaterials.AstralResidue.getFluidOrGas(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UXV)
            .duration(16 * SECONDS)
            .addTo(TR);
        // UIV
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(10), VAItemList.AstriumInfinityGem.get(32))
            .fluidInputs(
                FluidHelper.getFluidStackByName("molten.radoxpoly", 160 * INGOTS),
                MaterialsUEVplus.TranscendentMetal.getMolten(400 * INGOTS),
                ELEMENT.STANDALONE.RHUGNOR.getFluidStack(240 * INGOTS),
                ELEMENT.STANDALONE.CHRONOMATIC_GLASS.getFluidStack(200 * INGOTS),
                Materials.Bismuth.getMolten(40 * INGOTS))
            .fluidOutputs(SCPart.UIV.getMolten(1000 * INGOTS), VAMaterials.AstralResidue.getFluidOrGas(32 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UXV)
            .duration(32 * SECONDS)
            .addTo(TR);
        // UMV
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(10), VAItemList.AstriumInfinityGem.get(64))
            .fluidInputs(
                MaterialsUEVplus.SpaceTime.getMolten(216 * INGOTS),
                MyMaterial.orundum.getMolten(108 * INGOTS),
                ELEMENT.STANDALONE.HYPOGEN.getFluidStack(396 * INGOTS),
                ALLOY.TITANSTEEL.getFluidStack(180 * INGOTS),
                FluidHelper.getFluidStackByName("molten.dragonblood", 72 * INGOTS),
                Materials.Oxygen.getGas(36 * INGOTS))
            .fluidOutputs(SCPart.UMV.getMolten(972 * INGOTS), VAMaterials.AstralResidue.getFluidOrGas(64 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UXV)
            .duration(64 * SECONDS)
            .addTo(TR);
        // endregion

        // Sx ONE-STEP
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VAMaterials.AstralCatalystBase.get(OrePrefixes.dust, 12),
                Materials.TengamPurified.getDust(11),
                Materials.Infinity.getDust(10),
                Materials.CosmicNeutronium.getDust(8),
                Materials.NaquadahEnriched.getDust(6),
                Materials.Naquadria.getDust(5),
                MyMaterial.orundum.get(OrePrefixes.dust, 5),
                ELEMENT.STANDALONE.ADVANCED_NITINOL.getDust(4),
                ELEMENT.STANDALONE.ASTRAL_TITANIUM.getDust(4))
            .itemOutputs(VAMaterials.AstriumMagic.get(OrePrefixes.dust, 16))
            .fluidInputs(
                VAMaterials.AstralCatalystBaseExcited.getFluidOrGas(48 * BUCKETS),
                Materials.Helium.getPlasma(6 * BUCKETS),
                Materials.DraconiumAwakened.getMolten(12 * INGOTS),
                Materials.Indium.getMolten(7 * INGOTS))
            .fluidOutputs(
                VAMaterials.SuperconductorFlux.getFluidOrGas(72 * INGOTS),
                VAMaterials.AstralResidue.getFluidOrGas(24 * BUCKETS))
            .specialValue((int) HeatingCoilLevel.UEV.getHeat())
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(20 * SECONDS)
            .addTo(TR);
        // SpaceTime Extract
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VAItemList.AstriumInfinityGem.get(16),
                ItemList.EnergisedTesseract.get(1))
            .itemOutputs(
                VAMaterials.Astrium.get(OrePrefixes.dust, 64),
                VAMaterials.AstriumMagic.get(OrePrefixes.dust, 64),
                VAMaterials.AstriumInfinity.get(OrePrefixes.dust, 64),
                VAMaterials.Astrium.get(OrePrefixes.dust, 64),
                VAMaterials.AstriumMagic.get(OrePrefixes.dust, 64),
                VAMaterials.AstriumInfinity.get(OrePrefixes.dust, 64))
            .outputChances(5000, 5000, 5000, 2500, 2500, 2500)
            .fluidInputs(MaterialsUEVplus.SpaceTime.getMolten(512 * INGOTS))
            .fluidOutputs(
                MaterialsUEVplus.Space.getMolten(512 * INGOTS),
                MaterialsUEVplus.Time.getMolten(512 * INGOTS),
                VAMaterials.AstralResidue.getFluidOrGas(10 * BUCKETS),
                MaterialsUEVplus.DimensionallyTranscendentResidue.getFluid(10 * BUCKETS))
            .specialValue((int) HeatingCoilLevel.UMV.getHeat())
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(20 * SECONDS)
            .addTo(TR);
        // SpaceTime Transform
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                VAItemList.LensAstriumInfinity.get(0),
                VAItemList.AstriumInfinityGem.get(4))
            .itemOutputs(
                GT_Utility.copyAmountUnsafe(256, GT_ModHandler.getModItem(GTPlusPlus.ID, "MU-metaitem.01", 1, 32105)))
            .fluidInputs(
                MaterialsUEVplus.SpaceTime.getMolten(16 * INGOTS),
                VAMaterials.AstralCatalystBaseExcited.getFluidOrGas(4 * BUCKETS))
            .fluidOutputs(
                VAMaterials.AstralResidue.getFluidOrGas(10 * BUCKETS),
                MaterialsUEVplus.DimensionallyTranscendentResidue.getFluid(16 * BUCKETS))
            .specialValue((int) HeatingCoilLevel.UIV.getHeat())
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(16 * SECONDS)
            .addTo(TR);
    }
}
