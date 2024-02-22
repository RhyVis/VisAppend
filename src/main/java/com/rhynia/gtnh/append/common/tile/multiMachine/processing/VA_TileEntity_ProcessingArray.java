package com.rhynia.gtnh.append.common.tile.multiMachine.processing;

import static com.rhynia.gtnh.append.api.util.ProcessingMap.PROCESSING_MAP;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.rhynia.gtnh.append.api.enums.VA_Values;
import com.rhynia.gtnh.append.api.process.processingLogic.VA_ProcessingLogic;
import com.rhynia.gtnh.append.api.util.MathHelper;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase_Cube;

import gregtech.api.GregTech_API;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;

public class VA_TileEntity_ProcessingArray
    extends VA_MetaTileEntity_MultiBlockBase_Cube<VA_TileEntity_ProcessingArray> {

    public VA_TileEntity_ProcessingArray(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_ProcessingArray(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_ProcessingArray(mName);
    }

    @Override
    protected Block sCasingBlock() {
        return GregTech_API.sBlockCasings8;
    }

    @Override
    protected int sCasingIndex() {
        return 183;
    }

    @Override
    protected int sCasingBlockMeta() {
        return 7;
    }

    private ItemStack pMachineStack;
    private int pOrd;
    private String pDisplayName;

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new VA_ProcessingLogic() {

            @NotNull
            @Override
            public CheckRecipeResult process() {
                resetState();
                pMachineStack = getControllerSlot();
                for (ItemStack itemStack : mInputBusses.get(0)
                    .getRealInventory()) {
                    if (GT_Utility.isAnyIntegratedCircuit(itemStack)) {
                        pOrd = MathHelper.clampInt(itemStack.getItemDamage(), 1, 24);
                        break;
                    }
                }
                setRecipeMap(getProcessRecipeMapFromStack());
                setEuModifier(rEUModifier());
                setMaxParallel(rMaxParallel());
                setSpeedBonus(rSpeedBonus());
                setOverclock(rPerfectOverclock() ? 2 : 1, 2);
                return super.process();
            }
        };
    }

    @Override
    protected int rMaxParallel() {
        return 32768;
    }

    @Override
    protected boolean rPerfectOverclock() {
        return true;
    }

    @Nullable
    private RecipeMap<?> getProcessRecipeMapFromStack() {
        ItemStack stack = (pMachineStack != null) ? pMachineStack : getControllerSlot();
        int ordinal = pOrd;
        if (stack == null) return null;
        if (PROCESSING_MAP.containsKey(stack.getUnlocalizedName())) {
            ArrayList<RecipeMap<?>> tempArray = PROCESSING_MAP.get(stack.getUnlocalizedName());
            RecipeMap<?> tempRecipeMap;
            int tempSize = tempArray.size();
            if (tempSize == 1) {
                tempRecipeMap = tempArray.get(0);
            } else {
                int ord = MathHelper.clampInt(ordinal, 0, tempSize - 1);
                tempRecipeMap = tempArray.get(ord);
            }
            if (tempRecipeMap != null) pDisplayName = StatCollector.translateToLocal(tempRecipeMap.unlocalizedName);
            return tempRecipeMap;
        }
        return null;
    }

    private void resetState() {
        this.pOrd = 0;
        this.pDisplayName = "";
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("处理阵列")
            .addInfo("处理阵列的控制器")
            .addInfo("闹麻了, 似人复活了.")
            .addSeparator()
            .addInfo(VA_Values.CommonStrings.BluePrintTip)
            .beginStructureBlock(3, 3, 3, false)
            .addInputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addInputHatch(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addOutputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addOutputHatch(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addEnergyHatch(VA_Values.CommonStrings.BluePrintInfo, 1)
            .toolTipFinisher(VA_Values.CommonStrings.VisAppendGigaFac);
        return tt;
    }

    @Override
    public void getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor,
        IWailaConfigHandler config) {
        super.getWailaBody(itemStack, currentTip, accessor, config);
        final NBTTagCompound tag = accessor.getNBTData();
        if (!tag.getString("pDisplayNameW")
            .isEmpty()) {
            currentTip
                .add(EnumChatFormatting.WHITE + "执行配方: " + EnumChatFormatting.AQUA + tag.getString("pDisplayNameW"));
        }
    }

    @Override
    public void getWailaNBTData(EntityPlayerMP player, TileEntity tile, NBTTagCompound tag, World world, int x, int y,
        int z) {
        super.getWailaNBTData(player, tile, tag, world, x, y, z);
        final IGregTechTileEntity tileEntity = getBaseMetaTileEntity();
        if (tileEntity != null) {
            if (tileEntity.isActive()) {
                tag.setString("pDisplayNameW", pDisplayName);
            }
        }
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setInteger("pOrd", pOrd);
        aNBT.setString("pDisplayName", pDisplayName);
        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        pOrd = aNBT.getInteger("pOrd");
        pDisplayName = aNBT.getString("pDisplayName");
        super.loadNBTData(aNBT);
    }

}
