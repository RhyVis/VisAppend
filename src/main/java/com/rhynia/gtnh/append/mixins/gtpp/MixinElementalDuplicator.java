package com.rhynia.gtnh.append.mixins.gtpp;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import gregtech.api.logic.ProcessingLogic;
import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.production.GregtechMTE_ElementalDuplicator;

@Mixin(value = GregtechMTE_ElementalDuplicator.class, priority = 2000, remap = false)
public class MixinElementalDuplicator {

    @ModifyConstant(method = "createProcessingLogic", constant = @Constant(floatValue = 1F / 2F))
    private float bh$speedModify(float c) {
        return 1F / 3F;
    }

    @Inject(method = "createProcessingLogic", at = @At("RETURN"), cancellable = true)
    private void bh$injectEUModifier(CallbackInfoReturnable<ProcessingLogic> cir) {
        cir.setReturnValue(
            cir.getReturnValue()
                .setEuModifier(0.75F));
    }

    @ModifyReturnValue(method = "getMaxParallelRecipes", at = @At("RETURN"))
    private int bh$getMaxParallelRecipes(int o) {
        return o * 4;
    }

}
