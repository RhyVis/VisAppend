package com.rhynia.gtnh.append.mixins.gtpp;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import gregtech.api.logic.ProcessingLogic;
import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.processing.GregtechMetaTileEntity_IndustrialWashPlant;

@Mixin(value = GregtechMetaTileEntity_IndustrialWashPlant.class, priority = 2000, remap = false)
public class MixinIndustrialWashPlant {

    @ModifyConstant(method = "createProcessingLogic", constant = @Constant(floatValue = 1F / 5F))
    private float bh$speedModify(float c) {
        return 1F / 10F;
    }

    @ModifyReturnValue(method = "createProcessingLogic", at = @At("RETURN"))
    private ProcessingLogic bh$injectEUModifier(ProcessingLogic o) {
        return o.setEuModifier(1.0F / 2.0F);
    }

    @ModifyReturnValue(method = "getMaxParallelRecipes", at = @At("RETURN"))
    private int bh$getMaxParallelRecipes(int o) {
        return o * 4;
    }
}
