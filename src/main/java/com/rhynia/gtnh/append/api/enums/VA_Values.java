package com.rhynia.gtnh.append.api.enums;

import static com.rhynia.gtnh.append.VisAppend.MOD_ID;

import net.minecraft.util.EnumChatFormatting;

import com.gtnewhorizons.modularui.api.drawable.UITexture;
import com.rhynia.gtnh.append.common.material.VAMaterials;

import gregtech.api.enums.GT_Values;

@SuppressWarnings("unused")
public class VA_Values {

    public static RecipeValues RV;
    public static TextureSets TS;
    public static CommonStrings CS;
    public static VAMaterials VM = new VAMaterials();

    public static class RecipeValues {

        // region Voltages
        public static final long ULV = GT_Values.V[0];
        public static final long LV = GT_Values.V[1];
        public static final long MV = GT_Values.V[2];
        public static final long HV = GT_Values.V[3];
        public static final long EV = GT_Values.V[4];
        public static final long IV = GT_Values.V[5];
        public static final long LuV = GT_Values.V[6];
        public static final long ZPM = GT_Values.V[7];
        public static final long UV = GT_Values.V[8];
        public static final long UHV = GT_Values.V[9];
        public static final long UEV = GT_Values.V[10];
        public static final long UIV = GT_Values.V[11];
        public static final long UMV = GT_Values.V[12];
        public static final long UXV = GT_Values.V[13];
        public static final long MAX = GT_Values.V[14];
        // endregion

        // region Recipe Voltages
        public static final long RECIPE_ULV = GT_Values.VP[0];
        public static final long RECIPE_LV = GT_Values.VP[1];
        public static final long RECIPE_MV = GT_Values.VP[2];
        public static final long RECIPE_HV = GT_Values.VP[3];
        public static final long RECIPE_EV = GT_Values.VP[4];
        public static final long RECIPE_IV = GT_Values.VP[5];
        public static final long RECIPE_LuV = GT_Values.VP[6];
        public static final long RECIPE_ZPM = GT_Values.VP[7];
        public static final long RECIPE_UV = GT_Values.VP[8];
        public static final long RECIPE_UHV = GT_Values.VP[9];
        public static final long RECIPE_UEV = GT_Values.VP[10];
        public static final long RECIPE_UIV = GT_Values.VP[11];
        public static final long RECIPE_UMV = GT_Values.VP[12];
        public static final long RECIPE_UXV = GT_Values.VP[13];
        public static final long RECIPE_MAX = GT_Values.VP[14];
        // endregion

        // region Unit
        public static final int WILDCARD = 32767;
        // Time
        /**
         * An hour
         */
        public static final int HOURS = 72000;
        /**
         * A minute
         */
        public static final int MINUTES = 120;
        /**
         * A second
         */
        public static final int SECONDS = 20;
        public static final int TICKS = 1;
        // Item
        public static final int STACKS = 64;
        // Fluid
        public static final int INGOTS = 144;
        public static final int HALF_INGOT = 72;
        public static final int QUARTER_INGOT = 36;
        public static final int EIGHTH_INGOT = 18;
        public static final int NUGGETS = 16;
        public static final int BUCKETS = 1000;
        // endregion

        // region Chance
        public static final int FullChance = 10000;
        // endregion
    }

    public static class TextureSets {

        // Textures Icon
        public static final UITexture VA_LOGO_32 = UITexture.fullImage("append", "gui/picture/va_logo_32_t");
    }

    public static class CommonStrings {

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
        public static final String ChangeModeByScrewdriver = "使用螺丝刀切换模式.";

    }
}
