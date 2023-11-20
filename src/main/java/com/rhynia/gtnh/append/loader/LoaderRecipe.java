package com.rhynia.gtnh.append.loader;

import com.rhynia.gtnh.append.recipe.IRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.AppendCentrifugeRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.AppendChemicalReactorRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.AppendCommonRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.AppendElectroRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.AppendHammerRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.AppendLaserEngraverRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.AppendMachineRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.AppendMixerRecipePool;
import com.rhynia.gtnh.append.recipe.container.VARecipePool.AssemblyMatrixRecipePool;
import com.rhynia.gtnh.append.recipe.container.VARecipePool.AstraForgeRecipePool;
import com.rhynia.gtnh.append.recipe.container.VARecipePool.UltimateHeaterRecipePool;

public class LoaderRecipe {

    public static void loadRecipes() {
        IRecipePool[] recipePools = new IRecipePool[] {
            // Basic & Misc Recipe
            new AppendCommonRecipePool(), new AppendMachineRecipePool(),
            // VA Special Recipe
            new AstraForgeRecipePool(), new UltimateHeaterRecipePool(), new AssemblyMatrixRecipePool(),
            // GT Recipe
            new AppendCentrifugeRecipePool(), new AppendElectroRecipePool(), new AppendMixerRecipePool(),
            new AppendChemicalReactorRecipePool(), new AppendHammerRecipePool(), new AppendLaserEngraverRecipePool() };

        for (IRecipePool recipePool : recipePools) {
            recipePool.loadRecipes();
        }
    }
}
