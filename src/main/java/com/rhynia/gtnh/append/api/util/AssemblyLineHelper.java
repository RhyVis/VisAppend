package com.rhynia.gtnh.append.api.util;

import java.util.ArrayList;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.rhynia.gtnh.append.VisAppend;
import com.rhynia.gtnh.append.config.Config;

import gregtech.api.util.GT_AssemblyLineUtils;
import gregtech.api.util.GT_Recipe;

@SuppressWarnings("unused")
public class AssemblyLineHelper {

    protected ArrayList<ItemStack> pRawDataSticks = new ArrayList<>();
    protected ArrayList<GT_Recipe> pRecipes = new ArrayList<>();
    protected GT_Recipe pLastRecipe = null;
    protected ItemStack[] pInputItems = new ItemStack[0];
    protected FluidStack[] pInputFluids = new FluidStack[0];
    protected boolean bEnableDebug = false;

    public AssemblyLineHelper() {}

    @Contract(value = " -> new", pure = true)
    public static @NotNull AssemblyLineHelper builder() {
        return new AssemblyLineHelper();
    }

    public AssemblyLineHelper addRawDataSticks(ItemStack dataSticks) {
        pRawDataSticks.add(dataSticks);
        return this;
    }

    public AssemblyLineHelper setRawDataSticks(ArrayList<ItemStack> dataSticks) {
        pRawDataSticks = dataSticks;
        return this;
    }

    public AssemblyLineHelper setInputItems(ItemStack... inputs) {
        pInputItems = inputs;
        return this;
    }

    public AssemblyLineHelper setInputFluids(FluidStack... inputs) {
        pInputFluids = inputs;
        return this;
    }

    public AssemblyLineHelper setLastRecipe(@Nullable GT_Recipe recipe) {
        pLastRecipe = recipe;
        return this;
    }

    public AssemblyLineHelper enableDebug() {
        bEnableDebug = true;
        return this;
    }

    /**
     * Similar to the filterFindRecipe used in recipeMap-based search
     *
     * @param recipe     the recipe that needs to be checked
     * @param inputItem  real inputs of items in machine
     * @param inputFluid real inputs of fluids in machine
     * @return if the real inputs capable to process the recipe
     */
    private boolean examineRecipe(@NotNull GT_Recipe recipe, ItemStack[] inputItem, FluidStack[] inputFluid) {
        if (recipe.mEnabled && !recipe.mFakeRecipe) {
            return recipe.isRecipeInputEqual(false, inputFluid, inputItem);
        }
        return false;
    }

    /**
     * This method will generate a general GT_Recipe out of the assembly specific recipe
     *
     * @param tLookupResult the data of the scanned data sticks
     * @return a GT_Recipe with oredict alt info
     */
    @NotNull
    private GT_Recipe transformRecipe(GT_AssemblyLineUtils.LookupResult tLookupResult) {
        GT_Recipe.GT_Recipe_AssemblyLine tRecipe = tLookupResult.getRecipe();
        return new GT_Recipe(
            false,
            tRecipe.mInputs,
            new ItemStack[] { tRecipe.mOutput },
            null,
            null,
            tRecipe.mFluidInputs,
            null,
            tRecipe.mDuration,
            tRecipe.mEUt,
            0);
    }

    public Stream<GT_Recipe> generate() {

        if (!pRawDataSticks.isEmpty()) {
            for (ItemStack tDataStick : pRawDataSticks) {
                GT_AssemblyLineUtils.LookupResult tLookupResult = GT_AssemblyLineUtils
                    .findAssemblyLineRecipeFromDataStick(tDataStick, false);
                if (tLookupResult.getType() == GT_AssemblyLineUtils.LookupResultType.INVALID_STICK) {
                    continue;
                }
                GT_Recipe pRecipe = transformRecipe(tLookupResult);
                pRecipes.add(pRecipe);
            }

            if (Config.Debug_Mode && bEnableDebug) {
                VisAppend.LOG.info("RAL found " + pRecipes.size() + " recipes.");
            }

            if (pLastRecipe != null && examineRecipe(pLastRecipe, pInputItems, pInputFluids)) {
                pRecipes.add(pLastRecipe);
            }

            if (!pRecipes.isEmpty()) {
                return pRecipes.stream()
                    .filter(recipe -> examineRecipe(recipe, pInputItems, pInputFluids));
            }
        }

        return Stream.empty();
    }

}
