package com.rhynia.gtnh.append.util;

import com.rhynia.gtnh.append.common.material.MaterialGTMethod;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class UtilValues {

    // 字段
    public static final String AddByAppend = "由 " + EnumChatFormatting.GREEN
        + "GTNH: Append"
        + EnumChatFormatting.GRAY
        + " 添加";

    // 物品
    public static final ItemStack lensMagic = GT_Utility
        .copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroMagic, 1));
    public static final ItemStack lensInf = GT_Utility
        .copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, MaterialGTMethod.AstroInf, 1));
}
