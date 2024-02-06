package com.rhynia.gtnh.append.common.recipe.VARecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_UV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import net.minecraft.item.ItemStack;

import com.gtnewhorizons.gtnhintergalactic.block.IGBlocks;
import com.rhynia.gtnh.append.api.enums.refHelper.SolderMaterial;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.recipe.builder.VA_RecipeBuilder;
import com.rhynia.gtnh.append.api.util.AssemblyLineHelper;
import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.material.VAMaterials;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.util.GT_OreDictUnificator;

public class VASCompatibilityRecipePool implements IRecipePool {

    private final RecipeMap<?> RAL = AssemblyLineHelper.compatibilityRALMap;

    @Override
    public void loadRecipesCompleteInit() {
        // Test
        VA_RecipeBuilder.builder()
            .itemInputs(VAItemList.ItemUltimate.get(1))
            .fluidInputs(VAMaterials.Astrium.getMolten(1))
            .itemOutputs(VAItemList.Test.get(1))
            .eut(100_000_000)
            .duration(128)
            .addTo(RAL);

        // region GTNH-Intergalactic

        // Space Elevator Base Casing
        VA_RecipeBuilder.builder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Neutronium, 8),
                GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Palladium, 32),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Osmiridium, 64),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Infinite, 4),
                ItemList.Electric_Piston_UV.get(2),
                GT_OreDictUnificator.get(OrePrefixes.ring, Materials.CosmicNeutronium, 8))
            .fluidInputs(
                SolderMaterial.IndaAlloy.getFluidStack(5760),
                Materials.UUMatter.getFluid(2000),
                Materials.Iridium.getMolten(1152))
            .itemOutputs(new ItemStack(IGBlocks.SpaceElevatorCasing, 8, 0))
            .eut(RECIPE_UV)
            .duration(60 * SECONDS)
            .addTo(RAL);

        // endregion
    }

    @Override
    public void loadRecipesPostInit() {}
}
