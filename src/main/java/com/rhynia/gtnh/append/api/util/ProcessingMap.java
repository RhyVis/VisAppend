package com.rhynia.gtnh.append.api.util;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.jetbrains.annotations.NotNull;

import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gtPlusPlus.api.recipe.GTPPRecipeMaps;
import gtPlusPlus.xmod.gregtech.api.enums.GregtechItemList;

@SuppressWarnings("unused")
public enum ProcessingMap {

    // GTPP
    AlloySmelter(GTPPRecipeMaps.alloyBlastSmelterRecipes, GregtechItemList.Industrial_AlloySmelter.get(1)),
    ChemicalDehydrator(GTPPRecipeMaps.chemicalDehydratorNonCellRecipes,
        GregtechItemList.Controller_Vacuum_Furnace.get(1), 1),
    VacuumFurnace(GTPPRecipeMaps.vacuumFurnaceRecipes, GregtechItemList.Controller_Vacuum_Furnace.get(1), 2),
    ArcFurnace(RecipeMaps.arcFurnaceRecipes, GregtechItemList.Industrial_Arc_Furnace.get(1)),
    PlasmaArcFurnace(RecipeMaps.plasmaArcFurnaceRecipes, GregtechItemList.Industrial_Arc_Furnace.get(1)),;

    private final RecipeMap<?> pRecipeMap;
    private final ItemStack pMachine;
    private final int pSeparateOrdinal;

    ProcessingMap(RecipeMap<?> recipeMap, ItemStack machine) {
        this.pRecipeMap = recipeMap;
        this.pMachine = machine;
        this.pSeparateOrdinal = 0;
    }

    ProcessingMap(RecipeMap<?> recipeMap, ItemStack machine, int ordinal) {
        this.pRecipeMap = recipeMap;
        this.pMachine = machine;
        this.pSeparateOrdinal = ordinal;
    }

    public RecipeMap<?> getProcessingRecipeMap() {
        return this.pRecipeMap;
    }

    public ItemStack getProcessingMachine() {
        return this.pMachine;
    }

    public int getSeparateOrdinal() {
        return this.pSeparateOrdinal;
    }

    public String getMapNameUnlocalized() {
        return this.pRecipeMap.unlocalizedName;
    }

    public String getMapNameDisplay() {
        return StatCollector.translateToLocal(this.pRecipeMap.unlocalizedName);
    }

    @NotNull
    public ArrayList<ProcessingMap> getAllPropertiesBySameMachine() {
        ArrayList<ProcessingMap> tempList = new ArrayList<>();
        if (this.getSeparateOrdinal() == 0) {
            tempList.add(this);
        } else {
            for (ProcessingMap processMachine : ProcessingMap.values()) {
                if (processMachine.pMachine.isItemEqual(this.pMachine)) {
                    tempList.add(processMachine);
                }
            }
        }
        return tempList;
    }

    @NotNull
    public ArrayList<RecipeMap<?>> getAllRecipeMapsBySameMachine() {
        ArrayList<RecipeMap<?>> tempList = new ArrayList<>();
        if (this.getSeparateOrdinal() == 0) {
            tempList.add(this.pRecipeMap);
        } else {
            for (ProcessingMap processMachine : ProcessingMap.values()) {
                if (processMachine.pMachine.isItemEqual(this.pMachine)) {
                    tempList.add(processMachine.pRecipeMap);
                }
            }
        }
        return tempList;
    }
}
