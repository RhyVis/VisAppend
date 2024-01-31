package com.rhynia.gtnh.append.mixins.gtpp;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gtPlusPlus.xmod.gregtech.common.helpers.TreeFarmHelper;
import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.production.GregtechMetaTileEntityTreeFarm;

@Mixin(value = GregtechMetaTileEntityTreeFarm.class, priority = 2000, remap = false)
public class MixinTreeFarm {

    /**
     * Given that this is a multiplier used in final output calculation, we give exact number as boost
     */
    @Inject(method = "getSawBoost", at = @At("HEAD"), cancellable = true)
    private void bh$allSawBoost(TreeFarmHelper.SAWTOOL sawType, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(32);
    }
}
