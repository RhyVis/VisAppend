package com.rhynia.gtnh.append.mixins.gtpp;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import gregtech.api.logic.ProcessingLogic;
import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.production.GregtechMTE_ElementalDuplicator;

@Mixin(value = GregtechMTE_ElementalDuplicator.class, priority = 2000, remap = false)
public class MixinElementalDuplicator {

    /**
     * Increase speed to 300% (+200%).
     *
     * @since 1.0.2
     */
    @ModifyConstant(method = "createProcessingLogic", constant = @Constant(floatValue = 1F / 2F))
    private float bh$speedModify(float c) {
        return 1F / 3F;
    }

    /**
     * Decrease energy consumption to 50%.
     *
     * @since 1.0.7
     */
    @ModifyReturnValue(method = "createProcessingLogic", at = @At("RETURN"))
    private ProcessingLogic bh$injectEUModifier(ProcessingLogic o) {
        return o.setEuModifier(1.0F / 2.0F);
    }

    /**
     * Modify parallel limit to 32 each voltage.
     *
     * @since 1.0.2
     */
    @ModifyReturnValue(method = "getMaxParallelRecipes", at = @At("RETURN"))
    private int bh$getMaxParallelRecipes(int o) {
        return o * 4;
    }

}
