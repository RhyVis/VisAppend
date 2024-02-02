package com.rhynia.gtnh.append.common.recipe.VARecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.BUCKETS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_IV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_LuV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UEV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UHV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UMV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_ZPM;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import com.Nxer.TwistSpaceTechnology.common.GTCMItemList;
import com.dreammaster.gthandler.CustomItemList;
import com.dreammaster.gthandler.GT_CoreModSupport;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.api.enums.VA_Mods;
import com.rhynia.gtnh.append.api.enums.refHelper.BWPart;
import com.rhynia.gtnh.append.api.enums.refHelper.GGChip;
import com.rhynia.gtnh.append.api.enums.refHelper.SolderMaterial;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.common.material.VAMaterials;

import goodgenerator.items.MyMaterial;
import goodgenerator.util.ItemRefer;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;

@SuppressWarnings({ "SpellCheckingInspection" })
public class VASMicroAssemblyRecipePool implements IRecipePool {

    private final IRecipeMap MA = AppendRecipeMaps.microAssemblyRecipes;
    final boolean EnableTSTRecipes = true;

    @Override
    public void loadRecipesPostInit() {}

    @Override
    public void loadRecipesCompleteInit() {
        if (EnableTSTRecipes && VA_Mods.TwistSpaceTechnology.isModLoaded()) {
            loadTSTRecipes();
        }

        loadMainRecipes();
    }

