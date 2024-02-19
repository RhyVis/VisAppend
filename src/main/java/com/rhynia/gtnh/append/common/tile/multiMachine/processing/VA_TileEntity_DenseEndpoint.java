package com.rhynia.gtnh.append.common.tile.multiMachine.processing;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import com.github.bartimaeusnek.bartworks.API.recipe.BartWorksRecipeMaps;
import com.rhynia.gtnh.append.api.enums.VA_Values;
import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase_Cube;

import gregtech.api.GregTech_API;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;
import gregtech.common.blocks.GT_Block_Casings8;

public class VA_TileEntity_DenseEndpoint extends VA_MetaTileEntity_MultiBlockBase_Cube<VA_TileEntity_DenseEndpoint> {

    // region Builder
    public VA_TileEntity_DenseEndpoint(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_DenseEndpoint(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_DenseEndpoint(this.mName);
    }
    // endregion

    // region Process
    private byte pRecipeMode = 0;

    @Override
    public final void onScrewdriverRightClick(ForgeDirection side, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        if (getBaseMetaTileEntity().isServerSide()) {
            this.pRecipeMode = (byte) ((this.pRecipeMode + 1) % 3);
            GT_Utility.sendChatToPlayer(
                aPlayer,
                StatCollector.translateToLocal("append.DenseEndpoint.pRecipeMode." + this.pRecipeMode));
        }
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return switch (pRecipeMode) {
            case 0 -> BartWorksRecipeMaps.electricImplosionCompressorRecipes;
            case 1 -> RecipeMaps.nanoForgeRecipes;
            case 2 -> AppendRecipeMaps.quarkRefactoringRecipes;
            default -> RecipeMaps.assemblerRecipes;
        };
    }

    @Nonnull
    @Override
    public Collection<RecipeMap<?>> getAvailableRecipeMaps() {
        return Arrays.asList(
            BartWorksRecipeMaps.electricImplosionCompressorRecipes,
            RecipeMaps.nanoForgeRecipes,
            AppendRecipeMaps.quarkRefactoringRecipes);
    }

    @Override
    public int getRecipeCatalystPriority() {
        return -10;
    }

    @Override
    protected boolean rPerfectOverclock() {
        return true;
    }

    @Override
    protected int rMaxParallel() {
        long parallel = 128L * getMaxInputAmps();
        if (parallel <= Integer.MAX_VALUE) {
            return (int) parallel;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    @Override
    protected void setProcessingLogicPower(ProcessingLogic logic) {
        if (pRecipeMode == 1) {
            // Nano Forge use full EU import
            logic.setAvailableVoltage(getMaxInputEu());
            logic.setAvailableAmperage(1);
            logic.setAmperageOC(false);
        } else {
            super.setProcessingLogicPower(logic);
        }
    }

    // endregion

    // region Structure

    @Override
    protected Block sCasingBlock() {
        return GregTech_API.sBlockCasings8;
    }

    @Override
    protected int sCasingIndex() {
        return ((GT_Block_Casings8) GregTech_API.sBlockCasings8).getTextureIndex(10);
    }

    @Override
    protected int sCasingBlockMeta() {
        return 10;
    }

    // endregion

    // region Info
    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("电动聚爆压缩机 | 纳米锻炉 | 夸克重构机")
            .addInfo("致密极点的控制器")
            .addInfo("压缩到奇点, 再释放出来?")
            .addInfo("最大并行为128*最大输入电流.")
            .addInfo("执行无损超频.")
            .addSeparator()
            .addInfo(VA_Values.CommonStrings.BluePrintTip)
            .beginStructureBlock(3, 3, 3, false)
            .addInputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addInputHatch(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addOutputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addEnergyHatch(VA_Values.CommonStrings.BluePrintInfo, 1)
            .toolTipFinisher(VA_Values.CommonStrings.VisAppendNuclear);
        return tt;
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setByte("pRecipeMode", pRecipeMode);
        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        pRecipeMode = aNBT.getByte("pRecipeMode");
        super.loadNBTData(aNBT);
    }
    // endregion
}
