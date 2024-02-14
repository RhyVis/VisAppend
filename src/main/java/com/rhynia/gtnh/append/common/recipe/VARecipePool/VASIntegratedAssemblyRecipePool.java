package com.rhynia.gtnh.append.common.recipe.VARecipePool;

import static com.github.technus.tectech.loader.recipe.BaseRecipeLoader.getItemContainer;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.BUCKETS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.HOURS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_EV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_IV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_LuV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_MAX;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UEV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UHV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UIV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UMV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UXV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_ZPM;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.TICKS;
import static gregtech.api.enums.Mods.AE2FluidCraft;
import static gregtech.api.enums.Mods.AdvancedSolarPanel;
import static gregtech.api.enums.Mods.AppliedEnergistics2;
import static gregtech.api.enums.Mods.Avaritia;
import static gregtech.api.enums.Mods.AvaritiaAddons;
import static gregtech.api.enums.Mods.EternalSingularity;
import static gregtech.api.enums.Mods.GTPlusPlus;
import static gregtech.api.enums.Mods.GraviSuite;
import static gregtech.api.enums.Mods.SuperSolarPanels;
import static gregtech.api.enums.Mods.TinkerConstruct;

import net.minecraft.item.ItemStack;

import com.Nxer.TwistSpaceTechnology.common.GTCMItemList;
import com.dreammaster.gthandler.CustomItemList;
import com.rhynia.gtnh.append.api.enums.VA_Mods;
import com.rhynia.gtnh.append.api.enums.refHelper.BWPart;
import com.rhynia.gtnh.append.api.enums.refHelper.Basic;
import com.rhynia.gtnh.append.api.enums.refHelper.GGChip;
import com.rhynia.gtnh.append.api.enums.refHelper.SCPart;
import com.rhynia.gtnh.append.api.enums.refHelper.SolderMaterial;
import com.rhynia.gtnh.append.api.enums.refHelper.Tier;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.api.recipe.builder.VA_RecipeBuilder;
import com.rhynia.gtnh.append.api.util.FluidHelper;
import com.rhynia.gtnh.append.api.util.ItemHelper;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.material.VAMaterials;

import galaxyspace.core.register.GSItems;
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
import gtPlusPlus.core.material.ELEMENT;
import gtPlusPlus.xmod.gregtech.api.enums.GregtechItemList;
import vexatos.tgregworks.reference.PartTypes;
import vexatos.tgregworks.util.TGregUtils;

@SuppressWarnings({ "SpellCheckingInspection" })
public class VASIntegratedAssemblyRecipePool implements IRecipePool {

    private final IRecipeMap IA = AppendRecipeMaps.integratedAssemblyRecipes;
    private final RecipeMap<?> IA_R = AppendRecipeMaps.integratedAssemblyRecipes;
    final boolean EnableWirelessRecipes = true;
    final boolean EnableTSTRecipes = true;

    @Override
    public void loadRecipesPostInit() {
        // 终极配方-测试
        GT_Values.RA.stdBuilder()
            .itemInputs(
                VAMaterials.Primoium.get(OrePrefixes.dust, 64),
                VAMaterials.Originium.get(OrePrefixes.dust, 64),
                VAMaterials.Astrium.get(OrePrefixes.dust, 64),
                VAMaterials.Astrium.get(OrePrefixes.dust, 64),
                VAItemList.AstriumInfinityGem.get(64),
                VAItemList.AstriumInfinityGem.get(64),
                MaterialsUEVplus.Eternity.getNanite(64),
                MaterialsUEVplus.Eternity.getNanite(64))
            .fluidInputs(
                MaterialsUEVplus.Universium.getMolten(1440000000),
                MaterialsUEVplus.ExcitedDTSC.getFluid(1200000000),
                Materials.CosmicNeutronium.getMolten(1440000000),
                MaterialsUEVplus.Eternity.getMolten(1440000000),
                MaterialsUEVplus.SpaceTime.getMolten(1440000000),
                Materials.Infinity.getMolten(1440000000))
            .itemOutputs(VAItemList.ItemUltimate.get(1))
            .noOptimize()
            .eut(RECIPE_MAX)
            .duration(160 * HOURS)
            .addTo(IA);
    }

    @Override
    public void loadRecipesCompleteInit() {
        if (EnableWirelessRecipes) {
            loadWirelessHatchRecipes();
        }

        if (EnableTSTRecipes && VA_Mods.TwistSpaceTechnology.isModLoaded()) {
            loadTSTRecipes();
        }

        loadMainRecipes();

    }

