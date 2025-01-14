package com.rhynia.gtnh.append.common.recipe.VARecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.BUCKETS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.FullChance;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_EV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_HV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_IV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_LuV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UEV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UHV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UXV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_ZPM;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.dreammaster.gthandler.CustomItemList;
import com.elisis.gtnhlanth.common.register.WerkstoffMaterialPool;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.common.loader.VA_ItemList;
import com.rhynia.gtnh.append.common.material.VA_Materials;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.core.item.ModItems;
import gtPlusPlus.core.material.MISC_MATERIALS;
import gtPlusPlus.core.util.minecraft.ItemUtils;

public class VASAstraForgeRecipePool implements IRecipePool {

    private final IRecipeMap AF = AppendRecipeMaps.astralForgeRecipes;

    // region ItemStack
    private final ItemStack sand = new ItemStack(Blocks.sand, 64);
    private final ItemStack soulSand = new ItemStack(Blocks.soul_sand, 64, 0);
    private final ItemStack blazePowder = new ItemStack(Items.blaze_powder, 64, 0);
    private final ItemStack enderPearl = new ItemStack(Items.ender_pearl, 64, 0);
    // endregion

    @Override
    public void loadRecipesPostInit() {
        // region 10号电路 增殖
        // UU增殖
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64))
            .fluidInputs(Materials.UUMatter.getFluid(2048))
            .fluidOutputs(Materials.UUMatter.getFluid(32768))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(40 * SECONDS)
            .addTo(AF);
        // Enteral
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                VA_ItemList.LensAstriumInfinity.get(0),
                VA_ItemList.AstriumInfinityGem.get(4))
            .fluidInputs(
                MaterialsUEVplus.DimensionallyTranscendentResidue.getFluid(4 * BUCKETS),
                MaterialsUEVplus.SpaceTime.getMolten(4 * INGOTS))
            .itemOutputs(MyMaterial.shirabon.get(OrePrefixes.dust, 64), ItemList.Timepiece.get(4))
            .outputChances(8000, 6000)
            .fluidOutputs(
                MaterialsUEVplus.Eternity.getMolten(8 * INGOTS),
                MaterialsUEVplus.PrimordialMatter.getFluid(2 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UXV)
            .duration(10 * SECONDS)
            .addTo(AF);
        // endregion
    }

    @Override
    public void loadRecipesCompleteInit() {
        // region 12号电路
        // 润滑油
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                VA_ItemList.LensAstriumMagic.get(0),
                VA_Materials.Astrium.get(OrePrefixes.dust, 16),
                Materials.Redstone.getDust(1))
            .fluidInputs(Materials.Water.getFluid(32 * BUCKETS))
            .fluidOutputs(Materials.Lubricant.getFluid(256 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(12 * SECONDS)
            .addTo(AF);
        // 不稳定金属
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                VA_ItemList.LensAstriumMagic.get(0),
                Materials.Iron.getDust(64),
                Materials.Iron.getDust(64),
                Materials.Diamond.getDust(64),
                Materials.Diamond.getDust(64))
            .itemOutputs(Materials.Unstable.getDust(64), Materials.Unstable.getDust(64))
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(20 * SECONDS)
            .addTo(AF);
        // 星辉催化剂
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                VA_ItemList.LensAstriumMagic.get(0),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.AstralCatalystBase.get(OrePrefixes.dust, 16))
            .itemOutputs(VA_Materials.AstriumInfinity.get(OrePrefixes.dust, 1))
            .fluidOutputs(VA_Materials.AstralCatalystBase.getFluidOrGas(48 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(20 * SECONDS)
            .addTo(AF);
        // endregion

        // region 11号电路 魔法合成
        // 灵魂沙
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                VA_ItemList.LensAstriumInfinity.get(0),
                new ItemStack(Items.blaze_powder, 4, 0),
                Materials.Sulfur.getDust(16),
                sand,
                sand,
                sand,
                sand,
                sand,
                sand,
                sand,
                sand)
            .fluidInputs(Materials.Water.getFluid(1000), VA_Materials.Astrium.getMolten(16))
            .itemOutputs(soulSand, soulSand, soulSand, soulSand, soulSand, soulSand, soulSand, soulSand)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(18 * SECONDS)
            .addTo(AF);
        // 烈焰粉
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                VA_ItemList.LensAstriumInfinity.get(0),
                Materials.Carbon.getDust(64),
                Materials.Sulfur.getDust(64))
            .fluidInputs(Materials.Lava.getFluid(1000), VA_Materials.Astrium.getMolten(16))
            .itemOutputs(blazePowder, blazePowder, blazePowder, blazePowder)
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(18 * SECONDS)
            .addTo(AF);
        // 末影珍珠
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                VA_ItemList.LensAstriumMagic.get(0),
                GT_ModHandler.getModItem("HardcoreEnderExpansion", "end_powder", 16),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64))
            .fluidInputs(VA_Materials.Astrium.getMolten(16))
            .itemOutputs(enderPearl, enderPearl, enderPearl, enderPearl)
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(18 * SECONDS)
            .addTo(AF);
        // 胶质云母
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                VA_ItemList.LensAstriumMagic.get(0),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                Materials.Mica.getDust(64),
                Materials.Mica.getDust(64),
                Materials.Mica.getDust(64),
                Materials.Mica.getDust(64))
            .fluidInputs(VA_Materials.Astrium.getMolten(1000))
            .itemOutputs(
                CustomItemList.MicaBasedPulp.get(64),
                CustomItemList.MicaBasedPulp.get(64),
                CustomItemList.MicaBasedPulp.get(64),
                CustomItemList.MicaBasedPulp.get(64),
                CustomItemList.MicaBasedPulp.get(64),
                CustomItemList.MicaBasedPulp.get(64),
                CustomItemList.MicaBasedPulp.get(64),
                CustomItemList.MicaBasedPulp.get(64),
                CustomItemList.MicaBasedPulp.get(64),
                CustomItemList.MicaBasedPulp.get(64),
                CustomItemList.MicaBasedPulp.get(64),
                CustomItemList.MicaBasedPulp.get(64))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(20 * SECONDS)
            .addTo(AF);
        // endregion

        // region 9号电路 分离
        // 净化水
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(9),
                VA_ItemList.LensAstriumInfinity.get(0),
                VA_ItemList.LensAstriumMagic.get(0),
                WerkstoffLoader.Tiberium.get(OrePrefixes.lens, 0),
                CustomItemList.MysteriousCrystalLens.get(0),
                CustomItemList.ChromaticLens.get(0),
                VA_ItemList.LensPrimoium.get(0),
                VA_ItemList.LensOriginium.get(0),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64))
            .fluidInputs(Materials.Water.getFluid(512 * BUCKETS), Materials.UUMatter.getFluid(16 * BUCKETS))
            .itemOutputs(
                ItemUtils.simpleMetaStack(ModItems.itemStandarParticleBase, 0, 1),
                ItemUtils.simpleMetaStack(ModItems.itemStandarParticleBase, 7, 1),
                ItemUtils.simpleMetaStack(ModItems.itemStandarParticleBase, 18, 1),
                ItemUtils.simpleMetaStack(ModItems.itemStandarParticleBase, 24, 1),
                VA_Materials.AstriumMagic.get(OrePrefixes.dust, 32),
                VA_Materials.AstriumInfinity.get(OrePrefixes.dust, 32))
            .outputChances(10, 10, 10, 5, 5000, 1000)
            .fluidOutputs(
                Materials.Grade1PurifiedWater.getFluid(128 * BUCKETS),
                Materials.Grade2PurifiedWater.getFluid(128 * BUCKETS),
                Materials.Grade3PurifiedWater.getFluid(64 * BUCKETS),
                Materials.Grade4PurifiedWater.getFluid(64 * BUCKETS),
                Materials.Grade5PurifiedWater.getFluid(32 * BUCKETS),
                Materials.Grade6PurifiedWater.getFluid(32 * BUCKETS),
                Materials.Grade7PurifiedWater.getFluid(32 * BUCKETS),
                Materials.Grade8PurifiedWater.getFluid(32 * BUCKETS))
            .noOptimize()
            .eut(RECIPE_UEV)
            .duration(80 * SECONDS)
            .addTo(AF);
        // endregion

        // region 8号电路 矿物处理
        // SiO2
        Materials[] SiliconDioxide = { Materials.SiliconDioxide, Materials.NetherQuartz, Materials.CertusQuartz,
            Materials.Quartzite };
        for (Materials s : SiliconDioxide) {
            ItemStack[] inputArray = new ItemStack[16];
            inputArray[0] = GT_Utility.getIntegratedCircuit(8);
            inputArray[1] = VA_ItemList.LensAstriumInfinity.get(0);
            for (int j = 2; j < 16; j++) {
                inputArray[j] = s.getDust(64);
            }
            GT_Values.RA.stdBuilder()
                .itemInputs(inputArray)
                .itemOutputs(
                    Materials.Silicon.getDust(64),
                    Materials.Silicon.getDust(64),
                    Materials.Silicon.getDust(64),
                    Materials.Silicon.getDust(64),
                    Materials.Silicon.getDust(64),
                    Materials.Silicon.getDust(64),
                    Materials.Silicon.getDust(64),
                    Materials.Silicon.getDust(64),
                    Materials.SiliconSG.getDust(64),
                    Materials.SiliconSG.getDust(64),
                    Materials.SiliconSG.getDust(64),
                    Materials.SiliconSG.getDust(64),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 64))
                .outputChances(
                    FullChance,
                    FullChance,
                    FullChance,
                    FullChance,
                    FullChance,
                    FullChance,
                    FullChance,
                    FullChance,
                    7000,
                    7000,
                    7000,
                    7000,
                    6000,
                    6000)
                .noOptimize()
                .eut(RECIPE_HV)
                .duration(40 * SECONDS)
                .addTo(AF);
        }
        // 方解石
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64),
                Materials.Calcite.getDust(64))
            .itemOutputs(
                Materials.Calcium.getDust(64),
                Materials.Calcium.getDust(64),
                Materials.Calcium.getDust(64),
                Materials.Calcium.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                Materials.Carbon.getDust(64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64))
            .outputChances(
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                8000,
                8000)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * SECONDS)
            .addTo(AF);
        // 磷酸盐
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64),
                Materials.Phosphate.getDust(64))
            .itemOutputs(
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                Materials.Phosphorus.getDust(64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64))
            .outputChances(
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                8000,
                8000,
                6000,
                6000)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * SECONDS)
            .addTo(AF);
        // 硫酸钠
        final ItemStack Na2SO4 = WerkstoffLoader.Sodiumsulfate.get(OrePrefixes.dust, 64);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4,
                Na2SO4)
            .itemOutputs(
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sodium.getDust(64),
                Materials.Sulfur.getDust(64),
                Materials.Sulfur.getDust(64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64))
            .outputChances(
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                8000,
                8000)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * SECONDS)
            .addTo(AF);
        // 钐
        final ItemStack SmMix = WerkstoffMaterialPool.SamariumOreConcentrate.get(OrePrefixes.dust, 64);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                SmMix,
                SmMix,
                SmMix,
                SmMix)
            .itemOutputs(
                Materials.Samarium.getDust(64),
                Materials.Samarium.getDust(32),
                Materials.Lanthanum.getDust(16),
                Materials.Cerium.getDust(16),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64))
            .outputChances(FullChance, FullChance, 9500, 9500, 7500, 7500)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * SECONDS)
            .addTo(AF);
        // 富铈粉
        final ItemStack CeMix = WerkstoffMaterialPool.CeriumRichMixture.get(OrePrefixes.dust, 64);
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                CeMix,
                CeMix,
                CeMix,
                CeMix)
            .itemOutputs(
                Materials.Cerium.getDust(64),
                Materials.Cerium.getDust(16),
                Materials.Lanthanum.getDust(16),
                Materials.Europium.getDust(16),
                Materials.Gadolinium.getDust(16),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64))
            .outputChances(FullChance, FullChance, 9500, 8000, 8000, 7500, 7500)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * SECONDS)
            .addTo(AF);
        // 稀土
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                Materials.RareEarth.getDust(64),
                Materials.RareEarth.getDust(64))
            .itemOutputs(
                MISC_MATERIALS.RARE_EARTH_LOW.getDust(48),
                MISC_MATERIALS.RARE_EARTH_MID.getDust(40),
                MISC_MATERIALS.RARE_EARTH_HIGH.getDust(32))
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(12 * SECONDS)
            .addTo(AF);
        // 铂金属
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PTMetallicPowder.get(OrePrefixes.dust, 64))
            .itemOutputs(
                Materials.Platinum.getDust(64),
                Materials.Platinum.getDust(64),
                Materials.Palladium.getDust(64),
                Materials.Palladium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Osmium.getDust(64),
                Materials.Osmium.getDust(64),
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Ruthenium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Ruthenium.get(OrePrefixes.dust, 64))
            .outputChances(8000, 3000, 8000, 3000, 8000, 3000, 8000, 3000, 8000, 3000, 8000, 3000)
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(15 * SECONDS)
            .addTo(AF);
        // 铱渣
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                WerkstoffLoader.IrLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrLeachResidue.get(OrePrefixes.dust, 64))
            .itemOutputs(
                Materials.Iridium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Gold.getDust(64),
                Materials.Gold.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64))
            .outputChances(
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                8000,
                8000,
                6000,
                6000,
                6000,
                6000,
                3000,
                3000)
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(15 * SECONDS)
            .addTo(AF);
        // 浸出渣
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                WerkstoffLoader.LeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.LeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.LeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.LeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.LeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.LeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.LeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.LeachResidue.get(OrePrefixes.dust, 64))
            .itemOutputs(
                Materials.Iridium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Osmium.getDust(64),
                Materials.Osmium.getDust(64),
                Materials.Osmium.getDust(64),
                WerkstoffLoader.Ruthenium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Ruthenium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Ruthenium.get(OrePrefixes.dust, 64),
                Materials.Gold.getDust(48),
                Materials.Gold.getDust(48),
                Materials.Silicon.getDust(48),
                Materials.Silicon.getDust(48))
            .outputChances(8000, 8000, 8000, 8000, 8000, 8000, 8000, 8000, 8000, 6000, 6000, 3000, 3000)
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(15 * SECONDS)
            .addTo(AF);
        // 锇渣
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                WerkstoffLoader.IrOsLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrOsLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrOsLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrOsLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrOsLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrOsLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrOsLeachResidue.get(OrePrefixes.dust, 64),
                WerkstoffLoader.IrOsLeachResidue.get(OrePrefixes.dust, 64))
            .itemOutputs(
                Materials.Iridium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Iridium.getDust(64),
                Materials.Osmium.getDust(64),
                Materials.Osmium.getDust(64),
                Materials.Osmium.getDust(64),
                Materials.Osmium.getDust(64),
                Materials.Gold.getDust(64),
                Materials.Gold.getDust(64),
                Materials.Silicon.getDust(64),
                Materials.Silicon.getDust(64))
            .outputChances(
                FullChance,
                FullChance,
                8000,
                8000,
                FullChance,
                FullChance,
                8000,
                8000,
                6000,
                6000,
                3000,
                3000)
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(15 * SECONDS)
            .addTo(AF);
        // 钯金属
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                WerkstoffLoader.PDMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PDMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PDMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PDMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PDMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PDMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PDMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PDMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PDMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PDMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PDMetallicPowder.get(OrePrefixes.dust, 64),
                WerkstoffLoader.PDMetallicPowder.get(OrePrefixes.dust, 64))
            .itemOutputs(
                Materials.Palladium.getDust(64),
                Materials.Palladium.getDust(64),
                Materials.Palladium.getDust(64),
                Materials.Palladium.getDust(64),
                Materials.Palladium.getDust(64),
                Materials.Palladium.getDust(64),
                Materials.Palladium.getDust(64),
                Materials.Palladium.getDust(64),
                Materials.Palladium.getDust(64),
                Materials.Palladium.getDust(64),
                Materials.Palladium.getDust(64),
                Materials.Palladium.getDust(64))
            .outputChances(
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                6000,
                6000,
                6000,
                6000)
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(15 * SECONDS)
            .addTo(AF);
        // 粗制铑金属
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                WerkstoffLoader.CrudeRhMetall.get(OrePrefixes.dust, 64),
                WerkstoffLoader.CrudeRhMetall.get(OrePrefixes.dust, 64),
                WerkstoffLoader.CrudeRhMetall.get(OrePrefixes.dust, 64),
                WerkstoffLoader.CrudeRhMetall.get(OrePrefixes.dust, 64),
                WerkstoffLoader.CrudeRhMetall.get(OrePrefixes.dust, 64),
                WerkstoffLoader.CrudeRhMetall.get(OrePrefixes.dust, 64),
                WerkstoffLoader.CrudeRhMetall.get(OrePrefixes.dust, 64),
                WerkstoffLoader.CrudeRhMetall.get(OrePrefixes.dust, 64),
                WerkstoffLoader.CrudeRhMetall.get(OrePrefixes.dust, 64),
                WerkstoffLoader.CrudeRhMetall.get(OrePrefixes.dust, 64),
                WerkstoffLoader.CrudeRhMetall.get(OrePrefixes.dust, 64),
                WerkstoffLoader.CrudeRhMetall.get(OrePrefixes.dust, 64))
            .itemOutputs(
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64),
                WerkstoffLoader.Rhodium.get(OrePrefixes.dust, 64))
            .outputChances(
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                6000,
                6000,
                6000,
                6000)
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(15 * SECONDS)
            .addTo(AF);
        // 氧化硅岩
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                MyMaterial.naquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadahEarth.get(OrePrefixes.dust, 64))
            .itemOutputs(
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Titanium.getDust(64),
                Materials.Titanium.getDust(64),
                Materials.Titanium.getDust(64),
                Materials.Titanium.getDust(64),
                Materials.Adamantium.getDust(64),
                Materials.Adamantium.getDust(64),
                Materials.Gallium.getDust(64),
                Materials.Gallium.getDust(64))
            .outputChances(
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                8000,
                6000,
                4000,
                2000,
                8000,
                8000,
                8000,
                8000,
                8000,
                8000,
                8000,
                8000)
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(15 * SECONDS)
            .addTo(AF);
        // 超能硅岩
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                MyMaterial.naquadriaEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadriaEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadriaEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadriaEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadriaEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadriaEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadriaEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadriaEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadriaEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadriaEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadriaEarth.get(OrePrefixes.dust, 64),
                MyMaterial.naquadriaEarth.get(OrePrefixes.dust, 64))
            .itemOutputs(
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Barium.getDust(64),
                Materials.Barium.getDust(64),
                Materials.Barium.getDust(64),
                Materials.Indium.getDust(64),
                Materials.Indium.getDust(64),
                Materials.Indium.getDust(64))
            .outputChances(
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                8000,
                8000,
                8000,
                8000,
                6000,
                6000,
                8000,
                6000,
                6000,
                8000,
                6000,
                6000)
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(15 * SECONDS)
            .addTo(AF);
        // 富集硅岩
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                VA_ItemList.LensAstriumInfinity.get(0),
                MyMaterial.enrichedNaquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.enrichedNaquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.enrichedNaquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.enrichedNaquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.enrichedNaquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.enrichedNaquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.enrichedNaquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.enrichedNaquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.enrichedNaquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.enrichedNaquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.enrichedNaquadahEarth.get(OrePrefixes.dust, 64),
                MyMaterial.enrichedNaquadahEarth.get(OrePrefixes.dust, 64))
            .itemOutputs(
                Materials.NaquadahEnriched.getDust(64),
                Materials.NaquadahEnriched.getDust(64),
                Materials.NaquadahEnriched.getDust(64),
                Materials.NaquadahEnriched.getDust(64),
                Materials.NaquadahEnriched.getDust(64),
                Materials.NaquadahEnriched.getDust(64),
                Materials.NaquadahEnriched.getDust(64),
                Materials.NaquadahEnriched.getDust(64),
                Materials.NaquadahEnriched.getDust(64),
                Materials.NaquadahEnriched.getDust(64),
                Materials.Trinium.getDust(64),
                Materials.Trinium.getDust(64),
                Materials.Trinium.getDust(64),
                Materials.Trinium.getDust(64),
                Materials.Chrome.getDust(64))
            .outputChances(
                FullChance,
                FullChance,
                FullChance,
                FullChance,
                8000,
                8000,
                8000,
                8000,
                6000,
                6000,
                6000,
                6000,
                6000,
                6000,
                4000)
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(15 * SECONDS)
            .addTo(AF);
        // endregion

        // region 4号电路 合成
        // 合成阳光化合物 Su
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                VA_ItemList.LensAstriumInfinity.get(0),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                Materials.Glowstone.getDust(64))
            .fluidInputs(Materials.Hydrogen.getGas(48000))
            .itemOutputs(
                Materials.Sunnarium.getDust(64),
                Materials.Sunnarium.getDust(64),
                Materials.Sunnarium.getDust(64),
                Materials.Sunnarium.getDust(64))
            .noOptimize()
            .eut(RECIPE_ZPM)
            .duration(22 * SECONDS)
            .addTo(AF);
        // 合成超能硅岩 Nq*
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                VA_ItemList.LensAstriumInfinity.get(0),
                VA_Materials.AstriumInfinity.get(OrePrefixes.dust, 12),
                VA_Materials.Astrium.get(OrePrefixes.dust, 32),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64))
            .fluidInputs(Materials.NaquadahEnriched.getMolten(144 * 32))
            .itemOutputs(
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64),
                Materials.Naquadria.getDust(64))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(36 * SECONDS)
            .addTo(AF);
        // 合成合成玉 Or
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                VA_ItemList.LensAstriumInfinity.get(0),
                VA_Materials.Astrium.get(OrePrefixes.dust, 60),
                Materials.Naquadah.getDust(60))
            .fluidInputs(Materials.Helium.getGas(120000), Materials.Quantium.getMolten(32 * INGOTS))
            .itemOutputs(MyMaterial.orundum.get(OrePrefixes.dust, 64), MyMaterial.orundum.get(OrePrefixes.dust, 64))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(100 * SECONDS)
            .addTo(AF);
        // 合成源石
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                VA_ItemList.LensAstriumMagic.get(0),
                VA_ItemList.LensAstriumInfinity.get(0),
                WerkstoffLoader.Tiberium.get(OrePrefixes.lens, 0),
                MyMaterial.orundum.get(OrePrefixes.dust, 64),
                MyMaterial.orundum.get(OrePrefixes.dust, 64),
                MyMaterial.orundum.get(OrePrefixes.dust, 64),
                MyMaterial.orundum.get(OrePrefixes.dust, 64),
                Materials.Naquadah.getDust(60),
                VA_Materials.Astrium.get(OrePrefixes.dust, 64),
                VA_Materials.AstralCatalystBase.get(OrePrefixes.dust, 12))
            .itemOutputs(
                VA_Materials.Originium.get(OrePrefixes.dust, 64),
                VA_Materials.Originium.get(OrePrefixes.dust, 48))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(90 * SECONDS)
            .addTo(AF);
        // endregion

        // region 1号电路 制造星辉粉
        Materials[] pWater = { Materials.Grade1PurifiedWater, Materials.Grade2PurifiedWater,
            Materials.Grade3PurifiedWater, Materials.Grade4PurifiedWater, Materials.Grade5PurifiedWater,
            Materials.Grade6PurifiedWater, Materials.Grade7PurifiedWater };
        for (int i = 0; i < pWater.length; i++) {
            GT_Values.RA.stdBuilder()
                .itemInputs(GT_Utility.getIntegratedCircuit(1), VA_ItemList.LensPrimoium.get(0))
                .itemOutputs(
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)),
                    VA_Materials.Astrium.get(OrePrefixes.dust, 8 * (i + 2)))
                .fluidInputs(pWater[i].getFluid(16 * BUCKETS))
                .fluidOutputs(
                    VA_Materials.Astrium.getMolten(12 * 8 * (i + 2) * INGOTS),
                    VA_Materials.AstriumInfinity.getMolten(4 * 8 * (i + 2) * INGOTS))
                .noOptimize()
                .eut(RECIPE_UV)
                .duration(15 * SECONDS)
                .addTo(AF);
        }
        // endregion
    }
}
