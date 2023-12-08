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
import com.rhynia.gtnh.append.recipe.container.VARecipePool.VASAstraForgeRecipePool;
import com.rhynia.gtnh.append.recipe.container.VARecipePool.VASIntegratedAssemblyRecipePool;
import com.rhynia.gtnh.append.recipe.container.VARecipePool.VASMicroAssemblyRecipePool;
import com.rhynia.gtnh.append.recipe.container.VARecipePool.VASSuperconductingBinderRecipePool;
import com.rhynia.gtnh.append.recipe.container.VARecipePool.VASThermonuclearControlRecipePool;
import com.rhynia.gtnh.append.recipe.container.VARecipePool.VASTranscendentReactorRecipePool;

public class RecipeLoader {

    public static void loadRecipesPostInit() {
        IRecipePool[] recipePools = new IRecipePool[] {
            // VA Special Recipe
            new VASIntegratedAssemblyRecipePool(), new VASSuperconductingBinderRecipePool(),
            new VASThermonuclearControlRecipePool(), new VASAstraForgeRecipePool(), new VASMicroAssemblyRecipePool() };

        for (IRecipePool recipePool : recipePools) {
            recipePool.loadRecipesPostInit();
        }
    }

    public static void loadRecipesCompleteInit() {
        IRecipePool[] recipePools = new IRecipePool[] {
            // VA Special Recipe
            new VASIntegratedAssemblyRecipePool(), new VASSuperconductingBinderRecipePool(),
            new VASThermonuclearControlRecipePool(), new VASAstraForgeRecipePool(), new VASMicroAssemblyRecipePool(),
            new VASTranscendentReactorRecipePool(),
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
