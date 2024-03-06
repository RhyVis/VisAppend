package com.rhynia.gtnh.append.common.recipe.VARecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.BUCKETS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_EV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_IV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_LuV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UEV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UHV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UMV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UXV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_ZPM;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;
import static gregtech.api.enums.Mods.AppliedEnergistics2;
import static gregtech.api.enums.Mods.Computronics;
import static gregtech.api.enums.Mods.NewHorizonsCoreMod;
import static gregtech.api.enums.Mods.OpenComputers;
import static gregtech.api.enums.Mods.SuperSolarPanels;

import com.Nxer.TwistSpaceTechnology.common.GTCMItemList;
import com.dreammaster.gthandler.CustomItemList;
import com.dreammaster.gthandler.GT_CoreModSupport;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.api.enums.VA_Mods;
import com.rhynia.gtnh.append.api.enums.refHelper.BWPart;
import com.rhynia.gtnh.append.api.enums.refHelper.GGChip;
import com.rhynia.gtnh.append.api.enums.refHelper.SCPart;
import com.rhynia.gtnh.append.api.enums.refHelper.SolderMaterial;
import com.rhynia.gtnh.append.api.enums.refHelper.Tier;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.api.recipe.builder.VA_RecipeBuilder;
import com.rhynia.gtnh.append.api.util.FluidHelper;
import com.rhynia.gtnh.append.api.util.ItemHelper;
import com.rhynia.gtnh.append.common.loader.VA_ItemList;
import com.rhynia.gtnh.append.common.material.VA_Materials;
import com.rhynia.gtnh.append.config.Config;

import goodgenerator.items.MyMaterial;
import goodgenerator.util.ItemRefer;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.core.material.ALLOY;

@SuppressWarnings({ "SpellCheckingInspection" })
public class VASMicroAssemblyRecipePool implements IRecipePool {

    private final IRecipeMap MA = AppendRecipeMaps.microAssemblyRecipes;
    private final RecipeMap<?> MA_R = AppendRecipeMaps.microAssemblyRecipes;
    final boolean EnableTSTRecipes = true;
    int partOpticalMult = Config.Recipe_OPT_Mult;

    @Override
    public void loadRecipesCompleteInit() {
        if (EnableTSTRecipes && VA_Mods.TwistSpaceTechnology.isModLoaded()) {
            loadTSTRecipes();
        }

        loadMainRecipes();
        loadMachineAssmbly();
    }

