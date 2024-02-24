package com.rhynia.gtnh.append.common.tile.multiMachine.processing;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.enums.GT_HatchElement.Energy;
import static gregtech.api.enums.GT_HatchElement.ExoticEnergy;
import static gregtech.api.enums.GT_HatchElement.InputBus;
import static gregtech.api.enums.GT_HatchElement.InputHatch;
import static gregtech.api.enums.GT_HatchElement.OutputBus;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.rhynia.gtnh.append.api.enums.VA_Values;
import com.rhynia.gtnh.append.api.process.processingLogic.VA_ProcessingLogic;
import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.api.util.ItemHelper;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.multitileentity.multiblock.casing.Glasses;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;
import gregtech.common.blocks.GT_Block_Casings2;

public class VA_TileEntity_AssemblyMatrix extends VA_MetaTileEntity_MultiBlockBase<VA_TileEntity_AssemblyMatrix> {

    public byte mRecipeMode = 0; // 0-sAssemblyMatrixRecipes,1-sMicroAssemblyRecipes,2-SC recipes

    // region Class Constructor
    public VA_TileEntity_AssemblyMatrix(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_AssemblyMatrix(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_AssemblyMatrix(this.mName);
    }
    // endregion

    // region Processing Logic

    private int pModifier = 1;

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new VA_ProcessingLogic() {

            @NotNull
            @Override
            public CheckRecipeResult process() {
                pModifier = 1;
                boolean perfectOC = false;
                ItemStack stack = getControllerSlot();
                if (stack != null) {
                    if (ItemHelper.isAstralInfinityGem(stack)) {
                        pModifier = stack.stackSize;
                    } else if (ItemHelper.isAstralInfinityComplex(stack)) {
                        pModifier = stack.stackSize * 128;
                        perfectOC = true;
                    }
                }
                setMaxParallel(rMaxParallel());
                setSpeedBonus(rSpeedBonus());
                setOverclock(perfectOC ? 2 : 1, 2);
                return super.process();
            }
        };
    }

    @Override
    public int rMaxParallel() {
        return switch (mRecipeMode) {
            case 0 -> 4096 * pModifier;
            case 1 -> 1024 * pModifier;
            case 2 -> 2048 * pModifier;
            default -> 1;
        };
    }

    @Override
    public float rSpeedBonus() {
        return (float) Math.pow(0.95, GT_Utility.getTier(this.getMaxInputVoltage()));
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return switch (mRecipeMode) {
            case 0 -> AppendRecipeMaps.integratedAssemblyRecipes;
            case 1 -> AppendRecipeMaps.microAssemblyRecipes;
            case 2 -> AppendRecipeMaps.superconductingFormingRecipes;
            default -> RecipeMaps.nanoForgeRecipes;
        };
    }

    @Nonnull
    @Override
    public Collection<RecipeMap<?>> getAvailableRecipeMaps() {
        return Arrays.asList(
            AppendRecipeMaps.integratedAssemblyRecipes,
            AppendRecipeMaps.microAssemblyRecipes,
            AppendRecipeMaps.superconductingFormingRecipes);
    }

    @Override
    public final void onScrewdriverRightClick(ForgeDirection side, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        if (getBaseMetaTileEntity().isServerSide()) {
            this.mRecipeMode = (byte) ((this.mRecipeMode + 1) % 3);
            GT_Utility.sendChatToPlayer(
                aPlayer,
                StatCollector.translateToLocal("append.AssemblyMatrix.mRecipeMode." + this.mRecipeMode));
        }
    }

    // endregion

