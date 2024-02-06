package com.rhynia.gtnh.append.api.util;

import java.util.ArrayList;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.rhynia.gtnh.append.VisAppend;
import com.rhynia.gtnh.append.api.recipe.backend.VA_RecipeMapBackend;
import com.rhynia.gtnh.append.common.VAItemList;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.objects.ItemData;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMapBuilder;
import gregtech.api.util.GT_AssemblyLineUtils;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;

@SuppressWarnings("unused")
public class AssemblyLineHelper {

    protected ArrayList<ItemStack> pRawDataSticks = new ArrayList<>();
    protected ArrayList<GT_Recipe> pRecipes = new ArrayList<>();
    protected GT_Recipe pLastRecipe = null;
    protected ItemStack[] pInputItems = new ItemStack[0];
    protected FluidStack[] pInputFluids = new FluidStack[0];
    protected boolean bEnableCompatibilityRecipeMap = false;
    protected boolean bEnableAltCheck = false;
    protected boolean bEnableDebug = false;

    /** RAL Compatibility Map (Allow 64+ Stack) */
    public static final RecipeMap<VA_RecipeMapBackend> compatibilityRALMap = RecipeMapBuilder
        .of("va.recipe.compatibilityRALMap", VA_RecipeMapBackend::new)
        .maxIO(16, 1, 4, 0)
        .minInputs(1, 0)
        .disableOptimize()
        .disableRegisterNEI()
        .build();

    @Contract(" -> new")
    @NotNull
    private GT_Recipe getDummy() {
        VisAppend.LOG.error("A recipe build got error! Return dummy recipe.");
        return new GT_Recipe(
            false,
            new ItemStack[] { VAItemList.Test.get(1) },
            new ItemStack[] { VAItemList.Test.get(64) },
            null,
            null,
            null,
            null,
            128,
            128,
            0);
    }

    public AssemblyLineHelper() {}

    /**
     * Start calling builder.
     */
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

    /**
     * Inject compatibilityRALMap into recipe findings, no data sticks req, inputs req.
     */
    public AssemblyLineHelper enableCompatibilityRecipeMap(boolean b) {
        bEnableCompatibilityRecipeMap = b;
        return this;
    }

    /**
     * Experimental: Attempt to fix circuit input problem.
     */
    public AssemblyLineHelper enableAltCheck() {
        bEnableAltCheck = true;
        return this;
    }

    /**
     * Print debug info during running.
     */
    public AssemblyLineHelper enableDebug() {
        bEnableDebug = true;
        return this;
    }

    /**
     * I don't know why it is here...
     */
    public AssemblyLineHelper clear() {
        pRawDataSticks = new ArrayList<>();
        pRecipes = new ArrayList<>();
        pLastRecipe = null;
        pInputItems = new ItemStack[0];
        pInputFluids = new FluidStack[0];
        bEnableCompatibilityRecipeMap = false;
        bEnableAltCheck = false;
        bEnableDebug = false;
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
    private boolean examineRecipe(@NotNull GT_Recipe recipe, FluidStack[] inputFluid, ItemStack[] inputItem) {
        if (recipe.mEnabled) {
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
    @Contract("_ -> new")
    @NotNull
    private GT_Recipe buildRecipe(GT_AssemblyLineUtils.LookupResult tLookupResult) {
        GT_Recipe.GT_Recipe_AssemblyLine tRecipe = tLookupResult.getRecipe();

        ItemStack[] tInputItemStacks = tRecipe.mInputs.clone();
        ItemStack[] tOutputItemStacks = new ItemStack[] { tRecipe.mOutput.copy() };
        FluidStack[] tInputFluidStacks = tRecipe.mFluidInputs.clone();
        int tDur = tRecipe.mDuration;
        int tEUt = tRecipe.mDuration;

        return new GT_Recipe(
            false,
            tInputItemStacks,
            tOutputItemStacks,
            null,
            null,
            tInputFluidStacks,
            null,
            tDur,
            tEUt,
            0);

    }

    /**
     * This method will generate a general GT_Recipe out of the assembly specific recipe,
     * by the way it will also fix the circuit alt problem caused by AssemblyLineRecipe
     *
     * @param tLookupResult the data of the scanned data sticks
     * @return a GT_Recipe with oredict alt info
     */
    @Contract("_ -> new")
    @NotNull
    private GT_Recipe buildRecipeChecked(GT_AssemblyLineUtils.LookupResult tLookupResult) {
        GT_Recipe.GT_Recipe_AssemblyLine tRecipe = tLookupResult.getRecipe();

        ItemStack[] tInputItemStacks = tRecipe.mInputs.clone();
        ItemStack[] tOutputItemStacks = new ItemStack[] { tRecipe.mOutput.copy() };
        FluidStack[] tInputFluidStacks = tRecipe.mFluidInputs.clone();
        int tDur = tRecipe.mDuration;
        int tEUt = tRecipe.mEUt;

        ItemStack[][] tAlts = tRecipe.mOreDictAlt.clone();

        for (int i = 0; i < tInputItemStacks.length; i++) {
            if (tAlts[i] != null) {
                ItemStack tItemStackAlterable = tInputItemStacks[i];
                ItemData tAltInfo = GT_OreDictUnificator.getAssociation(tItemStackAlterable);

                if (tAltInfo == null) continue;

                if (tAltInfo.mPrefix == OrePrefixes.circuit) {
                    if (bEnableDebug) {
                        VisAppend.LOG.info("Found" + tAltInfo.mMaterial + "circuit, send alt stack.");
                    }
                    tInputItemStacks[i] = GT_OreDictUnificator
                        .get(OrePrefixes.circuit, tAltInfo.mMaterial.mMaterial, tItemStackAlterable.stackSize);
                }
            }
        }

        return GT_Values.RA.stdBuilder()
            .itemInputs(tInputItemStacks)
            .itemOutputs(tOutputItemStacks)
            .fluidInputs(tInputFluidStacks)
            .eut(tEUt)
            .duration(tDur)
            .build()
            .orElseGet(this::getDummy);
    }

    /**
     * Returns a Stream of available recipes.
     */
    public Stream<GT_Recipe> generate() {

        if (!pRawDataSticks.isEmpty()) {
            for (ItemStack tDataStick : pRawDataSticks) {
                GT_AssemblyLineUtils.LookupResult tLookupResult = GT_AssemblyLineUtils
                    .findAssemblyLineRecipeFromDataStick(tDataStick, false);
                if (tLookupResult.getType() == GT_AssemblyLineUtils.LookupResultType.INVALID_STICK) {
                    continue;
                }

                if (bEnableAltCheck) {
                    pRecipes.add(buildRecipeChecked(tLookupResult));
                } else {
                    pRecipes.add(buildRecipe(tLookupResult));
                }
            }

            if (bEnableDebug) {
                VisAppend.LOG.info("RAL found " + pRecipes.size() + " recipes.");
            }

            if (pLastRecipe != null && examineRecipe(pLastRecipe, pInputFluids, pInputItems)) {
                pRecipes.add(pLastRecipe);
            }

            if (!pRecipes.isEmpty()) {
                if (!bEnableCompatibilityRecipeMap) {
                    return pRecipes.stream()
                        .filter(recipe -> examineRecipe(recipe, pInputFluids, pInputItems));
                } else {
                    return Stream.concat(
                        pRecipes.stream()
                            .filter(recipe -> examineRecipe(recipe, pInputFluids, pInputItems)),
                        compatibilityRALMap.findRecipeQuery()
                            .items(pInputItems)
                            .fluids(pInputFluids)
                            .findAll());
                }
            }
        }

        return Stream.empty();
    }

}
