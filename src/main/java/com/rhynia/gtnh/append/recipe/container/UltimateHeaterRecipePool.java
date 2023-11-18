package com.rhynia.gtnh.append.recipe.container;

import static com.rhynia.gtnh.append.util.UtilValues.lensInf;
import static gregtech.api.enums.TierEU.*;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.dreammaster.gthandler.CustomItemList;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.common.machine.recipe.GTAppendRecipe;
import com.rhynia.gtnh.append.common.material.MaterialBartworksMethod;
import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.core.material.ALLOY;

public class UltimateHeaterRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map UH = GTAppendRecipe.instance.UltimateHeaterRecipes;

        // region 兰波顿
        // 兰波顿核心
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                CustomItemList.LapotronDust.get(64),
                CustomItemList.LapotronDust.get(64))
            .fluidInputs(MaterialBartworksMethod.astroCatalystActivated.getFluidOrGas(1000))
            .fluidOutputs(MaterialBartworksMethod.lapoActivatedFluid.getFluidOrGas(8000))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(115 * 20)
            .addTo(UH);
        // endregion

        // region 聚变加热
        // 等离子 Og
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                GT_ModHandler.getModItem("eternalsingularity", "eternal_singularity", 0),
                MaterialGTMethod.AstroInf.getGems(64))
            .fluidInputs(WerkstoffLoader.Oganesson.getFluidOrGas(144000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("molten.metastable oganesson"), 144000))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(115 * 20)
            .addTo(UH);
        // 等离子 Nitinol
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                GT_ModHandler.getModItem("eternalsingularity", "eternal_singularity", 0),
                MaterialGTMethod.AstroInf.getGems(4))
            .fluidInputs(ALLOY.NITINOL_60.getFluidStack(144000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("molten.advancednitinol"), 144000))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(135 * 20)
            .addTo(UH);
        // 等离子 AstralTitanium
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                GT_ModHandler.getModItem("eternalsingularity", "eternal_singularity", 0),
                MaterialGTMethod.AstroInf.getGems(8))
            .fluidInputs(Materials.Titanium.getMolten(144000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("molten.astraltitanium"), 144000))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(120 * 20)
            .addTo(UH);
        // 等离子 ChromaticGlass
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                GT_ModHandler.getModItem("eternalsingularity", "eternal_singularity", 0),
                MaterialGTMethod.AstroInf.getGems(8))
            .fluidInputs(Materials.Glass.getMolten(144000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("molten.chromaticglass"), 144000))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(195 * 20)
            .addTo(UH);
        // 等离子 CelestialTungsten
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                GT_ModHandler.getModItem("eternalsingularity", "eternal_singularity", 0),
                MaterialGTMethod.AstroInf.getGems(8))
            .fluidInputs(Materials.Tungsten.getMolten(144000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("molten.celestialtungsten"), 144000))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(145 * 20)
            .addTo(UH);
        // 等离子 DragonBlood
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                GT_ModHandler.getModItem("eternalsingularity", "eternal_singularity", 0),
                MaterialGTMethod.AstroInf.getGems(16))
            .fluidInputs(Materials.Enderium.getMolten(144000), MaterialGTMethod.Astro.getFluid(12000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("molten.dragonblood"), 144000))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(180 * 20)
            .addTo(UH);
        // endregion

        // region 稀有气体
        // 等离子 He
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Helium.getGas(16000))
            .fluidOutputs(Materials.Helium.getPlasma(16000))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Ne
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(WerkstoffLoader.Neon.getFluidOrGas(16000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("plasma.neon"), 16000))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Ar
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Argon.getGas(16000))
            .fluidOutputs(Materials.Argon.getPlasma(16000))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Kr
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(WerkstoffLoader.Krypton.getFluidOrGas(16000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("plasma.krypton"), 16000))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Xe
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(WerkstoffLoader.Xenon.getFluidOrGas(16000))
            .fluidOutputs(new FluidStack(FluidRegistry.getFluid("plasma.xenon"), 16000))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Rn
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Radon.getGas(16000))
            .fluidOutputs(Materials.Radon.getPlasma(16000))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // endregion
        // region 第一二周期 UV-UEV
        // 氢等离子 H
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Hydrogen.getGas(16000))
            .fluidOutputs(Materials.Hydrogen.getPlasma(16000))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * 20)
            .addTo(UH);
        // 氮等离子 N
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Nitrogen.getGas(16000))
            .fluidOutputs(Materials.Nitrogen.getPlasma(16000))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * 20)
            .addTo(UH);
        // 氧等离子 O
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Oxygen.getGas(16000))
            .fluidOutputs(Materials.Oxygen.getPlasma(16000))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 氟等离子 F
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Fluorine.getGas(16000))
            .fluidOutputs(Materials.Fluorine.getPlasma(16000))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
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
            .fluidOutputs(Materials.Sodium.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Mg
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensInf,
                Materials.Magnesium.getDust(64),
                Materials.Magnesium.getDust(64))
            .fluidOutputs(Materials.Magnesium.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Al
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensInf,
                Materials.Aluminium.getDust(64),
                Materials.Aluminium.getDust(64))
            .fluidOutputs(Materials.Aluminium.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Si
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensInf,
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64))
            .fluidOutputs(Materials.Silicon.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 P
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensInf,
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64))
            .fluidOutputs(Materials.Phosphorus.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 S
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensInf,
                Materials.Sulfur.getDust(64),
                Materials.Sulfur.getDust(64))
            .fluidOutputs(Materials.Sulfur.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Cl
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Chlorine.getGas(16000))
            .fluidOutputs(Materials.Chlorine.getPlasma(16000))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // endregion
        // region 第三周期+
        // 等离子 Ag
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Silver.getMolten(144 * 128))
            .fluidOutputs(Materials.Silver.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Ag
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Gold.getMolten(144 * 128))
            .fluidOutputs(Materials.Gold.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Fe
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Iron.getMolten(144 * 128))
            .fluidOutputs(Materials.Iron.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Ny
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Neodymium.getMolten(144 * 128))
            .fluidOutputs(Materials.Neodymium.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Pb
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Lead.getMolten(144 * 128))
            .fluidOutputs(Materials.Lead.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Nq
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Naquadah.getMolten(144 * 128))
            .fluidOutputs(Materials.Naquadah.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Nq
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Nickel.getMolten(144 * 128))
            .fluidOutputs(Materials.Nickel.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Rb
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Rubidium.getMolten(144 * 128))
            .fluidOutputs(Materials.Rubidium.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Sr
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensInf,
                Materials.Strontium.getDust(64),
                Materials.Strontium.getDust(64))
            .fluidOutputs(Materials.Strontium.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Sn
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Tin.getMolten(144 * 128))
            .fluidOutputs(Materials.Tin.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Ti
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Titanium.getMolten(144 * 128))
            .fluidOutputs(Materials.Titanium.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 W
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Tungsten.getMolten(144 * 128))
            .fluidOutputs(Materials.Tungsten.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Zn
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Zinc.getMolten(144 * 128))
            .fluidOutputs(Materials.Zinc.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Pt
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Platinum.getMolten(144 * 128))
            .fluidOutputs(Materials.Platinum.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(12 * 20)
            .addTo(UH);
        // 等离子 Os
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(12), lensInf)
            .fluidInputs(Materials.Osmium.getMolten(144 * 128))
            .fluidOutputs(Materials.Osmium.getPlasma(144 * 128))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(12 * 20)
            .addTo(UH);
        // endregion

    }
}
