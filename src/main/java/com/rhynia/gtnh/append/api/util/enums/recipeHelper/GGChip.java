package com.rhynia.gtnh.append.api.util.enums.recipeHelper;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import com.rhynia.gtnh.append.api.frame.IRecipeHelper;
import com.rhynia.gtnh.append.common.VAItemList;

import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;

@SuppressWarnings("unused")
public enum GGChip implements IRecipeHelper {

    ULV,
    LV,
    MV,
    HV,
    EV,
    IV,
    LuV,
    ZPM,
    UV,
    UHV,
    UEV,
    UIV,
    UMV,
    UXV,
    MAX;

    /**
     * Get a ItemStack of GG generated wrapped parts
     * with stack larger than 64 are also supported
     *
     * @param aAmount The amount of ItemStack
     * @since 0.6.14-pre
     */
    @Override
    public ItemStack getItemStack(int aAmount) {
        if (aAmount > 64) return GT_Utility.copyAmountUnsafe(
            aAmount,
            GT_ModHandler.getModItem("GoodGenerator", "circuitWrap", 1, this.ordinal(), VAItemList.Test.get(1)));
        else return GT_ModHandler
            .getModItem("GoodGenerator", "circuitWrap", aAmount, this.ordinal(), VAItemList.Test.get(1));
    }

    @Override
    public FluidStack getFluidStack(int aAmount) {
        return Materials.Water.getFluid(1);
    }
}
