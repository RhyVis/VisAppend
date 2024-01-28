package com.rhynia.gtnh.append.common.tile.multiMachine;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.enums.GT_HatchElement.Energy;
import static gregtech.api.enums.GT_HatchElement.ExoticEnergy;
import static gregtech.api.enums.GT_HatchElement.InputBus;
import static gregtech.api.enums.GT_HatchElement.InputHatch;
import static gregtech.api.enums.GT_HatchElement.OutputBus;
import static gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_FusionComputer.STRUCTURE_PIECE_MAIN;

import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizon.structurelib.structure.IItemSource;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.rhynia.gtnh.append.api.util.enums.VA_Values;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase;

import gregtech.api.GregTech_API;
import gregtech.api.enums.ItemList;
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
import gregtech.api.util.GT_AssemblyLineUtils;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;

public class VA_TileEntity_ReinforcedAssemblyLine
    extends VA_MetaTileEntity_MultiBlockBase<VA_TileEntity_ReinforcedAssemblyLine> {

    public byte mRecipeMode = 0;

    // region Class Constructor
    public VA_TileEntity_ReinforcedAssemblyLine(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_ReinforcedAssemblyLine(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_ReinforcedAssemblyLine(this.mName);
    }
    // endregion

    // region Processing Logic

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new ProcessingLogic() {

            @NotNull
            @Override
            public CheckRecipeResult process() {
                setEuModifier(rEUModifier());
                setMaxParallel(rMaxParallel());
                return super.process();
            }

            @Nonnull
            @Override
            protected Stream<GT_Recipe> findRecipeMatches(@Nullable RecipeMap<?> map) {
                ItemStack tDataStick = getControllerSlot();
                // Check if DataStick exists
                if (tDataStick == null || !tDataStick.isItemEqual(ItemList.Tool_DataStick.get(1))) {
                    return Stream.empty();
                }
                // Check if Recipe valid
                GT_AssemblyLineUtils.LookupResult tLookupResult = GT_AssemblyLineUtils
                    .findAssemblyLineRecipeFromDataStick(tDataStick, false);
                if (tLookupResult.getType() == GT_AssemblyLineUtils.LookupResultType.INVALID_STICK) {
                    return Stream.empty();
                }
                // Give out recipe
                // TODO: Problem exists that when not given enough inputs, an error will be thrown
                GT_Recipe.GT_Recipe_WithAlt pRecipe = getGTRecipe(tLookupResult);
                return Stream.of(pRecipe);
            }

            @NotNull
            private static GT_Recipe.GT_Recipe_WithAlt getGTRecipe(GT_AssemblyLineUtils.LookupResult tLookupResult) {
                GT_Recipe.GT_Recipe_AssemblyLine tRecipe = tLookupResult.getRecipe();
                return new GT_Recipe.GT_Recipe_WithAlt(
                    false,
                    tRecipe.mInputs,
                    new ItemStack[] { tRecipe.mOutput },
                    null,
                    null,
                    tRecipe.mFluidInputs,
                    null,
                    tRecipe.mDuration,
                    tRecipe.mEUt,
                    0,
                    tRecipe.mOreDictAlt);
            }
        };
    }

    @Override
    public int rMaxParallel() {
        return 2 * GT_Utility.getTier(this.getMaxInputVoltage());
    }

    @Override
    public float rSpeedBonus() {
        return (float) Math.max(0.1F, Math.pow(0.95, GT_Utility.getTier(this.getMaxInputVoltage())));
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return RecipeMaps.assemblylineVisualRecipes;
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
    private final int hOffset = 0, vOffset = 1, dOffset = 0;

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        removeMaintenance();
        return checkPiece(STRUCTURE_PIECE_MAIN, hOffset, vOffset, dOffset);
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        this.buildPiece(STRUCTURE_PIECE_MAIN, stackSize, hintsOnly, hOffset, vOffset, dOffset);
    }

    @SuppressWarnings("deprecation")
    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, IItemSource source, EntityPlayerMP actor) {
        if (this.mMachine) return -1;
        int realBudget = elementBudget >= 200 ? elementBudget : Math.min(200, elementBudget * 5);
        return this.survivialBuildPiece(
            STRUCTURE_PIECE_MAIN,
            stackSize,
            hOffset,
            vOffset,
            dOffset,
            realBudget,
            source,
            actor,
            false,
            true);
    }

    @Override
    public IStructureDefinition<VA_TileEntity_ReinforcedAssemblyLine> getStructureDefinition() {
        return StructureDefinition.<VA_TileEntity_ReinforcedAssemblyLine>builder()
            .addShape(STRUCTURE_PIECE_MAIN, transpose(STRUCTURE))
            .addElement('A', Glasses.chainAllGlasses())
            .addElement(
                'B',
                GT_HatchElementBuilder.<VA_TileEntity_ReinforcedAssemblyLine>builder()
                    .atLeast(Energy.or(ExoticEnergy))
                    .adder(VA_TileEntity_ReinforcedAssemblyLine::addEnergyInputToMachineListExtended)
                    .dot(1)
                    .casingIndex(16)
                    .buildAndChain(GregTech_API.sBlockCasings2, 0))
            .addElement('C', ofBlock(GregTech_API.sBlockCasings2, 5))
            .addElement('D', ofBlock(GregTech_API.sBlockCasings2, 9))
            .addElement('E', ofBlock(GregTech_API.sBlockCasings3, 10))
            .addElement(
                'F',
                GT_HatchElementBuilder.<VA_TileEntity_ReinforcedAssemblyLine>builder()
                    .atLeast(InputBus, InputHatch)
                    .adder(VA_TileEntity_ReinforcedAssemblyLine::addInputToMachineList)
                    .dot(2)
                    .casingIndex(16)
                    .buildAndChain(GregTech_API.sBlockCasings2, 0))
            .addElement(
                'G',
                GT_HatchElementBuilder.<VA_TileEntity_ReinforcedAssemblyLine>builder()
                    .atLeast(OutputBus)
                    .adder(VA_TileEntity_ReinforcedAssemblyLine::addOutputToMachineList)
                    .dot(3)
                    .casingIndex(16)
                    .buildAndChain(GregTech_API.sBlockCasings2, 0))
            .addElement('H', ofBlock(GregTech_API.sBlockCasings2, 0))
            .build();
    }

    // spotless:off
    @SuppressWarnings("SpellCheckingInspection")
    private final String[][] STRUCTURE = new String[][]{
        {"                ","BBBBBBBBBBBBBBBB","                "},
        {"~EEEEEEEEEEEEEEE","DDDDDDDDDDDDDDDD","EEEEEEEEEEEEEEEE"},
        {"AAAAAAAAAAAAAAAA","CCCCCCCCCCCCCCCC","AAAAAAAAAAAAAAAA"},
        {"HHHHHHHHHHHHHHHH","FFFFFFFFFFFFFFFG","HHHHHHHHHHHHHHHH"}
    };
    //spotless:on

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        if (sideDirection == facingDirection) {
            if (active) return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings2, 0)),
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
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings2, 0)),
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
            .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings2, 0)) };
    }
    // endregion

    // region TT

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("装配线")
            .addInfo("复式装配线的控制器")
            .addInfo("仅能执行一个配方, 但更加高效, 支持输入总成.")
            .addInfo("再见，进阶装配线!")
            .addInfo("电压每提高1级, 最大并行增加2.")
            .addInfo("电压每提高1级, 额外降低5%配方耗时(叠乘), 最高90%加速.")
            .addInfo(VA_Values.CommonStrings.ChangeModeByScrewdriver)
            .addSeparator()
            .addInfo(VA_Values.CommonStrings.StructureTooComplex)
            .addInfo(VA_Values.CommonStrings.BluePrintTip)
            .beginStructureBlock(16, 4, 3, false)
            .addInputHatch(VA_Values.CommonStrings.BluePrintInfo, 2)
            .addInputBus(VA_Values.CommonStrings.BluePrintInfo, 2)
            .addOutputBus(VA_Values.CommonStrings.BluePrintInfo, 3)
            .addEnergyHatch(VA_Values.CommonStrings.BluePrintInfo, 1)
            .toolTipFinisher(VA_Values.CommonStrings.VisAppendGigaFac);
        return tt;
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setInteger("mRecipeMode", mRecipeMode);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        mRecipeMode = (byte) aNBT.getInteger("mRecipeMode");
    }
    // endregion
}
