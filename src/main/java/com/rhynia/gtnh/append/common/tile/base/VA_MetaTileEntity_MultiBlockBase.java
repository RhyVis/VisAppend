package com.rhynia.gtnh.append.common.tile.base;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.jetbrains.annotations.ApiStatus.OverrideOnly;
import org.jetbrains.annotations.NotNull;

import com.gtnewhorizon.structurelib.alignment.constructable.IConstructable;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_ExtendedPowerMultiBlockBase;
import gregtech.api.recipe.check.CheckRecipeResult;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;

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
     * Shared name for simple structure shape
     */
    protected static final String STRUCTURE_PIECE_MAIN = "main";

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

    /**
     * Universal Hatch Adder
     */
    @Override
    public boolean addToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return super.addToMachineList(aTileEntity, aBaseCasingIndex)
            || addExoticEnergyInputToMachineList(aTileEntity, aBaseCasingIndex);
    }

    public boolean addEnergyInputToMachineListExtended(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return super.addEnergyInputToMachineList(aTileEntity, aBaseCasingIndex)
            || super.addExoticEnergyInputToMachineList(aTileEntity, aBaseCasingIndex);
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
    @OverrideOnly
    protected boolean rPerfectOverclock() {
        return false;
    }

    /**
     * Set EU Modifier
     *
     * @return Multiplier applied to total EU
     */
    @OverrideOnly
    protected float rEUModifier() {
        return 1.0F;
    }

    /**
     * Set Max parallel
     *
     * @return Max parallel for the machine
     */
    @OverrideOnly
    protected int rMaxParallel() {
        return 1;
    }

    /**
     * Set Duration Modifier
     *
     * @return Multiplier applied to duration
     */
    @OverrideOnly
    protected float rSpeedBonus() {
        return 1;
    }
    // endregion

    // region ToolTips and Info
    @Override
    public String[] getInfoData() {
        String[] oStr = super.getInfoData();
        String[] nStr = new String[oStr.length + 3];
        System.arraycopy(oStr, 0, nStr, 0, oStr.length);
        nStr[oStr.length] = EnumChatFormatting.AQUA + "最大并行" + ": " + EnumChatFormatting.GOLD + this.rMaxParallel();
        nStr[oStr.length + 1] = EnumChatFormatting.AQUA + "速度乘数" + ": " + EnumChatFormatting.GOLD + this.rSpeedBonus();
        nStr[oStr.length + 2] = EnumChatFormatting.AQUA + "功率乘数" + ": " + EnumChatFormatting.GOLD + this.rEUModifier();
        return nStr;
    }

    @Override
    public void getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor,
        IWailaConfigHandler config) {
        super.getWailaBody(itemStack, currentTip, accessor, config);
    }
    // endregion
}
