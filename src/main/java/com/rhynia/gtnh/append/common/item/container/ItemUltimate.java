package com.rhynia.gtnh.append.common.item.container;

import static com.rhynia.gtnh.append.util.UtilTextHandler.texter;
import static net.minecraft.client.gui.GuiScreen.isShiftKeyDown;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import com.rhynia.gtnh.append.util.UtilTextHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemUltimate extends Item {

    public String unlocalizedName;

    public ItemUltimate(String aName, String aMetaName, CreativeTabs aCreativeTabs) {
        super();
        this.setCreativeTab(aCreativeTabs);
        this.unlocalizedName = aMetaName;
        UtilTextHandler.texter(aName, this.unlocalizedName + ".name");
    }

    @Override
    public Item setUnlocalizedName(String aUnlocalizedName) {
        this.unlocalizedName = aUnlocalizedName;
        return this;
    }

    @Override
    public String getUnlocalizedName(ItemStack aItemStack) {
        return this.unlocalizedName;
    }

    @Override
    public String getUnlocalizedName() {
        return this.unlocalizedName;
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
                texter(
                    EnumChatFormatting.LIGHT_PURPLE + "You're stepping onto the highest edge of this game.",
                    "tooltips.ultimate.proof.line2"));
        } else {
            toolTip.add(texter("You're stepping onto the highest edge of this game.", "tooltips.ultimate.proof.line1"));
        }
    }

}
