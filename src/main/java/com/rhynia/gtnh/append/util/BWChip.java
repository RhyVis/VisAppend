package com.rhynia.gtnh.append.util;

import net.minecraft.item.ItemStack;

import gregtech.api.util.GT_ModHandler;

@SuppressWarnings("unused")
public class BWChip {

    // region 封装
    public static ItemStack getWrappedPart(int aIndex, int aAmount) {
        return GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", aAmount, aIndex);
    }

    private static final int WrapCircuit_Offset = 32699;
    public static final int LivingBioChip = WrapCircuit_Offset;
    public static final int LivingCrystalChip = WrapCircuit_Offset + 1;
    public static final int Opt_Ram = WrapCircuit_Offset + 2;
    public static final int Opt_CPUCasing = WrapCircuit_Offset + 3;
    public static final int Opt_CPU = WrapCircuit_Offset + 4;
    public static final int Opt_Board = WrapCircuit_Offset + 5;
    public static final int Opt_Card = WrapCircuit_Offset + 6;
    public static final int Opt_Inductor = WrapCircuit_Offset + 7;
    public static final int Adv_Inductor = WrapCircuit_Offset + 8;
    public static final int Opt_Capacitor = WrapCircuit_Offset + 9;
    public static final int Opt_Transistor = WrapCircuit_Offset + 10;
    public static final int Opt_Diode = WrapCircuit_Offset + 11;
    public static final int Opt_Resistor = WrapCircuit_Offset + 12;
    public static final int Bio_Cell = WrapCircuit_Offset + 13;
    public static final int Stem_Cell = WrapCircuit_Offset + 14;
    public static final int Bio_Processor = WrapCircuit_Offset + 15;
    public static final int Stem_Processor = WrapCircuit_Offset + 16;
    public static final int AdvCystal_Raw = WrapCircuit_Offset + 17;
    public static final int AdvCystal_SOC = WrapCircuit_Offset + 18;
    public static final int Crystal_CPU = WrapCircuit_Offset + 19;
    public static final int Part_QBit = WrapCircuit_Offset + 20;
    public static final int Part_NanoCPU = WrapCircuit_Offset + 21;
    public static final int Part_IC_Q = WrapCircuit_Offset + 22;
    public static final int Part_IC_P = WrapCircuit_Offset + 23;
    public static final int Part_IC_N = WrapCircuit_Offset + 24;
    public static final int Part_IC_L = WrapCircuit_Offset + 25;
    public static final int Part_IC_UL = WrapCircuit_Offset + 26;
    public static final int Part_IC_UH = WrapCircuit_Offset + 27;
    public static final int Part_IC_H = WrapCircuit_Offset + 28;
    public static final int Part_SSOC = WrapCircuit_Offset + 29;
    public static final int Part_IC = WrapCircuit_Offset + 30;
    public static final int Part_ASOC = WrapCircuit_Offset + 31;
    public static final int Part_SOC = WrapCircuit_Offset + 32;
    public static final int Part_CPU = WrapCircuit_Offset + 33;
    public static final int Part_NOR = WrapCircuit_Offset + 34;
    public static final int Part_NAND = WrapCircuit_Offset + 35;
    public static final int Part_RAM = WrapCircuit_Offset + 36;
    public static final int Part_ILC = WrapCircuit_Offset + 37;
    public static final int Adv_Capacitor = WrapCircuit_Offset + 38;
    public static final int Adv_Transistor = WrapCircuit_Offset + 39;
    public static final int Adv_Diode = WrapCircuit_Offset + 40;
    public static final int Adv_Resistor = WrapCircuit_Offset + 41;
    public static final int Basic_Capacitor = WrapCircuit_Offset + 42;
    public static final int Basic_Transistor = WrapCircuit_Offset + 43;
    public static final int Basic_Diode = WrapCircuit_Offset + 44;
    public static final int Basic_Inductor = WrapCircuit_Offset + 45;
    public static final int Basic_Resistor = WrapCircuit_Offset + 46;
    public static final int Bio_Board = WrapCircuit_Offset + 47;
    public static final int Bio_Board_Raw = WrapCircuit_Offset + 48;
    public static final int Plastic_Board = WrapCircuit_Offset + 49;
    public static final int Plastic_Board_Raw = WrapCircuit_Offset + 50;
    public static final int Wetware_Board = WrapCircuit_Offset + 51;
    public static final int Wetware_Board_Raw = WrapCircuit_Offset + 52;
    public static final int Elite_Board_Raw_Useless = WrapCircuit_Offset + 53;
    public static final int Elite_Board = WrapCircuit_Offset + 54;
    public static final int Delicate_Board = WrapCircuit_Offset + 55;
    public static final int Delicate_Board_Raw = WrapCircuit_Offset + 56;
    public static final int Adv_Board = WrapCircuit_Offset + 57;
    public static final int Adv_Board_Raw_Useless = WrapCircuit_Offset + 58;
    public static final int Good_Board = WrapCircuit_Offset + 59;
    public static final int Good_Board_Raw = WrapCircuit_Offset + 60;
    public static final int Basic_Board = WrapCircuit_Offset + 61;
    public static final int Basic_Board_Raw_Useless = WrapCircuit_Offset + 62;
    public static final int Lapotron = WrapCircuit_Offset + 63;
    public static final int Crystal_Raw = WrapCircuit_Offset + 64;
    public static final int Elite_Board_Raw = WrapCircuit_Offset + 65;
    public static final int Adv_Board_Raw = WrapCircuit_Offset + 66;
    public static final int Basic_Board_Raw = WrapCircuit_Offset + 67;
    // endregion

}
