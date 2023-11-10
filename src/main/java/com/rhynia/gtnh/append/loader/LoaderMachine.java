package com.rhynia.gtnh.append.loader;

import com.rhynia.gtnh.append.common.machine.multiblock.GT_TileEntity_CasimirMacroFieldGenerator;
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
    public static ItemStack CasimirMacroFieldGenerator;
    //endregion

    public static void loadMachines() {
        AstraForge = new GT_TileEntity_AstraForge(17501, "MultiAstraForge", "星辉锻造台").getStackForm(1);
        AppendItemList.AstraForge.set(AstraForge);
        CasimirMacroFieldGenerator = new GT_TileEntity_CasimirMacroFieldGenerator(17502, "MultiCasimirMacroFieldGenerator", "卡西米尔宏场发生器").getStackForm(1);
        AppendItemList.CasimirMacroFieldGenerator.set(CasimirMacroFieldGenerator);

        InfiniteLiquidAirHatch = new GT_MetaTileEntity_Hatch_LiquidAir(17401, "InfiniteLiquidAirHatch", "无限压缩进气仓", 9)
            .getStackForm(1);
        AppendItemList.InfiniteLiquidAirHatch.set(InfiniteLiquidAirHatch);
    }

    // endregion
}
