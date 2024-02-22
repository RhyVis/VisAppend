package com.rhynia.gtnh.append.api.process.itemUtil;

import net.minecraft.item.ItemStack;

@SuppressWarnings("unused")
public class ItemStackLong {

    protected ItemStack originalStack;
    protected long stackSize;

    public ItemStackLong(ItemStack stack, long amount) {
        originalStack = stack;
        stackSize = amount;
    }

    public long getStackSize() {
        return this.stackSize;
    }
}
