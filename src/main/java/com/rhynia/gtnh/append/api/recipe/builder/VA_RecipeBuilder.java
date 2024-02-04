package com.rhynia.gtnh.append.api.recipe.builder;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.recipe.RecipeMap;
import gregtech.api.util.GT_Recipe;

@SuppressWarnings("unused")
public class VA_RecipeBuilder {

    public static VA_RecipeBuilder builder() {
        return new VA_RecipeBuilder();
    }

    public ItemStack[] inputItems = new ItemStack[0];
    public ItemStack[] outputItems = new ItemStack[0];
    public FluidStack[] inputFluids = new FluidStack[0];
    public FluidStack[] outputFluids = new FluidStack[0];
    public int[] outputChance;
    public int eut = 0;
    public int duration = 0;
    public int specialValue = 0;

    public VA_RecipeBuilder() {}

    public VA_RecipeBuilder itemInputs(ItemStack... inputItems) {
        this.inputItems = inputItems;
        return this;
    }

    public VA_RecipeBuilder itemOutputs(ItemStack... outputItems) {
        this.outputItems = outputItems;
        return this;
    }

    public VA_RecipeBuilder fluidInputs(FluidStack... inputFluids) {
        this.inputFluids = inputFluids;
        return this;
    }

    public VA_RecipeBuilder fluidOutputs(FluidStack... outputFluids) {
        this.outputFluids = outputFluids;
        return this;
    }

    public VA_RecipeBuilder outputChances(int... outputChance) {
        this.outputChance = outputChance;
        return this;
    }

    public VA_RecipeBuilder eut(int eut) {
        this.eut = eut;
        return this;
    }

    public VA_RecipeBuilder eut(long eut) {
        this.eut = (int) eut;
        return this;
    }

    public VA_RecipeBuilder duration(int duration) {
        this.duration = duration;
        return this;
    }

    public VA_RecipeBuilder specialValue(int specialValue) {
        this.specialValue = specialValue;
        return this;
    }

    /**
     * Just for compatibility use
     */
    public VA_RecipeBuilder noOptimize() {
        return this;
    }

    public VA_RecipeBuilder addTo(RecipeMap<?> recipeMap) {
        GT_Recipe tempRecipe = new GT_Recipe(
            false,
            inputItems,
            outputItems,
            null,
            outputChance,
            inputFluids,
            outputFluids,
            duration,
            eut,
            specialValue);

        tempRecipe.mInputs = inputItems.clone();
        tempRecipe.mOutputs = outputItems.clone();

        recipeMap.add(tempRecipe);
        return this;
    }

    public GT_Recipe generateRecipe() {
        GT_Recipe tempRecipe = new GT_Recipe(
            false,
            inputItems,
            outputItems,
            null,
            outputChance,
            inputFluids,
            outputFluids,
            duration,
            eut,
            specialValue);

        tempRecipe.mInputs = inputItems.clone();
        tempRecipe.mOutputs = outputItems.clone();

        return tempRecipe;
    }
}
