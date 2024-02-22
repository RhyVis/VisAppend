package com.rhynia.gtnh.append.api.enums.refHelper;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import com.rhynia.gtnh.append.api.interfaces.IRecipeHelper;
import com.rhynia.gtnh.append.common.VA_ItemList;

import gregtech.api.enums.Materials;
import gtPlusPlus.core.material.ALLOY;
import gtPlusPlus.core.material.MISC_MATERIALS;

public enum SolderMaterial implements IRecipeHelper {

    SolderingAlloy,
    IndaAlloy,
    MutatedLivingAlloy;

    @Override
    public ItemStack getItemStack(int aAmount) {
        return VA_ItemList.Test01.get(1);
    }

    /**
     * Get a FluidStack of Solder
     *
     * @param aAmount The amount of FluidStack
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
