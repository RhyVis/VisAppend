package com.rhynia.gtnh.append.common.loader.control;

import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.loader.VA_ItemList;
import com.rhynia.gtnh.append.common.tile.blockMachine.VA_MetaTileEntity_Hatch_DistilledWater;
import com.rhynia.gtnh.append.common.tile.blockMachine.VA_MetaTileEntity_Hatch_Lava;
import com.rhynia.gtnh.append.common.tile.blockMachine.VA_MetaTileEntity_Hatch_LiquidAir;
import com.rhynia.gtnh.append.common.tile.blockMachine.VA_MetaTileEntity_Hatch_Lubricant;
import com.rhynia.gtnh.append.common.tile.blockMachine.VA_MetaTileEntity_Hatch_Oil;
import com.rhynia.gtnh.append.common.tile.blockMachine.VA_MetaTileEntity_Hatch_Steam;
import com.rhynia.gtnh.append.common.tile.blockMachine.VA_MetaTileEntity_HumongousCalibrationHalfInputHatch;
import com.rhynia.gtnh.append.common.tile.blockMachine.VA_MetaTileEntity_HumongousCalibrationInputHatch;
import com.rhynia.gtnh.append.common.tile.blockMachine.VA_MetaTileEntity_Machine_ZeroGenerator;
import com.rhynia.gtnh.append.common.tile.multiMachine.creation.VA_TileEntity_Creator;
import com.rhynia.gtnh.append.common.tile.multiMachine.creation.VA_TileEntity_EyeOfUltimate;
import com.rhynia.gtnh.append.common.tile.multiMachine.creation.VA_TileEntity_VoidMiner;
import com.rhynia.gtnh.append.common.tile.multiMachine.generation.VA_TileEntity_SelectedEnergyGenerator;
import com.rhynia.gtnh.append.common.tile.multiMachine.processing.VA_TileEntity_AssemblyMatrix;
import com.rhynia.gtnh.append.common.tile.multiMachine.processing.VA_TileEntity_AstraForge;
import com.rhynia.gtnh.append.common.tile.multiMachine.processing.VA_TileEntity_DenseEndpoint;
import com.rhynia.gtnh.append.common.tile.multiMachine.processing.VA_TileEntity_KelvinTransformField;
import com.rhynia.gtnh.append.common.tile.multiMachine.processing.VA_TileEntity_ProcessingMatrix;
import com.rhynia.gtnh.append.common.tile.multiMachine.processing.VA_TileEntity_ReinforcedAssemblyLine;
import com.rhynia.gtnh.append.common.tile.multiMachine.processing.VA_TileEntity_UltimateHeater;

public class MachineLoader {

    // region sig Machine
    public static ItemStack InfiniteLiquidAirHatch;
    public static ItemStack InfiniteDistilledWaterHatch;
    public static ItemStack InfiniteLavaHatch;
    public static ItemStack InfiniteOilHatch;
    public static ItemStack InfiniteLubricantHatch;
    public static ItemStack InfiniteSteamHatch;
    public static ItemStack ZeroGenerator;
    public static ItemStack HumongousCalibrationInputHatch;
    public static ItemStack HumongousCalibrationHalfInputHatch;
    // endregion

    // region multi Machine controller
    public static ItemStack AstraForge;
    public static ItemStack UltimateHeater;
    public static ItemStack AssemblyMatrix;
    public static ItemStack VoidMiner;
    public static ItemStack KelvinTransformField;
    public static ItemStack ReinforcedAssemblyLine;
    public static ItemStack SelectedEnergyGenerator;
    public static ItemStack Creator;
    public static ItemStack EyeOfUltimate;
    public static ItemStack DenseEndpoint;
    public static ItemStack ProcessingMatrix;
    // endregion

