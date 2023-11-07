package com.rhynia.gtnh.append;

import com.rhynia.gtnh.append.util.UtilDevPathHelper;
import com.rhynia.gtnh.append.util.UtilTextHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.7.10]")

public class GTNHAppend {

    /**
     * <li>The signal of whether in Development Mode.
     * <li>Keep care to set 'false' when dev complete.
     */
    public static final boolean isInDevMode = false;
    /**
     * The absolute Path of your workspace/resources folder.
     * It will be replaced by {@link UtilDevPathHelper#initResourceAbsolutePath(boolean)}.
     * If it not work correctly, please operate it manually and disable
     * the{@link UtilDevPathHelper#initResourceAbsolutePath(boolean)}.
     */
    public static String DevResource = "";
    public static final String MODID = Tags.MODID;
    public static final String MOD_ID = Tags.MODID;
    public static final String MOD_NAME = Tags.MODNAME;
    public static final String VERSION = Tags.VERSION;

    @SidedProxy(clientSide = "com.rhynia.gtnh.append.ClientProxy", serverSide = "com.rhynia.gtnh.append.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        UtilDevPathHelper.initResourceAbsolutePath(isInDevMode);
        UtilTextHandler.initLangMap(isInDevMode);

        proxy.preInit(event);
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        UtilTextHandler.serializeLangMap(isInDevMode);
    }
}
