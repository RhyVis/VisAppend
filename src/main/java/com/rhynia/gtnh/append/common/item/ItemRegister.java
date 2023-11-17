package com.rhynia.gtnh.append.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.common.AppendItemList;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegister {

    public static void registryItems() {
        Item[] itemsToReg = { ItemBasic.Ultimate };

        for (Item item : itemsToReg) {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
        }

    }

    // spotless:off
    public static void registryItemContainers() {
        AppendItemList.ItemUltimate.set(new ItemStack(ItemBasic.Ultimate,1));
    }
    // spotless:on
    public static void registry() {
        registryItems();
        registryItemContainers();
    }
}
