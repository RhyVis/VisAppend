package com.rhynia.gtnh.append.util;

// spotless:off

import static com.rhynia.gtnh.append.util.UtilTextHandler.texter;

import net.minecraft.util.EnumChatFormatting;

/**
 * <li>It's best to write here the texts that need auto generate en_US.lang .
 * <li>Or some usually used texts.
 */
public class UtilLocal {

    public static final String ModName = "GTNH: Append";
    public static final String textUseBlueprint = texter(
        "Use " + EnumChatFormatting.BLUE
            + "Blue"
            + EnumChatFormatting.AQUA
            + "print"
            + EnumChatFormatting.RESET
            + " to preview",
        "textUseBlueprint");
    public static final String StructureTooComplex = texter("The structure is too complex!", "StructureTooComplex");

    // region Astra Forge
    public static final String NameAstraForge = texter("Astra Forge", "NameAstraForge");
    public static final String Tooltip_AstraForge_MachineType = texter(
        "Grand Astra Forge",
        "Tooltip_AstraForge_MachineType");
    public static final String Tooltip_AstraForge_00 = texter(
        "Controller block for the Astra Forge",
        "Tooltip_AstraForge_00");
    public static final String Tooltip_AstraForge_01 = texter(
        EnumChatFormatting.RED + "Don't try to understand its principle!",
        "Tooltip_AstraForge_01");
    public static final String Tooltip_AstraForge_02 = texter(
        "Use starlight to transform the ordinary to miracles.",
        "Tooltip_AstraForge_02");
    public static final String Tooltip_AstraForge_03 = texter(
        "Has parallel equivalent to Perfect Overclock.",
        "Tooltip_AstraForge_03");
    public static final String Tooltip_AstraForge_04 = texter(
        "Additional 20%% reduction in time per Voltage Tier, multiplication calculus.",
        "Tooltip_AstraForge_04");

    // endregion
}