    public void loadMainRecipes() {
        int multiple = 1;
        // region 生物系
        // 生物超级电脑 UHV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Circuit_Board_Bio_Ultra.get(2 * multiple),
                ItemList.Circuit_Biowarecomputer.get(2 * multiple),
                ItemList.Circuit_Parts_TransistorXSMD.get(4 * multiple),
                ItemList.Circuit_Parts_ResistorXSMD.get(4 * multiple),
                ItemList.Circuit_Parts_CapacitorXSMD.get(4 * multiple),
                ItemList.Circuit_Parts_DiodeXSMD.get(4 * multiple),
                BWPart.Part_NOR.getItemStack(2 * multiple),
                BWPart.Part_RAM.getItemStack(4 * multiple))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(10 * INGOTS * multiple),
                Materials.BioMediumSterilized.getFluid(10 * INGOTS * multiple),
                Materials.SuperCoolant.getFluid(10 * BUCKETS * multiple),
                Materials.NiobiumTitanium.getMolten(4 * INGOTS * multiple),
                Materials.Silicone.getMolten(16 * INGOTS * multiple))
            .itemOutputs(ItemList.Circuit_Biowaresupercomputer.get(multiple))
            .eut(RECIPE_UV)
            .duration(100 * SECONDS * multiple)
            .addTo(MA);
        // 生物主机 UEV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Circuit_Biowaresupercomputer.get(2 * multiple),
                ItemList.Circuit_Parts_InductorXSMD.get(6 * multiple),
                ItemList.Circuit_Parts_TransistorXSMD.get(6 * multiple),
                ItemList.Circuit_Parts_ResistorXSMD.get(6 * multiple),
                ItemList.Circuit_Parts_CapacitorXSMD.get(6 * multiple),
                ItemList.Circuit_Parts_DiodeXSMD.get(6 * multiple),
                BWPart.Part_RAM.getItemStack(4 * multiple))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(20 * INGOTS * multiple),
                Materials.BioMediumSterilized.getFluid(20 * INGOTS * multiple),
                Materials.SuperCoolant.getFluid(20 * BUCKETS * multiple),
                Materials.Tritanium.getMolten(8 * INGOTS * multiple),
                Materials.Silicone.getMolten(16 * INGOTS * multiple),
                Materials.Polybenzimidazole.getMolten(16 * INGOTS * multiple),
                VAMaterials.SuperconductorFlux.getFluidOrGas(4 * INGOTS * multiple))
            .itemOutputs(ItemList.Circuit_Biomainframe.get(multiple))
            .eut(RECIPE_UHV)
            .duration(150 * SECONDS * multiple)
            .addTo(MA);
        // endregion

        // region 光学系
        // 光学集群 UHV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Circuit_Board_Optical.get(multiple),
                ItemList.Circuit_OpticalProcessor.get(2 * multiple),
                ItemList.Circuit_Parts_InductorXSMD.get(16 * multiple),
                ItemList.Circuit_Parts_CapacitorXSMD.get(20 * multiple),
                ItemList.Circuit_Parts_ResistorXSMD.get(20 * multiple),
                BWPart.Part_NOR.getItemStack(2 * multiple),
                BWPart.Part_RAM.getItemStack(4 * multiple))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(10 * INGOTS * multiple),
                Materials.Radon.getPlasma(10 * INGOTS * multiple),
                Materials.SuperCoolant.getFluid(10 * BUCKETS * multiple),
                WerkstoffLoader.Oganesson.getFluidOrGas(500 * multiple),
                MyMaterial.lumiium.getMolten(3 * INGOTS * multiple),
                Materials.Silicone.getMolten(16 * INGOTS * multiple))
            .itemOutputs(ItemList.Circuit_OpticalAssembly.get(multiple))
            .eut(RECIPE_UHV)
            .duration(10 * SECONDS * multiple)
            .addTo(MA);
        // 光学超级电脑 UEV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Circuit_Board_Optical.get(2 * multiple),
                ItemList.Circuit_OpticalAssembly.get(2 * multiple),
                ItemList.Circuit_Parts_TransistorXSMD.get(24 * multiple),
                ItemList.Circuit_Parts_ResistorXSMD.get(24 * multiple),
                ItemList.Circuit_Parts_CapacitorXSMD.get(24 * multiple),
                ItemList.Circuit_Parts_DiodeXSMD.get(24 * multiple),
                BWPart.Part_NOR.getItemStack(4 * multiple),
                BWPart.Part_ASOC.getItemStack(2 * multiple))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(20 * INGOTS * multiple),
                Materials.Radon.getPlasma(20 * INGOTS * multiple),
                Materials.SuperCoolant.getFluid(20 * BUCKETS * multiple),
                WerkstoffLoader.Oganesson.getFluidOrGas(BUCKETS * multiple),
                MyMaterial.lumiium.getMolten(4 * INGOTS * multiple),
                Materials.Silicone.getMolten(16 * INGOTS * multiple),
                Materials.Polybenzimidazole.getMolten(16 * INGOTS * multiple))
            .itemOutputs(ItemList.Circuit_OpticalComputer.get(multiple))
            .eut(RECIPE_UHV)
            .duration(100 * SECONDS * multiple)
            .addTo(MA);
        // 光学主机 UIV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Circuit_OpticalComputer.get(2 * multiple),
                BWPart.Opt_Inductor.getItemStack(2 * multiple),
                BWPart.Opt_Transistor.getItemStack(2 * multiple),
                BWPart.Opt_Resistor.getItemStack(2 * multiple),
                BWPart.Opt_Capacitor.getItemStack(2 * multiple),
                BWPart.Opt_Diode.getItemStack(2 * multiple),
                BWPart.Part_ASOC.getItemStack(2 * multiple))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(26 * INGOTS * multiple),
                Materials.Radon.getPlasma(40 * INGOTS * multiple),
                Materials.SuperCoolant.getFluid(40 * BUCKETS),
                WerkstoffLoader.Oganesson.getFluidOrGas(2 * BUCKETS * multiple),
                Materials.Tritanium.getMolten(16 * INGOTS * multiple),
                Materials.Silicone.getMolten(32 * INGOTS * multiple),
                Materials.Polybenzimidazole.getMolten(32 * INGOTS * multiple),
                VAMaterials.SuperconductorFlux.getFluidOrGas(16 * INGOTS * multiple))
            .itemOutputs(ItemList.Circuit_OpticalMainframe.get(multiple))
            .eut(RECIPE_UEV)
            .duration(150 * SECONDS * multiple)
            .addTo(MA);
        // endregion

        // region 量子-Piko电路 UXV-UMV
        // Quantum UXV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                CustomItemList.PikoCircuit.get(2 * multiple),
                BWPart.Opt_Capacitor.getItemStack(4 * multiple),
                BWPart.Opt_Diode.getItemStack(4 * multiple),
                BWPart.Opt_Transistor.getItemStack(4 * multiple),
                BWPart.Opt_Resistor.getItemStack(4 * multiple),
                BWPart.Part_IC_Q.getItemStack(4 * multiple))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(26 * INGOTS * multiple),
                Materials.UUMatter.getFluid(24 * BUCKETS * multiple),
                Materials.Osmium.getMolten(16 * INGOTS * multiple),
                Materials.Neutronium.getMolten(8 * INGOTS * multiple),
                MyMaterial.shirabon.getMolten(8 * INGOTS * multiple),
                Materials.Indium.getMolten(8 * INGOTS * multiple),
                MaterialsUEVplus.SpaceTime.getMolten(4 * INGOTS * multiple),
                Materials.Lanthanum.getMolten(2 * INGOTS * multiple))
            .itemOutputs(CustomItemList.QuantumCircuit.get(multiple))
            .eut(RECIPE_UMV)
            .duration(800 * SECONDS * multiple)
            .addTo(MA);
        // Piko UMV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Circuit_Board_Optical.get(multiple),
                CustomItemList.PicoWafer.get(4 * multiple),
                ItemList.Circuit_OpticalMainframe.get(2 * multiple),
                BWPart.Opt_Transistor.getItemStack(3 * multiple),
                BWPart.Opt_Resistor.getItemStack(3 * multiple),
                BWPart.Opt_Capacitor.getItemStack(3 * multiple),
                BWPart.Opt_Diode.getItemStack(3 * multiple),
                BWPart.Part_IC_P.getItemStack(4 * multiple))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(26 * INGOTS * multiple),
                Materials.UUMatter.getFluid(8 * BUCKETS * multiple),
                Materials.Osmium.getMolten(8 * INGOTS * multiple),
                GT_CoreModSupport.RadoxPolymer.getMolten(4 * INGOTS * multiple),
                MaterialsUEVplus.TranscendentMetal.getMolten(4 * INGOTS * multiple),
                Materials.Neutronium.getMolten(2 * INGOTS * multiple),
                Materials.Lanthanum.getMolten(8 * INGOTS * multiple))
            .itemOutputs(CustomItemList.PikoCircuit.get(multiple))
            .eut(RECIPE_UMV)
            .duration(400 * SECONDS * multiple)
            .addTo(MA);
        // endregion

        // region High Energy Flow Circuit
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.Elite_Board.getItemStack(12),
                GGChip.ZPM.getItemStack(24),
                BWPart.Part_IC_Q.getItemStack(48))
            .fluidInputs(
                SolderMaterial.IndaAlloy.getFluidStack(24 * INGOTS),
                VAMaterials.SuperconductorFlux.getFluidOrGas(6 * INGOTS),
                Materials.Infinity.getMolten(4 * INGOTS))
            .itemOutputs(GT_Utility.copyAmountUnsafe(256, CustomItemList.HighEnergyFlowCircuit.get(1)))
            .eut(RECIPE_LuV)
            .duration(1600 * SECONDS)
            .addTo(MA);
        // endregion

        // region 高算力工作站
        // T1
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.Elite_Board.getItemStack(1),
                GT_Utility
                    .copyAmountUnsafe(16 * 16, GT_ModHandler.getModItem("dreamcraft", "item.EngravedGoldChip", 1)),
                BWPart.Part_ASOC.getItemStack(8),
                BWPart.Part_NOR.getItemStack(32))
            .fluidInputs(
                SolderMaterial.SolderingAlloy.getFluidStack(16 * 2 * INGOTS),
                MyMaterial.signalium.getMolten(16 * 4 * INGOTS),
                Materials.Aluminium.getMolten(16 * 4 * INGOTS),
                Materials.TinAlloy.getMolten(16 * 4 * INGOTS))
            .itemOutputs(ItemRefer.HiC_T1.get(16))
            .eut(RECIPE_IV)
            .duration(12 * 60 * SECONDS)
            .addTo(MA);
        // T2
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemRefer.HiC_T1.get(32),
                GT_Utility.copyAmountUnsafe(16 * 8, CustomItemList.EngravedDiamondCrystalChip.get(1)),
                BWPart.Part_NAND.getItemStack(16))
            .fluidInputs(
                Materials.Plastic.getMolten(16 * 2 * INGOTS),
                MyMaterial.signalium.getMolten(16 * INGOTS),
                MyMaterial.lumiium.getMolten(16 * 72),
                Materials.Enderium.getMolten(16 * 72),
                Materials.Aluminium.getMolten(16 * 8 * INGOTS))
            .itemOutputs(ItemRefer.HiC_T2.get(16))
            .eut(RECIPE_LuV)
            .duration(12 * 5 * SECONDS)
            .addTo(MA);
        // T3
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemRefer.HiC_T2.get(32),
                BWPart.Lapotron.getItemStack(8),
                BWPart.AdvCrystal_Raw.getItemStack(1))
            .fluidInputs(
                MyMaterial.adamantiumAlloy.getMolten(16 * 4 * INGOTS),
                MyMaterial.signalium.getMolten(16 * 2 * INGOTS),
                MyMaterial.lumiium.getMolten(16 * INGOTS),
                Materials.TungstenCarbide.getMolten(16 * 72),
                Materials.StainlessSteel.getMolten(16 * 8 * INGOTS))
            .itemOutputs(ItemRefer.HiC_T3.get(16))
            .eut(RECIPE_ZPM)
            .duration(12 * 5 * SECONDS)
            .addTo(MA);
        // T4
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemRefer.HiC_T3.get(8),
                CustomItemList.EngravedEnergyChip.get(32),
                BWPart.Part_QBit.getItemStack(4))
            .fluidInputs(
                MyMaterial.marM200.getMolten(4 * 8 * INGOTS),
                MyMaterial.signalium.getMolten(4 * 4 * INGOTS),
                MyMaterial.lumiium.getMolten(4 * 2 * INGOTS),
                MyMaterial.artheriumSn.getMolten(4 * INGOTS),
                Materials.EnergeticAlloy.getMolten(4 * 8 * INGOTS))
            .itemOutputs(ItemRefer.HiC_T4.get(4))
            .eut(RECIPE_UV)
            .duration(3 * 5 * SECONDS)
            .addTo(MA);
        // endregion
    }

    // region TST
    public void loadTSTRecipes() {
        // Optical SOC
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.Opt_CPU.getItemStack(16),
                BWPart.Opt_Ram.getItemStack(32),
                BWPart.Opt_Capacitor.getItemStack(64),
                BWPart.Opt_Diode.getItemStack(64),
                MyMaterial.orundum.get(OrePrefixes.dust, 64),
                MyMaterial.orundum.get(OrePrefixes.dust, 64))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(48 * INGOTS),
                Materials.Sunnarium.getMolten(32 * INGOTS),
                VAMaterials.Astrium.getMolten(32 * INGOTS))
            .itemOutputs(GTCMItemList.OpticalSOC.get(64))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(512 * SECONDS)
            .addTo(MA);
    }
    // endregion
}