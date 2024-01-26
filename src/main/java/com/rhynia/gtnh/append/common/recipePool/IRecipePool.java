package com.rhynia.gtnh.append.common.recipePool;

public interface IRecipePool {

    /**
     * Called at RecipeLoader
     * <p>
     * More commonly used
     * </p>
     */
    void loadRecipesCompleteInit();

    /**
     * Called at RecipeLoader
     */
    void loadRecipesPostInit();
}
