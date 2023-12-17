package com.rhynia.gtnh.append.common.item;

import static com.rhynia.gtnh.append.common.item.registry.RegMetaItem01.initItem01;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.VAItemList;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegister {

    public static void registryItems() {
        Item[] itemsToReg = { ItemBasic.MetaItem01, ItemBasic.Ultimate, ItemBasic.LapoMatrix, ItemBasic.CrystalMatrix };

        for (Item item : itemsToReg) {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
        }

    }

    // spotless:off
    public static void registryItemContainers() {
        // Meta Item 01
        VAItemList.Test.set(initItem01("调试占位",0, new String[]{ "我相信它没什么用" }));
        VAItemList.LapoMatrix.set(initItem01("兰波顿矩阵",1, new String[]{ "兰波顿密度达到了极致" }));
        VAItemList.CrystalMatrix.set(initItem01("晶体矩阵",2, new String[]{ "用于批量生产各类晶体芯片" }));
        VAItemList.DenseMicaInsulatorFoil.set(initItem01("致密绝缘云母",3, new String[]{ "16倍的绝缘性能!" }));
        VAItemList.AstriumInfinityGem.set(initItem01("星极",4, new String[]{ "Aμⁿ", "浓缩的极限能量" }));
        VAItemList.LensAstriumInfinity.set(initItem01("星极天体透镜", 5, new String[]{ "能量聚集于此" }));
        VAItemList.LensAstriumMagic.set(initItem01("星芒魔力透镜", 6, new String[]{ "我想它能自行产生魔力" }));
        VAItemList.LensPrimoium.set(initItem01("原石透镜", 7, new String[]{ "异世界的能量?" }));
        VAItemList.LensOriginium.set(initItem01("源石透镜", 8, new String[]{ "操作时请注意源石尘" }));
        VAItemList.PreTesseract.set(initItem01("准超立方体", 9, new String[]{ "即将达到高维工程学" }));
        // Special Item
        VAItemList.ItemUltimate.set(new ItemStack(ItemBasic.Ultimate,1));
        VAItemList.ItemLapoMatrix.set(new ItemStack(ItemBasic.LapoMatrix,1));
        VAItemList.ItemCrystalMatrix.set(new ItemStack(ItemBasic.CrystalMatrix,1));
    }
    //spotless:on
    public static void registry() {
        registryItems();
        registryItemContainers();
    }
}
