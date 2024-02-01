package com.rhynia.gtnh.append.common.tile.multiMachine;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.enums.GT_HatchElement.Energy;
import static gregtech.api.enums.GT_HatchElement.ExoticEnergy;
import static gregtech.api.enums.GT_HatchElement.InputBus;
import static gregtech.api.enums.GT_HatchElement.InputHatch;
import static gregtech.api.enums.GT_HatchElement.OutputBus;
import static gregtech.api.util.GT_Utility.filterValidMTEs;
import static gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_FusionComputer.STRUCTURE_PIECE_MAIN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizon.structurelib.structure.IItemSource;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.rhynia.gtnh.append.api.enums.VA_Values;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase;

import gregtech.api.GregTech_API;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IHatchElement;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_DataAccess;
import gregtech.api.multitileentity.multiblock.casing.Glasses;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_AssemblyLineUtils;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gregtech.api.util.IGT_HatchAdder;

public class VA_TileEntity_ReinforcedAssemblyLine
    extends VA_MetaTileEntity_MultiBlockBase<VA_TileEntity_ReinforcedAssemblyLine> {

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

            @Override
            @NotNull
            public CheckRecipeResult process() {
                ArrayList<ItemStack> tDataStickCheckList = getDataItems(2);
                if (tDataStickCheckList.isEmpty()) {
                    return CheckRecipeResultRegistry.NO_DATA_STICKS;
                }
                setSpeedBonus(rSpeedBonus());
                setMaxParallel(rMaxParallel());
                return super.process();
            }

            /**
             * Generate a stream of matched recipes referencing data sticks,
             * and will be examined by real inputs, just like the original method does.
             * 
             * @param map fake map of assembly line, useless in the method
             * @return a stream of recipes that is available for processing
             */
            @Override
            @Nonnull
            protected Stream<GT_Recipe> findRecipeMatches(@Nullable RecipeMap<?> map) {
                ArrayList<ItemStack> tDataStickList = getDataItems(2);
                ArrayList<GT_Recipe> pDataStickRecipes = new ArrayList<>();

                if (!tDataStickList.isEmpty()) {
                    for (ItemStack tDataStick : tDataStickList) {
                        GT_AssemblyLineUtils.LookupResult tLookupResult = GT_AssemblyLineUtils
                            .findAssemblyLineRecipeFromDataStick(tDataStick, false);
                        if (tLookupResult.getType() == GT_AssemblyLineUtils.LookupResultType.INVALID_STICK) {
                            continue;
                        }
                        GT_Recipe.GT_Recipe_WithAlt pRecipe = transformRecipe(tLookupResult);
                        pDataStickRecipes.add(pRecipe);
                    }

                    if (lastRecipe != null && examineRecipe(lastRecipe, inputItems, inputFluids)) {
                        pDataStickRecipes.add(lastRecipe);
                    }

                    if (!pDataStickRecipes.isEmpty()) {
                        return pDataStickRecipes.stream()
                            .filter(recipe -> examineRecipe(recipe, inputItems, inputFluids));
                    }
                }

                return Stream.empty();
            }

            /**
             * Similar to the filterFindRecipe used in recipeMap-based search
             * 
             * @param recipe     the recipe that needs to be checked
             * @param inputItem  real inputs of items in machine
             * @param inputFluid real inputs of fluids in machine
             * @return if the real inputs capable to process the recipe
             */
            private boolean examineRecipe(@NotNull GT_Recipe recipe, ItemStack[] inputItem, FluidStack[] inputFluid) {
                if (recipe.mEnabled && !recipe.mFakeRecipe) {
                    return recipe.isRecipeInputEqual(false, false, inputFluid, inputItem);
                }
                return false;
            }

            /**
             * This method will generate a general GT_Recipe out of the assembly specific recipe
             * 
             * @param tLookupResult the data of the scanned data sticks
             * @return a GT_Recipe with oredict alt info
             */
            @NotNull
            private static GT_Recipe.GT_Recipe_WithAlt transformRecipe(
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
        return 2 * GT_Utility.getTier(this.getMaxInputVoltage());
    }

    @Override
    public float rSpeedBonus() {
        return (float) Math.max(0.3F, Math.pow(0.9, GT_Utility.getTier(this.getMaxInputVoltage())));
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return RecipeMaps.assemblylineVisualRecipes;
    }

    // endregion

    // region Structure
    private final int hOffset = 0, vOffset = 1, dOffset = 0;

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        removeMaintenance();
        mDataAccessHatches.clear();
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
                    .shouldReject(s -> s.mInputBusses.size() > 1 || s.mInputHatches.size() > 15)
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
            .addElement(
                'I',
                GT_HatchElementBuilder.<VA_TileEntity_ReinforcedAssemblyLine>builder()
                    .atLeast(DataHatchElement.DataAccess)
                    .shouldReject(s -> s.mDataAccessHatches.size() > 1)
                    .adder(VA_TileEntity_ReinforcedAssemblyLine::addDataAccessToMachineList)
                    .dot(4)
                    .casingIndex(42)
                    .buildAndChain(GregTech_API.sBlockCasings3, 10))
            .build();
    }

    // spotless:off
    @SuppressWarnings("SpellCheckingInspection")
    private final String[][] STRUCTURE = new String[][]{
        {"                ","BBBBBBBBBBBBBBBB","                "},
        {"~IEEEEEEEEEEEEEE","DDDDDDDDDDDDDDDD","EEEEEEEEEEEEEEEE"},
        {"AAAAAAAAAAAAAAAA","CCCCCCCCCCCCCCCC","AAAAAAAAAAAAAAAA"},
        {"HHHHHHHHHHHHHHHH","FFFFFFFFFFFFFFFG","HHHHHHHHHHHHHHHH"}
    };
    // spotless:on

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

    private enum DataHatchElement implements IHatchElement<VA_TileEntity_ReinforcedAssemblyLine> {

        DataAccess;

        @Override
        public List<? extends Class<? extends IMetaTileEntity>> mteClasses() {
            return Collections.singletonList(GT_MetaTileEntity_Hatch_DataAccess.class);
        }

        @Override
        public IGT_HatchAdder<VA_TileEntity_ReinforcedAssemblyLine> adder() {
            return VA_TileEntity_ReinforcedAssemblyLine::addDataAccessToMachineList;
        }

        @Override
        public long count(VA_TileEntity_ReinforcedAssemblyLine t) {
            return t.mDataAccessHatches.size();
        }
    }

    public boolean addDataAccessToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        if (aTileEntity == null) return false;
        IMetaTileEntity aMetaTileEntity = aTileEntity.getMetaTileEntity();
        if (aMetaTileEntity == null) return false;
        if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_DataAccess) {
            ((GT_MetaTileEntity_Hatch) aMetaTileEntity).updateTexture(aBaseCasingIndex);
            return mDataAccessHatches.add((GT_MetaTileEntity_Hatch_DataAccess) aMetaTileEntity);
        }
        return false;
    }

    private boolean isCorrectDataItem(ItemStack aStack, int state) {
        if ((state & 1) != 0 && ItemList.Circuit_Integrated.isStackEqual(aStack, true, true)) return true;
        if ((state & 2) != 0 && ItemList.Tool_DataStick.isStackEqual(aStack, false, true)) return true;
        return (state & 4) != 0 && ItemList.Tool_DataOrb.isStackEqual(aStack, false, true);
    }

    public ArrayList<ItemStack> getDataItems(int state) {
        ArrayList<ItemStack> rList = new ArrayList<>();
        if (GT_Utility.isStackValid(mInventory[1]) && isCorrectDataItem(mInventory[1], state)) {
            rList.add(mInventory[1]);
        }
        for (GT_MetaTileEntity_Hatch_DataAccess tHatch : filterValidMTEs(mDataAccessHatches)) {
            for (int i = 0; i < tHatch.getBaseMetaTileEntity()
                .getSizeInventory(); i++) {
                if (tHatch.getBaseMetaTileEntity()
                    .getStackInSlot(i) != null && isCorrectDataItem(
                        tHatch.getBaseMetaTileEntity()
                            .getStackInSlot(i),
                        state))
                    rList.add(
                        tHatch.getBaseMetaTileEntity()
                            .getStackInSlot(i));
            }
        }
        return rList;
    }

    public ArrayList<GT_MetaTileEntity_Hatch_DataAccess> mDataAccessHatches = new ArrayList<>();
    // endregion

    // region TT

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("装配线")
            .addInfo("复合装配线的控制器")
            .addInfo("更加高效, 支持输入总成.")
            .addInfo("再见，进阶装配线!")
            .addInfo("电压每提高1级, 最大并行增加2.")
            .addInfo("电压每提高1级, 额外降低10%配方耗时(叠乘), 最高70%加速.")
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

    // endregion
}
