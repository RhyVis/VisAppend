package com.rhynia.gtnh.append.common.item.container;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCrystalMatrix extends Item {

    public ItemCrystalMatrix(CreativeTabs aCreativeTabs) {
        super();
        this.setCreativeTab(aCreativeTabs);
        this.setUnlocalizedName("CrystalMatrix");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item aItem, CreativeTabs aCreativeTabs, List aList) {
        aList.add(new ItemStack(aItem, 1, 0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack itemStack, final EntityPlayer player, final List toolTip,
        final boolean advancedToolTips) {
        toolTip.add("已弃用!");
    }
}
