package com.rhynia.gtnh.append.api.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import com.rhynia.gtnh.append.VisAppend;
import com.rhynia.gtnh.append.common.loader.VA_ItemList;

@SuppressWarnings("unused")
public class ItemHelper {

    @Contract("!null, _ -> new; null, _ -> fail")
    @NotNull
    public static ItemStack getItemStack(Item item, int amount) {
        if (item != null) {
            return new ItemStack(item, amount);
        } else {
            throw new IllegalArgumentException("Null item!");
        }
    }

    @Contract("!null, _, _ -> new; null, _, _ -> fail")
    @NotNull
    public static ItemStack getItemStack(Item item, int amount, int meta) {
        if (item != null) {
            return new ItemStack(item, amount, meta);
        } else {
            throw new IllegalArgumentException("Null item!");
        }
    }

    // region ItemStack
    public static boolean metaItemEqual(ItemStack a, ItemStack b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        return a.getItem() == b.getItem() && a.getItemDamage() == b.getItemDamage();
    }

    /**
     *
     * @param isa1 The ItemStack Array 1.
     * @param isa2 The ItemStack Array 2.
     * @return The elements of these two arrays are identical and in the same order.
     */
    public static boolean itemStackArrayEqualAbsolutely(ItemStack[] isa1, ItemStack[] isa2) {
        if (isa1.length != isa2.length) return false;
        for (int i = 0; i < isa1.length; i++) {
            if (!metaItemEqual(isa1[i], isa2[i])) return false;
            if (isa1[i].stackSize != isa2[i].stackSize) return false;
        }
        return true;
    }

    public static boolean itemStackArrayEqualFuzzy(ItemStack[] isa1, ItemStack[] isa2) {
        if (isa1.length != isa2.length) return false;
        for (ItemStack itemStack1 : isa1) {
            boolean flag = false;
            for (ItemStack itemStack2 : isa2) {
                if (metaItemEqual(itemStack1, itemStack2)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) return false;
        }
        return true;
    }

    public static ItemStack[] copyItemStackArray(ItemStack... array) {
        ItemStack[] result = new ItemStack[array.length];
        for (int i = 0; i < result.length; i++) {
            if (array[i] == null) continue;
            result[i] = array[i].copy();
        }
        return result;
    }

    public static ItemStack copyAmount(int aAmount, ItemStack aStack) {
        ItemStack rStack = aStack.copy();
        if (isStackInvalid(rStack)) return null;
        // if (aAmount > 64) aAmount = 64;
        else if (aAmount == -1) aAmount = 111;
        else if (aAmount < 0) aAmount = 0;
        rStack.stackSize = aAmount;
        return rStack;
    }

    public static boolean isStackValid(ItemStack aStack) {
        return (aStack != null) && aStack.getItem() != null && aStack.stackSize >= 0;
    }

    public static boolean isStackInvalid(ItemStack aStack) {
        return aStack == null || aStack.getItem() == null || aStack.stackSize < 0;
    }

    public static ItemStack setStackSize(ItemStack itemStack, int amount) {
        if (itemStack == null) return null;
        if (amount < 0) {
            VisAppend.LOG
                .info("Error! Trying to set a item stack size lower than zero! " + itemStack + " to amount " + amount);
            return itemStack;
        }
        itemStack.stackSize = amount;
        return itemStack;
    }

    public static boolean isAstralInfinityGem(ItemStack itemStack) {
        if (itemStack == null) return false;
        return itemStack.isItemEqual(VA_ItemList.AstriumInfinityGem.get(1));
    }

    public static boolean isAstralInfinityComplex(ItemStack itemStack) {
        if (itemStack == null) return false;
        return itemStack.isItemEqual(VA_ItemList.AstriumInfinityComplex.get(1));
    }

    public static boolean isCalibration(ItemStack itemStack) {
        if (itemStack == null) return false;
        return itemStack.isItemEqual(VA_ItemList.Calibration.get(1));
    }

}
