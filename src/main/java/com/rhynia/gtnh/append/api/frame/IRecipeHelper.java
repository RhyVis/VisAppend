package com.rhynia.gtnh.append.api.frame;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface IRecipeHelper {

    ItemStack getItemStack(int aAmount);

    FluidStack getFluidStack(int aAmount);
}
