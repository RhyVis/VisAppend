package com.rhynia.gtnh.append.common.tile.base;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_ExtendedPowerMultiBlockBase;

public abstract class VA_MetaTileEntity_MultiBlockBase<T extends GT_MetaTileEntity_ExtendedPowerMultiBlockBase<T>>
    extends GT_MetaTileEntity_ExtendedPowerMultiBlockBase<T> {

    // region Builder
    protected VA_MetaTileEntity_MultiBlockBase(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    protected VA_MetaTileEntity_MultiBlockBase(String aName) {
        super(aName);
    }
    // endregion

    // region Function

    /**
     * Disable maintenance problems from happening.
     */
    protected void disableMaintenance() {
        mHardHammer = mSoftHammer = mScrewdriver = mCrowbar = mSolderingTool = mWrench = true;
    }
    // endregion

    // region Override Helper
    @Override
    public int getMaxEfficiency(ItemStack aStack) {
        return 10000;
    }

    @Override
    public int getDamageToComponent(ItemStack aStack) {
        return 0;
    }

    @Override
    public boolean explodesOnComponentBreak(ItemStack aStack) {
        return false;
    }

    @Override
    public boolean supportsVoidProtection() {
        return true;
    }

    @Override
    public boolean supportsInputSeparation() {
        return true;
    }

    @Override
    public boolean supportsBatchMode() {
        return true;
    }
    // endregion

    // region ProcessingLogic Helper
    protected int uParallel = 1;
    protected float uSpeed = 1.0F;

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new ProcessingLogic().setMaxParallel(getMaxParallel())
            .setSpeedBonus(getSpeedBonus());
    }

    protected int getMaxParallel() {
        return uParallel;
    }

    protected float getSpeedBonus() {
        return uSpeed;
    }
    // endregion

    // region ToolTips and Info
    @Override
    public String[] getInfoData() {
        String[] oStr = super.getInfoData();
        String[] nStr = new String[oStr.length + 2];
        System.arraycopy(oStr, 0, nStr, 0, oStr.length);
        nStr[oStr.length] = EnumChatFormatting.AQUA + "Parallel: " + EnumChatFormatting.GOLD + getMaxParallel();
        nStr[oStr.length + 1] = EnumChatFormatting.AQUA + "Recipe Time Multiplier: "
            + EnumChatFormatting.GOLD
            + getSpeedBonus();
        return nStr;
    }
    // endregion
}
