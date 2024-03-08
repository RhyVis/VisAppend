package com.rhynia.gtnh.append.common.tile.multiMachine.creation;

import static bloodasp.galacticgreg.registry.GalacticGregRegistry.getModContainers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.util.ForgeDirection;

import org.jetbrains.annotations.NotNull;

import com.github.bartimaeusnek.bartworks.common.configs.ConfigHandler;
import com.github.bartimaeusnek.bartworks.system.material.Werkstoff;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.github.bartimaeusnek.bartworks.system.oregen.BW_OreLayer;
import com.github.bartimaeusnek.bartworks.util.Pair;
import com.google.common.collect.ArrayListMultimap;
import com.rhynia.gtnh.append.api.enums.VA_Values;
import com.rhynia.gtnh.append.api.util.ItemHelper;
import com.rhynia.gtnh.append.api.util.MathHelper;
import com.rhynia.gtnh.append.common.tile.base.VA_MetaTileEntity_MultiBlockBase_Cube;

import bloodasp.galacticgreg.GT_Worldgen_GT_Ore_Layer_Space;
import bloodasp.galacticgreg.GT_Worldgen_GT_Ore_SmallPieces_Space;
import bloodasp.galacticgreg.GalacticGreg;
import bloodasp.galacticgreg.api.ModDimensionDef;
import bloodasp.galacticgreg.bartworks.BW_Worldgen_Ore_Layer_Space;
import bloodasp.galacticgreg.bartworks.BW_Worldgen_Ore_SmallOre_Space;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IGlobalWirelessEnergy;
import gregtech.api.interfaces.ISubTagContainer;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.objects.XSTR;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Utility;
import gregtech.common.GT_Worldgen_GT_Ore_Layer;
import gregtech.common.GT_Worldgen_GT_Ore_SmallPieces;

