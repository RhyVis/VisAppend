package com.rhynia.gtnh.append.mixins.gtpp;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import gregtech.api.logic.ProcessingLogic;
import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.production.GregtechMetaTileEntity_MassFabricator;

@Mixin(value = GregtechMetaTileEntity_MassFabricator.class, priority = 2000, remap = false)
public class MixinMassFabricator {

    @ModifyConstant(method = "createProcessingLogic", constant = @Constant(floatValue = 0.8F))
    private float bh$rEUModifier(float c) {
        return 1.0F / 10.0F;
    }

    @ModifyReturnValue(method = "createProcessingLogic", at = @At("RETURN"))
    private ProcessingLogic bh$injectSpeed(ProcessingLogic o) {
        return o.setSpeedBonus(1.0F / 4.0F);
    }

    @ModifyReturnValue(method = "getMaxParallelRecipes", at = @At("RETURN"))
    private int bh$rParallel(int o) {
        return o * 8;
    }
}
