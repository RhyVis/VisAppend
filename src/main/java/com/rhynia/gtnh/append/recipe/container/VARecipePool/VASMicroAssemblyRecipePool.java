package com.rhynia.gtnh.append.recipe.container.VARecipePool;

import static gregtech.api.enums.TierEU.RECIPE_LuV;
import static gregtech.api.enums.TierEU.RECIPE_UEV;
import static gregtech.api.enums.TierEU.RECIPE_UHV;
import static gregtech.api.enums.TierEU.RECIPE_UMV;
import static gregtech.api.enums.TierEU.RECIPE_UV;
import static gregtech.api.util.GT_RecipeBuilder.BUCKETS;
import static gregtech.api.util.GT_RecipeBuilder.INGOTS;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;

import com.dreammaster.gthandler.CustomItemList;
import com.dreammaster.gthandler.GT_CoreModSupport;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.common.machine.recipeMap.VA_RecipeAdder;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;
import com.rhynia.gtnh.append.util.recipeHelper.BWPart;
import com.rhynia.gtnh.append.util.recipeHelper.GGChip;
import com.rhynia.gtnh.append.util.recipeHelper.Solder;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.util.GT_Recipe;

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
                BWPart.getWrappedPart(BWPart.Part_NOR, 2 * Multiple),
                BWPart.getWrappedPart(BWPart.Part_RAM, 4 * Multiple))
            .fluidInputs(
                Solder.getSolder(3, 10 * INGOTS * Multiple),
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
                BWPart.getWrappedPart(BWPart.Part_RAM, 4 * Multiple))
            .fluidInputs(
                Solder.getSolder(3, 20 * INGOTS * Multiple),
                Materials.BioMediumSterilized.getFluid(20 * INGOTS * Multiple),
                Materials.SuperCoolant.getFluid(20 * BUCKETS * Multiple),
                Materials.Tritanium.getMolten(8 * INGOTS * Multiple),
                Materials.Silicone.getMolten(16 * INGOTS * Multiple),
                Materials.Polybenzimidazole.getMolten(16 * INGOTS * Multiple),
                VA_WerkstoffMaterialPool.superconductingFlux.getFluidOrGas(4 * INGOTS * Multiple))
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
                BWPart.getWrappedPart(BWPart.Part_NOR, 2 * Multiple),
                BWPart.getWrappedPart(BWPart.Part_RAM, 4 * Multiple))
            .fluidInputs(
                Solder.getSolder(3, 10 * INGOTS * Multiple),
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
                BWPart.getWrappedPart(BWPart.Part_NOR, 4 * Multiple),
                BWPart.getWrappedPart(BWPart.Part_ASOC, 2 * Multiple))
            .fluidInputs(
                Solder.getSolder(3, 20 * INGOTS * Multiple),
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
                BWPart.getWrappedPart(BWPart.Opt_Inductor, 2 * Multiple),
                BWPart.getWrappedPart(BWPart.Opt_Transistor, 2 * Multiple),
                BWPart.getWrappedPart(BWPart.Opt_Resistor, 2 * Multiple),
                BWPart.getWrappedPart(BWPart.Opt_Capacitor, 2 * Multiple),
                BWPart.getWrappedPart(BWPart.Opt_Diode, 2 * Multiple),
                BWPart.getWrappedPart(BWPart.Part_ASOC, 2 * Multiple))
            .fluidInputs(
                Solder.getSolder(3, 26 * INGOTS * Multiple),
                Materials.Radon.getPlasma(40 * INGOTS * Multiple),
                Materials.SuperCoolant.getFluid(40 * BUCKETS),
                WerkstoffLoader.Oganesson.getFluidOrGas(2 * BUCKETS * Multiple),
                Materials.Tritanium.getMolten(16 * INGOTS * Multiple),
                Materials.Silicone.getMolten(32 * INGOTS * Multiple),
                Materials.Polybenzimidazole.getMolten(32 * INGOTS * Multiple),
                VA_WerkstoffMaterialPool.superconductingFlux.getFluidOrGas(16 * INGOTS * Multiple))
            .itemOutputs(ItemList.Circuit_OpticalMainframe.get(Multiple))
            .eut(RECIPE_UEV)
            .duration(150 * SECONDS * Multiple)
            .addTo(MA);
        // endregion

        // region 量子-Piko电路 UXV-UMV
        // Quantum UXV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                CustomItemList.PikoCircuit.get(2 * Multiple),
                BWPart.getWrappedPart(BWPart.Opt_Capacitor, 4 * Multiple),
                BWPart.getWrappedPart(BWPart.Opt_Diode, 4 * Multiple),
                BWPart.getWrappedPart(BWPart.Opt_Transistor, 4 * Multiple),
                BWPart.getWrappedPart(BWPart.Opt_Resistor, 4 * Multiple),
                BWPart.getWrappedPart(BWPart.Part_IC_Q, 4 * Multiple))
            .fluidInputs(
                Solder.getSolder(3, 26 * INGOTS * Multiple),
                Materials.UUMatter.getFluid(24 * BUCKETS * Multiple),
                Materials.Osmium.getMolten(16 * INGOTS * Multiple),
                Materials.Neutronium.getMolten(8 * INGOTS * Multiple),
                MyMaterial.shirabon.getMolten(8 * INGOTS * Multiple),
                Materials.Indium.getMolten(8 * INGOTS * Multiple),
                MaterialsUEVplus.SpaceTime.getMolten(4 * INGOTS * Multiple),
                Materials.Lanthanum.getMolten(2 * INGOTS * Multiple))
            .itemOutputs(CustomItemList.QuantumCircuit.get(Multiple))
            .eut(RECIPE_UMV)
            .duration(800 * SECONDS * Multiple)
            .addTo(MA);
        // Piko UMV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Circuit_Board_Optical.get(Multiple),
                CustomItemList.PicoWafer.get(4 * Multiple),
                ItemList.Circuit_OpticalMainframe.get(2 * Multiple),
                BWPart.getWrappedPart(BWPart.Opt_Transistor, 3 * Multiple),
                BWPart.getWrappedPart(BWPart.Opt_Resistor, 3 * Multiple),
                BWPart.getWrappedPart(BWPart.Opt_Capacitor, 3 * Multiple),
                BWPart.getWrappedPart(BWPart.Opt_Diode, 3 * Multiple),
                BWPart.getWrappedPart(BWPart.Part_IC_P, 4 * Multiple))
            .fluidInputs(
                Solder.getSolder(3, 26 * INGOTS * Multiple),
                Materials.UUMatter.getFluid(8 * BUCKETS * Multiple),
                Materials.Osmium.getMolten(8 * INGOTS * Multiple),
                GT_CoreModSupport.RadoxPolymer.getMolten(4 * INGOTS * Multiple),
                MaterialsUEVplus.TranscendentMetal.getMolten(4 * INGOTS * Multiple),
                Materials.Neutronium.getMolten(2 * INGOTS * Multiple),
                Materials.Lanthanum.getMolten(8 * INGOTS * Multiple))
            .itemOutputs(CustomItemList.PikoCircuit.get(Multiple))
            .eut(RECIPE_UMV)
            .duration(400 * SECONDS * Multiple)
            .addTo(MA);
        // endregion

        // region High Energy Flow Circuit
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.getWrappedPart(BWPart.Elite_Board, 12),
                GGChip.getWrappedCircuit(GGChip.ZPM, 24),
                BWPart.getWrappedPart(BWPart.Part_IC_Q, 48))
            .fluidInputs(
                Solder.getSolder(2, 24 * INGOTS),
                VA_WerkstoffMaterialPool.superconductingFlux.getFluidOrGas(6 * INGOTS),
                Materials.Infinity.getMolten(4 * INGOTS))
            .itemOutputs(
                CustomItemList.HighEnergyFlowCircuit.get(64),
                CustomItemList.HighEnergyFlowCircuit.get(64),
                CustomItemList.HighEnergyFlowCircuit.get(64),
                CustomItemList.HighEnergyFlowCircuit.get(64))
            .eut(RECIPE_LuV)
            .duration(1600 * SECONDS)
            .addTo(MA);
        // endregion
    }
}
