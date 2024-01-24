package com.rhynia.gtnh.append.api.recipe.frontend;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import com.gtnewhorizons.modularui.api.math.Pos2d;
import com.gtnewhorizons.modularui.api.math.Size;
import com.rhynia.gtnh.append.api.util.enums.TextureSets;

import gregtech.api.recipe.BasicUIPropertiesBuilder;
import gregtech.api.recipe.NEIRecipePropertiesBuilder;
import gregtech.api.recipe.RecipeMapFrontend;
import gregtech.api.util.MethodsReturnNonnullByDefault;
import gregtech.common.gui.modularui.UIHelper;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SimpleFrontend extends RecipeMapFrontend {

    public SimpleFrontend(BasicUIPropertiesBuilder uiPropertiesBuilder,
        NEIRecipePropertiesBuilder neiPropertiesBuilder) {
        super(
            uiPropertiesBuilder.logo(TextureSets.VA_LOGO_32)
                .logoSize(new Size(17, 17))
                .logoPos(new Pos2d(79 + rowWidth * 4, 8 + rowWidth * 3)),
            neiPropertiesBuilder);
        int fullRowCount = getFullRowCount();
        neiProperties.recipeBackgroundSize = new Size(170, 10 + fullRowCount * rowWidth + yTweak * 2);
    }

    private static final int yPosBase = 8;
    private static final int xPosBase = 6;
    private static final int xPosBaseAlt = 98;
    private static final int rowWidth = 18;
    private static final int rowCount = 2;
    private static final int xTweak = 2 * rowWidth;
    private static final int yTweak = rowWidth / 2;

    private int getFullRowCount() {
        return getItemRowCount() + getFluidRowCount();
    }

    private int getItemRowCount() {
        return (Math.max(uiProperties.maxItemInputs, uiProperties.maxItemOutputs) - 1) / rowCount + 1;
    }

    private int getFluidRowCount() {
        return (Math.max(uiProperties.maxFluidInputs, uiProperties.maxFluidOutputs) - 1) / rowCount + 1;
    }

    @Override
    public List<Pos2d> getItemInputPositions(int itemInputCount) {
        return UIHelper.getGridPositions(itemInputCount, xPosBase + xTweak, yPosBase + yTweak, rowCount);
    }

    @Override
    public List<Pos2d> getItemOutputPositions(int itemOutputCount) {
        return UIHelper.getGridPositions(itemOutputCount, xPosBaseAlt + xTweak, yPosBase + yTweak, rowCount);
    }

    @Override
    public List<Pos2d> getFluidInputPositions(int fluidInputCount) {
        return UIHelper.getGridPositions(
            fluidInputCount,
            xPosBase + xTweak,
            yPosBase + yTweak + getFullRowCount() * rowWidth,
            rowCount);
    }

    @Override
    public List<Pos2d> getFluidOutputPositions(int fluidOutputCount) {
        return UIHelper.getGridPositions(
            fluidOutputCount,
            xPosBaseAlt + xTweak,
            yPosBase + yTweak + getFullRowCount() * rowWidth,
            rowCount);
    }
}