    public void loadMainRecipes() {
        // region MISC
        // 光辉玻璃板
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Sunnarium.getPlates(16))
            .fluidInputs(
                Materials.Glass.getMolten(72 * INGOTS),
                Materials.Glowstone.getMolten(16 * INGOTS),
                Materials.Uranium.getMolten(16 * INGOTS))
            .itemOutputs(GT_ModHandler.getModItem(AdvancedSolarPanel.ID, "asp_crafting_items", 64, 5))
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(20 * SECONDS)
            .addTo(IA);
        // 无尽箱子
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem(AppliedEnergistics2.ID, "item.ItemMultiMaterial", 10, 60),
                ItemList.Quantum_Chest_IV.get(18),
                GT_ModHandler.getModItem(Avaritia.ID, "Resource", 36, 0),
                Basic.getSingularity(1))
            .fluidInputs(
                Materials.InfinityCatalyst.getMolten(64 * INGOTS),
                Materials.Infinity.getMolten((4 + 4 * 9) * INGOTS),
                Materials.CosmicNeutronium.getMolten(6 * 9 * INGOTS))
            .itemOutputs(GT_ModHandler.getModItem(AvaritiaAddons.ID, "InfinityChest", 1, 0))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(4 * SECONDS)
            .addTo(IA);
        // 中子压缩机
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Hull_UV.get(1),
                Tier.UV.getComponent(Tier.Component.Electric_Motor, 4),
                Tier.UV.getComponent(Tier.Component.Electric_Piston, 8),
                Tier.UV.getComponent(Tier.Component.Conveyor_Module, 8),
                Tier.UHV.getCircuit(4),
                GT_ModHandler.getModItem(Avaritia.ID, "Resource", 8, 1),
                GT_ModHandler.getModItem(TinkerConstruct.ID, "heavyPlate", 10, 500),
                TGregUtils.newItemStack(Materials.BlackPlutonium, PartTypes.LargePlate, 8),
                GT_ModHandler.getModItem(TinkerConstruct.ID, "heavyPlate", 6, 315))
            .fluidInputs(
                Materials.CosmicNeutronium.getMolten(8 * 9 * INGOTS),
                Materials.Neutronium.getMolten(4 * INGOTS))
            .itemOutputs(GT_ModHandler.getModItem(Avaritia.ID, "Neutronium_Compressor", 1, 0))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(4 * SECONDS)
            .addTo(IA);
        // Gravitation Engine
        GT_Values.RA.stdBuilder()
            .itemInputs(
                SCPart.LuV.getPrefix(OrePrefixes.wireGt16, 4),
                ItemList.IV_Coil.get(16),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Copper, 64),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Copper, 64))
            .fluidInputs(FluidHelper.getFluidStackByName("supercoolant", 32 * BUCKETS))
            .itemOutputs(GT_ModHandler.getModItem(GraviSuite.ID, "itemSimpleItem", 64, 3))
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(30 * SECONDS)
            .addTo(IA);
        // PreTesseract v1
        GT_Values.RA.stdBuilder()
            .itemInputs(VAItemList.AstriumInfinityGem.get(1), GGChip.ZPM.getItemStack(1))
            .fluidInputs(
                Materials.CosmicNeutronium.getMolten(16 * 4 * INGOTS),
                ALLOY.OCTIRON.getFluidStack(16 * 4 * INGOTS),
                MyMaterial.tairitsu.getMolten(16 * 4 * INGOTS),
                Materials.Sunnarium.getMolten(16 * 4 * INGOTS),
                ALLOY.ABYSSAL.getFluidStack(16 * 24 * INGOTS),
                ALLOY.BOTMIUM.getFluidStack(16 * 2 * INGOTS))
            .itemOutputs(VAItemList.PreTesseract.get(64))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(25 * SECONDS)
            .addTo(IA);
        // PreTesseract v2
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Tesseract.get(0),
                VAItemList.AstriumInfinityGem.get(4),
                GT_ModHandler.getModItem(GTPlusPlus.ID, "MU-metaitem.01", 16L, 32105))
            .fluidInputs(MaterialsUEVplus.TranscendentMetal.getMolten(256 * INGOTS))
            .itemOutputs(GT_Utility.copyAmountUnsafe(256, VAItemList.PreTesseract.get(1)))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(25 * SECONDS)
            .addTo(IA);
        // endregion

        // region LENSES
        // Astrium MAGIC
        GT_Values.RA.stdBuilder()
            .itemInputs(CustomItemList.MysteriousCrystalLens.get(32), VAItemList.AstriumInfinityGem.get(16))
            .fluidInputs(
                VAMaterials.Astrium.getMolten(32 * INGOTS),
                Materials.Glass.getMolten(128 * INGOTS),
                Materials.Neutronium.getMolten(64 * INGOTS))
            .itemOutputs(VAItemList.LensAstriumMagic.get(1))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(120 * SECONDS)
            .addTo(IA);
        // Astrium INF
        GT_Values.RA.stdBuilder()
            .itemInputs(CustomItemList.ChromaticLens.get(32), VAItemList.AstriumInfinityGem.get(32))
            .fluidInputs(
                VAMaterials.Astrium.getMolten(128 * INGOTS),
                Materials.Glass.getMolten(1024 * INGOTS),
                Materials.CosmicNeutronium.getMolten(512 * INGOTS))
            .itemOutputs(VAItemList.LensAstriumInfinity.get(1))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(120 * SECONDS)
            .addTo(IA);
        // Or
        GT_Values.RA.stdBuilder()
            .itemInputs(CustomItemList.ReinforcedGlassLense.get(64), VAMaterials.Originium.get(OrePrefixes.dust, 64))
            .fluidInputs(
                VAMaterials.Originium.getMolten(128 * INGOTS),
                Materials.Glass.getMolten(64 * INGOTS),
                MyMaterial.orundum.getMolten(64 * INGOTS))
            .itemOutputs(VAItemList.LensOriginium.get(1))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(120 * SECONDS)
            .addTo(IA);
        // Pr
        GT_Values.RA.stdBuilder()
            .itemInputs(CustomItemList.ReinforcedGlassLense.get(64), VAMaterials.Primoium.get(OrePrefixes.dust, 64))
            .fluidInputs(
                VAMaterials.Primoium.getMolten(128 * INGOTS),
                Materials.Glass.getMolten(64 * INGOTS),
                Materials.DraconiumAwakened.getMolten(64 * INGOTS))
            .itemOutputs(VAItemList.LensPrimoium.get(1))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(120 * SECONDS)
            .addTo(IA);
        // endregion

        // region 电池配方
        // 兰波顿能量球 IV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.Delicate_Board.getItemStack(1),
                BWPart.Part_IC.getItemStack(4),
                BWPart.Part_NanoCPU.getItemStack(2))
            .fluidInputs(
                SolderMaterial.SolderingAlloy.getFluidStack(16 * INGOTS),
                Materials.Platinum.getMolten(160 * INGOTS),
                VAMaterials.LapotronEnhancedFluid.getFluidOrGas(6400))
            .itemOutputs(ItemList.Energy_LapotronicOrb.get(16))
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(12 * (25 * SECONDS + 12 * TICKS))
            .addTo(IA);
        // 兰波顿能量簇 LUV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.Delicate_Board.getItemStack(1),
                GT_Utility.copyAmountUnsafe(128, ItemList.Energy_LapotronicOrb.get(1)),
                BWPart.Part_IC_H.getItemStack(4),
                BWPart.Part_QBit.getItemStack(2))
            .fluidInputs(
                SolderMaterial.IndaAlloy.getFluidStack(16 * 5 * INGOTS),
                Materials.NiobiumTitanium.getMolten(16 * 2 * INGOTS),
                Materials.NaquadahAlloy.getMolten(16 * 16 * INGOTS))
            .itemOutputs(ItemList.Energy_LapotronicOrb2.get(16))
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(12 * (51 * SECONDS + 4 * TICKS))
            .addTo(IA);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.Elite_Board.getItemStack(1),
                GGChip.LuV.getItemStack(4),
                BWPart.Part_IC_H.getItemStack(64),
                BWPart.Adv_Diode.getItemStack(8),
                BWPart.Adv_Capacitor.getItemStack(8),
                BWPart.Adv_Resistor.getItemStack(8),
                BWPart.Adv_Transistor.getItemStack(8))
            .fluidInputs(
                SolderMaterial.IndaAlloy.getFluidStack(16 * 5 * INGOTS),
                Materials.NaquadahAlloy.getMolten(16 * 16 * INGOTS),
                Materials.Platinum.getMolten(16 * 8 * INGOTS),
                VAMaterials.LapotronEnhancedFluid.getFluidOrGas(16 * 800))
            .itemOutputs(ItemList.Energy_LapotronicOrb2.get(16))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(12 * 50 * SECONDS)
            .addTo(IA);
        // 能量模块 ZPM
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.ZPM.getItemStack(4),
                GT_Utility.copyAmountUnsafe(128, ItemList.Energy_LapotronicOrb2.get(1)),
                ItemList.Field_Generator_LuV.get(32),
                GT_Utility.copyAmountUnsafe(6 * 64, BWPart.Part_ASOC.getItemStack(1)),
                BWPart.Adv_Transistor.getItemStack(8))
            .fluidInputs(
                SolderMaterial.IndaAlloy.getFluidStack(16 * 20 * INGOTS),
                Materials.Europium.getMolten(16 * 16 * INGOTS),
                Materials.Naquadah.getMolten(16 * 16 * INGOTS),
                FluidHelper.getFluidStackByName("ic2coolant", 16 * 16000))
            .itemOutputs(ItemList.Energy_Module.get(16))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(12 * 100 * SECONDS)
            .addTo(IA);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.Wetware_Board.getItemStack(1),
                GGChip.ZPM.getItemStack(4),
                BWPart.Part_IC_UH.getItemStack(64),
                BWPart.Opt_Diode.getItemStack(8),
                BWPart.Opt_Capacitor.getItemStack(8),
                BWPart.Opt_Resistor.getItemStack(8),
                BWPart.Opt_Transistor.getItemStack(8))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(16 * 5 * INGOTS),
                Materials.Bedrockium.getMolten(16 * 16 * INGOTS),
                ELEMENT.STANDALONE.HYPOGEN.getFluidStack(16 * 6 * INGOTS),
                VAMaterials.LapotronEnhancedFluid.getFluidOrGas(16 * 1000))
            .itemOutputs(ItemList.Energy_Module.get(16))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * 50 * SECONDS)
            .addTo(IA);
        // 能量簇 UV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.UV.getItemStack(4),
                GT_Utility.copyAmountUnsafe(128, ItemList.Energy_Module.get(1)),
                ItemList.Field_Generator_ZPM.get(32),
                BWPart.Part_IC_H.getItemStack(256),
                BWPart.Adv_Diode.getItemStack(16))
            .fluidInputs(
                SolderMaterial.IndaAlloy.getFluidStack(16 * 20 * INGOTS),
                Materials.Americium.getMolten(16 * 16 * INGOTS),
                Materials.NaquadahAlloy.getMolten(16 * 16 * INGOTS),
                FluidHelper.getFluidStackByName("ic2coolant", 16 * 16000))
            .itemOutputs(ItemList.Energy_Cluster.get(16))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * 100 * SECONDS)
            .addTo(IA);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.Bio_Board.getItemStack(1),
                GGChip.UV.getItemStack(4),
                BWPart.Part_IC_N.getItemStack(64),
                BWPart.Opt_Diode.getItemStack(32),
                BWPart.Opt_Capacitor.getItemStack(32),
                BWPart.Opt_Resistor.getItemStack(32),
                BWPart.Opt_Transistor.getItemStack(32))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(16 * 10 * INGOTS),
                Materials.CosmicNeutronium.getMolten(16 * 16 * INGOTS),
                MaterialsUEVplus.SpaceTime.getMolten(16 * 6 * INGOTS),
                VAMaterials.LapotronEnhancedFluid.getFluidOrGas(16 * 2000))
            .itemOutputs(ItemList.Energy_Cluster.get(16))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * 50 * SECONDS)
            .addTo(IA);
        // 终极电池 UV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.UHV.getItemStack(4),
                GT_Utility.copyAmountUnsafe(128, ItemList.Energy_Cluster.get(1)),
                ItemList.Field_Generator_UV.get(32),
                BWPart.Part_IC_H.getItemStack(256),
                BWPart.Adv_Diode.getItemStack(32))
            .fluidInputs(
                SolderMaterial.IndaAlloy.getFluidStack(16 * 20 * INGOTS),
                Materials.Tritanium.getMolten(16 * 64 * INGOTS),
                VAMaterials.SuperconductorFlux.getFluidOrGas(16 * 4 * INGOTS),
                FluidHelper.getFluidStackByName("ic2coolant", 16 * 16000))
            .itemOutputs(ItemList.ZPM2.get(16))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * 150 * SECONDS)
            .addTo(IA);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.Opt_Board.getItemStack(1),
                GGChip.UHV.getItemStack(4),
                BWPart.Part_IC_P.getItemStack(64),
                BWPart.Opt_Diode.getItemStack(64),
                BWPart.Opt_Capacitor.getItemStack(64),
                BWPart.Opt_Resistor.getItemStack(64),
                BWPart.Opt_Transistor.getItemStack(64))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(16 * 20 * INGOTS),
                MyMaterial.shirabon.getMolten(16 * 16 * INGOTS),
                MaterialsUEVplus.WhiteDwarfMatter.getMolten(16 * 4 * INGOTS),
                MaterialsUEVplus.BlackDwarfMatter.getMolten(16 * 4 * INGOTS),
                MaterialsUEVplus.MagnetohydrodynamicallyConstrainedStarMatter.getMolten(8 * INGOTS),
                VAMaterials.LapotronEnhancedFluid.getFluidOrGas(16 * 5000))
            .itemOutputs(ItemList.ZPM2.get(16))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 50 * SECONDS)
            .addTo(IA);
        // 真·终极电池 UMV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.UEV.getItemStack(4),
                GT_Utility.copyAmountUnsafe(128, ItemList.ZPM2.get(1)),
                ItemList.Field_Generator_UHV.get(64),
                BWPart.Part_IC_UH.getItemStack(256),
                BWPart.Part_ASOC.getItemStack(3 * 64),
                BWPart.Adv_Diode.getItemStack(64))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(16 * 4608),
                Materials.Neutronium.getMolten(16 * 128 * INGOTS),
                Materials.Naquadria.getMolten(16 * 9216),
                VAMaterials.SuperconductorFlux.getFluidOrGas(16 * 16 * INGOTS),
                FluidHelper.getFluidStackByName("ic2coolant", 16 * 32000))
            .itemOutputs(ItemList.ZPM3.get(16))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * 200 * SECONDS)
            .addTo(IA);
        // 极·终极电池 UXV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.UIV.getItemStack(4),
                GT_Utility.copyAmountUnsafe(128, ItemList.ZPM3.get(1)),
                ItemList.Field_Generator_UEV.get(64),
                BWPart.Part_IC_P.getItemStack(256),
                BWPart.Part_ASOC.getItemStack(6 * 64),
                BWPart.Opt_Diode.getItemStack(64))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(16 * 9216),
                Materials.InfinityCatalyst.getMolten(16 * 128 * INGOTS),
                Materials.Quantium.getMolten(16 * 18432),
                Materials.Naquadria.getMolten(16 * 18432),
                VAMaterials.SuperconductorFlux.getFluidOrGas(16 * 64 * INGOTS),
                FluidHelper.getFluidStackByName("ic2coolant", 16 * 64000))
            .itemOutputs(ItemList.ZPM4.get(16))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 250 * SECONDS)
            .addTo(IA);
        // 狂·终极电池 MAX
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.UMV.getItemStack(4),
                GT_Utility.copyAmountUnsafe(128, ItemList.ZPM4.get(1)),
                ItemList.Field_Generator_UIV.get(64),
                BWPart.Part_IC_Q.getItemStack(256),
                GT_Utility.copyAmountUnsafe(16 * 64, CustomItemList.RawPicoWafer.get(1)),
                BWPart.Opt_Diode.getItemStack(64),
                BWPart.Opt_Inductor.getItemStack(32))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(16 * 18432),
                ELEMENT.STANDALONE.HYPOGEN.getFluidStack(16 * 128 * INGOTS),
                ELEMENT.STANDALONE.CELESTIAL_TUNGSTEN.getFluidStack(18_432),
                Materials.Quantium.getMolten(16 * 18432),
                VAMaterials.SuperconductorFlux.getFluidOrGas(16 * 128 * INGOTS),
                FluidHelper.getFluidStackByName("ic2coolant", 16 * 128000))
            .itemOutputs(ItemList.ZPM5.get(16))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(12 * 300 * SECONDS)
            .addTo(IA);
        // 太·终极电池 ER
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.UXV.getItemStack(4),
                GT_Utility.copyAmountUnsafe(128, ItemList.ZPM5.get(1)),
                ItemList.Field_Generator_UMV.get(64),
                BWPart.Part_IC_Q.getItemStack(256),
                GT_Utility.copyAmountUnsafe(16 * 64, CustomItemList.RawPicoWafer.get(1)),
                BWPart.Opt_Diode.getItemStack(64),
                BWPart.Opt_Inductor.getItemStack(64))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(16 * 36864),
                ELEMENT.STANDALONE.DRAGON_METAL.getFluidStack(16 * 128 * INGOTS),
                ELEMENT.STANDALONE.ASTRAL_TITANIUM.getFluidStack(16 * 36864),
                ELEMENT.STANDALONE.CELESTIAL_TUNGSTEN.getFluidStack(16 * 36864),
                VAMaterials.SuperconductorFlux.getFluidOrGas(16 * 256 * INGOTS),
                FluidHelper.getFluidStackByName("ic2coolant", 16 * 256000))
            .itemOutputs(ItemList.ZPM6.get(16))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(12 * 350 * SECONDS)
            .addTo(IA);
        // endregion

        // region 杂项组装
        // endregion

        // region 防辐射板
        // 防辐射板 64x
        VA_RecipeBuilder.builder()
            .itemInputs(
                ItemHelper.setStackSize(Materials.NaquadahAlloy.getPlates(64), 8 * 64),
                ItemHelper.setStackSize(Materials.Lanthanum.getPlates(64), 4 * 64))
            .fluidInputs(Materials.Lead.getMolten(64 * 8 * INGOTS))
            .itemOutputs(ItemRefer.Radiation_Protection_Plate.get(64))
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(12 * 80 * SECONDS)
            .addTo(IA_R);
        // 进阶防辐射板 64x
        VA_RecipeBuilder.builder()
            .itemInputs(
                ItemHelper.setStackSize(Materials.ElectrumFlux.getPlates(64), 256),
                ItemHelper.setStackSize(Materials.Trinium.getPlates(64), 256),
                ItemHelper.setStackSize(Materials.Osmiridium.getPlates(64), 256),
                ItemHelper.setStackSize(Materials.VibrantAlloy.getPlates(64), 256))
            .fluidInputs(
                SolderMaterial.IndaAlloy.getFluidStack(16 * 32 * INGOTS),
                Materials.Lead.getMolten(32 * 32 * INGOTS),
                Materials.NaquadahAlloy.getMolten(1280 * INGOTS),
                Materials.Lanthanum.getMolten(512 * INGOTS))
            .itemOutputs(ItemRefer.Advanced_Radiation_Protection_Plate.get(64))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(24 * 100 * SECONDS)
            .addTo(IA_R);
        // endregion

        // region 戴森球
        // 耐高温网 1024
        VA_RecipeBuilder.builder()
            .itemInputs(
                ItemHelper.setStackSize(GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.Carbon, 1), 128),
                ItemHelper.setStackSize(GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Neutronium, 1), 512),
                ItemHelper
                    .setStackSize(GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.TungstenCarbide, 1), 512),
                ItemHelper.setStackSize(GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Tantalum, 1), 512))
            .fluidInputs(
                SolderMaterial.IndaAlloy.getFluidStack(128 * INGOTS),
                ALLOY.SILICON_CARBIDE.getFluidStack(48 * INGOTS))
            .itemOutputs(ItemHelper.setStackSize(ItemHelper.getItemStack(GSItems.DysonSwarmItems, 1, 3), 1024))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(200 * SECONDS)
            .addTo(IA_R);
        // 戴森球模块 512 8x
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Cover_SolarPanel_UV.get(1),
                ItemHelper.getItemStack(GSItems.DysonSwarmItems, 32, 3),
                ItemRefer.Radiation_Protection_Plate.get(4),
                BWPart.Part_IC_Q.getItemStack(1),
                GGChip.UHV.getItemStack(1),
                ItemList.Emitter_UEV.get(4),
                ItemList.Sensor_UEV.get(4))
            .fluidInputs(SolderMaterial.MutatedLivingAlloy.getFluidStack(256 * INGOTS))
            .itemOutputs(GT_Utility.copyAmountUnsafe(8 * 64, ItemHelper.getItemStack(GSItems.DysonSwarmItems, 1, 0)))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(5 * SECONDS)
            .addTo(IA);
        // endregion

        // region Optical
        // Optical RAM
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.LuV.getItemStack(4),
                BWPart.Elite_Board.getItemStack(4),
                BWPart.Opt_Card.getItemStack(4),
                GT_ModHandler.getModItem(AdvancedSolarPanel.ID, "asp_crafting_items", 32, 5),
                BWPart.Part_RAM.getItemStack(64),
                BWPart.Part_SOC.getItemStack(64),
                BWPart.Part_NAND.getItemStack(64),
                GT_Utility.copyAmountUnsafe(128, GT_ModHandler.getModItem("gregtech", "gt.blockmachines", 1, 15470)))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(16 * 48 * INGOTS),
                Materials.VanadiumGallium.getMolten(16 * 16 * INGOTS),
                Materials.Infinity.getMolten(16 * 32 * INGOTS))
            .itemOutputs(GT_Utility.copyAmountUnsafe(2048, ItemList.Optically_Compatible_Memory.get(1)))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 80 * SECONDS)
            .addTo(IA);
        // Optical CPU
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.Opt_Card.getItemStack(8),
                BWPart.Opt_CPUCasing.getItemStack(8),
                BWPart.Opt_Card.getItemStack(4),
                GT_Utility.copyAmountUnsafe(128, GT_ModHandler.getModItem("gregtech", "gt.blockmachines", 1, 15470)),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Tritanium, 32),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Draconium, 32))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(48 * INGOTS),
                Materials.CosmicNeutronium.getMolten(64 * INGOTS))
            .itemOutputs(GT_Utility.copyAmountUnsafe(128, ItemList.Optically_Perfected_CPU.get(1)))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(120 * SECONDS)
            .addTo(IA);
        // endregion

        // region Coil Misc
        // SC Coil
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Trinium, 64),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Trinium, 64),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Trinium, 64),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Trinium, 64),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Trinium, 64),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Trinium, 64),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Trinium, 64),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Trinium, 64),
                VAItemList.DenseMicaInsulatorFoil.get(64))
            .fluidInputs(VAMaterials.SuperconductorFlux.getFluidOrGas(64 * INGOTS))
            .itemOutputs(ItemList.Casing_Coil_Superconductor.get(64))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(12 * 40 * SECONDS)
            .addTo(IA);
        // Adv SC Coil (GG)
        GT_Values.RA.stdBuilder()
            .itemInputs(ItemList.Casing_Coil_Superconductor.get(48), ItemRefer.HiC_T2.get(16))
            .fluidInputs(
                MyMaterial.marM200.getMolten(16 * 8 * INGOTS),
                MyMaterial.zircaloy4.getMolten(26 * 2 * INGOTS),
                Materials.Aluminium.getMolten(24 * INGOTS))
            .itemOutputs(ItemRefer.Compact_Fusion_Coil_T0.get(16))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(12 * 60 * SECONDS)
            .addTo(IA);
        // endregion

        // region Coil UHV+
        // Infinity
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.circuit.get(Materials.Infinite), 1),
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Infinity, 8),
                GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Infinity, 8),
                CustomItemList.MicaInsulatorFoil.get(64),
                CustomItemList.MicaInsulatorFoil.get(32))
            .fluidInputs(Materials.DraconiumAwakened.getMolten(4 * INGOTS))
            .itemOutputs(ItemList.Casing_Coil_Infinity.get(1))
            .noOptimize()
            .eut(8000000)
            .duration(60 * SECONDS)
            .addTo(IA);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.UHV.getItemStack(1),
                VAItemList.DenseMicaInsulatorFoil.get(64),
                VAItemList.DenseMicaInsulatorFoil.get(32))
            .fluidInputs(
                Materials.DraconiumAwakened.getMolten(16 * 4 * INGOTS),
                Materials.Infinity.getMolten(16 * 9 * INGOTS))
            .itemOutputs(ItemList.Casing_Coil_Infinity.get(16))
            .noOptimize()
            .eut(8000000)
            .duration(12 * 60 * SECONDS)
            .addTo(IA);
        // Hypogen
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.circuit.get(Materials.Bio), 1),
                ELEMENT.STANDALONE.HYPOGEN.getWire02(8),
                ELEMENT.STANDALONE.HYPOGEN.getScrew(8),
                CustomItemList.MicaInsulatorFoil.get(64),
                CustomItemList.MicaInsulatorFoil.get(64),
                CustomItemList.MicaInsulatorFoil.get(64))
            .fluidInputs(Materials.Infinity.getMolten(4 * INGOTS))
            .itemOutputs(ItemList.Casing_Coil_Hypogen.get(1))
            .noOptimize()
            .eut(8000000 * 4)
            .duration(60 * SECONDS)
            .addTo(IA);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.UEV.getItemStack(1),
                VAItemList.DenseMicaInsulatorFoil.get(64),
                VAItemList.DenseMicaInsulatorFoil.get(64),
                VAItemList.DenseMicaInsulatorFoil.get(64))
            .fluidInputs(
                ELEMENT.STANDALONE.HYPOGEN.getFluidStack(16 * 9 * INGOTS),
                Materials.Infinity.getMolten(16 * 4 * INGOTS))
            .itemOutputs(ItemList.Casing_Coil_Hypogen.get(16))
            .noOptimize()
            .eut(8000000 * 4)
            .duration(12 * 60 * SECONDS)
            .addTo(IA);
        // Eternal
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.circuit.get(Materials.Optical), 1),
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, MaterialsUEVplus.SpaceTime, 8),
                GT_OreDictUnificator.get(OrePrefixes.screw, MaterialsUEVplus.SpaceTime, 8),
                CustomItemList.MicaInsulatorFoil.get(64),
                CustomItemList.MicaInsulatorFoil.get(64),
                CustomItemList.MicaInsulatorFoil.get(64),
                CustomItemList.MicaInsulatorFoil.get(64),
                CustomItemList.MicaInsulatorFoil.get(64),
                CustomItemList.MicaInsulatorFoil.get(64))
            .fluidInputs(ELEMENT.STANDALONE.HYPOGEN.getFluidStack(4 * INGOTS))
            .itemOutputs(ItemList.Casing_Coil_Eternal.get(1))
            .noOptimize()
            .eut(8000000 * 16)
            .duration(60 * SECONDS)
            .addTo(IA);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.UIV.getItemStack(1),
                VAItemList.DenseMicaInsulatorFoil.get(64),
                VAItemList.DenseMicaInsulatorFoil.get(64),
                VAItemList.DenseMicaInsulatorFoil.get(64),
                VAItemList.DenseMicaInsulatorFoil.get(64),
                VAItemList.DenseMicaInsulatorFoil.get(64),
                VAItemList.DenseMicaInsulatorFoil.get(64))
            .fluidInputs(
                MaterialsUEVplus.SpaceTime.getMolten(16 * 9 * INGOTS),
                ELEMENT.STANDALONE.HYPOGEN.getFluidStack(16 * 4 * INGOTS))
            .itemOutputs(ItemList.Casing_Coil_Eternal.get(16))
            .noOptimize()
            .eut(8000000 * 16)
            .duration(12 * 60 * SECONDS)
            .addTo(IA);
        // endregion

        // region Coil Fusion
        // Casing Fusion Coil
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Casing_Coil_Superconductor.get(16),
                GGChip.LuV.getItemStack(4),
                ItemList.Field_Generator_MV.get(32))
            .fluidInputs(
                Materials.Iridium.getMolten(12 * 8 * INGOTS),
                Materials.Tin.getMolten(16 * 64 * INGOTS),
                Materials.TungstenCarbide.getMolten(8 * 72 * INGOTS),
                Materials.Beryllium.getMolten(8 * 72 * INGOTS))
            .itemOutputs(ItemList.Casing_Fusion_Coil.get(16))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(400 * SECONDS)
            .addTo(IA);
        // Advanced Casing Fusion Coil
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Casing_Fusion_Coil.get(16),
                GT_Utility.copyAmountUnsafe(16 * 16, ItemList.Energy_LapotronicOrb2.get(1)),
                GGChip.LuV.getItemStack(16),
                GGChip.UV.getItemStack(8),
                ItemList.Emitter_UHV.get(16),
                ItemList.Sensor_UHV.get(16))
            .fluidInputs(
                ALLOY.CINOBITE.getFluidStack(16 * 16 * INGOTS),
                ALLOY.OCTIRON.getFluidStack(16 * 16 * INGOTS),
                ELEMENT.STANDALONE.ASTRAL_TITANIUM.getFluidStack(16 * 16 * INGOTS),
                Materials.UUMatter.getFluid(16 * 8 * BUCKETS),
                Materials.Neutronium.getMolten(16 * 8 * INGOTS))
            .itemOutputs(GregtechItemList.Casing_Fusion_Internal.get(16))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * 16 * SECONDS)
            .addTo(IA);
        // Advanced Casing Fusion Coil II
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemRefer.Compact_Fusion_Coil_T2.get(16),
                ItemList.Casing_Fusion_Coil.get(16),
                GT_Utility.copyAmountUnsafe(16 * 16, ItemList.Energy_Module.get(1)),
                GGChip.ZPM.getItemStack(16),
                GGChip.UHV.getItemStack(8),
                ItemList.Emitter_UHV.get(16),
                ItemList.Sensor_UHV.get(16))
            .fluidInputs(
                ELEMENT.getInstance().NEPTUNIUM.getFluidStack(16 * 16 * INGOTS),
                ELEMENT.STANDALONE.CHRONOMATIC_GLASS.getFluidStack(16 * 16 * INGOTS),
                ALLOY.ABYSSAL.getFluidStack(16 * 16 * INGOTS),
                ELEMENT.STANDALONE.DRAGON_METAL.getFluidStack(16 * 16 * INGOTS),
                ELEMENT.STANDALONE.RHUGNOR.getFluidStack(16 * 8 * INGOTS))
            .itemOutputs(GregtechItemList.Casing_Fusion_Internal2.get(16))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 60 * SECONDS)
            .addTo(IA);

        // Compact Casing Fusion Coil
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Casing_Fusion_Coil.get(48),
                ItemRefer.HiC_T3.get(16),
                BWPart.Part_ILC.getItemStack(8),
                BWPart.Part_IC.getItemStack(1),
                ItemList.IV_Coil.get(1))
            .fluidInputs(
                MyMaterial.artheriumSn.getMolten(16 * 4 * INGOTS),
                MyMaterial.titaniumBetaC.getMolten(16 * INGOTS),
                Materials.EnergeticAlloy.getMolten(16 * INGOTS),
                Materials.Aluminium.getMolten(16 * INGOTS),
                Materials.Silver.getMolten(8 * INGOTS))
            .itemOutputs(ItemRefer.Compact_Fusion_Coil_T1.get(16))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(12 * 40 * SECONDS)
            .addTo(IA);
        // Compact Casing Fusion Coil Adv
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Casing_Fusion_Coil.get(48),
                ItemRefer.HiC_T5.get(16),
                ItemRefer.Advanced_Radiation_Protection_Plate.get(32),
                ItemList.NuclearStar.get(4))
            .fluidInputs(MyMaterial.dalisenite.getMolten(16 * 4 * INGOTS), MyMaterial.hikarium.getMolten(16 * INGOTS))
            .itemOutputs(ItemRefer.Compact_Fusion_Coil_T2.get(16))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(12 * 40 * SECONDS)
            .addTo(IA);
        // Compact Casing Fusion Coil II Prototype
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GregtechItemList.Casing_Fusion_Internal.get(48),
                ItemRefer.HiC_T5.get(16),
                GT_ModHandler.getModItem("miscutils", "item.itemBufferCore4", 16))
            .fluidInputs(ALLOY.ENERGYCRYSTAL.getFluidStack(16 * 8 * INGOTS), ALLOY.LAURENIUM.getFluidStack(16 * INGOTS))
            .itemOutputs(ItemRefer.Compact_Fusion_Coil_T3.get(16))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * 100 * SECONDS)
            .addTo(IA);
        // Compact Casing Fusion Coil II
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GregtechItemList.Casing_Fusion_Internal2.get(48),
                ItemRefer.HiC_T5.get(64),
                GGChip.UEV.getItemStack(1),
                GT_ModHandler.getModItem("miscutils", "item.itemBufferCore5", 16))
            .fluidInputs(
                ALLOY.BLACK_TITANIUM.getFluidStack(16 * 8 * INGOTS),
                MyMaterial.metastableOganesson.getMolten(16 * 4 * INGOTS))
            .itemOutputs(ItemRefer.Compact_Fusion_Coil_T4.get(16))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * 100 * SECONDS)
            .addTo(IA);
        // endregion

        // region Casing Fusion
        // Compact Casing Fusion Coil III
        GT_Values.RA.stdBuilder()
            .itemInputs(
                ItemList.Casing_Fusion2.get(16),
                GGChip.EV.getItemStack(16),
                GGChip.IV.getItemStack(8),
                ItemList.Electric_Motor_UHV.get(32),
                ItemList.Electric_Piston_UHV.get(16))
            .fluidInputs(
                Materials.TungstenCarbide.getMolten(16 * 8 * 9 * INGOTS),
                Materials.Neutronium.getMolten(16 * 8 * INGOTS),
                Materials.UUMatter.getFluid(16 * BUCKETS),
                ALLOY.CINOBITE.getFluidStack(16 * 4 * INGOTS),
                ALLOY.OCTIRON.getFluidStack(16 * 4 * INGOTS),
                ELEMENT.STANDALONE.ASTRAL_TITANIUM.getFluidStack(16 * 4 * INGOTS))
            .itemOutputs(GregtechItemList.Casing_Fusion_External.get(16))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * 15 * SECONDS)
            .addTo(IA);
        // Compact Casing Fusion Coil IV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GregtechItemList.Casing_Fusion_External.get(16),
                GGChip.IV.getItemStack(16),
                GGChip.LuV.getItemStack(8),
                ItemList.Electric_Motor_UEV.get(32),
                ItemList.Electric_Piston_UEV.get(16))
            .fluidInputs(
                Materials.NaquadahAlloy.getMolten(16 * 8 * 9 * INGOTS),
                ELEMENT.STANDALONE.CHRONOMATIC_GLASS.getFluidStack(16 * 16 * INGOTS),
                ELEMENT.getInstance().FERMIUM.getFluidStack(16 * 8 * INGOTS),
                ALLOY.ABYSSAL.getFluidStack(16 * 8 * INGOTS),
                ELEMENT.STANDALONE.DRAGON_METAL.getFluidStack(16 * 8 * INGOTS))
            .itemOutputs(GregtechItemList.Casing_Fusion_External2.get(16))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 15 * SECONDS)
            .addTo(IA);
        // endregion

        // region Casing Fusion
        // FRF Coil I
        VA_RecipeBuilder.builder()
            .itemInputs(
                Tier.UHV.getCircuitWrap(1),
                Tier.UV.getComponent(Tier.Component.Field_Generator, 32),
                ItemHelper.setStackSize(Tier.UV.getComponent(Tier.Component.Electric_Pump, 1), 128),
                ItemHelper.setStackSize(ItemList.Circuit_Wafer_PPIC.get(1), 32 * 16))
            .fluidInputs(
                SCPart.ZPM.getSxEqualFluid(128 * 16),
                Materials.Americium.getMolten(8 * 9 * 16 * INGOTS),
                Materials.BlackPlutonium.getMolten(16 * 3 * 16 * INGOTS),
                Materials.Osmium.getMolten(2 * 16 * INGOTS),
                Materials.ElectrumFlux.getMolten(64 * 16 * INGOTS),
                FluidHelper.getFluidStackByName("lubricant", 128000 * 16))
            .itemOutputs(ItemRefer.Field_Restriction_Coil_T1.get(16))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(12 * 900 * SECONDS)
            .addTo(IA_R);
        // FRF Coil II
        VA_RecipeBuilder.builder()
            .itemInputs(
                Tier.UEV.getCircuitWrap(1),
                Tier.UHV.getComponent(Tier.Component.Field_Generator, 32),
                ItemHelper.setStackSize(Tier.UHV.getComponent(Tier.Component.Electric_Pump, 1), 128),
                ItemHelper.setStackSize(ItemList.Circuit_Wafer_PPIC.get(1), 48 * 16))
            .fluidInputs(
                SCPart.UV.getSxEqualFluid(2 * 128 * 16),
                Materials.Infinity.getMolten(8 * 9 * 16 * INGOTS),
                Materials.Neutronium.getMolten(16 * 3 * 16 * INGOTS),
                Materials.CosmicNeutronium.getMolten(2 * 16 * INGOTS),
                Materials.DraconiumAwakened.getMolten(64 * 16 * INGOTS),
                FluidHelper.getFluidStackByName("lubricant", 128000 * 16))
            .itemOutputs(ItemRefer.Field_Restriction_Coil_T2.get(16))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(12 * 1800 * SECONDS)
            .addTo(IA_R);
        // FRF Coil III
        VA_RecipeBuilder.builder()
            .itemInputs(
                Tier.UIV.getCircuitWrap(1),
                Tier.UEV.getComponent(Tier.Component.Field_Generator, 32),
                ItemHelper.setStackSize(Tier.UEV.getComponent(Tier.Component.Electric_Pump, 1), 128),
                ItemHelper.setStackSize(ItemList.Circuit_Wafer_PPIC.get(1), 64 * 16))
            .fluidInputs(
                SCPart.UHV.getSxEqualFluid(4 * 128 * 16),
                MaterialsUEVplus.TranscendentMetal.getMolten(8 * 9 * 16 * INGOTS),
                Materials.Infinity.getMolten((16 * 3 + 2) * 16 * INGOTS),
                Materials.Neutronium.getMolten(64 * 16 * INGOTS),
                FluidHelper.getFluidStackByName("lubricant", 128000 * 16))
            .itemOutputs(ItemRefer.Field_Restriction_Coil_T3.get(16))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(12 * 3600 * SECONDS)
            .addTo(IA_R);
        // FRF Coil IV
        VA_RecipeBuilder.builder()
            .itemInputs(
                Tier.UMV.getCircuitWrap(1),
                Tier.UIV.getComponent(Tier.Component.Field_Generator, 32),
                ItemHelper.setStackSize(Tier.UIV.getComponent(Tier.Component.Electric_Pump, 1), 128),
                ItemHelper.setStackSize(ItemList.Circuit_Wafer_PPIC.get(1), 64 * 16))
            .fluidInputs(
                SCPart.UEV.getSxEqualFluid(4 * 128 * 16),
                MaterialsUEVplus.SpaceTime.getMolten((8 * 9 + 16 * 3 + 2) * 16 * INGOTS),
                MaterialsUEVplus.TranscendentMetal.getMolten(64 * 16 * INGOTS),
                FluidHelper.getFluidStackByName("lubricant", 128000 * 16))
            .itemOutputs(ItemRefer.Field_Restriction_Coil_T4.get(16))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(12 * 7200 * SECONDS)
            .addTo(IA_R);
        // endregion

        // region QFT
        // 脉冲T1
        VA_RecipeBuilder.builder()
            .itemInputs(
                GregtechItemList.ForceFieldGlass.get(16),
                ItemHelper.setStackSize(Materials.Carbon.getNanite(1), 4 * 16),
                ItemHelper.setStackSize(Tier.UV.getComponent(Tier.Component.Emitter, 1), 4 * 16),
                GT_ModHandler.getModItem(GTPlusPlus.ID, "MU-metaitem.01", 16, 32105),
                ItemRefer.Advanced_Radiation_Protection_Plate.get(2 * 16))
            .fluidInputs(Materials.Thulium.getMolten(10 * 16 * INGOTS), SCPart.UHV.getMolten(8 * 8 * INGOTS))
            .itemOutputs(GregtechItemList.NeutronPulseManipulator.get(16))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 60 * SECONDS)
            .addTo(IA_R);
        // 脉冲T2
        VA_RecipeBuilder.builder()
            .itemInputs(
                GregtechItemList.ForceFieldGlass.get(2 * 16),
                ItemHelper.setStackSize(Materials.Carbon.getNanite(1), 8 * 16),
                ItemHelper.setStackSize(Tier.UEV.getComponent(Tier.Component.Emitter, 1), 4 * 16),
                GT_ModHandler.getModItem(GTPlusPlus.ID, "MU-metaitem.01", 16, 32105),
                ItemRefer.Advanced_Radiation_Protection_Plate.get(4 * 16))
            .fluidInputs(Materials.Thulium.getMolten(12 * 16 * INGOTS), SCPart.UEV.getMolten(8 * 8 * INGOTS))
            .itemOutputs(GregtechItemList.CosmicFabricManipulator.get(16))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(12 * 75 * SECONDS)
            .addTo(IA_R);
        // 脉冲T3
        VA_RecipeBuilder.builder()
            .itemInputs(
                GregtechItemList.ForceFieldGlass.get(4 * 16),
                ItemHelper.setStackSize(Materials.Carbon.getNanite(1), 16 * 16),
                ItemHelper.setStackSize(Tier.UIV.getComponent(Tier.Component.Emitter, 1), 4 * 16),
                GT_ModHandler.getModItem(GTPlusPlus.ID, "MU-metaitem.01", 16, 32105),
                ItemHelper.setStackSize(ItemRefer.Advanced_Radiation_Protection_Plate.get(1), 8 * 16))
            .fluidInputs(Materials.Thulium.getMolten(15 * 16 * INGOTS), SCPart.UIV.getMolten(8 * 8 * INGOTS))
            .itemOutputs(GregtechItemList.InfinityInfusedManipulator.get(16))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(12 * 90 * SECONDS)
            .addTo(IA_R);
        // 脉冲T4
        VA_RecipeBuilder.builder()
            .itemInputs(
                ItemHelper.setStackSize(GregtechItemList.ForceFieldGlass.get(1), 8 * 16),
                ItemHelper.setStackSize(Materials.Carbon.getNanite(1), 32 * 16),
                ItemHelper.setStackSize(Tier.UMV.getComponent(Tier.Component.Emitter, 1), 4 * 16),
                GT_ModHandler.getModItem(GTPlusPlus.ID, "MU-metaitem.01", 16, 32105),
                ItemHelper.setStackSize(ItemRefer.Advanced_Radiation_Protection_Plate.get(1), 16 * 16))
            .fluidInputs(Materials.Thulium.getMolten(20 * 16 * INGOTS), SCPart.UMV.getMolten(8 * 8 * INGOTS))
            .itemOutputs(GregtechItemList.SpaceTimeContinuumRipper.get(16))
            .noOptimize()
            .eut(RECIPE_UXV)
            .duration(12 * 60 * SECONDS)
            .addTo(IA_R);
        // 核心T1
        VA_RecipeBuilder.builder()
            .itemInputs(
                ALLOY.QUANTUM.getFrameBox(16),
                ItemHelper
                    .setStackSize(GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Neutronium, 1), 16 * 16),
                Tier.UV.getComponent(Tier.Component.Field_Generator, 16))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(16 * 10 * INGOTS),
                MyMaterial.preciousMetalAlloy.getMolten(16 * 4 * 9 * INGOTS),
                ELEMENT.STANDALONE.CHRONOMATIC_GLASS.getFluidStack(16 * 2 * INGOTS))
            .itemOutputs(GregtechItemList.NeutronShieldingCore.get(16))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 60 * SECONDS)
            .addTo(IA_R);
        // 核心T2
        VA_RecipeBuilder.builder()
            .itemInputs(
                ALLOY.QUANTUM.getFrameBox(16 * 2),
                ItemHelper
                    .setStackSize(GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Infinity, 1), 16 * 16),
                Tier.UEV.getComponent(Tier.Component.Field_Generator, 16))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(16 * 20 * INGOTS),
                MyMaterial.enrichedNaquadahAlloy.getMolten(16 * 4 * 9 * INGOTS),
                FluidHelper.getFluidStackByName("molten.radoxpoly", 16 * 2 * INGOTS))
            .itemOutputs(GregtechItemList.CosmicFabricShieldingCore.get(16))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(12 * 75 * SECONDS)
            .addTo(IA_R);
        // 核心T3
        VA_RecipeBuilder.builder()
            .itemInputs(
                ALLOY.QUANTUM.getFrameBox(16 * 4),
                ItemHelper.setStackSize(
                    GT_OreDictUnificator.get(OrePrefixes.plateDense, MaterialsUEVplus.TranscendentMetal, 1),
                    16 * 16),
                Tier.UIV.getComponent(Tier.Component.Field_Generator, 16))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(16 * 40 * INGOTS),
                ELEMENT.STANDALONE.HYPOGEN.getFluidStack(16 * 4 * 9 * INGOTS),
                MyMaterial.metastableOganesson.getMolten(16 * 2 * INGOTS))
            .itemOutputs(GregtechItemList.InfinityInfusedShieldingCore.get(16))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(12 * 90 * SECONDS)
            .addTo(IA_R);
        // 核心T4
        VA_RecipeBuilder.builder()
            .itemInputs(
                ItemHelper.setStackSize(ALLOY.QUANTUM.getFrameBox(1), 16 * 8),
                ItemHelper.setStackSize(
                    GT_OreDictUnificator.get(OrePrefixes.plateDense, MaterialsUEVplus.SpaceTime, 1),
                    16 * 16),
                Tier.UMV.getComponent(Tier.Component.Field_Generator, 16))
            .fluidInputs(
                SolderMaterial.MutatedLivingAlloy.getFluidStack(16 * 40 * INGOTS),
                MyMaterial.shirabon.getMolten(16 * 4 * 9 * INGOTS),
                Materials.Dilithium.getMolten(16 * 2 * INGOTS))
            .itemOutputs(GregtechItemList.SpaceTimeBendingCore.get(16))
            .noOptimize()
            .eut(RECIPE_UXV)
            .duration(12 * 120 * SECONDS)
            .addTo(IA_R);
        // endregion

        // region AE2
        // 奇点存储元件
        VA_RecipeBuilder.builder()
            .itemInputs(
                GT_ModHandler.getModItem(AppliedEnergistics2.ID, "item.ItemMultiMaterial", 12, 60),
                ItemList.Quantum_Chest_IV.get(8),
                GT_ModHandler.getModItem(Avaritia.ID, "Resource", 4, 5),
                GT_ModHandler.getModItem(EternalSingularity.ID, "eternal_singularity", 1))
            .fluidInputs(
                Materials.CosmicNeutronium.getMolten(12 * 9 * INGOTS),
                Materials.Infinity.getMolten(4 * 9 * INGOTS))
            .itemOutputs(GT_ModHandler.getModItem(AppliedEnergistics2.ID, "item.ItemExtremeStorageCell.Singularity", 1))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(4 * SECONDS)
            .addTo(IA_R);
        // 奇点流体元件
        VA_RecipeBuilder.builder()
            .itemInputs(
                GT_ModHandler.getModItem(AE2FluidCraft.ID, "fluid_part", 8, 7),
                ItemList.Quantum_Tank_IV.get(8),
                Basic.getYOTCell(7, 4),
                GT_ModHandler.getModItem(Avaritia.ID, "Resource", 4, 5),
                Basic.getSingularity(1))
            .fluidInputs(
                Materials.CosmicNeutronium.getMolten(12 * 9 * INGOTS),
                Materials.Infinity.getMolten(4 * 9 * INGOTS))
            .itemOutputs(GT_ModHandler.getModItem(AE2FluidCraft.ID, "fluid_storage.singularity", 1))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(4 * SECONDS)
            .addTo(IA_R);
        // 量子存储元件
        VA_RecipeBuilder.builder()
            .itemInputs(
                GT_ModHandler.getModItem(AppliedEnergistics2.ID, "item.ItemMultiMaterial", 1, 61),
                GT_ModHandler.getModItem(AppliedEnergistics2.ID, "item.ItemMultiMaterial", 8, 60),
                Tier.UHV.getCircuit(4))
            .fluidInputs(
                Materials.CosmicNeutronium.getMolten(4 * 9 * INGOTS),
                Materials.Neutronium.getMolten(8 * 9 * INGOTS))
            .itemOutputs(GT_ModHandler.getModItem(AppliedEnergistics2.ID, "item.ItemExtremeStorageCell.Quantum", 1))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(4 * SECONDS)
            .addTo(IA_R);
        // 量子流体元件
        VA_RecipeBuilder.builder()
            .itemInputs(
                GT_ModHandler.getModItem(AE2FluidCraft.ID, "fluid_storage_housing", 1, 1),
                GT_ModHandler.getModItem(AE2FluidCraft.ID, "fluid_part", 8, 7),
                Tier.UHV.getCircuit(4))
            .fluidInputs(
                Materials.CosmicNeutronium.getMolten(4 * 9 * INGOTS),
                Materials.Neutronium.getMolten(8 * 9 * INGOTS))
            .itemOutputs(GT_ModHandler.getModItem(AE2FluidCraft.ID, "fluid_storage.quantum", 1))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(4 * SECONDS)
            .addTo(IA_R);
        // endregion

        // region Solar Panel
        // UV
        VA_RecipeBuilder.builder()
            .itemInputs(
                ItemHelper.setStackSize(CustomItemList.PicoWafer.get(1), 4 * 64),
                ItemHelper.setStackSize(CustomItemList.RawPicoWafer.get(1), 2 * 64),
                GGChip.UEV.getItemStack(8),
                GGChip.UHV.getItemStack(8),
                ItemHelper.setStackSize(Materials.Carbon.getPlates(1), 1024),
                BWPart.Part_IC_P.getItemStack(16),
                GT_ModHandler.getModItem(SuperSolarPanels.ID, "solarsplitter", 8, 0),
                VAItemList.CrystalMatrix.get(2))
            .fluidInputs(
                Materials.SiliconSG.getMolten(64 * 12 * 9 * INGOTS),
                SCPart.UHV.getMolten(12 * 64 * INGOTS),
                Materials.ReinforceGlass.getMolten(4 * 64 * INGOTS),
                Materials.Neutronium.getMolten(256 * INGOTS))
            .itemOutputs(ItemList.Cover_SolarPanel_UV.get(64))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(32 * 8 * SECONDS)
            .addTo(IA_R);
        // endregion
    }

    public void loadTSTRecipes() {
        // Particle Trap SpaceTime Shield
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.Opt_CPUCasing.getItemStack(2),
                VAItemList.PreTesseract.get(8),
                VAItemList.AstriumInfinityGem.get(1))
            .fluidInputs(
                VAMaterials.AstriumMagic.getMolten(8 * INGOTS),
                MaterialsUEVplus.SpaceTime.getMolten(4 * INGOTS))
            .itemOutputs(GTCMItemList.ParticleTrapTimeSpaceShield.get(64))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(60 * SECONDS)
            .addTo(IA);
    }

    // region Wireless Buffed Recipes
    public void loadWirelessHatchRecipes() {

        int recipeDuration = 20 * SECONDS;
        int recipeEU = 128_000_000;

        ItemStack[] energyHatches = { ItemList.Hatch_Energy_ULV.get(1), ItemList.Hatch_Energy_LV.get(1),
            ItemList.Hatch_Energy_MV.get(1), ItemList.Hatch_Energy_HV.get(1), ItemList.Hatch_Energy_EV.get(1),
            ItemList.Hatch_Energy_IV.get(1), ItemList.Hatch_Energy_LuV.get(1), ItemList.Hatch_Energy_ZPM.get(1),
            ItemList.Hatch_Energy_UV.get(1), ItemList.Hatch_Energy_MAX.get(1),
            getItemContainer("Hatch_Energy_UEV").get(1L), getItemContainer("Hatch_Energy_UIV").get(1L),
            getItemContainer("Hatch_Energy_UMV").get(1L), getItemContainer("Hatch_Energy_UXV").get(1L) };

        ItemStack[] energyHatches_4A = { com.github.technus.tectech.thing.CustomItemList.eM_energyMulti4_EV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti4_IV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti4_LuV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti4_ZPM.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti4_UV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti4_UHV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti4_UEV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti4_UIV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti4_UMV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti4_UXV.get(1) };

        ItemStack[] energyHatches_16A = { com.github.technus.tectech.thing.CustomItemList.eM_energyMulti16_EV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti16_IV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti16_LuV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti16_ZPM.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti16_UV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti16_UHV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti16_UEV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti16_UIV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti16_UMV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti16_UXV.get(1) };

        ItemStack[] energyHatches_64A = { com.github.technus.tectech.thing.CustomItemList.eM_energyMulti64_EV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti64_IV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti64_LuV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti64_ZPM.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti64_UV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti64_UHV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti64_UEV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti64_UIV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti64_UMV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyMulti64_UXV.get(1) };

        ItemStack[] laserTargets_UXV = { com.github.technus.tectech.thing.CustomItemList.eM_energyTunnel1_UXV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyTunnel2_UXV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyTunnel3_UXV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyTunnel4_UXV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyTunnel5_UXV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyTunnel6_UXV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyTunnel7_UXV.get(1) };

        ItemStack[] dynamoHatches = { ItemList.Hatch_Dynamo_ULV.get(1), ItemList.Hatch_Dynamo_LV.get(1),
            ItemList.Hatch_Dynamo_MV.get(1), ItemList.Hatch_Dynamo_HV.get(1), ItemList.Hatch_Dynamo_EV.get(1),
            ItemList.Hatch_Dynamo_IV.get(1), ItemList.Hatch_Dynamo_LuV.get(1), ItemList.Hatch_Dynamo_ZPM.get(1),
            ItemList.Hatch_Dynamo_UV.get(1), ItemList.Hatch_Dynamo_MAX.get(1),
            getItemContainer("Hatch_Dynamo_UEV").get(1L), getItemContainer("Hatch_Dynamo_UIV").get(1L),
            getItemContainer("Hatch_Dynamo_UMV").get(1L), getItemContainer("Hatch_Dynamo_UXV").get(1L) };

        Object[] circuitsTierPlusTwo = { new Object[] { OrePrefixes.circuit.get(Materials.Good), 1L }, // MV
            new Object[] { OrePrefixes.circuit.get(Materials.Advanced), 1L }, // HV
            new Object[] { OrePrefixes.circuit.get(Materials.Data), 1L }, // EV
            new Object[] { OrePrefixes.circuit.get(Materials.Elite), 1L }, // IV
            new Object[] { OrePrefixes.circuit.get(Materials.Master), 1L }, // LuV
            new Object[] { OrePrefixes.circuit.get(Materials.Ultimate), 1L }, // ZPM
            new Object[] { OrePrefixes.circuit.get(Materials.SuperconductorUHV), 1L }, // UV
            new Object[] { OrePrefixes.circuit.get(Materials.Infinite), 1L }, // UHV
            new Object[] { OrePrefixes.circuit.get(Materials.Bio), 1L }, // UEV
            new Object[] { OrePrefixes.circuit.get(Materials.Optical), 1L }, // UIV
            new Object[] { OrePrefixes.circuit.get(Materials.Piko), 1L }, // UMV
            new Object[] { OrePrefixes.circuit.get(Materials.Quantum), 1L }, // UXV
            new Object[] { OrePrefixes.circuit.get(Materials.Quantum), 4L }, // MAX
            new Object[] { OrePrefixes.circuit.get(Materials.Quantum), 16L } // MAX
        };

        ItemStack[] wirelessHatches = { ItemList.Wireless_Hatch_Energy_ULV.get(1),
            ItemList.Wireless_Hatch_Energy_LV.get(1), ItemList.Wireless_Hatch_Energy_MV.get(1),
            ItemList.Wireless_Hatch_Energy_HV.get(1), ItemList.Wireless_Hatch_Energy_EV.get(1),
            ItemList.Wireless_Hatch_Energy_IV.get(1), ItemList.Wireless_Hatch_Energy_LuV.get(1),
            ItemList.Wireless_Hatch_Energy_ZPM.get(1), ItemList.Wireless_Hatch_Energy_UV.get(1),
            ItemList.Wireless_Hatch_Energy_UHV.get(1), ItemList.Wireless_Hatch_Energy_UEV.get(1),
            ItemList.Wireless_Hatch_Energy_UIV.get(1), ItemList.Wireless_Hatch_Energy_UMV.get(1),
            ItemList.Wireless_Hatch_Energy_UXV.get(1) };

        ItemStack[] wirelessHatches_4A = {
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti4_EV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti4_IV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti4_LuV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti4_ZPM.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti4_UV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti4_UHV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti4_UEV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti4_UIV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti4_UMV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti4_UXV.get(1) };

        ItemStack[] wirelessHatches_16A = {
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti16_EV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti16_IV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti16_LuV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti16_ZPM.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti16_UV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti16_UHV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti16_UEV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti16_UIV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti16_UMV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti16_UXV.get(1) };

        ItemStack[] wirelessHatches_64A = {
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti64_EV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti64_IV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti64_LuV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti64_ZPM.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti64_UV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti64_UHV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti64_UEV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti64_UIV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti64_UMV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessMulti64_UXV.get(1) };

        ItemStack[] wirelessLasers = {
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessTunnel1_UXV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessTunnel2_UXV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessTunnel3_UXV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessTunnel4_UXV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessTunnel5_UXV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessTunnel6_UXV.get(1),
            com.github.technus.tectech.thing.CustomItemList.eM_energyWirelessTunnel7_UXV.get(1) };

        ItemStack[] wirelessDynamos = { ItemList.Wireless_Dynamo_Energy_ULV.get(1),
            ItemList.Wireless_Dynamo_Energy_LV.get(1), ItemList.Wireless_Dynamo_Energy_MV.get(1),
            ItemList.Wireless_Dynamo_Energy_HV.get(1), ItemList.Wireless_Dynamo_Energy_EV.get(1),
            ItemList.Wireless_Dynamo_Energy_IV.get(1), ItemList.Wireless_Dynamo_Energy_LuV.get(1),
            ItemList.Wireless_Dynamo_Energy_ZPM.get(1), ItemList.Wireless_Dynamo_Energy_UV.get(1),
            ItemList.Wireless_Dynamo_Energy_UHV.get(1), ItemList.Wireless_Dynamo_Energy_UEV.get(1),
            ItemList.Wireless_Dynamo_Energy_UIV.get(1), ItemList.Wireless_Dynamo_Energy_UMV.get(1),
            ItemList.Wireless_Dynamo_Energy_UXV.get(1) };

        // ------------------------ Wireless EU hatches ------------------------

        for (int i = 0; i < wirelessHatches.length; i++) {
            GT_Values.RA.stdBuilder()
                .itemInputs(
                    energyHatches[i],
                    ItemRefer.Compact_Fusion_Coil_T0.get(1),
                    ItemList.Casing_Coil_Superconductor.get(1),
                    com.github.technus.tectech.thing.CustomItemList.Machine_Multi_Transformer.get(1),
                    com.github.technus.tectech.thing.CustomItemList.eM_Power.get(2),
                    circuitsTierPlusTwo[i],
                    ItemList.EnergisedTesseract.get(1))
                .fluidInputs(
                    SolderMaterial.MutatedLivingAlloy.getFluidStack(9 * INGOTS),
                    Materials.Infinity.getMolten(8 * INGOTS),
                    MaterialsUEVplus.SpaceTime.getMolten(72),
                    MaterialsUEVplus.ExcitedDTEC.getFluid(500))
                .itemOutputs(wirelessHatches[i])
                .eut(recipeEU)
                .duration(recipeDuration)
                .addTo(IA);
        }

        // ------------------------ 4A Wireless EU hatches ------------------------

        for (int i = 0; i < wirelessHatches_4A.length; i++) {
            GT_Values.RA.stdBuilder()
                .itemInputs(
                    energyHatches_4A[i],
                    ItemRefer.Compact_Fusion_Coil_T1.get(1),
                    ItemList.Casing_Coil_Superconductor.get(1),
                    com.github.technus.tectech.thing.CustomItemList.Machine_Multi_Transformer.get(1),
                    com.github.technus.tectech.thing.CustomItemList.eM_Power.get(4),
                    circuitsTierPlusTwo[i + 4],
                    ItemList.EnergisedTesseract.get(1))
                .fluidInputs(
                    SolderMaterial.MutatedLivingAlloy.getFluidStack(4 * 9 * INGOTS),
                    Materials.Flerovium.getMolten(32 * INGOTS),
                    MyMaterial.shirabon.getMolten(12 * INGOTS),
                    MaterialsUEVplus.SpaceTime.getMolten(2 * INGOTS),
                    MaterialsUEVplus.ExcitedDTEC.getFluid(4 * 500))
                .itemOutputs(wirelessHatches_4A[i])
                .eut(recipeEU)
                .duration(recipeDuration)
                .addTo(IA);
        }

        // ------------------------ 16A Wireless EU hatches ------------------------

        for (int i = 0; i < wirelessHatches_16A.length; i++) {
            GT_Values.RA.stdBuilder()
                .itemInputs(
                    energyHatches_16A[i],
                    ItemRefer.Compact_Fusion_Coil_T2.get(1),
                    ItemList.Casing_Coil_Superconductor.get(1),
                    com.github.technus.tectech.thing.CustomItemList.Machine_Multi_Transformer.get(1),
                    com.github.technus.tectech.thing.CustomItemList.eM_Power.get(16),
                    circuitsTierPlusTwo[i + 4],
                    ItemList.EnergisedTesseract.get(1))
                .fluidInputs(
                    SolderMaterial.MutatedLivingAlloy.getFluidStack(16 * 9 * INGOTS),
                    MyMaterial.shirabon.getMolten(48 * INGOTS),
                    MaterialsUEVplus.TranscendentMetal.getMolten(32 * INGOTS),
                    MaterialsUEVplus.SpaceTime.getMolten(8 * INGOTS),
                    MaterialsUEVplus.ExcitedDTEC.getFluid(16 * 500))
                .itemOutputs(wirelessHatches_16A[i])
                .eut(recipeEU)
                .duration(recipeDuration)
                .addTo(IA);
        }

        // ------------------------ 64A Wireless EU hatches ------------------------

        for (int i = 0; i < wirelessHatches_64A.length; i++) {
            GT_Values.RA.stdBuilder()
                .itemInputs(
                    energyHatches_64A[i],
                    ItemRefer.Compact_Fusion_Coil_T3.get(1),
                    ItemList.Casing_Coil_Superconductor.get(1),
                    com.github.technus.tectech.thing.CustomItemList.Machine_Multi_Transformer.get(1),
                    com.github.technus.tectech.thing.CustomItemList.eM_Power.get(64),
                    circuitsTierPlusTwo[i + 4],
                    ItemList.EnergisedTesseract.get(1))
                .fluidInputs(
                    SolderMaterial.MutatedLivingAlloy.getFluidStack(64 * 9 * INGOTS),
                    MyMaterial.shirabon.getMolten(192 * INGOTS),
                    MyMaterial.metastableOganesson.getMolten(32 * INGOTS),
                    MaterialsUEVplus.SpaceTime.getMolten(32 * INGOTS),
                    MaterialsUEVplus.ExcitedDTEC.getFluid(64 * 500))
                .itemOutputs(wirelessHatches_64A[i])
                .eut(recipeEU)
                .duration(recipeDuration)
                .addTo(IA);
        }

        // ------------------------ Wireless UXV Lasers ------------------------

        for (int i = 0; i < wirelessLasers.length; i++) {
            GT_Values.RA.stdBuilder()
                .itemInputs(
                    laserTargets_UXV[i],
                    ItemRefer.Compact_Fusion_Coil_T4.get(1),
                    com.github.technus.tectech.thing.CustomItemList.LASERpipeBlock.get(64),
                    com.github.technus.tectech.thing.CustomItemList.Machine_Multi_Transformer.get(1),
                    com.github.technus.tectech.thing.CustomItemList.eM_Power.get(64),
                    GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Quantum, 16L),
                    ItemList.EnergisedTesseract.get(1))
                .fluidInputs(
                    SolderMaterial.MutatedLivingAlloy.getFluidStack(256 * 9 * INGOTS),
                    MaterialsUEVplus.Eternity.getMolten(32 * 8 * INGOTS),
                    MaterialsUEVplus.MagnetohydrodynamicallyConstrainedStarMatter.getMolten(16 * 8 * INGOTS),
                    MaterialsUEVplus.SpaceTime.getMolten(512 * INGOTS),
                    MaterialsUEVplus.ExcitedDTSC.getFluid(64 * 500))
                .itemOutputs(wirelessLasers[i])
                .eut(recipeEU)
                .duration(recipeDuration)
                .addTo(IA);
        }

        // ------------------------ Wireless EU dynamos ------------------------

        for (int i = 0; i < wirelessHatches.length; i++) {
            GT_Values.RA.stdBuilder()
                .itemInputs(
                    dynamoHatches[i],
                    ItemRefer.Compact_Fusion_Coil_T0.get(1),
                    ItemList.Casing_Coil_Superconductor.get(1),
                    com.github.technus.tectech.thing.CustomItemList.Machine_Multi_Transformer.get(1),
                    com.github.technus.tectech.thing.CustomItemList.eM_Power.get(2),
                    circuitsTierPlusTwo[i],
                    ItemList.Tesseract.get(1))
                .fluidInputs(
                    SolderMaterial.MutatedLivingAlloy.getFluidStack(9 * INGOTS),
                    Materials.Infinity.getMolten(8 * INGOTS),
                    MaterialsUEVplus.SpaceTime.getMolten(72),
                    MaterialsUEVplus.ExcitedDTEC.getFluid(500))
                .itemOutputs(wirelessDynamos[i])
                .eut(recipeEU)
                .duration(recipeDuration)
                .addTo(IA);
        }
    }
}
