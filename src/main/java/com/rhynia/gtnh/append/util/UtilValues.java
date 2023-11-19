package com.rhynia.gtnh.append.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;

import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;

public class UtilValues {

    // 字段
    public static final String AddByAppend = "由 " + EnumChatFormatting.GREEN
        + "Vis Append"
        + EnumChatFormatting.GRAY
        + " 添加";
    public static final String BluePrintInfo = "参考§9蓝§b图§r相对位置";
    public static final String BluePrintTip = "请参考§9Structure§1Lib§7全息投影，构建主体结构";
    public static final String StructureTooComplex = "结构太复杂了!";

    // 物品
    public static final ItemStack lensMagic = GT_Utility
        .copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 1));
    public static final ItemStack lensInf = GT_Utility
        .copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 1));
}
