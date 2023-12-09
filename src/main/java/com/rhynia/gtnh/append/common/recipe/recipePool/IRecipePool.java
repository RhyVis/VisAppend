package com.rhynia.gtnh.append.common.recipe.recipePool;

public interface IRecipePool {

    /**
     * Called at RecipeLoader
     */
    void loadRecipes();

    void loadRecipesPostInit();
}
