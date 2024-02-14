package com.rhynia.gtnh.append.common.tile.multiMachine.creation;

import static gregtech.api.metatileentity.BaseTileEntity.TOOLTIP_DELAY;

import java.util.Collections;
import java.util.List;

import gregtech.common.blocks.GT_Block_Casings8;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.modularui.api.screen.ModularWindow;
import com.gtnewhorizons.modularui.api.screen.UIBuildContext;
import com.gtnewhorizons.modularui.common.widget.CycleButtonWidget;
import com.rhynia.gtnh.append.api.enums.VA_Values;
import com.rhynia.gtnh.append.api.util.MathHelper;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase_Cube;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Textures;
import gregtech.api.gui.modularui.GT_UITextures;
import gregtech.api.interfaces.IGlobalWirelessEnergy;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_InputBus;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;
import gregtech.common.tileentities.machines.GT_MetaTileEntity_Hatch_OutputBus_ME;
import gregtech.common.tileentities.machines.GT_MetaTileEntity_Hatch_Output_ME;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;

public class VA_TileEntity_Creator extends VA_MetaTileEntity_MultiBlockBase_Cube<VA_TileEntity_Creator>
    implements IGlobalWirelessEnergy {

    // region Builder
    public VA_TileEntity_Creator(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_Creator(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_Creator(this.mName);
    }
    // endregion

    // region Process
    private boolean pItemProcess = true;
    private int pMultiplier = 0;
    private int pBase = 0;
    private long pProduce = 0L;
    private String pUUID = "";
    private String pName = "";
    private final FluidStack pFluidStackZeroPoint = Materials.Water.getFluid(1);
    private FluidStack pFluidStackStore = pFluidStackZeroPoint;
    private final ItemStack pItemStackZeroPoint = VAItemList.Test.get(1);
    private ItemStack pItemStackStore = pItemStackZeroPoint;

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return null;
    }

    @Override
    @NotNull
    public CheckRecipeResult checkProcessing() {
        resetState();
        ItemStack tempStack = getControllerSlot();

        for (GT_MetaTileEntity_Hatch_InputBus inputBus : mInputBusses) {
            for (ItemStack itemStack : inputBus.getRealInventory()) {
                if (itemStack != null && itemStack.isItemEqual(VAItemList.AstriumInfinityGem.get(1))) {
                    pBase += itemStack.stackSize;
                }
                if (GT_Utility.isAnyIntegratedCircuit(itemStack)) {
                    pMultiplier += MathHelper.clampInt(itemStack.getItemDamage(), 0, 24);
                }
            }
        }

        pProduce = (long) (pBase * Math.pow(2L, Math.min(48, pMultiplier)));
        if (pProduce < 0) {
            pProduce = -pProduce;
        }

        if (pItemProcess) {

            if (tempStack == null) {
                return CheckRecipeResultRegistry.NO_RECIPE;
            }
            setWorkingItem(tempStack);
            if (pItemStackStore.isItemEqual(pItemStackZeroPoint)) {
                return CheckRecipeResultRegistry.NO_RECIPE;
            }

            if (addEUToGlobalEnergyMap(pUUID, -pProduce)) {
                outputItemToAENetwork(pItemStackStore, pProduce);
                return CheckRecipeResultRegistry.SUCCESSFUL;
            }

        } else {

            FluidStack tempFluidStack = GT_Utility.convertCellToFluid(tempStack);

            if (tempFluidStack == null) {
                return CheckRecipeResultRegistry.NO_RECIPE;
            }
            setWorkingFluid(tempFluidStack);
            if (tempFluidStack.isFluidEqual(pFluidStackZeroPoint)) {
                return CheckRecipeResultRegistry.NO_RECIPE;
            }

            if (addEUToGlobalEnergyMap(pUUID, -pProduce)) {
                outputFluidToAENetwork(pFluidStackStore, pProduce);
                return CheckRecipeResultRegistry.SUCCESSFUL;
            }

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

    private void setWorkingItem(ItemStack itemStack) {
        pItemStackStore = itemStack;
        pName = itemStack.getDisplayName();
    }

    private void setWorkingFluid(FluidStack fluidStack) {
        pFluidStackStore = fluidStack;
        pName = fluidStack.getLocalizedName();
    }

    private void resetState() {
        mMaxProgresstime = 128;
        mEfficiencyIncrease = 10000;
        pBase = 0;
        pMultiplier = 0;
        pProduce = 0L;
    }

    private void outputItemToAENetwork(ItemStack item, long amount) {

        if ((item == null) || (amount <= 0)) {
            return;
        }

        if (amount < Integer.MAX_VALUE) {
            ItemStack tmpItem = item.copy();
            tmpItem.stackSize = (int) amount;
            ((GT_MetaTileEntity_Hatch_OutputBus_ME) mOutputBusses.get(0)).store(tmpItem);
        } else {
            // For item stacks > Int max.
            while (amount >= Integer.MAX_VALUE) {
                ItemStack tmpItem = item.copy();
                tmpItem.stackSize = Integer.MAX_VALUE;
                ((GT_MetaTileEntity_Hatch_OutputBus_ME) mOutputBusses.get(0)).store(tmpItem);
                amount -= Integer.MAX_VALUE;
            }
            ItemStack tmpItem = item.copy();
            tmpItem.stackSize = (int) amount;
            ((GT_MetaTileEntity_Hatch_OutputBus_ME) mOutputBusses.get(0)).store(tmpItem);
        }
    }

    private void outputFluidToAENetwork(FluidStack fluid, long amount) {

        if ((fluid == null) || (amount <= 0)) {
            return;
        }

        if (amount < Integer.MAX_VALUE) {
            FluidStack tmpFluid = fluid.copy();
            tmpFluid.amount = (int) amount;
            ((GT_MetaTileEntity_Hatch_Output_ME) mOutputHatches.get(0)).tryFillAE(tmpFluid);
        } else {
            // For fluidStacks > Int max.
            while (amount >= Integer.MAX_VALUE) {
                FluidStack tmpFluid = fluid.copy();
                tmpFluid.amount = Integer.MAX_VALUE;
                ((GT_MetaTileEntity_Hatch_Output_ME) mOutputHatches.get(0)).tryFillAE(tmpFluid);
                amount -= Integer.MAX_VALUE;
            }
            FluidStack tmpFluid = fluid.copy();
            tmpFluid.amount = (int) amount;
            ((GT_MetaTileEntity_Hatch_Output_ME) mOutputHatches.get(0)).tryFillAE(tmpFluid);
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
        return ((GT_Block_Casings8) GregTech_API.sBlockCasings8).getTextureIndex(7);
    }

    @Override
    protected int sCasingBlockMeta() {
        return 7;
    }

    @Override
    protected int sCoreBlockMeta() {
        return 0;
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
        tt.addMachineType("造物者")
            .addInfo("逆向奇点的控制器")
            .addInfo("复制指定的物品或流体.")
            .addInfo("直接从无线电网获取所需能量.")
            .addSeparator()
            .addInfo(VA_Values.CommonStrings.BluePrintTip)
            .beginStructureBlock(3, 3, 3, false)
            .addInputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .toolTipFinisher(VA_Values.CommonStrings.VisAppendMagical);
        return tt;
    }

    @Override
    public void addUIWidgets(ModularWindow.Builder builder, UIBuildContext buildContext) {
        super.addUIWidgets(builder, buildContext);
        builder.widget(
            new CycleButtonWidget().setToggle(() -> pItemProcess, val -> pItemProcess = val)
                .setTextureGetter(
                    state -> state == 1 ? GT_UITextures.OVERLAY_BUTTON_AUTOOUTPUT_ITEM
                        : GT_UITextures.OVERLAY_BUTTON_AUTOOUTPUT_FLUID)
                .setPlayClickSound(true)
                .setBackground(GT_UITextures.BUTTON_STANDARD)
                .setPos(80, 91)
                .setSize(16, 16)
                .dynamicTooltip(
                    () -> Collections
                        .singletonList(StatCollector.translateToLocal("append.pItemProcess." + (pItemProcess ? 1 : 0))))
                .setUpdateTooltipEveryTick(true)
                .setTooltipShowUpDelay(TOOLTIP_DELAY));
    }

    @Override
    public String[] getInfoData() {
        String[] oStr = super.getInfoData();
        String[] nStr = new String[oStr.length + 1];
        System.arraycopy(oStr, 0, nStr, 0, oStr.length);
        nStr[oStr.length] = EnumChatFormatting.AQUA + "复制目标: " + EnumChatFormatting.GOLD + pName;
        return nStr;
    }

    @Override
    public void getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor,
        IWailaConfigHandler config) {
        super.getWailaBody(itemStack, currentTip, accessor, config);
        final NBTTagCompound tag = accessor.getNBTData();
        if (!tag.getString("pName").isEmpty()) {
            currentTip.add(EnumChatFormatting.WHITE + "复制目标: " + EnumChatFormatting.AQUA + tag.getString("pName"));
            currentTip.add(
                EnumChatFormatting.WHITE + "生产总量: "
                    + EnumChatFormatting.GREEN
                    + GT_Utility.formatNumbers(tag.getLong("pProduce")));
        }
    }

    @Override
    public void getWailaNBTData(EntityPlayerMP player, TileEntity tile, NBTTagCompound tag, World world, int x, int y,
        int z) {
        super.getWailaNBTData(player, tile, tag, world, x, y, z);
        final IGregTechTileEntity tileEntity = getBaseMetaTileEntity();
        if (tileEntity != null) {
            if (tileEntity.isActive()) {
                tag.setString("pName", pName);
                tag.setLong("pProduce", pProduce);
            }
        }
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setInteger("pMultiplier", pMultiplier);
        aNBT.setInteger("pBase", pBase);
        aNBT.setLong("pProduce", pProduce);
        aNBT.setString("pName", pName);
        aNBT.setBoolean("pItemProcess", pItemProcess);
        aNBT.setTag("pItemStackStore", GT_Utility.saveItem(pItemStackStore));
        NBTTagCompound fNBT = new NBTTagCompound();
        pFluidStackStore.writeToNBT(fNBT);
        aNBT.setTag("pFluidStackStore", fNBT);
        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        pMultiplier = aNBT.getInteger("pMultiplier");
        pBase = aNBT.getInteger("pBase");
        pProduce = aNBT.getLong("pProduce");
        pName = aNBT.getString("pName");
        pItemProcess = aNBT.getBoolean("pItemProcess");
        pItemStackStore = GT_Utility.loadItem(aNBT, "pItemStackStore");
        pFluidStackStore = GT_Utility.loadFluid(aNBT, "pFluidStackStore");
        super.loadNBTData(aNBT);
    }
    // endregion
}
