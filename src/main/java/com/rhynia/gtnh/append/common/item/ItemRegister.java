package com.rhynia.gtnh.append.common.item;

import com.rhynia.gtnh.append.common.item.container.ItemUltimate;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import static com.rhynia.gtnh.append.common.item.ItemBasic.MetaItem01;

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
