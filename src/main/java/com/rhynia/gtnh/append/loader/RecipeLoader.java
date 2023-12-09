package com.rhynia.gtnh.append.loader;

import com.rhynia.gtnh.append.common.recipe.recipePool.IRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.GTRecipePool.VACentrifugeRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.GTRecipePool.VAChemicalReactorRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.GTRecipePool.VACommonRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.GTRecipePool.VAElectroRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.GTRecipePool.VAFusionRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.GTRecipePool.VAHammerRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.GTRecipePool.VALaserEngraverRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.GTRecipePool.VAMachineRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.GTRecipePool.VAMixerRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.VARecipePool.VASAstraForgeRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.VARecipePool.VASIntegratedAssemblyRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.VARecipePool.VASMicroAssemblyRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.VARecipePool.VASSuperconductingBinderRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.VARecipePool.VASThermonuclearControlRecipePool;
import com.rhynia.gtnh.append.common.recipe.recipePool.container.VARecipePool.VASTranscendentReactorRecipePool;

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
            new VAChemicalReactorRecipePool(), new VAHammerRecipePool(), new VALaserEngraverRecipePool(),
            new VAFusionRecipePool() };

        for (IRecipePool recipePool : recipePools) {
            recipePool.loadRecipes();
        }
    }
}
