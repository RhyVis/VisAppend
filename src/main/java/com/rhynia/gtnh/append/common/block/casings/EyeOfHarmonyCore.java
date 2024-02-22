package com.rhynia.gtnh.append.common.block.casings;

import static com.rhynia.gtnh.append.client.block.BlockStaticDataClientOnly.iconsEyeOfHarmonyCoreMap;
import static com.rhynia.gtnh.append.common.block.BlockBasic.EyeOfHarmonyCoreCasing;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.rhynia.gtnh.append.api.util.MetaItemStack;
import com.rhynia.gtnh.append.client.VA_Tab;
import com.rhynia.gtnh.append.common.block.base.BlockBase01;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.GregTech_API;

public class EyeOfHarmonyCore extends BlockBase01 {

    public EyeOfHarmonyCore() {
        this.setHardness(9.0F);
        this.setResistance(5.0F);
        this.setHarvestLevel("wrench", 1);
        this.setCreativeTab(VA_Tab.tabMetaBlock01);
        EyeOfHarmonyCoreCasingSet.add(0);
        GregTech_API.registerMachineBlock(this, -1);
    }

    public EyeOfHarmonyCore(String unlocalizedName, String localName) {
        this();
        this.unlocalizedName = unlocalizedName;
    }

    public static Set<Integer> EyeOfHarmonyCoreCasingSet = new HashSet<>();
    private IIcon blockIcon;
    private String unlocalizedName;

    public static ItemStack eyeOfHarmonyCoreCasingMeta(String i18nName, int meta) {

        return MetaItemStack.initMetaItemStack(i18nName, meta, EyeOfHarmonyCoreCasing, EyeOfHarmonyCoreCasingSet);
    }

    @Override
    public String getUnlocalizedName() {
        return "va." + this.unlocalizedName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return iconsEyeOfHarmonyCoreMap.containsKey(meta) ? iconsEyeOfHarmonyCoreMap.get(meta)
            : iconsEyeOfHarmonyCoreMap.get(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon("append:EyeOfHarmonyCore/0");
        for (int Meta : EyeOfHarmonyCoreCasingSet) {
            iconsEyeOfHarmonyCoreMap.put(Meta, reg.registerIcon("append:EyeOfHarmonyCore/" + Meta));
        }
    }

    @Override
    public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
        if (GregTech_API.isMachineBlock(this, aWorld.getBlockMetadata(aX, aY, aZ))) {
            GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
        }
    }

    @Override
    public void breakBlock(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMetaData) {
        if (GregTech_API.isMachineBlock(this, aWorld.getBlockMetadata(aX, aY, aZ))) {
            GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubBlocks(Item aItem, CreativeTabs aCreativeTabs, List list) {
        for (int Meta : EyeOfHarmonyCoreCasingSet) {
            list.add(new ItemStack(aItem, 1, Meta));
        }
    }
}
