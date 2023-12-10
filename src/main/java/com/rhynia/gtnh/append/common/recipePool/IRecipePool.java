package com.rhynia.gtnh.append.common.recipePool;

public interface IRecipePool {

    /**
     * Called at RecipeLoader
     */
    void loadRecipes();

    void loadRecipesPostInit();
}
