package com.rhynia.gtnh.append.common.tile.multiMachine;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.isAir;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlocksTiered;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.onElementPass;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.enums.GT_HatchElement.Energy;
import static gregtech.api.enums.GT_HatchElement.ExoticEnergy;
import static gregtech.api.enums.GT_HatchElement.InputBus;
import static gregtech.api.enums.GT_HatchElement.InputHatch;
import static gregtech.api.enums.GT_HatchElement.OutputBus;
import static gregtech.api.enums.GT_HatchElement.OutputHatch;
import static gregtech.api.util.GT_StructureUtility.ofFrame;
import static gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_FusionComputer.STRUCTURE_PIECE_MAIN;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;

import org.apache.commons.lang3.tuple.Pair;

import com.github.technus.tectech.thing.casing.TT_Container_Casings;
import com.google.common.collect.ImmutableList;
import com.gtnewhorizon.structurelib.structure.IItemSource;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.api.util.Values;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase;

import fox.spiteful.avaritia.blocks.LudicrousBlocks;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;

public class VA_TileEntity_AstraForge extends VA_MetaTileEntity_MultiBlockBase<VA_TileEntity_AstraForge> {

    // region Class Constructor
    public VA_TileEntity_AstraForge(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_AstraForge(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_AstraForge(this.mName);
    }
    // endregion

    // region Processing Logic
    private int uStableField;

    @Override
    public int rMaxParallel() {
        int r = uStableField + 7;
        return (int) Math.max(1, Math.pow(2, r));
    }

    @Override
    public float rSpeedBonus() {
        double t = 1.0 - 0.02 * (uStableField + 1);
        return Math.min(1.0F, (float) Math.pow(t, GT_Utility.getTier(this.getMaxInputVoltage())));
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return AppendRecipeMaps.astralForgeRecipes;
    }

    // endregion

    // region Structure

    private final int hOffSet = 2, vOffSet = 1, dOffSet = 0;

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        removeMaintenance();
        uStableField = -1;
        return checkPiece(STRUCTURE_PIECE_MAIN, hOffSet, vOffSet, dOffSet);
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        this.buildPiece(STRUCTURE_PIECE_MAIN, stackSize, hintsOnly, hOffSet, vOffSet, dOffSet);
    }

    @SuppressWarnings("deprecation")
    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, IItemSource source, EntityPlayerMP actor) {
        if (this.mMachine) return -1;
        int realBudget = elementBudget >= 200 ? elementBudget : Math.min(200, elementBudget * 5);
        return this.survivialBuildPiece(
            STRUCTURE_PIECE_MAIN,
            stackSize,
            hOffSet,
            vOffSet,
            dOffSet,
            realBudget,
            source,
            actor,
            false,
            true);
    }

    @Override
    public IStructureDefinition<VA_TileEntity_AstraForge> getStructureDefinition() {
        return StructureDefinition.<VA_TileEntity_AstraForge>builder()
            .addShape(STRUCTURE_PIECE_MAIN, transpose(shape))
            .addElement('A', ofBlock(GregTech_API.sBlockCasings2, 9))
            .addElement(
                'B',
                GT_HatchElementBuilder.<VA_TileEntity_AstraForge>builder()
                    .atLeast(InputBus, OutputBus, InputHatch, OutputHatch)
                    .adder(VA_TileEntity_AstraForge::addToMachineList)
                    .dot(1)
                    .casingIndex(183)
                    .buildAndChain(GregTech_API.sBlockCasings8, 7))
            .addElement('C', ofBlock(LudicrousBlocks.resource_block, 1))
            .addElement('D', ofFrame(Materials.Neutronium))
            .addElement(
                'E',
                GT_HatchElementBuilder.<VA_TileEntity_AstraForge>builder()
                    .atLeast(Energy.or(ExoticEnergy))
                    .adder(VA_TileEntity_AstraForge::addToMachineList)
                    .dot(2)
                    .casingIndex(183)
                    .buildAndChain(GregTech_API.sBlockCasings8, 7))
            .addElement(
                'F',
                ofChain(
                    onElementPass(t -> t.uStableField = -1, isAir()),
                    ofBlocksTiered(
                        (block, meta) -> block == TT_Container_Casings.StabilisationFieldGenerators ? meta : null,
                        ImmutableList.of(
                            Pair.of(TT_Container_Casings.StabilisationFieldGenerators, 0),
                            Pair.of(TT_Container_Casings.StabilisationFieldGenerators, 1),
                            Pair.of(TT_Container_Casings.StabilisationFieldGenerators, 2),
                            Pair.of(TT_Container_Casings.StabilisationFieldGenerators, 3),
                            Pair.of(TT_Container_Casings.StabilisationFieldGenerators, 4),
                            Pair.of(TT_Container_Casings.StabilisationFieldGenerators, 5),
                            Pair.of(TT_Container_Casings.StabilisationFieldGenerators, 6),
                            Pair.of(TT_Container_Casings.StabilisationFieldGenerators, 7),
                            Pair.of(TT_Container_Casings.StabilisationFieldGenerators, 8)),
                        -1,
                        (t, meta) -> t.uStableField = meta,
                        t -> t.uStableField)))
            .addElement('G', ofFrame(Materials.Infinity))
            .build();
    }

    @Override
    public boolean addToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return super.addToMachineList(aTileEntity, aBaseCasingIndex)
            || addExoticEnergyInputToMachineList(aTileEntity, aBaseCasingIndex);
    }

    // spotless:off
    @SuppressWarnings("SpellCheckingInspection")
    private final String[][] shape = new String[][]{
        {"  E  ","  G  ","EGFGE","  G  ","  E  "},
        {" D~D ","D   D","B C B","D   D"," DBD "},
        {" BBB ","BAAAB","BAAAB","BAAAB"," BBB "}
    };
    //spotless:on

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        if (sideDirection == facingDirection) {
            if (active) return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings8, 7)),
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
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings8, 7)),
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
            .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings8, 7)) };
    }
    // endregion

    // region TT

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("星光聚能器")
            .addInfo("星辉锻造台的控制器")
            .addInfo(EnumChatFormatting.RED + "不要试图去理解原理.")
            .addInfo("使用星光将平凡转化为奇迹.")
            .addInfo("没有在指定位置安装稳定器时，最大并行为64.")
            .addInfo("安装稳定器后, 最大并行=16*2^等级.")
            .addInfo("且电压每提高1级, 额外降低(等级*2)%配方耗时(叠乘).")
            .addSeparator()
            .addInfo(Values.StructureTooComplex)
            .addInfo(Values.BluePrintTip)
            .beginStructureBlock(5, 3, 5, false)
            .addInputHatch(Values.BluePrintInfo, 1)
            .addOutputHatch(Values.BluePrintInfo, 1)
            .addInputBus(Values.BluePrintInfo, 1)
            .addOutputBus(Values.BluePrintInfo, 1)
            .addEnergyHatch(Values.BluePrintInfo, 2)
            .toolTipFinisher(Values.VisAppendMagical);
        return tt;
    }

    @Override
    public String[] getInfoData() {
        String u = String.valueOf(uStableField);
        String[] o = super.getInfoData();
        String[] n = new String[o.length + 1];
        System.arraycopy(o, 0, n, 0, o.length);
        n[o.length] = EnumChatFormatting.AQUA + "Stable Field" + ": " + EnumChatFormatting.GOLD + u;
        return n;
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setInteger("uStableField", uStableField);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        uStableField = aNBT.getInteger("uStableField");
    }

    // endregion
}
