package com.rhynia.gtnh.append.common.machine.multiMachine;

import com.gtnewhorizon.structurelib.alignment.constructable.IConstructable;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IItemSource;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.rhynia.gtnh.append.common.machine.mapRecipe.VARecipe;
import fox.spiteful.avaritia.blocks.LudicrousBlocks;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_EnhancedMultiBlockBase;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;
import org.jetbrains.annotations.NotNull;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static com.rhynia.gtnh.append.VisAppend.MOD_NAME;
import static com.rhynia.gtnh.append.util.UtilValues.*;
import static gregtech.api.enums.GT_HatchElement.*;
import static gregtech.api.enums.Materials.Infinity;
import static gregtech.api.enums.Materials.Neutronium;
import static gregtech.api.enums.Textures.BlockIcons.*;
import static gregtech.api.util.GT_StructureUtility.ofFrame;
import static gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_FusionComputer.STRUCTURE_PIECE_MAIN;

public class VA_TileEntity_CasimirGenerator extends GT_MetaTileEntity_EnhancedMultiBlockBase<VA_TileEntity_CasimirGenerator>
    implements IConstructable, ISurvivalConstructable {

    // region Class Constructor
    public VA_TileEntity_CasimirGenerator(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_CasimirGenerator(String aName) {
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
                return super.process();
            }
        }.setMaxParallelSupplier(this::getMaxParallelRecipes);
    }

    public int getMaxParallelRecipes() {
        return (int) (Math.pow(4, GT_Utility.getTier(this.getMaxInputVoltage())));
    }

    public float getSpeedBonus() {
        return (float) Math.pow(0.95, GT_Utility.getTier(this.getMaxInputVoltage()));
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return VARecipe.instance.AstraForgeRecipes;
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        return checkPiece(STRUCTURE_PIECE_MAIN, horizontalOffSet, verticalOffSet, depthOffSet);
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

    private final int horizontalOffSet = 1;
    private final int verticalOffSet = 10;
    private final int depthOffSet = 0;

    @Override
    public IStructureDefinition<VA_TileEntity_CasimirGenerator> getStructureDefinition() {
        return StructureDefinition.<VA_TileEntity_CasimirGenerator>builder()
            .addShape(STRUCTURE_PIECE_MAIN, transpose(shape))
            .addElement('A', ofBlock(GregTech_API.sBlockCasings2, 9))
            .addElement(
                'B',
                GT_HatchElementBuilder.<VA_TileEntity_CasimirGenerator>builder()
                    .atLeast(InputBus, OutputBus, InputHatch, OutputHatch, Maintenance, Energy.or(ExoticEnergy))
                    .adder(VA_TileEntity_CasimirGenerator::addToMachineList)
                    .dot(1)
                    .casingIndex(183)
                    .buildAndChain(GregTech_API.sBlockCasings8, 7))
            .addElement('C', ofBlock(LudicrousBlocks.resource_block, 1))
            .addElement('D', ofFrame(Neutronium))
            .addElement('E', ofFrame(Infinity))
            .build();
    }

    @Override
    public boolean addToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return super.addToMachineList(aTileEntity, aBaseCasingIndex)
            || addExoticEnergyInputToMachineList(aTileEntity, aBaseCasingIndex);
    }

    private final String[][] shape = new String[][] { { "   ", " E ", "   " }, { " D ", "DDD", " D " },
        { " B ", "BAB", " B " }, { " D ", "DAD", " D " }, { " D ", "DAD", " D " }, { " D ", "DAD", " D " },
        { "CEC", "ECE", "CEC" }, { " D ", "DAD", " D " }, { " D ", "DAD", " D " }, { " D ", "DAD", " D " },
        { " ~ ", "BAB", " B " }, { "BBB", "BBB", "BBB" } };
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
        tt.addMachineType("零点能差分机")
            .addInfo("星辉锻造台的控制器")
            .addInfo("§c不要试图去理解祂的原理.")
            .addInfo("使用星光将平凡转化为奇迹.")
            .addInfo("需要星极或星芒透镜辅助合成.")
            .addInfo("拥有与无损超频等效的并行(但有损超频).")
            .addInfo("电压每提高1级, 额外降低5%配方耗时, 叠乘计算.")
            .addSeparator()
            .addInfo(StructureTooComplex)
            .addInfo(BluePrintTip)
            .beginStructureBlock(3, 12, 3, false)
            .addInputHatch(BluePrintInfo, 1)
            .addOutputHatch(BluePrintInfo, 1)
            .addInputBus(BluePrintInfo, 1)
            .addOutputBus(BluePrintInfo, 1)
            .addMaintenanceHatch(BluePrintInfo, 3)
            .addEnergyHatch(BluePrintInfo, 2)
            .toolTipFinisher(MOD_NAME);
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
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_CasimirGenerator(this.mName);
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        if (sideDirection == facingDirection) {
            if (active) return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings8, 7)),
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
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings8, 7)),
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
}