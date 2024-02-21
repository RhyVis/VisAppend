package com.rhynia.gtnh.append.mixins.gtpp;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.processing.GregtechMetaTileEntity_IndustrialDehydrator;

@Mixin(value = GregtechMetaTileEntity_IndustrialDehydrator.class, priority = 2000, remap = false)
public class MixinIndustrialDehydrator {

    @ModifyConstant(method = "createProcessingLogic", constant = @Constant(floatValue = 1F / 2.2F))
    private float va$speed(float f) {
        return 1F / 16F;
    }

    @ModifyConstant(method = "createProcessingLogic", constant = @Constant(floatValue = 0.5F))
    private float va$eu(float f) {
        return 1F / 12F;
    }

    @ModifyConstant(method = "getMaxParallelRecipes", constant = @Constant(intValue = 4))
    private int va$parallel(int i) {
        return 32768;
    }
}
