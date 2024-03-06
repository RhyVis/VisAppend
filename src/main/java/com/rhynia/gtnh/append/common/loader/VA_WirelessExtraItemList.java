package com.rhynia.gtnh.append.common.loader;

import static gregtech.api.enums.GT_Values.W;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import gregtech.api.interfaces.IItemContainer;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;

@SuppressWarnings({ "deprecation", "unused" })
public enum VA_WirelessExtraItemList implements IItemContainer {

    // region ExtWireless
    extLaser_IV_1(5, 256),
    extLaser_IV_2(5, 1024),
    extLaser_IV_3(5, 4096),
    extLaser_IV_4(5, 16384),
    extLaser_IV_5(5, 65536),
    extLaser_IV_6(5, 262144),
    extLaser_IV_7(5, 1048576),
    extLaser_LuV_1(6, 256),
    extLaser_LuV_2(6, 1024),
    extLaser_LuV_3(6, 4096),
    extLaser_LuV_4(6, 16384),
    extLaser_LuV_5(6, 65536),
    extLaser_LuV_6(6, 262144),
    extLaser_LuV_7(6, 1048576),
    extLaser_ZPM_1(7, 256),
    extLaser_ZPM_2(7, 1024),
    extLaser_ZPM_3(7, 4096),
    extLaser_ZPM_4(7, 16384),
    extLaser_ZPM_5(7, 65536),
    extLaser_ZPM_6(7, 262144),
    extLaser_ZPM_7(7, 1048576),
    extLaser_UV_1(8, 256),
    extLaser_UV_2(8, 1024),
    extLaser_UV_3(8, 4096),
    extLaser_UV_4(8, 16384),
    extLaser_UV_5(8, 65536),
    extLaser_UV_6(8, 262144),
    extLaser_UV_7(8, 1048576),
    extLaser_UHV_1(9, 256),
    extLaser_UHV_2(9, 1024),
    extLaser_UHV_3(9, 4096),
    extLaser_UHV_4(9, 16384),
    extLaser_UHV_5(9, 65536),
    extLaser_UHV_6(9, 262144),
    extLaser_UHV_7(9, 1048576),
    extLaser_UEV_1(10, 256),
    extLaser_UEV_2(10, 1024),
    extLaser_UEV_3(10, 4096),
    extLaser_UEV_4(10, 16384),
    extLaser_UEV_5(10, 65536),
    extLaser_UEV_6(10, 262144),
    extLaser_UEV_7(10, 1048576),
    extLaser_UIV_1(11, 256),
    extLaser_UIV_2(11, 1024),
    extLaser_UIV_3(11, 4096),
    extLaser_UIV_4(11, 16384),
    extLaser_UIV_5(11, 65536),
    extLaser_UIV_6(11, 262144),
    extLaser_UIV_7(11, 1048576),
    extLaser_UMV_1(12, 256),
    extLaser_UMV_2(12, 1024),
    extLaser_UMV_3(12, 4096),
    extLaser_UMV_4(12, 16384),
    extLaser_UMV_5(12, 65536),
    extLaser_UMV_6(12, 262144),
    extLaser_UMV_7(12, 1048576),;
    // endregion;

    // endregion

    // region Member Variables
    private boolean mHasNotBeenSet;
    private boolean mDeprecated;
    private boolean mWarned;
    private int tier;
    private int amp;

    private ItemStack mStack;
    // endregion

    VA_WirelessExtraItemList() {
        mHasNotBeenSet = true;
    }

    VA_WirelessExtraItemList(int tierVal, int ampVal) {
        mHasNotBeenSet = true;
        tier = tierVal;
        amp = ampVal;
    }

    VA_WirelessExtraItemList(boolean aDeprecated) {
        if (aDeprecated) {
            mDeprecated = true;
            mHasNotBeenSet = true;
        }
    }

    public int getTier() {
        return this.tier;
    }

    public int getAmp() {
        return this.amp;
    }

    @Override
    public Item getItem() {
        sanityCheck();
        if (GT_Utility.isStackInvalid(mStack)) return null;
        return mStack.getItem();
    }

    @Override
    public Block getBlock() {
        sanityCheck();
        return Block.getBlockFromItem(getItem());
    }

    @Override
    public boolean isStackEqual(Object aStack) {
        return isStackEqual(aStack, false, false);
    }

    @Override
    public boolean isStackEqual(Object aStack, boolean aWildcard, boolean aIgnoreNBT) {
        if (mDeprecated && !mWarned) {
            new Exception(this + " is now deprecated").printStackTrace(GT_Log.err);
            // warn only once
            mWarned = true;
        }
        if (GT_Utility.isStackInvalid(aStack)) return false;
        return GT_Utility.areUnificationsEqual((ItemStack) aStack, aWildcard ? getWildcard(1) : get(1), aIgnoreNBT);
    }

    @Override
    public ItemStack get(long aAmount, Object... aReplacements) {
        sanityCheck();
        // if invalid, return a replacements
        if (GT_Utility.isStackInvalid(mStack)) {
            GT_Log.out.println("Object in the ItemList is null at:");
            new NullPointerException().printStackTrace(GT_Log.out);
            return GT_Utility.copyAmount(aAmount, aReplacements);
        }
        return GT_Utility.copyAmount(aAmount, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getWildcard(long aAmount, Object... aReplacements) {
        sanityCheck();
        if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, aReplacements);
        return GT_Utility.copyAmountAndMetaData(aAmount, W, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getUndamaged(long aAmount, Object... aReplacements) {
        sanityCheck();
        if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, aReplacements);
        return GT_Utility.copyAmountAndMetaData(aAmount, 0, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getAlmostBroken(long aAmount, Object... aReplacements) {
        sanityCheck();
        if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, aReplacements);
        return GT_Utility.copyAmountAndMetaData(aAmount, mStack.getMaxDamage() - 1, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getWithDamage(long aAmount, long aMetaValue, Object... aReplacements) {
        sanityCheck();
        if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, aReplacements);
        return GT_Utility.copyAmountAndMetaData(aAmount, aMetaValue, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public IItemContainer set(Item aItem) {
        mHasNotBeenSet = false;
        if (aItem == null) return this;
        ItemStack aStack = new ItemStack(aItem, 1, 0);
        mStack = GT_Utility.copyAmount(1, aStack);
        return this;
    }

    @Override
    public IItemContainer set(ItemStack aStack) {
        if (aStack != null) {
            mHasNotBeenSet = false;
            mStack = GT_Utility.copyAmount(1, aStack);
        }
        return this;
    }

    @Override
    public IItemContainer registerOre(Object... aOreNames) {
        return null;
    }

    @Override
    public IItemContainer registerWildcardAsOre(Object... aOreNames) {
        return null;
    }

    @Override
    public ItemStack getWithCharge(long aAmount, int aEnergy, Object... aReplacements) {
        return null;
    }

    @Override
    public ItemStack getWithName(long aAmount, String aDisplayName, Object... aReplacements) {
        return null;
    }

    @Override
    public boolean hasBeenSet() {
        return !mHasNotBeenSet;
    }

    /**
     * Returns the internal stack. This method is unsafe. It's here only for quick operations. DON'T CHANGE THE RETURNED
     * VALUE!
     */
    public ItemStack getInternalStack_unsafe() {
        return mStack;
    }

    private void sanityCheck() {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        if (mDeprecated && !mWarned) {
            new Exception(this + " is now deprecated").printStackTrace(GT_Log.err);
            // warn only once
            mWarned = true;
        }
    }
}
