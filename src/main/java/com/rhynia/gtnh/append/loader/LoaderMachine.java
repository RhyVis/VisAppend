package com.rhynia.gtnh.append.loader;

import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.AppendItemList;
import com.rhynia.gtnh.append.common.machine.multiMachine.VA_TileEntity_AstraForge;
import com.rhynia.gtnh.append.common.machine.multiMachine.VA_TileEntity_UltimateHeater;
import com.rhynia.gtnh.append.common.machine.singleMachine.VA_MetaTileEntity_Hatch_DistilledWater;
import com.rhynia.gtnh.append.common.machine.singleMachine.VA_MetaTileEntity_Hatch_Lava;
import com.rhynia.gtnh.append.common.machine.singleMachine.VA_MetaTileEntity_Hatch_LiquidAir;
import com.rhynia.gtnh.append.common.machine.singleMachine.VA_MetaTileEntity_Hatch_Oil;
import com.rhynia.gtnh.append.common.machine.singleMachine.VA_MetaTileEntity_Machine_ZeroGenerator;

public class LoaderMachine {

    // region sig Machine
    public static ItemStack InfiniteLiquidAirHatch;
    public static ItemStack InfiniteDistilledWaterHatch;
    public static ItemStack InfiniteLavaHatch;
    public static ItemStack InfiniteOilHatch;
    public static ItemStack ZeroGenerator;
    // endregion

    // region multi Machine controller
    public static ItemStack AstraForge;
    public static ItemStack UltimateHeater;
    // endregion

    public static void loadMachines() {
        AstraForge = new VA_TileEntity_AstraForge(17501, "MultiAstraForge", "星辉锻造台").getStackForm(1);
        AppendItemList.AstraForge.set(AstraForge);
        UltimateHeater = new VA_TileEntity_UltimateHeater(17502, "MultiUltimateHeater", "粒子宏").getStackForm(1);
        AppendItemList.UltimateHeater.set(UltimateHeater);

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

        ZeroGenerator = new VA_MetaTileEntity_Machine_ZeroGenerator(17450, "MachineGeneratorZero", "零点能发电机", 14)
            .getStackForm(1L);

        AppendItemList.InfiniteLiquidAirHatch.set(InfiniteLiquidAirHatch);
        AppendItemList.InfiniteDistilledWaterHatch.set(InfiniteDistilledWaterHatch);
        AppendItemList.InfiniteLavaHatch.set(InfiniteLavaHatch);
        AppendItemList.InfiniteOilHatch.set(InfiniteOilHatch);
        AppendItemList.ZeroGenerator.set(ZeroGenerator);
    }

    // endregion
}
