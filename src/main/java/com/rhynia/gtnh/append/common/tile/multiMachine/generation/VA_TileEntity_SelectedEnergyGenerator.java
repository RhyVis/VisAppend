package com.rhynia.gtnh.append.common.tile.multiMachine.generation;

import java.math.BigInteger;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.jetbrains.annotations.NotNull;

import com.rhynia.gtnh.append.api.enums.VA_Values;
import com.rhynia.gtnh.append.api.util.ItemHelper;
import com.rhynia.gtnh.append.api.util.MathHelper;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase_Cube;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IGlobalWirelessEnergy;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_InputBus;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.SimpleCheckRecipeResult;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;
import gregtech.common.blocks.GT_Block_Casings1;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;

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
    private int pCoreValue = 0;
    private int pTweakValue = 0;
    private long pBaseValue = 0L;
    private BigInteger pConstruct = BigInteger.ZERO;
    private BigInteger pEnergy = BigInteger.ZERO;
    private String pUUID = "";

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return null;
    }

    @Override
    @NotNull
    public CheckRecipeResult checkProcessing() {
        resetState();
        ItemStack controlStack = getControllerSlot();

        if (ItemHelper.isAstralInfinityComplex(controlStack)) {
            pCoreValue = controlStack.stackSize;
        } else {
            return SimpleCheckRecipeResult.ofFailure("no_core_set");
        }

        for (GT_MetaTileEntity_Hatch_InputBus inputBus : mInputBusses) {
            for (ItemStack itemStack : inputBus.getRealInventory()) {
                if (ItemHelper.isCalibration(itemStack)) {
                    pBaseValue += itemStack.stackSize;
                }
                if (pTweakValue <= 0 && GT_Utility.isAnyIntegratedCircuit(itemStack)) {
                    pTweakValue = MathHelper.clampVal(itemStack.getItemDamage(), 2, 24);
                }
            }
        }

        if (pBaseValue <= 0) {
            pBaseValue = 1L;
        }
        if (pTweakValue <= 0) {
            pTweakValue = 1;
        }

        pConstruct = BigInteger.valueOf(pBaseValue)
            .multiply(
                BigInteger.valueOf(pTweakValue)
                    .pow(pCoreValue));
        pEnergy = BigInteger.valueOf(Integer.MAX_VALUE)
            .multiply(pConstruct)
            .multiply(BigInteger.valueOf(128));

        addEUToGlobalEnergyMap(pUUID, pEnergy);

        return SimpleCheckRecipeResult.ofSuccess("importing_energy");
    }

    private void resetState() {
        pCoreValue = 0;
        pTweakValue = 0;
        pBaseValue = 0L;
        pConstruct = BigInteger.ZERO;
        pEnergy = BigInteger.ZERO;
        mMaxProgresstime = 128;
        mEfficiencyIncrease = 10000;
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

    @Override
    public boolean supportsVoidProtection() {
        return false;
    }

    @Override
    public boolean supportsInputSeparation() {
        return false;
    }

    @Override
    public boolean supportsBatchMode() {
        return false;
    }

    @Override
    public boolean supportsSingleRecipeLocking() {
        return false;
    }

    // endregion

    // region Structure

    @Override
    protected Block sCasingBlock() {
        return GregTech_API.sBlockCasings1;
    }

    @Override
    protected int sCasingIndex() {
        return ((GT_Block_Casings1) GregTech_API.sBlockCasings1).getTextureIndex(12);
    }

    @Override
    protected int sCasingBlockMeta() {
        return 12;
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        if (sideDirection == facingDirection) {
            if (active) return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(sCasingBlock(), sCasingBlockMeta())),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_DTPF_ON)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_DTPF_ON)
                    .extFacing()
                    .glow()
                    .build() };
            return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(sCasingBlock(), sCasingBlockMeta())),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_DTPF_OFF)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_DTPF_OFF)
                    .extFacing()
                    .glow()
                    .build() };
        }
        return new ITexture[] { Textures.BlockIcons
            .getCasingTextureForId(GT_Utility.getCasingTextureIndex(sCasingBlock(), sCasingBlockMeta())) };
    }

    // endregion

    // region Info
    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("虚空发电机")
            .addInfo("虚空发电机的控制器")
            .addInfo("发电 = 电路板编号^星矩 * 标定指示 * MAX A/t.")
            .addInfo("产出的能量将直接输出至无线网络.")
            .addSeparator()
            .addInfo(VA_Values.CommonStrings.BluePrintTip)
            .beginStructureBlock(3, 3, 3, false)
            .addInputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .toolTipFinisher(VA_Values.CommonStrings.VisAppendMagical);
        return tt;
    }

    @Override
    public String[] getInfoData() {
        String[] oStr = super.getInfoData();
        String[] nStr = new String[oStr.length + 1];
        System.arraycopy(oStr, 0, nStr, 0, oStr.length);
        nStr[oStr.length] = EnumChatFormatting.AQUA + "等效能量: "
            + EnumChatFormatting.GOLD
            + GT_Utility.formatNumbers(pConstruct)
            + "MAX EU/t";
        return nStr;
    }

    @Override
    public void getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor,
        IWailaConfigHandler config) {
        super.getWailaBody(itemStack, currentTip, accessor, config);
        final NBTTagCompound tag = accessor.getNBTData();
        currentTip.add(
            EnumChatFormatting.WHITE + "等效能量: "
                + EnumChatFormatting.AQUA
                + GT_Utility.formatNumbers(new BigInteger(tag.getByteArray("pConstructW")))
                + " "
                + EnumChatFormatting.WHITE
                + EnumChatFormatting.UNDERLINE
                + "MAX"
                + EnumChatFormatting.WHITE
                + " EU/t");
    }

    @Override
    public void getWailaNBTData(EntityPlayerMP player, TileEntity tile, NBTTagCompound tag, World world, int x, int y,
        int z) {
        super.getWailaNBTData(player, tile, tag, world, x, y, z);
        final IGregTechTileEntity tileEntity = getBaseMetaTileEntity();
        if (tileEntity != null) {
            if (tileEntity.isActive()) {
                tag.setByteArray("pConstructW", pConstruct.toByteArray());
            }
        }
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setInteger("pCoreValue", pCoreValue);
        aNBT.setInteger("pTweakValue", pTweakValue);
        aNBT.setLong("pBaseValue", pBaseValue);
        aNBT.setByteArray("pConstruct", pConstruct.toByteArray());
        aNBT.setByteArray("pEnergy", pEnergy.toByteArray());
        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        pCoreValue = aNBT.getInteger("pCoreValue");
        pTweakValue = aNBT.getInteger("pTweakValue");
        pBaseValue = aNBT.getLong("pBaseValue");
        pConstruct = new BigInteger(aNBT.getByteArray("pConstruct"));
        pEnergy = new BigInteger(aNBT.getByteArray("pEnergy"));
        super.loadNBTData(aNBT);
    }
    // endregion
}
