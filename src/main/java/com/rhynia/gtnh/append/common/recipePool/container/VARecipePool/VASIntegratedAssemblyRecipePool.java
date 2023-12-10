package com.rhynia.gtnh.append.common.recipePool.container.VARecipePool;

import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Adv_Capacitor;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Adv_Diode;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Adv_Resistor;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Adv_Transistor;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Bio_Board;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Delicate_Board;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Elite_Board;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Opt_Board;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Opt_Capacitor;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Opt_Diode;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Opt_Inductor;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Opt_Resistor;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Opt_Transistor;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Part_ASOC;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Part_IC;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Part_IC_H;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Part_IC_N;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Part_IC_P;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Part_IC_Q;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Part_IC_UH;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Part_ILC;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Part_NanoCPU;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Part_QBit;
import static com.rhynia.gtnh.append.api.util.recipeHelper.BWPart.WrappedPart.Wetware_Board;
import static com.rhynia.gtnh.append.api.util.recipeHelper.GGChip.Tier.EV;
import static com.rhynia.gtnh.append.api.util.recipeHelper.GGChip.Tier.IV;
import static com.rhynia.gtnh.append.api.util.recipeHelper.GGChip.Tier.LuV;
import static com.rhynia.gtnh.append.api.util.recipeHelper.GGChip.Tier.UEV;
import static com.rhynia.gtnh.append.api.util.recipeHelper.GGChip.Tier.UHV;
import static com.rhynia.gtnh.append.api.util.recipeHelper.GGChip.Tier.UIV;
import static com.rhynia.gtnh.append.api.util.recipeHelper.GGChip.Tier.UMV;
import static com.rhynia.gtnh.append.api.util.recipeHelper.GGChip.Tier.UV;
import static com.rhynia.gtnh.append.api.util.recipeHelper.GGChip.Tier.UXV;
import static com.rhynia.gtnh.append.api.util.recipeHelper.GGChip.Tier.ZPM;
import static com.rhynia.gtnh.append.api.util.recipeHelper.SolderMaterial.Solder.IndaAlloy;
import static com.rhynia.gtnh.append.api.util.recipeHelper.SolderMaterial.Solder.MutatedLivingAlloy;
import static com.rhynia.gtnh.append.api.util.recipeHelper.SolderMaterial.Solder.SolderingAlloy;
import static gregtech.api.enums.TierEU.RECIPE_EV;
import static gregtech.api.enums.TierEU.RECIPE_IV;
import static gregtech.api.enums.TierEU.RECIPE_LuV;
import static gregtech.api.enums.TierEU.RECIPE_MAX;
import static gregtech.api.enums.TierEU.RECIPE_UEV;
import static gregtech.api.enums.TierEU.RECIPE_UHV;
import static gregtech.api.enums.TierEU.RECIPE_UIV;
import static gregtech.api.enums.TierEU.RECIPE_UMV;
import static gregtech.api.enums.TierEU.RECIPE_UV;
import static gregtech.api.enums.TierEU.RECIPE_ZPM;
import static gregtech.api.util.GT_RecipeBuilder.BUCKETS;
import static gregtech.api.util.GT_RecipeBuilder.HOURS;
import static gregtech.api.util.GT_RecipeBuilder.INGOTS;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;
import static gregtech.api.util.GT_RecipeBuilder.TICKS;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.dreammaster.gthandler.CustomItemList;
import com.rhynia.gtnh.append.api.recipe.VA_Recipe;
import com.rhynia.gtnh.append.api.util.recipeHelper.BWPart;
import com.rhynia.gtnh.append.api.util.recipeHelper.GGChip;
import com.rhynia.gtnh.append.api.util.recipeHelper.SolderMaterial;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.common.recipePool.IRecipePool;

import goodgenerator.items.MyMaterial;
import goodgenerator.util.ItemRefer;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.core.material.ALLOY;
import gtPlusPlus.core.material.ELEMENT;
import gtPlusPlus.xmod.gregtech.api.enums.GregtechItemList;

public class VASIntegratedAssemblyRecipePool implements IRecipePool {

    private final GT_Recipe.GT_Recipe_Map IA = VA_Recipe.instance.sIntegratedAssemblyRecipes;

