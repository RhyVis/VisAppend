package com.rhynia.gtnh.append.common.tile.base;

import static gregtech.api.util.GT_Utility.filterValidMTEs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.jetbrains.annotations.ApiStatus.OverrideOnly;
import org.jetbrains.annotations.NotNull;

import com.github.technus.tectech.thing.metaTileEntity.hatch.GT_MetaTileEntity_Hatch_DynamoMulti;
import com.gtnewhorizon.structurelib.alignment.constructable.IConstructable;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.rhynia.gtnh.append.api.process.processingLogic.VA_ProcessingLogic;

import gregtech.api.interfaces.IHatchElement;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_ExtendedPowerMultiBlockBase;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Dynamo;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.util.GT_Utility;
import gregtech.api.util.IGT_HatchAdder;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;

@SuppressWarnings("unused")
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
            || addExoticEnergyInputToMachineList(aTileEntity, aBaseCasingIndex)
            || addExoticDynamoToMachineList(aTileEntity, aBaseCasingIndex);
    }

    public boolean addEnergyInputToMachineListExtended(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return super.addEnergyInputToMachineList(aTileEntity, aBaseCasingIndex)
            || super.addExoticEnergyInputToMachineList(aTileEntity, aBaseCasingIndex);
    }

    public boolean addDynamoToMachineListExtended(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return super.addDynamoToMachineList(aTileEntity, aBaseCasingIndex)
            || addExoticDynamoToMachineList(aTileEntity, aBaseCasingIndex);
    }

    public boolean addExoticDynamoToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        if (aTileEntity == null) return false;
        IMetaTileEntity aMetaTileEntity = aTileEntity.getMetaTileEntity();
        if (aMetaTileEntity == null) return false;
        if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_DynamoMulti hatchDynamoMulti) {
            hatchDynamoMulti.updateTexture(aBaseCasingIndex);
            hatchDynamoMulti.updateCraftingIcon(this.getMachineCraftingIcon());
            return mExoticDynamoHatches.add(hatchDynamoMulti);
        }
        return false;
    }

    protected List<GT_MetaTileEntity_Hatch_DynamoMulti> mExoticDynamoHatches = new ArrayList<>();

    protected enum HatchElement implements IHatchElement<VA_MetaTileEntity_MultiBlockBase<?>> {

        ExoticDynamo(VA_MetaTileEntity_MultiBlockBase::addDynamoToMachineList,
            GT_MetaTileEntity_Hatch_DynamoMulti.class) {

            @Override
            public long count(VA_MetaTileEntity_MultiBlockBase vaMultiBase) {
                return vaMultiBase.mExoticDynamoHatches.size();
            }
        };

        private final List<Class<? extends IMetaTileEntity>> mteClasses;
        private final IGT_HatchAdder<VA_MetaTileEntity_MultiBlockBase<?>> adder;

        @SafeVarargs
        HatchElement(IGT_HatchAdder<VA_MetaTileEntity_MultiBlockBase<?>> adder,
            Class<? extends IMetaTileEntity>... mteClasses) {
            this.adder = adder;
            this.mteClasses = Collections.unmodifiableList(Arrays.asList(mteClasses));
        }

        @Override
        public List<? extends Class<? extends IMetaTileEntity>> mteClasses() {
            return mteClasses;
        }

        @Override
        public IGT_HatchAdder<? super VA_MetaTileEntity_MultiBlockBase<?>> adder() {
            return adder;
        }

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
    @OverrideOnly
    protected boolean useAltLogic() {
        return true;
    }

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return useAltLogic() ? new VA_ProcessingLogic() {

            @NotNull
            @Override
            public CheckRecipeResult process() {
                setEuModifier(rEUModifier());
                setMaxParallel(rMaxParallel());
                setSpeedBonus(rSpeedBonus());
                setOverclock(rPerfectOverclock() ? 2 : 1, 2);
                return super.process();
            }
        } : new ProcessingLogic() {

            @NotNull
            @Override
            public CheckRecipeResult process() {
                setEuModifier(rEUModifier());
                setMaxParallel(rMaxParallel());
                setSpeedBonus(rSpeedBonus());
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

    /**
     * Extend support for ExoticDynamo
     */
    @Override
    public boolean addEnergyOutputMultipleDynamos(long totalEU, boolean aAllowMixedVoltageDynamos) {
        // Check positive
        if (totalEU < 0) totalEU = -totalEU;
        // Store free capacity of dynamo hatch
        long freeCapacity;
        // Check Normal Dynamo
        for (GT_MetaTileEntity_Hatch_Dynamo tHatch : filterValidMTEs(mDynamoHatches)) {
            freeCapacity = tHatch.maxEUStore() - tHatch.getBaseMetaTileEntity()
                .getStoredEU();
            if (freeCapacity > 0) {
                if (totalEU > freeCapacity) {
                    tHatch.setEUVar(tHatch.maxEUStore());
                    totalEU -= freeCapacity;
                } else {
                    tHatch.setEUVar(
                        tHatch.getBaseMetaTileEntity()
                            .getStoredEU() + totalEU);
                    return true;
                }
            }
        }
        // Check Multi Dynamo
        for (GT_MetaTileEntity_Hatch_DynamoMulti tHatch : filterValidMTEs(mExoticDynamoHatches)) {
            freeCapacity = tHatch.maxEUStore() - tHatch.getBaseMetaTileEntity()
                .getStoredEU();
            if (freeCapacity > 0) {
                if (totalEU > freeCapacity) {
                    tHatch.setEUVar(tHatch.maxEUStore());
                    totalEU -= freeCapacity;
                } else {
                    tHatch.setEUVar(
                        tHatch.getBaseMetaTileEntity()
                            .getStoredEU() + totalEU);
                    return true;
                }
            }
        }
        return false;
    }

    // endregion

    // region ToolTips and Info
    @Override
    public String[] getInfoData() {
        String dSpeed = String.format("%.3f", this.rSpeedBonus() * 100) + "%";
        String dEUMod = String.format("%.3f", this.rEUModifier() * 100) + "%";

        String[] oStr = super.getInfoData();
        String[] nStr = new String[oStr.length + 3];
        System.arraycopy(oStr, 0, nStr, 0, oStr.length);
        nStr[oStr.length] = EnumChatFormatting.AQUA + "最大并行: "
            + EnumChatFormatting.GOLD
            + GT_Utility.formatNumbers(this.rMaxParallel());
        nStr[oStr.length + 1] = EnumChatFormatting.AQUA + "速度乘数: " + EnumChatFormatting.GOLD + dSpeed;
        nStr[oStr.length + 2] = EnumChatFormatting.AQUA + "功率乘数: " + EnumChatFormatting.GOLD + dEUMod;
        return nStr;
    }

    @Override
    public void getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor,
        IWailaConfigHandler config) {
        super.getWailaBody(itemStack, currentTip, accessor, config);
    }

    @Override
    public void getWailaNBTData(EntityPlayerMP player, TileEntity tile, NBTTagCompound tag, World world, int x, int y,
        int z) {
        super.getWailaNBTData(player, tile, tag, world, x, y, z);
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
    }
    // endregion
}