    // region Structure
    private final int hOffset = 1, vOffset = 1, dOffset = 0;

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        removeMaintenance();
        return checkPiece(STRUCTURE_PIECE_MAIN, hOffset, vOffset, dOffset);
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        this.buildPiece(STRUCTURE_PIECE_MAIN, stackSize, hintsOnly, hOffset, vOffset, dOffset);
    }

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, ISurvivalBuildEnvironment env) {
        if (mMachine) return -1;
        return survivialBuildPiece(
            STRUCTURE_PIECE_MAIN,
            stackSize,
            hOffset,
            vOffset,
            dOffset,
            elementBudget,
            env,
            false,
            true);
    }

    @Override
    public IStructureDefinition<VA_TileEntity_AssemblyMatrix> getStructureDefinition() {
        return StructureDefinition.<VA_TileEntity_AssemblyMatrix>builder()
            .addShape(STRUCTURE_PIECE_MAIN, transpose(STRUCTURE))
            .addElement('A', Glasses.chainAllGlasses())
            .addElement('B', ofBlock(GregTech_API.sBlockCasings2, 5))
            .addElement(
                'C',
                GT_HatchElementBuilder.<VA_TileEntity_AssemblyMatrix>builder()
                    .atLeast(InputBus, InputHatch, Energy.or(ExoticEnergy))
                    .adder(VA_TileEntity_AssemblyMatrix::addToMachineList)
                    .dot(1)
                    .casingIndex(((GT_Block_Casings2) GregTech_API.sBlockCasings2).getTextureIndex(9))
                    .buildAndChain(GregTech_API.sBlockCasings2, 9))
            .addElement('D', ofBlock(GregTech_API.sBlockCasings3, 10))
            .addElement(
                'F',
                GT_HatchElementBuilder.<VA_TileEntity_AssemblyMatrix>builder()
                    .atLeast(OutputBus)
                    .adder(VA_TileEntity_AssemblyMatrix::addToMachineList)
                    .dot(2)
                    .casingIndex(((GT_Block_Casings2) GregTech_API.sBlockCasings2).getTextureIndex(9))
                    .buildAndChain(GregTech_API.sBlockCasings2, 9))
            .addElement('T', ofBlock(GregTech_API.sBlockCasings2, 9))
            .build();
    }

    // spotless:off
    private final String[][] STRUCTURE = new String[][]{
        {"CCC","CDC","CDC","CDC","CDC","CDC","FFF"},
        {"C~C","ABA","ABA","ABA","ABA","ABA","FFF"},
        {"CCC","TTT","TTT","TTT","TTT","TTT","FFF"}
    };
    //spotless:on

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        if (sideDirection == facingDirection) {
            if (active) return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings2, 9)),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_FRONT_ASSEMBLY_LINE_ACTIVE)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_FRONT_ASSEMBLY_LINE_ACTIVE_GLOW)
                    .extFacing()
                    .glow()
                    .build() };
            return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings2, 9)),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_FRONT_ASSEMBLY_LINE)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_FRONT_ASSEMBLY_LINE_GLOW)
                    .extFacing()
                    .glow()
                    .build() };
        }
        return new ITexture[] { Textures.BlockIcons
            .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings2, 9)) };
    }
    // endregion

    // region TT

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("集成装配线 | 微加工装配线 | 超导成型机")
            .addInfo("组装矩阵的控制器")
            .addInfo("现代化的组装机构.")
            .addInfo("高效组装各类基础元件.")
            .addInfo("在控制器内放入星极和星矩来决定并行.")
            .addInfo("电压每提高1级, 加速5%(叠乘计算).")
            .addInfo(VA_Values.CommonStrings.ChangeModeByScrewdriver)
            .addSeparator()
            .addInfo(VA_Values.CommonStrings.StructureTooComplex)
            .addInfo(VA_Values.CommonStrings.BluePrintTip)
            .beginStructureBlock(3, 3, 7, false)
            .addInputHatch(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addInputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addOutputBus(VA_Values.CommonStrings.BluePrintInfo, 2)
            .addEnergyHatch(VA_Values.CommonStrings.BluePrintInfo, 1)
            .toolTipFinisher(VA_Values.CommonStrings.VisAppendGigaFac);
        return tt;
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setInteger("mRecipeMode", mRecipeMode);
        aNBT.setInteger("pModifier", pModifier);
        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        mRecipeMode = (byte) aNBT.getInteger("mRecipeMode");
        pModifier = aNBT.getInteger("pModifier");
        super.loadNBTData(aNBT);
    }
    // endregion
}
