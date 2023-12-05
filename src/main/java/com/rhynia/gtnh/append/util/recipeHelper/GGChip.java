package com.rhynia.gtnh.append.util.recipeHelper;

import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.VAItemList;

import gregtech.api.util.GT_ModHandler;

@SuppressWarnings("unused")
public class GGChip {

    /**
     * Get a ItemStack of GG generated wrapped parts
     * 
     * @param aIndex  The tier of it, see GGChip
     * @param aAmount The amount of ItemStack
     * @author Rhynia
     * @since 0.6.14-pre
     */
    public static ItemStack getWrappedCircuit(int aIndex, int aAmount) {
        return GT_ModHandler.getModItem("GoodGenerator", "circuitWrap", aAmount, aIndex, VAItemList.Test.get(1));
    }

    public static final int ULV = 0, LV = 1, MV = 2, HV = 3, EV = 4, IV = 5, LuV = 6, ZPM = 7, UV = 8, UHV = 9,
        UEV = 10, UIV = 11, UMV = 12, UXV = 13, MAX = 14;
}
