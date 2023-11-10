package com.rhynia.gtnh.append.loader;

import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.AppendItemList;
import com.rhynia.gtnh.append.common.machine.multiblock.GT_TileEntity_AstraForge;
import com.rhynia.gtnh.append.common.machine.singalblock.GT_MetaTileEntity_Hatch_LiquidAir;

public class LoaderMachine {

    // region sig Machine
    public static ItemStack InfiniteLiquidAirHatch;
    //endregion

    // region multi Machine controller
    public static ItemStack AstraForge;
    //endregion

    public static void loadMachines() {
        AstraForge = new GT_TileEntity_AstraForge(17501, "MultiAstraForge", "星辉锻造台").getStackForm(1);
        AppendItemList.AstraForge.set(AstraForge);

        InfiniteLiquidAirHatch = new GT_MetaTileEntity_Hatch_LiquidAir(17401, "InfiniteLiquidAirHatch", "无限压缩进气仓", 9)
            .getStackForm(1);
        AppendItemList.InfiniteLiquidAirHatch.set(InfiniteLiquidAirHatch);
    }

    // endregion
}
