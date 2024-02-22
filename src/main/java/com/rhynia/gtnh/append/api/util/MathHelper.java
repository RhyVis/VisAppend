package com.rhynia.gtnh.append.api.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import gregtech.api.metatileentity.MetaTileEntity;

@SuppressWarnings("unused")
public class MathHelper {

    private static final double LOG_2 = Math.log(2);

    public static int safeInt(long number, int margin) {
        return number > (long) (Integer.MAX_VALUE - margin) ? Integer.MAX_VALUE - margin : (int) number;
    }

    @Contract("null -> null")
    public static ItemStack[] sortNoNullArray(ItemStack... itemStacks) {
        if (itemStacks == null) return null;
        List<ItemStack> list = new ArrayList<>();
        for (ItemStack itemStack : itemStacks) {
            if (itemStack == null) continue;
            list.add(itemStack);
        }
        if (list.isEmpty()) return new ItemStack[0];
        return list.toArray(new ItemStack[0]);
    }

    @Contract("null -> null")
    public static FluidStack[] sortNoNullArray(FluidStack... fluidStacks) {
        if (fluidStacks == null) return null;
        List<FluidStack> list = new ArrayList<>();
        for (FluidStack fluidStack : fluidStacks) {
            if (fluidStack == null) continue;
            list.add(fluidStack);
        }
        if (list.isEmpty()) return new FluidStack[0];
        return list.toArray(new FluidStack[0]);
    }

    @Contract("null -> null")
    public static Object[] sortNoNullArray(Object... objects) {
        if (objects == null) return null;
        List<Object> list = new ArrayList<>();
        for (Object object : objects) {
            if (object == null) continue;
            list.add(object);
        }
        if (list.isEmpty()) return new Object[0];
        return list.toArray(new Object[0]);
    }

    @Contract("_ -> param1")
    public static <T extends Collection<E>, E extends MetaTileEntity> @NotNull T filterValidMTEs(T metaTileEntities) {
        metaTileEntities.removeIf(mte -> mte == null || !mte.isValid());
        return metaTileEntities;
    }

    @SuppressWarnings("unchecked")
    @SafeVarargs
    public static <T> T[] mergeArrays(@NotNull T[]... arrays) {
        List<T> totals = new ArrayList<>();
        for (T[] array : arrays) {
            totals.addAll(Arrays.asList(array));
        }
        return (T[]) totals.toArray(new Object[0]);
    }

    public static int clampInt(int process, int min, int max) {
        return process < min ? min : (Math.min(process, max));
    }

    public static long clampLong(long process, long min, long max) {
        return process < min ? min : (Math.min(process, max));
    }

    public static int min(int... values) {
        Arrays.sort(values);
        return values[0];
    }

    public static int max(int... values) {
        Arrays.sort(values);
        return values[values.length - 1];
    }

    public static long min(long... values) {
        Arrays.sort(values);
        return values[0];
    }

    public static long max(long... values) {
        Arrays.sort(values);
        return values[values.length - 1];
    }

    public static double calculatePowerTier(double voltage) {
        return 1 + Math.max(0, (Math.log(voltage) / LOG_2) - 5) / 2;
    }
}
