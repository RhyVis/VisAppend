package com.rhynia.gtnh.append.api.recipe;

import com.gtnewhorizons.modularui.common.widget.ProgressBar;
import com.rhynia.gtnh.append.api.recipe.frontend.AstralForgeFrontend;
import com.rhynia.gtnh.append.api.recipe.frontend.IntegratedAssemblyFrontend;
import com.rhynia.gtnh.append.api.recipe.frontend.MicroAssemblyFrontend;
import com.rhynia.gtnh.append.api.recipe.frontend.ThermonuclearControlFrontend;
import com.rhynia.gtnh.append.api.recipe.frontend.TranscendentReactorFrontend;
import com.rhynia.gtnh.append.api.util.Values;

import gregtech.api.gui.modularui.GT_UITextures;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMapBackend;
import gregtech.api.recipe.RecipeMapBuilder;

public class AppendRecipeMaps {

    /** Astral Forge (AF) Recipe */
    public static final RecipeMap<RecipeMapBackend> astralForgeRecipes = RecipeMapBuilder.of("va.recipe.astralForge")
        .maxIO(16, 16, 8, 8)
        .minInputs(1, 0)
        .logo(Values.VA_LOGO_32)
        .logoSize(17, 17)
        .logoPos(79, 52)
        .neiTransferRect(80, 30, 15, 15)
        .progressBar(GT_UITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .progressBarSize(15, 15)
        .progressBarPos(80, 30)
        .neiRecipeBackgroundSize(170, 10 + 6 * 18)
        .frontend(AstralForgeFrontend::new)
        .build();
    /** Thermonuclear Control (TC) Recipe */
    public static final RecipeMap<RecipeMapBackend> thermonuclearControlRecipes = RecipeMapBuilder
        .of("va.recipe.thermonuclearControl")
        .maxIO(4, 0, 2, 4)
        .minInputs(1, 0)
        .logo(Values.VA_LOGO_32)
        .logoSize(17, 17)
        .logoPos(79, 52)
        .progressBar(GT_UITextures.PROGRESSBAR_MIXER, ProgressBar.Direction.CIRCULAR_CW)
        .progressBarSize(17, 17)
        .progressBarPos(79, 27)
        .neiRecipeBackgroundSize(170, 10 + 4 * 18)
        .frontend(ThermonuclearControlFrontend::new)
        .build();
    /** Transcendent Reactor (TR) Recipe */
    public static final RecipeMap<RecipeMapBackend> transcendentReactorRecipes = RecipeMapBuilder
        .of("va.recipe.transcendentReactor")
        .maxIO(16, 6, 8, 6)
        .minInputs(1, 0)
        .logo(Values.VA_LOGO_32)
        .logoSize(17, 17)
        .logoPos(84, 80)
        .progressBar(GT_UITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .progressBarSize(15, 15)
        .progressBarPos(85, 27)
        .neiRecipeBackgroundSize(170, 10 + 6 * 18)
        .frontend(TranscendentReactorFrontend::new)
        .build();
    /** Integrated Assembly (IA) Recipe */
    public static final RecipeMap<RecipeMapBackend> integratedAssemblyRecipes = RecipeMapBuilder
        .of("va.recipe.integratedAssembly")
        .maxIO(12, 1, 8, 0)
        .minInputs(1, 0)
        .logo(Values.VA_LOGO_32)
        .logoSize(17, 17)
        .logoPos(79 + 18 * 4, 8 + 18 * 4)
        .progressBar(GT_UITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .progressBarSize(17, 17)
        .progressBarPos(88, 27)
        .neiRecipeBackgroundSize(170, 10 + 5 * 18)
        .frontend(IntegratedAssemblyFrontend::new)
        .build();
    /** Micro Assembly (MA) Recipe */
    public static final RecipeMap<RecipeMapBackend> microAssemblyRecipes = RecipeMapBuilder
        .of("va.recipe.microAssembly")
        .maxIO(8, 1, 8, 0)
        .minInputs(1, 0)
        .logo(Values.VA_LOGO_32)
        .logoSize(17, 17)
        .logoPos(79 + 18 * 4, 8 + 18 * 3)
        .progressBar(GT_UITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .progressBarSize(17, 17)
        .progressBarPos(88, 27)
        .neiRecipeBackgroundSize(170, 10 + 4 * 18)
        .frontend(MicroAssemblyFrontend::new)
        .build();
    /** Superconducting Assembly (SA) Recipe */
    public static final RecipeMap<RecipeMapBackend> superconductingAssemblyRecipes = RecipeMapBuilder
        .of("va.recipe.superconductingAssembly")
        .maxIO(8, 1, 8, 0)
        .minInputs(1, 0)
        .logo(Values.VA_LOGO_32)
        .logoSize(17, 17)
        .logoPos(79 + 18 * 4, 8 + 18 * 3)
        .progressBar(GT_UITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .progressBarSize(17, 17)
        .progressBarPos(88, 27)
        .neiRecipeBackgroundSize(170, 10 + 4 * 18)
        .frontend(MicroAssemblyFrontend::new)
        .build();
}