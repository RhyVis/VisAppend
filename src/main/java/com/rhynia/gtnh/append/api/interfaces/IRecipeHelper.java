package com.rhynia.gtnh.append.api.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface IRecipeHelper {

    ItemStack getItemStack(int aAmount);

    FluidStack getFluidStack(int aAmount);
}
