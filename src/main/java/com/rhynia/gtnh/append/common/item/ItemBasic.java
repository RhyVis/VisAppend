package com.rhynia.gtnh.append.common.item;

import static com.rhynia.gtnh.append.client.GTNHATab.tabMetaItem01;

import net.minecraft.item.Item;

import com.rhynia.gtnh.append.common.item.registry.RegItem;

public final class ItemBasic {

    public static final Item MetaItem01 = new RegItem("MetaItem01Base", "MetaItem01", tabMetaItem01)
        .setTextureName("append:MetaItem01/0");

}