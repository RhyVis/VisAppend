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
import com.rhynia.gtnh.append.api.util.ItemHelper;
import com.rhynia.gtnh.append.api.util.MathHelper;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase_Cube;

import gregtech.api.GregTech_API;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_InputBus;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.SimpleCheckRecipeResult;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;

public class VA_TileEntity_ProcessingMatrix
    extends VA_MetaTileEntity_MultiBlockBase_Cube<VA_TileEntity_ProcessingMatrix> {

    public VA_TileEntity_ProcessingMatrix(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_ProcessingMatrix(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_ProcessingMatrix(mName);
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
    private int pIndexParallel;
    private boolean pCalibrationSet;
    private String pDisplayName;

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new VA_ProcessingLogic() {

            @NotNull
            @Override
            public CheckRecipeResult process() {
                resetState();
                pMachineStack = getControllerSlot();
                if (pMachineStack == null) {
                    return SimpleCheckRecipeResult.ofFailure("no_recipe_map_set");
                }
                pIndexParallel += pMachineStack.stackSize * 16;
                for (GT_MetaTileEntity_Hatch_InputBus inputBus : mInputBusses) {
                    for (ItemStack itemStack : inputBus.getRealInventory()) {
                        if (itemStack != null) {
                            if (!pCalibrationSet && ItemHelper.isCalibration(itemStack)) {
                                pOrd = MathHelper.clampInt(itemStack.stackSize, 1, 24);
                                pCalibrationSet = true;
                            }
                            if (ItemHelper.isAstralInfinityComplex(itemStack)) {
                                pIndexParallel += itemStack.stackSize * 2048;
                            }
                        }
                    }
                }
                RecipeMap<?> map = getProcessRecipeMapFromStack();
                if (map == null) {
                    return SimpleCheckRecipeResult.ofFailure("no_recipe_map_set");
                }
                setRecipeMap(map);
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
        return pIndexParallel;
    }

    @Override
    protected boolean rPerfectOverclock() {
        return true;
    }

    @Nullable
    private RecipeMap<?> getProcessRecipeMapFromStack() {
        ItemStack stack = pMachineStack;
        int ordinal = pOrd;
        if (stack == null) return null;
        if (PROCESSING_MAP.containsKey(stack.getUnlocalizedName())) {
            ArrayList<RecipeMap<?>> tempArray = PROCESSING_MAP.get(stack.getUnlocalizedName());
            RecipeMap<?> tempRecipeMap;
            int tempSize = tempArray.size();
            if (tempSize == 1) {
                tempRecipeMap = tempArray.get(0);
            } else {
                int ord = MathHelper.clampInt(ordinal, 1, tempSize) - 1;
                tempRecipeMap = tempArray.get(ord);
            }
            if (tempRecipeMap != null) pDisplayName = StatCollector.translateToLocal(tempRecipeMap.unlocalizedName);
            return tempRecipeMap;
        }
        return null;
    }

    private void resetState() {
        this.pIndexParallel = 0;
        this.pOrd = 0;
        this.pDisplayName = "";
        this.pCalibrationSet = false;
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("处理矩阵")
            .addInfo("处理矩阵的控制器")
            .addInfo("将多方块机器压缩到这个矩阵中来.")
            .addInfo("在控制器放入机器组或机器.")
            .addInfo("在一个机器组对应多个配方时, 用标定指示的数量决定配方.")
            .addInfo("控制器中每个机器组对应16并行, 放入星矩等效为2048并行.")
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
    public String[] getInfoData() {
        String[] oStr = super.getInfoData();
        String[] nStr = new String[oStr.length + 1];
        System.arraycopy(oStr, 0, nStr, 0, oStr.length);
        nStr[oStr.length] = EnumChatFormatting.AQUA + "执行配方: "
            + EnumChatFormatting.GOLD
            + (this.pDisplayName.isEmpty() ? "无" : pDisplayName);
        return nStr;
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
            tag.setString("pDisplayNameW", pDisplayName);
        }
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setInteger("pIndexParallel", pIndexParallel);
        aNBT.setInteger("pOrd", pOrd);
        aNBT.setString("pDisplayName", pDisplayName);
        aNBT.setBoolean("pCalibrationSet", pCalibrationSet);
        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        pIndexParallel = aNBT.getInteger("pIndexParallel");
        pOrd = aNBT.getInteger("pOrd");
        pDisplayName = aNBT.getString("pDisplayName");
        pCalibrationSet = aNBT.getBoolean("pCalibrationSet");
        super.loadNBTData(aNBT);
    }

}
