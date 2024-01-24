package com.rhynia.gtnh.append.common.tile.multiMachine;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlockUnlocalizedName;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.enums.GT_HatchElement.InputBus;
import static gregtech.api.enums.GT_HatchElement.InputHatch;
import static gregtech.api.enums.GT_HatchElement.OutputBus;
import static gregtech.api.enums.GT_HatchElement.OutputHatch;
import static gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_FusionComputer.STRUCTURE_PIECE_MAIN;

import java.io.Serial;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;
import com.gtnewhorizon.structurelib.structure.IItemSource;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.rhynia.gtnh.append.api.util.enums.CommonStrings;
import com.rhynia.gtnh.append.common.material.VAMaterials;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase_EM;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IGlobalWirelessEnergy;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.recipe.check.SimpleCheckRecipeResult;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;
import gregtech.common.blocks.GT_Block_Casings2;

public class VA_TileEntity_VoidEnergyGenerator extends VA_MetaTileEntity_MultiBlockBase_EM
    implements IGlobalWirelessEnergy {

    // region Class Constructor
    public VA_TileEntity_VoidEnergyGenerator(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_VoidEnergyGenerator(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_VoidEnergyGenerator(this.mName);
    }
    // endregion

    // region Processing Logic

    private String sUUID = "";
    private BigInteger pOutput = BigInteger.ZERO;
    private boolean bWorking = false;
    private final BigInteger pBaseOutputMultiply = BigInteger.valueOf(Integer.MAX_VALUE);
    private final long pBaseAstriumBuffer = 1_000L;
    private final long pAstriumRequirement = 1_000_000L;

    private final Map<FluidStack, Long> pFluidMap = new HashMap<>() {

        @Serial
        private static final long serialVersionUID = -899844111188130L;
        {
            put(VAMaterials.Astrium.getMolten(1), 0L);
            put(VAMaterials.AstriumInfinity.getMolten(1), 0L);
            put(VAMaterials.AstriumMagic.getMolten(1), 0L);
        }
    };

    private void pDrainFluid() {
        for (GT_MetaTileEntity_Hatch_Input inputHatch : mInputHatches) {
            FluidStack fluidInHatch = inputHatch.getFluid();
            if (fluidInHatch == null) {
                continue;
            }
            // Get fluid in hatches and store
            for (FluidStack validFluid : pFluidMap.keySet()) {
                if (fluidInHatch.isFluidEqual(validFluid)) {
                    pFluidMap.put(validFluid, pFluidMap.get(validFluid) + (long) fluidInHatch.amount);
                    inputHatch.setFillableStack(null);
                }
            }
        }
    }

    @Override
    public void onPreTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        super.onPreTick(aBaseMetaTileEntity, aTick);
        if (aTick == 1) {
            sUUID = String.valueOf(getBaseMetaTileEntity().getOwnerUuid());
            String userName = getBaseMetaTileEntity().getOwnerName();
            strongCheckOrAddUser(sUUID, userName);
        }
        if (!bWorking) {
            if ((aTick % 20) == 0) {
                pDrainFluid();
            }
        }
    }

    private long getAstrium() {
        return pFluidMap.get(VAMaterials.Astrium.getMolten(1));
    }

    private void clearStorage(Werkstoff material) {
        pFluidMap.put(material.getMolten(1), 0L);
    }

    private void clearStorage() {
        pFluidMap.put(VAMaterials.Astrium.getMolten(1), 0L);
        pFluidMap.put(VAMaterials.AstriumInfinity.getMolten(1), 0L);
        pFluidMap.put(VAMaterials.AstriumMagic.getMolten(1), 0L);
    }

    @Override
    @NotNull
    public CheckRecipeResult checkProcessing_EM() {
        mMaxProgresstime = 128;

        if (getAstrium() < pAstriumRequirement) {
            return SimpleCheckRecipeResult.ofFailure("no_astrium");
        }

        pOutput = BigInteger.valueOf(getAstrium() / pBaseAstriumBuffer)
            .multiply(pBaseOutputMultiply);

        addEUToGlobalEnergyMap(sUUID, pOutput);

        clearStorage(VAMaterials.Astrium);

        bWorking = true;

        return CheckRecipeResultRegistry.SUCCESSFUL;
    }

    public void outputAfterRecipe_EM() {
        bWorking = false;
        eRequiredData = 0L;
        pOutput = BigInteger.ZERO;
        super.outputAfterRecipe_EM();
    }

    @Override
    public void stopMachine() {
        super.stopMachine();
        bWorking = false;
    }
    // endregion

    // region Structure
    private final int hOffset = 1, vOffset = 1, dOffset = 0;

    @Override
    public boolean checkMachine_EM(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        turnOffMaintenance();
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
    public IStructureDefinition<VA_TileEntity_VoidEnergyGenerator> getStructure_EM() {
        return StructureDefinition.<VA_TileEntity_VoidEnergyGenerator>builder()
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
                GT_HatchElementBuilder.<VA_TileEntity_VoidEnergyGenerator>builder()
                    .atLeast(InputBus, InputHatch)
                    .adder(VA_TileEntity_VoidEnergyGenerator::addToMachineList)
                    .dot(1)
                    .casingIndex(((GT_Block_Casings2) GregTech_API.sBlockCasings2).getTextureIndex(9))
                    .buildAndChain(GregTech_API.sBlockCasings2, 9))
            .addElement('D', ofBlock(GregTech_API.sBlockCasings3, 10))
            .addElement(
                'F',
                GT_HatchElementBuilder.<VA_TileEntity_VoidEnergyGenerator>builder()
                    .atLeast(OutputBus, OutputHatch)
                    .adder(VA_TileEntity_VoidEnergyGenerator::addToMachineList)
                    .dot(1)
                    .casingIndex(((GT_Block_Casings2) GregTech_API.sBlockCasings2).getTextureIndex(9))
                    .buildAndChain(GregTech_API.sBlockCasings2, 9))
            .addElement('T', ofBlock(GregTech_API.sBlockCasings2, 9))
            .build();
    }

    // spotless:off
    private final String[][] shape = new String[][]{
        {"CCC","TDT","FFF"},
        {"C~C","ABA","FFF"},
        {"CCC","TTT","FFF"}
    };
    // spotless:on
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
        tt.addMachineType("星辉能极差发电机")
            .addInfo("星辉能极差发电机的控制器")
            .addInfo("\"这里面也有能量?\"")
            .addInfo("产出的能量将直接输出至无线网络.")
            .addSeparator()
            .addInfo(CommonStrings.StructureTooComplex)
            .addInfo(CommonStrings.BluePrintTip)
            .beginStructureBlock(3, 3, 7, false)
            .addInputHatch(CommonStrings.BluePrintInfo, 1)
            .addInputBus(CommonStrings.BluePrintInfo, 1)
            .addOutputBus(CommonStrings.BluePrintInfo, 1)
            .addMaintenanceHatch(CommonStrings.BluePrintInfo, 3)
            .addEnergyHatch(CommonStrings.BluePrintInfo, 2)
            .toolTipFinisher(CommonStrings.VisAppendGigaFac);
        return tt;
    }

    // endregion
}
