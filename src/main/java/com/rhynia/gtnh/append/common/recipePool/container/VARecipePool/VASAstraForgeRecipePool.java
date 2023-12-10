package com.rhynia.gtnh.append.common.recipePool.container.VARecipePool;

import static com.rhynia.gtnh.append.api.util.Values.FullChance;
import static com.rhynia.gtnh.append.api.util.Values.lensInf;
import static com.rhynia.gtnh.append.api.util.Values.lensMagic;
import static gregtech.api.enums.OrePrefixes.dust;
import static gregtech.api.enums.TierEU.RECIPE_EV;
import static gregtech.api.enums.TierEU.RECIPE_HV;
import static gregtech.api.enums.TierEU.RECIPE_IV;
import static gregtech.api.enums.TierEU.RECIPE_LuV;
import static gregtech.api.enums.TierEU.RECIPE_UEV;
import static gregtech.api.enums.TierEU.RECIPE_UHV;
import static gregtech.api.enums.TierEU.RECIPE_UV;
import static gregtech.api.enums.TierEU.RECIPE_ZPM;
import static gregtech.api.util.GT_RecipeBuilder.BUCKETS;
import static gregtech.api.util.GT_RecipeBuilder.INGOTS;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.dreammaster.gthandler.CustomItemList;
import com.elisis.gtnhlanth.common.register.WerkstoffMaterialPool;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.rhynia.gtnh.append.api.recipe.VA_Recipe;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.common.recipePool.IRecipePool;

import goodgenerator.items.MyMaterial;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.core.item.ModItems;
import gtPlusPlus.core.material.MISC_MATERIALS;
import gtPlusPlus.core.util.minecraft.ItemUtils;

public class VASAstraForgeRecipePool implements IRecipePool {

    private final GT_Recipe.GT_Recipe_Map AF = VA_Recipe.instance.sAstraForgeRecipes;