public class VA_TileEntity_VoidMiner extends VA_MetaTileEntity_MultiBlockBase_Cube<VA_TileEntity_VoidMiner>
    implements IGlobalWirelessEnergy {

    // region Builder
    public VA_TileEntity_VoidMiner(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public VA_TileEntity_VoidMiner(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_TileEntity_VoidMiner(this.mName);
    }
    // endregion

    // region Process
    private static ArrayListMultimap<Integer, Pair<Pair<Integer, Boolean>, Float>> extraDropsDimMap = ArrayListMultimap
        .create();
    private Map<Pair<Integer, Boolean>, Float> dropmap = null;
    private float totalWeight;
    private int multiplier = 1;

    public static void addMaterialToDimensionList(int DimensionID, ISubTagContainer Material, float weight) {
        if (Material instanceof Materials) getExtraDropsDimMap()
            .put(DimensionID, new Pair<>(new Pair<>(((Materials) Material).mMetaItemSubID, false), weight));
        else if (Material instanceof Werkstoff) getExtraDropsDimMap()
            .put(DimensionID, new Pair<>(new Pair<>((int) ((Werkstoff) Material).getmID(), true), weight));
    }

    // adding tellurium to OW to ensure a way to get it, as it's used in Magneto Resonatic Dust and Circuit Compound MK3
    // Dust
    static {
        addMaterialToDimensionList(0, Materials.Tellurium, 8.0f);
    }

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return null;
    }

    @Override
    @NotNull
    public CheckRecipeResult checkProcessing() {
        if (this.dropmap == null || this.totalWeight == 0) this.calculateDropMap();
        resetState();
        int b1, b2;
        ItemStack controlStack = getControllerSlot();
        if (ItemHelper.isCalibration(controlStack)) {
            b1 = controlStack.stackSize;
        } else b1 = 1;
        b2 = this.getStoredInputs()
            .stream()
            .filter(ItemHelper::isAstralInfinityGem)
            .map(stack -> stack.stackSize)
            .reduce(Integer::sum)
            .orElse(0);
        this.multiplier = MathHelper.safeInt((long) (b1 * Math.pow(2, b2)), 7);

        AtomicBoolean ext = new AtomicBoolean(false);
        this.getStoredInputs()
            .stream()
            .filter(GT_Utility::isOre)
            .findFirst()
            .ifPresent(stack -> {
                var tmp = GT_Utility.copyAmountUnsafe(this.multiplier, stack);
                mOutputItems = new ItemStack[] { tmp };
                ext.set(true);
            });
        if (ext.get()) return CheckRecipeResultRegistry.SUCCESSFUL;
        if (this.totalWeight != 0.f) {
            var output = this.getOreItemStack(this.getOreDamage());
            mOutputItems = new ItemStack[] { output };
            return CheckRecipeResultRegistry.SUCCESSFUL;
        }
        return CheckRecipeResultRegistry.NO_RECIPE;
    }

    private void resetState() {
        this.multiplier = 1;
        this.mEUt = 0;
        this.lEUt = 0;
        this.mMaxProgresstime = 20;
    }

    private void calculateDropMap() {
        this.dropmap = new HashMap<>();
        int id = this.getBaseMetaTileEntity()
            .getWorld().provider.dimensionId;
        this.handleModDimDef(id);
        this.handleExtraDrops(id);
        this.calculateTotalWeight();
    }

    /**
     * Computes the ores of the dim for the specifed dim id
     *
     * @param id the dim number
     */
    private void handleModDimDef(int id) {
        // vanilla dims or TF
        if (id <= 1 && id >= -1 || id == 7) {
            this.getDropsVanillaVeins(this.makeOreLayerPredicate());
            this.getDropsVanillaSmallOres(this.makeSmallOresPredicate());

            // ross dims
        } else if (id == ConfigHandler.ross128BID || id == ConfigHandler.ross128BAID) {
            this.getDropMapRoss(id);

            // other space dims
        } else {
            Optional.ofNullable(this.makeModDimDef())
                .ifPresent(def -> {
                    // normal space dim
                    this.getDropsOreVeinsSpace(def);
                    this.getDropsSmallOreSpace(def);

                    // BW space dim
                    Consumer<BW_OreLayer> addToList = this.makeAddToList();
                    this.addOresVeinsBartworks(def, addToList);
                    this.addSmallOresBartworks(def);
                });
        }
    }

    /**
     * Handles the ores added manually with addMatierialToDimensionList
     *
     * @param id the specified dim id
     */
    private void handleExtraDrops(int id) {
        Optional.ofNullable(getExtraDropsDimMap().get(id))
            .ifPresent(e -> e.forEach(f -> this.addDrop(f.getKey(), f.getValue())));
    }

    /**
     * Computes the total weight for normalisation
     */
    private void calculateTotalWeight() {
        this.totalWeight = 0.0f;
        this.dropmap.values()
            .forEach(f -> this.totalWeight += f);
    }

    /**
     * Method to add the ores of a vanilla GT worldgen
     *
     * @param oreLayerPredicate the predicate made by makeOreLayerPredicate
     */
    private void getDropsVanillaVeins(Predicate<GT_Worldgen_GT_Ore_Layer> oreLayerPredicate) {
        GT_Worldgen_GT_Ore_Layer.sList.stream()
            .filter(gt_worldgen -> gt_worldgen.mEnabled && oreLayerPredicate.test(gt_worldgen))
            .forEach(element -> {
                this.addDrop(new Pair<>((int) element.mPrimaryMeta, false), element.mWeight);
                this.addDrop(new Pair<>((int) element.mSecondaryMeta, false), element.mWeight);
                this.addDrop(new Pair<>((int) element.mSporadicMeta, false), element.mWeight / 8f);
                this.addDrop(new Pair<>((int) element.mBetweenMeta, false), element.mWeight / 8f);
            });
    }

    /**
     * Method to add the small ores of a vanilla GT worldgen
     *
     * @param smallOresPredicate the predicate made by makeSmallOresPredicate
     */
    private void getDropsVanillaSmallOres(Predicate<GT_Worldgen_GT_Ore_SmallPieces> smallOresPredicate) {
        GT_Worldgen_GT_Ore_SmallPieces.sList.stream()
            .filter(gt_worldgen -> gt_worldgen.mEnabled && smallOresPredicate.test(gt_worldgen))
            .forEach(element -> this.addDrop(new Pair<>((int) element.mMeta, false), element.mAmount));
    }

    /**
     * Handles the addition of Ross dims' ores into the drop map
     *
     * @param aID dim id of Ross128b or Ross128ba
     */
    private void getDropMapRoss(int aID) {
        Consumer<BW_OreLayer> addToList = this.makeAddToList();
        BW_OreLayer.sList.stream()
            .filter(
                gt_worldgen -> gt_worldgen.mEnabled && gt_worldgen instanceof BW_OreLayer
                    && gt_worldgen.isGenerationAllowed(null, aID, 0))
            .forEach(addToList);
    }

    /**
     * add to the dropmap the ores from the gagreg space worldgen corresponding to the target dim
     *
     * @param finalDef ModDimensionDef corresponding to the target dim
     */
    private void getDropsOreVeinsSpace(ModDimensionDef finalDef) {
        GalacticGreg.oreVeinWorldgenList.stream()
            .filter(
                gt_worldgen -> gt_worldgen.mEnabled
                    && gt_worldgen instanceof GT_Worldgen_GT_Ore_Layer_Space oreLayerSpace
                    && oreLayerSpace.isEnabledForDim(finalDef))
            .map(gt_worldgen -> (GT_Worldgen_GT_Ore_Layer_Space) gt_worldgen)
            .forEach(element -> {
                this.addDrop(new Pair<>((int) element.mPrimaryMeta, false), element.mWeight);
                this.addDrop(new Pair<>((int) element.mSecondaryMeta, false), element.mWeight);
                this.addDrop(new Pair<>((int) element.mSporadicMeta, false), element.mWeight / 8f);
                this.addDrop(new Pair<>((int) element.mBetweenMeta, false), element.mWeight / 8f);
            });
    }

    /**
     * add to the dropmap the small ores from the gagreg space worldgen corresponding to the target dim
     *
     * @param finalDef ModDimensionDef corresponding to the target dim
     */
    private void getDropsSmallOreSpace(ModDimensionDef finalDef) {
        GalacticGreg.smallOreWorldgenList.stream()
            .filter(
                gt_worldgen -> gt_worldgen.mEnabled
                    && gt_worldgen instanceof GT_Worldgen_GT_Ore_SmallPieces_Space oreSmallPiecesSpace
                    && oreSmallPiecesSpace.isEnabledForDim(finalDef))
            .map(gt_worldgen -> (GT_Worldgen_GT_Ore_SmallPieces_Space) gt_worldgen)
            .forEach(element -> this.addDrop(new Pair<>((int) element.mMeta, false), element.mAmount));
    }

    /**
     * Handles the addition of small ores for bartwork dims
     *
     * @param finalDef  the ModDimensionDef object corresponding to the dim
     * @param addToList a consumer used to add the ores from the vein with proper weight
     */
    private void addOresVeinsBartworks(ModDimensionDef finalDef, Consumer<BW_OreLayer> addToList) {
        try {
            GalacticGreg.oreVeinWorldgenList.stream()
                .filter(
                    gt_worldgen -> gt_worldgen.mEnabled
                        && gt_worldgen instanceof BW_Worldgen_Ore_Layer_Space oreLayerSpace
                        && oreLayerSpace.isEnabledForDim(finalDef))
                .map(gt_worldgen -> (BW_Worldgen_Ore_Layer_Space) gt_worldgen)
                .forEach(addToList);
        } catch (NullPointerException ignored) {}
    }

    /**
     * Handles the addition of small ores for bartwork dims
     *
     * @param finalDef the ModDimensionDef object corresponding to the dim
     */
    private void addSmallOresBartworks(ModDimensionDef finalDef) {
        try {
            GalacticGreg.smallOreWorldgenList.stream()
                .filter(
                    gt_worldgen -> gt_worldgen.mEnabled
                        && gt_worldgen instanceof BW_Worldgen_Ore_SmallOre_Space smallOreSpace
                        && smallOreSpace.isEnabledForDim(finalDef))
                .map(gt_worldgen -> (BW_Worldgen_Ore_SmallOre_Space) gt_worldgen)
                .forEach(
                    element -> this.addDrop(new Pair<>(element.mPrimaryMeta, element.bwOres != 0), element.mDensity));
        } catch (NullPointerException ignored) {}
    }

    /**
     * getter for the external drop map
     *
     * @return the extraDriosDimMap
     */
    public static ArrayListMultimap<Integer, Pair<Pair<Integer, Boolean>, Float>> getExtraDropsDimMap() {
        return extraDropsDimMap;
    }

    /**
     * Method used to generate a consumer used specifically to add BW ores into the dropmap
     *
     * @return the consumer
     */
    private Consumer<BW_OreLayer> makeAddToList() {
        return element -> {
            List<Pair<Integer, Boolean>> data = element.getStacksRawData();
            for (int i = 0; i < data.size(); i++) {
                if (i < data.size() - 2) this.addDrop(data.get(i), element.mWeight);
                else this.addDrop(data.get(i), element.mWeight / 8f);
            }
        };
    }

    /**
     * Method used to build the ModDimensionDef object corresponding to the dimension the VM is in.
     *
     * @return the ModDimensionDef object.
     */
    private ModDimensionDef makeModDimDef() {
        return getModContainers().stream()
            .flatMap(
                modContainer -> modContainer.getDimensionList()
                    .stream())
            .filter(
                modDimensionDef -> modDimensionDef.getChunkProviderName()
                    .equals(
                        ((ChunkProviderServer) this.getBaseMetaTileEntity()
                            .getWorld()
                            .getChunkProvider()).currentChunkProvider.getClass()
                                .getName()))
            .findFirst()
            .orElse(null);
    }

    /**
     * Makes a predicate for the GT normal ore veins worldgen
     *
     * @return the predicate
     */
    private Predicate<GT_Worldgen_GT_Ore_Layer> makeOreLayerPredicate() {
        World world = this.getBaseMetaTileEntity()
            .getWorld();
        return switch (world.provider.dimensionId) {
            case -1 -> gt_worldgen -> gt_worldgen.mNether;
            case 0 -> gt_worldgen -> gt_worldgen.mOverworld;
            case 1 -> gt_worldgen -> gt_worldgen.mEnd || gt_worldgen.mEndAsteroid;
            /*
             * explicitely giving different dim numbers so it default to false in the config, keeping compat with the
             * current worldgen config
             */
            case 7 -> gt_worldgen -> gt_worldgen.isGenerationAllowed(world, 0, 7);
            default -> throw new IllegalStateException();
        };
    }

    /**
     * Makes a predicate for the GT normal small ore worldgen
     *
     * @return the predicate
     */
    private Predicate<GT_Worldgen_GT_Ore_SmallPieces> makeSmallOresPredicate() {
        World world = this.getBaseMetaTileEntity()
            .getWorld();
        return switch (world.provider.dimensionId) {
            case -1 -> gt_worldgen -> gt_worldgen.mNether;
            case 0 -> gt_worldgen -> gt_worldgen.mOverworld;
            case 1 -> gt_worldgen -> gt_worldgen.mEnd;
            /*
             * explicitely giving different dim numbers so it default to false in the config, keeping compat with the
             * current worldgen config
             */
            case 7 -> gt_worldgen -> gt_worldgen.isGenerationAllowed(world, 0, 7);
            default -> throw new IllegalStateException();
        };
    }

    /**
     * method used to pick the next key in the dropmap that will be used to generate the ore.
     *
     * @return the chosen key
     */
    private Pair<Integer, Boolean> getOreDamage() {
        float curentWeight = 0.f;
        while (true) {
            float rd = XSTR.XSTR_INSTANCE.nextFloat() * this.totalWeight;
            for (Map.Entry<Pair<Integer, Boolean>, Float> entry : this.dropmap.entrySet()) {
                curentWeight += entry.getValue();
                if (rd < curentWeight) return entry.getKey();
            }
        }
    }

    /**
     * Method used to add an ore to the droplist
     *
     * @param key   a Pair of <int, bool> where the int is the meta of the ore, and the bool is true for BW ores, false
     *              for GT ores
     * @param value the non normalised weight
     */
    private void addDrop(Pair<Integer, Boolean> key, float value) {
        final ItemStack ore = this.getOreItemStack(key);
        if (ConfigHandler.voidMinerBlacklist.contains(
            String.format(
                "%s:%d",
                Objects.requireNonNull(GameRegistry.findUniqueIdentifierFor(ore.getItem())),
                ore.getItemDamage())))
            return;
        if (!this.dropmap.containsKey(key)) this.dropmap.put(key, value);
        else this.dropmap.put(key, this.dropmap.get(key) + value);
    }

    /**
     * Builds the ore item stack from the key specified in the dropmap
     *
     * @param stats the key of the dropmap
     * @return an ItemStack corresponding to the target ore, with a stacksize corresponding to the mutiplier induced by
     *         the gas used
     */
    private ItemStack getOreItemStack(Pair<Integer, Boolean> stats) {
        return new ItemStack(
            stats.getValue() ? WerkstoffLoader.BWOres : GregTech_API.sBlockOres1,
            this.multiplier,
            stats.getKey());
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

    // endregion

    // region Structure

    @Override
    protected Block sCasingBlock() {
        return GregTech_API.sBlockCasings1;
    }

    @Override
    protected Block sCoreBlock() {
        return null;
    }

    @Override
    protected int sCasingIndex() {
        return 10;
    }

    @Override
    protected int sCasingBlockMeta() {
        return 10;
    }

    @Override
    protected int sCoreBlockMeta() {
        return 0;
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection sideDirection,
        ForgeDirection facingDirection, int colorIndex, boolean active, boolean redstoneLevel) {
        if (sideDirection == facingDirection) {
            if (active) return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(this.sCasingIndex()),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_DTPF_ON)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_DTPF_ON)
                    .extFacing()
                    .glow()
                    .build() };
            return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(this.sCasingIndex()),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_DTPF_OFF)
                    .extFacing()
                    .build(),
                TextureFactory.builder()
                    .addIcon(Textures.BlockIcons.OVERLAY_DTPF_OFF)
                    .extFacing()
                    .glow()
                    .build() };
        }
        return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(this.sCasingIndex()) };
    }

    // endregion

    // region Info
    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("虚空采矿机")
            .addInfo("虚空原矿厂的控制器")
            .addInfo("产出指定数量的当前维度矿石.")
            .addSeparator()
            .addInfo(VA_Values.CommonStrings.BluePrintTip)
            .beginStructureBlock(3, 3, 3, false)
            .addInputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .addOutputBus(VA_Values.CommonStrings.BluePrintInfo, 1)
            .toolTipFinisher(VA_Values.CommonStrings.VisAppendMagical);
        return tt;
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
    }
    // endregion
}
