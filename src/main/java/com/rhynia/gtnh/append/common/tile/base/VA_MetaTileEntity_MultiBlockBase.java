package com.rhynia.gtnh.append.common.tile.base;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import com.gtnewhorizon.structurelib.alignment.constructable.IConstructable;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;

import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_ExtendedPowerMultiBlockBase;
import gregtech.api.recipe.check.CheckRecipeResult;

public abstract class VA_MetaTileEntity_MultiBlockBase<T extends GT_MetaTileEntity_ExtendedPowerMultiBlockBase<T>>
    extends GT_MetaTileEntity_ExtendedPowerMultiBlockBase<T> implements IConstructable, ISurvivalConstructable {

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
    protected void removeMaintenance() {
        mHardHammer = mSoftHammer = mScrewdriver = mCrowbar = mSolderingTool = mWrench = true;
    }

    @Override
    public boolean isCorrectMachinePart(ItemStack aStack) {
        return true;
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

    @Override
    public boolean supportsSingleRecipeLocking() {
        return true;
    }
    // endregion

    // region ProcessingLogic Helper
    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new ProcessingLogic() {

            @NotNull
            @Override
            public CheckRecipeResult process() {
                setEuModifier(rEUModifier());
                setMaxParallel(rMaxParallel());
                setOverclock(rPerfectOverclock() ? 2 : 1, 2);
                return super.process();
            }
        };
    }

    /**
     * Perfect OC
     *
     * @return if Perfect OC enabled
     */
    @ApiStatus.OverrideOnly
    protected boolean rPerfectOverclock() {
        return false;
    }

    /**
     * Set EU Modifier
     *
     * @return Multiplier applied to total EU
     */
    @ApiStatus.OverrideOnly
    protected float rEUModifier() {
        return 1.0F;
    }

    /**
     * Set Max parallel
     *
     * @return Max parallel for the machine
     */
    @ApiStatus.OverrideOnly
    protected abstract int rMaxParallel();

    /**
     * Set Duration Modifier
     *
     * @return Multiplier applied to duration
     */
    @ApiStatus.OverrideOnly
    protected abstract float rSpeedBonus();
    // endregion

    // region ToolTips and Info
    @Override
    public String[] getInfoData() {
        String[] origin = super.getInfoData();
        String[] ret = new String[origin.length + 3];
        System.arraycopy(origin, 0, ret, 0, origin.length);
        ret[origin.length] = EnumChatFormatting.AQUA + "Parallels"
            + ": "
            + EnumChatFormatting.GOLD
            + this.rMaxParallel();
        ret[origin.length + 1] = EnumChatFormatting.AQUA + "Speed Multiplier"
            + ": "
            + EnumChatFormatting.GOLD
            + this.rSpeedBonus();
        ret[origin.length + 2] = EnumChatFormatting.AQUA + "EU Modifier"
            + ": "
            + EnumChatFormatting.GOLD
            + this.rEUModifier();
        return ret;
        // endregion
    }
}
