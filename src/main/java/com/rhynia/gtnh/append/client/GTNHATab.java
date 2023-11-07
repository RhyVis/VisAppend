package com.rhynia.gtnh.append.client;

import static com.rhynia.gtnh.append.util.UtilTextHandler.texter;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.rhynia.gtnh.append.common.item.ItemBasic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GTNHATab {

    /**
     * Creative Tab for MetaItem01
     */
    public static final CreativeTabs tabMetaItem01 = new CreativeTabs(
        texter("AppendMetaItems1", "itemGroup.AppendMetaItems1")) {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return ItemBasic.MetaItem01;
        }
    };

    /**
     * Creative Tab for MetaBlock01
     */
    public static final CreativeTabs tabMetaBlock01 = new CreativeTabs(
        texter("AppendMetaBlocks1", "itemGroup.AppendMetaBlocks1")) {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return ItemBasic.MetaItem01;
        }
    };

    /**
     * Creative Tab for MetaBlock01
     */
    public static final CreativeTabs tabGTCMGeneralTab = new CreativeTabs(texter("GTAppend", "itemGroup.GTAppend")) {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return ItemBasic.MetaItem01;
        }
    };
}
