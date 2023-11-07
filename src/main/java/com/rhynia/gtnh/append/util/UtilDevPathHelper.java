package com.rhynia.gtnh.append.util;

import com.rhynia.gtnh.append.GTNHAppend;

import java.net.URL;

public class UtilDevPathHelper {
    /**
     * Auto init the workspace 'resources' folder's path.
     *
     * @param isInDevMode
     */
    public static void initResourceAbsolutePath(boolean isInDevMode) {
        if (isInDevMode) {
            /* Get the URL(Path) of the mod when RUN. */
            URL tempUrl = GTNHAppend.class.getResource("");
            String tempString;
            if (tempUrl != null) {
                /* Cut the String. */
                tempString = tempUrl.getFile()
                    .substring(
                        6,
                        tempUrl.getFile()
                            .indexOf("build"));
                GTNHAppend.DevResource = tempString + "src/main/resources";
            }
        }
    }
}
