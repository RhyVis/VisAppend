package com.rhynia.gtnh.append.common.tile.multiMachine;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockUnlocalizedName;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
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
import com.rhynia.gtnh.append.api.util.enums.CommonStrings;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase;

import gregtech.api.GregTech_API;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_AssemblyLineUtils;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gregtech.common.blocks.GT_Block_Casings2;

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
                if (tDataStick == null || !tDataStick.isItemEqual(ItemList.Tool_DataStick.get(1))) {
                    return Stream.empty();
                }

                GT_AssemblyLineUtils.LookupResult tLookupResult = GT_AssemblyLineUtils
                    .findAssemblyLineRecipeFromDataStick(tDataStick, false);
                if (tLookupResult.getType() == GT_AssemblyLineUtils.LookupResultType.INVALID_STICK) {
                    return Stream.empty();
                }
                GT_Recipe.GT_Recipe_WithAlt pRecipe = getGtRecipeWithAlt(tLookupResult);
                return Stream.of(pRecipe);
            }

            @NotNull
            private static GT_Recipe.GT_Recipe_WithAlt getGtRecipeWithAlt(
                GT_AssemblyLineUtils.LookupResult tLookupResult) {
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
        return 8 * GT_Utility.getTier(this.getMaxInputVoltage());
    }

    @Override
    public float rSpeedBonus() {
        return (float) Math.pow(0.95, GT_Utility.getTier(this.getMaxInputVoltage()));
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
    private final int hOffset = 1, vOffset = 2, dOffset = 0;

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
            .addElement(
                'A',
                ofChain(
                    ofBlockUnlocalizedName("IC2", "blockAlloyGlass", 0, true),
                    ofBlockUnlocalizedName("bartworks", "BW_GlasBlocks", 0, true),
                    // Warded Glass
                    ofBlockUnlocalizedName("Thaumcraft", "blockCosmeticOpaque", 2, false)))
            .addElement('B', ofBlock(GregTech_API.sBlockCasings2, 5))
            .addElement(
                'C',
                GT_HatchElementBuilder.<VA_TileEntity_ReinforcedAssemblyLine>builder()
                    .atLeast(InputBus, InputHatch, Energy.or(ExoticEnergy))
                    .adder(VA_TileEntity_ReinforcedAssemblyLine::addToMachineList)
                    .dot(1)
                    .casingIndex(((GT_Block_Casings2) GregTech_API.sBlockCasings2).getTextureIndex(9))
                    .buildAndChain(GregTech_API.sBlockCasings2, 9))
            .addElement('D', ofBlock(GregTech_API.sBlockCasings3, 10))
            .addElement(
                'F',
                GT_HatchElementBuilder.<VA_TileEntity_ReinforcedAssemblyLine>builder()
                    .atLeast(OutputBus)
                    .adder(VA_TileEntity_ReinforcedAssemblyLine::addToMachineList)
                    .dot(2)
                    .casingIndex(((GT_Block_Casings2) GregTech_API.sBlockCasings2).getTextureIndex(9))
                    .buildAndChain(GregTech_API.sBlockCasings2, 9))
            .addElement('T', ofBlock(GregTech_API.sBlockCasings2, 9))
            .build();
    }

    @Override
    public boolean addToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return super.addToMachineList(aTileEntity, aBaseCasingIndex)
            || addExoticEnergyInputToMachineList(aTileEntity, aBaseCasingIndex);
    }

    // spotless:off
    private final String[][] STRUCTURE = new String[][]{
        {" D "," D "," D "," D "," D "," D "," D "," D "," D "},
        {"CCC","CTC","CTC","CTC","CTC","CTC","CTC","CTC","FFF"},
        {"C~C","ABA","ABA","ABA","ABA","ABA","ABA","ABA","FFF"},
        {"CCC","TTT","TTT","TTT","TTT","TTT","TTT","TTT","FFF"}
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
        tt.addMachineType("装配线")
            .addInfo("集成装配线的控制器")
            .addInfo("仅能执行一个配方, 但更加高效.")
            .addInfo("再见，进阶装配线!")
            .addInfo("电压每提高1级, 最大并行增加8.")
            .addInfo("电压每提高1级, 额外降低5%配方耗时, 叠乘计算.")
            .addInfo(CommonStrings.ChangeModeByScrewdriver)
            .addSeparator()
            .addInfo(CommonStrings.StructureTooComplex)
            .addInfo(CommonStrings.BluePrintTip)
            .beginStructureBlock(3, 4, 9, false)
            .addInputHatch(CommonStrings.BluePrintInfo, 1)
            .addInputBus(CommonStrings.BluePrintInfo, 1)
            .addOutputBus(CommonStrings.BluePrintInfo, 2)
            .addEnergyHatch(CommonStrings.BluePrintInfo, 1)
            .toolTipFinisher(CommonStrings.VisAppendGigaFac);
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
