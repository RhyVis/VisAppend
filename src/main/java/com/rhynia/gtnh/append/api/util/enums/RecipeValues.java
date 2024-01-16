package com.rhynia.gtnh.append.api.util.enums;

import static gregtech.api.enums.GT_Values.V;

import gregtech.api.enums.GT_Values;

@SuppressWarnings("unused")
public class RecipeValues {

    // region Voltages
    public static final long ULV = V[0];
    public static final long LV = V[1];
    public static final long MV = V[2];
    public static final long HV = V[3];
    public static final long EV = V[4];
    public static final long IV = V[5];
    public static final long LuV = V[6];
    public static final long ZPM = V[7];
    public static final long UV = V[8];
    public static final long UHV = V[9];
    public static final long UEV = V[10];
    public static final long UIV = V[11];
    public static final long UMV = V[12];
    public static final long UXV = V[13];
    public static final long MAX = V[14];
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
    public static final int HOURS = 20 * 60 * 60;
    public static final int MINUTES = 20 * 60;
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
