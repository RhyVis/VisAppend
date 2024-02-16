package com.rhynia.gtnh.append.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

@SuppressWarnings("SpellCheckingInspection")
public class Config {

    // region RegionNames
    public static final String GENERAL = "General";
    public static final String RECIPE = "Recipe";
    // endregion

    // region Definition
    public static boolean Debug_Mode = false;
    // Recipes-IA
    public static boolean Recipes_IA_Wireless_Enable = true;
    // Recipes-TR
    public static boolean Reccipe_TR_DTC_Enable = true;
    public static int Recipe_TR_RecipeMult = 64;
    public static int Recipe_TR_OutputMult = 250;
    public static int Recipe_TR_CatalystACAMult = 4000;
    public static int Recipe_TR_CatalystACRMult = 2000;
    public static int Recipe_OPT_Mult = 4;
    // endregion

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        Debug_Mode = configuration.getBoolean("Debug_Mode", GENERAL, false, "调试模式");

        Recipes_IA_Wireless_Enable = configuration
            .getBoolean("Recipes_IA_Wireless_Enable", RECIPE, true, "是否启用无线仓的IA简化配方");
        Reccipe_TR_DTC_Enable = configuration.getBoolean("Reccipe_TR_DTC_Enable", RECIPE, true, "是否启用TR超维度催化剂配方");
        Recipe_TR_RecipeMult = configuration
            .getInt("Recipe_TR_RecipeMult", RECIPE, 64, 1, Integer.MAX_VALUE / 144 - 1, "TR进行单次合成时的倍率");
        Recipe_TR_OutputMult = configuration
            .getInt("Recipe_TR_OutputMult", RECIPE, 250, 1, Integer.MAX_VALUE / 144 - 1, "TR进行单次合成时输出催化剂的量，单位L");
        Recipe_TR_CatalystACAMult = configuration.getInt(
            "Recipe_TR_CatalystACAMult",
            RECIPE,
            4000,
            16,
            Integer.MAX_VALUE / 16 - 1,
            "TR进行单次合成时的ACA催化剂消耗，不受合成倍率影响，单位L");
        Recipe_TR_CatalystACRMult = configuration
            .getInt("Recipe_TR_CatalystACRMult", RECIPE, 250, 1, Integer.MAX_VALUE, "TR进行单次合成时的ACR催化剂消耗，不受合成倍率影响，单位L");
        Recipe_OPT_Mult = configuration
            .getInt("Recipe_OPT_Mult", RECIPE, 4, 1, Integer.MAX_VALUE / 16, "Recipe_OPT_Mult");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