    @Override
    public void loadRecipesPostInit() {
        // 终极配方-测试
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 64),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 64),
                VA_WerkstoffMaterialPool.Primogem.get(OrePrefixes.lens, 64),
                VA_WerkstoffMaterialPool.Originiums.get(OrePrefixes.lens, 64),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 64),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 64),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.AstroInf.getGems(64),
                VA_GregtechMaterialPool.AstroInf.getGems(64),
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
    public void loadRecipes() {
        // region MISC
        // 光辉玻璃板
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Sunnarium.getPlates(16))
            .fluidInputs(
                Materials.Glass.getMolten(72 * INGOTS),
                Materials.Glowstone.getMolten(16 * INGOTS),
                Materials.Uranium.getMolten(16 * INGOTS))
            .itemOutputs(GT_ModHandler.getModItem("AdvancedSolarPanel", "asp_crafting_items", 64, 5))
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(20 * SECONDS)
            .addTo(IA);
        // endregion

        // region 电池配方
        // 兰波顿能量球 IV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.getWrappedPart(Delicate_Board, 1),
                BWPart.getWrappedPart(Part_IC, 4),
                BWPart.getWrappedPart(Part_NanoCPU, 2))
            .fluidInputs(
                SolderMaterial.getSolder(SolderingAlloy, 16 * INGOTS),
                Materials.Platinum.getMolten(160 * INGOTS),
                VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(6400))
            .itemOutputs(ItemList.Energy_LapotronicOrb.get(16))
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(12 * (25 * SECONDS + 12 * TICKS))
            .addTo(IA);
        // 兰波顿能量簇 LUV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.getWrappedPart(Delicate_Board, 1),
                GT_Utility.copyAmountUnsafe(128, ItemList.Energy_LapotronicOrb.get(1)),
                BWPart.getWrappedPart(Part_IC_H, 4),
                BWPart.getWrappedPart(Part_QBit, 2))
            .fluidInputs(
                SolderMaterial.getSolder(IndaAlloy, 16 * 5 * INGOTS),
                Materials.NiobiumTitanium.getMolten(16 * 2 * INGOTS),
                Materials.NaquadahAlloy.getMolten(16 * 16 * INGOTS))
            .itemOutputs(ItemList.Energy_LapotronicOrb2.get(16))
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(12 * (51 * SECONDS + 4 * TICKS))
            .addTo(IA);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.getWrappedPart(Elite_Board, 1),
                GGChip.getWrappedCircuit(LuV, 4),
                BWPart.getWrappedPart(Part_IC_H, 64),
                BWPart.getWrappedPart(Adv_Diode, 8),
                BWPart.getWrappedPart(Adv_Capacitor, 8),
                BWPart.getWrappedPart(Adv_Resistor, 8),
                BWPart.getWrappedPart(Adv_Transistor, 8))
            .fluidInputs(
                SolderMaterial.getSolder(IndaAlloy, 16 * 5 * INGOTS),
                Materials.NaquadahAlloy.getMolten(16 * 16 * INGOTS),
                Materials.Platinum.getMolten(16 * 8 * INGOTS),
                VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(16 * 800))
            .itemOutputs(ItemList.Energy_LapotronicOrb2.get(16))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(12 * 50 * SECONDS)
            .addTo(IA);
        // 能量模块 ZPM
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.getWrappedCircuit(ZPM, 4),
                GT_Utility.copyAmountUnsafe(128, ItemList.Energy_LapotronicOrb2.get(1)),
                ItemList.Field_Generator_LuV.get(32),
                GT_Utility.copyAmountUnsafe(6 * 64, BWPart.getWrappedPart(Part_ASOC, 1)),
                BWPart.getWrappedPart(Adv_Transistor, 8))
            .fluidInputs(
                SolderMaterial.getSolder(IndaAlloy, 16 * 20 * INGOTS),
                Materials.Europium.getMolten(16 * 16 * INGOTS),
                Materials.Naquadah.getMolten(16 * 16 * INGOTS),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 16 * 16000))
            .itemOutputs(ItemList.Energy_Module.get(16))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(12 * 100 * SECONDS)
            .addTo(IA);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.getWrappedPart(Wetware_Board, 1),
                GGChip.getWrappedCircuit(ZPM, 4),
                BWPart.getWrappedPart(Part_IC_UH, 64),
                BWPart.getWrappedPart(Opt_Diode, 8),
                BWPart.getWrappedPart(Opt_Capacitor, 8),
                BWPart.getWrappedPart(Opt_Resistor, 8),
                BWPart.getWrappedPart(Opt_Transistor, 8))
            .fluidInputs(
                SolderMaterial.getSolder(MutatedLivingAlloy, 16 * 5 * INGOTS),
                Materials.Bedrockium.getMolten(16 * 16 * INGOTS),
                ELEMENT.STANDALONE.HYPOGEN.getFluidStack(16 * 6 * INGOTS),
                VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(16 * 1000))
            .itemOutputs(ItemList.Energy_Module.get(16))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * 50 * SECONDS)
            .addTo(IA);
        // 能量簇 UV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.getWrappedCircuit(UV, 4),
                GT_Utility.copyAmountUnsafe(128, ItemList.Energy_Module.get(1)),
                ItemList.Field_Generator_ZPM.get(32),
                BWPart.getWrappedPart(Part_IC_H, 256),
                BWPart.getWrappedPart(Adv_Diode, 16))
            .fluidInputs(
                SolderMaterial.getSolder(IndaAlloy, 16 * 20 * INGOTS),
                Materials.Americium.getMolten(16 * 16 * INGOTS),
                Materials.NaquadahAlloy.getMolten(16 * 16 * INGOTS),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 16 * 16000))
            .itemOutputs(ItemList.Energy_Cluster.get(16))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * 100 * SECONDS)
            .addTo(IA);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.getWrappedPart(Bio_Board, 1),
                GGChip.getWrappedCircuit(UV, 4),
                BWPart.getWrappedPart(Part_IC_N, 64),
                BWPart.getWrappedPart(Opt_Diode, 32),
                BWPart.getWrappedPart(Opt_Capacitor, 32),
                BWPart.getWrappedPart(Opt_Resistor, 32),
                BWPart.getWrappedPart(Opt_Transistor, 32))
            .fluidInputs(
                SolderMaterial.getSolder(MutatedLivingAlloy, 16 * 10 * INGOTS),
                Materials.CosmicNeutronium.getMolten(16 * 16 * INGOTS),
                MaterialsUEVplus.SpaceTime.getMolten(16 * 6 * INGOTS),
                VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(16 * 2000))
            .itemOutputs(ItemList.Energy_Cluster.get(16))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * 50 * SECONDS)
            .addTo(IA);
        // 终极电池 UV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.getWrappedCircuit(UHV, 4),
                GT_Utility.copyAmountUnsafe(128, ItemList.Energy_Cluster.get(1)),
                ItemList.Field_Generator_UV.get(32),
                BWPart.getWrappedPart(Part_IC_H, 256),
                BWPart.getWrappedPart(Adv_Diode, 32))
            .fluidInputs(
                SolderMaterial.getSolder(IndaAlloy, 16 * 20 * INGOTS),
                Materials.Tritanium.getMolten(16 * 64 * INGOTS),
                VA_WerkstoffMaterialPool.superconductingFlux.getFluidOrGas(16 * 4 * INGOTS),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 16 * 16000))
            .itemOutputs(ItemList.ZPM2.get(16))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * 150 * SECONDS)
            .addTo(IA);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                BWPart.getWrappedPart(Opt_Board, 1),
                GGChip.getWrappedCircuit(UHV, 4),
                BWPart.getWrappedPart(Part_IC_P, 64),
                BWPart.getWrappedPart(Opt_Diode, 64),
                BWPart.getWrappedPart(Opt_Capacitor, 64),
                BWPart.getWrappedPart(Opt_Resistor, 64),
                BWPart.getWrappedPart(Opt_Transistor, 64))
            .fluidInputs(
                SolderMaterial.getSolder(MutatedLivingAlloy, 16 * 20 * INGOTS),
                MyMaterial.shirabon.getMolten(16 * 16 * INGOTS),
                MaterialsUEVplus.WhiteDwarfMatter.getMolten(16 * 4 * INGOTS),
                MaterialsUEVplus.BlackDwarfMatter.getMolten(16 * 4 * INGOTS),
                MaterialsUEVplus.MagnetohydrodynamicallyConstrainedStarMatter.getMolten(8 * INGOTS),
                VA_WerkstoffMaterialPool.lapoActivatedFluid.getFluidOrGas(16 * 5000))
            .itemOutputs(ItemList.ZPM2.get(16))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 50 * SECONDS)
            .addTo(IA);
        // 真·终极电池 UMV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.getWrappedCircuit(UEV, 4),
                GT_Utility.copyAmountUnsafe(128, ItemList.ZPM2.get(1)),
                ItemList.Field_Generator_UHV.get(64),
                BWPart.getWrappedPart(Part_IC_UH, 256),
                BWPart.getWrappedPart(Part_ASOC, 3 * 64),
                BWPart.getWrappedPart(Adv_Diode, 64))
            .fluidInputs(
                SolderMaterial.getSolder(MutatedLivingAlloy, 16 * 4608),
                Materials.Neutronium.getMolten(16 * 128 * INGOTS),
                Materials.Naquadria.getMolten(16 * 9216),
                VA_WerkstoffMaterialPool.superconductingFlux.getFluidOrGas(16 * 16 * INGOTS),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 16 * 32000))
            .itemOutputs(ItemList.ZPM3.get(16))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(12 * 200 * SECONDS)
            .addTo(IA);
        // 极·终极电池 UXV
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.getWrappedCircuit(UIV, 4),
                GT_Utility.copyAmountUnsafe(128, ItemList.ZPM3.get(1)),
                ItemList.Field_Generator_UEV.get(64),
                BWPart.getWrappedPart(Part_IC_P, 256),
                BWPart.getWrappedPart(Part_ASOC, 6 * 64),
                BWPart.getWrappedPart(Opt_Diode, 64))
            .fluidInputs(
                SolderMaterial.getSolder(MutatedLivingAlloy, 16 * 9216),
                Materials.InfinityCatalyst.getMolten(16 * 128 * INGOTS),
                Materials.Quantium.getMolten(16 * 18432),
                Materials.Naquadria.getMolten(16 * 18432),
                VA_WerkstoffMaterialPool.superconductingFlux.getFluidOrGas(16 * 64 * INGOTS),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 16 * 64000))
            .itemOutputs(ItemList.ZPM4.get(16))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(12 * 250 * SECONDS)
            .addTo(IA);
        // 狂·终极电池 MAX
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.getWrappedCircuit(UMV, 4),
                GT_Utility.copyAmountUnsafe(128, ItemList.ZPM4.get(1)),
                ItemList.Field_Generator_UIV.get(64),
                BWPart.getWrappedPart(Part_IC_Q, 256),
                GT_Utility.copyAmountUnsafe(16 * 64, CustomItemList.RawPicoWafer.get(1)),
                BWPart.getWrappedPart(Opt_Diode, 64),
                BWPart.getWrappedPart(Opt_Inductor, 32))
            .fluidInputs(
                SolderMaterial.getSolder(MutatedLivingAlloy, 16 * 18432),
                ELEMENT.STANDALONE.HYPOGEN.getFluidStack(16 * 128 * INGOTS),
                ELEMENT.STANDALONE.CELESTIAL_TUNGSTEN.getFluidStack(18_432),
                Materials.Quantium.getMolten(16 * 18432),
                VA_WerkstoffMaterialPool.superconductingFlux.getFluidOrGas(16 * 128 * INGOTS),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 16 * 128000))
            .itemOutputs(ItemList.ZPM5.get(16))
            .noOptimize()
            .eut(RECIPE_UIV)
            .duration(12 * 300 * SECONDS)
            .addTo(IA);
        // 太·终极电池 ER
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GGChip.getWrappedCircuit(UXV, 4),
                GT_Utility.copyAmountUnsafe(128, ItemList.ZPM5.get(1)),
                ItemList.Field_Generator_UMV.get(64),
                BWPart.getWrappedPart(Part_IC_Q, 256),
                GT_Utility.copyAmountUnsafe(16 * 64, CustomItemList.RawPicoWafer.get(1)),
                BWPart.getWrappedPart(Opt_Diode, 64),
                BWPart.getWrappedPart(Opt_Inductor, 64))
            .fluidInputs(
                SolderMaterial.getSolder(MutatedLivingAlloy, 16 * 36864),
                ELEMENT.STANDALONE.DRAGON_METAL.getFluidStack(16 * 128 * INGOTS),
                ELEMENT.STANDALONE.ASTRAL_TITANIUM.getFluidStack(16 * 36864),
                ELEMENT.STANDALONE.CELESTIAL_TUNGSTEN.getFluidStack(16 * 36864),
                VA_WerkstoffMaterialPool.superconductingFlux.getFluidOrGas(16 * 256 * INGOTS),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 16 * 256000))
            .itemOutputs(ItemList.ZPM6.get(16))
            .noOptimize()
            .eut(RECIPE_UMV)
            .duration(12 * 350 * SECONDS)
            .addTo(IA);
        // endregion

        // region 杂项组装
        // Magic 透镜
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_GregtechMaterialPool.AstroMagic.getGems(64))
            .fluidInputs(
                SolderMaterial.getSolder(MutatedLivingAlloy, 32 * INGOTS),
                Materials.DraconiumAwakened.getMolten(48 * INGOTS),
                VA_GregtechMaterialPool.Astro.getFluid(64000))
            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 1))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(810 * SECONDS)
            .addTo(IA);
        // Inf 透镜
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_GregtechMaterialPool.AstroInf.getGems(64))
            .fluidInputs(
                SolderMaterial.getSolder(MutatedLivingAlloy, 32 * INGOTS),
                Materials.CosmicNeutronium.getMolten(256 * INGOTS),
                Materials.DraconiumAwakened.getMolten(256 * INGOTS),
                VA_GregtechMaterialPool.Astro.getFluid(128000),
                VA_GregtechMaterialPool.AstroInf.getFluid(48000))
            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 1))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(900 * SECONDS)
            .addTo(IA);
        // endregion

        // region 防辐射板
        // 防辐射板 64x
        GT_Values.RA.stdBuilder()
            .itemInputs(
                Materials.NaquadahAlloy.getPlates(64),
                Materials.NaquadahAlloy.getPlates(64),
                Materials.NaquadahAlloy.getPlates(64),
                Materials.NaquadahAlloy.getPlates(64),
                Materials.NaquadahAlloy.getPlates(64),
                Materials.NaquadahAlloy.getPlates(64),
                Materials.NaquadahAlloy.getPlates(64),
                Materials.NaquadahAlloy.getPlates(64),
                Materials.Lanthanum.getPlates(64),
                Materials.Lanthanum.getPlates(64),
                Materials.Lanthanum.getPlates(64),
                Materials.Lanthanum.getPlates(64))
            .fluidInputs(Materials.Lead.getMolten(64 * 8 * INGOTS))
            .itemOutputs(ItemRefer.Radiation_Protection_Plate.get(64))
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(12 * 4 * 20 * SECONDS)
            .addTo(IA);
        // 进阶防辐射板 16x
        GT_Values.RA.stdBuilder()
            .itemInputs(
                Materials.ElectrumFlux.getPlates(64),
                Materials.ElectrumFlux.getPlates(64),
                Materials.Trinium.getPlates(64),
                Materials.Trinium.getPlates(64),
                Materials.Osmiridium.getPlates(64),
                Materials.Osmiridium.getPlates(64),
                Materials.VibrantAlloy.getPlates(64),
                Materials.VibrantAlloy.getPlates(64))
            .fluidInputs(
                SolderMaterial.getSolder(IndaAlloy, 16 * 16 * INGOTS),
                Materials.NaquadahAlloy.getMolten(10 * 64 * INGOTS),
                Materials.Lanthanum.getMolten(4 * 64 * INGOTS),
                Materials.Lead.getMolten(32 * 16 * INGOTS))
            .itemOutputs(ItemRefer.Advanced_Radiation_Protection_Plate.get(32))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(12 * 100 * SECONDS)
            .addTo(IA);
        // endregion

        // region 光学
        // 光学内存
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_ModHandler.getModItem("OpenComputers", "item", 64, 39),
                GT_ModHandler.getModItem("gregtech", "gt.metaitem.03", 64, 32724),
                GT_ModHandler.getModItem("gregtech", "gt.blockmachines", 64, 15470),
                GT_ModHandler.getModItem("gregtech", "gt.blockmachines", 64, 15470),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.InfinityCatalyst, 64),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.InfinityCatalyst, 64))
            .fluidInputs(
                Materials.Infinity.getMolten(64 * INGOTS),
                SolderMaterial.getSolder(MutatedLivingAlloy, 96 * INGOTS))
            .itemOutputs(
                GT_Utility.copyAmountUnsafe(128, GT_ModHandler.getModItem("gregtech", "gt.metaitem.03", 1, 32725)))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(80 * SECONDS)
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
                GGChip.getWrappedCircuit(UHV, 1),
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
                GGChip.getWrappedCircuit(UEV, 1),
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
                GGChip.getWrappedCircuit(UIV, 1),
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
                GGChip.getWrappedCircuit(LuV, 4),
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
                GGChip.getWrappedCircuit(LuV, 16),
                GGChip.getWrappedCircuit(UV, 8),
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
                GGChip.getWrappedCircuit(ZPM, 16),
                GGChip.getWrappedCircuit(UHV, 8),
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
                BWPart.getWrappedPart(Part_ILC, 8),
                BWPart.getWrappedPart(Part_IC, 1),
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
                GGChip.getWrappedCircuit(UEV, 1),
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
                GGChip.getWrappedCircuit(EV, 16),
                GGChip.getWrappedCircuit(IV, 8),
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
                GGChip.getWrappedCircuit(IV, 16),
                GGChip.getWrappedCircuit(LuV, 8),
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
    }
}
