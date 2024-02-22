package com.rhynia.gtnh.append.api.process.itemUtil;

import java.util.Objects;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

import org.jetbrains.annotations.Nullable;

import gregtech.api.util.GT_Utility.ItemId;

@SuppressWarnings("unused")
public class VA_ItemID extends ItemId {

    // region Member Variables
    private Item item;
    private int metaData;
    private NBTTagCompound nbt;
    // endregion

    // region Class Constructors
    public VA_ItemID(Item item, int metaData, NBTTagCompound nbt) {
        this.item = item;
        this.metaData = metaData;
        this.nbt = nbt;
    }

    public VA_ItemID(Item item, int metaData) {
        this.item = item;
        this.metaData = metaData;
    }

    public VA_ItemID(Item item) {
        this.item = item;
        this.metaData = 0;
    }

    public VA_ItemID() {}
    // endregion

    // region Static Methods
    public static VA_ItemID create(ItemStack itemStack) {
        return new VA_ItemID(itemStack.getItem(), itemStack.getItemDamage(), itemStack.getTagCompound());
    }

    public static VA_ItemID createNoNBT(ItemStack itemStack) {
        return new VA_ItemID(itemStack.getItem(), itemStack.getItemDamage());
    }

    public static VA_ItemID createAsWildcard(ItemStack itemStack) {
        return new VA_ItemID(itemStack.getItem(), OreDictionary.WILDCARD_VALUE);
    }
    // endregion

    // region Special Methods
    public ItemStack getItemStack() {
        return new ItemStack(item, 1, metaData);
    }

    public ItemStack getItemStack(int amount) {
        return new ItemStack(item, amount, metaData);
    }

    public ItemStack getItemStackWithNBT() {
        ItemStack itemStack = new ItemStack(item, 1, metaData);
        itemStack.setTagCompound(nbt);
        return itemStack;
    }

    public ItemStack getItemStackWithNBT(int amount) {
        ItemStack itemStack = new ItemStack(item, amount, metaData);
        itemStack.setTagCompound(nbt);
        return itemStack;
    }

    // endregion

    // region General Methods
    public boolean isWildcard() {
        return this.metaData == OreDictionary.WILDCARD_VALUE;
    }

    public VA_ItemID setItem(Item item) {
        this.item = item;
        return this;
    }

    public VA_ItemID setMetaData(int metaData) {
        this.metaData = metaData;
        return this;
    }

    public VA_ItemID setNbt(NBTTagCompound nbt) {
        this.nbt = nbt;
        return this;
    }

    @Override
    protected Item item() {
        return item;
    }

    @Override
    protected int metaData() {
        return metaData;
    }

    @Nullable
    @Override
    protected NBTTagCompound nbt() {
        return nbt;
    }

    @Nullable
    protected NBTTagCompound getNBT() {
        return nbt;
    }

    protected Item getItem() {
        return item;
    }

    protected int getMetaData() {
        return metaData;
    }

    public boolean equalItemStack(ItemStack itemStack) {
        return this.equals(isWildcard() ? createAsWildcard(itemStack) : createNoNBT(itemStack));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VA_ItemID vaItemID)) {
            return false;
        }
        return metaData == vaItemID.metaData && Objects.equals(item, vaItemID.item)
            && Objects.equals(nbt, vaItemID.nbt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, metaData, nbt);
    }
}
