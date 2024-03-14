package com.rhynia.wireless_ext.common.loader.control;

import com.rhynia.wireless_ext.common.loader.VA_WirelessExtraItemList;
import com.rhynia.wireless_ext.common.tile.base.VA_MetaTileEntity_Hatch_WirelessMulti;

import gregtech.api.util.GT_Utility;

public class WirelessExtraLoader {

    private static final int OFFSET = 17420;

    private static String tierName(int tier) {
        return switch (tier) {
            case 5 -> "IV";
            case 6 -> "LuV";
            case 7 -> "ZPM";
            case 8 -> "UV";
            case 9 -> "UHV";
            case 10 -> "UIV";
            case 11 -> "UEV";
            case 12 -> "UMV";
            default -> "?";
        };
    }

    public static void doRegister() {
        for (VA_WirelessExtraItemList extW : VA_WirelessExtraItemList.values()) {
            extW.set(
                new VA_MetaTileEntity_Hatch_WirelessMulti(
                    OFFSET + extW.ordinal(),
                    "hatch." + extW + ".tier." + extW.getTier(),
                    tierName(extW.getTier()) + " " + GT_Utility.formatNumbers(extW.getAmp()) + "A/t 无线能源仓",
                    extW.getTier(),
                    extW.getAmp()).getStackForm(1L));
        }
    }
}
