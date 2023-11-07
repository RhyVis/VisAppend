package com.rhynia.gtnh.append.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class ItemStaticDataClientOnly {
    @SideOnly(Side.CLIENT)
    public static Map<Integer, IIcon> iconsMap01 = new HashMap<>();

    /**
     * @param aMetaName The Name of the Icon.
     * @return Return the Path of the Icon.
     */
    @SideOnly(Side.CLIENT)
    public static String getIconPath01(String aMetaName) {
        return "gtnhcommunitymod:MetaItem01/" + aMetaName;
    }
}
