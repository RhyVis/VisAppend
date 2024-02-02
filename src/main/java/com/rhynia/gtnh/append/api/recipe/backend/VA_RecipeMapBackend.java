package com.rhynia.gtnh.append.api.recipe.backend;

import gregtech.api.recipe.RecipeMapBackend;
import gregtech.api.recipe.RecipeMapBackendPropertiesBuilder;

/**
 * If recipe input items include GT Material meta item and stack size is larger than 64, use this class to replace
 * RecipeMapBackend .
 */
public class VA_RecipeMapBackend extends RecipeMapBackend {

    public VA_RecipeMapBackend(RecipeMapBackendPropertiesBuilder propertiesBuilder) {
        super(propertiesBuilder);
    }

    /**
     * Do nothing.
     */
    @Override
    public void reInit() {}
}
