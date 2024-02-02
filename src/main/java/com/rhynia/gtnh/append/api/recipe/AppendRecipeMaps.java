package com.rhynia.gtnh.append.api.recipe;

import com.gtnewhorizons.modularui.common.widget.ProgressBar;
import com.rhynia.gtnh.append.api.enums.VA_Values;
import com.rhynia.gtnh.append.api.recipe.backend.VA_RecipeMapBackend;
import com.rhynia.gtnh.append.api.recipe.frontend.AstralForgeFrontend;
import com.rhynia.gtnh.append.api.recipe.frontend.IntegratedAssemblyFrontend;
import com.rhynia.gtnh.append.api.recipe.frontend.MicroAssemblyFrontend;
import com.rhynia.gtnh.append.api.recipe.frontend.ThermonuclearControlFrontend;
import com.rhynia.gtnh.append.api.recipe.frontend.TranscendentReactorFrontend;
import com.rhynia.gtnh.append.common.VAItemList;

import gregtech.api.gui.modularui.GT_UITextures;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMapBackend;
import gregtech.api.recipe.RecipeMapBuilder;

public class AppendRecipeMaps {

    /** Astral Forge (AF) Recipe */
    public static final RecipeMap<RecipeMapBackend> astralForgeRecipes = RecipeMapBuilder.of("va.recipe.astralForge")
        .maxIO(16, 16, 8, 8)
        .minInputs(1, 0)
        .logo(VA_Values.TextureSets.VA_LOGO_32)
        .logoSize(17, 17)
        .logoPos(79, 52)
        .neiTransferRect(80, 30, 15, 15)
        .progressBar(GT_UITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .progressBarSize(15, 15)
        .progressBarPos(80, 30)
        .neiHandlerInfo(
            builder -> builder.setDisplayStack(VAItemList.AstraForge.get(1))
                .setMaxRecipesPerPage(1))
        .neiRecipeBackgroundSize(170, 10 + 6 * 18)
        .frontend(AstralForgeFrontend::new)
        .build();
    /** Thermonuclear Control (TC) Recipe */
    public static final RecipeMap<RecipeMapBackend> thermonuclearControlRecipes = RecipeMapBuilder
        .of("va.recipe.thermonuclearControl")
        .maxIO(4, 0, 2, 4)
        .minInputs(1, 0)
        .logo(VA_Values.TextureSets.VA_LOGO_32)
        .logoSize(17, 17)
        .logoPos(79, 52)
        .progressBar(GT_UITextures.PROGRESSBAR_MIXER, ProgressBar.Direction.CIRCULAR_CW)
        .progressBarSize(17, 17)
        .progressBarPos(79, 27)
        .neiHandlerInfo(
            builder -> builder.setDisplayStack(VAItemList.UltimateHeater.get(1))
                .setMaxRecipesPerPage(2))
        .neiRecipeBackgroundSize(170, 10 + 4 * 18)
        .frontend(ThermonuclearControlFrontend::new)
        .build();
    /** Transcendent Reactor (TR) Recipe */
    public static final RecipeMap<RecipeMapBackend> transcendentReactorRecipes = RecipeMapBuilder
        .of("va.recipe.transcendentReactor")
        .maxIO(16, 6, 8, 6)
        .minInputs(1, 0)
        .logo(VA_Values.TextureSets.VA_LOGO_32)
        .logoSize(17, 17)
        .logoPos(84, 80)
        .progressBar(GT_UITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .progressBarSize(15, 15)
        .progressBarPos(85, 27)
        .neiHandlerInfo(
            builder -> builder.setDisplayStack(VAItemList.UltimateHeater.get(1))
                .setMaxRecipesPerPage(1))
        .neiRecipeBackgroundSize(170, 10 + 6 * 18)
        .frontend(TranscendentReactorFrontend::new)
        .build();
    /** Integrated Assembly (IA) Recipe (Allow 64+ Stack) */
    public static final RecipeMap<VA_RecipeMapBackend> integratedAssemblyRecipes = RecipeMapBuilder
        .of("va.recipe.integratedAssembly", VA_RecipeMapBackend::new)
        .maxIO(12, 1, 8, 0)
        .minInputs(1, 0)
        .logo(VA_Values.TextureSets.VA_LOGO_32)
        .logoSize(17, 17)
        .logoPos(79 + 18 * 4, 8 + 18 * 4)
        .progressBar(GT_UITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .progressBarSize(17, 17)
        .progressBarPos(9 + 88, 27)
        .neiTransferRect(9 + 88, 27, 17, 17)
        .neiHandlerInfo(
            builder -> builder.setDisplayStack(VAItemList.AssemblyMatrix.get(1))
                .setMaxRecipesPerPage(1))
        .neiRecipeBackgroundSize(170, 10 + 5 * 18)
        .frontend(IntegratedAssemblyFrontend::new)
        .build();
    /** Micro Assembly (MA) Recipe */
    public static final RecipeMap<RecipeMapBackend> microAssemblyRecipes = RecipeMapBuilder
        .of("va.recipe.microAssembly")
        .maxIO(8, 1, 8, 0)
        .minInputs(1, 0)
        .logo(VA_Values.TextureSets.VA_LOGO_32)
        .logoSize(17, 17)
        .logoPos(79 + 18 * 4, 8 + 18 * 3)
        .progressBar(GT_UITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .progressBarSize(17, 17)
        .progressBarPos(9 + 88, 27)
        .neiTransferRect(9 + 88, 27, 17, 17)
        .neiHandlerInfo(
            builder -> builder.setDisplayStack(VAItemList.AssemblyMatrix.get(1))
                .setMaxRecipesPerPage(1))
        .neiRecipeBackgroundSize(170, 10 + 4 * 18)
        .frontend(MicroAssemblyFrontend::new)
        .build();
    /** Void Energy Generator (VEG) Recipe */
    public static final RecipeMap<RecipeMapBackend> voidEnergyGeneratorRecipes = RecipeMapBuilder
        .of("va.recipe.voidEnergyGenerator")
        .maxIO(2, 2, 2, 2)
        .minInputs(1, 0)
        .progressBar(GT_UITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .progressBarSize(17, 17)
        .progressBarPos(79, 27)
        .neiTransferRect(79, 27, 17, 17)
        .neiHandlerInfo(
            builder -> builder.setDisplayStack(VAItemList.VoidEnergyGenerator.get(1))
                .setMaxRecipesPerPage(2))
        .build();
    /** Superconducting Forming (SF) Recipe (Allow 64+ Stack) */
    public static final RecipeMap<VA_RecipeMapBackend> superconductingFormingRecipes = RecipeMapBuilder
        .of("va.recipe.superconductingForming", VA_RecipeMapBackend::new)
        .maxIO(6, 2, 3, 2)
        .minInputs(1, 0)
        .logo(VA_Values.TextureSets.VA_LOGO_32)
        .neiHandlerInfo(
            builder -> builder.setDisplayStack(VAItemList.AssemblyMatrix.get(1))
                .setMaxRecipesPerPage(2))
        .build();
}
