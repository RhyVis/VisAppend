package com.rhynia.gtnh.append.common.recipe.VARecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.BUCKETS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.MINUTES;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UEV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UHV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UIV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UMV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import net.minecraft.item.ItemStack;

import com.dreammaster.gthandler.CustomItemList;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.api.util.FluidHelper;
import com.rhynia.gtnh.append.common.loader.VA_ItemList;
import com.rhynia.gtnh.append.common.material.VA_Materials;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.core.material.ALLOY;
import gtPlusPlus.core.material.ELEMENT;

@SuppressWarnings({ "SpellCheckingInspection" })
public class VASThermonuclearControlRecipePool implements IRecipePool {

    private final IRecipeMap TC = AppendRecipeMaps.thermonuclearControlRecipes;
    private final ItemStack esCata = GT_ModHandler.getModItem("eternalsingularity", "eternal_singularity", 0);

    @Override
    public void loadRecipesPostInit() {
        // region 稀有气体
        // 等离子 He
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), VA_ItemList.LensAstriumInfinity.get(0))
            .fluidInputs(Materials.Helium.getGas(16 * BUCKETS))
            .fluidOutputs(Materials.Helium.getPlasma(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * SECONDS)
            .addTo(TC);
        // 等离子 Ne
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), VA_ItemList.LensAstriumInfinity.get(0))
            .fluidInputs(WerkstoffLoader.Neon.getFluidOrGas(16 * BUCKETS))
            .fluidOutputs(FluidHelper.getFluidStackByName("plasma.neon", 16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * SECONDS)
            .addTo(TC);
        // 等离子 Ar
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), VA_ItemList.LensAstriumInfinity.get(0))
            .fluidInputs(Materials.Argon.getGas(16 * BUCKETS))
            .fluidOutputs(Materials.Argon.getPlasma(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * SECONDS)
            .addTo(TC);
        // 等离子 Kr
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), VA_ItemList.LensAstriumInfinity.get(0))
            .fluidInputs(WerkstoffLoader.Krypton.getFluidOrGas(16 * BUCKETS))
            .fluidOutputs(FluidHelper.getFluidStackByName("plasma.krypton", 16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(TC);
        // 等离子 Xe
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), VA_ItemList.LensAstriumInfinity.get(0))
            .fluidInputs(WerkstoffLoader.Xenon.getFluidOrGas(16 * BUCKETS))
            .fluidOutputs(FluidHelper.getFluidStackByName("plasma.xenon", 16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(TC);
        // 等离子 Rn
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), VA_ItemList.LensAstriumInfinity.get(0))
            .fluidInputs(Materials.Radon.getGas(16 * BUCKETS))
            .fluidOutputs(Materials.Radon.getPlasma(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(TC);
        // endregion
    }

    @Override
    public void loadRecipesCompleteInit() {

        // region 特殊材料制造
        // 兰波顿核心
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                CustomItemList.LapotronDust.get(64),
                CustomItemList.LapotronDust.get(64))
            .fluidInputs(VA_Materials.AstralCatalystBaseExcited.getFluidOrGas(BUCKETS))
            .fluidOutputs(
                VA_Materials.LapotronEnhancedFluid.getFluidOrGas(8 * BUCKETS),
                VA_Materials.AstralResidue.getFluidOrGas(500))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(115 * SECONDS)
            .addTo(TC);
        // 超导通流
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                esCata,
                VA_ItemList.LensOriginium.get(0),
                VA_ItemList.LensAstriumMagic.get(0))
            .fluidInputs(
                VA_Materials.AstralCatalystBaseExcited.getFluidOrGas(32 * BUCKETS),
                VA_Materials.SuperconductorFluxRaw.getMolten(72 * INGOTS))
            .fluidOutputs(
                VA_Materials.SuperconductorFlux.getFluidOrGas(64 * INGOTS),
                VA_Materials.AstralResidue.getFluidOrGas(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(30 * SECONDS)
            .addTo(TC);
        // endregion

        // region 聚变
        // MetaStableOg
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                esCata,
                VA_ItemList.AstriumInfinityGem.get(64))
            .fluidInputs(WerkstoffLoader.Oganesson.getFluidOrGas(144 * BUCKETS))
            .fluidOutputs(FluidHelper.getFluidStackByName("molten.metastable oganesson", 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(115 * SECONDS)
            .addTo(TC);
        // AdvNitinol
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                esCata,
                VA_ItemList.AstriumInfinityGem.get(4))
            .fluidInputs(ALLOY.NITINOL_60.getFluidStack(1000 * INGOTS))
            .fluidOutputs(FluidHelper.getFluidStackByName("molten.advancednitinol", 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(135 * SECONDS)
            .addTo(TC);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                esCata,
                VA_ItemList.AstriumInfinityGem.get(8))
            .fluidInputs(Materials.Nickel.getMolten(400 * INGOTS), Materials.Titanium.getMolten(600 * INGOTS))
            .fluidOutputs(FluidHelper.getFluidStackByName("molten.advancednitinol", 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(100 * SECONDS)
            .addTo(TC);
        // AstralTitanium
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                esCata,
                VA_ItemList.AstriumInfinityGem.get(8))
            .fluidInputs(Materials.Titanium.getMolten(1000 * INGOTS))
            .fluidOutputs(FluidHelper.getFluidStackByName("molten.astraltitanium", 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(120 * SECONDS)
            .addTo(TC);
        // ChromaticGlass
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                esCata,
                VA_ItemList.AstriumInfinityGem.get(8))
            .fluidInputs(Materials.Glass.getMolten(1000 * INGOTS))
            .fluidOutputs(FluidHelper.getFluidStackByName("molten.chromaticglass", 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(195 * SECONDS)
            .addTo(TC);
        // CelestialTungsten
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                esCata,
                VA_ItemList.AstriumInfinityGem.get(8))
            .fluidInputs(Materials.Tungsten.getMolten(1000 * INGOTS))
            .fluidOutputs(FluidHelper.getFluidStackByName("molten.celestialtungsten", 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(145 * SECONDS)
            .addTo(TC);
        // DragonBlood
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                esCata,
                VA_ItemList.AstriumInfinityGem.get(16))
            .fluidInputs(
                Materials.DraconiumAwakened.getMolten(1000 * INGOTS),
                VA_Materials.Astrium.getMolten(12 * BUCKETS))
            .fluidOutputs(FluidHelper.getFluidStackByName("molten.dragonblood", 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(3 * MINUTES)
            .addTo(TC);
        // Np
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                esCata,
                VA_ItemList.AstriumInfinityGem.get(16))
            .fluidInputs(Materials.Radon.getGas(750 * INGOTS), Materials.Nitrogen.getGas(750 * INGOTS))
            .fluidOutputs(FluidHelper.getFluidStackByName("plasma.neptunium", 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(40 * SECONDS)
            .addTo(TC);
        // Fm
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                esCata,
                VA_ItemList.AstriumInfinityGem.get(16))
            .fluidInputs(Materials.Americium.getMolten(750 * INGOTS), Materials.Boron.getMolten(750 * INGOTS))
            .fluidOutputs(FluidHelper.getFluidStackByName("plasma.fermium", 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(40 * SECONDS)
            .addTo(TC);
        // Transcendent Metal
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                esCata,
                VA_ItemList.PreTesseract.get(64))
            .fluidInputs(
                Materials.CosmicNeutronium.getMolten(64 * INGOTS),
                VA_Materials.AstralCatalystBaseExcited.getFluidOrGas(4 * BUCKETS))
            .fluidOutputs(
                MaterialsUEVplus.TranscendentMetal.getMolten(32 * 64 * INGOTS),
                ELEMENT.STANDALONE.CELESTIAL_TUNGSTEN.getFluidStack(16 * 64 * INGOTS),
                VA_Materials.AstralResidue.getFluidOrGas(4 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(30 * SECONDS)
            .addTo(TC);
        // Shirabon
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                esCata,
                VA_ItemList.AstriumInfinityGem.get(32))
            .fluidInputs(
                MyMaterial.metastableOganesson.getMolten(16 * INGOTS),
                VA_Materials.AstralCatalystReforged.getFluidOrGas(250))
            .fluidOutputs(MyMaterial.shirabon.getMolten(16 * INGOTS), VA_Materials.AstralResidue.getFluidOrGas(500))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(25 * SECONDS)
            .addTo(TC);
        // ELEMENT.STANDALONE.RHUGNOR.getFluidStack(240 * INGOTS)
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                esCata,
                GT_Utility.copyAmountUnsafe(96, VA_ItemList.AstriumInfinityGem.get(1)))
            .fluidInputs(Materials.Infinity.getMolten(256 * INGOTS), ALLOY.QUANTUM.getFluidStack(256 * INGOTS))
            .fluidOutputs(
                ELEMENT.STANDALONE.RHUGNOR.getFluidStack(512 * INGOTS),
                VA_Materials.AstralResidue.getFluidOrGas(5000))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(80 * SECONDS)
            .addTo(TC);
        // endregion

        // region Gas to Plasma (UEV)
        Materials[] gas2plasma = { Materials.Hydrogen, Materials.Nitrogen, Materials.Oxygen, Materials.Fluorine,
            Materials.Chlorine };
        for (Materials materials : gas2plasma) {
            GT_Values.RA.stdBuilder()
                .itemInputs(GT_Utility.getIntegratedCircuit(12), VA_ItemList.LensAstriumInfinity.get(0))
                .fluidInputs(materials.getGas(128 * INGOTS))
                .fluidOutputs(materials.getPlasma(128 * INGOTS))
                .noOptimize()
                .eut(RECIPE_UEV)
                .duration(12 * SECONDS)
                .addTo(TC);
        }
        // endregion

        // region Dust to Plasma (UEV)
        Materials[] dust2plasma = { Materials.Boron, Materials.Sodium, Materials.Magnesium, Materials.Aluminium,
            Materials.Silicon, Materials.Phosphorus, Materials.Sulfur, Materials.Strontium };
        for (Materials materials : dust2plasma) {
            GT_Values.RA.stdBuilder()
                .itemInputs(
                    GT_Utility.getIntegratedCircuit(12),
                    VA_ItemList.LensAstriumInfinity.get(0),
                    materials.getDust(64),
                    materials.getDust(64))
                .fluidOutputs(materials.getPlasma(128 * INGOTS))
                .noOptimize()
                .eut(RECIPE_UEV)
                .duration(12 * SECONDS)
                .addTo(TC);
        }
        // endregion

        // region Molten to Plasma (UEV)
        Materials[] molten2plasma = { Materials.Silver, Materials.Gold, Materials.Iron, Materials.Bismuth,
            Materials.Calcium, Materials.Neodymium, Materials.Niobium, Materials.Lead, Materials.Naquadah,
            Materials.Nickel, Materials.Rubidium, Materials.Thorium, Materials.Plutonium241, Materials.Americium,
            Materials.Tin, Materials.Titanium, Materials.Tungsten, Materials.Zinc, Materials.Platinum,
            Materials.Osmium };
        for (Materials materials : molten2plasma) {
            GT_Values.RA.stdBuilder()
                .itemInputs(GT_Utility.getIntegratedCircuit(12), VA_ItemList.LensAstriumInfinity.get(0))
                .fluidInputs(materials.getMolten(128 * INGOTS))
                .fluidOutputs(materials.getPlasma(128 * INGOTS))
                .noOptimize()
                .eut(RECIPE_UEV)
                .duration(12 * SECONDS)
                .addTo(TC);
        }
        // endregion

    }
}
