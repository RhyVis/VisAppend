package com.rhynia.gtnh.append.common.item.container;

import static net.minecraft.client.gui.GuiScreen.isShiftKeyDown;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemUltimate extends Item {

    public ItemUltimate(CreativeTabs aCreativeTabs) {
        super();
        this.setCreativeTab(aCreativeTabs);
        this.setUnlocalizedName("Ultimate");
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
                EnumChatFormatting.BLUE + "你"
                    + EnumChatFormatting.AQUA
                    + "会"
                    + EnumChatFormatting.RED
                    + "更进一步"
                    + EnumChatFormatting.BLUE
                    + "吗？");
        } else {
            toolTip.add("你可能已经触及这个游戏的最终阶段了.");
        }
    }

}
