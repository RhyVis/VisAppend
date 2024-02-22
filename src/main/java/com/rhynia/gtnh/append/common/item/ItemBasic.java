package com.rhynia.gtnh.append.common.item;

import static com.rhynia.gtnh.append.client.VA_Tab.tabMetaItem01;

import net.minecraft.item.Item;

import com.rhynia.gtnh.append.common.item.container.ItemUltimate;
import com.rhynia.gtnh.append.common.item.container.RegMetaItem01;
import com.rhynia.gtnh.append.common.item.container.RegMetaItem02;

public final class ItemBasic {

    public static final Item MetaItem01 = new RegMetaItem01("MetaItem01Base", "MetaItem01", tabMetaItem01)
        .setTextureName("append:MetaItem01/0");
    public static final Item MetaItem02 = new RegMetaItem02("MetaItem02Base", "MetaItem02", tabMetaItem01)
        .setTextureName("append:MetaItem02/0");
    public static final Item Ultimate = new ItemUltimate(tabMetaItem01).setTextureName("append:ultimate");
}
