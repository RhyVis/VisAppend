package com.rhynia.gtnh.append.api.util.recipeHelper;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import com.rhynia.gtnh.append.api.util.IRecipeHelper;
import com.rhynia.gtnh.append.common.VAItemList;

import gregtech.api.enums.Materials;
import gtPlusPlus.core.material.ALLOY;
import gtPlusPlus.core.material.MISC_MATERIALS;

public enum SolderMaterial implements IRecipeHelper {

    SolderingAlloy,
    IndaAlloy,
    MutatedLivingAlloy;

    @Override
    public ItemStack getWrapped(int aAmount) {
        return VAItemList.Test.get(1);
    }

    /**
     * Get a FluidStack of Solder
     *
     * @param aAmount The amount of FluidStack
     * @author Rhynia
     * @since 0.6.17-pre
     */
    @Override
    public FluidStack getFluidStack(int aAmount) {
        if (this.ordinal() == 0) return Materials.SolderingAlloy.getMolten(aAmount);
        if (this.ordinal() == 1) return ALLOY.INDALLOY_140.getFluidStack(aAmount);
        if (this.ordinal() == 2) return MISC_MATERIALS.MUTATED_LIVING_SOLDER.getFluidStack(aAmount);
        else {
            return Materials.Water.getFluid(1);
        }
    }

}
