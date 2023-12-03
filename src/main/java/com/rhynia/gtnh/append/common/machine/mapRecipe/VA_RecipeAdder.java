package com.rhynia.gtnh.append.common.machine.mapRecipe;

import static com.rhynia.gtnh.append.util.UtilValues.VA_LOGO_32;
import static gregtech.api.gui.modularui.GT_UITextures.PROGRESSBAR_ARROW_MULTIPLE;
import static gregtech.api.gui.modularui.GT_UITextures.PROGRESSBAR_MIXER;

import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

import com.gtnewhorizons.modularui.api.forge.IItemHandlerModifiable;
import com.gtnewhorizons.modularui.api.math.Pos2d;
import com.gtnewhorizons.modularui.api.screen.ModularWindow;

import gregtech.common.GT_RecipeAdder;
import gregtech.common.gui.modularui.UIHelper;

public class VA_RecipeAdder extends GT_RecipeAdder {

    public static VA_RecipeAdder instance = new VA_RecipeAdder();
    public VA_RecipeMap sAstraForgeRecipes = (VA_RecipeMap) new VA_RecipeMap(
        new HashSet<>(),
        "append.recipe.astraForge",
        "星辉聚能转化",
        null,
        "gregtech:textures/gui/basicmachines/LCRNEI",
        16,
        16,
        0,
        0,
        1,
        "",
        1,
        "",
        false,
        true) {

        private static final int xDirMaxCount = 4;
        private static final int yOrigin = 8;

        private int getItemRowCount() {
            return (Math.max(mUsualInputCount, mUsualOutputCount) - 1) / xDirMaxCount + 1;
        }

        private int getFluidRowCount() {
            return (Math.max(getUsualFluidInputCount(), getUsualFluidOutputCount()) - 1) / xDirMaxCount + 1;
        }

        @Override
        public List<Pos2d> getItemInputPositions(int itemInputCount) {
            return UIHelper.getGridPositions(itemInputCount, 6, yOrigin, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getItemOutputPositions(int itemOutputCount) {
            return UIHelper.getGridPositions(itemOutputCount, 98, yOrigin, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getFluidInputPositions(int fluidInputCount) {
            return UIHelper.getGridPositions(fluidInputCount, 6, yOrigin + getItemRowCount() * 18, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getFluidOutputPositions(int fluidOutputCount) {
            return UIHelper.getGridPositions(fluidOutputCount, 98, yOrigin + getItemRowCount() * 18, xDirMaxCount);
        }

        @Override
        public ModularWindow.Builder createNEITemplate(IItemHandlerModifiable itemInputsInventory,
            IItemHandlerModifiable itemOutputsInventory, IItemHandlerModifiable specialSlotInventory,
            IItemHandlerModifiable fluidInputsInventory, IItemHandlerModifiable fluidOutputsInventory,
            Supplier<Float> progressSupplier, Pos2d windowOffset) {
            setNEIBackgroundSize(172, 10 + (getItemRowCount() + getFluidRowCount()) * 18);
            return super.createNEITemplate(
                itemInputsInventory,
                itemOutputsInventory,
                specialSlotInventory,
                fluidInputsInventory,
                fluidOutputsInventory,
                progressSupplier,
                windowOffset);
        }
    }.useModularUI(true)
        .setLogo(VA_LOGO_32)
        .setLogoSize(17, 17)
        .setLogoPos(79, 82)
        .setProgressBar(PROGRESSBAR_ARROW_MULTIPLE)
        .setProgressBarPos(80, 30)
        .setProgressBarSize(15, 15)
        .setUsualFluidInputCount(8)
        .setUsualFluidOutputCount(8)
        .setDisableOptimize(true);

    public VA_RecipeMap sUltimateHeaterRecipes = (VA_RecipeMap) new VA_RecipeMap(
        new HashSet<>(),
        "append.recipe.ultimateHeater",
        "热核控制过程",
        null,
        "gregtech:textures/gui/basicmachines/LCRNEI",
        4,
        0,
        0,
        0,
        0,
        "",
        1,
        "",
        false,
        true) {

        private static final int xDirMaxCount = 2;
        private static final int yOrigin = 17;
        private static final int yOriginOutput = -9;

        private int getItemRowCount() {
            return (Math.max(mUsualInputCount, mUsualOutputCount) - 1) / xDirMaxCount + 1;
        }

        private int getFluidRowCount() {
            return (Math.max(getUsualFluidInputCount(), getUsualFluidOutputCount()) - 1) / xDirMaxCount + 1;
        }

        @Override
        public List<Pos2d> getItemInputPositions(int itemInputCount) {
            return UIHelper.getGridPositions(itemInputCount, 24, yOrigin, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getItemOutputPositions(int itemOutputCount) {
            return UIHelper.getGridPositions(itemOutputCount, 116, yOriginOutput, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getFluidInputPositions(int fluidInputCount) {
            return UIHelper.getGridPositions(fluidInputCount, 24, yOrigin + getItemRowCount() * 18, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getFluidOutputPositions(int fluidOutputCount) {
            return UIHelper
                .getGridPositions(fluidOutputCount, 116, yOriginOutput + getItemRowCount() * 18, xDirMaxCount);
        }

        @Override
        public ModularWindow.Builder createNEITemplate(IItemHandlerModifiable itemInputsInventory,
            IItemHandlerModifiable itemOutputsInventory, IItemHandlerModifiable specialSlotInventory,
            IItemHandlerModifiable fluidInputsInventory, IItemHandlerModifiable fluidOutputsInventory,
            Supplier<Float> progressSupplier, Pos2d windowOffset) {
            setNEIBackgroundSize(172, 10 + (getItemRowCount() + getFluidRowCount()) * 18);
            return super.createNEITemplate(
                itemInputsInventory,
                itemOutputsInventory,
                specialSlotInventory,
                fluidInputsInventory,
                fluidOutputsInventory,
                progressSupplier,
                windowOffset);
        }
    }.useModularUI(true)
        .setLogo(VA_LOGO_32)
        .setLogoSize(17, 17)
        .setLogoPos(79, 52)
        .setProgressBar(PROGRESSBAR_MIXER)
        .setProgressBarPos(79, 27)
        .setProgressBarSize(17, 17)
        .setUsualFluidInputCount(2)
        .setUsualFluidOutputCount(4)
        .setDisableOptimize(true);

    public VA_RecipeMap sAssemblyMatrixRecipes = (VA_RecipeMap) new VA_RecipeMap(
        new HashSet<>(),
        "append.recipe.assemblyMatrix",
        "集成组装",
        null,
        "gregtech:textures/gui/basicmachines/LCRNEI",
        12,
        6,
        0,
        0,
        1,
        "",
        1,
        "",
        false,
        true) {

        private static final int xDirMaxCount = 6;
        private static final int xDirOutputMaxCount = 2;
        private static final int yOrigin = 8;

        private int getItemRowCount() {
            return (Math.max(mUsualInputCount, mUsualOutputCount) - 1) / xDirMaxCount + 1;
        }

        private int getFluidRowCount() {
            return (Math.max(getUsualFluidInputCount(), getUsualFluidOutputCount()) - 1) / xDirMaxCount + 1;
        }

        @Override
        public List<Pos2d> getItemInputPositions(int itemInputCount) {
            return UIHelper.getGridPositions(itemInputCount, 6, yOrigin, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getItemOutputPositions(int itemOutputCount) {
            return UIHelper.getGridPositions(itemOutputCount, 134, yOrigin, xDirOutputMaxCount);
        }

        @Override
        public List<Pos2d> getFluidInputPositions(int fluidInputCount) {
            return UIHelper.getGridPositions(fluidInputCount, 6, yOrigin + getItemRowCount() * 18, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getFluidOutputPositions(int fluidOutputCount) {
            return UIHelper.getGridPositions(fluidOutputCount, 134, yOrigin + 9 + getItemRowCount() * 18, xDirMaxCount);
        }

        @Override
        public ModularWindow.Builder createNEITemplate(IItemHandlerModifiable itemInputsInventory,
            IItemHandlerModifiable itemOutputsInventory, IItemHandlerModifiable specialSlotInventory,
            IItemHandlerModifiable fluidInputsInventory, IItemHandlerModifiable fluidOutputsInventory,
            Supplier<Float> progressSupplier, Pos2d windowOffset) {
            setNEIBackgroundSize(172, 10 + (getItemRowCount() + getFluidRowCount()) * 18);
            return super.createNEITemplate(
                itemInputsInventory,
                itemOutputsInventory,
                specialSlotInventory,
                fluidInputsInventory,
                fluidOutputsInventory,
                progressSupplier,
                windowOffset);
        }
    }.useModularUI(true)
        .setLogo(VA_LOGO_32)
        .setLogoSize(17, 17)
        .setLogoPos(116, 46)
        .setProgressBar(PROGRESSBAR_ARROW_MULTIPLE)
        .setProgressBarPos(116, 23)
        .setProgressBarSize(17, 17)
        .setUsualFluidInputCount(6)
        .setUsualFluidOutputCount(0)
        .setDisableOptimize(true);

    public VA_RecipeMap sMicroAssemblyRecipes = (VA_RecipeMap) new VA_RecipeMap(
        new HashSet<>(),
        "append.recipe.microAssembly",
        "微加工组装",
        null,
        "gregtech:textures/gui/basicmachines/LCRNEI",
        8,
        16,
        0,
        0,
        1,
        "",
        1,
        "",
        false,
        true) {

        private static final int xDirMaxCount = 4;
        private static final int yOrigin = 8;

        private int getItemRowCount() {
            return (Math.max(mUsualInputCount, mUsualOutputCount) - 1) / xDirMaxCount + 1;
        }

        private int getFluidRowCount() {
            return (Math.max(getUsualFluidInputCount(), getUsualFluidOutputCount()) - 1) / xDirMaxCount + 1;
        }

        @Override
        public List<Pos2d> getItemInputPositions(int itemInputCount) {
            return UIHelper.getGridPositions(itemInputCount, 6, yOrigin, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getItemOutputPositions(int itemOutputCount) {
            return UIHelper.getGridPositions(itemOutputCount, 98, yOrigin, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getFluidInputPositions(int fluidInputCount) {
            return UIHelper.getGridPositions(fluidInputCount, 6, yOrigin + 36, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getFluidOutputPositions(int fluidOutputCount) {
            return UIHelper.getGridPositions(fluidOutputCount, 98, yOrigin + getItemRowCount() * 18, xDirMaxCount);
        }

        @Override
        public ModularWindow.Builder createNEITemplate(IItemHandlerModifiable itemInputsInventory,
                                                       IItemHandlerModifiable itemOutputsInventory, IItemHandlerModifiable specialSlotInventory,
                                                       IItemHandlerModifiable fluidInputsInventory, IItemHandlerModifiable fluidOutputsInventory,
                                                       Supplier<Float> progressSupplier, Pos2d windowOffset) {
            setNEIBackgroundSize(172, 10 + (getItemRowCount() + getFluidRowCount() - 2) * 18);
            return super.createNEITemplate(
                itemInputsInventory,
                itemOutputsInventory,
                specialSlotInventory,
                fluidInputsInventory,
                fluidOutputsInventory,
                progressSupplier,
                windowOffset);
        }
    }.useModularUI(true)
        .setLogo(VA_LOGO_32)
        .setLogoSize(17, 17)
        .setLogoPos(79, 46)
        .setProgressBar(PROGRESSBAR_ARROW_MULTIPLE)
        .setProgressBarPos(79, 27)
        .setProgressBarSize(17, 17)
        .setUsualFluidInputCount(8)
        .setUsualFluidOutputCount(0)
        .setDisableOptimize(true);

    public VA_RecipeMap sSuperconductingBinderRecipes = (VA_RecipeMap) new VA_RecipeMap(
        new HashSet<>(),
        "append.recipe.superconductingBinder",
        "超导成型",
        null,
        "gregtech:textures/gui/basicmachines/LCRNEI",
        4,
        12,
        0,
        0,
        1,
        "",
        1,
        "",
        false,
        true) {

        private static final int xDirMaxCount = 4;
        private static final int yOrigin = 8;

        private int getItemRowCount() {
            return (Math.max(mUsualInputCount, mUsualOutputCount) - 1) / xDirMaxCount + 1;
        }

        private int getFluidRowCount() {
            return (Math.max(getUsualFluidInputCount(), getUsualFluidOutputCount()) - 1) / xDirMaxCount + 1;
        }

        @Override
        public List<Pos2d> getItemInputPositions(int itemInputCount) {
            return UIHelper.getGridPositions(itemInputCount, 6, yOrigin, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getItemOutputPositions(int itemOutputCount) {
            return UIHelper.getGridPositions(itemOutputCount, 98, yOrigin, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getFluidInputPositions(int fluidInputCount) {
            return UIHelper.getGridPositions(fluidInputCount, 6, yOrigin + 18, xDirMaxCount);
        }

        @Override
        public List<Pos2d> getFluidOutputPositions(int fluidOutputCount) {
            return UIHelper.getGridPositions(fluidOutputCount, 98, yOrigin + getItemRowCount() * 18, xDirMaxCount);
        }

        @Override
        public ModularWindow.Builder createNEITemplate(IItemHandlerModifiable itemInputsInventory,
            IItemHandlerModifiable itemOutputsInventory, IItemHandlerModifiable specialSlotInventory,
            IItemHandlerModifiable fluidInputsInventory, IItemHandlerModifiable fluidOutputsInventory,
            Supplier<Float> progressSupplier, Pos2d windowOffset) {
            setNEIBackgroundSize(172, 10 + (getItemRowCount() + getFluidRowCount() - 2) * 18);
            return super.createNEITemplate(
                itemInputsInventory,
                itemOutputsInventory,
                specialSlotInventory,
                fluidInputsInventory,
                fluidOutputsInventory,
                progressSupplier,
                windowOffset);
        }
    }.useModularUI(true)
        .setLogo(VA_LOGO_32)
        .setLogoSize(17, 17)
        .setLogoPos(79, 46)
        .setProgressBar(PROGRESSBAR_ARROW_MULTIPLE)
        .setProgressBarPos(79, 27)
        .setProgressBarSize(17, 17)
        .setUsualFluidInputCount(8)
        .setUsualFluidOutputCount(0)
        .setDisableOptimize(true);

}
