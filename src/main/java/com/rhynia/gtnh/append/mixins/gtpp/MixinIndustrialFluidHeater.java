package com.rhynia.gtnh.append.mixins.gtpp;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.processing.GregtechMetaTileEntity_IndustrialFluidHeater;

@Mixin(value = GregtechMetaTileEntity_IndustrialFluidHeater.class, priority = 2000, remap = false)
public class MixinIndustrialFluidHeater {

    @ModifyConstant(method = "createProcessingLogic", constant = @Constant(floatValue = 1F / 2.2F))
    private float bh$speedModify(float c) {
        return 1F / 8F;
    }

    @ModifyConstant(method = "createProcessingLogic", constant = @Constant(floatValue = 0.9F))
    private float bh$EUModifier(float c) {
        return 0.35F;
    }

    @ModifyReturnValue(method = "getMaxParallelRecipes", at = @At("RETURN"))
    private int bh$getMaxParallelRecipes(int o) {
        return o * 6;
    }
}
