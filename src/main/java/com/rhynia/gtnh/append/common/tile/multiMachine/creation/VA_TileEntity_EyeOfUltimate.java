package com.rhynia.gtnh.append.common.tile.multiMachine.creation;

import static com.github.technus.tectech.TecTech.eyeOfHarmonyRecipeStorage;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlocksTiered;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static gregtech.api.enums.GT_HatchElement.InputBus;
import static gregtech.api.enums.GT_HatchElement.OutputBus;
import static gregtech.api.enums.GT_HatchElement.OutputHatch;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import com.github.technus.tectech.recipe.EyeOfHarmonyRecipe;
import com.github.technus.tectech.thing.CustomItemList;
import com.github.technus.tectech.thing.casing.TT_Container_Casings;
import com.github.technus.tectech.util.FluidStackLong;
import com.github.technus.tectech.util.ItemStackLong;
import com.google.common.collect.ImmutableList;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.rhynia.gtnh.append.api.enums.VA_Values;
import com.rhynia.gtnh.append.common.VA_ItemList;
import com.rhynia.gtnh.append.common.block.BlockBasic;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase_Cube;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IGlobalWirelessEnergy;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_InputBus;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.recipe.check.SimpleCheckRecipeResult;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_HatchElementBuilder;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;
import gregtech.common.blocks.GT_Block_Casings1;
import gregtech.common.tileentities.machines.GT_MetaTileEntity_Hatch_OutputBus_ME;
import gregtech.common.tileentities.machines.GT_MetaTileEntity_Hatch_Output_ME;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;

