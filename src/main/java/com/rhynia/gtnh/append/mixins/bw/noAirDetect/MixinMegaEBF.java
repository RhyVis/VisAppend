package com.rhynia.gtnh.append.mixins.bw.noAirDetect;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;

import java.util.Arrays;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.bartimaeusnek.bartworks.common.tileentities.multis.mega.GT_TileEntity_MegaBlastFurnace;

@Deprecated
@Mixin(value = GT_TileEntity_MegaBlastFurnace.class, priority = 2000, remap = false)
public class MixinMegaEBF {

    /**
     * Replace shape with no air limit
     */
    @SuppressWarnings("SpellCheckingInspection")
    @Inject(method = "createShape", at = @At("HEAD"), cancellable = true)
    private static void bh$injectStructure(CallbackInfoReturnable<String[][]> cir) {
        String[][] r = new String[20][];

        r[0] = new String[15];
        String topCasing = "ttttttttttttttt";
        String middleTopCasing = "tttttttmttttttt";
        r[0][0] = topCasing;
        for (int i = 1; i < 15; i++) {
            r[0][i] = topCasing;
        }
        r[0][7] = middleTopCasing;

        r[1] = new String[15];
        String allGlass = "ggggggggggggggg";
        String allCoil = "gCCCCCCCCCCCCCg";
        String middleLine = "gC           Cg";
        r[1][0] = allGlass;
        r[1][1] = allCoil;
        r[1][13] = allCoil;
        r[1][14] = allGlass;
        for (int i = 2; i < 13; i++) {
            r[1][i] = middleLine;
        }
        for (int i = 2; i < 19; i++) {
            r[i] = r[1];
        }
        String bottomCasing = "bbbbbbbbbbbbbbb";
        r[19] = new String[15];
        for (int i = 0; i < 15; i++) {
            r[19][i] = bottomCasing;
        }

        r[17] = Arrays.copyOf(r[17], r[17].length);
        r[17][0] = "ggggggg~ggggggg";

        cir.setReturnValue(transpose(r));
    }
}
