package com.rhynia.gtnh.append.api.util.recipeHelper;

import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.VAItemList;

import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;

@SuppressWarnings("unused")
public class GGChip {

    /**
     * Get a ItemStack of GG generated wrapped parts
     * Now stack larger than 64 are also supported
     *
     * @param aIndex  The tier of it, see GGChip
     * @param aAmount The amount of ItemStack
     * @author Rhynia
     * @since 0.6.14-pre
     */
    public static ItemStack getWrappedCircuit(Tier aIndex, int aAmount) {
        if (aAmount > 64) return GT_Utility.copyAmountUnsafe(
            aAmount,
            GT_ModHandler.getModItem("GoodGenerator", "circuitWrap", 1, aIndex.ordinal(), VAItemList.Test.get(1)));
        else return GT_ModHandler
            .getModItem("GoodGenerator", "circuitWrap", aAmount, aIndex.ordinal(), VAItemList.Test.get(1));
    }

    public enum Tier {
        ULV,
        LV,
        MV,
        HV,
        EV,
        IV,
        LuV,
        ZPM,
        UV,
        UHV,
        UEV,
        UIV,
        UMV,
        UXV,
        MAX
    }
}
