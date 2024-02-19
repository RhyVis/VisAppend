package com.rhynia.gtnh.append.common.loader;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.UXV;

import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_Hatch_WirelessMulti;
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
import com.rhynia.gtnh.append.common.tile.multiMachine.generation.VA_TileEntity_SelectedEnergyGenerator;
import com.rhynia.gtnh.append.common.tile.multiMachine.generation.VA_TileEntity_VoidEnergyGenerator;
import com.rhynia.gtnh.append.common.tile.multiMachine.processing.VA_TileEntity_AssemblyMatrix;
import com.rhynia.gtnh.append.common.tile.multiMachine.processing.VA_TileEntity_AstraForge;
import com.rhynia.gtnh.append.common.tile.multiMachine.processing.VA_TileEntity_KelvinTransformField;
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
    public static ItemStack WirelessTunnel9001;
    public static ItemStack WirelessTunnel9001Debug;
    // endregion

    // region multi Machine controller
    public static ItemStack AstraForge;
    public static ItemStack UltimateHeater;
    public static ItemStack AssemblyMatrix;
    public static ItemStack VoidEnergyGenerator;
    public static ItemStack KelvinTransformField;
    public static ItemStack ReinforcedAssemblyLine;
    public static ItemStack SelectedEnergyGenerator;
    public static ItemStack Creator;
    public static ItemStack EyeOfUltimate;
    // endregion

    public static void loadMachines() {
        // region Multi claim
        AstraForge = new VA_TileEntity_AstraForge(17501, "MultiAstraForge", "星辉锻造台").getStackForm(1);
        UltimateHeater = new VA_TileEntity_UltimateHeater(17502, "MultiUltimateHeater", "粒子宏").getStackForm(1);
        AssemblyMatrix = new VA_TileEntity_AssemblyMatrix(17503, "MultiAssemblyMatrix", "组装矩阵").getStackForm(1);
        VoidEnergyGenerator = new VA_TileEntity_VoidEnergyGenerator(17504, "MultiVoidEnergyGenerator", "星辉能极差发电机")
            .getStackForm(1);
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
        // endregion

        // region Single claim
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
        ZeroGenerator = new VA_MetaTileEntity_Machine_ZeroGenerator(17450, "MachineGeneratorZero", "零点能发电机", 14)
            .getStackForm(1L);
        HumongousCalibrationInputHatch = new VA_MetaTileEntity_HumongousCalibrationInputHatch(
            17451,
            "HatchHumongousCalibration",
            "鸿蒙标定仓").getStackForm(1);
        HumongousCalibrationHalfInputHatch = new VA_MetaTileEntity_HumongousCalibrationHalfInputHatch(
            17452,
            "HatchHumongousCalibrationHalf",
            "鸿蒙半标定仓").getStackForm(1);
        WirelessTunnel9001 = new VA_MetaTileEntity_Hatch_WirelessMulti(
            17453,
            "HatchWirelessTunnel9001",
            "传奇无线能源仓",
            13,
            (int) UXV,
            false).getStackForm(1);
        WirelessTunnel9001Debug = new VA_MetaTileEntity_Hatch_WirelessMulti(
            17454,
            "HatchWirelessTunnel9001Debug",
            "传奇调试能源仓",
            13,
            (int) UXV,
            true).getStackForm(1);
        // endregion

        // region ItemList
        // Multi
        VAItemList.AstraForge.set(AstraForge);
        VAItemList.UltimateHeater.set(UltimateHeater);
        VAItemList.AssemblyMatrix.set(AssemblyMatrix);
        VAItemList.VoidEnergyGenerator.set(VoidEnergyGenerator);
        VAItemList.KelvinTransformField.set(KelvinTransformField);
        VAItemList.ReinforcedAssemblyLine.set(ReinforcedAssemblyLine);
        VAItemList.SelectedEnergyGenerator.set(SelectedEnergyGenerator);
        VAItemList.Creator.set(Creator);
        VAItemList.EyeOfUltimate.set(EyeOfUltimate);
        // Single
        VAItemList.InfiniteLiquidAirHatch.set(InfiniteLiquidAirHatch);
        VAItemList.InfiniteDistilledWaterHatch.set(InfiniteDistilledWaterHatch);
        VAItemList.InfiniteLavaHatch.set(InfiniteLavaHatch);
        VAItemList.InfiniteOilHatch.set(InfiniteOilHatch);
        VAItemList.InfiniteLubricantHatch.set(InfiniteLubricantHatch);
        VAItemList.InfiniteSteamHatch.set(InfiniteSteamHatch);
        VAItemList.ZeroGenerator.set(ZeroGenerator);
        VAItemList.HumongousCalibrationInputHatch.set(HumongousCalibrationInputHatch);
        VAItemList.HumongousCalibrationHalfInputHatch.set(HumongousCalibrationHalfInputHatch);
        VAItemList.WirelessTunnel9001.set(WirelessTunnel9001);
        VAItemList.WirelessTunnel9001Debug.set(WirelessTunnel9001Debug);
        // endregion
    }

    // endregion
}
