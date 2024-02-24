package com.rhynia.gtnh.append.api.process.itemUtil;

import net.minecraft.item.ItemStack;

@SuppressWarnings("unused")
public class ItemStackLong {

    protected ItemStack originalStack;
    protected long originalSize;

    public ItemStackLong(ItemStack stack, long amount) {
        originalStack = stack;
        originalSize = amount;
    }

    public ItemStackLong() {}

    public static ItemStackLong ISL = new ItemStackLong();

    public ItemStackLong of(ItemStack stack, long amount) {
        this.originalStack = stack;
        this.originalSize = amount;
        return this;
    }

    public ItemStack getOriginalStack() {
        return this.originalStack;
    }

    public long getStackSize() {
        return this.originalSize;
    }
}
