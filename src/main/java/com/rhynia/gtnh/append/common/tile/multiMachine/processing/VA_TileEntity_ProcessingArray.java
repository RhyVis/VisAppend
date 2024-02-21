package com.rhynia.gtnh.append.common.tile.multiMachine.processing;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import com.rhynia.gtnh.append.api.util.MathHelper;
import com.rhynia.gtnh.append.api.util.ProcessingMap;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase_Cube;

import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VA_TileEntity_ProcessingArray
    extends VA_MetaTileEntity_MultiBlockBase_Cube<VA_TileEntity_ProcessingArray> {

    protected VA_TileEntity_ProcessingArray(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    protected VA_TileEntity_ProcessingArray(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_ProcessingArray(mName);
    }

    @Override
    protected Block sCasingBlock() {
        return null;
    }

    @Override
    protected int sCasingIndex() {
        return 0;
    }

    @Override
    protected int sCasingBlockMeta() {
        return 0;
    }

    private ItemStack pMachineStack;
    private int pOrd;
    private String pDisplayName;

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new ProcessingLogic() {

            @NotNull
            @Override
            public CheckRecipeResult process() {
                resetState();
                pMachineStack = getControllerSlot();
                for (ItemStack itemStack : mInputBusses.get(0)
                    .getRealInventory()) {
                    if (GT_Utility.isAnyIntegratedCircuit(itemStack)) {
                        pOrd = MathHelper.clampInt(itemStack.getItemDamage(), 0, 24);
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

    @Nullable
    private RecipeMap<?> getProcessRecipeMapFromStack() {
        ItemStack stack = pMachineStack;
        int ordinal = pOrd;
        if (stack == null) return null;
        for (ProcessingMap process : ProcessingMap.values()) {
            if (stack.isItemEqual(process.getProcessingMachine())) {
                if (process.getSeparateOrdinal() == 0) {
                    pDisplayName = process.getMapNameDisplay();
                    return process.getProcessingRecipeMap();
                } else {
                    if (ordinal == process.getSeparateOrdinal()) {
                        pDisplayName = process.getMapNameDisplay();
                        return process.getProcessingRecipeMap();
                    }
                }
            }
        }
        return null;
    }

    private void resetState() {
        this.pMachineStack = null;
        this.pDisplayName = "";
        this.pOrd = 0;
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        return null;
    }

    @Override
    public void getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor,
                             IWailaConfigHandler config) {
        super.getWailaBody(itemStack, currentTip, accessor, config);
        final NBTTagCompound tag = accessor.getNBTData();
        if (!tag.getString("pDisplayNameW")
            .isEmpty()) {
            currentTip.add(EnumChatFormatting.WHITE + "执行配方: " + EnumChatFormatting.AQUA + tag.getString("pDisplayNameW"));
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
        aNBT.setTag("pMachineStack", pMachineStack.writeToNBT(new NBTTagCompound()));
        aNBT.setInteger("pOrd", pOrd);
        aNBT.setString("pDisplayName", pDisplayName);
        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        pMachineStack = ItemStack.loadItemStackFromNBT(aNBT.getCompoundTag("pMachineStack"));
        pOrd = aNBT.getInteger("pOrd");
        pDisplayName = aNBT.getString("pDisplayName");
        super.loadNBTData(aNBT);
    }

}
