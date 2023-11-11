package com.rhynia.gtnh.append.loader;

import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.AppendItemList;
import com.rhynia.gtnh.append.common.machine.multiblock.GT_TileEntity_AstraForge;
import com.rhynia.gtnh.append.common.machine.multiblock.GT_TileEntity_UltimateHeater;
import com.rhynia.gtnh.append.common.machine.single.GT_MetaTileEntity_Hatch_DistilledWater;
import com.rhynia.gtnh.append.common.machine.single.GT_MetaTileEntity_Hatch_Lava;
import com.rhynia.gtnh.append.common.machine.single.GT_MetaTileEntity_Hatch_LiquidAir;
import com.rhynia.gtnh.append.common.machine.single.GT_MetaTileEntity_Hatch_Oil;

public class LoaderMachine {

    // region sig Machine
    public static ItemStack InfiniteLiquidAirHatch;
    public static ItemStack InfiniteDistilledWaterHatch;
    public static ItemStack InfiniteLavaHatch;
    public static ItemStack InfiniteOilHatch;
    // endregion

    // region multi Machine controller
    public static ItemStack AstraForge;
    public static ItemStack UltimateHeater;
    // endregion

    public static void loadMachines() {
        AstraForge = new GT_TileEntity_AstraForge(17501, "MultiAstraForge", "星辉锻造台").getStackForm(1);
        AppendItemList.AstraForge.set(AstraForge);
        UltimateHeater = new GT_TileEntity_UltimateHeater(17502, "MultiUltimateHeater", "至终加热场").getStackForm(1);
        AppendItemList.UltimateHeater.set(UltimateHeater);

        InfiniteLiquidAirHatch = new GT_MetaTileEntity_Hatch_LiquidAir(17401, "HatchInfiniteLiquidAir", "无限压缩进气仓", 9)
            .getStackForm(1);
        InfiniteDistilledWaterHatch = new GT_MetaTileEntity_Hatch_DistilledWater(
            17402,
            "HatchDistilledWaterHatch",
            "无限蒸馏仓",
            9).getStackForm(1);
        InfiniteLavaHatch = new GT_MetaTileEntity_Hatch_Lava(17403, "HatchInfiniteLavaHatch", "无限熔岩仓", 9)
            .getStackForm(1);
        InfiniteOilHatch = new GT_MetaTileEntity_Hatch_Oil(17404, "HatchInfiniteOilHatch", "无限油泉仓", 9).getStackForm(1);

        AppendItemList.InfiniteLiquidAirHatch.set(InfiniteLiquidAirHatch);
        AppendItemList.InfiniteDistilledWaterHatch.set(InfiniteDistilledWaterHatch);
        AppendItemList.InfiniteLavaHatch.set(InfiniteLavaHatch);
        AppendItemList.InfiniteOilHatch.set(InfiniteOilHatch);
    }

    // endregion
}
