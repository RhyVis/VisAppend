package com.rhynia.gtnh.append.common.machine.recipe;

import static com.rhynia.gtnh.append.util.UtilLocal.NameAstraForge;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

import com.gtnewhorizons.modularui.api.forge.IItemHandlerModifiable;
import com.gtnewhorizons.modularui.api.math.Pos2d;
import com.gtnewhorizons.modularui.api.screen.ModularWindow;

import gregtech.api.util.GT_Recipe;
import gregtech.common.gui.modularui.UIHelper;

public class GTAppendRecipe {

    public static final GTAppendRecipe instance = new GTAppendRecipe();

    public static class GTAppendRecipeMap extends GT_Recipe.GT_Recipe_Map {

        /**
         * Initialises a new type of Recipe Handler.
         *
         * @param aRecipeList                a List you specify as Recipe List. Usually just an ArrayList with a
         *                                   pre-initialised Size.
         * @param aUnlocalizedName           the unlocalised Name of this Recipe Handler, used mainly for NEI.
         * @param aLocalName                 @deprecated the displayed Name of the NEI Recipe GUI title,
         *                                   if null then use the aUnlocalizedName;
         *                                   always is null, by using aUnlocalizedName with i18n.
         * @param aNEIName
         * @param aNEIGUIPath                the displayed GUI Texture, usually just a Machine GUI. Auto-Attaches ".png"
         *                                   if forgotten.
         * @param aUsualInputCount           the usual amount of Input Slots this Recipe Class has.
         * @param aUsualOutputCount          the usual amount of Output Slots this Recipe Class has.
         * @param aUsualFluidInputCount      the usual amount of Fluid Input Slots this Recipe Class has.
         * @param aUsualFluidOutputCount     the usual amount of Fluid Output Slots this Recipe Class has.
         * @param aMinimalInputItems
         * @param aMinimalInputFluids
         * @param aAmperage
         * @param aNEISpecialValuePre        the String in front of the Special Value in NEI.
         * @param aNEISpecialValueMultiplier the Value the Special Value is getting Multiplied with before displaying
         * @param aNEISpecialValuePost       the String after the Special Value. Usually for a Unit or something.
         * @param aShowVoltageAmperageInNEI
         * @param aNEIAllowed                if NEI is allowed to display this Recipe Handler in general.
         */
        public GTAppendRecipeMap(Collection<GT_Recipe> aRecipeList, String aUnlocalizedName, String aLocalName,
            String aNEIName, String aNEIGUIPath, int aUsualInputCount, int aUsualOutputCount, int aUsualFluidInputCount,
            int aUsualFluidOutputCount, boolean disableOptimize, int aMinimalInputItems, int aMinimalInputFluids,
            int aAmperage, String aNEISpecialValuePre, int aNEISpecialValueMultiplier, String aNEISpecialValuePost,
            boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed) {
            super(
                aRecipeList,
                aUnlocalizedName,
                aLocalName,
                aNEIName,
                aNEIGUIPath,
                aUsualInputCount,
                aUsualOutputCount,
                aMinimalInputItems,
                aMinimalInputFluids,
                aAmperage,
                aNEISpecialValuePre,
                aNEISpecialValueMultiplier,
                aNEISpecialValuePost,
                aShowVoltageAmperageInNEI,
                aNEIAllowed);

            useModularUI(true);
            // setProgressBarPos(78, getItemRowCount() * 18);
            setLogoPos(79, (getItemRowCount() + getFluidRowCount()) * 18);
            setUsualFluidInputCount(aUsualFluidInputCount);
            setUsualFluidOutputCount(aUsualFluidOutputCount);
            setDisableOptimize(disableOptimize);

        }

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
            // Delay setter so that calls to #setUsualFluidInputCount and #setUsualFluidOutputCount are considered
            setNEIBackgroundSize(172, 10 + (getItemRowCount() + getFluidRowCount()) * 18);
            // setNEIBackgroundSize(172, 82 + (Math.max(getItemRowCount() + getFluidRowCount() - 4, 0)) * 18);
            return super.createNEITemplate(
                itemInputsInventory,
                itemOutputsInventory,
                specialSlotInventory,
                fluidInputsInventory,
                fluidOutputsInventory,
                progressSupplier,
                windowOffset);
        }
    }

    public final GTAppendRecipeMap AstraForgeRecipes = new GTAppendRecipeMap(
        new HashSet<>(),
        "append.recipe.AstraForgeRecipes",
        NameAstraForge,
        null,
        "gregtech:textures/gui/basicmachines/LCRNEI",
        16,
        16,
        16,
        16,
        true,
        0,
        0,
        1,
        "",
        1,
        "",
        false,
        true);
}
