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
public enum VA_ItemList implements IItemContainer {

    // region Regular Item
    ItemUltimate,
    // endregion

    // region MetaItem01
    // res
    Test01,
    LapoMatrix,
    CrystalMatrix,
    DenseMicaInsulatorFoil,
    PreTesseract,

    // res
    AstriumInfinityGem,
    AstriumInfinityComplex,
    AstriumInfinityGauge,

    LensAstriumInfinity,
    LensAstriumMagic,
    LensPrimoium,
    LensOriginium,

    // region MetaItem02
    Calibration,
    Assembly_DTPF,
    // endregion

    // region Block
    Test_MetaBlock_01,
    EOH_Core_T1,
    EOH_Core_T2,
    EOH_Core_T3,
    EOH_Core_T4,
    EOH_Core_T5,
    EOH_Core_T6,
    EOH_Core_T7,
    EOH_Core_T8,
    EOH_Core_T9,
    // endregion

    // region Machine Controller
    AstraForge,
    UltimateHeater,
    AssemblyMatrix,
    VoidMiner,
    KelvinTransformField,
    ReinforcedAssemblyLine,
    SelectedEnergyGenerator,
    Creator,
    EyeOfUltimate,
    DenseEndpoint,
    ProcessingMatrix,

    // Sig Block
    InfiniteLiquidAirHatch,
    InfiniteDistilledWaterHatch,
    InfiniteLavaHatch,
    InfiniteOilHatch,
    InfiniteLubricantHatch,
    InfiniteSteamHatch,
    ZeroGenerator,
    HumongousCalibrationInputHatch,
    HumongousCalibrationHalfInputHatch,;
    // endregion;

    // endregion

    // region Member Variables
    private boolean mHasNotBeenSet;
    private boolean mDeprecated;
    private boolean mWarned;

    private ItemStack mStack;
    // endregion

    VA_ItemList() {
        mHasNotBeenSet = true;
    }

    VA_ItemList(boolean aDeprecated) {
        if (aDeprecated) {
            mDeprecated = true;
            mHasNotBeenSet = true;
        }
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
