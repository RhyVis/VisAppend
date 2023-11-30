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
import com.rhynia.gtnh.append.recipe.container.VARecipePool.VASAssemblyMatrixRecipePool;
import com.rhynia.gtnh.append.recipe.container.VARecipePool.VASAstraForgeRecipePool;
import com.rhynia.gtnh.append.recipe.container.VARecipePool.VASSuperconductingBinderRecipePool;
import com.rhynia.gtnh.append.recipe.container.VARecipePool.VASUltimateHeaterRecipePool;

public class LoaderRecipe {

    public static void loadRecipes() {
        IRecipePool[] recipePools = new IRecipePool[] {
            // Basic & Misc Recipe
            new VACommonRecipePool(), new VAMachineRecipePool(),
            // VA Special Recipe
            new VASAstraForgeRecipePool(), new VASUltimateHeaterRecipePool(), new VASAssemblyMatrixRecipePool(),
            new VASSuperconductingBinderRecipePool(),
            // GT Recipe
            new VACentrifugeRecipePool(), new VAElectroRecipePool(), new VAMixerRecipePool(),
            new VAChemicalReactorRecipePool(), new VAHammerRecipePool(), new VALaserEngraverRecipePool() };

        for (IRecipePool recipePool : recipePools) {
            recipePool.loadRecipes();
        }
    }
}
