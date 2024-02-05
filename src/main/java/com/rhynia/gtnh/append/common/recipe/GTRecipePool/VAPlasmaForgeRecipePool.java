package com.rhynia.gtnh.append.common.recipe.GTRecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UIV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UXV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;
import static gregtech.api.enums.Mods.GTPlusPlus;
import static gregtech.api.util.GT_RecipeConstants.COIL_HEAT;

import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.common.VAItemList;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GT_ModHandler;

public class VAPlasmaForgeRecipePool implements IRecipePool {

    @Override
    public void loadRecipesCompleteInit() {
        final IRecipeMap PF = RecipeMaps.plasmaForgeRecipes;

        GT_Values.RA.stdBuilder()
            .itemInputs(VAItemList.PreTesseract.get(4))
            .itemOutputs(ItemList.Tesseract.get(4))
            .fluidInputs(MaterialsUEVplus.ExcitedDTRC.getFluid(1000))
            .fluidOutputs(MaterialsUEVplus.DimensionallyTranscendentResidue.getFluid(1000 / 2))
            .eut(RECIPE_UIV)
            .duration(20 * SECONDS)
            .metadata(COIL_HEAT, 10800 + 900)
            .addTo(PF);
        GT_Values.RA.stdBuilder()
            .itemInputs(ItemList.Tesseract.get(0), VAItemList.PreTesseract.get(16))
            .itemOutputs(ItemList.Tesseract.get(16))
            .fluidInputs(MaterialsUEVplus.ExcitedDTSC.getFluid(1000))
            .fluidOutputs(MaterialsUEVplus.DimensionallyTranscendentResidue.getFluid(1000 * 2))
            .eut(RECIPE_UXV)
            .duration(20 * SECONDS)
            .metadata(COIL_HEAT, 10800 + 2700)
            .addTo(PF);

        GT_Values.RA.stdBuilder()
            .itemInputs(
                VAItemList.PreTesseract.get(4),
                GT_ModHandler.getModItem(GTPlusPlus.ID, "MU-metaitem.01", 0, 32100))
            .fluidInputs(
                MaterialsUEVplus.DimensionallyTranscendentResidue.getFluid(20000),
                Materials.Infinity.getMolten(4 * 1152))
            .fluidOutputs(MaterialsUEVplus.SpaceTime.getMolten(4 * 576))
            .eut(2_000_000_000)
            .duration(20 * SECONDS)
            .metadata(COIL_HEAT, 10800 + 2700)
            .addTo(PF);
    }

    @Override
    public void loadRecipesPostInit() {}
}
