package com.rhynia.gtnh.append.util.recipeHelper;

import net.minecraftforge.fluids.FluidStack;

import gregtech.api.enums.Materials;
import gtPlusPlus.core.material.ALLOY;
import gtPlusPlus.core.material.MISC_MATERIALS;

public class Solder {

    /**
     * Get a FluidStack of Solder
     * 
     * @param aTier   1 for SolderingAlloy, 2 for IndAlloy140, 3 for Mutated Living Solder
     * @param aAmount The amount of FluidStack
     * @author Rhynia
     * @since 0.6.17-pre
     */
    public static FluidStack getSolder(int aTier, int aAmount) {
        if (aTier == 1) return Materials.SolderingAlloy.getMolten(aAmount);
        if (aTier == 2) return ALLOY.INDALLOY_140.getFluidStack(aAmount);
        if (aTier == 3) return MISC_MATERIALS.MUTATED_LIVING_SOLDER.getFluidStack(aAmount);
        else {
            return Materials.Water.getFluid(1);
        }
    }
}
