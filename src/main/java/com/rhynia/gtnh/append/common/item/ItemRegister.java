package com.rhynia.gtnh.append.common.item;

import static com.rhynia.gtnh.append.common.item.container.RegMetaItem01.initItem01;
import static com.rhynia.gtnh.append.common.item.container.RegMetaItem02.initItem02;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import com.rhynia.gtnh.append.common.VA_ItemList;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegister {

    public static void registryItems() {
        Item[] itemsToReg = { ItemBasic.MetaItem01, ItemBasic.MetaItem02, ItemBasic.Ultimate };

        for (Item item : itemsToReg) {
            GameRegistry.registerItem(item, item.getUnlocalizedName());
        }

    }

    // spotless:off
    public static void registryItemContainers() {

        // region Meta Item 01

        // Mat
        VA_ItemList.Test01.set(initItem01("调试占位", 0, new String[]{"我相信它没什么用"}));
        VA_ItemList.LapoMatrix.set(initItem01("兰波顿矩阵", 1, new String[]{"兰波顿密度达到了极致"}));
        VA_ItemList.CrystalMatrix.set(initItem01("晶体矩阵", 2, new String[]{"用于批量生产各类晶体芯片"}));
        VA_ItemList.DenseMicaInsulatorFoil.set(initItem01("致密绝缘云母", 3, new String[]{"16倍的绝缘性能!"}));
        VA_ItemList.PreTesseract.set(initItem01("准超立方体", 4, new String[]{"即将达到高维工程学"}));

        // Res
        VA_ItemList.AstriumInfinityGem.set(initItem01("星极", 1001, new String[]{"Aμⁿ", "能量络合物"}));
        VA_ItemList.AstriumInfinityComplex.set(initItem01("星矩", 1002, new String[]{"-[Aμⁿ-Aμⁿ]-", EnumChatFormatting.DARK_RED + "高维能量络合物"}));
        VA_ItemList.AstriumInfinityComplex.set(initItem01("星规", 1003, new String[]{
            "┌Aμⁿ-Aμⁿ┐",
            "└Aμⁿ-Aμⁿ┘",
            EnumChatFormatting.DARK_PURPLE + "时空之空洞"}));

        // Lens
        VA_ItemList.LensAstriumInfinity.set(initItem01("星极天体透镜", 3001, new String[]{"能量聚集于此"}));
        VA_ItemList.LensAstriumMagic.set(initItem01("星芒魔力透镜", 3002, new String[]{"我想它能自行产生魔力"}));
        VA_ItemList.LensPrimoium.set(initItem01("原石透镜", 3003, new String[]{"异世界的能量?"}));
        VA_ItemList.LensOriginium.set(initItem01("源石透镜", 3004, new String[]{"操作时请注意源石尘"}));

        // endregion

        // region Meta Item 02
        VA_ItemList.Calibration.set(initItem02("标定指示", 0, new String[]{"记录着GTNH世界运行的原理"}));
        VA_ItemList.Assembly_DTPF.set(initItem02("套件-DTPF", 1, new String[]{"内置永恒锻炉"}));
        // endregion

        // Special Item
        VA_ItemList.ItemUltimate.set(new ItemStack(ItemBasic.Ultimate, 1));
    }
    // spotless:on

    public static void registry() {
        registryItems();
        registryItemContainers();
    }
}
