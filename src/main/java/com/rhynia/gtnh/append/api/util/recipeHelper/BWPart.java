package com.rhynia.gtnh.append.api.util.recipeHelper;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import com.rhynia.gtnh.append.api.util.IRecipeHelper;
import com.rhynia.gtnh.append.common.VAItemList;

import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;

@SuppressWarnings("unused")
public enum BWPart implements IRecipeHelper {

    LivingBioChip,
    LivingCrystalChip,
    Opt_Ram,
    Opt_CPUCasing,
    Opt_CPU,
    Opt_Board,
    Opt_Card,
    Opt_Inductor,
    Adv_Inductor,
    Opt_Capacitor,
    Opt_Transistor,
    Opt_Diode,
    Opt_Resistor,
    Bio_Cell,
    Stem_Cell,
    Bio_Processor,
    Stem_Processor,
    AdvCrystal_Raw,
    AdvCrystal_SOC,
    Crystal_CPU,
    Part_QBit,
    Part_NanoCPU,
    Part_IC_Q,
    Part_IC_P,
    Part_IC_N,
    Part_IC_L,
    Part_IC_UL,
    Part_IC_UH,
    Part_IC_H,
    Part_SSOC,
    Part_IC,
    Part_ASOC,
    Part_SOC,
    Part_CPU,
    Part_NOR,
    Part_NAND,
    Part_RAM,
    Part_ILC,
    Adv_Capacitor,
    Adv_Transistor,
    Adv_Diode,
    Adv_Resistor,
    Basic_Capacitor,
    Basic_Transistor,
    Basic_Diode,
    Basic_Inductor,
    Basic_Resistor,
    Bio_Board,
    Bio_Board_Raw,
    Plastic_Board,
    Plastic_Board_Raw,
    Wetware_Board,
    Wetware_Board_Raw,
    Elite_Board_Raw_Useless,
    Elite_Board,
    Delicate_Board,
    Delicate_Board_Raw,
    Adv_Board,
    Adv_Board_Raw_Useless,
    Good_Board,
    Good_Board_Raw,
    Basic_Board,
    Basic_Board_Raw_Useless,
    Lapotron,
    Crystal_Raw,
    Elite_Board_Raw,
    Adv_Board_Raw,
    Basic_Board_Raw;

    // region Wrapped Parts
    /**
     * Get a ItemStack of BW generated wrapped parts
     * with stack larger than 64 are also supported
     *
     * @param aAmount The amount of ItemStack
     * @since 0.6.13-pre
     */
    @Override
    public ItemStack getItemStack(int aAmount) {
        final int WrapCircuit_Offset = 32699;
        if (aAmount > 64) return GT_Utility.copyAmountUnsafe(
            aAmount,
            GT_ModHandler.getModItem(
                "bartworks",
                "gt.bwMetaGeneratedItem0",
                1,
                this.ordinal() + WrapCircuit_Offset,
                VAItemList.Test.get(1)));
        else return GT_ModHandler.getModItem(
            "bartworks",
            "gt.bwMetaGeneratedItem0",
            aAmount,
            this.ordinal() + WrapCircuit_Offset,
            VAItemList.Test.get(1));
    }

    @Override
    public FluidStack getFluidStack(int aAmount) {
        return Materials.Water.getFluid(1);
    }
    // endregion

}
