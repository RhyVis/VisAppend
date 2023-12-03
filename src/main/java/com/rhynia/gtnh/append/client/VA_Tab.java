package com.rhynia.gtnh.append.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.rhynia.gtnh.append.common.item.ItemBasic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class VA_Tab {

    /**
     * Creative Tab for MetaItem01
     */
    public static final CreativeTabs tabMetaItem01 = new CreativeTabs("Append: Item") {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return ItemBasic.MetaItem01;
        }
    };

    /**
     * Creative Tab for MetaBlock01
     */
    public static final CreativeTabs tabMetaBlock01 = new CreativeTabs("Append: Block") {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return ItemBasic.MetaItem01;
        }
    };

    /**
     * Creative Tab for MetaBlock01
     */
    public static final CreativeTabs tabGeneralTab = new CreativeTabs("Append: Material") {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return ItemBasic.MetaItem01;
        }
    };
}
