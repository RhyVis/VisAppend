package com.rhynia.gtnh.append.common.machine.recipe;

import com.gtnewhorizons.modularui.api.math.Pos2d;
import com.gtnewhorizons.modularui.api.screen.ModularWindow;
import com.gtnewhorizons.modularui.common.widget.DrawableWidget;
import goodgenerator.client.GUI.GG_UITextures;
import gregtech.api.util.GT_Recipe;
import net.minecraft.util.StatCollector;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Supplier;

public class GTAppendGeneratorRecipe {
    public static final GTAppendGeneratorRecipe instance = new GTAppendGeneratorRecipe();
    public final CasimirMFGMapper CMFGFuels = (CasimirMFGMapper) new CasimirMFGMapper(
        new HashSet<>(50),
        "append.recipe.CasimirMacroFieldGenerator",
        "零点能发生器",
        null,
        "append:textures/gui/MFGM",
        0,
        0,
        0,
        1,
        1,
        "基础动能场压:",
        1,
        " EU/t",
        false,
        true) {
        @Override
        public void addProgressBarUI(ModularWindow.Builder builder, Supplier<Float> progressSupplier,
                                     Pos2d windowOffset) {
            builder.widget(
                new DrawableWidget().setDrawable(GG_UITextures.PICTURE_NAQUADAH_REACTOR)
                    .setPos(new Pos2d(59, 20).add(windowOffset)).setSize(58, 42));
        }
    }.useModularUI(true);

    public static class CasimirMFGMapper extends GT_Recipe.GT_Recipe_Map_Fuel {

        public CasimirMFGMapper(
            Collection<GT_Recipe> aRecipeList,
            String aUnlocalizedName, String aLocalName,
            String aNEIName, String aNEIGUIPath, int aUsualInputCount, int aUsualOutputCount,
            int aMinimalInputItems, int aMinimalInputFluids, int aAmperage, String aNEISpecialValuePre,
            int aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI,
            boolean aNEIAllowed) {
            super(
                aRecipeList,
                aUnlocalizedName,
                aLocalName,
                aNEIName,
                aNEIGUIPath,
                aUsualInputCount,
                aUsualOutputCount,
                aMinimalInputItems,
                aMinimalInputFluids,
                aAmperage,
                aNEISpecialValuePre,
                aNEISpecialValueMultiplier,
                aNEISpecialValuePost,
                aShowVoltageAmperageInNEI,
                aNEIAllowed);
        }
    }
}
