package com.rhynia.gtnh.append.recipe.container.VARecipePool;

import static gregtech.api.enums.TierEU.RECIPE_UEV;
import static gregtech.api.enums.TierEU.RECIPE_UHV;
import static gregtech.api.enums.TierEU.RECIPE_UV;
import static gregtech.api.util.GT_RecipeBuilder.BUCKETS;
import static gregtech.api.util.GT_RecipeBuilder.INGOTS;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;

import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.common.machine.recipeMap.VA_RecipeAdder;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;
import com.rhynia.gtnh.append.util.BWChip;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_Recipe;
import gtPlusPlus.core.material.MISC_MATERIALS;

public class VASMicroAssemblyRecipePool implements IRecipePool {

    final GT_Recipe.GT_Recipe_Map MA = VA_RecipeAdder.instance.sMicroAssemblyRecipes;
    final int Multiple = 1;

    @Override
    public void loadRecipesPostInit() {}

    @Override
    public void loadRecipes() {
        // region 生物系
        // 生物超级电脑 UHV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Circuit_Board_Bio_Ultra.get(2 * Multiple),
                ItemList.Circuit_Biowarecomputer.get(2 * Multiple),
                ItemList.Circuit_Parts_TransistorXSMD.get(4 * Multiple),
                ItemList.Circuit_Parts_ResistorXSMD.get(4 * Multiple),
                ItemList.Circuit_Parts_CapacitorXSMD.get(4 * Multiple),
                ItemList.Circuit_Parts_DiodeXSMD.get(4 * Multiple),
                BWChip.getWrapCircuit(BWChip.Part_NOR, 2 * Multiple),
                BWChip.getWrapCircuit(BWChip.Part_RAM, 4 * Multiple))
            .fluidInputs(
                MISC_MATERIALS.MUTATED_LIVING_SOLDER.getFluidStack(10 * INGOTS * Multiple),
                Materials.BioMediumSterilized.getFluid(10 * INGOTS * Multiple),
                Materials.SuperCoolant.getFluid(10 * BUCKETS * Multiple),
                Materials.NiobiumTitanium.getMolten(4 * INGOTS * Multiple),
                Materials.Silicone.getMolten(16 * INGOTS * Multiple))
            .itemOutputs(ItemList.Circuit_Biowaresupercomputer.get(Multiple))
            .eut(RECIPE_UV)
            .duration(100 * SECONDS * Multiple)
            .addTo(MA);
        // 生物主机 UEV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Circuit_Biowaresupercomputer.get(2 * Multiple),
                ItemList.Circuit_Parts_InductorXSMD.get(6 * Multiple),
                ItemList.Circuit_Parts_TransistorXSMD.get(6 * Multiple),
                ItemList.Circuit_Parts_ResistorXSMD.get(6 * Multiple),
                ItemList.Circuit_Parts_CapacitorXSMD.get(6 * Multiple),
                ItemList.Circuit_Parts_DiodeXSMD.get(6 * Multiple),
                BWChip.getWrapCircuit(BWChip.Part_RAM, 4 * Multiple))
            .fluidInputs(
                MISC_MATERIALS.MUTATED_LIVING_SOLDER.getFluidStack(20 * INGOTS * Multiple),
                Materials.BioMediumSterilized.getFluid(20 * INGOTS * Multiple),
                Materials.SuperCoolant.getFluid(20 * BUCKETS * Multiple),
                Materials.Tritanium.getMolten(8 * INGOTS * Multiple),
                Materials.Silicone.getMolten(16 * INGOTS * Multiple),
                Materials.Polybenzimidazole.getMolten(16 * INGOTS * Multiple),
                VA_WerkstoffMaterialPool.superconductingFluid.getMolten(16 * INGOTS * Multiple))
            .itemOutputs(ItemList.Circuit_Biomainframe.get(Multiple))
            .eut(RECIPE_UHV)
            .duration(150 * SECONDS * Multiple)
            .addTo(MA);
        // endregion
        // region 光学系
        // 光学集群 UHV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Circuit_Board_Optical.get(Multiple),
                ItemList.Circuit_OpticalProcessor.get(2 * Multiple),
                ItemList.Circuit_Parts_InductorXSMD.get(16 * Multiple),
                ItemList.Circuit_Parts_CapacitorXSMD.get(20 * Multiple),
                ItemList.Circuit_Parts_ResistorXSMD.get(20 * Multiple),
                BWChip.getWrapCircuit(BWChip.Part_NOR, 2 * Multiple),
                BWChip.getWrapCircuit(BWChip.Part_RAM, 4 * Multiple))
            .fluidInputs(
                MISC_MATERIALS.MUTATED_LIVING_SOLDER.getFluidStack(10 * INGOTS * Multiple),
                Materials.Radon.getPlasma(10 * INGOTS * Multiple),
                Materials.SuperCoolant.getFluid(10 * BUCKETS * Multiple),
                WerkstoffLoader.Oganesson.getFluidOrGas(500 * Multiple),
                MyMaterial.lumiium.getMolten(3 * INGOTS * Multiple),
                Materials.Silicone.getMolten(16 * INGOTS * Multiple))
            .itemOutputs(ItemList.Circuit_OpticalAssembly.get(Multiple))
            .eut(RECIPE_UHV)
            .duration(10 * SECONDS * Multiple)
            .addTo(MA);
        // 光学超级电脑 UEV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Circuit_Board_Optical.get(2 * Multiple),
                ItemList.Circuit_OpticalAssembly.get(2 * Multiple),
                ItemList.Circuit_Parts_TransistorXSMD.get(24 * Multiple),
                ItemList.Circuit_Parts_ResistorXSMD.get(24 * Multiple),
                ItemList.Circuit_Parts_CapacitorXSMD.get(24 * Multiple),
                ItemList.Circuit_Parts_DiodeXSMD.get(24 * Multiple),
                BWChip.getWrapCircuit(BWChip.Part_NOR, 4 * Multiple),
                BWChip.getWrapCircuit(BWChip.Part_ASOC, 2 * Multiple))
            .fluidInputs(
                MISC_MATERIALS.MUTATED_LIVING_SOLDER.getFluidStack(20 * INGOTS * Multiple),
                Materials.Radon.getPlasma(20 * INGOTS * Multiple),
                Materials.SuperCoolant.getFluid(20 * BUCKETS * Multiple),
                WerkstoffLoader.Oganesson.getFluidOrGas(BUCKETS * Multiple),
                MyMaterial.lumiium.getMolten(4 * INGOTS * Multiple),
                Materials.Silicone.getMolten(16 * INGOTS * Multiple),
                Materials.Polybenzimidazole.getMolten(16 * INGOTS * Multiple))
            .itemOutputs(ItemList.Circuit_OpticalComputer.get(Multiple))
            .eut(RECIPE_UHV)
            .duration(100 * SECONDS * Multiple)
            .addTo(MA);
        // 光学主机 UIV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Circuit_OpticalComputer.get(2 * Multiple),
                BWChip.getWrapCircuit(BWChip.Opt_Inductor, 2 * Multiple),
                BWChip.getWrapCircuit(BWChip.Opt_Transistor, 2 * Multiple),
                BWChip.getWrapCircuit(BWChip.Opt_Resistor, 2 * Multiple),
                BWChip.getWrapCircuit(BWChip.Opt_Capacitor, 2 * Multiple),
                BWChip.getWrapCircuit(BWChip.Opt_Diode, 2 * Multiple),
                BWChip.getWrapCircuit(BWChip.Part_ASOC, 2 * Multiple))
            .fluidInputs(
                MISC_MATERIALS.MUTATED_LIVING_SOLDER.getFluidStack(26 * INGOTS * Multiple),
                Materials.Radon.getPlasma(40 * INGOTS * Multiple),
                Materials.SuperCoolant.getFluid(40 * BUCKETS),
                WerkstoffLoader.Oganesson.getFluidOrGas(2 * BUCKETS * Multiple),
                Materials.Tritanium.getMolten(16 * INGOTS * Multiple),
                Materials.Silicone.getMolten(32 * INGOTS * Multiple),
                Materials.Polybenzimidazole.getMolten(32 * INGOTS * Multiple),
                VA_WerkstoffMaterialPool.superconductingFluid.getMolten(64 * INGOTS * Multiple))
            .itemOutputs(ItemList.Circuit_OpticalMainframe.get(Multiple))
            .eut(RECIPE_UEV)
            .duration(150 * SECONDS * Multiple)
            .addTo(MA);
        // endregion
    }
}
