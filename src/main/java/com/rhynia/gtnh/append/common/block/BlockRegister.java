package com.rhynia.gtnh.append.common.block;

import static com.rhynia.gtnh.append.common.block.BlockBasic.EyeOfHarmonyCoreCasing;
import static com.rhynia.gtnh.append.common.block.BlockBasic.MetaBlock01;

import com.rhynia.gtnh.append.common.VA_ItemList;
import com.rhynia.gtnh.append.common.block.base.BlockBaseItem01;
import com.rhynia.gtnh.append.common.block.casings.EyeOfHarmonyCore;
import com.rhynia.gtnh.append.common.block.casings.EyeOfHarmonyCoreItem;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRegister {

    public static void registryBlocks() {
        GameRegistry.registerBlock(MetaBlock01, BlockBaseItem01.class, MetaBlock01.getUnlocalizedName());
        GameRegistry.registerBlock(
            EyeOfHarmonyCoreCasing,
            EyeOfHarmonyCoreItem.class,
            EyeOfHarmonyCoreCasing.getUnlocalizedName());
    }

    public static void registryBlockContainers() {
        VA_ItemList.Test_MetaBlock_01.set(BlockBaseItem01.initMetaBlock01("测试方块", 0));
        VA_ItemList.EOH_Core_T1.set(EyeOfHarmonyCore.eyeOfHarmonyCoreCasingMeta("测试方块", 0));
        VA_ItemList.EOH_Core_T2.set(EyeOfHarmonyCore.eyeOfHarmonyCoreCasingMeta("测试方块", 1));
        VA_ItemList.EOH_Core_T3.set(EyeOfHarmonyCore.eyeOfHarmonyCoreCasingMeta("测试方块", 2));
        VA_ItemList.EOH_Core_T4.set(EyeOfHarmonyCore.eyeOfHarmonyCoreCasingMeta("测试方块", 3));
        VA_ItemList.EOH_Core_T5.set(EyeOfHarmonyCore.eyeOfHarmonyCoreCasingMeta("测试方块", 4));
        VA_ItemList.EOH_Core_T6.set(EyeOfHarmonyCore.eyeOfHarmonyCoreCasingMeta("测试方块", 5));
        VA_ItemList.EOH_Core_T7.set(EyeOfHarmonyCore.eyeOfHarmonyCoreCasingMeta("测试方块", 6));
        VA_ItemList.EOH_Core_T8.set(EyeOfHarmonyCore.eyeOfHarmonyCoreCasingMeta("测试方块", 7));
        VA_ItemList.EOH_Core_T9.set(EyeOfHarmonyCore.eyeOfHarmonyCoreCasingMeta("测试方块", 8));
    }

    public static void registry() {
        registryBlocks();
        registryBlockContainers();
    }
}
