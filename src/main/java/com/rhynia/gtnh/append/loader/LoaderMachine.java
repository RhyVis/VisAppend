package com.rhynia.gtnh.append.loader;

import com.rhynia.gtnh.append.common.machine.singalblock.GT_MetaTileEntity_Hatch_LiquidAir;
import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.AppendItemList;
import com.rhynia.gtnh.append.common.machine.multiblock.GT_TileEntity_AstraForge;

public class LoaderMachine {

    public static ItemStack AstraForge;

    // region sig Machine
    public static ItemStack InfiniteLiquidAirHatch;
    // region multi Machine controller

    public static void loadMachines() {
        AstraForge = new GT_TileEntity_AstraForge(17501, "NameAstraForge", "星辉锻造台").getStackForm(1);
        AppendItemList.AstraForge.set(AstraForge);


        InfiniteLiquidAirHatch = new GT_MetaTileEntity_Hatch_LiquidAir(18999, "InfiniteLiquidAirHatch", "无限压缩进气仓", 9)
            .getStackForm(1);
        AppendItemList.InfiniteLiquidAirHatch.set(InfiniteLiquidAirHatch);
    }

    // endregion
}
