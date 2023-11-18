package com.rhynia.gtnh.append.common.item;

import static com.rhynia.gtnh.append.client.GTNHATab.tabMetaItem01;

import com.rhynia.gtnh.append.common.item.container.ItemLapo;
import net.minecraft.item.Item;

import com.rhynia.gtnh.append.common.item.container.ItemUltimate;
import com.rhynia.gtnh.append.common.item.registry.RegItem;

public final class ItemBasic {

    public static final Item MetaItem01 = new RegItem("MetaItem01Base", "MetaItem01", tabMetaItem01)
        .setTextureName("append:MetaItem01/0");

    public static final Item LapoMatrix = new ItemLapo(tabMetaItem01).setTextureName("append:circuit/LapotronMatrix");

    public static final Item Ultimate = new ItemUltimate(tabMetaItem01).setTextureName("append:ultimate");

}
