package com.rhynia.gtnh.append.util;

import static com.rhynia.gtnh.append.VisAppend.MOD_ID;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import com.gtnewhorizons.modularui.api.drawable.UITexture;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;

import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;

public class UtilValues {

    // 字段
    public static final String VisAppendID = MOD_ID;
    public static final String VisAppendMagical = EnumChatFormatting.AQUA + "Vis"
        + EnumChatFormatting.RED
        + "Append"
        + EnumChatFormatting.GRAY
        + " - "
        + EnumChatFormatting.GREEN
        + "Magical Reveal";
    public static final String VisAppendNuclear = EnumChatFormatting.AQUA + "Vis"
        + EnumChatFormatting.RED
        + "Append"
        + EnumChatFormatting.GRAY
        + " - "
        + EnumChatFormatting.GREEN
        + "Thermonuclear Reaction";
    public static final String VisAppendGigaFac = EnumChatFormatting.AQUA + "Vis"
        + EnumChatFormatting.RED
        + "Append"
        + EnumChatFormatting.GRAY
        + " - "
        + EnumChatFormatting.GREEN
        + "Giga Factory";
    public static final String AddByAppend = "由 " + EnumChatFormatting.GREEN
        + "Vis Append"
        + EnumChatFormatting.GRAY
        + " 添加";
    public static final String BluePrintInfo = "如" + EnumChatFormatting.BLUE
        + "蓝"
        + EnumChatFormatting.AQUA
        + "图"
        + EnumChatFormatting.GRAY
        + "所示相对位置";
    public static final String BluePrintTip = "请参考" + EnumChatFormatting.BLUE
        + "Structure"
        + EnumChatFormatting.DARK_BLUE
        + "Lib"
        + EnumChatFormatting.GRAY
        + "全息投影，构建主体结构";
    public static final String StructureTooComplex = "结构太复杂了!";

    // 物品
    public static final ItemStack lensMagic = GT_Utility
        .copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 1));
    public static final ItemStack lensInf = GT_Utility
        .copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 1));
    // 图标
    public static final UITexture VA_LOGO_32 = UITexture.fullImage("append", "gui/picture/va_logo_32_t");
}
