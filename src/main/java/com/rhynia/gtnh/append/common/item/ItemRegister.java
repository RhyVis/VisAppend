package com.rhynia.gtnh.append.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.VAItemList;

import cpw.mods.fml.common.registry.GameRegistry;

import static com.rhynia.gtnh.append.common.item.registry.RegMetaItem01.initItem01;

public class ItemRegister {

    public static void registryItems() {
        Item[] itemsToReg = { ItemBasic.Ultimate, ItemBasic.LapoMatrix, ItemBasic.CrystalMatrix };

        for (Item item : itemsToReg) {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
        }

    }

    // spotless:off
    public static void registryItemContainers() {
        // Meta Item
        VAItemList.Test.set(initItem01("调试占位",0, new String[]{ "我相信它没什么用." }));
        VAItemList.LapoMatrix.set(initItem01("兰波顿矩阵",1, new String[]{ "兰波顿密度达到了极致." }));
        VAItemList.CrystalMatrix.set(initItem01("晶体矩阵",2, new String[]{ "用于批量生产各类晶体芯片." }));
        // Special Item
        VAItemList.ItemUltimate.set(new ItemStack(ItemBasic.Ultimate,1));
        VAItemList.ItemLapoMatrix.set(new ItemStack(ItemBasic.LapoMatrix,1));
        VAItemList.ItemCrystalMatrix.set(new ItemStack(ItemBasic.CrystalMatrix,1));
    }
    // spotless:on
    public static void registry() {
        registryItems();
        registryItemContainers();
    }
}
