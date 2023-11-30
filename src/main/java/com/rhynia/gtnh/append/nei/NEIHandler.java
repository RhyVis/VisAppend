package com.rhynia.gtnh.append.nei;

import net.minecraft.nbt.NBTTagCompound;

import com.rhynia.gtnh.append.Tags;

import cpw.mods.fml.common.event.FMLInterModComms;

public class NEIHandler {

    public static void IMCSender() {
        // Handler
        sendHandler("append.recipe.UltimateHeaterRecipes", "gregtech:gt.blockmachines:17502");
        sendHandler("append.recipe.AssemblyMatrixRecipes", "gregtech:gt.blockmachines:17503");
        sendHandler("append.recipe.SuperconductingBinderRecipes", "gregtech:gt.blockmachines:17504");
        // Catalyst
        sendCatalyst("append.recipe.UltimateHeaterRecipes", "gregtech:gt.blockmachines:17502");
        sendCatalyst("append.recipe.AssemblyMatrixRecipes", "gregtech:gt.blockmachines:17503");
        sendCatalyst("append.recipe.SuperconductingBinderRecipes", "gregtech:gt.blockmachines:17504");

    }

    private static void sendHandler(String aName, String aBlock) {
        NBTTagCompound aNBT = new NBTTagCompound();
        aNBT.setString("handler", aName);
        aNBT.setString("modName", Tags.MODNAME);
        aNBT.setString("modId", Tags.MODID);
        aNBT.setBoolean("modRequired", true);
        aNBT.setString("itemName", aBlock);
        aNBT.setInteger("handlerHeight", 135);
        aNBT.setInteger("handlerWidth", 166);
        aNBT.setInteger("maxRecipesPerPage", 2);
        aNBT.setInteger("yShift", 6);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerHandlerInfo", aNBT);
    }

    private static void sendCatalyst(String aName, String aStack) {
        NBTTagCompound aNBT = new NBTTagCompound();
        aNBT.setString("handlerID", aName);
        aNBT.setString("itemName", aStack);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerCatalystInfo", aNBT);
    }

}
