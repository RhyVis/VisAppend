package com.rhynia.gtnh.append.api.enums.processingUtil;

import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.VA_ItemList;

import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gtPlusPlus.api.recipe.GTPPRecipeMaps;
import gtPlusPlus.xmod.gregtech.api.enums.GregtechItemList;

@SuppressWarnings("unused")
public enum ProcessingEnum {

    // GTPP
    AlloySmelter(GTPPRecipeMaps.alloyBlastSmelterRecipes, GregtechItemList.Industrial_AlloySmelter.get(1)),
    ChemicalDehydrator(GTPPRecipeMaps.chemicalDehydratorNonCellRecipes,
        GregtechItemList.Controller_Vacuum_Furnace.get(1)),
    VacuumFurnace(GTPPRecipeMaps.vacuumFurnaceRecipes, GregtechItemList.Controller_Vacuum_Furnace.get(1)),
    ArcFurnace(RecipeMaps.arcFurnaceRecipes, GregtechItemList.Industrial_Arc_Furnace.get(1)),
    PlasmaArcFurnace(RecipeMaps.plasmaArcFurnaceRecipes, GregtechItemList.Industrial_Arc_Furnace.get(1)),
    FluidHeater(RecipeMaps.fluidHeaterRecipes, GregtechItemList.Controller_IndustrialFluidHeater.get(1)),
    MolecularTransformer(GTPPRecipeMaps.molecularTransformerRecipes,
        GregtechItemList.Controller_MolecularTransformer.get(1)),
    OreWash(RecipeMaps.oreWasherRecipes, GregtechItemList.Industrial_WashPlant.get(1)),
    SimpleWash(GTPPRecipeMaps.simpleWasherRecipes, GregtechItemList.Industrial_WashPlant.get(1)),
    ChemicalWash(RecipeMaps.chemicalBathRecipes, GregtechItemList.Industrial_WashPlant.get(1)),
    DTPF(RecipeMaps.plasmaForgeRecipes, VA_ItemList.Assembly_DTPF.get(1)),;

    private final RecipeMap<?> pRecipeMap;
    private final ItemStack pMachine;

    ProcessingEnum(RecipeMap<?> recipeMap, ItemStack machine) {
        this.pRecipeMap = recipeMap;
        this.pMachine = machine;
    }

    public RecipeMap<?> getProcessingRecipeMap() {
        return this.pRecipeMap;
    }

    public ItemStack getProcessingMachine() {
        return this.pMachine;
    }

}
