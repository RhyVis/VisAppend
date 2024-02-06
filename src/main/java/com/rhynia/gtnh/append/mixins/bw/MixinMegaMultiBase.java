package com.rhynia.gtnh.append.mixins.bw;

import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("SpellCheckingInspection")
@Pseudo
@Mixin(
    targets = "com.github.bartimaeusnek.bartworks.common.tileentities.multis.mega.GT_TileEntity_MegaMultiBlockBase$StructureElementAirNoHint",
    priority = 2000,
    remap = false)
public class MixinMegaMultiBase<T> {

    /**
     * This attempts to remove all air checks depending on StructureElementAirNoHint
     */
    @Inject(method = "check", at = @At("HEAD"), cancellable = true)
    private void bh$injectAirCheck(T o, World world, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
