package com.rhynia.gtnh.append.api.enums.refHelper;

import static gregtech.api.enums.Mods.EternalSingularity;
import static gregtech.api.enums.Mods.GTPlusPlus;
import static gregtech.api.enums.Mods.GoodGenerator;
import static gregtech.api.enums.Mods.KekzTech;

import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.api.util.MathHelper;

import gregtech.api.util.GT_ModHandler;

@SuppressWarnings({ "unused", "SpellCheckingInspection" })
public class BasicRef {

    public static ItemStack getSingularity(int amount) {
        return GT_ModHandler.getModItem(EternalSingularity.ID, "eternal_singularity", amount);
    }

    public static ItemStack getSingularityCatalyst() {
        return GT_ModHandler.getModItem(EternalSingularity.ID, "eternal_singularity", 0);
    }

    public static ItemStack getFluidCore(int tier, int amount) {
        int tierMeta = MathHelper.clampVal(tier, 1, 10) - 1;
        return GT_ModHandler.getModItem(GoodGenerator.ID, "fluidCore", amount, tierMeta);
    }

    public static ItemStack getYOTCell(int tier, int amount) {
        int tierMeta = MathHelper.clampVal(tier, 1, 10) - 1;
        return GT_ModHandler.getModItem(GoodGenerator.ID, "yottaFluidTankCells", amount, tierMeta);
    }

    public static ItemStack getTFFTCell(int tier, int amount) {
        int tierMeta = MathHelper.clampVal(tier, 1, 10);
        return GT_ModHandler.getModItem(KekzTech.ID, "kekztech_tfftstoragefield_block", amount, tierMeta);
    }

    public static ItemStack getQuantumAnomaly(int amount) {
        return GT_ModHandler.getModItem(GTPlusPlus.ID, "MU-metaitem.01", amount, 32105);
    }

    public static ItemStack getFusionMatrixCatalyst() {
        return GT_ModHandler.getModItem(GTPlusPlus.ID, "MU-metaitem.01", 0, 32100);
    }
}
