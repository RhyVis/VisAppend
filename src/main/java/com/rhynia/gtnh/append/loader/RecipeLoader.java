package com.rhynia.gtnh.append.loader;

import com.rhynia.gtnh.append.recipe.IRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.VACentrifugeRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.VAChemicalReactorRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.VACommonRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.VAElectroRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.VAHammerRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.VALaserEngraverRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.VAMachineRecipePool;
import com.rhynia.gtnh.append.recipe.container.GTRecipePool.VAMixerRecipePool;
import com.rhynia.gtnh.append.recipe.container.VARecipePool.*;

public class RecipeLoader {

    public static void loadRecipesPostInit() {
        IRecipePool[] recipePools = new IRecipePool[] {
            // VA Special Recipe
            new VASAssemblyMatrixRecipePool(), new VASSuperconductingBinderRecipePool(),
            new VASUltimateHeaterRecipePool(), new VASAstraForgeRecipePool(), new VASMicroAssemblyRecipePool() };

        for (IRecipePool recipePool : recipePools) {
            recipePool.loadRecipesPostInit();
        }
    }

    public static void loadRecipesCompleteInit() {
        IRecipePool[] recipePools = new IRecipePool[] {
            // VA Special Recipe
            new VASAssemblyMatrixRecipePool(), new VASSuperconductingBinderRecipePool(),
            new VASUltimateHeaterRecipePool(), new VASAstraForgeRecipePool(), new VASMicroAssemblyRecipePool(),
            // Basic & Misc Recipe
            new VACommonRecipePool(), new VAMachineRecipePool(),
            // GT Recipe
            new VACentrifugeRecipePool(), new VAElectroRecipePool(), new VAMixerRecipePool(),
            new VAChemicalReactorRecipePool(), new VAHammerRecipePool(), new VALaserEngraverRecipePool() };

        for (IRecipePool recipePool : recipePools) {
            recipePool.loadRecipes();
        }
    }
}
