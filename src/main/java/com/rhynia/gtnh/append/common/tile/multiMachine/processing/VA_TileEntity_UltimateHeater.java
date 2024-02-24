package com.rhynia.gtnh.append.common.tile.multiMachine.processing;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlocksTiered;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.onElementPass;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.withChannel;
import static gregtech.api.enums.GT_HatchElement.Energy;
import static gregtech.api.enums.GT_HatchElement.ExoticEnergy;
import static gregtech.api.enums.GT_HatchElement.InputBus;
import static gregtech.api.enums.GT_HatchElement.InputHatch;
import static gregtech.api.enums.GT_HatchElement.OutputBus;
import static gregtech.api.enums.GT_HatchElement.OutputHatch;
import static gregtech.api.util.GT_StructureUtility.ofCoil;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import com.github.technus.tectech.thing.CustomItemList;
import com.github.technus.tectech.thing.casing.TT_Container_Casings;
import com.google.common.collect.ImmutableList;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.rhynia.gtnh.append.api.enums.VA_Values;
import com.rhynia.gtnh.append.api.process.processingLogic.VA_ProcessingLogic;
import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.api.util.MathHelper;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase;

import gregtech.api.GregTech_API;
import gregtech.api.enums.HeatingCoilLevel;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;
import gregtech.common.blocks.GT_Block_Casings1;

public class VA_TileEntity_UltimateHeater extends VA_MetaTileEntity_MultiBlockBase<VA_TileEntity_UltimateHeater> {

    // region Definition
    public byte mRecipeMode = 0; // 0-sUltimateHeaterRecipes,1-sTranscendentReactorRecipes
    private HeatingCoilLevel mCoilLevel;
    // endregion

    // region Class Constructor
    public VA_TileEntity_UltimateHeater(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_UltimateHeater(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_UltimateHeater(this.mName);
    }
    // endregion

    // region Processing Logic
    private int uSpacetimeCompressionCount, uTimeAccelerationField, uStarArrayCount;

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new VA_ProcessingLogic() {

            @NotNull
            @Override
            public CheckRecipeResult process() {
                uStarArrayCount = 0;
                ItemStack u = getControllerSlot();
                if (u != null && u.isItemEqual(CustomItemList.astralArrayFabricator.get(1))) {
                    uStarArrayCount += u.stackSize;
                }
                setEuModifier(rEUModifier());
                setMaxParallel(rMaxParallel());
                setSpeedBonus(rSpeedBonus());
                setOverclock(rPerfectOverclock() ? 2 : 1, 2);
                return super.process();
            }
        };
    }

    @Override
    protected boolean rPerfectOverclock() {
        return mCoilLevel.getTier() > 11;
    }

    @Override
    public int rMaxParallel() {
        return (1 + uStarArrayCount) * (1 + uSpacetimeCompressionCount);
    }

    @Override
    public float rSpeedBonus() {
        return (float) Math
            .min(0.1F, (Math.pow(0.97D, mCoilLevel.getTier()) * Math.pow(0.93D, (uTimeAccelerationField + 1))));
    }

    @Override
    public float rEUModifier() {
        return (1.0F - MathHelper.clampFloat((0.0005F * uSpacetimeCompressionCount), 0F, 0.9F)) / (1 + uStarArrayCount);
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return switch (mRecipeMode) {
            case 0 -> AppendRecipeMaps.thermonuclearControlRecipes;
            case 1 -> AppendRecipeMaps.transcendentReactorRecipes;
            case 2 -> RecipeMaps.fusionRecipes;
            default -> RecipeMaps.nanoForgeRecipes;
        };
    }

    @Nonnull
    @Override
    public Collection<RecipeMap<?>> getAvailableRecipeMaps() {
        return Arrays.asList(
            AppendRecipeMaps.thermonuclearControlRecipes,
            AppendRecipeMaps.transcendentReactorRecipes,
            RecipeMaps.fusionRecipes);
    }

    @Override
    public int getRecipeCatalystPriority() {
        return -20;
    }

