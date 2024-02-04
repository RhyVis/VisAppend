package com.rhynia.gtnh.append.common.tile.base;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.isAir;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.enums.GT_HatchElement.InputBus;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import org.jetbrains.annotations.ApiStatus.OverrideOnly;

import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Utility;

public abstract class VA_MetaTileEntity_MultiBlockBase_Cube<T extends VA_MetaTileEntity_MultiBlockBase<T>>
    extends VA_MetaTileEntity_MultiBlockBase<T> {

    // region Builder
    protected VA_MetaTileEntity_MultiBlockBase_Cube(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    protected VA_MetaTileEntity_MultiBlockBase_Cube(String aName) {
        super(aName);
    }
    // endregion

    // region Structure
    @OverrideOnly
    protected abstract Block sCasingBlock();

    @OverrideOnly
    protected abstract Block sCoreBlock();

    @OverrideOnly
    protected abstract int sCasingIndex();

    @OverrideOnly
    protected abstract int sCasingBlockMeta();

    @OverrideOnly
    protected abstract int sCoreBlockMeta();

    protected final int hOffSet = 1, vOffSet = 1, dOffSet = 0;

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        removeMaintenance();
        return checkPiece(STRUCTURE_PIECE_MAIN, hOffSet, vOffSet, dOffSet);
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        this.buildPiece(STRUCTURE_PIECE_MAIN, stackSize, hintsOnly, hOffSet, vOffSet, dOffSet);
    }

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, ISurvivalBuildEnvironment env) {
        if (this.mMachine) return -1;
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
    public IStructureDefinition<T> getStructureDefinition() {
        return StructureDefinition.<T>builder()
            .addShape(STRUCTURE_PIECE_MAIN, transpose(STRUCTURE))
            .addElement('B', sCoreBlock() == null ? isAir() : ofBlock(sCoreBlock(), sCoreBlockMeta()))
            .addElement(
                'C',
                GT_HatchElementBuilder.<T>builder()
                    .atLeast(InputBus)
                    .adder(T::addToMachineList)
                    .dot(1)
                    .casingIndex(sCasingIndex())
                    .buildAndChain(sCasingBlock(), sCasingBlockMeta()))
            .build();
    }

    // spotless:off
    protected final String[][] STRUCTURE = new String[][]{
        {"CCC","CCC","CCC"},
        {"C~C","CBC","CCC"},
        {"CCC","CCC","CCC"}
    };
    // spotless:on
    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        if (sideDirection == facingDirection) {
            if (active) return new ITexture[] {
                Textures.BlockIcons
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(sCasingBlock(), sCasingBlockMeta())),
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
                    .getCasingTextureForId(GT_Utility.getCasingTextureIndex(sCasingBlock(), sCasingBlockMeta())),
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
            .getCasingTextureForId(GT_Utility.getCasingTextureIndex(sCasingBlock(), sCasingBlockMeta())) };
    }
    // endregion
}
