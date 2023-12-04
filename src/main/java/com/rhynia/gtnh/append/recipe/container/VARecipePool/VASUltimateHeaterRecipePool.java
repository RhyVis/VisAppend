package com.rhynia.gtnh.append.recipe.container.VARecipePool;

import static com.rhynia.gtnh.append.util.Values.lensInf;
import static gregtech.api.enums.TierEU.RECIPE_UEV;
import static gregtech.api.enums.TierEU.RECIPE_UHV;
import static gregtech.api.enums.TierEU.RECIPE_UIV;
import static gregtech.api.enums.TierEU.RECIPE_UMV;
import static gregtech.api.enums.TierEU.RECIPE_UV;
import static gregtech.api.util.GT_RecipeBuilder.BUCKETS;
import static gregtech.api.util.GT_RecipeBuilder.INGOTS;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.dreammaster.gthandler.CustomItemList;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.common.machine.recipeMap.VA_RecipeAdder;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.core.material.ALLOY;

public class VASUltimateHeaterRecipePool implements IRecipePool {

    final GT_Recipe.GT_Recipe_Map UH = VA_RecipeAdder.instance.sUltimateHeaterRecipes;
    final ItemStack esCata = GT_ModHandler.getModItem("eternalsingularity", "eternal_singularity", 0);

