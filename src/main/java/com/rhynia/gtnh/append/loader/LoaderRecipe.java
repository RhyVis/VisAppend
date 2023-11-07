package com.rhynia.gtnh.append.loader;

public class LoaderRecipe {

    public static void loadRecipes() {
        IRecipePool[] recipePools = new IRecipePool[] { new GTCMMachineRecipePool(),
            new IntensifyChemicalDistorterRecipePool(), new ChemicalReactorRecipePool(),
            new PreciseHighEnergyPhotonicQuantumMasterRecipePool(), new CircuitAssemblerRecipePool(),
            new MiracleTopRecipePool(), new FluidSolidifierRecipePool() };

        for (IRecipePool recipePool : recipePools) {
            recipePool.loadRecipes();
        }
    }
}