    public static void loadMachines() {
        // region Multi claim
        AstraForge = new VA_TileEntity_AstraForge(17501, "MultiAstraForge", "星辉锻造台").getStackForm(1);
        UltimateHeater = new VA_TileEntity_UltimateHeater(17502, "MultiUltimateHeater", "粒子宏").getStackForm(1);
        AssemblyMatrix = new VA_TileEntity_AssemblyMatrix(17503, "MultiAssemblyMatrix", "组装矩阵").getStackForm(1);
        VoidMiner = new VA_TileEntity_VoidMiner(17504, "MultiVoidMiner", "虚空原矿厂").getStackForm(1);
        KelvinTransformField = new VA_TileEntity_KelvinTransformField(17505, "MultiKelvinTransformField", "开尔文变换场")
            .getStackForm(1);
        ReinforcedAssemblyLine = new VA_TileEntity_ReinforcedAssemblyLine(17506, "MultiReinforcedAssemblyLine", "复合装配线")
            .getStackForm(1);
        SelectedEnergyGenerator = new VA_TileEntity_SelectedEnergyGenerator(
            17507,
            "MultiSelectedEnergyGenerator",
            "虚空发电机").getStackForm(1);
        Creator = new VA_TileEntity_Creator(17508, "MultiCreator", "逆向奇点").getStackForm(1);
        EyeOfUltimate = new VA_TileEntity_EyeOfUltimate(17509, "MultiEyeOfUltimate", "终极之眼").getStackForm(1);
        DenseEndpoint = new VA_TileEntity_DenseEndpoint(17510, "MultiDenseEndpoint", "致密极点").getStackForm(1);
        ProcessingMatrix = new VA_TileEntity_ProcessingMatrix(17511, "NameProcessingMatrix", "处理矩阵").getStackForm(1);
        // endregion

        // region Single claim
        ZeroGenerator = new VA_MetaTileEntity_Machine_ZeroGenerator(17400, "MachineGeneratorZero", "零点能发电机", 14)
            .getStackForm(1L);
        InfiniteLiquidAirHatch = new VA_MetaTileEntity_Hatch_LiquidAir(17401, "HatchInfiniteLiquidAir", "无限压缩进气仓", 10)
            .getStackForm(1);
        InfiniteDistilledWaterHatch = new VA_MetaTileEntity_Hatch_DistilledWater(
            17402,
            "HatchInfiniteDistilledWater",
            "无限蒸馏仓",
            9).getStackForm(1);
        InfiniteLavaHatch = new VA_MetaTileEntity_Hatch_Lava(17403, "HatchInfiniteLava", "无限熔岩仓", 8).getStackForm(1);
        InfiniteOilHatch = new VA_MetaTileEntity_Hatch_Oil(17404, "HatchInfiniteOil", "无限油泉仓", 8).getStackForm(1);
        InfiniteLubricantHatch = new VA_MetaTileEntity_Hatch_Lubricant(17405, "HatchInfiniteLubricant", "无限润滑仓", 10)
            .getStackForm(1);
        InfiniteSteamHatch = new VA_MetaTileEntity_Hatch_Steam(17406, "HatchInfiniteSteam", "无限蒸汽仓", 8).getStackForm(1);
        HumongousCalibrationInputHatch = new VA_MetaTileEntity_HumongousCalibrationInputHatch(
            17407,
            "HatchHumongousCalibration",
            "鸿蒙标定仓").getStackForm(1);
        HumongousCalibrationHalfInputHatch = new VA_MetaTileEntity_HumongousCalibrationHalfInputHatch(
            17408,
            "HatchHumongousCalibrationHalf",
            "鸿蒙半标定仓").getStackForm(1);
        // endregion

        // region ItemList
        // Multi
        VA_ItemList.AstraForge.set(AstraForge);
        VA_ItemList.UltimateHeater.set(UltimateHeater);
        VA_ItemList.AssemblyMatrix.set(AssemblyMatrix);
        VA_ItemList.VoidMiner.set(VoidMiner);
        VA_ItemList.KelvinTransformField.set(KelvinTransformField);
        VA_ItemList.ReinforcedAssemblyLine.set(ReinforcedAssemblyLine);
        VA_ItemList.SelectedEnergyGenerator.set(SelectedEnergyGenerator);
        VA_ItemList.Creator.set(Creator);
        VA_ItemList.EyeOfUltimate.set(EyeOfUltimate);
        VA_ItemList.DenseEndpoint.set(DenseEndpoint);
        VA_ItemList.ProcessingMatrix.set(ProcessingMatrix);
        // Single
        VA_ItemList.InfiniteLiquidAirHatch.set(InfiniteLiquidAirHatch);
        VA_ItemList.InfiniteDistilledWaterHatch.set(InfiniteDistilledWaterHatch);
        VA_ItemList.InfiniteLavaHatch.set(InfiniteLavaHatch);
        VA_ItemList.InfiniteOilHatch.set(InfiniteOilHatch);
        VA_ItemList.InfiniteLubricantHatch.set(InfiniteLubricantHatch);
        VA_ItemList.InfiniteSteamHatch.set(InfiniteSteamHatch);
        VA_ItemList.ZeroGenerator.set(ZeroGenerator);
        VA_ItemList.HumongousCalibrationInputHatch.set(HumongousCalibrationInputHatch);
        VA_ItemList.HumongousCalibrationHalfInputHatch.set(HumongousCalibrationHalfInputHatch);
        // endregion
    }

    // endregion
}