    public void loadMainRecipes() {
        // region 光学元件
        // 二极管
        VA_RecipeBuilder.builder()
            .itemInputs(
                ItemHelper.setStackSize(
                    GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Tritanium, 1),
                    partOpticalMult * 32 * 4),
                ItemHelper.setStackSize(ALLOY.LAFIUM.getFoil(1), partOpticalMult * 32 * 2),
                ItemHelper.setStackSize(
                    GT_OreDictUnificator.get(OrePrefixes.foil, SCPart.ZPM.getMaterial(true), 1),
                    partOpticalMult * 32))
            .fluidInputs(
                FluidHelper.getFluidStackByName("xenoxene", partOpticalMult * 32 * INGOTS),
                SCPart.LuV.getMolten(partOpticalMult * 16 * INGOTS))
            .itemOutputs(ItemHelper.setStackSize(ItemList.Circuit_Parts_DiodeXSMD.get(1), partOpticalMult * 32 * 64))
            .eut(RECIPE_ZPM)
            .duration(partOpticalMult * 32 * 5 * SECONDS)
            .addTo(MA_R);
        // 电阻
        VA_RecipeBuilder.builder()
            .itemInputs(
                ItemHelper.setStackSize(
                    GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Naquadria, 1),
                    partOpticalMult * 32 * 4),
                ItemHelper.setStackSize(ALLOY.PIKYONIUM.getFoil(1), partOpticalMult * 32 * 2),
                ItemHelper.setStackSize(
                    GT_OreDictUnificator.get(OrePrefixes.foil, SCPart.ZPM.getMaterial(true), 1),
                    partOpticalMult * 32))
            .fluidInputs(
                FluidHelper.getFluidStackByName("xenoxene", partOpticalMult * 32 * INGOTS),
                SCPart.LuV.getMolten(partOpticalMult * 16 * INGOTS))
            .itemOutputs(ItemHelper.setStackSize(ItemList.Circuit_Parts_ResistorXSMD.get(1), partOpticalMult * 32 * 32))
            .eut(RECIPE_ZPM)
            .duration(partOpticalMult * 32 * 5 * SECONDS)
            .addTo(MA_R);
        // 晶体管
        VA_RecipeBuilder.builder()
            .itemInputs(
                ItemHelper.setStackSize(
                    GT_OreDictUnificator.get(OrePrefixes.foil, Materials.BlackPlutonium, 1),
                    partOpticalMult * 32 * 4),
                ItemHelper.setStackSize(ALLOY.TRINIUM_REINFORCED_STEEL.getFoil(1), partOpticalMult * 32 * 2),
                ItemHelper.setStackSize(
                    GT_OreDictUnificator.get(OrePrefixes.foil, SCPart.ZPM.getMaterial(true), 1),
                    partOpticalMult * 32))
            .fluidInputs(
                FluidHelper.getFluidStackByName("xenoxene", partOpticalMult * 32 * INGOTS),
                SCPart.LuV.getMolten(partOpticalMult * 16 * INGOTS))
            .itemOutputs(
                ItemHelper.setStackSize(ItemList.Circuit_Parts_TransistorXSMD.get(1), partOpticalMult * 32 * 32))
            .eut(RECIPE_ZPM)
            .duration(partOpticalMult * 32 * 5 * SECONDS)
            .addTo(MA_R);
        // 电容
        VA_RecipeBuilder.builder()
            .itemInputs(
                ItemHelper.setStackSize(
                    GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Draconium, 1),
                    partOpticalMult * 32 * 4),
                ItemHelper.setStackSize(ALLOY.CINOBITE.getFoil(1), partOpticalMult * 32 * 2),
                ItemHelper.setStackSize(
                    GT_OreDictUnificator.get(OrePrefixes.foil, SCPart.ZPM.getMaterial(true), 1),
                    partOpticalMult * 32))
            .fluidInputs(
                FluidHelper.getFluidStackByName("xenoxene", partOpticalMult * 32 * INGOTS),
                SCPart.LuV.getMolten(partOpticalMult * 16 * INGOTS))
            .itemOutputs(
                ItemHelper.setStackSize(ItemList.Circuit_Parts_CapacitorXSMD.get(1), partOpticalMult * 32 * 32))
            .eut(RECIPE_ZPM)
            .duration(partOpticalMult * 32 * 5 * SECONDS)
            .addTo(MA_R);
        // 电感
        VA_RecipeBuilder.builder()
            .itemInputs(
                ItemHelper.setStackSize(MyMaterial.hikarium.get(OrePrefixes.foil, 1), partOpticalMult * 32 * 4),
                ItemHelper.setStackSize(MyMaterial.artheriumSn.get(OrePrefixes.foil, 1), partOpticalMult * 32))
            .fluidInputs(
                FluidHelper.getFluidStackByName("xenoxene", partOpticalMult * 32 * INGOTS),
                SCPart.LuV.getMolten(partOpticalMult * 16 * INGOTS))
            .itemOutputs(ItemHelper.setStackSize(ItemList.Circuit_Parts_InductorXSMD.get(1), partOpticalMult * 32 * 32))
            .eut(RECIPE_ZPM)
            .duration(partOpticalMult * 32 * 5 * SECONDS)
            .addTo(MA_R);
        // endregion

        // region MISC
        GT_Values.RA.stdBuilder()
            .itemInputs(
                Materials.Plastic.getPlates(32),
                Materials.Obsidian.getPlates(64),
                BWPart.Adv_Board.getItemStack(4),
                Tier.HV.getCircuit(4),
                GT_ModHandler.getModItem(SuperSolarPanels.ID, "redcomponent", 4),
                GT_ModHandler.getModItem(SuperSolarPanels.ID, "greencomponent", 4),
                GT_ModHandler.getModItem(SuperSolarPanels.ID, "bluecomponent", 4))
            .itemOutputs(GT_Utility.copyAmountUnsafe(256, GT_ModHandler.getModItem(OpenComputers.ID, "hologram2", 1)))
            .eut(RECIPE_IV)
            .duration(16 * SECONDS)
            .addTo(MA);
        // endregion

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
                VA_Materials.SuperconductorFlux.getFluidOrGas(4 * INGOTS * multiple))
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
                VA_Materials.SuperconductorFlux.getFluidOrGas(16 * INGOTS * multiple))
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
                VA_Materials.SuperconductorFlux.getFluidOrGas(6 * INGOTS),
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
        // T5
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemRefer.HiC_T4.get(8),
                GT_ModHandler.getModItem("dreamcraft", "item.EngravedManyullynCrystalChip", 32),
                ItemList.Circuit_Chip_BioCPU.get(1))
            .fluidInputs(
                MyMaterial.titaniumBetaC.getMolten(4 * 1728),
                MyMaterial.signalium.getMolten(4 * 1152),
                MyMaterial.lumiium.getMolten(4 * 576),
                MyMaterial.dalisenite.getMolten(4 * 288),
                Materials.TungstenCarbide.getMolten(4 * 8 * INGOTS))
            .itemOutputs(ItemRefer.HiC_T5.get(4))
            .eut(RECIPE_UHV)
            .duration(3 * 5 * SECONDS)
            .addTo(MA);
        // endregion

        // region OC
        // Magic RAM
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Optically_Compatible_Memory.get(6),
                Tier.UEV.getCircuit(6),
                GT_ModHandler.getModItem(OpenComputers.ID, "item", 2, 103),
                ItemList.Circuit_Chip_PPIC.get(8),
                Materials.Neutronium.getPlates(24))
            .fluidInputs(Materials.ElectrumFlux.getMolten(12 * INGOTS), Materials.Infinity.getMolten(2 * INGOTS))
            .itemOutputs(GT_ModHandler.getModItem(Computronics.ID, "computronics.ocSpecialParts", 1, 0))
            .eut(RECIPE_UEV)
            .duration(4 * SECONDS)
            .addTo(MA);
        // APU T3
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem(OpenComputers.ID, "item", 1, 102),
                GT_ModHandler.getModItem(OpenComputers.ID, "item", 1, 43),
                GT_ModHandler.getModItem(OpenComputers.ID, "item", 1, 10),
                Tier.UV.getCircuit(2),
                Tier.LuV.getCircuit(4),
                Tier.IV.getCircuit(16))
            .itemOutputs(GT_ModHandler.getModItem(OpenComputers.ID, "item", 1, 103))
            .eut(RECIPE_UV)
            .duration(4 * SECONDS)
            .addTo(MA);
        // Super Server
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem(OpenComputers.ID, "case3", 1, 0),
                GT_ModHandler.getModItem(OpenComputers.ID, "item", 2, 103),
                Tier.UV.getCircuit(2),
                Tier.LuV.getCircuit(16),
                Tier.IV.getCircuit(4))
            .itemOutputs(GT_ModHandler.getModItem(OpenComputers.ID, "item", 1, 69))
            .eut(RECIPE_UV)
            .duration(4 * SECONDS)
            .addTo(MA);
        // endregion

        // region AE
        // Spacial 1
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem(NewHorizonsCoreMod.ID, "item.EngineeringProcessorSpatialPulsatingCore", 16),
                GT_ModHandler.getModItem(NewHorizonsCoreMod.ID, "item.ChargedCertusQuartzPlate", 64),
                Materials.Redstone.getPlates(64),
                Materials.NetherQuartz.getPlates(64))
            .fluidInputs(
                SolderMaterial.SolderingAlloy.getFluidStack(4 * INGOTS),
                Materials.Glowstone.getMolten(64 * INGOTS))
            .itemOutputs(GT_ModHandler.getModItem(AppliedEnergistics2.ID, "item.ItemMultiMaterial", 16, 32))
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(4 * SECONDS)
            .addTo(MA);
        // Spacial 2
        VA_RecipeBuilder.builder()
            .itemInputs(
                GT_ModHandler.getModItem(NewHorizonsCoreMod.ID, "item.EngineeringProcessorSpatialPulsatingCore", 64),
                ItemHelper.setStackSize(
                    GT_ModHandler.getModItem(NewHorizonsCoreMod.ID, "item.ChargedCertusQuartzPlate", 64),
                    4 * 64),
                ItemHelper.setStackSize(Materials.Redstone.getPlates(64), 4 * 64),
                ItemHelper.setStackSize(Materials.NetherQuartz.getPlates(64), 4 * 64))
            .fluidInputs(
                SolderMaterial.IndaAlloy.getFluidStack(4 * INGOTS),
                Materials.EnderPearl.getMolten(64 * INGOTS))
            .itemOutputs(GT_ModHandler.getModItem(AppliedEnergistics2.ID, "item.ItemMultiMaterial", 4, 33))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(4 * SECONDS)
            .addTo(MA_R);
        // Spacial 3
        VA_RecipeBuilder.builder()
            .itemInputs(
                ItemHelper.setStackSize(
                    GT_ModHandler
                        .getModItem(NewHorizonsCoreMod.ID, "item.EngineeringProcessorSpatialPulsatingCore", 64),
                    256),
                ItemHelper.setStackSize(
                    GT_ModHandler.getModItem(NewHorizonsCoreMod.ID, "item.ChargedCertusQuartzPlate", 64),
                    16 * 64),
                ItemHelper.setStackSize(Materials.Redstone.getPlates(64), 16 * 64),
                ItemHelper.setStackSize(Materials.NetherQuartz.getPlates(64), 16 * 64))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(4 * INGOTS),
                Materials.EnderEye.getMolten(64 * INGOTS))
            .itemOutputs(GT_ModHandler.getModItem(AppliedEnergistics2.ID, "item.ItemMultiMaterial", 1, 34))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(4 * SECONDS)
            .addTo(MA_R);
        // endregion

        // region EOHC
        // EOHC
        for (int i = 1; i < 10; i++) {
            GT_Values.RA.stdBuilder()
                .itemInputs(
                    Tier.UXV.getCircuitWrap(4),
                    GT_Utility.copyAmountUnsafe(
                        138,
                        com.github.technus.tectech.thing.CustomItemList
                            .valueOf("SpacetimeCompressionFieldGeneratorTier" + (i - 1))
                            .get(1)),
                    GT_Utility.copyAmountUnsafe(
                        168,
                        com.github.technus.tectech.thing.CustomItemList
                            .valueOf("TimeAccelerationFieldGeneratorTier" + (i - 1))
                            .get(1)),
                    GT_Utility.copyAmountUnsafe(
                        48,
                        com.github.technus.tectech.thing.CustomItemList
                            .valueOf("StabilisationFieldGeneratorTier" + (i - 1))
                            .get(1)))
                .fluidInputs(
                    SolderMaterial.MutatedLivingAlloy.getFluidStack(128 * i * INGOTS),
                    MaterialsUEVplus.SpaceTime.getMolten(96 * i * INGOTS))
                .itemOutputs(
                    VA_ItemList.valueOf("EOH_Core_T" + i)
                        .get(1))
                .noOptimize()
                .eut(RECIPE_UXV)
                .duration(16 * i * SECONDS)
                .addTo(MA);
        }
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
                VA_Materials.Astrium.getMolten(32 * INGOTS))
            .itemOutputs(GTCMItemList.OpticalSOC.get(64))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(512 * SECONDS)
            .addTo(MA);
    }
    // endregion

    // region Machine Assembly
    private void loadMachineAssmbly() {
        // Calibration
        GT_ModHandler.addShapelessCraftingRecipe(
            VA_ItemList.Calibration.get(1),
            new Object[] { Tier.UV.getCircuit(1), Tier.UHV.getCircuit(1), Tier.UEV.getCircuit(1) });
        // DTPF
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Machine_Multi_PlasmaForge.get(1),
                GT_Utility.copyAmountUnsafe(2121, ItemList.Casing_Dim_Trans.get(1)),
                GT_Utility.copyAmountUnsafe(1273, ItemList.Casing_Dim_Injector.get(1)),
                GT_Utility.copyAmountUnsafe(120, ItemList.Casing_Dim_Bridge.get(1)),
                GT_Utility.copyAmountUnsafe(2112, ItemList.Casing_Coil_Eternal.get(1)))
            .fluidInputs(SolderMaterial.MutatedLivingAlloy.getFluidStack(64 * INGOTS))
            .itemOutputs(VA_ItemList.Assembly_DTPF.get(1))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(16 * SECONDS)
            .addTo(MA);
    }
    // endregion
}
