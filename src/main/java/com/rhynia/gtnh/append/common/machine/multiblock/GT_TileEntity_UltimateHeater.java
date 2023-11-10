package com.rhynia.gtnh.append.common.machine.multiblock;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static gregtech.api.enums.GT_HatchElement.*;
import static gregtech.api.enums.Materials.*;
import static gregtech.api.enums.Textures.BlockIcons.*;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_ASSEMBLY_LINE_GLOW;
import static gregtech.api.util.GT_StructureUtility.ofCoil;
import static gregtech.api.util.GT_StructureUtility.ofFrame;
import static gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_FusionComputer.STRUCTURE_PIECE_MAIN;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizon.structurelib.alignment.constructable.IConstructable;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IItemSource;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.rhynia.gtnh.append.common.machine.recipe.GTAppendRecipe;

import gregtech.api.GregTech_API;
import gregtech.api.enums.HeatingCoilLevel;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_EnhancedMultiBlockBase;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gregtech.common.blocks.GT_Block_Casings8;

public class GT_TileEntity_UltimateHeater extends GT_MetaTileEntity_EnhancedMultiBlockBase<GT_TileEntity_UltimateHeater>
    implements IConstructable, ISurvivalConstructable {

    // region Definition
    private HeatingCoilLevel coilLevel;
    public byte glassTier;
    // endregion

    // region Class Constructor
    public GT_TileEntity_UltimateHeater(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public GT_TileEntity_UltimateHeater(String aName) {
        super(aName);
    }
    // endregion

    // region Processing Logic
    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new ProcessingLogic() {

            @NotNull
            @Override
            public CheckRecipeResult process() {
                setSpeedBonus(getSpeedBonus());
                setOverclock(coilLevel.getTier() > 12 ? 2 : 1, 2);
                return super.process();
            }
        }.setMaxParallelSupplier(this::getMaxParallelRecipes);
    }

    public int getMaxParallelRecipes() {
        return (int) (Math.pow(2, GT_Utility.getTier(this.getMaxInputVoltage())));
    }

    public float getSpeedBonus() {
        return (float) Math.pow(0.95, coilLevel.getTier());
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return GTAppendRecipe.instance.AstraForgeRecipes;
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        this.glassTier = 0;
        if (!checkPiece(STRUCTURE_PIECE_MAIN, horizontalOffSet, verticalOffSet, depthOffSet)) {
            return false;
        }
        if (glassTier < 12) {
            for (GT_MetaTileEntity_Hatch hatch : this.mExoticEnergyHatches) {
                if (this.glassTier < hatch.mTier) {
                    return false;
                }
            }
        }
        return true;
    }

    // endregion

    // region Structure
    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        this.buildPiece(STRUCTURE_PIECE_MAIN, stackSize, hintsOnly, horizontalOffSet, verticalOffSet, depthOffSet);
    }

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, IItemSource source, EntityPlayerMP actor) {
        if (this.mMachine) return -1;
        int realBudget = elementBudget >= 200 ? elementBudget : Math.min(200, elementBudget * 5);
        return this.survivialBuildPiece(
            STRUCTURE_PIECE_MAIN,
            stackSize,
            horizontalOffSet,
            verticalOffSet,
            depthOffSet,
            realBudget,
            source,
            actor,
            false,
            true);
    }

    private final int horizontalOffSet = 3;
    private final int verticalOffSet = 9;
    private final int depthOffSet = 0;

    @Override
    public IStructureDefinition<GT_TileEntity_UltimateHeater> getStructureDefinition() {
        return StructureDefinition.<GT_TileEntity_UltimateHeater>builder()
            .addShape(STRUCTURE_PIECE_MAIN, transpose(shape))
            .addElement(
                'A',
                withChannel(
                    "glass",
                    com.github.bartimaeusnek.bartworks.API.BorosilicateGlass.ofBoroGlass(
                        (byte) 0,
                        (byte) 1,
                        Byte.MAX_VALUE,
                        (te, t) -> te.glassTier = t,
                        te -> te.glassTier)))
            .addElement('B', ofBlock(GregTech_API.sBlockCasings1, 15))
            .addElement('C', ofBlock(GregTech_API.sBlockCasings4, 7))
            .addElement(
                'D',
                withChannel(
                    "coil",
                    ofCoil(GT_TileEntity_UltimateHeater::setCoilLevel, GT_TileEntity_UltimateHeater::getCoilLevel)))
            .addElement(
                'E',
                GT_HatchElementBuilder.<GT_TileEntity_UltimateHeater>builder()
                    .atLeast(InputBus, OutputBus, InputHatch, OutputHatch, Maintenance, Energy.or(ExoticEnergy))
                    .adder(GT_TileEntity_UltimateHeater::addToMachineList)
                    .dot(1)
                    .casingIndex(((GT_Block_Casings8) GregTech_API.sBlockCasings8).getTextureIndex(2))
                    .buildAndChain(GregTech_API.sBlockCasings8, 2))
            .addElement('F', ofBlock(GregTech_API.sBlockCasings8, 7))
            .addElement('G', ofBlock(GregTech_API.sBlockCasings8, 10))
            .addElement('H', ofFrame(Neutronium))
            .addElement('I', ofFrame(CosmicNeutronium))
            .addElement('J', ofFrame(Infinity))
            .build();
    }

    @Override
    public boolean addToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return super.addToMachineList(aTileEntity, aBaseCasingIndex)
            || addExoticEnergyInputToMachineList(aTileEntity, aBaseCasingIndex);
    }

    // spotless: off
    private final String[][] shape = new String[][] {
        { " EEEEE ", "EHGGGHE", "EGBBBGE", "EGBIBGE", "EGBBBGE", "EHGGGHE", " EEEEE " },
        { " FFFFF ", "FBBBBBF", "FB I BF", "FBIDIBF", "FB I BF", "FBBBBBF", " FFFFF " },
        { " FAAAF ", "FC   CF", "A  J  A", "A JDJ A", "A  J  A", "FC   CF", " FAAAF " },
        { " FAAAF ", "FC B CF", "A BJB A", "ABJDJBA", "A BJB A", "FC B CF", " FAAAF " },
        { " FAAAF ", "FC   CF", "A  J  A", "A JDJ A", "A  J  A", "FC   CF", " FAAAF " },
        { " FAAAF ", "FC B CF", "A BJB A", "ABJDJBA", "A BJB A", "FC B CF", " FAAAF " },
        { " FAAAF ", "FC   CF", "A  J  A", "A JDJ A", "A  J  A", "FC   CF", " FAAAF " },
        { " FAAAF ", "FC B CF", "A BJB A", "ABJDJBA", "A BJB A", "FC B CF", " FAAAF " },
        { " FAAAF ", "FC   CF", "A  J  A", "A JDJ A", "A  J  A", "FC   CF", " FAAAF " },
        { " EE~EE ", "EEGGGEE", "EGGGGGE", "EGGGGGE", "EGGGGGE", "EEGGGEE", " EEEEE " } };
    // spotless: on
    // endregion

    // region Overrides

    @Override
    public String[] getInfoData() {
        String[] origin = super.getInfoData();
        String[] ret = new String[origin.length + 2];
        System.arraycopy(origin, 0, ret, 0, origin.length);
        ret[origin.length - 1] = EnumChatFormatting.AQUA + "Parallel: "
            + EnumChatFormatting.GOLD
            + this.getMaxParallelRecipes();
        ret[origin.length] = EnumChatFormatting.AQUA + "Recipe Time multiplier: "
            + EnumChatFormatting.GOLD
            + this.getSpeedBonus();
        return ret;
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("离子发生器")
            .addInfo("至终加热场的控制器")
            .addInfo("用纯粹的能量暴力地将物质加热为离子态.")
            .addInfo("需要星极透镜聚焦热量.")
            .addInfo("电压每提高1级, 并行翻倍.")
            .addInfo("线圈每提高1级, 额外减少5%配方耗时.")
            .addInfo("线圈等级在海珀珍及以上时，解锁无损超频.")
            .addSeparator()
            .addInfo("结构太复杂了!")
            .addInfo("请参考§9Structure§1Lib§7全息投影，构建主体结构")
            .beginStructureBlock(3, 12, 3, false)
            .addInputHatch("用§9蓝§b图§r预览", 1)
            .addOutputHatch("用§9蓝§b图§r预览", 1)
            .addInputBus("用§9蓝§b图§r预览", 1)
            .addOutputBus("用§9蓝§b图§r预览", 1)
            .addMaintenanceHatch("用§9蓝§b图§r预览", 3)
            .addEnergyHatch("用§9蓝§b图§r预览", 2)
            .toolTipFinisher("GTNH: Append");
        return tt;
    }

    @Override
    public boolean isCorrectMachinePart(ItemStack aStack) {
        return true;
    }

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

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_TileEntity_UltimateHeater(this.mName);
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        if (sideDirection == facingDirection) {
            if (active) return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings8, 2)),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_ASSEMBLY_LINE_ACTIVE)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_ASSEMBLY_LINE_ACTIVE_GLOW)
                    .extFacing()
                    .glow()
                    .build() };
            return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings8, 2)),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_ASSEMBLY_LINE)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_ASSEMBLY_LINE_GLOW)
                    .extFacing()
                    .glow()
                    .build() };
        }
        return new ITexture[] { Textures.BlockIcons
            .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings8, 7)) };
    }

    // endregion

    // region Selector
    public void setCoilLevel(HeatingCoilLevel aCoilLevel) {
        this.coilLevel = aCoilLevel;
    }

    public HeatingCoilLevel getCoilLevel() {
        return this.coilLevel;
    }
    // endregion
}
