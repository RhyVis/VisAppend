package com.rhynia.gtnhextend;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
    modid = Tags.MODID,
    version = Tags.VERSION,
    name = Tags.MODNAME,
    dependencies = "required-after:IC2; " + "required-after:gregtech; " + "required-after:bartworks; ",
    acceptedMinecraftVersions = "[1.7.10]")

public class GTNHExtend {

    /**
     * <li>The signal of whether in Development Mode.
     * <li>Keep care to set 'false' when dev complete.
     */
    public static final boolean isInDevMode = false;

    /**
     * The absolute Path of your workspace/resources folder.
     * If it not work correctly, please operate it manually and disable
     */
    public static String DevResource = "";

    public static final String MODID = Tags.MODID;
    public static final String MOD_ID = Tags.MODID;
    public static final String MOD_NAME = Tags.MODNAME;
    public static final String VERSION = Tags.VERSION;

    @Mod.Instance
    public static GTNHExtend instance;

    @SidedProxy(clientSide = "com.rhynia.gtnhextend.ClientProxy", serverSide = "com.rhynia.gtnhextend.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
}
