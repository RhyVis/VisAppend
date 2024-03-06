package com.rhynia.gtnh.append.api.util;

import static gregtech.api.util.GT_AssemblyLineUtils.findAssemblyLineRecipeFromDataStick;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.rhynia.gtnh.append.VisAppend;
import com.rhynia.gtnh.append.api.recipe.backend.VA_RecipeMapBackend;
import com.rhynia.gtnh.append.common.loader.VA_ItemList;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMapBuilder;
import gregtech.api.util.GT_AssemblyLineUtils;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;

@SuppressWarnings("unused")
public class AssemblyLineRecipeHelper {

    protected ArrayList<ItemStack> pRawDataSticks = new ArrayList<>();
    protected ArrayList<GT_Recipe> pRecipes = new ArrayList<>();
    protected GT_Recipe pLastRecipe = null;
    protected ItemStack[] pInputItems = new ItemStack[0];
    protected FluidStack[] pInputFluids = new FluidStack[0];
    protected long pVoltage = 0L;
    protected boolean bEnableCompatibilityRecipeMap = false;
    protected boolean bEnableDebug = false;
    protected boolean bOnlyCheckFirst = false;
    protected boolean bEnableAltCheck = true;

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
            new ItemStack[] { VA_ItemList.Test01.get(1) },
            new ItemStack[] { VA_ItemList.Test01.get(64) },
            null,
            null,
            null,
            null,
            128,
            128,
            0);
    }

    public AssemblyLineRecipeHelper() {}

    /**
     * Start calling builder.
     */
    public static AssemblyLineRecipeHelper ALH = new AssemblyLineRecipeHelper();

    public static AssemblyLineRecipeHelper builder() {
        return new AssemblyLineRecipeHelper();
    }

    public AssemblyLineRecipeHelper setSticks(ArrayList<ItemStack> dataSticks) {
        pRawDataSticks = dataSticks;
        return this;
    }

    public AssemblyLineRecipeHelper setInputItems(ItemStack... inputs) {
        pInputItems = inputs;
        return this;
    }

    public AssemblyLineRecipeHelper setInputFluids(FluidStack... inputs) {
        pInputFluids = inputs;
        return this;
    }

    public AssemblyLineRecipeHelper setLastRecipe(@Nullable GT_Recipe recipe) {
        pLastRecipe = recipe;
        return this;
    }

    public AssemblyLineRecipeHelper setVoltage(long vol) {
        pVoltage = vol;
        return this;
    }

    /**
     * Inject compatibilityRALMap into recipe findings, no data sticks req, inputs req.
     */
    public AssemblyLineRecipeHelper enableCompatibilityRecipeMap(boolean b) {
        bEnableCompatibilityRecipeMap = b;
        return this;
    }

    /**
     * Designed for focus mode.
     */
    public AssemblyLineRecipeHelper onlyCheckFirst(boolean b) {
        bOnlyCheckFirst = b;
        return this;
    }

    /**
     * Give up the attempt to fix circuit input problem.
     */
    public AssemblyLineRecipeHelper disableAltCheck() {
        bEnableAltCheck = false;
        return this;
    }

    /**
     * Print debug info during running.
     */
    public AssemblyLineRecipeHelper enableDebug() {
        bEnableDebug = true;
        return this;
    }

    /**
     * I don't know why it is here...
     */
    public AssemblyLineRecipeHelper clear() {
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

    public ItemStack switchCircuit(ItemStack stack) {
        var tAltInfo = GT_OreDictUnificator.getAssociation(stack);
        if (tAltInfo == null) return null;
        if (tAltInfo.mPrefix == OrePrefixes.circuit || !tAltInfo.hasValidMaterialData()) {
            return GT_OreDictUnificator.get(OrePrefixes.circuit, tAltInfo.mMaterial.mMaterial, stack.stackSize);
        } else return null;
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
    private GT_Recipe buildRecipe(@NotNull GT_AssemblyLineUtils.LookupResult tLookupResult) {
        GT_Recipe.GT_Recipe_AssemblyLine tRecipe = tLookupResult.getRecipe();

        ItemStack[] tInputItemStacks = tRecipe.mInputs.clone();
        ItemStack[] tOutputItemStacks = new ItemStack[] { tRecipe.mOutput.copy() };
        FluidStack[] tInputFluidStacks = tRecipe.mFluidInputs.clone();
        int tDur = tRecipe.mDuration;
        int tEUt = tRecipe.mDuration;

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
     * This method will generate a general Stream<GT_Recipe> out of the assembly specific recipe,
     * by the way it will also fix the wildcard alt problem caused by AssemblyLineRecipe
     *
     * @return a List<GT_Recipe> with oredict alt info
     */
    private List<GT_Recipe> buildRecipeChecked(GT_Recipe.GT_Recipe_AssemblyLine tRecipe) {

        ItemStack[] tInputStacks = tRecipe.mInputs.clone();
        ItemStack[] tOutput = new ItemStack[] { tRecipe.mOutput.copy() };
        FluidStack[] tFluids = tRecipe.mFluidInputs.clone();

        ItemStack[][] tAlts = tRecipe.mOreDictAlt;

        var tRecipeList = new ArrayList<GT_Recipe>();

        var builder = GT_Values.RA.stdBuilder()
            .eut(tRecipe.mEUt)
            .duration(tRecipe.mDuration);

        if (tAlts != null && tAlts.length > 0) {
            for (int i = 0; i < tAlts.length; i++) {
                if (tAlts[i] != null && tAlts[i].length > 0) {
                    var cs = Optional.ofNullable(switchCircuit(tAlts[i][0]));
                    var tmp = i;
                    cs.ifPresent(
                        // Circuit wildcard
                        itemStack -> {
                            tInputStacks[tmp] = itemStack;
                            tRecipeList.add(
                                builder.itemInputs(tInputStacks)
                                    .fluidInputs(tFluids)
                                    .itemOutputs(tOutput)
                                    .build()
                                    .orElseGet(this::getDummy));
                        });
                    cs.orElseGet(
                        // Other wildcard
                        () -> {
                            for (ItemStack wildcard : tAlts[tmp]) {
                                tInputStacks[tmp] = wildcard;
                                tRecipeList.add(
                                    builder.itemInputs(tInputStacks)
                                        .fluidInputs(tFluids)
                                        .itemOutputs(tOutput)
                                        .build()
                                        .orElseGet(this::getDummy));
                            }
                            return null;
                        });
                }
            }
        }
        tRecipeList.add(
            builder.itemInputs(tInputStacks)
                .fluidInputs(tFluids)
                .itemOutputs(tOutput)
                .build()
                .orElseGet(this::getDummy));

        return tRecipeList;
    }

    /**
     * Generate a Stream of available recipes.
     */
    public Stream<GT_Recipe> generate() {
        if (!pRawDataSticks.isEmpty()) {
            var temp = pRawDataSticks.stream()
                .filter(
                    stick -> findAssemblyLineRecipeFromDataStick(stick, false).getType()
                        != GT_AssemblyLineUtils.LookupResultType.INVALID_STICK)
                .map(stick -> buildRecipeChecked(findAssemblyLineRecipeFromDataStick(stick)))
                .flatMap(List::stream)
                .distinct()
                .filter(recipe -> examineRecipe(recipe, pInputFluids, pInputItems));
            if (!bEnableCompatibilityRecipeMap) {
                return temp;
            } else {
                return Stream.concat(
                    temp,
                    compatibilityRALMap.findRecipeQuery()
                        .items(pInputItems)
                        .fluids(pInputFluids)
                        .findAll());
            }
        } else {
            return Stream.empty();
        }
    }
}
