package com.rhynia.gtnh.append.common.tile.multiMachine.generation;

import java.math.BigInteger;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.modularui.common.widget.DynamicPositionedColumn;
import com.gtnewhorizons.modularui.common.widget.FakeSyncWidget;
import com.gtnewhorizons.modularui.common.widget.SlotWidget;
import com.gtnewhorizons.modularui.common.widget.TextWidget;
import com.rhynia.gtnh.append.api.enums.VA_Values;
import com.rhynia.gtnh.append.api.util.MathHelper;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase_Cube;

import gregtech.api.GregTech_API;
import gregtech.api.interfaces.IGlobalWirelessEnergy;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_InputBus;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.recipe.check.SimpleCheckRecipeResult;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;

public class VA_TileEntity_SelectedEnergyGenerator extends
    VA_MetaTileEntity_MultiBlockBase_Cube<VA_TileEntity_SelectedEnergyGenerator> implements IGlobalWirelessEnergy {

    // region Builder
    public VA_TileEntity_SelectedEnergyGenerator(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_SelectedEnergyGenerator(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_SelectedEnergyGenerator(this.mName);
    }
    // endregion

    // region Process
    private int pMultiplier = 0;
    private int pBase = 0;
    private int pValue = 0;
    private BigInteger pEnergy = BigInteger.ZERO;
    private String pUUID = "";

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return null;
    }

    @Override
    @NotNull
    public CheckRecipeResult checkProcessing() {
        pMultiplier = pBase = pValue = 0;
        pEnergy = BigInteger.ZERO;
        mMaxProgresstime = 128;
        mEfficiencyIncrease = 10000;
        ItemStack controlStack = getControllerSlot();

        for (GT_MetaTileEntity_Hatch_InputBus inputBus : mInputBusses) {
            for (ItemStack itemStack : inputBus.getRealInventory()) {
                if (GT_Utility.isAnyIntegratedCircuit(itemStack)) {
                    pMultiplier += MathHelper.clampInt(itemStack.getItemDamage(), 0, 24);
                }
            }
        }

        if (controlStack == null) {
            pBase = 1;
        } else if (controlStack.isItemEqual(VAItemList.AstriumInfinityGem.get(1))) {
            pBase = (int) Math.pow(2, controlStack.stackSize);
        }

        pValue = pBase * pMultiplier;

        if (pValue != 0) {
            pEnergy = BigInteger.valueOf(Integer.MAX_VALUE)
                .multiply(BigInteger.valueOf(pValue));
            if (!pUUID.isBlank()) {
                addEUToGlobalEnergyMap(pUUID, pEnergy);
                return SimpleCheckRecipeResult.ofSuccess("importing_energy");
            }
            return SimpleCheckRecipeResult.ofFailure("no_user");
        }

        return CheckRecipeResultRegistry.NO_RECIPE;
    }

    @Override
    public void onPreTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        super.onPreTick(aBaseMetaTileEntity, aTick);
        if (aTick == 1) {
            pUUID = String.valueOf(getBaseMetaTileEntity().getOwnerUuid());
            String userName = getBaseMetaTileEntity().getOwnerName();
            strongCheckOrAddUser(pUUID, userName);
        }
    }

    // endregion

    // region Structure
    @Override
    protected Block sCasingBlock() {
        return GregTech_API.sBlockCasings8;
    }

    @Override
    protected Block sCoreBlock() {
        return null;
    }

    @Override
    protected int sCasingIndex() {
        return 176;
    }

    @Override
    protected int sCasingBlockMeta() {
        return 0;
    }

    @Override
    protected int sCoreBlockMeta() {
        return 0;
    }
    // endregion

    // region Info
    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("虚空发电机")
            .addInfo("虚空发电机的控制器")
            .addInfo("使用电路板编号*电路板数量指定输出电量.")
            .addInfo("在控制器内放入" + EnumChatFormatting.WHITE + "星极" + EnumChatFormatting.GRAY + "来决定基数幂")
            .addInfo("产出的能量将直接输出至无线网络.")
            .addSeparator()
            .addInfo(VA_Values.CommonStrings.BluePrintTip)
            .beginStructureBlock(3, 3, 7, false)
            .addInputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .toolTipFinisher(VA_Values.CommonStrings.VisAppendMagical);
        return tt;
    }

    @Override
    protected void drawTexts(DynamicPositionedColumn screenElements, SlotWidget inventorySlot) {
        super.drawTexts(screenElements, inventorySlot);
        screenElements
            .widget(
                new TextWidget(
                    StatCollector.translateToLocal("va.gui.desc.import_energy") + ": "
                        + GT_Utility.formatNumbers(pValue)
                        + "MAX EU/Process").setDefaultColor(COLOR_TEXT_WHITE.get())
                            .setEnabled(widget -> getBaseMetaTileEntity().getErrorDisplayID() == 0))
            .widget(new FakeSyncWidget.IntegerSyncer(() -> pValue, val -> pValue = val));
    }

    @Override
    public String[] getInfoData() {
        String[] oStr = super.getInfoData();
        String[] nStr = new String[oStr.length + 1];
        System.arraycopy(oStr, 0, nStr, 0, oStr.length);
        nStr[oStr.length] = EnumChatFormatting.AQUA + "Probably Generates"
            + ": "
            + EnumChatFormatting.GOLD
            + GT_Utility.formatNumbers(pValue)
            + "MAX EU/Process";
        return nStr;
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setInteger("pMultiplier", pMultiplier);
        aNBT.setInteger("pBase", pBase);
        aNBT.setInteger("pValue", pValue);
        aNBT.setByteArray("pEnergy", pEnergy.toByteArray());
        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        pMultiplier = aNBT.getInteger("pMultiplier");
        pBase = aNBT.getInteger("pBase");
        pValue = aNBT.getInteger("pValue");
        pEnergy = new BigInteger(aNBT.getByteArray("pEnergy"));
        super.loadNBTData(aNBT);
    }
    // endregion
}
