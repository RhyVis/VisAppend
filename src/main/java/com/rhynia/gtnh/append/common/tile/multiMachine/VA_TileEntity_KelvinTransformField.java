package com.rhynia.gtnh.append.common.tile.multiMachine;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.enums.GT_HatchElement.Energy;
import static gregtech.api.enums.GT_HatchElement.ExoticEnergy;
import static gregtech.api.enums.GT_HatchElement.InputBus;
import static gregtech.api.enums.GT_HatchElement.InputHatch;
import static gregtech.api.enums.GT_HatchElement.OutputBus;
import static gregtech.api.enums.GT_HatchElement.OutputHatch;
import static gregtech.api.enums.Textures.BlockIcons.casingTexturePages;
import static gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_FusionComputer.STRUCTURE_PIECE_MAIN;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import com.gtnewhorizon.structurelib.structure.IItemSource;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.rhynia.gtnh.append.api.util.enums.CommonStrings;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;
import gregtech.common.blocks.GT_Block_Casings2;
import gtPlusPlus.api.recipe.GTPPRecipeMaps;

public class VA_TileEntity_KelvinTransformField
    extends VA_MetaTileEntity_MultiBlockBase<VA_TileEntity_KelvinTransformField> {

    public byte mRecipeMode = 0; // 0-sVacuumRecipes,1-?

    // region Class Constructor
    public VA_TileEntity_KelvinTransformField(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_KelvinTransformField(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_KelvinTransformField(this.mName);
    }
    // endregion

    // region Processing Logic

    @Override
    public int rMaxParallel() {
        return mRecipeMode == 0 ? 2048 : 64 + 16 * GT_Utility.getTier(this.getMaxInputVoltage());
    }

    @Override
    public float rSpeedBonus() {
        return mRecipeMode == 0 ? (float) Math.pow(0.95, GT_Utility.getTier(this.getMaxInputVoltage())) : 1.0F;
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return mRecipeMode == 0 ? RecipeMaps.vacuumFreezerRecipes : GTPPRecipeMaps.advancedFreezerRecipes;
    }

    @Nonnull
    @Override
    public Collection<RecipeMap<?>> getAvailableRecipeMaps() {
        return Arrays.asList(RecipeMaps.vacuumFreezerRecipes, GTPPRecipeMaps.advancedFreezerRecipes);
    }

    @Override
    public int getRecipeCatalystPriority() {
        return -10;
    }

    @Override
    public final void onScrewdriverRightClick(ForgeDirection side, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        if (getBaseMetaTileEntity().isServerSide()) {
            this.mRecipeMode = (byte) ((this.mRecipeMode + 1) % 2);
            GT_Utility.sendChatToPlayer(
                aPlayer,
                StatCollector.translateToLocal("append.KelvinTransformField.mRecipeMode." + this.mRecipeMode));
        }
    }
    // endregion

    // region Structure

    private final int hOffSet = 1, vOffSet = 1, dOffSet = 0;

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        removeMaintenance();
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
    public IStructureDefinition<VA_TileEntity_KelvinTransformField> getStructureDefinition() {
        return StructureDefinition.<VA_TileEntity_KelvinTransformField>builder()
            .addShape(STRUCTURE_PIECE_MAIN, transpose(shape))
            .addElement('B', ofBlock(GregTech_API.sBlockCasings4, 7))
            .addElement(
                'C',
                GT_HatchElementBuilder.<VA_TileEntity_KelvinTransformField>builder()
                    .atLeast(InputBus, InputHatch, OutputBus, OutputHatch, Energy.or(ExoticEnergy))
                    .adder(VA_TileEntity_KelvinTransformField::addToMachineList)
                    .dot(1)
                    .casingIndex(((GT_Block_Casings2) GregTech_API.sBlockCasings2).getTextureIndex(1))
                    .buildAndChain(GregTech_API.sBlockCasings2, 1))
            .build();
    }

    @Override
    public boolean addToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return super.addToMachineList(aTileEntity, aBaseCasingIndex)
            || addExoticEnergyInputToMachineList(aTileEntity, aBaseCasingIndex);
    }

    // spotless:off
    private final String[][] shape = new String[][]{
        {"CCC","CCC","CCC"},
        {"C~C","CBC","CCC"},
        {"CCC","CCC","CCC"}
    };
    //spotless:on
    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        if (sideDirection == facingDirection) {
            if (active) return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings2, 1)),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_FRONT_VACUUM_FREEZER_ACTIVE)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_FRONT_VACUUM_FREEZER_ACTIVE_GLOW)
                    .extFacing()
                    .glow()
                    .build() };
            return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(GregTech_API.sBlockCasings2, 1)),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_FRONT_VACUUM_FREEZER)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_FRONT_VACUUM_FREEZER_GLOW)
                    .extFacing()
                    .glow()
                    .build() };
        }
        return new ITexture[] { casingTexturePages[0][17] };
    }
    // endregion

    // region Overrides

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("真空冷冻机 | 热动力学解析")
            .addInfo("开尔文变换场的控制器")
            .addInfo(
                EnumChatFormatting.RED + "\"万物都有"
                    + EnumChatFormatting.DARK_RED
                    + "终结"
                    + EnumChatFormatting.RED
                    + "，我不过是定义了它的到来.\"")
            .addInfo("指挥粒子做它该做的热运动.")
            .addInfo("真空冷冻机模式下，最大并行为2048.")
            .addInfo("且电压每提高1级, 降低5%配方耗时(叠乘计算).")
            .addInfo("热动力学解析模式下，基础最大并行为64.")
            .addInfo("且电压每提高1级, 增加16并行.")
            .addInfo(CommonStrings.ChangeModeByScrewdriver)
            .addSeparator()
            .addInfo(CommonStrings.StructureTooComplex)
            .addInfo(CommonStrings.BluePrintTip)
            .beginStructureBlock(3, 3, 3, false)
            .addInputBus(CommonStrings.BluePrintInfo, 1)
            .addInputHatch(CommonStrings.BluePrintInfo, 1)
            .addOutputBus(CommonStrings.BluePrintInfo, 1)
            .addOutputHatch(CommonStrings.BluePrintInfo, 1)
            .addEnergyHatch(CommonStrings.BluePrintInfo, 2)
            .toolTipFinisher(CommonStrings.VisAppendNuclear);
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
