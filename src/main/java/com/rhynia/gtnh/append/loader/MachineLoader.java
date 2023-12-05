package com.rhynia.gtnh.append.loader;

import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.machine.multiMachine.VA_TileEntity_AssemblyMatrix;
import com.rhynia.gtnh.append.common.machine.multiMachine.VA_TileEntity_AstraForge;
import com.rhynia.gtnh.append.common.machine.multiMachine.VA_TileEntity_KelvinTransformField;
import com.rhynia.gtnh.append.common.machine.multiMachine.VA_TileEntity_SuperconductingBinder;
import com.rhynia.gtnh.append.common.machine.multiMachine.VA_TileEntity_UltimateHeater;
import com.rhynia.gtnh.append.common.machine.singleMachine.VA_MetaTileEntity_Hatch_DistilledWater;
import com.rhynia.gtnh.append.common.machine.singleMachine.VA_MetaTileEntity_Hatch_Lava;
import com.rhynia.gtnh.append.common.machine.singleMachine.VA_MetaTileEntity_Hatch_LiquidAir;
import com.rhynia.gtnh.append.common.machine.singleMachine.VA_MetaTileEntity_Hatch_Lubricant;
import com.rhynia.gtnh.append.common.machine.singleMachine.VA_MetaTileEntity_Hatch_Oil;
import com.rhynia.gtnh.append.common.machine.singleMachine.VA_MetaTileEntity_Hatch_Steam;
import com.rhynia.gtnh.append.common.machine.singleMachine.VA_MetaTileEntity_Machine_ZeroGenerator;

public class MachineLoader {

    // region sig Machine
    public static ItemStack InfiniteLiquidAirHatch;
    public static ItemStack InfiniteDistilledWaterHatch;
    public static ItemStack InfiniteLavaHatch;
    public static ItemStack InfiniteOilHatch;
    public static ItemStack InfiniteLubricantHatch;
    public static ItemStack InfiniteSteamHatch;
    public static ItemStack ZeroGenerator;
    // endregion

    // region multi Machine controller
    public static ItemStack AstraForge;
    public static ItemStack UltimateHeater;
    public static ItemStack AssemblyMatrix;
    public static ItemStack SuperconductingBinder;
    public static ItemStack KelvinTransformField;
    // endregion

    public static void loadMachines() {
        // region Multi claim
        AstraForge = new VA_TileEntity_AstraForge(17501, "MultiAstraForge", "星辉锻造台").getStackForm(1);
        UltimateHeater = new VA_TileEntity_UltimateHeater(17502, "MultiUltimateHeater", "粒子宏").getStackForm(1);
        AssemblyMatrix = new VA_TileEntity_AssemblyMatrix(17503, "MultiAssemblyMatrix", "组装矩阵").getStackForm(1);
        SuperconductingBinder = new VA_TileEntity_SuperconductingBinder(17504, "MultiSuperconductingBinder", "超导装配线")
            .getStackForm(1);
        KelvinTransformField = new VA_TileEntity_KelvinTransformField(17505, "MultiKelvinTransformField", "开尔文变换场")
            .getStackForm(1);
        // endregion

        // region Single claim
        InfiniteLiquidAirHatch = new VA_MetaTileEntity_Hatch_LiquidAir(17401, "HatchInfiniteLiquidAir", "无限压缩进气仓", 10)
            .getStackForm(1);
        InfiniteDistilledWaterHatch = new VA_MetaTileEntity_Hatch_DistilledWater(
            17402,
            "HatchDistilledWaterHatch",
            "无限蒸馏仓",
            9).getStackForm(1);
        InfiniteLavaHatch = new VA_MetaTileEntity_Hatch_Lava(17403, "HatchInfiniteLavaHatch", "无限熔岩仓", 8)
            .getStackForm(1);
        InfiniteOilHatch = new VA_MetaTileEntity_Hatch_Oil(17404, "HatchInfiniteOilHatch", "无限油泉仓", 8).getStackForm(1);
        InfiniteLubricantHatch = new VA_MetaTileEntity_Hatch_Lubricant(17405, "HatchInfiniteLubricantHatch", "无限润滑仓", 8)
            .getStackForm(1);
        InfiniteSteamHatch = new VA_MetaTileEntity_Hatch_Steam(17406, "HatchInfiniteSteamHatch", "无限蒸汽仓", 8)
            .getStackForm(1);
        ZeroGenerator = new VA_MetaTileEntity_Machine_ZeroGenerator(17450, "MachineGeneratorZero", "零点能发电机", 14)
            .getStackForm(1L);
        // endregion

        // region ItemList
        // Multi
        VAItemList.AstraForge.set(AstraForge);
        VAItemList.UltimateHeater.set(UltimateHeater);
        VAItemList.AssemblyMatrix.set(AssemblyMatrix);
        VAItemList.SuperconductingBinder.set(SuperconductingBinder);
        VAItemList.KelvinTransformField.set(KelvinTransformField);
        // Single
        VAItemList.InfiniteLiquidAirHatch.set(InfiniteLiquidAirHatch);
        VAItemList.InfiniteDistilledWaterHatch.set(InfiniteDistilledWaterHatch);
        VAItemList.InfiniteLavaHatch.set(InfiniteLavaHatch);
        VAItemList.InfiniteOilHatch.set(InfiniteOilHatch);
        VAItemList.InfiniteLubricantHatch.set(InfiniteLubricantHatch);
        VAItemList.InfiniteSteamHatch.set(InfiniteSteamHatch);
        VAItemList.ZeroGenerator.set(ZeroGenerator);
        // endregion
    }

    // endregion
}
