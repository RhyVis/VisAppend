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
    // Recipes-TR
    public static boolean Reccipe_TR_DTC_Enable = true;
    public static int Recipe_TR_RecipeMult = 32;
    public static int Recipe_TR_OutputMult = 250;
    public static int Recipe_TR_CatalystACAMult = 16000;
    public static int Recipe_TR_CatalystACRMult = 2000;
    // endregion

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        Reccipe_TR_DTC_Enable = configuration.getBoolean("Reccipe_TR_DTC_Enable", RECIPE, true, "是否启用TR超维度催化剂配方");
        Recipe_TR_RecipeMult = configuration.getInt("Recipe_TR_RecipeMult", RECIPE, 32, 1, 64, "TR进行单次合成时的倍率");
        Recipe_TR_OutputMult = configuration
            .getInt("Recipe_TR_OutputMult", RECIPE, 250, 1, Integer.MAX_VALUE / 144 - 1, "TR进行单次合成时输出催化剂的量，单位L");
        Recipe_TR_CatalystACAMult = configuration.getInt(
            "Recipe_TR_CatalystACAMult",
            RECIPE,
            16000,
            1,
            Integer.MAX_VALUE,
            "TR进行单次合成时的ACA催化剂消耗，不受合成倍率影响，单位L");
        Recipe_TR_CatalystACRMult = configuration
            .getInt("Recipe_TR_CatalystACRMult", RECIPE, 2000, 1, Integer.MAX_VALUE, "TR进行单次合成时的ACR催化剂消耗，不受合成倍率影响，单位L");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}