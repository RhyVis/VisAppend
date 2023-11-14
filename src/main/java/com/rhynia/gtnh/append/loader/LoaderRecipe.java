package com.rhynia.gtnh.append.loader;

import com.rhynia.gtnh.append.recipe.IRecipePool;
import com.rhynia.gtnh.append.recipe.container.AppendCentrifugeRecipePool;
import com.rhynia.gtnh.append.recipe.container.AppendElectroRecipePool;
import com.rhynia.gtnh.append.recipe.container.AppendCommonRecipePool;
import com.rhynia.gtnh.append.recipe.container.AppendMachineRecipePool;
import com.rhynia.gtnh.append.recipe.container.AstraForgeRecipePool;
import com.rhynia.gtnh.append.recipe.container.UltimateHeaterRecipePool;

public class LoaderRecipe {

    public static void loadRecipes() {
        IRecipePool[] recipePools = new IRecipePool[] { new AppendCommonRecipePool(), new AppendMachineRecipePool(),
            new AppendCentrifugeRecipePool(), new AppendElectroRecipePool(), new AstraForgeRecipePool(), new UltimateHeaterRecipePool() };

        for (IRecipePool recipePool : recipePools) {
            recipePool.loadRecipes();
        }
    }
}
