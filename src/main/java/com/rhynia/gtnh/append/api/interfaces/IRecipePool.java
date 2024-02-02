package com.rhynia.gtnh.append.api.interfaces;

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
    @Deprecated
    void loadRecipesPostInit();
}
