package com.rhynia.gtnh.append.common.machine.multiMachine;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockUnlocalizedName;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static com.rhynia.gtnh.append.util.Values.BluePrintInfo;
import static com.rhynia.gtnh.append.util.Values.BluePrintTip;
import static com.rhynia.gtnh.append.util.Values.StructureTooComplex;
import static com.rhynia.gtnh.append.util.Values.VisAppendGigaFac;
import static gregtech.api.enums.GT_HatchElement.Energy;
import static gregtech.api.enums.GT_HatchElement.ExoticEnergy;
import static gregtech.api.enums.GT_HatchElement.InputBus;
import static gregtech.api.enums.GT_HatchElement.InputHatch;
import static gregtech.api.enums.GT_HatchElement.Maintenance;
import static gregtech.api.enums.GT_HatchElement.OutputBus;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_ASSEMBLY_LINE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_ASSEMBLY_LINE_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_ASSEMBLY_LINE_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_ASSEMBLY_LINE_GLOW;
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
import com.rhynia.gtnh.append.common.machine.recipeMap.VA_Recipe;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_ExtendedPowerMultiBlockBase;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import gregtech.common.blocks.GT_Block_Casings2;

public class VA_TileEntity_SuperconductingBinder
    extends GT_MetaTileEntity_ExtendedPowerMultiBlockBase<VA_TileEntity_SuperconductingBinder>
    implements IConstructable, ISurvivalConstructable {

    // region Class Constructor
    public VA_TileEntity_SuperconductingBinder(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_SuperconductingBinder(String aName) {
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
        return (int) (Math.pow(3, GT_Utility.getTier(this.getMaxInputVoltage())));
    }

    public float getSpeedBonus() {
        return (float) Math.pow(0.95, GT_Utility.getTier(this.getMaxInputVoltage()));
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipeMap() {
        return VA_Recipe.instance.sSuperconductingBinderRecipes;
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
    private final int verticalOffSet = 1;
    private final int depthOffSet = 0;

    @Override
    public IStructureDefinition<VA_TileEntity_SuperconductingBinder> getStructureDefinition() {
        return StructureDefinition.<VA_TileEntity_SuperconductingBinder>builder()
            .addShape(STRUCTURE_PIECE_MAIN, transpose(shape))
            .addElement(
                'A',
                ofChain(
                    ofBlockUnlocalizedName("IC2", "blockAlloyGlass", 0, true),
                    ofBlockUnlocalizedName("bartworks", "BW_GlasBlocks", 0, true),
                    // Warded Glass
                    ofBlockUnlocalizedName("Thaumcraft", "blockCosmeticOpaque", 2, false)))
            .addElement('B', ofBlock(GregTech_API.sBlockCasings1, 15))
            .addElement(
                'C',
                GT_HatchElementBuilder.<VA_TileEntity_SuperconductingBinder>builder()
                    .atLeast(InputBus, InputHatch, Energy.or(ExoticEnergy))
                    .adder(VA_TileEntity_SuperconductingBinder::addToMachineList)
                    .dot(1)
                    .casingIndex(((GT_Block_Casings2) GregTech_API.sBlockCasings2).getTextureIndex(9))
                    .buildAndChain(GregTech_API.sBlockCasings2, 9))
            .addElement('D', ofBlock(GregTech_API.sBlockCasings3, 10))
            .addElement(
                'E',
                GT_HatchElementBuilder.<VA_TileEntity_SuperconductingBinder>builder()
                    .atLeast(Maintenance)
                    .adder(VA_TileEntity_SuperconductingBinder::addToMachineList)
                    .dot(1)
                    .casingIndex(((GT_Block_Casings2) GregTech_API.sBlockCasings2).getTextureIndex(9))
                    .buildAndChain(GregTech_API.sBlockCasings2, 9))
            .addElement(
                'F',
                GT_HatchElementBuilder.<VA_TileEntity_SuperconductingBinder>builder()
                    .atLeast(OutputBus)
                    .adder(VA_TileEntity_SuperconductingBinder::addToMachineList)
                    .dot(1)
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
    private final String[][] shape = new String[][]{
        {"CCC","CDC","CDC","CDC","CDC","CDC","FFF"},
        {"C~C","ABA","ABA","ABA","ABA","ABA","FEF"},
        {"CCC","TTT","TTT","TTT","TTT","TTT","FFF"}
    };
//spotless:on
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
        tt.addMachineType("超导成型器")
            .addInfo("超导装配线的控制器")
            .addInfo("\"我不是很懂为什么超导烂大街了.\"")
            .addInfo("直接将原料构建为超导线缆.")
            .addInfo("电压每提高1级, 最大并行翻3倍.")
            .addInfo("电压每提高1级, 额外降低5%配方耗时, 叠乘计算.")
            .addSeparator()
            .addInfo(StructureTooComplex)
            .addInfo(BluePrintTip)
            .beginStructureBlock(3, 3, 7, false)
            .addInputHatch(BluePrintInfo, 1)
            .addInputBus(BluePrintInfo, 1)
            .addOutputBus(BluePrintInfo, 1)
            .addMaintenanceHatch(BluePrintInfo, 3)
            .addEnergyHatch(BluePrintInfo, 2)
            .toolTipFinisher(VisAppendGigaFac);
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
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_SuperconductingBinder(this.mName);
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        if (sideDirection == facingDirection) {
            if (active) return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings2, 9)),
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
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings2, 9)),
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
            .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings2, 9)) };
    }

    // endregion
}
