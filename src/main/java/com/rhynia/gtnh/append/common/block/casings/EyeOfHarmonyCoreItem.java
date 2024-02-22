package com.rhynia.gtnh.append.common.block.casings;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import com.rhynia.gtnh.append.client.VA_Tab;
import com.rhynia.gtnh.append.common.block.base.BlockBaseItem01;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EyeOfHarmonyCoreItem extends BlockBaseItem01 {

    public EyeOfHarmonyCoreItem(Block aBlock) {
        super(aBlock);
        this.setCreativeTab(VA_Tab.tabGeneralTab);
    }

    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings({ "unchecked" })
    public void addInformation(ItemStack aItemStack, EntityPlayer p_77624_2_, List theTooltipsList,
        boolean p_77624_4_) {
        if (null == aItemStack) return;
        theTooltipsList.add(
            EnumChatFormatting.BOLD + ""
                + EnumChatFormatting.AQUA
                + "允许执行鸿蒙之眼T"
                + (aItemStack.getItemDamage() + 1)
                + "配方");
        theTooltipsList.add(mNoMobsToolTip);
        theTooltipsList.add(mNoTileEntityToolTip);
    }
}
