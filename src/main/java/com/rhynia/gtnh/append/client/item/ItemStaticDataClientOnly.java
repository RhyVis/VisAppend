package com.rhynia.gtnh.append.client.item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemStaticDataClientOnly {

    @SideOnly(Side.CLIENT)
    public static Map<Integer, IIcon> iconsMap01 = new HashMap<>();
    @SideOnly(Side.CLIENT)
    public static Map<Integer, IIcon> iconsMap02 = new HashMap<>();

    /**
     * @param aMetaName The Name of the Icon.
     * @return Return the Path of the Icon.
     */
    @SideOnly(Side.CLIENT)
    public static String getIconPath01(String aMetaName) {
        return "append:MetaItem01/" + aMetaName;
    }

    @SideOnly(Side.CLIENT)
    public static String getIconPath02(String aMetaName) {
        return "append:MetaItem02/" + aMetaName;
    }
}
