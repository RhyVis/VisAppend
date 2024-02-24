package com.rhynia.gtnh.append.common.recipe.GTRecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.common.material.VA_Materials;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.core.item.chemistry.GenericChem;
import gtPlusPlus.core.lib.CORE;
import gtPlusPlus.core.util.minecraft.ItemUtils;

public class VAQuantumForceTransformerRecipePool implements IRecipePool {

    @Override
    public void loadRecipesCompleteInit() {
        CORE.RA.addQuantumTransformerRecipe(
            new ItemStack[] { Materials.Calcium.getDust(32), VA_Materials.Astrium.get(OrePrefixes.dust, 32),
                ItemUtils.getSimpleStack(GenericChem.mRawIntelligenceCatalyst, 0) },
            new FluidStack[] {},
            new FluidStack[] { Materials.GrowthMediumRaw.getFluid(1000 * 1024),
                Materials.GrowthMediumSterilized.getFluid(1000 * 512) },
            new ItemStack[] { GT_Utility.copyAmountUnsafe(64 * 48, ItemList.Circuit_Chip_Stemcell.get(1)) },
            new int[] { 3333, 3333, 3333 },
            16 * SECONDS,
            (int) TierEU.RECIPE_UEV,
            3);
    }
}
