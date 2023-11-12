package com.rhynia.gtnh.append.loader;

import com.rhynia.gtnh.append.recipe.IRecipePool;
import com.rhynia.gtnh.append.recipe.container.*;

public class LoaderRecipe {

    public static void loadRecipes() {
        IRecipePool[] recipePools = new IRecipePool[] { new AppendCommonRecipePool(), new AppendMachineRecipePool(),
            new AppendCentrifugeRecipePool(), new AstraForgeRecipePool(), new UltimateHeaterRecipePool() };

        for (IRecipePool recipePool : recipePools) {
            recipePool.loadRecipes();
        }
    }
}
