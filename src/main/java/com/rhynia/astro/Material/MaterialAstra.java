package com.rhynia.astro.Material;

import gregtech.api.enums.Dyes;
import gregtech.api.enums.Materials;
import gregtech.api.enums.SubTag;
import gregtech.api.enums.TextureSet;
import gregtech.api.interfaces.IColorModulationContainer;
import gregtech.api.interfaces.ISubTagContainer;

public class MaterialAstra implements IColorModulationContainer, ISubTagContainer {

    public static Materials Astro = new Materials(
        250,
        TextureSet.SET_SHINY,
        1.0F,
        0,
        2,
        1|2|8|16|32|128,
        0,
        50,
        255,
        0,
        "Astro",
        "Astro",
        0,
        0,
        8500,
        9500,
        true,
        true,
        10,
        1,
        1,
        Dyes._NULL,
        "custom",
        false,
        "");

    @SuppressWarnings("unused")

    @Override
    public short[] getRGBA() {
        return new short[0];
    }

    @Override
    public boolean contains(SubTag aTag) {
        return false;
    }

    @Override
    public ISubTagContainer add(SubTag... aTags) {
        return null;
    }

    @Override
    public boolean remove(SubTag aTag) {
        return false;
    }
}
