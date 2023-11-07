package com.rhynia.gtnh.append.common.block;

import static com.rhynia.gtnh.append.common.block.BlockBasic.MetaBlock01;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRegister {

    public static void registryBlocks() {
        GameRegistry.registerBlock(MetaBlock01, BlockBasicItem.class, MetaBlock01.getUnlocalizedName());
    }
}
