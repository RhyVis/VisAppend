package com.rhynia.gtnh.append.api.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface IRecipeHelper {

    ItemStack getWrapped(int aAmount);

    FluidStack getFluidStack(int aAmount);
}
