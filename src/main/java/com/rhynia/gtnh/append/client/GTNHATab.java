package com.rhynia.gtnh.append.client;

import com.rhynia.gtnh.append.common.item.ItemBasic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import static com.rhynia.gtnh.append.util.UtilTextHandler.texter;

public class GTNHATab {
    /**
     * Creative Tab for MetaItem01
     */
    public static final CreativeTabs tabMetaItem01 = new CreativeTabs(
        texter("GTCM Meta Items 1", "itemGroup.GTCM Meta Items 1")) {

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
        texter("GTCM Meta Blocks 1", "itemGroup.GTCM Meta Blocks 1")) {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return ItemBasic.MetaItem01;
        }
    };

    /**
     * Creative Tab for MetaBlock01
     */
    public static final CreativeTabs tabGTCMGeneralTab = new CreativeTabs(texter("GTCM", "itemGroup.GTCM")) {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return ItemBasic.MetaItem01;
        }
    };
}
