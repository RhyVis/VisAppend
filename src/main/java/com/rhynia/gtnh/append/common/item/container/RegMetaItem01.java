package com.rhynia.gtnh.append.common.item.container;

import static com.rhynia.gtnh.append.api.util.MetaItemStack.initMetaItemStack;
import static com.rhynia.gtnh.append.api.util.MetaItemStack.metaItemStackTooltipsAdd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.rhynia.gtnh.append.client.item.ItemStaticDataClientOnly;
import com.rhynia.gtnh.append.common.item.ItemBasic;
import com.rhynia.gtnh.append.common.item.registry.RegItemBasic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RegMetaItem01 extends RegItemBasic {

    /**
     * A Set contains the meta value that has been used.
     */
    public static final Set<Integer> Meta01Set = new HashSet<>();
    public static final Map<Integer, String[]> MetaItemTooltipsMap01 = new HashMap<>();

    private final String unlocalizedName;

    /**
     * Create the basic item MetaItem01.
     */
    public RegMetaItem01(String aName, String aMetaName, CreativeTabs aCreativeTabs) {
        super(aName, aMetaName, aCreativeTabs);
        this.unlocalizedName = aMetaName;
    }

    /**
     * The method about creating Items with ItemStack form by Meta Item System.
     * Use this method to create Items at ItemList.
     *
     * @param aName The name of your creating item.
     * @param aMeta The MetaValue of your creating item.
     * @return Return the Item with ItemStack form you create.
     */
    public static ItemStack initItem01(String aName, int aMeta) {

        return initMetaItemStack(aName, aMeta, ItemBasic.MetaItem01, Meta01Set);

    }

    public static ItemStack initItem01(String aName, int aMeta, String[] tooltips) {

        if (tooltips != null) {
            metaItemStackTooltipsAdd(MetaItemTooltipsMap01, aMeta, tooltips);
        }

        return initItem01(aName, aMeta);

    }

    /**
     * Init the basic items at the game pre init.
     */

    // region Overrides

    @Override
    public String getUnlocalizedName(ItemStack aItemStack) {
        return "va." + this.unlocalizedName + "." + aItemStack.getItemDamage();
    }

    @Override
    public String getUnlocalizedName() {
        return this.unlocalizedName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        super.registerIcons(iconRegister);
        this.itemIcon = iconRegister.registerIcon("append:MetaItem01/0");
        for (int meta : Meta01Set) {
            ItemStaticDataClientOnly.iconsMap01.put(meta, iconRegister.registerIcon("append:MetaItem01/" + meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int aMetaData) {
        return ItemStaticDataClientOnly.iconsMap01.containsKey(aMetaData)
            ? ItemStaticDataClientOnly.iconsMap01.get(aMetaData)
            : ItemStaticDataClientOnly.iconsMap01.get(0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings({ "unchecked" })
    public void addInformation(ItemStack aItemStack, EntityPlayer p_77624_2_, List theTooltipsList,
        boolean p_77624_4_) {
        int meta = aItemStack.getItemDamage();
        if (null != MetaItemTooltipsMap01.get(meta)) {
            String[] tooltips = MetaItemTooltipsMap01.get(meta);
            theTooltipsList.addAll(Arrays.asList(tooltips));
        }
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item aItem, CreativeTabs aCreativeTabs, List aList) {
        for (int Meta : Meta01Set) {
            aList.add(new ItemStack(ItemBasic.MetaItem01, 1, Meta));
        }
    }
    // endregion
}
