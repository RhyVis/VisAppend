package com.rhynia.gtnh.append.common.machine.multiblock;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static goodgenerator.main.GG_Config_Loader.*;
import static goodgenerator.util.DescTextLocalization.BLUE_PRINT_INFO;
import static gregtech.api.util.GT_StructureUtility.buildHatchAdder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rhynia.gtnh.append.common.machine.recipe.GTAppendGeneratorRecipe;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

import com.github.bartimaeusnek.bartworks.util.Pair;
import com.github.technus.tectech.thing.metaTileEntity.hatch.GT_MetaTileEntity_Hatch_DynamoMulti;
import com.gtnewhorizon.structurelib.alignment.constructable.IConstructable;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IItemSource;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import goodgenerator.blocks.tileEntity.base.GT_MetaTileEntity_TooltipMultiBlockBase_EM;
import goodgenerator.items.MyMaterial;
import goodgenerator.loader.Loaders;
import goodgenerator.util.CrackRecipeAdder;
import goodgenerator.util.DescTextLocalization;
import goodgenerator.util.MyRecipeAdder;
import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_HatchElement;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Dynamo;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Maintenance;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Output;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_Multiblock_Tooltip_Builder;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
public class GT_TileEntity_CasimirMacroFieldGenerator extends GT_MetaTileEntity_TooltipMultiBlockBase_EM
    implements IConstructable, ISurvivalConstructable {
    //Elements
    protected IStructureDefinition<GT_TileEntity_CasimirMacroFieldGenerator> multiDefinition = null;
    protected long leftEnergy = 0;
    protected long trueOutput = 0;
    protected int trueEff = 0;
    protected FluidStack lockedFluid = null;
    protected int times = 1;
    protected int basicOutput;
    private static final List<Pair<FluidStack, Integer>> excitedLiquid;
    private static final List<Pair<FluidStack, Integer>> coolant;

    //Extra Liquid
    static {
        excitedLiquid = Arrays.asList(
            new Pair<>(MaterialsUEVplus.Space.getMolten(20L), ExcitedLiquidCoe[0]),
            new Pair<>(MyMaterial.atomicSeparationCatalyst.getMolten(20), ExcitedLiquidCoe[1]),
            new Pair<>(Materials.Naquadah.getMolten(20L), ExcitedLiquidCoe[2]),
            new Pair<>(Materials.Uranium235.getMolten(180L), ExcitedLiquidCoe[3]),
            new Pair<>(Materials.Caesium.getMolten(180L), ExcitedLiquidCoe[4]));
        coolant = Arrays.asList(
            new Pair<>(MaterialsUEVplus.Time.getMolten(20L), CoolantEfficiency[0]),
            new Pair<>(FluidRegistry.getFluidStack("cryotheum", 1000), CoolantEfficiency[1]),
            new Pair<>(Materials.SuperCoolant.getFluid(1000L), CoolantEfficiency[2]),
            new Pair<>(FluidRegistry.getFluidStack("ic2coolant", 1000), CoolantEfficiency[3]));
    }
    @Override
    public void construct(ItemStack itemStack, boolean hintsOnly) {
        structureBuild_EM(mName, 3, 7, 0, itemStack, hintsOnly);
    }

    @Override
    public String[] getStructureDescription(ItemStack itemStack) {
        return new String[] {"Ser"};
    }

    public final boolean addToGeneratorList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        if (aTileEntity == null) {
            return false;
        } else {
            IMetaTileEntity aMetaTileEntity = aTileEntity.getMetaTileEntity();
            if (aMetaTileEntity == null) {
                return false;
            } else {
                if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch) {
                    ((GT_MetaTileEntity_Hatch) aMetaTileEntity).updateTexture(aBaseCasingIndex);
                }
                if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Input) {
                    return this.mInputHatches.add((GT_MetaTileEntity_Hatch_Input) aMetaTileEntity);
                } else if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Output) {
                    return this.mOutputHatches.add((GT_MetaTileEntity_Hatch_Output) aMetaTileEntity);
                } else if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Dynamo) {
                    return this.mDynamoHatches.add((GT_MetaTileEntity_Hatch_Dynamo) aMetaTileEntity);
                } else if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Maintenance) {
                    return this.mMaintenanceHatches.add((GT_MetaTileEntity_Hatch_Maintenance) aMetaTileEntity);
                } else if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_DynamoMulti) {
                    return this.eDynamoMulti.add((GT_MetaTileEntity_Hatch_DynamoMulti) aMetaTileEntity);
                } else {
                    return false;
                }
            }
        }
    }
    //Structure
    @Override
    public IStructureDefinition<GT_TileEntity_CasimirMacroFieldGenerator> getStructure_EM() {
        if (multiDefinition == null) {
            multiDefinition = StructureDefinition.<GT_TileEntity_CasimirMacroFieldGenerator>builder().addShape(
                    mName,
                    transpose(
                        new String[][] {
                            { "AAAAAAA", "AAAAAAA", "AAAAAAA", "AAAAAAA", "AAAAAAA", "AAAAAAA", "AAAAAAA" },
                            { "N     N", "       ", "  CCC  ", "  CPC  ", "  CCC  ", "       ", "N     N" },
                            { "N     N", "       ", "  CCC  ", "  CPC  ", "  CCC  ", "       ", "N     N" },
                            { "N     N", "       ", "  CCC  ", "  CPC  ", "  CCC  ", "       ", "N     N" },
                            { "N     N", "       ", "  CCC  ", "  CPC  ", "  CCC  ", "       ", "N     N" },
                            { "AAAAAAA", "A     A", "A CCC A", "A CPC A", "A CCC A", "A     A", "AAAAAAA" },
                            { "ANNNNNA", "N     N", "N CCC N", "N CPC N", "N CCC N", "N     N", "ANNNNNA" },
                            { "XXX~XXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX" }, }))
                .addElement(
                    'X',
                    ofChain(
                        buildHatchAdder(GT_TileEntity_CasimirMacroFieldGenerator.class).atLeast(
                            HatchElement.DynamoMulti.or(GT_HatchElement.Dynamo),
                            GT_HatchElement.InputHatch,
                            GT_HatchElement.OutputHatch,
                            GT_HatchElement.Maintenance).casingIndex(44).dot(1).build(),
                        ofBlock(GregTech_API.sBlockCasings3, 12)))
                .addElement('A', ofBlock(GregTech_API.sBlockCasings3, 12))
                .addElement('N', ofBlock(Loaders.radiationProtectionSteelFrame, 0))
                .addElement('C', ofBlock(Loaders.MAR_Casing, 0))
                .addElement('P', ofBlock(GregTech_API.sBlockCasings2, 15)).build();
        }
        return multiDefinition;
    }
    public GT_TileEntity_CasimirMacroFieldGenerator(String name) {
        super(name);
    }

    public GT_TileEntity_CasimirMacroFieldGenerator(int id, String name, String nameRegional) {
        super(id, name, nameRegional);
    }

    @Override
    public boolean isCorrectMachinePart(ItemStack aStack) {
        return true;
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        this.times = aNBT.getInteger("mTimes");
        this.leftEnergy = aNBT.getLong("mLeftEnergy");
        this.basicOutput = aNBT.getInteger("mbasicOutput");
        if (FluidRegistry.getFluid(aNBT.getString("mLockedFluidName")) != null) this.lockedFluid = new FluidStack(
            FluidRegistry.getFluid(aNBT.getString("mLockedFluidName")),
            aNBT.getInteger("mLockedFluidAmount"));
        else this.lockedFluid = null;
        super.loadNBTData(aNBT);
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setInteger("mTimes", this.times);
        aNBT.setLong("mLeftEnergy", this.leftEnergy);
        aNBT.setInteger("mbasicOutput", this.basicOutput);
        if (lockedFluid != null) {
            aNBT.setString("mLockedFluidName", this.lockedFluid.getFluid().getName());
            aNBT.setInteger("mLockedFluidAmount", this.lockedFluid.amount);
        }
        super.saveNBTData(aNBT);
    }

    @Override
    public @NotNull CheckRecipeResult checkProcessing_EM() {

        ArrayList<FluidStack> tFluids = getStoredFluids();

        for (int i = 0; i < tFluids.size() - 1; i++) {
            for (int j = i + 1; j < tFluids.size(); j++) {
                if (GT_Utility.areFluidsEqual(tFluids.get(i), tFluids.get(j))) {
                    if ((tFluids.get(i)).amount >= (tFluids.get(j)).amount) {
                        tFluids.remove(j--);
                    } else {
                        tFluids.remove(i--);
                        break;
                    }
                }
            }
        }

        GT_Recipe tRecipe = GTAppendGeneratorRecipe.instance.CMFGFuels
            .findRecipe(this.getBaseMetaTileEntity(), true, 1 << 30, tFluids.toArray(new FluidStack[0]));
        if (tRecipe != null) {
            Pair<FluidStack, Integer> excitedInfo = getExcited(tFluids.toArray(new FluidStack[0]), false);
            int pall = excitedInfo == null ? 1 : excitedInfo.getValue();
            if (consumeFuel(
                CrackRecipeAdder.copyFluidWithAmount(tRecipe.mFluidInputs[0], pall),
                tFluids.toArray(new FluidStack[0]))) {
                mOutputFluids = new FluidStack[] {
                    CrackRecipeAdder.copyFluidWithAmount(tRecipe.mFluidOutputs[0], pall) };
                basicOutput = tRecipe.mSpecialValue;
                times = pall;
                lockedFluid = excitedInfo == null ? null : excitedInfo.getKey();
                mMaxProgresstime = tRecipe.mDuration;
                return CheckRecipeResultRegistry.GENERATING;
            }
        }

        return CheckRecipeResultRegistry.NO_FUEL_FOUND;
    }

    @Override
    public boolean onRunningTick(ItemStack stack) {
        if (this.getBaseMetaTileEntity().isServerSide()) {
            if (mMaxProgresstime != 0 && mProgresstime % 20 == 0) {
                FluidStack[] input = getStoredFluids().toArray(new FluidStack[0]);
                int eff = 100, time = 1;
                if (LiquidAirConsumptionPerSecond != 0
                    && !consumeFuel(Materials.LiquidAir.getFluid(LiquidAirConsumptionPerSecond), input)) {
                    this.mEUt = 0;
                    this.trueEff = 0;
                    this.trueOutput = 0;
                    return true;
                }
                if (getCoolant(input, true) != null) eff = getCoolant(input, false).getValue();
                if (consumeFuel(lockedFluid, input)) time = times;
                this.mEUt = basicOutput * eff * time / 100;
                this.trueEff = eff;
                this.trueOutput = (long) basicOutput * (long) eff * (long) time / 100;
            }
            addAutoEnergy(trueOutput);
        }
        return true;
    }

    @Override
    public String[] getInfoData() {
        String[] info = super.getInfoData();
        info[4] = "产生能源: " + EnumChatFormatting.RED
            + GT_Utility.formatNumbers(Math.abs(this.trueOutput))
            + EnumChatFormatting.RESET
            + " EU/t";
        info[6] = "问题: " + EnumChatFormatting.RED
            + (this.getIdealStatus() - this.getRepairStatus())
            + EnumChatFormatting.RESET
            + " 效率: "
            + EnumChatFormatting.YELLOW
            + trueEff
            + EnumChatFormatting.RESET
            + " %";
        return info;
    }

    public boolean consumeFuel(FluidStack target, FluidStack[] input) {
        if (target == null) return false;
        for (FluidStack inFluid : input) {
            if (inFluid != null && inFluid.isFluidEqual(target) && inFluid.amount >= target.amount) {
                inFluid.amount -= target.amount;
                return true;
            }
        }
        return false;
    }

    public Pair<FluidStack, Integer> getExcited(FluidStack[] input, boolean isConsume) {
        for (Pair<FluidStack, Integer> fluidPair : excitedLiquid) {
            FluidStack tFluid = fluidPair.getKey();
            for (FluidStack inFluid : input) {
                if (inFluid != null && inFluid.isFluidEqual(tFluid) && inFluid.amount >= tFluid.amount) {
                    if (isConsume) inFluid.amount -= tFluid.amount;
                    return fluidPair;
                }
            }
        }
        return null;
    }

    public Pair<FluidStack, Integer> getCoolant(FluidStack[] input, boolean isConsume) {
        for (Pair<FluidStack, Integer> fluidPair : coolant) {
            FluidStack tFluid = fluidPair.getKey();
            for (FluidStack inFluid : input) {
                if (inFluid != null && inFluid.isFluidEqual(tFluid) && inFluid.amount >= tFluid.amount) {
                    if (isConsume) inFluid.amount -= tFluid.amount;
                    return fluidPair;
                }
            }
        }
        return null;
    }

    public void addAutoEnergy(long outputPower) {
        if (this.eDynamoMulti.size() > 0) for (GT_MetaTileEntity_Hatch tHatch : this.eDynamoMulti) {
            long voltage = tHatch.maxEUOutput();
            long power = voltage * tHatch.maxAmperesOut();
            long outputAmperes;
            if (outputPower > power) doExplosion(8 * GT_Utility.getTier(power));
            if (outputPower >= voltage) {
                leftEnergy += outputPower;
                outputAmperes = leftEnergy / voltage;
                leftEnergy -= outputAmperes * voltage;
                addEnergyOutput_EM(voltage, outputAmperes);
            } else {
                addEnergyOutput_EM(outputPower, 1);
            }
        }
        if (this.mDynamoHatches.size() > 0) for (GT_MetaTileEntity_Hatch tHatch : this.mDynamoHatches) {
            long voltage = tHatch.maxEUOutput();
            long power = voltage * tHatch.maxAmperesOut();
            long outputAmperes;
            if (outputPower > power) doExplosion(8 * GT_Utility.getTier(power));
            if (outputPower >= voltage) {
                leftEnergy += outputPower;
                outputAmperes = leftEnergy / voltage;
                leftEnergy -= outputAmperes * voltage;
                addEnergyOutput_EM(voltage, outputAmperes);
            } else {
                addEnergyOutput_EM(outputPower, 1);
            }
        }
    }

    @Override
    public boolean checkMachine_EM(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        return structureCheck_EM(mName, 3, 7, 0) && mMaintenanceHatches.size() == 1
            && mDynamoHatches.size() + eDynamoMulti.size() == 1;
    }

    @Override
    public int getMaxEfficiency(ItemStack aStack) {
        return 0;
    }

    @Override
    public int getPollutionPerTick(ItemStack aStack) {
        return 0;
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
        return new GT_TileEntity_CasimirMacroFieldGenerator(this.mName);
    }

    @Override
    protected GT_Multiblock_Tooltip_Builder createTooltip() {
        final GT_Multiblock_Tooltip_Builder tt = new GT_Multiblock_Tooltip_Builder();
        tt.addMachineType("零点能发生器").addInfo("卡西米尔宏场发生器的控制器")
            .addInfo("你知道虚空中有多少能量吗?")
            .addInfo("通过高能星辉物质从虚空中释放能量.")
            .addInfo(
                String.format(
                    "消耗 %d L/s 液态空气以保持运行，否则" + EnumChatFormatting.YELLOW
                        + "它不会产生任何能量"
                        + EnumChatFormatting.GRAY
                        + ".",
                    LiquidAirConsumptionPerSecond))
            .addInfo("Input liquid nuclear fuel or liquid naquadah fuel.")
            .addInfo(
                "当宏场发生器中的燃料超过" + EnumChatFormatting.RED
                    + "一种"
                    + EnumChatFormatting.GRAY
                    + "时，宏场发生器将立即爆炸!")
            .addInfo("可以以 1000L/s 的消耗使用以下流体来提高效率:")
            .addInfo(
                String.format(
                    "IC2 Coolant %d%%, Super Coolant %d%%, Cryotheum %d%%, Tachyon Rich Temporal Fluid %d%%",
                    CoolantEfficiency[3],
                    CoolantEfficiency[2],
                    CoolantEfficiency[1],
                    CoolantEfficiency[0]))
            .addInfo("Can consume excited liquid to increase the output power and fuel usage:")
            .addInfo(String.format("Molten Caesium | %dx power | 180 L/s ", ExcitedLiquidCoe[4]))
            .addInfo(String.format("Molten Uranium-235 | %dx power | 180 L/s", ExcitedLiquidCoe[3]))
            .addInfo(String.format("Molten Naquadah | %dx power | 20 L/s", ExcitedLiquidCoe[2]))
            .addInfo(String.format("Molten Atomic Separation Catalyst | %dx power | 20 L/s", ExcitedLiquidCoe[1]))
            .addInfo(String.format("Spatially Enlarged Fluid | %dx power | 20 L/s", ExcitedLiquidCoe[0]))
            .addInfo("结构太复杂了!")
            .addInfo("请参考§9Structure§1Lib§7全息投影，构建主体结构")
            .addSeparator()
            .beginStructureBlock(7, 8, 7, true)
            .addController("正面底部")
            .addDynamoHatch("Any bottom layer casing, only accept ONE!")
            .addInputHatch("Any bottom layer casing")
            .addOutputHatch("Any bottom layer casing")
            .addMaintenanceHatch("Any bottom layer casing")
            .toolTipFinisher("GT: Append");
        return tt;
    }

    @Override
    @SuppressWarnings("ALL")
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, ForgeDirection side, ForgeDirection facing,
                                 int colorIndex, boolean aActive, boolean aRedstone) {
        if (side == facing) {
            if (aActive) return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(44),
                new GT_RenderedTexture(Textures.BlockIcons.NAQUADAH_REACTOR_SOLID_FRONT_ACTIVE),
                TextureFactory.builder().addIcon(Textures.BlockIcons.NAQUADAH_REACTOR_SOLID_FRONT_ACTIVE_GLOW)
                    .glow().build() };
            return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(44),
                new GT_RenderedTexture(Textures.BlockIcons.NAQUADAH_REACTOR_SOLID_FRONT) };
        }
        return new ITexture[] { Textures.BlockIcons.getCasingTextureForId(44) };
    }

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, IItemSource source, EntityPlayerMP actor) {
        if (mMachine) return -1;
        return survivialBuildPiece(mName, stackSize, 3, 7, 0, elementBudget, source, actor, false, true);
    }
}
