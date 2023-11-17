package com.rhynia.gtnh.append.nei;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.rhynia.gtnh.append.Tags;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIConfig implements IConfigureNEI {

    public static boolean isAdded = true;

    public static void hide(Block aBlock) {
        API.hideItem(new ItemStack(aBlock, 1));
    }

    public static void hide(Item aItem) {
        API.hideItem(new ItemStack(aItem, 1));
    }

    @Override
    public void loadConfig() {
        isAdded = true;
    }

    @Override
    public String getName() {
        return Tags.MODNAME + " NEI Plugin";
    }

    @Override
    public String getVersion() {
        return Tags.VERSION;
    }
}
