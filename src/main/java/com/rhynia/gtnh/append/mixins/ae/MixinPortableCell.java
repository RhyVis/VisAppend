package com.rhynia.gtnh.append.mixins.ae;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import appeng.items.tools.powered.ToolPortableCell;

@Mixin(value = ToolPortableCell.class, priority = 2000, remap = false)
public class MixinPortableCell {

    @Inject(method = "getBytes", at = @At("HEAD"), cancellable = true)
    private void getBytes(ItemStack cellItem, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(32768);
    }
}
