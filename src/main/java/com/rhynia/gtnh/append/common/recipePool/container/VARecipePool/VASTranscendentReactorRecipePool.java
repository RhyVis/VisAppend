package com.rhynia.gtnh.append.common.recipePool.container.VARecipePool;

import static gregtech.api.enums.TierEU.RECIPE_MAX;
import static gregtech.api.enums.TierEU.RECIPE_UEV;
import static gregtech.api.enums.TierEU.RECIPE_UIV;
import static gregtech.api.enums.TierEU.RECIPE_UMV;
import static gregtech.api.enums.TierEU.RECIPE_UXV;
import static gregtech.api.util.GT_RecipeBuilder.BUCKETS;
import static gregtech.api.util.GT_RecipeBuilder.INGOTS;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;

import com.rhynia.gtnh.append.api.recipe.VA_Recipe;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.common.recipePool.IRecipePool;
import com.rhynia.gtnh.append.config.Config;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.HeatingCoilLevel;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.core.material.ELEMENT;

@SuppressWarnings({ "SpellCheckingInspection", "IntegerMultiplicationImplicitCastToLong" })
public class VASTranscendentReactorRecipePool implements IRecipePool {

    private final GT_Recipe.GT_Recipe_Map TR = VA_Recipe.instance.sTranscendentReactorRecipes;
    final boolean EnableDTCRecipes = Config.Reccipe_TR_DTC_Enable;
    final int RecipeMult = Config.Recipe_TR_RecipeMult;
    final int OutputMult = Config.Recipe_TR_OutputMult;
    final int CatalystACA = Config.Recipe_TR_CatalystACAMult;
    final int CatalystACR = Config.Recipe_TR_CatalystACRMult;

    @Override
    public void loadRecipesPostInit() {}

    @Override
    public void loadRecipes() {
        // DTC Conf
        if (EnableDTCRecipes) {
            loadTRDTCRecipes();
        }
        loadMiscRecipes();
    }

