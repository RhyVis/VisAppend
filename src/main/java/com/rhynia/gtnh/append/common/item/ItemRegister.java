package com.rhynia.gtnh.append.common.item;

import static com.rhynia.gtnh.append.common.item.ItemBasic.MetaItem01;

import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegister {

    public static void registryItems() {
        Item[] itemsToReg = { MetaItem01 };

        for (Item item : itemsToReg) {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
        }

    }

    // spotless:off
    public static void registryItemContainers() {}
    // spotless:on
    public static void registry() {
        registryItems();
        registryItemContainers();
    }
}