public class VA_TileEntity_EyeOfUltimate extends VA_MetaTileEntity_MultiBlockBase_Cube<VA_TileEntity_EyeOfUltimate>
    implements IGlobalWirelessEnergy {

    // region Builder
    public VA_TileEntity_EyeOfUltimate(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_EyeOfUltimate(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_EyeOfUltimate(this.mName);
    }
    // endregion

    // region Process
    private byte pRecipeTime = 0;
    private long pMultiplier = 0;
    private int pBaseA = 0;
    private int pBaseB = 0;
    private int pSpacetimeCompressionFieldMetadata = -1;
    private String pDisplayName = "";
    private String pUUID = "";
    private EyeOfHarmonyRecipe pCurrentRecipe;
    private List<ItemStackLong> outputItems = new ArrayList<ItemStackLong>();
    private List<FluidStackLong> outputFluids = new ArrayList<FluidStackLong>();

    @Override
    public final void onScrewdriverRightClick(ForgeDirection side, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        if (getBaseMetaTileEntity().isServerSide()) {
            this.pRecipeTime = (byte) ((this.pRecipeTime + 1) % 6);
            GT_Utility.sendChatToPlayer(aPlayer, "合成时间: " + (this.pRecipeTime + 1) + "s");
        }
    }

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return null;
    }

    @Override
    @NotNull
    public CheckRecipeResult checkProcessing() {
        resetState();
        ItemStack tempStack = getControllerSlot();
        if (tempStack == null) {
            return SimpleCheckRecipeResult.ofFailure("no_planet_block");
        }
        pCurrentRecipe = eyeOfHarmonyRecipeStorage.recipeLookUp(tempStack);
        if (pCurrentRecipe == null) {
            return CheckRecipeResultRegistry.NO_RECIPE;
        }

        pDisplayName = tempStack.getDisplayName();

        CheckRecipeResult result = processRecipe(pCurrentRecipe);

        if (result.wasSuccessful()) {
            return result;
        }

        pCurrentRecipe = null;

        return CheckRecipeResultRegistry.NO_RECIPE;
    }

    private CheckRecipeResult processRecipe(EyeOfHarmonyRecipe recipe) {
        getIndex();

        if (pSpacetimeCompressionFieldMetadata == -1) {
            return CheckRecipeResultRegistry.insufficientMachineTier((int) recipe.getSpacetimeCasingTierRequired());
        }
        if ((pSpacetimeCompressionFieldMetadata + 1) < recipe.getSpacetimeCasingTierRequired()) {
            return CheckRecipeResultRegistry.insufficientMachineTier((int) recipe.getSpacetimeCasingTierRequired());
        }

        if (!addEUToGlobalEnergyMap(pUUID, 1_000_000_000)) {
            return SimpleCheckRecipeResult.ofFailure("insufficient_power_no_val");
        }

        outputItems = recipe.getOutputItems();
        outputFluids = recipe.getOutputFluids();

        for (ItemStackLong itemStackLong : outputItems) {
            itemStackLong.stackSize *= pMultiplier;
            outputItemToAENetwork(itemStackLong.itemStack, itemStackLong.stackSize);
        }
        for (FluidStackLong fluidStackLong : outputFluids) {
            fluidStackLong.amount *= pMultiplier;
            outputFluidToAENetwork(fluidStackLong.fluidStack, fluidStackLong.amount);
        }

        outputItems = new ArrayList<>();
        outputFluids = new ArrayList<>();

        return CheckRecipeResultRegistry.SUCCESSFUL;
    }

    private void getIndex() {
        for (GT_MetaTileEntity_Hatch_InputBus inputBus : mInputBusses) {
            for (ItemStack itemStack : inputBus.getRealInventory()) {
                if (itemStack != null) {
                    if (itemStack.isItemEqual(CustomItemList.astralArrayFabricator.get(1))) {
                        pBaseA += itemStack.stackSize;
                    }
                    if (itemStack.isItemEqual(VA_ItemList.AstriumInfinityComplex.get(1))) {
                        pBaseB += itemStack.stackSize;
                    }
                }
            }
        }
        pMultiplier = 8L * pBaseA + 2L * pBaseB + 1L;
    }

    private void resetState() {
        mMaxProgresstime = 20 * (pRecipeTime + 1);
        mEfficiencyIncrease = 10000;
        pBaseA = pBaseB = 0;
        pMultiplier = 0;
        pCurrentRecipe = null;
        pDisplayName = "";
    }

    @Override
    public void onPreTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {
        super.onPreTick(aBaseMetaTileEntity, aTick);
        if (aTick == 1) {
            pUUID = String.valueOf(getBaseMetaTileEntity().getOwnerUuid());
            String userName = getBaseMetaTileEntity().getOwnerName();
            strongCheckOrAddUser(pUUID, userName);
        }
    }

    @Override
    public boolean supportsVoidProtection() {
        return false;
    }

    @Override
    public boolean supportsInputSeparation() {
        return false;
    }

    @Override
    public boolean supportsBatchMode() {
        return false;
    }

    @Override
    public boolean supportsSingleRecipeLocking() {
        return false;
    }

    private void outputItemToAENetwork(ItemStack item, long amount) {

        if ((item == null) || (amount <= 0)) {
            return;
        }

        if (amount < Integer.MAX_VALUE) {
            ItemStack tmpItem = item.copy();
            tmpItem.stackSize = (int) amount;
            ((GT_MetaTileEntity_Hatch_OutputBus_ME) mOutputBusses.get(0)).store(tmpItem);
        } else {
            // For item stacks > Int max.
            while (amount >= Integer.MAX_VALUE) {
                ItemStack tmpItem = item.copy();
                tmpItem.stackSize = Integer.MAX_VALUE;
                ((GT_MetaTileEntity_Hatch_OutputBus_ME) mOutputBusses.get(0)).store(tmpItem);
                amount -= Integer.MAX_VALUE;
            }
            ItemStack tmpItem = item.copy();
            tmpItem.stackSize = (int) amount;
            ((GT_MetaTileEntity_Hatch_OutputBus_ME) mOutputBusses.get(0)).store(tmpItem);
        }
    }

    private void outputFluidToAENetwork(FluidStack fluid, long amount) {

        if ((fluid == null) || (amount <= 0)) {
            return;
        }

        if (amount < Integer.MAX_VALUE) {
            FluidStack tmpFluid = fluid.copy();
            tmpFluid.amount = (int) amount;
            ((GT_MetaTileEntity_Hatch_Output_ME) mOutputHatches.get(0)).tryFillAE(tmpFluid);
        } else {
            // For fluidStacks > Int max.
            while (amount >= Integer.MAX_VALUE) {
                FluidStack tmpFluid = fluid.copy();
                tmpFluid.amount = Integer.MAX_VALUE;
                ((GT_MetaTileEntity_Hatch_Output_ME) mOutputHatches.get(0)).tryFillAE(tmpFluid);
                amount -= Integer.MAX_VALUE;
            }
            FluidStack tmpFluid = fluid.copy();
            tmpFluid.amount = (int) amount;
            ((GT_MetaTileEntity_Hatch_Output_ME) mOutputHatches.get(0)).tryFillAE(tmpFluid);
        }
    }

    // endregion

    // region Structure

    @Override
    protected Block sCasingBlock() {
        return TT_Container_Casings.sBlockCasingsBA0;
    }

    @Override
    protected int sCasingIndex() {
        return ((GT_Block_Casings1) GregTech_API.sBlockCasings1).getTextureIndex(12);
    }

    @Override
    protected int sCasingBlockMeta() {
        return 11;
    }

    @Override
    public IStructureDefinition<VA_TileEntity_EyeOfUltimate> getStructureDefinition() {
        return StructureDefinition.<VA_TileEntity_EyeOfUltimate>builder()
            .addShape(STRUCTURE_PIECE_MAIN, transpose(STRUCTURE))
            .addElement(
                'B',
                ofBlocksTiered(
                    (block, meta) -> block == BlockBasic.EyeOfHarmonyCoreCasing ? meta : null,
                    ImmutableList.of(
                        Pair.of(BlockBasic.EyeOfHarmonyCoreCasing, 0),
                        Pair.of(BlockBasic.EyeOfHarmonyCoreCasing, 1),
                        Pair.of(BlockBasic.EyeOfHarmonyCoreCasing, 2),
                        Pair.of(BlockBasic.EyeOfHarmonyCoreCasing, 3),
                        Pair.of(BlockBasic.EyeOfHarmonyCoreCasing, 4),
                        Pair.of(BlockBasic.EyeOfHarmonyCoreCasing, 5),
                        Pair.of(BlockBasic.EyeOfHarmonyCoreCasing, 6),
                        Pair.of(BlockBasic.EyeOfHarmonyCoreCasing, 7),
                        Pair.of(BlockBasic.EyeOfHarmonyCoreCasing, 8)),
                    -1,
                    (t, meta) -> t.pSpacetimeCompressionFieldMetadata = meta,
                    t -> t.pSpacetimeCompressionFieldMetadata))
            .addElement(
                'C',
                GT_HatchElementBuilder.<VA_TileEntity_EyeOfUltimate>builder()
                    .atLeast(InputBus, OutputBus, OutputHatch)
                    .adder(VA_TileEntity_EyeOfUltimate::addToMachineList)
                    .dot(1)
                    .casingIndex(sCasingIndex())
                    .buildAndChain(sCasingBlock(), sCasingBlockMeta()))
            .build();
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        pSpacetimeCompressionFieldMetadata = -1;
        return super.checkMachine(aBaseMetaTileEntity, aStack);
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        if (sideDirection == facingDirection) {
            if (active) return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(sCasingIndex()),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_DTPF_ON)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_DTPF_ON)
                    .extFacing()
                    .glow()
                    .build() };
            return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(sCasingIndex()), TextureFactory.builder()
                .addIcon(Textures.BlockIcons.OVERLAY_DTPF_OFF)
                .extFacing()
                .build(),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_DTPF_OFF)
                    .extFacing()
                    .glow()
                    .build() };
        }
        return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(sCasingIndex()) };
    }

    // endregion

    // region Info
    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("终极之眼")
            .addInfo("终极之眼的控制器")
            .addInfo("执行鸿蒙之眼配方.")
            .addInfo("每个星阵提供8并行, 每个星矩提供2并行.")
            .addInfo("不产出能量, 直接从无线电网获取所需能量.")
            .addSeparator()
            .addInfo(VA_Values.CommonStrings.BluePrintTip)
            .beginStructureBlock(3, 3, 3, false)
            .addInputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addOutputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addOutputHatch(VA_Values.CommonStrings.BluePrintInfo, 1)
            .toolTipFinisher(VA_Values.CommonStrings.VisAppendGigaFac);
        return tt;
    }

    @Override
    public String[] getInfoData() {
        String[] oStr = super.getInfoData();
        String[] nStr = new String[oStr.length + 1];
        System.arraycopy(oStr, 0, nStr, 0, oStr.length);
        nStr[oStr.length] = EnumChatFormatting.AQUA + "执行并行: "
            + EnumChatFormatting.GOLD
            + GT_Utility.formatNumbers(pMultiplier);
        return nStr;
    }

    @Override
    public void getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor,
        IWailaConfigHandler config) {
        super.getWailaBody(itemStack, currentTip, accessor, config);
        final NBTTagCompound tag = accessor.getNBTData();

        if (!tag.getString("pDisplayNameW")
            .isEmpty()) {
            currentTip
                .add(EnumChatFormatting.WHITE + "执行配方: " + EnumChatFormatting.AQUA + tag.getString("pDisplayNameW"));
        }

    }

    @Override
    public void getWailaNBTData(EntityPlayerMP player, TileEntity tile, NBTTagCompound tag, World world, int x, int y,
        int z) {
        super.getWailaNBTData(player, tile, tag, world, x, y, z);
        final IGregTechTileEntity tileEntity = getBaseMetaTileEntity();
        if (tileEntity != null) {
            if (tileEntity.isActive()) {
                tag.setString("pDisplayNameW", pDisplayName);
            }
        }
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setLong("pMultiplier", pMultiplier);
        aNBT.setInteger("pBaseA", pBaseA);
        aNBT.setInteger("pBaseB", pBaseB);
        aNBT.setByte("pRecipeTime", pRecipeTime);
        aNBT.setInteger("pSpacetimeCompressionFieldMetadata", pSpacetimeCompressionFieldMetadata);
        aNBT.setString("pDisplayName", pDisplayName);

        NBTTagCompound itemStackListNBTTag = new NBTTagCompound();
        itemStackListNBTTag.setLong("EOUItems", outputItems.size());
        int indexItems = 0;
        for (ItemStackLong itemStackLong : outputItems) {
            itemStackListNBTTag.setLong(indexItems + "stackSize", itemStackLong.stackSize);
            aNBT.setTag(indexItems + "itemStack", itemStackLong.itemStack.writeToNBT(new NBTTagCompound()));
            indexItems++;
        }
        aNBT.setTag("EOUItemsTag", itemStackListNBTTag);

        NBTTagCompound fluidStackListNBTTag = new NBTTagCompound();
        fluidStackListNBTTag.setLong("EOUFluids", outputFluids.size());
        int indexFluids = 0;
        for (FluidStackLong fluidStackLong : outputFluids) {
            fluidStackListNBTTag.setLong(indexFluids + "amount", fluidStackLong.amount);
            aNBT.setTag(indexFluids + "fluidStack", fluidStackLong.fluidStack.writeToNBT(new NBTTagCompound()));
            indexFluids++;
        }
        aNBT.setTag("EOUFluidsTag", fluidStackListNBTTag);

        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        pMultiplier = aNBT.getLong("pMultiplier");
        pBaseA = aNBT.getInteger("pBaseA");
        pBaseB = aNBT.getInteger("pBaseB");
        pRecipeTime = aNBT.getByte("pRecipeTime");
        pSpacetimeCompressionFieldMetadata = aNBT.getInteger("pSpacetimeCompressionFieldMetadata");
        pDisplayName = aNBT.getString("pDisplayName");

        NBTTagCompound tempItemTag = aNBT.getCompoundTag("EOUItemsTag");
        for (int indexItems = 0; indexItems < tempItemTag.getInteger("EOUItems"); indexItems++) {
            long stackSize = tempItemTag.getLong(indexItems + "stackSize");
            ItemStack itemStack = ItemStack.loadItemStackFromNBT(aNBT.getCompoundTag(indexItems + "itemStack"));
            outputItems.add(new ItemStackLong(itemStack, stackSize));
        }

        NBTTagCompound tempFluidTag = aNBT.getCompoundTag("EOUFluidsTag");
        for (int indexFluids = 0; indexFluids < tempFluidTag.getInteger("EOUFluids"); indexFluids++) {
            long fluidAmount = tempFluidTag.getLong(indexFluids + "amount");
            FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(aNBT.getCompoundTag(indexFluids + "fluidStack"));
            outputFluids.add(new FluidStackLong(fluidStack, fluidAmount));
        }

        super.loadNBTData(aNBT);
    }
    // endregion
}
