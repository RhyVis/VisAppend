package com.rhynia.gtnh.append.api.recipe.frontend;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import com.gtnewhorizons.modularui.api.math.Pos2d;

import gregtech.api.recipe.BasicUIPropertiesBuilder;
import gregtech.api.recipe.NEIRecipePropertiesBuilder;
import gregtech.api.recipe.RecipeMapFrontend;
import gregtech.api.util.MethodsReturnNonnullByDefault;
import gregtech.common.gui.modularui.UIHelper;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AstralForgeFrontend extends RecipeMapFrontend {

    public AstralForgeFrontend(BasicUIPropertiesBuilder uiPropertiesBuilder,
        NEIRecipePropertiesBuilder neiPropertiesBuilder) {
        super(uiPropertiesBuilder, neiPropertiesBuilder);
    }

    private final int yOrigin = 8;
    private final int xOriginLeft = 6;
    private final int xOriginRight = 98;
    private final int rowWidth = 18;
    private final int xDirMaxCount = 3;

    @Override
    public List<Pos2d> getItemInputPositions(int itemInputCount) {
        return UIHelper.getGridPositions(itemInputCount, xOriginLeft + 9, yOrigin, xDirMaxCount);
    }

    @Override
    public List<Pos2d> getItemOutputPositions(int itemOutputCount) {
        return UIHelper.getGridPositions(itemOutputCount, xOriginRight + 9, yOrigin, xDirMaxCount);
    }

    @Override
    public List<Pos2d> getFluidInputPositions(int fluidInputCount) {
        return UIHelper.getGridPositions(fluidInputCount, xOriginLeft + 9, yOrigin + 6 * rowWidth, xDirMaxCount);
    }

    @Override
    public List<Pos2d> getFluidOutputPositions(int fluidOutputCount) {
        return UIHelper.getGridPositions(fluidOutputCount, xOriginRight + 9, yOrigin + 6 * rowWidth, xDirMaxCount);
    }
}
