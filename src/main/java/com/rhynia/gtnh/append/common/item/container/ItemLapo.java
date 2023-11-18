package com.rhynia.gtnh.append.common.item.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

import static net.minecraft.client.gui.GuiScreen.isShiftKeyDown;

public class ItemLapo extends Item {

    public ItemLapo(CreativeTabs aCreativeTabs) {
        super();
        this.setCreativeTab(aCreativeTabs);
        this.setUnlocalizedName("LapoMatrix");
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
        if (isShiftKeyDown()) {
            toolTip.add(
                EnumChatFormatting.DARK_AQUA + "能量"
                    + EnumChatFormatting.RED
                    + "聚集"
                    + EnumChatFormatting.DARK_AQUA
                    + "于一小块芯片中.");
        } else {
            toolTip.add("高级"+EnumChatFormatting.DARK_AQUA+"兰波顿"+EnumChatFormatting.GRAY+"矩阵.");
        }
    }

}
