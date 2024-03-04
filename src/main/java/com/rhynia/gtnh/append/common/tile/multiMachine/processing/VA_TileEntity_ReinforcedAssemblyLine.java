package com.rhynia.gtnh.append.common.tile.multiMachine.processing;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.enums.GT_HatchElement.Energy;
import static gregtech.api.enums.GT_HatchElement.ExoticEnergy;
import static gregtech.api.enums.GT_HatchElement.InputBus;
import static gregtech.api.enums.GT_HatchElement.InputHatch;
import static gregtech.api.enums.GT_HatchElement.OutputBus;
import static gregtech.api.metatileentity.BaseTileEntity.TOOLTIP_DELAY;
import static gregtech.api.util.GT_Utility.filterValidMTEs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.gtnewhorizons.modularui.api.screen.ModularWindow;
import com.gtnewhorizons.modularui.api.screen.UIBuildContext;
import com.gtnewhorizons.modularui.common.widget.CycleButtonWidget;
import com.rhynia.gtnh.append.api.enums.VA_Values;
import com.rhynia.gtnh.append.api.process.processingLogic.VA_ProcessingLogic;
import com.rhynia.gtnh.append.api.util.AssemblyLineRecipeHelper;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase;

import gregtech.api.GregTech_API;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Textures;
import gregtech.api.gui.modularui.GT_UITextures;
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

    protected boolean pInjectCompatibilityMap = false;
    protected boolean pFocusMode = false;

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new VA_ProcessingLogic() {

            @Override
            @NotNull
            public CheckRecipeResult process() {
                if (getDataItems(2).isEmpty()) {
                    return CheckRecipeResultRegistry.NO_DATA_STICKS;
                }
                setSpeedBonus(rSpeedBonus());
                setMaxParallel(rMaxParallel());
                setEuModifier(rEUModifier());
                setOverclock(rPerfectOverclock() ? 2 : 1, 2);
                return super.process();
            }

            @Override
            @NotNull
            protected Stream<GT_Recipe> findRecipeMatches(@Nullable RecipeMap<?> map) {
                return AssemblyLineRecipeHelper.builder()
                    .setSticks(getDataItems(2))
                    .setInputItems(inputItems)
                    .setInputFluids(inputFluids)
                    .setLastRecipe(lastRecipe)
                    .enableCompatibilityRecipeMap(pInjectCompatibilityMap)
                    .generate();
            }

        };
    }

    @Override
    protected int rMaxParallel() {
        if (pFocusMode) return 1;
        double rParallelTimes = Math.floor(Math.log10(this.getMaxInputAmps()) / Math.log(4));
        return 4 * ((int) (1 + rParallelTimes));
    }

    @Override
    protected float rSpeedBonus() {
        if (pFocusMode) return 1 / 500F;
        long rSpeedTimes = this.getMaxInputEu() / (64L * VA_Values.RecipeValues.MAX);
        return (float) Math.max(0.3F, Math.pow(0.9F, rSpeedTimes));
    }

    @Override
    protected float rEUModifier() {
        return pFocusMode ? 1F / 2F : 1F;
    }

    @Override
    protected boolean rPerfectOverclock() {
        return pFocusMode;
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return RecipeMaps.assemblylineVisualRecipes;
    }

    @Override
    public boolean supportsInputSeparation() {
        return false;
    }

    @Override
    public final void onScrewdriverRightClick(ForgeDirection side, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        if (getBaseMetaTileEntity().isServerSide()) {
            this.pFocusMode = !this.pFocusMode;
        }
    }

    @Override
    public boolean onWireCutterRightClick(ForgeDirection side, ForgeDirection wrenchingSide, EntityPlayer aPlayer,
        float aX, float aY, float aZ, ItemStack aTool) {
        if (getBaseMetaTileEntity().isServerSide()) {
            this.pInjectCompatibilityMap = !this.pInjectCompatibilityMap;
        }
        return false;
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

    @Override
    public void addUIWidgets(ModularWindow.Builder builder, UIBuildContext buildContext) {
        super.addUIWidgets(builder, buildContext);
        builder.widget(
            new CycleButtonWidget().setToggle(() -> pFocusMode, val -> pFocusMode = val)
                .setTextureGetter(
                    state -> state == 1 ? GT_UITextures.OVERLAY_BUTTON_IMPORT : GT_UITextures.OVERLAY_BUTTON_CYCLIC)
                .setPlayClickSound(true)
                .setBackground(GT_UITextures.BUTTON_STANDARD)
                .setPos(80, 91)
                .setSize(16, 16)
                .dynamicTooltip(
                    () -> Collections
                        .singletonList(StatCollector.translateToLocal("append.RAL.pFocusMode." + (pFocusMode ? 1 : 0))))
                .setUpdateTooltipEveryTick(true)
                .setTooltipShowUpDelay(TOOLTIP_DELAY));
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
            .addInfo("在普通模式下:")
            .addInfo("初始并行为4, 每4^n输入电流将提供n倍并行")
            .addInfo("每64A-MAX的输入功率将提供10%加速(叠乘), 最高70%加速.")
            .addInfo("在专注模式下:")
            .addInfo("并行限制为1, 节省50%能量.")
            .addInfo("加速率接近极限, 执行无损超频.")
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
    public String[] getInfoData() {
        String[] oStr = super.getInfoData();
        String[] nStr = new String[oStr.length + 2];
        System.arraycopy(oStr, 0, nStr, 0, oStr.length);
        nStr[oStr.length] = EnumChatFormatting.AQUA + "专注模式: " + EnumChatFormatting.GOLD + this.pFocusMode;
        nStr[oStr.length + 1] = EnumChatFormatting.AQUA + "兼容配方: "
            + EnumChatFormatting.GOLD
            + this.pInjectCompatibilityMap;
        return nStr;
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setBoolean("pInjectCompatibilityMap", pInjectCompatibilityMap);
        aNBT.setBoolean("pFocusMode", pFocusMode);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        pInjectCompatibilityMap = aNBT.getBoolean("pInjectCompatibilityMap");
        pFocusMode = aNBT.getBoolean("pFocusMode");
    }

    // endregion
}