    @Override
    public final void onScrewdriverRightClick(ForgeDirection side, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        if (getBaseMetaTileEntity().isServerSide()) {
            this.mRecipeMode = (byte) ((this.mRecipeMode + 1) % 3);
            GT_Utility.sendChatToPlayer(
                aPlayer,
                StatCollector.translateToLocal("append.UltimateHeater.mRecipeMode." + this.mRecipeMode));
        }
    }

    // endregion

    // region Structure
    private final int hOffSet = 7, vOffSet = 0, dOffSet = 7;

    @Override
    public boolean isFlipChangeAllowed() {
        return false;
    }

    @Override
    public boolean isRotationChangeAllowed() {
        return false;
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        removeMaintenance();
        this.uSpacetimeCompressionCount = 0;
        this.uTimeAccelerationField = -1;
        return checkPiece(STRUCTURE_PIECE_MAIN, hOffSet, vOffSet, dOffSet);
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        this.buildPiece(STRUCTURE_PIECE_MAIN, stackSize, hintsOnly, hOffSet, vOffSet, dOffSet);
    }

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, ISurvivalBuildEnvironment env) {
        if (mMachine) return -1;
        return survivialBuildPiece(
            STRUCTURE_PIECE_MAIN,
            stackSize,
            hOffSet,
            vOffSet,
            dOffSet,
            elementBudget,
            env,
            false,
            true);
    }

    @Override
    public IStructureDefinition<VA_TileEntity_UltimateHeater> getStructureDefinition() {
        return StructureDefinition.<VA_TileEntity_UltimateHeater>builder()
            .addShape(STRUCTURE_PIECE_MAIN, transpose(STRUCTURE))
            .addElement('A', ofBlock(GregTech_API.sBlockCasings1, 12))
            .addElement('B', ofBlock(GregTech_API.sBlockCasings1, 15))
            .addElement(
                'C',
                ofChain(
                    onElementPass(t -> t.uTimeAccelerationField = -1, ofBlock(GregTech_API.sBlockCasings1, 14)),
                    ofBlocksTiered(
                        (block, meta) -> block == TT_Container_Casings.TimeAccelerationFieldGenerator ? meta : null,
                        ImmutableList.of(
                            Pair.of(TT_Container_Casings.TimeAccelerationFieldGenerator, 0),
                            Pair.of(TT_Container_Casings.TimeAccelerationFieldGenerator, 1),
                            Pair.of(TT_Container_Casings.TimeAccelerationFieldGenerator, 2),
                            Pair.of(TT_Container_Casings.TimeAccelerationFieldGenerator, 3),
                            Pair.of(TT_Container_Casings.TimeAccelerationFieldGenerator, 4),
                            Pair.of(TT_Container_Casings.TimeAccelerationFieldGenerator, 5),
                            Pair.of(TT_Container_Casings.TimeAccelerationFieldGenerator, 6),
                            Pair.of(TT_Container_Casings.TimeAccelerationFieldGenerator, 7),
                            Pair.of(TT_Container_Casings.TimeAccelerationFieldGenerator, 8)),
                        -1,
                        (t, meta) -> t.uTimeAccelerationField = meta,
                        t -> t.uTimeAccelerationField)))
            .addElement(
                'D',
                withChannel(
                    "coil",
                    ofCoil(VA_TileEntity_UltimateHeater::setCoilLevel, VA_TileEntity_UltimateHeater::getCoilLevel)))
            .addElement(
                'E',
                ofChain(
                    ofBlock(GregTech_API.sBlockCasings1, 14),
                    onElementPass(
                        k -> k.uSpacetimeCompressionCount += 1,
                        ofBlock(TT_Container_Casings.SpacetimeCompressionFieldGenerators, 0)),
                    onElementPass(
                        k -> k.uSpacetimeCompressionCount += 2,
                        ofBlock(TT_Container_Casings.SpacetimeCompressionFieldGenerators, 1)),
                    onElementPass(
                        k -> k.uSpacetimeCompressionCount += 4,
                        ofBlock(TT_Container_Casings.SpacetimeCompressionFieldGenerators, 2)),
                    onElementPass(
                        k -> k.uSpacetimeCompressionCount += 8,
                        ofBlock(TT_Container_Casings.SpacetimeCompressionFieldGenerators, 3)),
                    onElementPass(
                        k -> k.uSpacetimeCompressionCount += 16,
                        ofBlock(TT_Container_Casings.SpacetimeCompressionFieldGenerators, 4)),
                    onElementPass(
                        k -> k.uSpacetimeCompressionCount += 32,
                        ofBlock(TT_Container_Casings.SpacetimeCompressionFieldGenerators, 5)),
                    onElementPass(
                        k -> k.uSpacetimeCompressionCount += 64,
                        ofBlock(TT_Container_Casings.SpacetimeCompressionFieldGenerators, 6)),
                    onElementPass(
                        k -> k.uSpacetimeCompressionCount += 128,
                        ofBlock(TT_Container_Casings.SpacetimeCompressionFieldGenerators, 7)),
                    onElementPass(
                        k -> k.uSpacetimeCompressionCount += 256,
                        ofBlock(TT_Container_Casings.SpacetimeCompressionFieldGenerators, 8))))
            .addElement('F', ofBlock(GregTech_API.sBlockCasings1, 14))
            .addElement(
                'G',
                GT_HatchElementBuilder.<VA_TileEntity_UltimateHeater>builder()
                    .atLeast(InputBus, InputHatch)
                    .adder(VA_TileEntity_UltimateHeater::addInputToMachineList)
                    .dot(1)
                    .casingIndex(((GT_Block_Casings1) GregTech_API.sBlockCasings1).getTextureIndex(12))
                    .buildAndChain(GregTech_API.sBlockCasings1, 12))
            .addElement(
                'H',
                GT_HatchElementBuilder.<VA_TileEntity_UltimateHeater>builder()
                    .atLeast(OutputBus, OutputHatch)
                    .adder(VA_TileEntity_UltimateHeater::addOutputToMachineList)
                    .dot(2)
                    .casingIndex(((GT_Block_Casings1) GregTech_API.sBlockCasings1).getTextureIndex(12))
                    .buildAndChain(GregTech_API.sBlockCasings1, 12))
            .addElement(
                'I',
                GT_HatchElementBuilder.<VA_TileEntity_UltimateHeater>builder()
                    .atLeast(Energy.or(ExoticEnergy))
                    .adder(VA_TileEntity_UltimateHeater::addEnergyInputToMachineListExtended)
                    .dot(3)
                    .casingIndex(((GT_Block_Casings1) GregTech_API.sBlockCasings1).getTextureIndex(12))
                    .buildAndChain(GregTech_API.sBlockCasings1, 12))
            .build();
    }

    // spotless: off
    @SuppressWarnings("SpellCheckingInspection")
    private final String[][] STRUCTURE = new String[][] {
        { "               ", "      GGG      ", "    AA E AA    ", "   I   E   I   ", "  A    E    A  ",
            "  A    E    A  ", " G     E     G ", " GEEEEE~EEEEEG ", " G     E     G ", "  A    E    A  ",
            "  A    E    A  ", "   I   E   I   ", "    AA E AA    ", "      GGG      ", "               " },
        { "      HHH      ", "    AABBBAA    ", "   ABBADABBA   ", "  ABAA D AABA  ", " ABA   D   ABA ",
            " ABA  DDD  ABA ", "HBA  D D D  ABH", "HBDDDDDCDDDDDBH", "HBA  D D D  ABH", " ABA  DDD  ABA ",
            " ABA   D   ABA ", "  ABAA D AABA  ", "   ABBADABBA   ", "    AABBBAA    ", "      HHH      " },
        { "               ", "      GGG      ", "    AA E AA    ", "   I   E   I   ", "  A    E    A  ",
            "  A    E    A  ", " G     E     G ", " AEEEEEFEEEEEA ", " G     E     G ", "  A    E    A  ",
            "  A    E    A  ", "   I   E   I   ", "    AA E AA    ", "      GGG      ", "               " } };

    // spotless: on
    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        if (sideDirection == ForgeDirection.UP) {
            if (active) return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings1, 12)),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_FUSION1)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_FUSION1_GLOW)
                    .extFacing()
                    .glow()
                    .build() };
            return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings1, 12)),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_SCREEN)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_SCREEN)
                    .extFacing()
                    .glow()
                    .build() };
        }
        return new ITexture[] { Textures.BlockIcons
            .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings1, 12)) };
    }
    // endregion

    // region TT

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("热核控制场 | 超维度反应器 | 聚变反应堆")
            .addInfo("粒子宏的控制器")
            .addInfo("用纯粹的能量扭曲物质的存在.")
            .addInfo("每个时空压缩场提供2^(等级-1)的额外并行.")
            .addInfo("并提供(0.05*等级)%的功耗减免, 最高90%.")
            .addInfo("安装时间加速场后, 每级减少10%配方耗时(叠乘).")
            .addInfo("线圈每提高1级, 同样减少10%配方耗时(叠乘).")
            .addInfo("时间缩减的极限是原配方时间的10%.")
            .addInfo("线圈等级在海珀珍及以上时，解锁无损超频.")
            .addInfo("可以在控制器中放入" + EnumChatFormatting.BOLD + "星阵" + EnumChatFormatting.GRAY + "来倍增总并行，同时不增加耗能.")
            .addInfo(VA_Values.CommonStrings.ChangeModeByScrewdriver)
            .addSeparator()
            .addInfo(VA_Values.CommonStrings.StructureTooComplex)
            .addInfo(VA_Values.CommonStrings.BluePrintTip)
            .beginStructureBlock(15, 3, 15, false)
            .addInputHatch(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addOutputHatch(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addInputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addOutputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addEnergyHatch(VA_Values.CommonStrings.BluePrintInfo, 1)
            .toolTipFinisher(VA_Values.CommonStrings.VisAppendNuclear);
        return tt;
    }

    @Override
    public String[] getInfoData() {
        String[] o = super.getInfoData();
        String[] n = new String[o.length + 3];
        System.arraycopy(o, 0, n, 0, o.length);
        n[o.length] = EnumChatFormatting.AQUA + "时空压缩"
            + ": "
            + EnumChatFormatting.GOLD
            + GT_Utility.formatNumbers(uSpacetimeCompressionCount);
        n[o.length + 1] = EnumChatFormatting.AQUA + "时间加速"
            + ": "
            + EnumChatFormatting.GOLD
            + GT_Utility.formatNumbers(uTimeAccelerationField);
        n[o.length + 2] = EnumChatFormatting.AQUA + "星阵数量"
            + ": "
            + EnumChatFormatting.GOLD
            + GT_Utility.formatNumbers(uStarArrayCount);
        return n;
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setInteger("mRecipeMode", mRecipeMode);
        aNBT.setInteger("uSpacetimeCompressionField", uSpacetimeCompressionCount);
        aNBT.setInteger("uTimeAccelerationField", uTimeAccelerationField);
        aNBT.setInteger("uStarArrayCount", uStarArrayCount);
        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        mRecipeMode = (byte) aNBT.getInteger("mRecipeMode");
        uSpacetimeCompressionCount = aNBT.getInteger("uSpacetimeCompressionField");
        uTimeAccelerationField = aNBT.getInteger("uTimeAccelerationField");
        uStarArrayCount = aNBT.getInteger("uStarArrayCount");
        super.loadNBTData(aNBT);
    }

    // endregion

    // region Selector
    public void setCoilLevel(HeatingCoilLevel aCoilLevel) {
        this.mCoilLevel = aCoilLevel;
    }

    public HeatingCoilLevel getCoilLevel() {
        return this.mCoilLevel;
    }
    // endregion
}
