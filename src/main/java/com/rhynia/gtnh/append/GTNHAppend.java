package com.rhynia.gtnh.append;

import com.rhynia.gtnh.append.util.UtilDevPathHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    /**
     * If you need send a message to the Log, call {@link GTNHAppend#LOG#info(String message)} .
     */
    public static final Logger LOG = LogManager.getLogger(Tags.MODID);
    @SidedProxy(clientSide = "com.rhynia.gtnh.append.ClientProxy", serverSide = "com.rhynia.gtnh.append.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        UtilDevPathHelper.initResourceAbsolutePath(isInDevMode);
        TextHandler.initLangMap(isInDevMode);

        proxy.preInit(event);
    }
}
