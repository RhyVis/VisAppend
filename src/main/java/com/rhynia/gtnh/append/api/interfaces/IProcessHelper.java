package com.rhynia.gtnh.append.api.interfaces;

import net.minecraftforge.fluids.Fluid;

@SuppressWarnings("unused")
public interface IProcessHelper {

    boolean consumeFluid(Fluid fluid, int amount);
}