    @Override
    public void loadRecipesPostInit() {
        // region 稀有气体
        // 等离子 He
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Helium.getGas(16 * BUCKETS))
            .fluidOutputs(Materials.Helium.getPlasma(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Ne
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(WerkstoffLoader.Neon.getFluidOrGas(16 * BUCKETS))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("plasma.neon"), 16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Ar
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Argon.getGas(16 * BUCKETS))
            .fluidOutputs(Materials.Argon.getPlasma(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Kr
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(WerkstoffLoader.Krypton.getFluidOrGas(16 * BUCKETS))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("plasma.krypton"), 16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Xe
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(WerkstoffLoader.Xenon.getFluidOrGas(16 * BUCKETS))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("plasma.xenon"), 16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Rn
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Radon.getGas(16 * BUCKETS))
            .fluidOutputs(Materials.Radon.getPlasma(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // endregion
    }

    @Override
    public void loadRecipes() {

        // region 特殊材料制造
        // 兰波顿核心
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                CustomItemList.LapotronDust.get(64),
                CustomItemList.LapotronDust.get(64))
            .fluidInputs(VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(BUCKETS))
            .fluidOutputs(
                VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(8 * BUCKETS),
                VA_WerkstoffMaterialPool.astroCatalystBase.getFluidOrGas(500))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(115 * SECONDS)
            .addTo(UH);
        // 超导通流
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                VA_WerkstoffMaterialPool.Primogem.get(OrePrefixes.lens, 0),
                VA_WerkstoffMaterialPool.Originiums.get(OrePrefixes.lens, 0))
            .fluidInputs(
                VA_WerkstoffMaterialPool.astroCatalystActivated.getFluidOrGas(32 * BUCKETS),
                VA_WerkstoffMaterialPool.superconductingFluxRaw.getMolten(72 * INGOTS))
            .fluidOutputs(
                VA_WerkstoffMaterialPool.superconductingFlux.getFluidOrGas(64 * INGOTS),
                VA_WerkstoffMaterialPool.astroCatalystBase.getFluidOrGas(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(30 * SECONDS)
            .addTo(UH);
        // endregion

        // region 聚变加热
        // MetaStableOg
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                esCata,
                VA_GregtechMaterialPool.AstroInf.getGems(64))
            .fluidInputs(WerkstoffLoader.Oganesson.getFluidOrGas(144 * BUCKETS))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("molten.metastable oganesson"), 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(115 * SECONDS)
            .addTo(UH);
        // AdvNitinol
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                esCata,
                VA_GregtechMaterialPool.AstroInf.getGems(4))
            .fluidInputs(ALLOY.NITINOL_60.getFluidStack(1000 * INGOTS))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("molten.advancednitinol"), 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(135 * SECONDS)
            .addTo(UH);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                esCata,
                VA_GregtechMaterialPool.AstroInf.getGems(4))
            .fluidInputs(Materials.Nickel.getMolten(600 * INGOTS), Materials.Titanium.getMolten(600 * INGOTS))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("molten.advancednitinol"), 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(100 * SECONDS)
            .addTo(UH);
        // AstralTitanium
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                esCata,
                VA_GregtechMaterialPool.AstroInf.getGems(8))
            .fluidInputs(Materials.Titanium.getMolten(1000 * INGOTS))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("molten.astraltitanium"), 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(120 * SECONDS)
            .addTo(UH);
        // ChromaticGlass
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                esCata,
                VA_GregtechMaterialPool.AstroInf.getGems(8))
            .fluidInputs(Materials.Glass.getMolten(1000 * INGOTS))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("molten.chromaticglass"), 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(195 * SECONDS)
            .addTo(UH);
        // CelestialTungsten
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                esCata,
                VA_GregtechMaterialPool.AstroInf.getGems(8))
            .fluidInputs(Materials.Tungsten.getMolten(1000 * INGOTS))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("molten.celestialtungsten"), 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(145 * SECONDS)
            .addTo(UH);
        // DragonBlood
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                esCata,
                VA_GregtechMaterialPool.AstroInf.getGems(16))
            .fluidInputs(
                Materials.DraconiumAwakened.getMolten(1000 * INGOTS),
                VA_GregtechMaterialPool.Astro.getFluid(12 * BUCKETS))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("molten.dragonblood"), 1000 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(180 * SECONDS)
            .addTo(UH);
        // endregion

        // region 第一二周期 UV-UEV
        // 氢等离子 H
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Hydrogen.getGas(16 * BUCKETS))
            .fluidOutputs(Materials.Hydrogen.getPlasma(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 氮等离子 N
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Nitrogen.getGas(16 * BUCKETS))
            .fluidOutputs(Materials.Nitrogen.getPlasma(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 氧等离子 O
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Oxygen.getGas(16 * BUCKETS))
            .fluidOutputs(Materials.Oxygen.getPlasma(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 氟等离子 F
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Fluorine.getGas(16 * BUCKETS))
            .fluidOutputs(Materials.Fluorine.getPlasma(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // endregion
        // region 第三周期 UEV
        // 等离子 Na
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensInf,
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64))
            .fluidOutputs(Materials.Sodium.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Mg
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensInf,
                Materials.Magnesium.getDust(64),
                Materials.Magnesium.getDust(64))
            .fluidOutputs(Materials.Magnesium.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Al
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensInf,
                Materials.Aluminium.getDust(64),
                Materials.Aluminium.getDust(64))
            .fluidOutputs(Materials.Aluminium.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Si
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensInf,
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64))
            .fluidOutputs(Materials.Silicon.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 P
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensInf,
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64))
            .fluidOutputs(Materials.Phosphorus.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 S
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensInf,
                Materials.Sulfur.getDust(64),
                Materials.Sulfur.getDust(64))
            .fluidOutputs(Materials.Sulfur.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Cl
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Chlorine.getGas(16 * BUCKETS))
            .fluidOutputs(Materials.Chlorine.getPlasma(16 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // endregion
        // region 第三周期+
        // 等离子 Ag
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Silver.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Silver.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Ag
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Gold.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Gold.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Fe
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Iron.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Iron.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Ny
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Neodymium.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Neodymium.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Pb
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Lead.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Lead.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Nq
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Naquadah.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Naquadah.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Nq
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Nickel.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Nickel.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Rb
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Rubidium.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Rubidium.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Sr
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensInf,
                Materials.Strontium.getDust(64),
                Materials.Strontium.getDust(64))
            .fluidOutputs(Materials.Strontium.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Sn
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Tin.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Tin.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Ti
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Titanium.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Titanium.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 W
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Tungsten.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Tungsten.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Zn
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Zinc.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Zinc.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Pt
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Platinum.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Platinum.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // 等离子 Os
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Osmium.getMolten(128 * INGOTS))
            .fluidOutputs(Materials.Osmium.getPlasma(128 * INGOTS))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(12 * SECONDS)
            .addTo(UH);
        // endregion

    }
}
