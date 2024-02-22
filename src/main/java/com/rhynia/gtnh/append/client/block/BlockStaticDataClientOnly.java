package com.rhynia.gtnh.append.client.block;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStaticDataClientOnly {

    @SideOnly(Side.CLIENT)
    public static Map<Integer, IIcon> iconsBlockMap01 = new HashMap<>();
    @SideOnly(Side.CLIENT)
    public static Map<Integer, IIcon> iconsEyeOfHarmonyCoreMap = new HashMap<>();

}
