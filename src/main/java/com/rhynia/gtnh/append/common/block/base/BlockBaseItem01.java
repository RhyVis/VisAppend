package com.rhynia.gtnh.append.common.block.base;

import static com.rhynia.gtnh.append.api.util.MetaItemStack.initMetaItemStack;
import static com.rhynia.gtnh.append.api.util.MetaItemStack.metaItemStackTooltipsAdd;
import static com.rhynia.gtnh.append.client.VA_Tab.tabMetaBlock01;
import static com.rhynia.gtnh.append.common.block.BlockBasic.MetaBlock01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.util.GT_LanguageManager;

public class BlockBaseItem01 extends ItemBlock {

    // region statics

    public static final Map<Integer, String[]> MetaBlockTooltipsMap01 = new HashMap<>();
    public static final Set<Integer> MetaBlockSet01 = new HashSet<>();

    // endregion

    // region Constructors

    public BlockBaseItem01(Block aBlock) {
        super(aBlock);
        setHasSubtypes(true);
        setMaxDamage(0);
        this.setCreativeTab(tabMetaBlock01);
    }

    // endregion

    // region MetaBlock Generators

    public static ItemStack initMetaBlock01(String i18nName, int Meta) {
        return initMetaItemStack(i18nName, Meta, MetaBlock01, MetaBlockSet01);
    }

    public static ItemStack initMetaBlock01(String i18nName, int Meta, String[] tooltips) {
        if (tooltips != null) {
            metaItemStackTooltipsAdd(MetaBlockTooltipsMap01, Meta, tooltips);
        }
        return initMetaBlock01(i18nName, Meta);
    }

    // endregion

    // region Member Variables

    public final String mNoMobsToolTip = GT_LanguageManager
        .addStringLocalization("gt.nomobspawnsonthisblock", "Mobs cannot Spawn on this Block");
    public final String mNoTileEntityToolTip = GT_LanguageManager
        .addStringLocalization("gt.notileentityinthisblock", "This is NOT a TileEntity!");

    // endregion

    // region Overrides

    /**
     * Handle the tooltips.
     */
    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings({ "unchecked" })
    public void addInformation(ItemStack aItemStack, EntityPlayer p_77624_2_, List theTooltipsList,
        boolean p_77624_4_) {
        int meta = aItemStack.getItemDamage();
        if (null != MetaBlockTooltipsMap01.get(meta)) {
            String[] tooltips = MetaBlockTooltipsMap01.get(meta);
            theTooltipsList.addAll(Arrays.asList(tooltips));
        }
        theTooltipsList.add(mNoMobsToolTip);
        theTooltipsList.add(mNoTileEntityToolTip);
    }

    @Override
    public String getUnlocalizedName(ItemStack aStack) {
        return this.field_150939_a.getUnlocalizedName() + "." + this.getDamage(aStack);
    }

    @Override
    public int getMetadata(int aMeta) {
        return aMeta;
    }

    // endregion
}
