package com.rhynia.gtnh.append.loader;

import com.rhynia.gtnh.append.recipe.IRecipePool;
import com.rhynia.gtnh.append.recipe.container.AppendCommonRecipePool;
import com.rhynia.gtnh.append.recipe.container.AppendMachineRecipePool;
import com.rhynia.gtnh.append.recipe.container.AstraForgeRecipePool;

public class LoaderRecipe {

    public static void loadRecipes() {
        IRecipePool[] recipePools = new IRecipePool[] { new AppendCommonRecipePool(), new AppendMachineRecipePool(),
            new AstraForgeRecipePool() };

        for (IRecipePool recipePool : recipePools) {
            recipePool.loadRecipes();
        }
    }
}
