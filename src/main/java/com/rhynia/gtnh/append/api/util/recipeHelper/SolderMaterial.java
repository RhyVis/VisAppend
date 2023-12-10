package com.rhynia.gtnh.append.api.util.recipeHelper;

import static com.rhynia.gtnh.append.api.util.recipeHelper.SolderMaterial.Solder.IndaAlloy;
import static com.rhynia.gtnh.append.api.util.recipeHelper.SolderMaterial.Solder.MutatedLivingAlloy;
import static com.rhynia.gtnh.append.api.util.recipeHelper.SolderMaterial.Solder.SolderingAlloy;

import net.minecraftforge.fluids.FluidStack;

import gregtech.api.enums.Materials;
import gtPlusPlus.core.material.ALLOY;
import gtPlusPlus.core.material.MISC_MATERIALS;

public class SolderMaterial {

    /**
     * Get a FluidStack of Solder
     *
     * @param aTier   SolderingAlloy, IndaAlloy, MutatedLivingAlloy
     * @param aAmount The amount of FluidStack
     * @author Rhynia
     * @since 0.6.17-pre
     */
    public static FluidStack getSolder(Solder aTier, int aAmount) {
        if (aTier == SolderingAlloy) return Materials.SolderingAlloy.getMolten(aAmount);
        if (aTier == IndaAlloy) return ALLOY.INDALLOY_140.getFluidStack(aAmount);
        if (aTier == MutatedLivingAlloy) return MISC_MATERIALS.MUTATED_LIVING_SOLDER.getFluidStack(aAmount);
        else {
            return Materials.Water.getFluid(1);
        }
    }

    public enum Solder {
        SolderingAlloy,
        IndaAlloy,
        MutatedLivingAlloy
    }
}