    public void loadTRDTCRecipes() {

        // region 催化剂
        // DTCC
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(1),
                Materials.Iron.getDust(RecipeMult),
                Materials.Calcium.getDust(RecipeMult),
                Materials.Niobium.getDust(RecipeMult))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(CatalystACA),
                Materials.Helium.getGas(RecipeMult * INGOTS))
            .fluidOutputs(
                MaterialsUEVplus.DimensionallyTranscendentCrudeCatalyst.getFluid(RecipeMult * OutputMult),
                VA_WerkstoffMaterialPool.astroResidue.getFluidOrGas(CatalystACA / 2))
            .specialValue((int) HeatingCoilLevel.UEV.getHeat())
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(10 * SECONDS)
            .addTo(TR);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                Materials.Iron.getDust(RecipeMult),
                Materials.Calcium.getDust(RecipeMult),
                Materials.Niobium.getDust(RecipeMult))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(CatalystACA),
                VA_WerkstoffMaterialPool.astroCatalystReforged.getFluidOrGas(CatalystACR),
                Materials.Helium.getGas(RecipeMult * INGOTS))
            .fluidOutputs(
                MaterialsUEVplus.ExcitedDTCC.getFluid(RecipeMult * OutputMult),
                VA_WerkstoffMaterialPool.astroResidue.getFluidOrGas(CatalystACA / 2 + CatalystACR))
            .specialValue((int) HeatingCoilLevel.UEV.getHeat())
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(10 * SECONDS)
            .addTo(TR);
        // DTPC
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(2),
                Materials.Iron.getDust(RecipeMult),
                Materials.Calcium.getDust(RecipeMult),
                Materials.Niobium.getDust(RecipeMult),
                Materials.Nickel.getDust(RecipeMult),
                Materials.Boron.getDust(RecipeMult),
                Materials.Sulfur.getDust(RecipeMult))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(CatalystACA),
                Materials.Helium.getGas(RecipeMult * INGOTS),
                Materials.Radon.getGas(RecipeMult * INGOTS))
            .fluidOutputs(
                MaterialsUEVplus.DimensionallyTranscendentProsaicCatalyst.getFluid(RecipeMult * OutputMult),
                VA_WerkstoffMaterialPool.astroResidue.getFluidOrGas(CatalystACA / 2))
            .specialValue((int) HeatingCoilLevel.UIV.getHeat())
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(10 * SECONDS)
            .addTo(TR);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                Materials.Iron.getDust(RecipeMult),
                Materials.Calcium.getDust(RecipeMult),
                Materials.Niobium.getDust(RecipeMult),
                Materials.Nickel.getDust(RecipeMult),
                Materials.Boron.getDust(RecipeMult),
                Materials.Sulfur.getDust(RecipeMult))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(CatalystACA),
                VA_WerkstoffMaterialPool.astroCatalystReforged.getFluidOrGas(CatalystACR),
                Materials.Helium.getGas(RecipeMult * INGOTS),
                Materials.Radon.getGas(RecipeMult * INGOTS))
            .fluidOutputs(
                MaterialsUEVplus.ExcitedDTPC.getFluid(RecipeMult * OutputMult),
                VA_WerkstoffMaterialPool.astroResidue.getFluidOrGas(CatalystACA / 2 + CatalystACR))
            .specialValue((int) HeatingCoilLevel.UIV.getHeat())
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(10 * SECONDS)
            .addTo(TR);
        // DTRC
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(3),
                Materials.Iron.getDust(RecipeMult),
                Materials.Calcium.getDust(RecipeMult),
                Materials.Niobium.getDust(RecipeMult),
                Materials.Nickel.getDust(RecipeMult),
                Materials.Boron.getDust(RecipeMult),
                Materials.Sulfur.getDust(RecipeMult),
                Materials.Zinc.getDust(RecipeMult),
                Materials.Silver.getDust(RecipeMult),
                Materials.Titanium.getDust(RecipeMult))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(CatalystACA),
                Materials.Helium.getGas(RecipeMult * INGOTS),
                Materials.Radon.getGas(RecipeMult * INGOTS),
                Materials.Nitrogen.getGas(RecipeMult * INGOTS))
            .fluidOutputs(
                MaterialsUEVplus.DimensionallyTranscendentResplendentCatalyst.getFluid(RecipeMult * OutputMult),
                VA_WerkstoffMaterialPool.astroResidue.getFluidOrGas(CatalystACA / 2))
            .specialValue((int) HeatingCoilLevel.UMV.getHeat())
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(10 * SECONDS)
            .addTo(TR);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(13),
                Materials.Iron.getDust(RecipeMult),
                Materials.Calcium.getDust(RecipeMult),
                Materials.Niobium.getDust(RecipeMult),
                Materials.Nickel.getDust(RecipeMult),
                Materials.Boron.getDust(RecipeMult),
                Materials.Sulfur.getDust(RecipeMult),
                Materials.Zinc.getDust(RecipeMult),
                Materials.Silver.getDust(RecipeMult),
                Materials.Titanium.getDust(RecipeMult))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(CatalystACA),
                VA_WerkstoffMaterialPool.astroCatalystReforged.getFluidOrGas(CatalystACR),
                Materials.Helium.getGas(RecipeMult * INGOTS),
                Materials.Radon.getGas(RecipeMult * INGOTS),
                Materials.Nitrogen.getGas(RecipeMult * INGOTS))
            .fluidOutputs(
                MaterialsUEVplus.ExcitedDTRC.getFluid(RecipeMult * OutputMult),
                VA_WerkstoffMaterialPool.astroResidue.getFluidOrGas(CatalystACA / 2 + CatalystACR))
            .specialValue((int) HeatingCoilLevel.UMV.getHeat())
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(10 * SECONDS)
            .addTo(TR);
        // DTEC
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                Materials.Iron.getDust(RecipeMult),
                Materials.Calcium.getDust(RecipeMult),
                Materials.Niobium.getDust(RecipeMult),
                Materials.Nickel.getDust(RecipeMult),
                Materials.Boron.getDust(RecipeMult),
                Materials.Sulfur.getDust(RecipeMult),
                Materials.Zinc.getDust(RecipeMult),
                Materials.Silver.getDust(RecipeMult),
                Materials.Titanium.getDust(RecipeMult),
                Materials.Americium.getDust(RecipeMult),
                Materials.Bismuth.getDust(RecipeMult),
                Materials.Tin.getDust(RecipeMult))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(CatalystACA),
                Materials.Helium.getGas(RecipeMult * INGOTS),
                Materials.Radon.getGas(RecipeMult * INGOTS),
                Materials.Nitrogen.getGas(RecipeMult * INGOTS),
                Materials.Oxygen.getGas(RecipeMult * INGOTS))
            .fluidOutputs(
                MaterialsUEVplus.DimensionallyTranscendentExoticCatalyst.getFluid(RecipeMult * OutputMult),
                VA_WerkstoffMaterialPool.astroResidue.getFluidOrGas(CatalystACA / 2))
            .specialValue((int) HeatingCoilLevel.UXV.getHeat())
            .noOptimize()
            .eut(RECIPE_UXV)
            .duration(10 * SECONDS)
            .addTo(TR);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(14),
                Materials.Iron.getDust(RecipeMult),
                Materials.Calcium.getDust(RecipeMult),
                Materials.Niobium.getDust(RecipeMult),
                Materials.Nickel.getDust(RecipeMult),
                Materials.Boron.getDust(RecipeMult),
                Materials.Sulfur.getDust(RecipeMult),
                Materials.Zinc.getDust(RecipeMult),
                Materials.Silver.getDust(RecipeMult),
                Materials.Titanium.getDust(RecipeMult),
                Materials.Americium.getDust(RecipeMult),
                Materials.Bismuth.getDust(RecipeMult),
                Materials.Tin.getDust(RecipeMult))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(CatalystACA),
                VA_WerkstoffMaterialPool.astroCatalystReforged.getFluidOrGas(CatalystACR),
                Materials.Helium.getGas(RecipeMult * INGOTS),
                Materials.Radon.getGas(RecipeMult * INGOTS),
                Materials.Nitrogen.getGas(RecipeMult * INGOTS),
                Materials.Oxygen.getGas(RecipeMult * INGOTS))
            .fluidOutputs(
                MaterialsUEVplus.ExcitedDTEC.getFluid(RecipeMult * OutputMult),
                VA_WerkstoffMaterialPool.astroResidue.getFluidOrGas(CatalystACA / 2 + CatalystACR))
            .specialValue((int) HeatingCoilLevel.UXV.getHeat())
            .noOptimize()
            .eut(RECIPE_UXV)
            .duration(10 * SECONDS)
            .addTo(TR);
        // DTSC
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(5),
                Materials.Iron.getDust(RecipeMult),
                Materials.Calcium.getDust(RecipeMult),
                Materials.Niobium.getDust(RecipeMult),
                Materials.Nickel.getDust(RecipeMult),
                Materials.Boron.getDust(RecipeMult),
                Materials.Sulfur.getDust(RecipeMult),
                Materials.Zinc.getDust(RecipeMult),
                Materials.Silver.getDust(RecipeMult),
                Materials.Titanium.getDust(RecipeMult),
                Materials.Americium.getDust(RecipeMult),
                Materials.Bismuth.getDust(RecipeMult),
                Materials.Tin.getDust(RecipeMult),
                Materials.Lead.getDust(RecipeMult),
                Materials.Thorium.getDust(RecipeMult),
                Materials.Plutonium241.getDust(RecipeMult))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(CatalystACA),
                Materials.Helium.getGas(RecipeMult * INGOTS),
                Materials.Radon.getGas(RecipeMult * INGOTS),
                Materials.Nitrogen.getGas(RecipeMult * INGOTS),
                Materials.Oxygen.getGas(RecipeMult * INGOTS),
                MaterialsUEVplus.RawStarMatter.getFluid(200L))
            .fluidOutputs(
                MaterialsUEVplus.DimensionallyTranscendentStellarCatalyst.getFluid(RecipeMult * OutputMult),
                VA_WerkstoffMaterialPool.astroResidue.getFluidOrGas(CatalystACA / 2))
            .specialValue((int) HeatingCoilLevel.MAX.getHeat())
            .noOptimize()
            .eut(RECIPE_MAX)
            .duration(10 * SECONDS)
            .addTo(TR);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(15),
                Materials.Iron.getDust(RecipeMult),
                Materials.Calcium.getDust(RecipeMult),
                Materials.Niobium.getDust(RecipeMult),
                Materials.Nickel.getDust(RecipeMult),
                Materials.Boron.getDust(RecipeMult),
                Materials.Sulfur.getDust(RecipeMult),
                Materials.Zinc.getDust(RecipeMult),
                Materials.Silver.getDust(RecipeMult),
                Materials.Titanium.getDust(RecipeMult),
                Materials.Americium.getDust(RecipeMult),
                Materials.Bismuth.getDust(RecipeMult),
                Materials.Tin.getDust(RecipeMult),
                Materials.Lead.getDust(RecipeMult),
                Materials.Thorium.getDust(RecipeMult),
                Materials.Plutonium241.getDust(RecipeMult))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(CatalystACA),
                VA_WerkstoffMaterialPool.astroCatalystReforged.getFluidOrGas(CatalystACR),
                Materials.Helium.getGas(RecipeMult * INGOTS),
                Materials.Radon.getGas(RecipeMult * INGOTS),
                Materials.Nitrogen.getGas(RecipeMult * INGOTS),
                Materials.Oxygen.getGas(RecipeMult * INGOTS),
                MaterialsUEVplus.RawStarMatter.getFluid(200L))
            .fluidOutputs(
                MaterialsUEVplus.ExcitedDTSC.getFluid(RecipeMult * OutputMult),
                VA_WerkstoffMaterialPool.astroResidue.getFluidOrGas(CatalystACA / 2 + CatalystACR))
            .specialValue((int) HeatingCoilLevel.MAX.getHeat())
            .noOptimize()
            .eut(RECIPE_MAX)
            .duration(10 * SECONDS)
            .addTo(TR);
        // endregion
    }

    public void loadMiscRecipes() {
        // Sx ONE-STEP
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_WerkstoffMaterialPool.astroCatalystBase.get(OrePrefixes.dust, 12),
                Materials.TengamPurified.getDust(11),
                Materials.Infinity.getDust(10),
                Materials.CosmicNeutronium.getDust(8),
                Materials.NaquadahEnriched.getDust(6),
                Materials.Naquadria.getDust(5),
                MyMaterial.orundum.get(OrePrefixes.dust, 5),
                ELEMENT.STANDALONE.ADVANCED_NITINOL.getDust(4),
                ELEMENT.STANDALONE.ASTRAL_TITANIUM.getDust(4))
            .itemOutputs(VA_GregtechMaterialPool.AstroMagic.getDust(16))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(48 * BUCKETS),
                Materials.Helium.getPlasma(6 * BUCKETS),
                Materials.DraconiumAwakened.getMolten(12 * INGOTS),
                Materials.Indium.getMolten(7 * INGOTS))
            .fluidOutputs(
                VA_WerkstoffMaterialPool.superconductingFlux.getFluidOrGas(72 * INGOTS),
                VA_WerkstoffMaterialPool.astroResidue.getFluidOrGas(24 * BUCKETS))
            .specialValue((int) HeatingCoilLevel.UEV.getHeat())
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(20 * SECONDS)
            .addTo(TR);
    }
}