    @Override
    public void loadRecipesPostInit() {
        // region 10号电路 增殖
        // UU增殖
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(10),
                lensInf,
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64))
            .fluidInputs(Materials.UUMatter.getFluid(2048))
            .fluidOutputs(Materials.UUMatter.getFluid(32768))
            .noOptimize()
            .eut(RECIPE_LuV)
            .duration(40 * SECONDS)
            .addTo(AF);
        // endregion
    }

    @Override
    public void loadRecipes() {
        // region 12号电路
        // 润滑油
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(12),
                lensMagic,
                VA_GregtechMaterialPool.Astro.getDust(16),
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
                lensMagic,
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
                lensMagic,
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_WerkstoffMaterialPool.astroCatalystBase.get(OrePrefixes.dust, 16))
            .itemOutputs(VA_GregtechMaterialPool.AstroInf.getDust(1))
            .fluidOutputs(VA_WerkstoffMaterialPool.astroCatalystBase.getFluidOrGas(48 * BUCKETS))
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
                lensInf,
                new ItemStack(Items.blaze_powder, 4, 0),
                Materials.Sulfur.getDust(16),
                new ItemStack(Blocks.sand, 64),
                new ItemStack(Blocks.sand, 64),
                new ItemStack(Blocks.sand, 64),
                new ItemStack(Blocks.sand, 64),
                new ItemStack(Blocks.sand, 64),
                new ItemStack(Blocks.sand, 64),
                new ItemStack(Blocks.sand, 64),
                new ItemStack(Blocks.sand, 64))
            .fluidInputs(Materials.Water.getFluid(1000), VA_GregtechMaterialPool.Astro.getFluid(16))
            .itemOutputs(
                new ItemStack(Blocks.soul_sand, 64, 0),
                new ItemStack(Blocks.soul_sand, 64, 0),
                new ItemStack(Blocks.soul_sand, 64, 0),
                new ItemStack(Blocks.soul_sand, 64, 0),
                new ItemStack(Blocks.soul_sand, 64, 0),
                new ItemStack(Blocks.soul_sand, 64, 0),
                new ItemStack(Blocks.soul_sand, 64, 0),
                new ItemStack(Blocks.soul_sand, 64, 0))
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(18 * SECONDS)
            .addTo(AF);
        // 烈焰粉
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                lensInf,
                Materials.Carbon.getDust(64),
                Materials.Sulfur.getDust(64))
            .fluidInputs(Materials.Lava.getFluid(1000), VA_GregtechMaterialPool.Astro.getFluid(16))
            .itemOutputs(
                new ItemStack(Items.blaze_powder, 64, 0),
                new ItemStack(Items.blaze_powder, 64, 0),
                new ItemStack(Items.blaze_powder, 64, 0),
                new ItemStack(Items.blaze_powder, 64, 0))
            .noOptimize()
            .eut(RECIPE_EV)
            .duration(18 * SECONDS)
            .addTo(AF);
        // 末影珍珠
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                lensMagic,
                GT_ModHandler.getModItem("HardcoreEnderExpansion", "end_powder", 16),
                VA_GregtechMaterialPool.Astro.getDust(64))
            .fluidInputs(VA_GregtechMaterialPool.Astro.getFluid(16))
            .itemOutputs(
                new ItemStack(Items.ender_pearl, 64, 0),
                new ItemStack(Items.ender_pearl, 64, 0),
                new ItemStack(Items.ender_pearl, 64, 0),
                new ItemStack(Items.ender_pearl, 64, 0))
            .noOptimize()
            .eut(RECIPE_IV)
            .duration(18 * SECONDS)
            .addTo(AF);
        // 胶质云母
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(11),
                lensMagic,
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64),
                Materials.Mica.getDust(64),
                Materials.Mica.getDust(64),
                Materials.Mica.getDust(64),
                Materials.Mica.getDust(64))
            .fluidInputs(VA_GregtechMaterialPool.Astro.getFluid(1000))
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
                lensInf,
                lensMagic,
                WerkstoffLoader.Tiberium.get(OrePrefixes.lens, 0),
                CustomItemList.MysteriousCrystalLens.get(0),
                CustomItemList.ChromaticLens.get(0),
                VA_WerkstoffMaterialPool.Originiums.get(OrePrefixes.lens, 0),
                VA_WerkstoffMaterialPool.Primogem.get(OrePrefixes.lens, 0),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64))
            .fluidInputs(Materials.Water.getFluid(512 * BUCKETS), Materials.UUMatter.getFluid(16 * BUCKETS))
            .itemOutputs(
                ItemUtils.simpleMetaStack(ModItems.itemStandarParticleBase, 0, 1),
                ItemUtils.simpleMetaStack(ModItems.itemStandarParticleBase, 7, 1),
                ItemUtils.simpleMetaStack(ModItems.itemStandarParticleBase, 18, 1),
                ItemUtils.simpleMetaStack(ModItems.itemStandarParticleBase, 24, 1),
                VA_GregtechMaterialPool.AstroMagic.getDust(32),
                VA_GregtechMaterialPool.AstroInf.getDust(32))
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
        // 石英岩
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64),
                Materials.Quartzite.getDust(64))
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
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64))
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
        // 二氧化硅
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64),
                Materials.SiliconDioxide.getDust(64))
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
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64))
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
        // 赛特斯石英
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
                Materials.CertusQuartz.getDust(64),
                Materials.CertusQuartz.getDust(64),
                Materials.CertusQuartz.getDust(64),
                Materials.CertusQuartz.getDust(64),
                Materials.CertusQuartz.getDust(64),
                Materials.CertusQuartz.getDust(64),
                Materials.CertusQuartz.getDust(64),
                Materials.CertusQuartz.getDust(64),
                Materials.CertusQuartz.getDust(64),
                Materials.CertusQuartz.getDust(64),
                Materials.CertusQuartz.getDust(64),
                Materials.CertusQuartz.getDust(64),
                Materials.CertusQuartz.getDust(64),
                Materials.CertusQuartz.getDust(64))
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
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64))
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
        // 下界石英
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
                Materials.NetherQuartz.getDust(64),
                Materials.NetherQuartz.getDust(64),
                Materials.NetherQuartz.getDust(64),
                Materials.NetherQuartz.getDust(64),
                Materials.NetherQuartz.getDust(64),
                Materials.NetherQuartz.getDust(64),
                Materials.NetherQuartz.getDust(64),
                Materials.NetherQuartz.getDust(64),
                Materials.NetherQuartz.getDust(64),
                Materials.NetherQuartz.getDust(64),
                Materials.NetherQuartz.getDust(64),
                Materials.NetherQuartz.getDust(64),
                Materials.NetherQuartz.getDust(64),
                Materials.NetherQuartz.getDust(64))
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
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64))
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
        // 方解石
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
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
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64))
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
                lensInf,
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
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64))
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
                lensInf,
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
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64))
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
            .itemInputs(GT_Utility.getIntegratedCircuit(8), lensInf, SmMix, SmMix, SmMix, SmMix)
            .itemOutputs(
                Materials.Samarium.getDust(64),
                Materials.Samarium.getDust(32),
                Materials.Lanthanum.getDust(16),
                Materials.Cerium.getDust(16),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64))
            .outputChances(FullChance, FullChance, 9500, 9500, 7500, 7500)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * SECONDS)
            .addTo(AF);
        // 富铈粉
        final ItemStack CeMix = WerkstoffMaterialPool.CeriumRichMixture.get(OrePrefixes.dust, 64);
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(8), lensInf, CeMix, CeMix, CeMix, CeMix)
            .itemOutputs(
                Materials.Cerium.getDust(64),
                Materials.Cerium.getDust(16),
                Materials.Lanthanum.getDust(16),
                Materials.Europium.getDust(16),
                Materials.Gadolinium.getDust(16),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64))
            .outputChances(FullChance, FullChance, 9500, 8000, 8000, 7500, 7500)
            .noOptimize()
            .eut(RECIPE_HV)
            .duration(40 * SECONDS)
            .addTo(AF);
        // 稀土
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
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
                lensInf,
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
                lensInf,
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
        // 锇渣
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
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
                lensInf,
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
        // 氧化硅岩
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(8),
                lensInf,
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
                lensInf,
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
                lensInf,
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
                lensInf,
                VA_GregtechMaterialPool.Astro.getDust(64),
                Materials.Glowstone.getDust(64))
            .fluidInputs(Materials.Hydrogen.getFluid(48000))
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
                lensInf,
                VA_GregtechMaterialPool.AstroInf.getDust(12),
                VA_GregtechMaterialPool.Astro.getDust(32),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64),
                Materials.Naquadah.getDust(64))
            .fluidInputs(Materials.NaquadahEnriched.getFluid(144 * 32))
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
                lensInf,
                VA_GregtechMaterialPool.Astro.getDust(60),
                Materials.Naquadah.getDust(60))
            .fluidInputs(Materials.Helium.getGas(120000), Materials.Quantium.getMolten(32 * INGOTS))
            .itemOutputs(MyMaterial.orundum.get(dust, 64), MyMaterial.orundum.get(dust, 64))
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(100 * SECONDS)
            .addTo(AF);

        // 合成源石
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                lensMagic,
                lensInf,
                WerkstoffLoader.Tiberium.get(OrePrefixes.lens, 0),
                MyMaterial.orundum.get(dust, 64),
                MyMaterial.orundum.get(dust, 64),
                MyMaterial.orundum.get(dust, 64),
                MyMaterial.orundum.get(dust, 64),
                Materials.Naquadah.getDust(60),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_WerkstoffMaterialPool.astroCatalystBase.get(dust, 12))
            .itemOutputs(
                VA_WerkstoffMaterialPool.Originiums.get(dust, 64),
                VA_WerkstoffMaterialPool.Originiums.get(dust, 48))
            .noOptimize()
            .eut(RECIPE_UHV)
            .duration(90 * SECONDS)
            .addTo(AF);

        // endregion
    }
}
