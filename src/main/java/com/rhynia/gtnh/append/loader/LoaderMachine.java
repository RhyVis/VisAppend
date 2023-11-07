package com.rhynia.gtnh.append.loader;

import static com.rhynia.gtnh.append.util.UtilLocal.NameAstraForge;

import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.AppendItemList;
import com.rhynia.gtnh.append.common.machine.metablock.GT_TileEntity_AstraForge;

public class LoaderMachine {

    public static ItemStack AstraForge;

    // region multi Machine controller

    public static void loadMachines() {
        AstraForge = new GT_TileEntity_AstraForge(17501, "NameAstraForge", NameAstraForge).getStackForm(1);
        AppendItemList.AstraForge.set(AstraForge);
    }

    // endregion
}
