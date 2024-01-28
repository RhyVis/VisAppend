package com.rhynia.gtnh.append.api.util.enums;

import java.util.Locale;

import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.common.Loader;

/**
 * This is an addon of Mods enum in GT5U
 */
@SuppressWarnings("unused")
public enum VA_Mods {

    TwistSpaceTechnology(Names.TWIST_SPACE_TECHNOLOGY),;

    public static class Names {

        public static final String TWIST_SPACE_TECHNOLOGY = "TwistSpaceTechnology";

    }

    public final String ID;
    public final String resourceDomain;
    private Boolean modLoaded;

    VA_Mods(String ID) {
        this.ID = ID;
        this.resourceDomain = ID.toLowerCase(Locale.ENGLISH);
    }

    public boolean isModLoaded() {
        if (this.modLoaded == null) {
            this.modLoaded = Loader.isModLoaded(ID);
        }
        return this.modLoaded;
    }

    public String getResourcePath(String... path) {
        return this.getResourceLocation(path)
            .toString();
    }

    public ResourceLocation getResourceLocation(String... path) {
        return new ResourceLocation(this.resourceDomain, String.join("/", path));
    }
}
